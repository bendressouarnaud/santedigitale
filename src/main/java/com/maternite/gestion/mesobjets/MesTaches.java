package com.maternite.gestion.mesobjets;

import com.maternite.gestion.beans.Heure;
import com.maternite.gestion.beans.Medecin;
import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MesTaches {

    // A t t r i b u t e s :
    int compteur = 0;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RendezvousRepository rendezvousRepository;
    @Autowired
    HeureRepository heureRepository;

    @Autowired
    JavaMailSender emailSender;
    @PersistenceUnit
    EntityManagerFactory emf;

    // initialDelay : 5 s
    // fixedDelay   : 1 mn
    //@Scheduled( initialDelay = 5 * 1000, fixedDelay = 3600 * 1000)
    //@Scheduled(cron="0 0 9 * * *", zone="Africa/Nouakchott")  // tous les jours à 9h
    @Scheduled(cron="0 15 9 * * *", zone="Africa/Nouakchott")  // tous les jours à 9h
    public void execution(){
        // Envoyer les EMAILS de rappels :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get first DOCTORS' IDS for who registred a consultation :
        List<Object> listeDocteursIds = em.createQuery(
            "select distinct a.idmed from anf_medecin a inner join anf_consultation b on " +
            "a.idmed=b.idmed inner join anf_patient c on b.idpat=c.idpat inner join anf_rdv d " +
            "on b.idcon=d.idcon where timestampdiff( DAY,d.dates,date(now())) in (-3,-1)").
                getResultList();
        if(listeDocteursIds != null) {
            if (listeDocteursIds.size() > 0) {
                //
                for (Object donneesIDs : listeDocteursIds) {

                    List<Object[]> listeRappels = em.createQuery(
                            "select distinct concat(a.nom,' ',a.prenom) as medecin, a.email," +
                                    "concat(c.nom,' ',c.prenom) as patient, date(d.dates),e.libelle,f.libelle " +
                                    ",c.telephone from anf_medecin a inner join anf_consultation b on a.idmed=b.idmed " +
                                    "inner join anf_patient c on b.idpat=c.idpat " +
                                    "inner join anf_rdv d on b.idcon=d.idcon " +
                                    "inner join anf_heure e on d.heure=e.idheu " +
                                    "inner join anf_hopital f on b.idhop=f.idhop " +
                                    "where timestampdiff(DAY,d.dates,date(now())) in (-3,-1) and a.idmed=" +
                                    String.valueOf(donneesIDs)).
                            getResultList();

                    // Browse :
                    if (listeRappels.size() > 0) {
                        try {
                            MimeMessage mimeMessage = emailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
                                    "utf-8");
                            StringBuilder contenu = new StringBuilder();
                            contenu.append("<table border='1'>");
                            contenu.append("<tr style='background-color:#D3D8E1'><th>M&eacute;decin</th>" +
                                    "<th>Patient</th><th>Num. Patient</th><th>Date</th>");
                            contenu.append("<th>Heure</th><th>H&ocirc;pital</th></tr>");

                            // Set Doctors email ARRAY for Carbon Copy :
                            //String[] doctorsEmail = new String[listeRappels.size()];
                            String doctorEmail = "";
                            int cpt_email = 0;
                            for (Object[] donnees : listeRappels) {
                                // Prepare our MAIL :
                                contenu.append("<tr><td>" + String.valueOf(donnees[0]) + "</td>" +
                                        "<td>" + String.valueOf(donnees[2]) + "</td>" +
                                        "<td>" + String.valueOf(donnees[6]) + "</td>" +
                                        "<td>" + String.valueOf(donnees[3]) + "</td>" +
                                        "<td>" + String.valueOf(donnees[4]) + "</td><td>" + String.valueOf(donnees[5]) +
                                        "</td></tr>");
                                // Set the CC :
                                doctorEmail = String.valueOf(donnees[1]);
                                //doctorsEmail[cpt_email] = "gbandama@gmail.com";
                                //cpt_email++;
                            }
                            // Close :
                            contenu.append("</table>");

                            helper.setText(String.valueOf(contenu), true);
                            //helper.setTo("ngbandamakonan@gmail.com");
                            helper.setTo(doctorEmail);
                            //helper.setTo(utilis.getEmail());
                            helper.setCc("ngbandamakonan@gmail.com");
                            helper.setSubject("Rappel des RDV");
                            helper.setFrom("bendressouarnaud@gmail.com");
                            try {
                                emailSender.send(mimeMessage);
                            } catch (Exception exc) {
                                System.out.println("Exception lors de l'envoi du mail : " + exc.toString());
                            }
                        } catch (Exception exc) {
                            //System.out.println("Exception rencontree : " + exc.toString());
                        }
                    }
                }
            }
        }

        // Close :
        em.getTransaction().commit();
        em.close();
    }




    @Scheduled(cron="0 30 15-18 * * *", zone="Africa/Nouakchott")  // tous les jours à 9h
    public void executionResume() {
        // Envoyer les EMAILS de rappels :
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // get first DOCTORS' IDS for who registred a consultation :
            List<Object[]> listeStat = em.createQuery(
                    "select a.libelle,date(b.dateconsultation) as dte, " +
                            "count(b.idcon) as tot from anf_services a inner join anf_consultation b " +
                            "on a.idser=b.idser where date(b.dateconsultation) = date(now()) " +
                            "group by a.libelle,date(b.dateconsultation)").
                    getResultList();

            // Close :
            em.getTransaction().commit();
            em.close();

            // Keep the DATE :
            String keepDate =  "";

            if (listeStat != null) {
                if (listeStat.size() > 0) {
                    //
                    MimeMessage mimeMessage = emailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
                            "utf-8");
                    StringBuilder contenu = new StringBuilder();
                    contenu.append("<table border='1'>");
                    contenu.append("<tr style='background-color:#D3D8E1'><th>Service</th>" +
                            "<th>Date</th><th>Total</th></tr>");

                    for (Object[] donnees : listeStat) {
                        // Keep DATE :
                        keepDate =  String.valueOf(donnees[1]);

                        contenu.append("<tr><td>" + String.valueOf(donnees[0]) + "</td>" +
                            "<td>" + String.valueOf(donnees[1]) + "</td>" +
                            "<td>" + String.valueOf(donnees[2]) + "</td></tr>");
                    }
                    // Close :
                    contenu.append("</table>");

                    // Envoi du MAIL :
                    helper.setText(String.valueOf(contenu), true);
                    helper.setTo("ngbandamakonan@gmail.com");
                    helper.setSubject("Bilan du "+ keepDate +" - Clinique ESPERANCE");
                    helper.setFrom("bendressouarnaud@gmail.com");
                    emailSender.send(mimeMessage);
                }
            }
        }
        catch (Exception exc){

        }
    }



}
