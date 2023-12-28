package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.JourPatient;
import com.maternite.gestion.mesobjets.TachesService;
import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class ApiMobileController {

    // A t t r i b u t s  :
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    DemanderdvRepository demanderdvRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientfamilleRepository patientfamilleRepository;
    @Autowired
    PatientreservationRepository patientreservationRepository;
    @Autowired
    TachesService tachesService;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    TamponsRepository tamponsRepository;
    @Autowired
    FacturationRepository facturationRepository;
    @Autowired
    FactorisationrdvRepository factorisationrdvRepository;

    @PersistenceUnit
    EntityManagerFactory emf;


    // M e t h o d e s  :
    @CrossOrigin("*")
    @GetMapping(value="/getservices" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<Services> getservices(Principal principal, HttpSession session){
        List<Services> retour = servicesRepository.findAll();
        return retour;
    }

    //
    @CrossOrigin("*")
    @GetMapping(value="/sendrdvrequest/{service}/{userid}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Reponserdvrqt sendrdvrequest(@PathVariable String service
            ,@PathVariable int userid){

        Reponserdvrqt reponserdvrqt = new Reponserdvrqt();

        // Display :
        //System.out.println("service : "+service);
        //System.out.println("Idpat : "+userid);
        //System.out.println("service : "+service);

        // Get Service first :
        Services services = servicesRepository.findByLibelle(service.trim());
        if(services != null){
            // Check if the request does not exist :
            Demanderdv demanderdv =
                demanderdvRepository.
                    findByIdserAndIdpatAndIdmed(services.getIdser(),
                        userid, 0);
                /*demanderdvRepository.findByIdserAndEmail(services.getIdser(),
                        email.toLowerCase().trim());*/
            if(demanderdv == null){
                //System.out.println("Demande NULL ! ");
                // Then Create :
                Patient patient =
                    patientRepository.findByIdpat(userid);
                // Get the Hospital :
                Consultation constn =
                        consultationRepository.findTopByIdpat(patient.getIdpat());
                Facturation factn =
                        facturationRepository.findTopByIdpat(patient.getIdpat());

                //.findByEmail(email.toLowerCase().trim());
                demanderdv = new Demanderdv();
                demanderdv.setIdpat(patient==null ? 0 : patient.getIdpat());
                demanderdv.setEmail(patient.getEmail());
                demanderdv.setIdmed(0);
                demanderdv.setIdser(services.getIdser());
                demanderdv.setNom(patient.getNom()+" "+patient.getPrenom());
                demanderdv.setIdheu(0);
                demanderdv.setSuggestion("");
                try{
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    demanderdv.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                }
                catch (Exception exc){
                }
                // Save :
                Demanderdv dv = demanderdvRepository.save(demanderdv);
                // Call the tHread :
                int getHopital = constn != null ? constn.getIdhop() : factn != null ? factn.getIdhop() : 0 ;
                // Call this for suggestion :
                tachesService.suggestionRdvAutomatique(services.getIdser(), getHopital, dv);

                // Set the ANSWER :
                reponserdvrqt.setMedecin("ok");
                reponserdvrqt.setHeure("ok");
                reponserdvrqt.setJour("ok");
                reponserdvrqt.setService("ok");
            }
            else{
                // Set the ANSWER :
                reponserdvrqt.setMedecin("nok");
                reponserdvrqt.setHeure("nok");
                reponserdvrqt.setJour("nok");
                reponserdvrqt.setService("nok");
            }
        }
        else{
            // Set the ANSWER :
            reponserdvrqt.setMedecin("pok");
            reponserdvrqt.setHeure("pok");
            reponserdvrqt.setJour("pok");
            reponserdvrqt.setService("pok");
        }

        return reponserdvrqt;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getrequests/{idpat}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<Reponserdvrqt> getrequests(@PathVariable String idpat,
        Principal principal, HttpSession session){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        // Demande de RDV
        List <Object[]> resultat = emr.createQuery(
            "select distinct a.idddv, date(a.dates),d.libelle, concat(b.nom,' ',b.prenom), " +
                    "c.libelle from anf_demanderdv a inner join anf_services d on " +
                    "a.idser=d.idser left join anf_medecin b on a.idmed=b.idmed " +
                    "left join anf_heure c on a.idheu=c.idheu where a.idpat="+idpat)
                .getResultList();
        // RDV fixés par le MEDECIN :
        List <Object[]> rdvFixes = emr.createQuery(
            "select distinct 0,date(c.dates),l.libelle," +
            "concat(k.nom,' ',k.prenom),m.libelle from anf_patient a " +
            "inner join anf_consultation b on a.idpat=b.idpat inner join " +
            "anf_rdv c on c.idcon=b.idcon " +
            "inner join anf_medecin k on k.idmed=b.idmed " +
            "inner join anf_services l on l.idser=b.idser " +
            "inner join anf_heure m on m.idheu=c.heure " +
            "where a.idpat="+idpat)
                .getResultList();

        List<Reponserdvrqt> retour = new ArrayList<>();
        for (Object[] donnee : resultat){
            Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
            reponserdvrqt.setIdddv(Integer.valueOf(String.valueOf(donnee[0])));
            reponserdvrqt.setJour(String.valueOf(donnee[1]));
            reponserdvrqt.setService(String.valueOf(donnee[2]));
            reponserdvrqt.setHeure(String.valueOf(donnee[4]));
            reponserdvrqt.setMedecin(String.valueOf(donnee[3]));
            // Ajouter :
            retour.add(reponserdvrqt);
        }
        // Browse --- rdvFixes
        for (Object[] donnee : rdvFixes){
            Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
            reponserdvrqt.setIdddv(Integer.valueOf(String.valueOf(donnee[0])));
            reponserdvrqt.setJour(String.valueOf(donnee[1]));
            reponserdvrqt.setService(String.valueOf(donnee[2]));
            reponserdvrqt.setHeure(String.valueOf(donnee[4]));
            reponserdvrqt.setMedecin(String.valueOf(donnee[3]));
            // Ajouter :
            retour.add(reponserdvrqt);
        }

        // Close :
        emr.getTransaction().commit();
        emr.close();

        return retour;
    }


    // For nEW MEMEBER :
    @CrossOrigin("*")
    @GetMapping(value="/sendnewmember/{nom}/{prenom}/{numero}/{mail}/{lien}/{idpat}/{memberid}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck sendnewmember(@PathVariable String nom,
        @PathVariable String prenom, @PathVariable String numero
            , @PathVariable String mail, @PathVariable String lien,
            @PathVariable int idpat,@PathVariable int memberid){

        int lienParente = 0;
        switch (lien.trim()){
            case "Fils":
                lienParente = 1;
                break;
            case "Fille":
                lienParente = 2;
                break;
            case "Mère":
                lienParente = 3;
                break;
            case "Père":
                lienParente = 4;
                break;
            case "Grand-Père":
                lienParente = 5;
                break;
            case "Grand-Mère":
                lienParente = 6;
                break;
            case "Conjoint":
                lienParente = 7;
                break;
            case "Frère":
                lienParente = 8;
                break;
            case "Soeur":
                lienParente = 9;
                break;
            case "Autres":
                lienParente = 10;
                break;

            default:
                lienParente = 10;
                break;
        }

        // create the familly MEMBER :
        Patientfamille pf = patientfamilleRepository.findByIdpaf(memberid);
        // In case it's a new USER, instantiate :
        if(pf == null) pf = new Patientfamille();
        pf.setNom(nom);
        pf.setPrenom(prenom);
        try{
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            pf.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }
        catch (Exception exc){}
        pf.setNumero(numero);
        pf.setEmail(mail);
        pf.setLienparente(lienParente);
        pf.setIdparent(idpat);
        Patientfamille pfe = patientfamilleRepository.save(pf);
        // Keep a trace of any UPDATE :
        if(memberid > 0){
            tachesService.enregistrerJournal(
                "Modification d'un membre, Id : "+memberid,
                idpat);
        }

        ReponseUserCheck ruc = new ReponseUserCheck();
        ruc.setNom("ok");
        ruc.setUserid(pfe.getIdpaf());
        // Print :
        //System.out.println("Id Membre : "+memberid);
        return ruc;
    }


    // For nEW BOOKING :
    @CrossOrigin("*")
    @GetMapping(value="/sendnewbook/{idparente}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck sendnewbook(@PathVariable int idparente){
        int lienParente = 0;
        Patientreservation pn = new Patientreservation();
        pn.setIdpaf(idparente);
        try{
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            pn.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }
        catch (Exception exc){}
        //
        Patientreservation pne = patientreservationRepository.save(pn);
        ReponseUserCheck ruc = new ReponseUserCheck();
        ruc.setNom("ok");
        ruc.setUserid(pne.getIdpre());

        return ruc;
    }



    // Delete RDV's request :
    @CrossOrigin("*")
    @GetMapping(value="/deleterequest/{idddv}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Reponserdvrqt> deleterequest(@PathVariable int idddv){

        List<Reponserdvrqt> listRqt = new ArrayList<>();
        //
        Demanderdv demanderdv = demanderdvRepository.findByIdddv(idddv);
        Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
        reponserdvrqt.setService("");
        reponserdvrqt.setMedecin("");
        reponserdvrqt.setHeure("");
        reponserdvrqt.setJour("");
        if(demanderdv != null ){
            // Try to delete :
            demanderdvRepository.deleteByIdddv(demanderdv.getIdddv());
            reponserdvrqt.setIdddv(1);
        }
        else reponserdvrqt.setIdddv(0);

        //
        listRqt.add(reponserdvrqt);
        return listRqt;
    }


    // Delete RDV's request :
    @CrossOrigin("*")
    @GetMapping(value="/deletereservation/{idpre}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Reponserdvrqt> deletereservation(@PathVariable int idpre){

        List<Reponserdvrqt> listRqt = new ArrayList<>();
        //
        Patientreservation patientreservation =
                patientreservationRepository.findByIdpre(idpre);
        Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
        reponserdvrqt.setService("");
        reponserdvrqt.setMedecin("");
        reponserdvrqt.setHeure("");
        reponserdvrqt.setJour("");
        if(patientreservation != null ){
            // Try to delete :
            patientreservationRepository.deleteByIdpre(patientreservation.getIdpre());
            reponserdvrqt.setIdddv(1);
        }
        else reponserdvrqt.setIdddv(0);

        //
        listRqt.add(reponserdvrqt);
        return listRqt;
    }


    // Delete RDV's request :
    @CrossOrigin("*")
    @GetMapping(value="/deletemembre/{idpaf}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Reponserdvrqt> deletemembre(@PathVariable int idpaf){

        List<Reponserdvrqt> listRqt = new ArrayList<>();
        //
        Patientfamille patientfamille = patientfamilleRepository.findByIdpaf(idpaf);
        Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
        reponserdvrqt.setService("");
        reponserdvrqt.setMedecin("");
        reponserdvrqt.setHeure("");
        reponserdvrqt.setJour("");
        if(patientfamille != null ){
            // Try to delete :
            patientfamilleRepository.deleteByIdpaf(patientfamille.getIdpaf());
            reponserdvrqt.setIdddv(1);
        }
        else reponserdvrqt.setIdddv(0);

        //
        listRqt.add(reponserdvrqt);
        return listRqt;
    }



    // Identify a new PATIENT from mobile APPLICTAION :
    @CrossOrigin("*")
    @GetMapping(value="/checkuser/{email}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck checkuser(@PathVariable String email){

        //
        ReponseUserCheck ruc = new ReponseUserCheck();
        //System.out.println("email -- "+email);
        // Check PATIENT, only those who do not come from anf_patient_familly :
        Patient patient =
                patientRepository.findByEmailAndProvenance(email, 0);
        if(patient != null){
            ruc.setUserid(patient.getIdpat());
            ruc.setNom(email+"__"+patient.getNom()+"__"+patient.getPrenom());
            //System.out.println("OK -- ");
        }
        else{
            ruc.setUserid(0);
            ruc.setNom("");
            //System.out.println("NULL");
        }
        return ruc;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getfamillemembre/{idpat}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<Reponserdvrqt> getfamillemembre(@PathVariable int idpat,
            Principal principal, HttpSession session){

        List<Patientfamille> liste =
                patientfamilleRepository.findAllByIdparent(idpat);

        List<Reponserdvrqt> retour = new ArrayList<>();
        for (Patientfamille donnee : liste){
            Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
            reponserdvrqt.setJour(donnee.getNom()+" "+donnee.getPrenom());
            reponserdvrqt.setService(String.valueOf(donnee.getLienparente()));
            reponserdvrqt.setHeure(donnee.getNumero());
            reponserdvrqt.setMedecin( donnee.getEmail());
            reponserdvrqt.setIdddv(donnee.getIdpaf());
            // Ajouter :
            retour.add(reponserdvrqt);
        }
        return retour;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getreservations/{idpat}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<Reponserdvrqt> getreservations(@PathVariable int idpat,
        Principal principal, HttpSession session){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        List <Object[]> resultat = emr.createQuery(
            "select distinct concat(a.nom,' ',a.prenom),date(b.dates),b.idpre from " +
            "anf_patient_famille a inner join anf_patient_reservation b " +
            "on a.idpaf=b.idpaf where a.idparent="+idpat )
                .getResultList();

        List<Reponserdvrqt> retour = new ArrayList<>();
        for (Object[] donnee : resultat){
            Reponserdvrqt reponserdvrqt = new Reponserdvrqt();
            reponserdvrqt.setJour(String.valueOf(donnee[0]));
            reponserdvrqt.setHeure(String.valueOf(donnee[1]));
            reponserdvrqt.setService("");
            reponserdvrqt.setMedecin("");
            reponserdvrqt.setIdddv(Integer.valueOf(String.valueOf(donnee[2])));
            // Ajouter :
            retour.add(reponserdvrqt);
        }

        // Close :
        emr.getTransaction().commit();
        emr.close();

        return retour;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getusersummary/{idpat}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<ReponseSummary> getusersummary(@PathVariable int idpat,
        Principal principal, HttpSession session){

        //
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // N o m b r e       d e     R D V
        long nbreRdv = 0;
        try {
            Object nbre_consultation = em.createQuery(
                "select count(a.idddv) as tot from anf_demanderdv a " +
                "where a.idpat="+idpat).getSingleResult();
            nbreRdv = (Long) nbre_consultation;
            // RDV fixés par le NEDECIN :
            Object nbre_consultation_rdv = em.createQuery(
                "select count(c.idrdv) as tot from anf_patient a inner join " +
                "anf_consultation b on a.idpat=b.idpat inner join anf_rdv c on " +
                "c.idcon=b.idcon where a.idpat="+idpat).getSingleResult();
            nbreRdv += (Long) nbre_consultation_rdv;
        }
        catch (Exception exc){}

        // N o m b r e       d e     M E M B R E
        long nbreMembre = 0;
        try {
            Object nbre_consultation = em.createQuery(
                    "select count(a.idpaf) as tot from anf_patient_famille a " +
                            "where a.idparent="+idpat).getSingleResult();
            nbreMembre = (Long) nbre_consultation;
        }
        catch (Exception exc){}

        // N o m b r e       d e       R E S E R V A T I O N  :
        long nbreReservation = 0;
        try {
            Object nbre_consultation = em.createQuery(
                "select count(b.idpre) as tot from anf_patient_famille a inner " +
                "join anf_patient_reservation b on a.idpaf=b.idpaf " +
                "where a.idparent="+idpat).getSingleResult();
            nbreReservation = (Long) nbre_consultation;
        }
        catch (Exception exc){}

        // N o m b r e       d e     C O N S U L T A T I O N S  :
        long nbreConsultation = 0;
        try {
            Object nbre_consultation = em.createQuery(
                "select count(distinct b.idcon) from anf_patient a inner join anf_consultation " +
                "b on a.idpat=b.idpat " +
                "inner join anf_ordonnance c on c.idcon=b.idcon " +
                "inner join anf_hopital d on b.idhop=d.idhop " +
                "where a.idpat="+idpat).getSingleResult();
            nbreConsultation = (Long) nbre_consultation;
        }
        catch (Exception exc){}


        // Close :
        em.getTransaction().commit();
        em.close();

        // set the RESULT :
        List<ReponseSummary> retour = new ArrayList<>();
        ReponseSummary reponseSummary = new ReponseSummary();
        reponseSummary.setMembre((int)nbreMembre);
        reponseSummary.setRdv((int)nbreRdv);
        reponseSummary.setReservation((int)nbreReservation);
        reponseSummary.setConsultation((int)nbreConsultation);
        //
        retour.add(reponseSummary);
        return retour;
    }




    @CrossOrigin("*")
    @GetMapping(value="/getconsultations/{idpat}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<ReponseConsultation> getconsultations(@PathVariable int idpat){

        //
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List <Object[]> resultat = em.createQuery(
            "select date(b.dates),b.heure,d.libelle,b.idcon,count(c.idord) from " +
            "anf_patient a inner join " +
            "anf_consultation b on a.idpat=b.idpat " +
            "inner join anf_ordonnance c on c.idcon=b.idcon " +
            "inner join anf_hopital d on b.idhop=d.idhop " +
            "where a.idpat=" +idpat+
            " group by date(b.dates),b.heure,d.libelle,b.idcon order by date(b.dates) desc")
                .getResultList();

        // Close :
        em.getTransaction().commit();
        em.close();

        List<ReponseConsultation> retour = new ArrayList<>();
        for (Object[] donnee : resultat){
            ReponseConsultation reponse = new ReponseConsultation();
            reponse.setJour(String.valueOf(donnee[0]));
            reponse.setHeure(String.valueOf(donnee[1]));
            reponse.setHopital(String.valueOf(donnee[2]));
            reponse.setIdcon(Integer.valueOf(String.valueOf(donnee[3])));
            reponse.setTotal(Integer.valueOf(String.valueOf(donnee[4])));
            // Ajouter :
            retour.add(reponse);
        }

        return retour;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getordonnances/{idcon}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<ReponseOrdonnance> getordonnances(@PathVariable int idcon){

        //
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List <Object[]> resultat = em.createQuery(
            "select distinct a.prescription, b.libelle,c.libelle,d.libelle,e.libelle, " +
            "concat(g.nom,' ',g.prenom), h.libelle from anf_ordonnance a inner join " +
            "anf_quantite b on a.quantite=b.idqte " +
            "inner join anf_posologie c on c.idpos=a.posologie " +
            "inner join anf_dosage d on d.iddos=a.dosage " +
            "inner join anf_type e on e.idtyp=a.typemedic " +
            "inner join anf_consultation f on f.idcon=a.idcon " +
            "inner join anf_medecin g on g.idmed=f.idmed " +
            "inner join anf_hopital h on h.idhop=f.idhop where a.idcon="+idcon)
                .getResultList();

        // Close :
        em.getTransaction().commit();
        em.close();

        List<ReponseOrdonnance> retour = new ArrayList<>();
        for (Object[] donnee : resultat){
            ReponseOrdonnance reponse = new ReponseOrdonnance();
            reponse.setMedicament(String.valueOf(donnee[0]));
            reponse.setQuantite(String.valueOf(donnee[1]));
            reponse.setPosologie(String.valueOf(donnee[2]));
            reponse.setDosage(String.valueOf(donnee[3]));
            reponse.setTypes(String.valueOf(donnee[4]));
            reponse.setMedecin(String.valueOf(donnee[5]));
            reponse.setHopital(String.valueOf(donnee[6]));
            // Ajouter :
            retour.add(reponse);
        }

        return retour;
    }


    // GEt the MEDECIN signature :
    @CrossOrigin("*")
    @GetMapping(value="/getsignatures/{idcon}" ,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<ReponseConsultation> getsignatures(@PathVariable int idcon){

        //
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        Tampons tampons = tamponsRepository.findTopByIdmed(consultation.getIdmed());
        //
        ReponseConsultation reponse = new ReponseConsultation();
        reponse.setJour("");
        reponse.setHeure("");
        reponse.setHopital(tampons.getTampon());
        reponse.setIdcon(0);
        reponse.setTotal(0);
        //
        List<ReponseConsultation> retour= new ArrayList<>();
        retour.add(reponse);
        return retour;
    }

}
