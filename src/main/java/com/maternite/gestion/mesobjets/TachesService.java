package com.maternite.gestion.mesobjets;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TachesService {

    // Attributes :
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    AffectationservRepository affectationservRepository;

    @Autowired
    AssignationRepository assignationRepository;

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    RendezvousRepository rendezvousRepository;
    @Autowired
    HeureRepository heureRepository;
    @Autowired
    DemanderdvRepository demanderdvRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    FactorisationrdvRepository factorisationrdvRepository;

    @PersistenceUnit
    EntityManagerFactory emf;



    // Methods :
    @Async
    public void envoiMail(String objet, String message, String[] destinataire){
        //
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try{
            //System.out.println("C'est bon ");
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,
                    "utf-8");
            StringBuilder contenu = new StringBuilder();
            contenu.append("<h2> Information </h2>");
            contenu.append("<div><p>"+message+"</p></div>");
            //
            helper.setText(String.valueOf(contenu), true);
            helper.setTo(destinataire[0]);
            // For CC :
            if(destinataire.length > 1) {
                for (int i=1; i < destinataire.length; i++) {
                    helper.setCc(destinataire[i]);
                }
            }
            helper.setSubject(objet);
            helper.setFrom("bendressouarnaud@gmail.com");
            emailSender.send(mimeMessage);
        }
        catch (Exception exc){
            enregistrerJournal(exc.getMessage());
        }
    }


    // Methods :
    @Async
    public void envoiMailRdv(String medecin, String dates, String heure,
        String patient){
        // Send a mail :
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
                    "utf-8");
            StringBuilder contenu = new StringBuilder();
            contenu.append("<table border='1'>");
            contenu.append("<tr style='background-color:#D3D8E1'>" +
                    "<th>Med&eacute;cin</th><th>Date</th>");
            contenu.append("<th>Heure</th></tr>");

            // Populate :
            contenu.append("<tr><td>Dr " + medecin + "</td>" +
                    "<td>" + dates + "</td>" +
                    "<td>" + heure + "</td></tr>");
            // ------
            helper.setText(String.valueOf(contenu), true);
            helper.setTo(patient);
            helper.setCc("ngbandamakonan@gmail.com");
            helper.setSubject("Confirmation de RDV");
            helper.setFrom("bendressouarnaud@gmail.com");
            try {
                emailSender.send(mimeMessage);
            } catch (Exception exc) {
                enregistrerJournal(exc.getMessage());
            }
        } catch (Exception exc) {
            enregistrerJournal(exc.getMessage());
        }
    }

    // Run Journal
    public void enregistrerJournal(String message, int... element){
        try{
            Journal journal = new Journal();
            journal.setAction(message);
            if(element.length > 0) journal.setIdmed(element[0]);
            else journal.setIdmed(0);
            String heure = new SimpleDateFormat("HH:mm").format(new Date());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            journal.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            journal.setHeure(heure);
            journalRepository.save(journal);
        }
        catch (Exception exc){
        }
    }

    // Sugestions et Fixations de RDV automatiques  , utilisons varargs:
    @Async
    public void suggestionRdvAutomatique(int idService, int hopital,
        Demanderdv dv, Integer... dateEventuelle){

        String messagePatient = "";
        Date mdate = null;


        //
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
            // Recuperer la liste des medecins :
            List<Object[]> listeGMedecins =
                em.createQuery("select distinct a.idmed,a.email from anf_medecin a " +
                    "inner join anf_affec_service b on a.idmed=b.idmed " +
                    "inner join anf_assignation c on c.idmed=a.idmed " +
                    "where c.idhop="+hopital+" and b.idser="+
                        idService).getResultList();

            List<Affectationserv> listeMedecins =
                    affectationservRepository.findAllByIdser(idService);
            List<Medecin> listeIdMedecins = new ArrayList<>();
            List<Integer> listeSetIdMedecins = new ArrayList<>();
            String iDsMedecins = "";
            int jourRetenu = 0;

            switch (listeGMedecins.size()){
                case 0:
                    // Nothing :
                    break;
                case 1:
                    iDsMedecins="("+String.valueOf(listeGMedecins.get(0)[0])+")";
                    break;
                default:
                    iDsMedecins="(";
                    for(int i=0;i < listeGMedecins.size(); i++){
                        if(i==0){
                            iDsMedecins+=String.valueOf(listeGMedecins.get(i)[0]);
                        }
                        else{
                            iDsMedecins+=","+String.valueOf(listeGMedecins.get(i)[0]);
                        }
                    }
                    iDsMedecins=")";
                    break;
            }

            // S'il n'y a aucun MEDECIN assigné au SERVICE, arreter le processus :
            if(listeGMedecins.size() == 0) return;

            // Garder la liste des Identifiants des MEDECINS :
            //for(Affectationserv donnee : listeMedecins){
            for(Object[] donnee : listeGMedecins){
                listeSetIdMedecins.add(
                        Integer.valueOf(String.valueOf(donnee[0])));
                listeIdMedecins.add(
                    medecinRepository.findByIdmed(
                         Integer.valueOf(String.valueOf(donnee[0]))));
            }
            // Obtenir le Calendrier :
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int jourDeLaSemaine = calendar.get(Calendar.DAY_OF_WEEK);

            String currentdate = "";
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            if(dateEventuelle.length == 0) {
                //
                jourRetenu = jourDeLaSemaine == 7 ? 2 : 1;
                c.add(Calendar.DATE, jourRetenu);
                dt = c.getTime();
                currentdate = new SimpleDateFormat("yyyy-MM-dd").format(dt);
            }
            else {
                //
                jourRetenu = dateEventuelle[0] + 1;
                c.add(Calendar.DATE, jourRetenu);
                dt = c.getTime();
                currentdate = new SimpleDateFormat("yyyy-MM-dd").format(dt);
            }

            //
            Date dte = new SimpleDateFormat("yyyy-MM-dd").
                parse(currentdate);
            mdate = dte;
            Factorisationrdv fv =
                factorisationrdvRepository.
                    findTopByIdserAndDatesAndIdmedIn(idService, dte,
                            listeSetIdMedecins);
            // Set our Medecins :
            List<Medecin> currentMedecin = new ArrayList<>();
            if(fv != null) {
                currentMedecin.add(medecinRepository.findByIdmed(fv.getIdmed()));
            }

            // Vérifier si il y a des HEURES encore disponible pour le service demandé :
            int heureDisponible = 0;
            try {
                Object max_heure = em.createQuery(
                    "select count(a.heure) from anf_factorisationrdv a where " +
                    "a.dates='"+currentdate+"' and a.idmed in "+iDsMedecins+
                    " and a.idser=" + idService+
                        " and a.hopital="+hopital).getSingleResult();
                heureDisponible = (int) max_heure;
            }
            catch (Exception exc){}

            // Si on a plus se 11 demandes de RDV, reporter sur un autre JOUR :
            if(heureDisponible < 11){

                // Set The EMAIL :
                Patient patient = patientRepository.findByIdpat(dv.getIdpat());

                // Obtenir une plage d'heures :
                int hMax = 0;
                try {
                    Object max_heure = em.createQuery(
                        "select max(a.heure) from anf_factorisationrdv a where " +
                        "a.dates='"+currentdate+"' and a.idmed in "+iDsMedecins+
                        " and a.idser=" + idService+" and a.hopital="+hopital).getSingleResult();
                    hMax = (int) max_heure;
                }
                catch (Exception exc){}

                em.getTransaction().commit();
                em.close();

                // Obtenir une approximation :
                Heure getHeureAvant = heureRepository.findByIdheu((hMax == 0 || hMax ==1) ? 1 : hMax == 25 ? 24 : hMax);
                Heure getHeureApres = heureRepository.findByIdheu((hMax == 0 || hMax ==1)  ? 2 : hMax == 25 ? 25 : (hMax+1));
                //Heure getHeureApres = heureRepository.findByIdheu(hMax == 25 ? 25 : (hMax+1));

                StringBuilder nomSuggestionsMedecins = new StringBuilder();
                messagePatient = "RDV possible le : "+currentdate+"   |   "+
                        getHeureAvant.getLibelle()+"  -  "+
                        getHeureApres.getLibelle()+
                "    |   Une confirmation précise vous sera transmise dans un nouveau mail.";
                if(fv != null){
                    // Le meme Medecin, facile donc :
                    if(listeIdMedecins.size() > 1) {
                        // L'oter de la liste des Medecins, si possible :
                        listeIdMedecins.remove(currentMedecin.get(0));
                        for(int j=0;j<listeIdMedecins.size();j++){
                            nomSuggestionsMedecins.append(listeIdMedecins.get(j).getNom());
                            nomSuggestionsMedecins.append(" ");
                            nomSuggestionsMedecins.append(listeIdMedecins.get(j).getPrenom());
                            if(j==1) {
                                nomSuggestionsMedecins.append("  |  ");
                                break;
                            }
                            nomSuggestionsMedecins.append("  |  ");
                        }
                    /* Maintenant obtenir les HEURES libres pour le jour
                    en fonction du service : */
                        nomSuggestionsMedecins.append(currentdate+
                            "   |   "+
                                getHeureAvant.getLibelle()+"  -  "+
                                getHeureApres.getLibelle());
                    }
                    else{
                        // On a un seul medecin par défaut :
                        nomSuggestionsMedecins.append(listeIdMedecins.get(0).getNom());
                        nomSuggestionsMedecins.append(" ");
                        nomSuggestionsMedecins.append(listeIdMedecins.get(0).getPrenom());
                        /* HEURES  : */
                        nomSuggestionsMedecins.append("  |   "+currentdate+
                                "   |   "+
                                getHeureAvant.getLibelle()+"  -  "+
                                getHeureApres.getLibelle());
                    }
                    dv.setSuggestion(nomSuggestionsMedecins.toString());
                    demanderdvRepository.save(dv);
                    //
                    String[] tabAdresse = {patient.getEmail()};
                    envoiMail("Prise en charge du RDV demandé",
                            messagePatient, tabAdresse);
                }
                else{
                    // Il n'y a pas de RDV prélablement pris :
                    for(int j=0;j<listeIdMedecins.size();j++){
                        nomSuggestionsMedecins.append(listeIdMedecins.get(j).getNom());
                        nomSuggestionsMedecins.append(" ");
                        nomSuggestionsMedecins.append(listeIdMedecins.get(j).getPrenom());
                        if(j==1) {
                            nomSuggestionsMedecins.append("  |  ");
                            break;
                        }
                        nomSuggestionsMedecins.append("  |  ");
                    }
                    /* HEURES  : */
                    nomSuggestionsMedecins.append(currentdate+
                            "   |   "+
                            getHeureAvant.getLibelle()+"  -  "+
                            getHeureApres.getLibelle());
                    dv.setSuggestion(nomSuggestionsMedecins.toString());
                    demanderdvRepository.save(dv);
                    //
                    String[] tabAdresse = {patient.getEmail()};
                    envoiMail("Prise en charge du RDV demandé",
                            messagePatient, tabAdresse);
                }
            }
            else{

                em.getTransaction().commit();
                em.close();

                // La recursivité :
                suggestionRdvAutomatique(idService, hopital,
                        dv, jourRetenu);
            }
        }
        catch (Exception exc){
            //Journal jrnl = new Journal();
            //jrnl.setHeure("");
            //jrnl.setIdmed(1);
            //jrnl.setDates(mdate);
            //jrnl.setAction(exc.toString());
            //journalRepository.save(jrnl);
        }



        /*
        switch (jourDeLaSemaine){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                //   Dimanche - Vendredi
                break;

            case 7:
                //   Samedi
                c.add(Calendar.DATE, 2); // +2j pour attenidre LUNDI :
                dt = c.getTime();
                currentdate = new SimpleDateFormat("yyyy-MM-dd").format(dt);
                System.out.println("Jour : "+currentdate);
                break;
        }
        */

    }

}
