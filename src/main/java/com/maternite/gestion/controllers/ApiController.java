package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.*;
import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class ApiController {

    // A t t r i b u t e s :
    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    ProfessionRepository professionRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ObservationRepository observationRepository;
    @Autowired
    AutorisationRepository autorisationRepository;
    @Autowired
    NaturefichierRepository naturefichierRepository;
    @Autowired
    CoordonneesRepository coordonneesRepository;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    HeureRepository heureRepository;
    @Autowired
    ServiceoptionRepository serviceoptionRepository;
    @Autowired
    QuantiteRepository quantiteRepository;
    @Autowired
    PosologieRepository posologieRepository;
    @Autowired
    DosageRepository dosageRepository;
    @Autowired
    TypesmedicRepository typesmedicRepository;
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    AssignationRepository assignationRepository;
    @Autowired
    MedicamentRepository medicamentRepository;
    @Autowired
    ConstanteRepository constanteRepository;
    @Autowired
    PatientrecoursRepository patientrecoursRepository;
    @Autowired
    AffectationservRepository affectationservRepository;
    @Autowired
    CabinetdentaireRepository cabinetdentaireRepository;
    @Autowired
    ProthesesRepository prothesesRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    @CrossOrigin("*")
    @GetMapping(value="/getpatientjour/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<JourPatient> getpatientjour(@PathVariable int idmed,
                                            Principal principal, HttpSession session){

        //Object medecinid = session.getAttribute("medecinid");
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        try {
            Medecin medecin = medecinRepository.findByIdmed(idmed);
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> resultat = new ArrayList<Object[]>();

            resultat = emr.createQuery(
                    "select date(a.dateconsultation),count(a.idpat) from anf_consultation a " +
                            "where a.idmed=" + medecin.getIdmed() +
                            " group by date(a.dateconsultation) order by date(a.dateconsultation) desc")
                    .setMaxResults(5).getResultList();
            emr.getTransaction().commit();

            // Browse listePanneau :
            //System.out.println("Taille : "+resultat.size());
            for (Object[] ob : resultat) {
                // Create a new Object :
                String dts = String.valueOf(ob[0]);
                //String dates = new SimpleDateFormat("yyyy-MM-dd").format(ob[0]);
                listeObjet.add(new JourPatient(dts,
                        (Long) ob[1]));
            }
            if (listeObjet.size() > 0) {
                // Ordonner selon le date :
                listeObjet.sort(Comparator.comparing(JourPatient::getJour));
            }

            emr.close();
        }
        catch (Exception exc){
            listeObjet.add(new JourPatient(exc.getMessage(),
                    1));
        }
        return listeObjet;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getcalendriermedecin/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<PatientBackRdv> getcalendriermedecin(@PathVariable int idmed,
        Principal principal, HttpSession session){

        //Object medecinid = session.getAttribute("medecinid");
        List<PatientBackRdv> listeObjet = new ArrayList<>();
        try {
            Medecin medecin = medecinRepository.findByIdmed(idmed);
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> resultat = new ArrayList<>();
            List<Object[]> resultatDemande = new ArrayList<>();

            // Get of Attendees list for RDV
            resultat = emr.createQuery(
                "select distinct concat(a.nom,' ',a.prenom) as patient,c.telephone" +
                ", date(d.dates),e.libelle from anf_medecin a inner join " +
                "anf_consultation b on a.idmed=b.idmed " +
                "inner join anf_patient c on b.idpat=c.idpat " +
                "inner join anf_rdv d on b.idcon=d.idcon " +
                "inner join anf_heure e on d.heure=e.idheu " +
                "inner join anf_hopital f on b.idhop=f.idhop " +
                "inner join anf_assignation g on g.idhop=b.idhop " +
                "where g.idmed="+medecin.getIdmed()).getResultList();
            // Demande de RDV confirmés :
            resultatDemande = emr.createQuery(
                "select distinct concat(a.nom,' ',a.prenom) as patient,c.telephone " +
                ", date(d.dates),e.libelle from anf_medecin a inner join " +
                "anf_consultation b on a.idmed=b.idmed " +
                "inner join anf_patient c on b.idpat=c.idpat " +
                "inner join anf_demanderdv d on d.idpat=c.idpat " +
                "inner join anf_heure e on d.idheu =e.idheu " +
                "inner join anf_assignation f on f.idhop=b.idhop " +
                "where d.secretaire > 0 and f.idmed="+medecin.getIdmed()).getResultList();
            //
            if(resultat.size() > 0){
                resultat.addAll(resultatDemande);
            }
            else if(resultatDemande.size() > 0) {
                resultat = resultatDemande;
            }

            emr.getTransaction().commit();
            emr.close();

            // Browse :
            for (Object[] ob : resultat) {
                PatientBackRdv pbr = new PatientBackRdv();
                pbr.setPatient(String.valueOf(ob[0]));
                pbr.setTelephone(String.valueOf(ob[1]));
                String[] dates = String.valueOf(ob[2]).split("-");
                pbr.setJour(Integer.valueOf(dates[2]));
                pbr.setMois(Integer.valueOf(dates[1])-1);
                pbr.setAnnee(Integer.valueOf(dates[0]));
                String[] heures = String.valueOf(ob[3]).split("h");
                pbr.setHeure(Integer.valueOf(heures[0]));
                pbr.setMinute(heures[1]);
                //
                listeObjet.add(pbr);
            }

        }
        catch (Exception exc){
        }
        return listeObjet;
    }





    @CrossOrigin("*")
    @GetMapping(value="/getcalendriermedecinpat/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<PatientBackRdv> getcalendriermedecinpat(@PathVariable int idmed,
                                                     Principal principal, HttpSession session){

        //Object medecinid = session.getAttribute("medecinid");
        List<PatientBackRdv> listeObjet = new ArrayList<>();
        try {
            Medecin medecin = medecinRepository.findByIdmed(idmed);
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> resultat = new ArrayList<>();
            List<Object[]> resultatDemande = new ArrayList<>();

            // Get of Attendees list for RDV
            resultat = emr.createQuery(
                    "select distinct concat(c.nom,' ',c.prenom) as patient,c.telephone" +
                            ", date(d.dates),e.libelle from anf_medecin a inner join " +
                            "anf_consultation b on a.idmed=b.idmed " +
                            "inner join anf_patient c on b.idpat=c.idpat " +
                            "inner join anf_rdv d on b.idcon=d.idcon " +
                            "inner join anf_heure e on d.heure=e.idheu " +
                            "inner join anf_hopital f on b.idhop=f.idhop " +
                            "inner join anf_assignation g on g.idhop=b.idhop " +
                            "where g.idmed="+medecin.getIdmed()).getResultList();
            // Demande de RDV confirmés :
            resultatDemande = emr.createQuery(
                    "select distinct concat(c.nom,' ',c.prenom) as patient,c.telephone " +
                            ", date(d.dates),e.libelle from anf_medecin a inner join " +
                            "anf_consultation b on a.idmed=b.idmed " +
                            "inner join anf_patient c on b.idpat=c.idpat " +
                            "inner join anf_demanderdv d on d.idpat=c.idpat " +
                            "inner join anf_heure e on d.idheu =e.idheu " +
                            "inner join anf_assignation f on f.idhop=b.idhop " +
                            "where d.secretaire > 0 and f.idmed="+medecin.getIdmed()).getResultList();
            //
            if(resultat.size() > 0){
                resultat.addAll(resultatDemande);
            }
            else if(resultatDemande.size() > 0) {
                resultat = resultatDemande;
            }

            emr.getTransaction().commit();
            emr.close();

            // Browse :
            for (Object[] ob : resultat) {
                PatientBackRdv pbr = new PatientBackRdv();
                pbr.setPatient(String.valueOf(ob[0]));
                pbr.setTelephone(String.valueOf(ob[1]));
                String[] dates = String.valueOf(ob[2]).split("-");
                pbr.setJour(Integer.valueOf(dates[2]));
                pbr.setMois(Integer.valueOf(dates[1])-1);
                pbr.setAnnee(Integer.valueOf(dates[0]));
                String[] heures = String.valueOf(ob[3]).split("h");
                pbr.setHeure(Integer.valueOf(heures[0]));
                pbr.setMinute(heures[1]);
                //
                listeObjet.add(pbr);
            }

        }
        catch (Exception exc){
        }
        return listeObjet;
    }




    @CrossOrigin("*")
    @GetMapping(value="/getcalendriermedecinrdv/{idser}/{secretaireid}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<PatientBackRdv> getcalendriermedecinrdv(@PathVariable int idser,
        @PathVariable int secretaireid,HttpSession session){

        Medecin medecin = medecinRepository.findByIdmed(secretaireid);
        Assignation assignation =
                assignationRepository.findByIdmed(medecin.getIdmed());

                //Object medecinid = session.getAttribute("medecinid");
        List<PatientBackRdv> listeObjet = new ArrayList<>();
        try {
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> listeRdvFixeMedecins = new ArrayList<>();

            // RDVs fixés par le medecin :
            listeRdvFixeMedecins = emr.createQuery(
                "select distinct concat(a.nom,' ',a.prenom) as medecin," +
                "date(d.dates),e.libelle from anf_medecin a inner join " +
                "anf_consultation b on a.idmed=b.idmed " +
                "inner join anf_patient c on b.idpat=c.idpat " +
                "inner join anf_rdv d on b.idcon=d.idcon " +
                "inner join anf_heure e on d.heure=e.idheu " +
                "inner join anf_hopital f on b.idhop=f.idhop " +
                "inner join anf_affec_service g on g.idmed=a.idmed " +
                "where g.idser ="+idser+" and b.idhop="+assignation.getIdhop()).getResultList();
            //System.out.println("Taille RDV fixes : "+listeRdvFixeMedecins.size());

            // RDV programmés par la SECRETAIRE :
            List<Object[]> listeRdvProgramme = new ArrayList<>();
            listeRdvProgramme = emr.createQuery(
                "select distinct concat(c.nom,' ',c.prenom) as medecin," +
                "date(b.dates) as dte, d.libelle from " +
                "anf_patient a inner join anf_demanderdv b on a.idpat=b.idpat " +
                "inner join anf_medecin c on c.idmed=b.idmed " +
                "inner join anf_heure d on d.idheu=b.idheu " +
                "where b.idser="+idser+" and b.secretaire = "+assignation.getIdhop()).
                    getResultList();

            // Merger :
            if(listeRdvFixeMedecins.size() > 0) {
                for (Object[] donnees : listeRdvProgramme) {
                    listeRdvFixeMedecins.add(donnees);
                }
            }
            else listeRdvFixeMedecins = listeRdvProgramme;

            // Browse :
            for (Object[] ob : listeRdvFixeMedecins) {
                PatientBackRdv pbr = new PatientBackRdv();
                pbr.setPatient(String.valueOf(ob[0]));
                pbr.setTelephone("");
                String[] dates = String.valueOf(ob[1]).split("-");
                pbr.setJour(Integer.valueOf(dates[2]));
                pbr.setMois(Integer.valueOf(dates[1])-1);
                pbr.setAnnee(Integer.valueOf(dates[0]));
                String[] heures = String.valueOf(ob[2]).split("h");
                pbr.setHeure(Integer.valueOf(heures[0]));
                pbr.setMinute(heures[1]);
                //
                listeObjet.add(pbr);
            }
            emr.getTransaction().commit();
            emr.close();
        }
        catch (Exception exc){
        }
        return listeObjet;
    }




    // produces = { "application/xml", "text/xml" }
    @CrossOrigin("*")
    @GetMapping(value="/getpatientgenre/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> getpatientgenre(@PathVariable int idmed,
        HttpSession session, Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);

        List <Object[]> resultat = new ArrayList<Object[]>();
        // The list of OBJECTS :
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        Journal jrnl = new Journal();

        try{
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            //
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            jrnl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));

            resultat = emr.createQuery(
                    "select a.sexe,count(a.idpat) from anf_patient a inner join " +
                            "anf_consultation b on a.idpat=b.idpat where b.idmed=" +medecin.getIdmed()+
                            " group by a.sexe" )
                    .getResultList();
            emr.getTransaction().commit();

            // Browse listePanneau :
            //System.out.println("Taille : "+resultat.size());
            for(Object[] ob : resultat){
                // Create a new Object :
                listeObjet.add(new JourPatient((String)ob[0],
                        (Long)ob[1]));
            }

            emr.close();
        }
        catch (Exception exc){
            jrnl.setHeure("");
            jrnl.setIdmed(1);
            jrnl.setAction(exc.toString());
            journalRepository.save(jrnl);
        }
        return listeObjet;
    }


    // SERVICE OPTION
    @CrossOrigin("*")
    @GetMapping(value="/getserviceoption/{service}/{hopital}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Serviceoption> getserviceoption(HttpSession session,
                                                @PathVariable int service, @PathVariable int hopital){
        // -----------------
        Serviceoption sn = serviceoptionRepository.findByIdserAndIdhop(service, hopital);
        List<Serviceoption> liste = new ArrayList<>();
        liste.add(sn);
        //
        return liste;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getnaturefichier" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Naturefichier> getnaturefichier(){
        // -----------------
        List<Naturefichier> listeNaturefichier = naturefichierRepository.findAll();
        //
        return listeNaturefichier;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getexamensliste" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Examen> getexamensliste(){
        // -----------------
        List<Examen> listeExamen = examenRepository.findAll();
        //
        return listeExamen;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getlesheures" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Heure> getlesheures(){
        // -----------------
        List<Heure> listeHeure = heureRepository.findAll();
        //
        return listeHeure;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getpatientinfo/{idpatient}" ,
            produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Patient> getpatientinfo(HttpSession session,
                                        @PathVariable int idpatient){
        // -----------------
        Patient patient = patientRepository.findByIdpat(idpatient);
        List<Patient> listePatient = new ArrayList<Patient>();
        listePatient.add(patient);
        //
        return listePatient;
    }



    @CrossOrigin("*")
    @GetMapping(value="/checkpatientwallet/{idpatient}" ,
            produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<PatientDonnee> checkpatientwallet(HttpSession session,
                                        @PathVariable int idpatient){
        // -----------------
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultat = new ArrayList<>();
        List<PatientDonnee> retour = new ArrayList<>();

        resultat = em.createQuery(
            "select b.idpat,b.montant from anf_patient a inner join anf_patient_wallet_state b \n" +
            "on a.idpat=b.idpat where a.idpat="+idpatient)
                .getResultList();
        em.getTransaction().commit();
        em.close();

        // Set the LIST :
        PatientDonnee patientDonnee = new PatientDonnee();
        if(resultat.size() > 0) {
            for (Object[] donnee : resultat) {
                patientDonnee.setIdpat(Integer.valueOf(String.valueOf(donnee[0])));
                patientDonnee.setMontant(Double.valueOf(String.valueOf(donnee[1])));
                //-----------------------
                retour.add(patientDonnee);
            }
        }
        else{
            patientDonnee.setIdpat(0);
            patientDonnee.setMontant(0.0);
            //-----------------------
            retour.add(patientDonnee);
        }

        //
        return retour;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getdoctorslist/{idpatient}/{idhop}" ,
            produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<ObjetMedecin> getdoctorslist(HttpSession session,
        @PathVariable int idpatient, @PathVariable int idhop){
        // -----------------
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> resultat = new ArrayList<Object[]>();
        resultat = emr.createQuery(
            "select a.idmed,concat(a.nom,' ',a.prenom) as medecin from " +
            "anf_medecin a inner join anf_affec_service b on a.idmed=b.idmed inner " +
            "join anf_facturation c on c.service=b.idser " +
            "inner join anf_assignation d on d.idmed=a.idmed " +
            "where idfac=(select max(d.idfac) from anf_facturation d where " +
            "d.idpat = "+idpatient+") and d.idhop="+idhop)
            .getResultList();
        emr.getTransaction().commit();
        emr.close();

        // Browse :
        List<ObjetMedecin> mListe = new ArrayList<>();
        for(Object[] medecins : resultat){
            mListe.add(new ObjetMedecin(Integer.valueOf(String.valueOf(medecins[0])),
                    String.valueOf(medecins[1])));
        }
        //
        return mListe;
    }




    @CrossOrigin("*")
    @GetMapping(value="/getserviceid/{idService}/{hopitallib}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Serviceoption> getserviceid(HttpSession session,
        @PathVariable int idService, @PathVariable int hopitallib){
        // -----------------
        //Serviceoption so = serviceoptionRepository.findByIdser(idService);
        Serviceoption so = serviceoptionRepository.findByIdserAndIdhop(idService,
                hopitallib);
        List<Serviceoption> listeService = new ArrayList<Serviceoption>();
        listeService.add(so);
        //
        return listeService;
    }


    // Get PATIENT's ID using idcons :
    @CrossOrigin("*")
    @GetMapping(value="/getpatidbyidcons/{idcons}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Patient> getpatidbyidcons(HttpSession session,
        @PathVariable int idcons){
        // -----------------
        Constante ce = constanteRepository.findByIdcons(idcons);
        Patient pt = patientRepository.findByIdpat(ce.getIdpat());
        List<Patient> liste = new ArrayList<>();
        liste.add(pt);
        //
        return liste;
    }


    // Get PATIENT's CONSTANTES using idcons :
    @CrossOrigin("*")
    @GetMapping(value="/getpatconstbyidcons/{idcons}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Constante> getpatconstbyidcons(HttpSession session,
                                          @PathVariable int idcons){
        // -----------------
        Constante ce = constanteRepository.findByIdcons(idcons);
        List<Constante> liste = new ArrayList<>();
        liste.add(ce);
        //
        return liste;
    }

    // Get PATIENT's CABINET DENTAIRE DATA 1
    @CrossOrigin("*")
    @GetMapping(value="/getcabinetdata/{idpat}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Cabinetdentaire> getcabinetdata(HttpSession session, @PathVariable int idpat){
        // -----------------
        Cabinetdentaire ce = cabinetdentaireRepository.findByIdpat(idpat);
        List<Cabinetdentaire> liste = new ArrayList<>();
        liste.add(ce);
        //
        return liste;
    }

    // Get PATIENT's CABINET DENTAIRE DATA 2
    @CrossOrigin("*")
    @GetMapping(value="/getcabinetdataproth/{idpat}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Protheses> getcabinetdataproth(HttpSession session, @PathVariable int idpat){
        // -----------------
        List<Protheses> ps = prothesesRepository.findAllByIdpat(idpat);
        return ps;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getpatientinfotest" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Patient> getpatientinfotest(){
        // -----------------
        List<Patient> listePatient = new ArrayList<Patient>();
        listePatient = patientRepository.findAll();
        Patient patient = new Patient();
        patient = listePatient.get(0);
        //
        List<Patient> listePatients = new ArrayList<Patient>();
        listePatients.add(patient);
        return listePatients;
    }



    @CrossOrigin("*")
    @GetMapping(value="/checklistemedecincpec/{idser}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> checklistemedecincpec(@PathVariable int idser){
        // -----------------
        List<JourPatient> listeRetour = new ArrayList<>();
        List<Affectationserv> lesAffectations =
                affectationservRepository.findAllByIdser(idser);
        if(lesAffectations != null){
            //System.out.println("Non NULL");
            if(lesAffectations.size() > 0) {
                //System.out.println("Taille : "+lesAffectations.size());
                JourPatient jp = new JourPatient("", lesAffectations.size());
                listeRetour.add(jp);
            }
            else{
                //System.out.println("Taille 0");
                JourPatient jp = new JourPatient("", 0);
                listeRetour.add(jp);
            }
        }
        else{
            //System.out.println("NULL");
            JourPatient jp = new JourPatient("", 0);
            listeRetour.add(jp);
        }
        //
        return listeRetour;
    }





    @CrossOrigin("*")
    @GetMapping(value="/getlistemedecincpec/{idser}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Medecin> getlistemedecincpec(@PathVariable int idser){
        // -----------------
        List<Medecin> listeRetour = new ArrayList<>();
        List<Affectationserv> lesAffectations =
            affectationservRepository.findAllByIdser(idser);
        // Get All MEDECINS :
        for(Affectationserv av : lesAffectations){
            Medecin mn = medecinRepository.findByIdmed(av.getIdmed());
            listeRetour.add(mn);
        }
        return listeRetour;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getpattest" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<JourPatient> getpattest(Principal principal, HttpSession session){

        List<JourPatient> listeObjet = new ArrayList<JourPatient>();

        try {

            Object medecinid = session.getAttribute("medecinid");
            Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
            //Medecin medecin = medecinRepository.findByIdmed(1);

            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> resultat = new ArrayList<Object[]>();

            resultat = emr.createQuery(
                    "select date(a.dateconsultation),count(a.idpat) from anf_consultation a " +
                            "where a.idmed=" + medecin.getIdmed() +
                            " group by date(a.dateconsultation) order by date(a.dateconsultation) desc")
                    .setMaxResults(5).getResultList();
            emr.getTransaction().commit();

            // The list of OBJECTS :

            // Browse listePanneau :
            //System.out.println("Taille : "+resultat.size());
            for (Object[] ob : resultat) {
                // Create a new Object :
                String dts = String.valueOf(ob[0]);
                //String dates = new SimpleDateFormat("yyyy-MM-dd").format(ob[0]);
                listeObjet.add(new JourPatient(dts,
                        (Long) ob[1]));
            }
            if (listeObjet.size() > 0) {
                // Ordonner selon le date :
                listeObjet.sort(Comparator.comparing(JourPatient::getJour));
            }

            emr.close();
        }
        catch (Exception exc){
            listeObjet.add(new JourPatient("2020-12-12",5));
        }
        return listeObjet;
    }




    // Afficher le nombre de patients par Medecin lié à un HOPITAL
    @CrossOrigin("*")
    @GetMapping(value="/getpatientmedecin/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> getpatientmedecin(@PathVariable int idmed,
                                               Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);
        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
            "select concat(c.nom,' ',c.prenom) as noms, count(a.idcon) as total  " +
            "from anf_consultation a inner join anf_affectation b on a.idhop=b.idhop " +
            "inner join anf_medecin c on a.idmed=c.idmed where b.idmed= " + medecin.getIdmed()+
            " group by concat(c.nom,' ',c.prenom) order by count(a.idcon) desc" )
                .setMaxResults(5).getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new JourPatient((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }


    @CrossOrigin("*")
    @GetMapping(value="/patientgenreadmin/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> getpatientgenre(@PathVariable int idmed,
                                             Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
                "select d.sexe,count(d.idpat) " +
                "from anf_consultation a inner join anf_affectation b on a.idhop=b.idhop " +
                "inner join anf_medecin c on a.idmed=c.idmed " +
                "inner join anf_patient d on a.idpat=d.idpat where b.idmed= " + medecin.getIdmed()+
                " group by d.sexe")
                .getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new JourPatient((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }


    @CrossOrigin("*")
    @GetMapping(value="/patientparservice/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> patientparservice(@PathVariable int idmed,
                                               Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
                "select d.libelle,count(a.idcon) " +
                        "from anf_consultation a inner join anf_medecin c on a.idmed=c.idmed " +
                        "inner join anf_services d on a.idser=d.idser " +
                        "group by d.libelle")
                .getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new JourPatient((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }


    @CrossOrigin("*")
    @GetMapping(value="/trancheagemedecin/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> trancheagemedecin(@PathVariable int idmed,
        Principal principal, HttpSession session){

        Medecin medecin = medecinRepository.findByIdmed(idmed);
        //Object medecinid = session.getAttribute("medecinid");

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();

        // 0-14
        Object donnee1 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                        "where b.idmed="+medecin.getIdmed()+" and a.age between 0 and 14")
                .getSingleResult();
        //  Cast :
        long nbreconsultation = (Long) donnee1;
        listeObjet.add(new JourPatient("0-14",nbreconsultation));
        // 15-24
        Object donnee2 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                        "where b.idmed="+medecin.getIdmed()+" and a.age between 15 and 24")
                .getSingleResult();
        //  Cast :
        long nbreconsultation2 = (Long) donnee2;
        listeObjet.add(new JourPatient("15-24",nbreconsultation2));
        // 25-64
        Object donnee3 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                        "where b.idmed="+medecin.getIdmed()+" and a.age between 25 and 64")
                .getSingleResult();
        //  Cast :
        long nbreconsultation3 = (Long) donnee3;
        listeObjet.add(new JourPatient("25-64",nbreconsultation3));
        // > 64
        Object donnee4 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                        "where b.idmed="+medecin.getIdmed()+" and a.age > 64")
                .getSingleResult();
        //  Cast :
        long nbreconsultation4 = (Long) donnee4;
        listeObjet.add(new JourPatient("+64",nbreconsultation4));

        emr.getTransaction().commit();
        emr.close();
        return listeObjet;
    }



    @CrossOrigin("*")
    @GetMapping(value="/trancheageadmin/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> trancheageadmin(@PathVariable int idmed,
                                             Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();

        // 0-14
        Object donnee1 = emr.createQuery(
            "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b " +
            "on a.idpat=b.idpat where a.age between 0 and 14")
                .getSingleResult();
        //  Cast :
        long nbreconsultation = (Long) donnee1;
        listeObjet.add(new JourPatient("0-14",nbreconsultation));
        // 15-24
        Object donnee2 = emr.createQuery(
            "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b " +
            "on a.idpat=b.idpat where a.age between 15 and 24")
                .getSingleResult();
        //  Cast :
        long nbreconsultation2 = (Long) donnee2;
        listeObjet.add(new JourPatient("15-24",nbreconsultation2));
        // 25-64
        Object donnee3 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b " +
                        "on a.idpat=b.idpat where a.age between 25 and 64")
                .getSingleResult();
        //  Cast :
        long nbreconsultation3 = (Long) donnee3;
        listeObjet.add(new JourPatient("25-64",nbreconsultation3));
        // > 64
        Object donnee4 = emr.createQuery(
                "select count(b.idcon) as tot from anf_patient a inner join anf_consultation b " +
                        "on a.idpat=b.idpat where a.age > 64")
                .getSingleResult();
        //  Cast :
        long nbreconsultation4 = (Long) donnee4;
        listeObjet.add(new JourPatient("+64",nbreconsultation4));

        emr.getTransaction().commit();
        emr.close();
        return listeObjet;
    }



    // genrap
    @PostMapping(value="/sendfile")
    @ResponseBody
    public String savefichier(@RequestParam("imageprop") String imageprop,
                              @RequestParam("imagemag") String imagemag,
                              @RequestParam("nom") String nom,
                              @RequestParam("prenom") String prenom,
                              @RequestParam("telephone") String telephone,
                              @RequestParam("cni") String cni,
                              @RequestParam("nummag") String nummag,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("latitude") String latitude,
                              HttpServletRequest request){

        try {

            Vendeuse vendeuse = new Vendeuse();
            vendeuse.setNom(nom+" "+prenom);
            vendeuse.setContact(telephone);
            vendeuse.setEmail("");

            Date tpdate;
            try {
                // Date actuelle :
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                vendeuse.setDatenaissance(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
            }
            catch (Exception exc){}

            vendeuse.setPieceidentite(cni);
            vendeuse.setSexe(0);
            vendeuse.setIdmar(1);

            // Create :
            Vendeuse vd = vendeuseRepository.save(vendeuse);


            // Now HIT COORDONNEES TABLE :
            Coordonnees coordonnees = new Coordonnees();
            coordonnees.setIdven(vd.getIdven());
            coordonnees.setImagemag(imagemag);
            coordonnees.setImageprop(imageprop);
            coordonnees.setNumag(Integer.parseInt(nummag));
            coordonnees.setLongitude(Double.parseDouble(longitude));
            coordonnees.setLatitude(Double.parseDouble(latitude));
            // save :
            coordonneesRepository.save(coordonnees);

        }
        catch (Exception exc){
            //saveJournal(idusr, "exception sendfile : "+  exc.toString());
            //System.out.println("Erreur : "+exc.toString());
            return "nok";
        }

        return "ok";
    }




    //
    @GetMapping(value="/getadministres" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<LatitudeLongitude> getadministres(){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = new ArrayList<Object[]>();

        // for TYPES :
        histoCommande = emr.createQuery(
                "select b.latitude,b.longitude,a.nom,b.numag,b.idcord " +
                        "from anf_vendeuse a inner join anf_coordonnees b on " +
                        "a.idven=b.idven ").getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<LatitudeLongitude> listeObjet = new ArrayList<LatitudeLongitude>();
        // Browse listePanneau :
        for(Object[] ob : histoCommande){
            // Create a new Object :
            LatitudeLongitude ll = new LatitudeLongitude((Double)ob[0],
                    (Double)ob[1], (String)ob[2], (Integer)ob[3], (Integer)ob[4]);
            listeObjet.add(ll);
        }
        //
        emr.close();
        return listeObjet;
    }


    @GetMapping(value="/getcoordid" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Coordonnees> getcoordid(@RequestParam("idcord") int idcord){
        Coordonnees coordonnees = coordonneesRepository.findByIdcord(idcord);
        //
        List<Coordonnees> liste = new ArrayList<Coordonnees>();
        liste.add(coordonnees);
        //System.out.println("Idcord : "+idcord);
        //System.out.println("Image : "+coordonnees.getImageprop());
        return liste;
    }


    @GetMapping(value="/getcomlist" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE) // MediaType.ALL_VALUE
    @ResponseBody
    public List<Commune> getComlist(){
        List<Commune> liste = new ArrayList<Commune>();
        Commune cmne = new Commune();
        cmne.setIdcom(1);
        cmne.setLibelle("Koumassi");
        liste.add(cmne);
        //
        Commune cmne1 = new Commune();
        cmne1.setIdcom(1);
        cmne1.setLibelle("Plateau");
        liste.add(cmne1);

        return liste;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getquantitedonne" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quantite> getquantitedonne(){
        // -----------------
        List<Quantite> liste = quantiteRepository.findAllByOrderByLibelleAsc();
        //
        return liste;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getposologiedonne" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Posologie> getposologiedonne(){
        // -----------------
        List<Posologie> liste = posologieRepository.findAllByOrderByLibelleAsc();
        //
        return liste;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getdosagedonne" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Dosage> getdosagedonne(){
        // -----------------
        List<Dosage> liste = dosageRepository.findAllByOrderByLibelleAsc();
        //
        return liste;
    }


    @CrossOrigin("*")
    @GetMapping(value="/gettypedonne" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Typesmedic> gettypedonne(){
        // -----------------
        List<Typesmedic> liste = typesmedicRepository.findAllByOrderByLibelleAsc();
        //
        return liste;
    }



    // Montant par mois
    @CrossOrigin("*")
    @GetMapping(value="/getmoismontant/{idmed}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<JourPatient> getmoismontant(@PathVariable int idmed,
                                               Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);
        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
            "select month(a.dates),sum(a.montant) from anf_facturation a " +
            "inner join anf_affectation b on a.idhop=b.idhop " +
            "where b.idmed="+idmed+" and year(a.dates)=(select " +
            "year(max(dates)) from " +
            "anf_facturation) group by month(a.dates) " +
            "order by month(a.dates) asc" ).getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<JourPatient> listeObjet = new ArrayList<JourPatient>();
        for(Object[] ob : resultat){
            // Create a new Object :
            String mois ="";
            switch ((Integer)ob[0]){
                case 1:
                    mois = "Janv.";
                    break;
                case 2:
                    mois = "Fevr.";
                    break;
                case 3:
                    mois = "Mars";
                    break;
                case 4:
                    mois = "Avri.";
                    break;
                case 5:
                    mois = "Mai";
                    break;
                case 6:
                    mois = "Juin";
                    break;
                case 7:
                    mois = "Juil.";
                    break;
                case 8:
                    mois = "Aout";
                    break;
                case 9:
                    mois = "Sept.";
                    break;
                case 10:
                    mois = "Oct.";
                    break;
                case 11:
                    mois = "Nov.";
                    break;
                case 12:
                    mois = "Dec.";
                    break;
            }

            /*System.out.println("Montant somme : "+String.valueOf(ob[1]));

            try{
                int entier = Integer.parseInt(String.valueOf(ob[1]));
                System.out.println("entier : "+entier);
            }
            catch (Exception exc){
                System.out.println("erreur entier !");
            }
            //
            try{
                long longue = Long.parseLong(String.valueOf(ob[1]));
                System.out.println("longue : "+longue);
            }
            catch (Exception exc){
                System.out.println("erreur longue !");
            }
            //
            try{
                double doubles = Double.parseDouble(String.valueOf(ob[1]));
                long longT = (long)doubles;
                System.out.println("double : "+doubles);
            }
            catch (Exception exc){
                System.out.println("erreur doubles !");
            }

            String[] tampon = String.valueOf(ob[1]).split(".");
            System.out.println("Montant splité : "+tampon[0]);*/

            double doubles = Double.parseDouble(String.valueOf(ob[1]));
            listeObjet.add(new JourPatient(mois,
                    (long)doubles));
        }

        emr.close();
        return listeObjet;
    }


    @CrossOrigin("*")
    @GetMapping(value="/consultationparmedecin" ,
            produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<StatConsultationMois> consultationparmedecin(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Call the stored Procedure :
        StoredProcedureQuery procedureQuery =
                em.createStoredProcedureQuery("findAllPatientTreatedPerDays");
        List<Object[]> resultat = procedureQuery.getResultList();

        /*List <Object[]> resultat = emr.createQuery(
            "select a.idmed,a.nom,a.prenom,date(b.dates),count(b.idcon) as tot from anf_medecin a inner " +
                    "join anf_consultation b on a.idmed=b.idmed where date(b.dates) >= date(NOW() - INTERVAL 8 DAY) " +
                    "group by a.idmed,a.nom,a.prenom,date(b.dates) order by date(b.dates) asc")
            .getResultList();*/
        em.getTransaction().commit();
        em.close();

        List<StatConsultationMois> donneesfinale = new ArrayList<>();
        for(Object[] ob : resultat){
            StatConsultationMois ss = new StatConsultationMois();
            ss.setIdservices(  Integer.parseInt(String.valueOf(ob[0]))  );  // identifiant du MEDECIN
            ss.setMois( String.valueOf(ob[3]) ); // Dates
            ss.setServices( String.valueOf(ob[1]) + " "+ String.valueOf(ob[2]) ); // nom MEDECIN
            ss.setTotal(Long.valueOf(String.valueOf(ob[4])));  // Total
            donneesfinale.add(ss);
        }

        // Retour
        return donneesfinale;
    }



    @CrossOrigin("*")
    @GetMapping(value="/consultationservmois/{idmed}" ,
            produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<StatConsultationMois> consultationservmois(
        @PathVariable int idmed, Principal principal){

        Medecin medecin = medecinRepository.findByIdmed(idmed);

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        // Obtenir les mois pour lesquels on a une consultation :
        List <Integer> lesMOis = new ArrayList<>();
        lesMOis = emr.createQuery(
                "select distinct month(a.dates) from " +
                "anf_consultation a where year(a.dates) = year(now()) " +
                "order by month(a.dates) asc")
                .getResultList();
        //System.out.println("Taille : "+lesMOis.size());
        List<Integer> setMois = new ArrayList<>();
        for(Integer getMois : lesMOis){
            setMois.add(getMois);
        }

        List <Object[]> resultat = new ArrayList<Object[]>();
        resultat = emr.createQuery(
            "select c.libelle,c.idser,month(a.dates), count(a.idcon) from " +
            "anf_consultation a inner join anf_services c on a.idser=c.idser " +
            "where year(a.dates) = year(now()) " +
            "group by c.libelle,c.idser,month(a.dates) order " +
                    "by c.libelle,month(a.dates) asc")
                .getResultList();
        emr.getTransaction().commit();
        emr.close();

        // The list of OBJECTS :
        List<ServicesMois> donnees = new ArrayList<>();
        List<ServicesLibelle> donneesLibelle = new ArrayList<>();
        List<StatServiceMois> listStatSerMois = new ArrayList<>();
        ArrayList<Integer> donneesIndex = new ArrayList<>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            ServicesMois sm = new ServicesMois();
            sm.setMois(Integer.parseInt(String.valueOf(ob[2])));
            sm.setServices(Integer.parseInt(String.valueOf(ob[1])));
            sm.setTotal((Long)ob[3]);
            sm.setLibservice(String.valueOf(ob[0]));
            donnees.add(sm);
            //
            StatServiceMois ssm = new StatServiceMois();
            ssm.setMois(Integer.parseInt(String.valueOf(ob[2])));
            ssm.setService(Integer.parseInt(String.valueOf(ob[1])));
            if(!listStatSerMois.contains(ssm)){
                listStatSerMois.add(ssm);
            }


            //System.out.println("Mois : "+Integer.parseInt(String.valueOf(ob[2])));
            //System.out.println("Service : "+String.valueOf(ob[0]));
            //System.out.println("Total : "+(Long)ob[3]);

            //
            ServicesLibelle sl = new ServicesLibelle();
            sl.setServices(Integer.parseInt(String.valueOf(ob[1])));
            sl.setLibelle(String.valueOf(ob[0]));
            donneesLibelle.add(sl);
            //
            int valeur = Integer.parseInt(String.valueOf(ob[1]));
            if(!donneesIndex.contains(valeur)){
                donneesIndex.add(valeur);
            }
        }

        // Process :
        List<ServicesMois> donneesTraite = new ArrayList<>();
        //System.out.println("Taille listStatSerMois : "+listStatSerMois.size());
        for(int mois : setMois){           // Mois
            //System.out.println("Mois : "+mois);
            for(int index : donneesIndex){ // Service Id

                StatServiceMois ssm = new StatServiceMois();
                ssm.setMois(mois);
                ssm.setService(index);
                boolean check = false;
                for(StatServiceMois sss : listStatSerMois){
                    if(sss.getMois()==mois && sss.getService()==index){
                        check = true;
                    }
                }
                if(!check){
                    // ajouter le 0 pour le service et le mois :
                    for(ServicesMois mObjet : donnees){
                        if(mObjet.getServices()==index){
                            ServicesMois ss = new ServicesMois();
                            ss.setTotal(0);
                            ss.setLibservice(mObjet.getLibservice());
                            ss.setMois(mois);
                            ss.setServices(mObjet.getServices());
                            //
                            donnees.add(ss);
                            // Ajouter :
                            listStatSerMois.add(ssm);
                            break;
                        }
                    }
                }
                /*
                for(ServicesMois mObjet : donnees){
                    if(index == mObjet.getServices()) {
                        if (mObjet.getMois() == mois && mObjet.getServices() == index) {
                            donneesTraite.add(mObjet);
                        } else if(mObjet.getServices() == index) {
                            ServicesMois ss = new ServicesMois();
                            ss.setTotal(0);
                            ss.setLibservice(mObjet.getLibservice());
                            ss.setMois(mois);
                            ss.setServices(mObjet.getServices());
                            donneesTraite.add(ss);
                            break;
                        }
                    }
                }
                */
            }
        }

        List<StatConsultationMois> donneesfinale = new ArrayList<>();
        for(ServicesMois mObjet : donnees ){
            StatConsultationMois scm = new StatConsultationMois();
            scm.setMois(getMonth(mObjet.getMois()));
            scm.setServices(mObjet.getLibservice());
            scm.setTotal(mObjet.getTotal());
            scm.setIdservices(mObjet.getServices());
            donneesfinale.add(scm);
        }

        // Preparer les données :
        return donneesfinale;
    }



    private String getMonth(int mois){
        String retour = "";
        switch(mois){
            case 1:
                retour = "Jan";
                break;
            case 2:
                retour = "Fev";
                break;
            case 3:
                retour = "Mar";
                break;
            case 4:
                retour = "Avr";
                break;
            case 5:
                retour = "Mai";
                break;
            case 6:
                retour = "Jui";
                break;
            case 7:
                retour = "Juil";
                break;
            case 8:
                retour = "Aou";
                break;
            case 9:
                retour = "Sep";
                break;
            case 10:
                retour = "Oct";
                break;
            case 11:
                retour = "Nov";
                break;
            case 12:
                retour = "Dec";
                break;
        }

        //
        return retour;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getmedicamentpharm" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Medicament> getmedicamentpharm(){
        // -----------------
        List<Medicament> listeMedicament = medicamentRepository.findAllByOrderByLibelleAsc();
        //
        return listeMedicament;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getmedicamentprice/{drogid}" , produces = MediaType.TEXT_XML_VALUE,
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Medicament> getmedicamentprice(HttpSession session,
        @PathVariable int drogid){
        // -----------------
        Medicament mt = medicamentRepository.findByIdmd(drogid);
        List<Medicament> listeDrogs = new ArrayList<>();
        listeDrogs.add(mt);
        //
        return listeDrogs;
    }



    // Get PATIENT's ID using idpat :
    @CrossOrigin("*")
    @GetMapping(value="/getpatientfulldatas/{idpat}" ,
        produces = MediaType.TEXT_XML_VALUE,
        consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<PatientPersonne> getpatientfulldatas(HttpSession session,
        @PathVariable int idpat){
        // -----------------
        Patient pt = patientRepository.findByIdpat(idpat);
        Patientrecours patientrecours =
            patientrecoursRepository.findTopByIdpat(idpat);
        List<PatientPersonne> liste = new ArrayList<>();
        PatientPersonne pp = new PatientPersonne();
        pp.setPatient(pt);
        pp.setPatientrecours(patientrecours);
        liste.add(pp);
        //
        return liste;
    }

}
