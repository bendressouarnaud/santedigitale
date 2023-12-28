package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.FonctionOrdinaire;
import com.maternite.gestion.mesobjets.QrCode;
import com.maternite.gestion.mesobjets.TachesService;
import com.maternite.gestion.repositories.*;
import net.bytebuddy.asm.Advice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

@Controller
public class FacturationController {

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
    AffectationRepository affectationRepository;
    @Autowired
    AssignationRepository assignationRepository;
    @Autowired
    AffectationservRepository affectationservRepository;
    @Autowired
    OrdonnanceRepository ordonnanceRepository;
    @Autowired
    FichiersRepository fichiersRepository;
    @Autowired
    NaturefichierRepository naturefichierRepository;
    @Autowired
    ConstanteRepository constanteRepository;
    @Autowired
    HabilitationRepository habilitationRepository;
    @Autowired
    HeureRepository heureRepository;
    @Autowired
    RendezvousRepository rendezvousRepository;
    @Autowired
    ModedevieRepository modedevieRepository;
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
    AssuranceRepository assuranceRepository;
    @Autowired
    FacturationRepository facturationRepository;
    @Autowired
    CmdexamensRepository cmdexamensRepository;
    @Autowired
    AssignationpharmRepository assignationpharmRepository;
    @Autowired
    ServiceplusRepository serviceplusRepository;
    @Autowired
    AbonnerRepository abonnerRepository;
    @Autowired
    TacheserviceRepository tacheserviceRepository;
    @Autowired
    DemanderdvRepository demanderdvRepository;
    @Autowired
    PatientwalletRepository patientwalletRepository;
    @Autowired
    PatientwalletstateRepository patientwalletstateRepository;
    @Autowired
    PatientreservationRepository patientreservationRepository;
    @Autowired
    PatientfamilleRepository patientfamilleRepository;
    @Autowired
    ParametrageRepository parametrageRepository;
    @Autowired
    TachesService tachesService;
    @Autowired
    FactorisationrdvRepository factorisationrdvRepository;
    @Autowired
    MedicamentRepository medicamentRepository;
    @Autowired
    ApprovisionnementRepository approvisionnementRepository;
    @Autowired
    TicketMedicRepository ticketMedicRepository;
    @Autowired
    VentemedicamentRepository ventemedicamentRepository;
    @Autowired
    SeuilRepository seuilRepository;
    @Autowired
    PatientrecoursRepository patientrecoursRepository;

    @Autowired
    AnfCustomerRepository customerRepository;

    @Autowired
    JavaMailSender emailSender;

    @PersistenceUnit
    EntityManagerFactory emf;

    @Value("${mon.lienlocal}")
    private String monUrl;


    //
    @GetMapping(value = "/accfacturation")
    public String accfacturation(Principal principal, Model model){

        // Montant annuel :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Object montantAnnuel = em.createQuery(
            "select sum(a.montant) from anf_facturation a " +
            "where a.idhop="+assignation.getIdhop()+
            " and year(a.dates) = year(now())").getSingleResult();
        Double mAnnuel = (Double) montantAnnuel;

        // Nombre de patients journaliers
        Object patientJournalier = em.createQuery(
                "select count(a.idfac) from anf_facturation a " +
                        "where a.idhop="+assignation.getIdhop()+
                        " and date(a.dates) = date(now())").getSingleResult();
        long patientJ = (long) patientJournalier;
        // Montant journaliers
        Object montantJournalier = em.createQuery(
                "select sum(a.montant) from anf_facturation a " +
                        "where a.idhop="+assignation.getIdhop()+
                        " and date(a.dates) = date(now())").getSingleResult();
        Double montantJ = (Double) montantJournalier;

        //
        model.addAttribute("montantannuel", mAnnuel);
        model.addAttribute("patientjournalier", patientJ);
        model.addAttribute("montantjournalier", montantJ);

        // Liste des facturations :
        List<Object[]> listefacturations = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom)," +
            "date(b.dates),b.montant,c.libelle,b.idfac,d.libelle, " +
            "case " +
            "when a.provenance = 0 then 'Patient' " +
            "else 'Membre' end as types from " +
            "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
            "inner join anf_services c on c.idser=b.service " +
            "left join anf_assurance d on d.idass=b.assurance " +
            "where b.idhop= " + assignation.getIdhop()).getResultList();
        model.addAttribute("listefacturations", listefacturations);

        // Liste of RDV request :
        List<Object[]> listeRdvAttente = em.createQuery(
            "select distinct a.idddv, date(a.dates),a.nom as patient ,d.libelle, " +
                    "case " +
                    " when concat(b.nom,' ',b.prenom) is null then '---' " +
                    " else concat(b.nom,' ',b.prenom) end as medecin , " +
                    "case " +
                    " when c.libelle is null then '---'" +
                    " else c.libelle end as heure, d.idser, " +
                    "case " +
                    " when a.suggestion is null then '---'" +
                    " else a.suggestion end as proposition " +
                    " from anf_demanderdv a inner join anf_services d on a.idser=d.idser " +
                    "left join anf_medecin b on a.idmed=b.idmed left join anf_heure c on " +
                    "a.idheu=c.idheu").getResultList();
        model.addAttribute("listeRdvAttente", listeRdvAttente);

        // List of BOOKING :
        List<Object[]> listeReservation =
            em.createQuery("select distinct concat(c.nom,' ',c.prenom) as " +
            "membre,c.numero,c.email,concat(a.nom,' ',a.prenom) as gestionn," +
            "date(d.dates) as dte,d.idpre from anf_patient a inner join " +
            "anf_facturation b on a.idpat=b.idpat inner join " +
            "anf_patient_famille c on a.idpat=c.idparent inner join " +
            "anf_patient_reservation d on d.idpaf=c.idpaf " +
            "where b.idhop="+assignation.getIdhop()).getResultList();
        model.addAttribute("listeReservation", listeReservation);

        em.getTransaction().commit();
        em.close();

        return "accueilfacturation";
    }



    //
    @GetMapping(value = "/acclaboratoire")
    public String acclaboratoire(Principal principal, Model model){

        // Get the MEDECIN
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        // Liste des facturations :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatients = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom),date(b.dates),c.libelle," +
            "e.idcon,d.libelle from anf_patient a inner join anf_facturation b on a.idpat=" +
            "b.idpat inner join anf_services c on c.idser=b.service " +
            "inner join anf_cmdexamens e on a.idpat=e.idpat " +
            "left join anf_assurance d on d.idass=b.assurance where e.montant > 0 and b.idhop= "
            + assignation.getIdhop()).getResultList();
        model.addAttribute("listepatients", listepatients);

        // Nombre de patients journaliers
        Object patientJournalier = em.createQuery(
            "select count( distinct a.idpat)  from anf_patient a inner join " +
            "anf_cmdexamens b on a.idpat=b.idpat inner join anf_facturation c on " +
            "c.idpat=a.idpat where date(b.date_fichier)=date(now()) and b.statut=1 " +
            "and c.idhop=" +assignation.getIdhop()).getSingleResult();
        long patientJ = patientJournalier != null ? ((long) patientJournalier) : 0;
        model.addAttribute("patientJ", patientJ);

        // Nombre d'Examens Journaliers
        Object examenJournalier = em.createQuery(
            "select count(b.idcmx)  from anf_patient a inner join " +
            "anf_cmdexamens b on a.idpat=b.idpat inner join anf_facturation c on " +
            "c.idpat=a.idpat where date(b.date_fichier)=date(now()) and b.statut=1 " +
            "and c.idhop=" +assignation.getIdhop()).getSingleResult();
        long examenJ = examenJournalier != null ? ((long) examenJournalier) : 0;
        model.addAttribute("examenJ", examenJ);

        em.getTransaction().commit();
        em.close();

        return "accueilaboratoire";
    }



    //
    @GetMapping(value = "/accpharmacie")
    public String accpharmacie(Principal principal, Model model){

        // Get the PHARMACIES
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation =
            assignationRepository.findByIdmed(medecin.getIdmed());

        //  :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //
        List<Object[]> listevente = em.createQuery(
            "select distinct concat(b.nom,' ',b.prenom),date(a.dates)," +
            "a.heure,c.libelle from anf_ventemedicament a inner join anf_medecin b " +
            "on a.idusr=b.idmed inner join anf_medicament c on a.idmd=c.idmd " +
            "where date(a.dates) = date(now())").getResultList();
        model.addAttribute("listevente", listevente);

        /* Historique des ventes sur les 14 derniers jours */
        StoredProcedureQuery procedureQuery = em
                .createStoredProcedureQuery("findAllVente");
        List<Object[]> listeventedeuxSem = procedureQuery.getResultList();
        model.addAttribute("listeventedeuxSem", listeventedeuxSem);

        // Nombre de patients journalier :
        long totJournalierPatient = 0;
        List<Object[]> listeJournalierPatient = em.createQuery(
            "select b.idtck,count(distinct a.idtck)  from anf_ventemedicament a inner " +
            "join anf_ticket_medic b on a.idtck=b.idtck  where date(a.dates)=" +
            "date(now()) group by b.idtck").getResultList();
        for(Object[] tot : listeJournalierPatient){
            totJournalierPatient += (long) tot[1];
        }
        model.addAttribute("totJournalierPatient", totJournalierPatient);

        // Dernier medicament vendu :
        try {
            Object dernierVendu = em.createQuery(
                    "select c.libelle from anf_ventemedicament b " +
                            "inner join anf_medicament c on c.idmd=b.idmd " +
                            "where b.idvmd = (select max(a.idvmd) from anf_ventemedicament a) " +
                            "and date(b.dates)=date(now())").getSingleResult();
            String dernierVenduLib = String.valueOf(dernierVendu);
            model.addAttribute("dernierVenduLib", dernierVenduLib);
        }
        catch (Exception exc){
            model.addAttribute("dernierVenduLib", "---");
        }

        // Total medicament
        Object totalMedic = em.createQuery(
            "select count(a.idmd) from anf_medicament a").getSingleResult();
        long totalMedicJ = (long) totalMedic;
        model.addAttribute("totalMedicJ", totalMedicJ);

        // Check to see if some DRUGS have reached the threshold :
        Seuil sl = seuilRepository.findTopByOrderByIdseuDesc();
        //System.out.println("Seuil : "+sl.getSeuil());
        List<Medicament> listeLimite =
            medicamentRepository.findByQuantiteLessThanEqual(sl.getSeuil());
        if(listeLimite != null){
            if(listeLimite.size() > 0){
                model.addAttribute("listeLimite", listeLimite);
            }
        }

        em.getTransaction().commit();
        em.close();

        return "accueilpharmacie";
    }



    @GetMapping(value = "/modiffacture/{idfac}")
    public String modiffacture(@PathVariable int idfac,
        Model model, Principal principal){

        // Get facturation :
        Facturation facturation = facturationRepository.findByIdfac(idfac);
        model.addAttribute("facturation", facturation);
        // Lien pour la modification :
        model.addAttribute("modification",
                "/gestion/modiffacturation/"+idfac);
        // Get the Patient :
        Patient patient = patientRepository.findByIdpat(facturation.getIdpat());
        model.addAttribute("patient", patient);
        // Convert the DATE :
        String dte = new SimpleDateFormat("MM/dd/yyyy").format(
            patient.getDatenaissance());
        model.addAttribute("datenaissance", dte);

        // Services :  listeServices
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);

        // listeassurance
        List<Assurance> listeassurance = assuranceRepository.findAllByActif(1);
        model.addAttribute("listeassurance", listeassurance);

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
                "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
                        "where b.idfac = " + idfac).getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();

        // Get Profession LIST :
        List<Profession> listeProf =
                professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeProf", listeProf);

        // Now, if We have 'PERSONNE a CONTACTER', get it :
        Patientrecours ps =
            patientrecoursRepository.findTopByIdpat(patient.getIdpat());
        if(ps != null){
            model.addAttribute("patientrecours", ps);
        }

        //
        model.addAttribute("monurl", monUrl);

        return "nouvfacturation";
    }

    //
    @GetMapping(value = "/nouvfacturation")
    public String nouvfacturation(Model model, Principal principal){

        // Services :  listeServices
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);

        // listeassurance
        List<Assurance> listeassurance = assuranceRepository.findAllByActif(1);
        model.addAttribute("listeassurance", listeassurance);

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
            "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient from " +
            "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
            "where b.idhop = " + assignation.getIdhop()).getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();

        // Get Profession LIST :
        List<Profession> listeProf =
            professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeProf", listeProf);

        // Set the current date :
        String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        model.addAttribute("currentdate", currentdate);

        //
        model.addAttribute("creation", 1);

        //
        model.addAttribute("monurl", monUrl);

        return "nouvfacturation";
    }


    // Use this one ERROR MESSAGES :
    @GetMapping(value = "/nouvfacturationerr/{error}")
    public String nouvfacturationerr(@PathVariable int error,
        Model model,
        Principal principal){

        // Services :  listeServices
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);

        // listeassurance
        List<Assurance> listeassurance = assuranceRepository.findAllByActif(1);
        model.addAttribute("listeassurance", listeassurance);

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
                "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
                        "where b.idhop = " + assignation.getIdhop()).getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();

        //
        model.addAttribute("creation", 1);
        if(error == 1)
            model.addAttribute("messageerreur", 1);

        return "nouvfacturation";
    }

    // Reach the interface :
    @GetMapping(value = "/nouvelexamen")
    public String nouvelexamen(Model model, Principal principal){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeexamens = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom) as patient," +
            "b.idcon,date(b.dates) as dte from " +
            "anf_patient a inner join anf_cmdexamens b " +
            "on a.idpat=b.idpat" ).getResultList();
        model.addAttribute("listeexamens", listeexamens);
        em.getTransaction().commit();
        em.close();

        //
        return "listeattenteexam";
    }


    // Reach the interface :
    @GetMapping(value = "/nouvelsouscription")
    public String nouvelsouscription(Model model, Principal principal){

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
                "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
                        "where b.idhop = " + assignation.getIdhop()).getResultList();
        model.addAttribute("listepatient", listepatient);

        // Get the services PLUS :
        List<Serviceplus> listeServicePlus = serviceplusRepository.findAll();
        model.addAttribute("listeServicePlus", listeServicePlus);

        // Now display list of those who suscribed :
        List<Object[]> listesoucripteur = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom),a.telephone,date(d.dates),e.libelle,d.idabo," +
                        "case " +
                        "when f.idtac is null then 0" +
                        "    else f.idtac " +
                        "end as tache from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat inner join " +
                        "anf_assignation c on b.idhop=c.idhop inner join anf_abonner d on d.idpat=" +
                        "a.idpat inner join anf_serviceplus e on e.idsps=d.idsps " +
                        "left join anf_tacheservice f on d.idabo=f.idabo where c.idhop = " +
                        assignation.getIdhop()).getResultList();
        model.addAttribute("listesoucripteur", listesoucripteur);

        em.getTransaction().commit();
        em.close();

        return "nouvelsouscription";
    }


    @PostMapping(value = "/modifsouscription/{idabo}")
    public String modifsouscription(@PathVariable int idabo,
        @RequestParam("idpatient") int idpatient,
        @RequestParam("idsps") int idsps,
        @RequestParam("mail") String mail){

        // Check first :
        Abonner abonner = abonnerRepository.findByIdabo(idabo);
        if(abonner != null){
            abonner.setIdpat(idpatient);
            abonner.setIdsps(idsps);
            try{
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                abonner.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                abonnerRepository.save(abonner);
                // Update PATIENT email :
                Patient patient = patientRepository.findByIdpat(idpatient);
                if(patient != null){
                    patient.setEmail(mail);
                    // Save it :
                    patientRepository.save(patient);
                }
            }
            catch (Exception exc){
            }
        }
        else return "redirect:/nouvelsouscription";
        //
        return "redirect:/gererserviceplus/"+abonner.getIdabo();
    }

    // Set the SERVICE :
    @GetMapping(value = "/gererserviceplus/{idabo}")
    public String configurerservice(@PathVariable int idabo,
        Model model, Principal principal){
        // Liste of HOURS :
        List<Heure> listeHeure = heureRepository.findAll();
        model.addAttribute("listeHeure", listeHeure);
        model.addAttribute("creation",
                "/gestion/enregparamserv/"+idabo);

        // Pull data for this request :
        Tacheservice tacheservice = tacheserviceRepository.findByIdabo(idabo);
        if(tacheservice != null) {
            model.addAttribute("tacheservice", tacheservice);
            model.addAttribute("modification",
                    "/gestion/modifparamserv/"+tacheservice.getIdtac());
        }

        return "configurerservice";
    }


    @GetMapping(value = "/modifiersouscription/{idabo}")
    public String modifiersouscription(@PathVariable int idabo,
        Model model, Principal principal){

        // Get the subscription :
        Abonner abonner = abonnerRepository.findByIdabo(idabo);
        model.addAttribute("modifabonner", abonner);
        model.addAttribute("modification",
                "/gestion/modifsouscription/"+idabo);

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
                "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat " +
                        "where b.idhop = " + assignation.getIdhop()).getResultList();
        model.addAttribute("listepatient", listepatient);

        // Get the services PLUS :
        List<Serviceplus> listeServicePlus = serviceplusRepository.findAll();
        model.addAttribute("listeServicePlus", listeServicePlus);

        // Now display list of those who suscribed :
        List<Object[]> listesoucripteur = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom),a.telephone,date(d.dates),e.libelle,d.idabo," +
                        "case " +
                        "when f.idtac is null then 0" +
                        "    else f.idtac " +
                        "end as tache from " +
                        "anf_patient a inner join anf_facturation b on a.idpat=b.idpat inner join " +
                        "anf_assignation c on b.idhop=c.idhop inner join anf_abonner d on d.idpat=" +
                        "a.idpat inner join anf_serviceplus e on e.idsps=d.idsps " +
                        "left join anf_tacheservice f on d.idabo=f.idabo where c.idhop = " +
                        assignation.getIdhop()).getResultList();
        model.addAttribute("listesoucripteur", listesoucripteur);

        em.getTransaction().commit();
        em.close();

        return "nouvelsouscription";
    }


    @PostMapping(value = "/enregsouscription")
    public String enregsouscription(@RequestParam("idpatient") int idpatient,
        @RequestParam("idsps") int idsps,
        @RequestParam("mail") String mail){
        // Check first :
        Abonner abonner =
                abonnerRepository.findByIdpatAndIdsps(idpatient, idsps);
        int idabo = 1;
        if(abonner == null){
            abonner = new Abonner();
            abonner.setIdpat(idpatient);
            abonner.setIdsps(idsps);
            try{
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                abonner.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                Abonner getAbo = abonnerRepository.save(abonner);
                idabo = getAbo.getIdabo();

                // Update PATIENT email :
                Patient patient = patientRepository.findByIdpat(idpatient);
                if(patient != null){
                    patient.setEmail(mail);
                    // Save it :
                    patientRepository.save(patient);
                }
            }
            catch (Exception exc){
            }
        }
        //
        //return "configurerservice";
        // gererserviceplus/{idabo}"
        return "redirect:/gererserviceplus/"+idabo;
    }


    @PostMapping(value = "/enregfacturation")
    public String enregfacturation(@RequestParam("idpatient") int idpatient,
        @RequestParam("nom") String nom,
        @RequestParam("mail") String mail,
        @RequestParam("prenom") String prenom,
        @RequestParam("typeclient") int typeclient,
        @RequestParam("numclient") String numclient,
        @RequestParam("assurance") int assurance,
        @RequestParam("couverture") Double couverture,
        @RequestParam("montant") Double montant,
        @RequestParam("services") int services,
        @RequestParam("sexe") String sexe,
        @RequestParam("societe") String societe,

        @RequestParam("localisation") String localisation,
        @RequestParam("jeunefille") String jeunefille,
        @RequestParam("datenaissance") String datenaissance,
        @RequestParam("lieunaissance") String lieunaissance,
        @RequestParam("pere") String pere,
        @RequestParam("mere") String mere,
        @RequestParam("residence") String residence,
        @RequestParam("telephone") String telephone,
        @RequestParam("profession") int profession,

        @RequestParam("nomprenom") String nomprenom,
        @RequestParam("adressepostale") String adressepostale,
        @RequestParam("telautre") String telautre,
        Model model, Principal principal)
    {
        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        Patient pt = patientRepository.findByIdpat(idpatient);
        if(pt == null){
            Patient patient = new Patient();
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setSexe(sexe);
            patient.setAge(0);
            //patient.setProfession(0);
            patient.setSociete(societe);
            // Set Identifiant
            String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
            date = date.replaceAll("-","").replaceAll(":","")
                    .replaceAll(" ","");
            patient.setIdentifiant(date);
            patient.setAdresse("");
            patient.setCni("");
            patient.setEmail(mail);
            //
            patient.setLocalisation(localisation.trim());
            patient.setNomjeunefille(jeunefille);
            try {
                Date dateNaiss = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaissance));
                patient.setDatenaissance(dateNaiss);
                String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                Date dateAujourdhui = new SimpleDateFormat("yyyy-MM-dd").
                        parse(dte);
                long diffInMillies = Math.abs(dateAujourdhui.getTime() - dateNaiss.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                // Now in YEARS :
                int annee = (int)(diff / 365);
                patient.setAge(annee);
            }
            catch (Exception exc){}
            patient.setLieunaissance(lieunaissance);
            patient.setNompere(pere);
            patient.setNommere(mere);
            patient.setResidence(residence);
            patient.setTelephone(telephone);
            patient.setProfession(profession);
            // Save :
            pt = patientRepository.save(patient);
            if(nomprenom.trim().length() > 0) {
                // now save the data related to PERSONNE A CONTACTER :
                Patientrecours patientrecours = new Patientrecours();
                patientrecours.setIdpat(pt.getIdpat());
                patientrecours.setAdressepostale(adressepostale);
                patientrecours.setNomprenom(nomprenom);
                patientrecours.setTelephone(telautre);
                patientrecoursRepository.save(patientrecours);
            }
        }

        // Hit Facturation :
        Facturation facturation = new Facturation();
        // Si 'NON ASSURE' choisi, alors ne pas mettre d'assurance
        facturation.setAssurance(typeclient == 0 ? 1 : assurance);
        facturation.setCouverture(couverture);
        try {
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            facturation.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            // Get the HOSPITAL where this SECRETARY is :
            Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());
            facturation.setIdhop(assignation.getIdhop());
            facturation.setIdmed(medecin.getIdmed());
            facturation.setIdpat(pt.getIdpat());
            facturation.setMontant(montant);
            facturation.setNumclient(numclient);
            facturation.setService(services);
            facturation.setTypeclient(typeclient);
            facturation.setHeure(heure);
            // Save :
            facturationRepository.save(facturation);

            // Envoi de mail aux profils SECRETAIRE
        }
        catch (Exception exc){
            //System.out.println("Exception creation Facturation : "+exc.toString());
        }

        // Retour :
        return "redirect:/accfacturation";
    }


    @PostMapping(value = "/modiffacturation/{idfac}")
    public String modiffacturation(@PathVariable int idfac,
        @RequestParam("idpatient") int idpatient,
        @RequestParam("nom") String nom,
        @RequestParam("mail") String mail,
        @RequestParam("prenom") String prenom,
        @RequestParam("typeclient") int typeclient,
        @RequestParam("numclient") String numclient,
        @RequestParam("assurance") int assurance,
        @RequestParam("couverture") Double couverture,
        @RequestParam("montant") Double montant,
        @RequestParam("services") int services,
        @RequestParam("sexe") String sexe,
        @RequestParam("societe") String societe,

        @RequestParam("localisation") String localisation,
        @RequestParam("jeunefille") String jeunefille,
        @RequestParam("datenaissance") String datenaissance,
        @RequestParam("lieunaissance") String lieunaissance,
        @RequestParam("pere") String pere,
        @RequestParam("mere") String mere,
        @RequestParam("residence") String residence,
        @RequestParam("telephone") String telephone,
        @RequestParam("profession") int profession,

        @RequestParam("nomprenom") String nomprenom,
        @RequestParam("adressepostale") String adressepostale,
        @RequestParam("telautre") String telautre,

        Model model, Principal principal)
    {
        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        Patient pt = patientRepository.findByIdpat(idpatient);
        // Update PATIENT data :
        pt.setNom(nom);
        pt.setPrenom(prenom);
        pt.setSexe(sexe);
        pt.setProfession(profession);
        pt.setSociete(societe);
        // Set Identifiant
        String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
        date = date.replaceAll("-","").replaceAll(":","")
                .replaceAll(" ","");
        pt.setIdentifiant(date);
        pt.setEmail(mail);
        //
        pt.setLocalisation(localisation.trim());
        pt.setNomjeunefille(jeunefille);
        try {
            Date dateNaiss = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaissance));
            pt.setDatenaissance(dateNaiss);
        }
        catch (Exception exc){}
        pt.setLieunaissance(lieunaissance);
        pt.setNompere(pere);
        pt.setNommere(mere);
        pt.setResidence(residence);
        pt.setTelephone(telephone);
        pt.setProfession(profession);
        patientRepository.save(pt);

        // Hit Facturation :
        Facturation facturation = facturationRepository.findByIdfac(idfac);
        facturation.setAssurance(assurance);
        facturation.setCouverture(couverture);
        try {
            String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            facturation.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dates));
            // Get the HOSPITAL where this SECRETARY is :
            Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());
            facturation.setIdhop(assignation.getIdhop());
            facturation.setIdmed(medecin.getIdmed());
            facturation.setIdpat(pt.getIdpat());
            /*       Check if the patient is MEMBRE :         */
            double montantApayer = montant;
            if(pt.getProvenance() > 0){
                // Get familly MEMBER :
                Patientfamille patientfamille =
                        patientfamilleRepository.findByIdpaf(pt.getProvenance());
                // Get the familly OWNER :
                Patient patientOwner =
                        patientRepository.findByIdpat(patientfamille.getIdparent());
                // Now get account SOLDE :
                Patientwalletstate patientwalletstate =
                        patientwalletstateRepository.findByIdpat(patientOwner.getIdpat());
                //
                Double vieuxMontant = facturation.getMontant();
                Double montantReuniOwner = patientwalletstate.getMontant() +
                        vieuxMontant;
                if(montantReuniOwner >= montant){
                    // Process, get the OLD facturation amount :
                    Double difference = montantReuniOwner - montant;
                    patientwalletstate.setMontant(difference);
                    patientwalletstateRepository.save(patientwalletstate);
                    //
                    montantApayer = montant;
                }
                else{
                    // Informer la secretaire de cette anomalie :
                    return "redirect:/nouvfacturationerr/1";
                }
            }
            // set the amout :
            facturation.setMontant(montantApayer);
            facturation.setNumclient(numclient);
            facturation.setService(services);
            facturation.setTypeclient(typeclient);
            // Save :
            facturationRepository.save(facturation);

            //
            Patientrecours ps =
                patientrecoursRepository.findTopByIdpat(pt.getIdpat());
            if(ps != null){
                ps.setTelephone(telautre);
                ps.setNomprenom(nomprenom);
                ps.setAdressepostale(adressepostale);
                patientrecoursRepository.save(ps);
            }
            // Envoi de mail aux profils SECRETAIRE :
        }
        catch (Exception exc){
            //System.out.println("Exception creation Facturation : "+exc.toString());
        }

        // Retour :
        return "redirect:/accfacturation";
    }


    @GetMapping(value = "/ajusterfacturelabo/{idcon}")
    public String ajusterfacturelbo(@PathVariable int idcon,
        Model model, Principal principal){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Liste des facturations :
        List<Object[]> listefactureslabo = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom) as patient," +
            "date(b.dates),b.montant,c.libelle,b.idcmx  from anf_patient a " +
            "inner join anf_cmdexamens b on a.idpat=b.idpat inner join anf_examen " +
            "c on b.idexam=c.idexam where b.idcon=" + idcon).getResultList();
        model.addAttribute("listefactureslabo", listefactureslabo);

        em.getTransaction().commit();
        em.close();

        return "ajusterfacturelabo";
    }

    // Afficher un message d'erreur concernant le MONTANT :
    @GetMapping(value = "/erreurfacturelabo/{idcon}")
    public String erreurfacturelabo(@PathVariable int idcon,
        Model model, Principal principal){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Liste des facturations :
        List<Object[]> listefactureslabo = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom) as patient," +
                        "date(b.dates),b.montant,c.libelle,b.idcmx  from anf_patient a " +
                        "inner join anf_cmdexamens b on a.idpat=b.idpat inner join anf_examen " +
                        "c on b.idexam=c.idexam where b.idcon=" + idcon).getResultList();
        model.addAttribute("listefactureslabo", listefactureslabo);
        em.getTransaction().commit();
        em.close();
        // Message d'ERREUR :
        model.addAttribute("messageerreur", 1);
        return "ajusterfacturelabo";
    }


    @GetMapping(value = "/modifexamfile/{idcon}")
    public String modifexamfile(@PathVariable int idcon,
                                    Model model, Principal principal){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Liste des facturations :
        List<Object[]> listesexamens = em.createQuery(
            "select distinct b.libelle,a.idexam," +
                    "case " +
                    " when a.nomfichier is null then '---'" +
                    " else a.nomfichier " +
                    "end as nom from anf_cmdexamens a inner join anf_examen b " +
                    "on a.idexam=b.idexam where a.idcon=" + idcon).getResultList();
        model.addAttribute("listesexamens", listesexamens);
        model.addAttribute("lien",
                "/gestion/miseajourfichier/".concat(String.valueOf(idcon)));

        em.getTransaction().commit();
        em.close();

        return "ajusterexamenfichier";
    }



    @GetMapping(value = "/visualiserord/{idcon}")
    public String visualiserord(@PathVariable int idcon,
                                Model model, Principal principal){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Liste des facturations :
        List<Object[]> listesordonnances = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom) as pte, c.prescription," +
            "d.libelle from anf_patient a inner join anf_consultation b on a.idpat=" +
            "b.idpat inner join anf_ordonnance c on b.idcon=c.idcon inner join " +
            "anf_type d on d.idtyp=c.typemedic where c.idcon=" + idcon).getResultList();
        model.addAttribute("listesordonnances", listesordonnances);

        em.getTransaction().commit();
        em.close();
        return "visualiserordonnance";
    }




    @PostMapping(value = "/modifacturelabo")
    public String modifacturelabo(@RequestParam("montant[]") Double[] montant,
        @RequestParam("idcmx[]") Integer[] idcmx){

        double gMontant = 0;
        for(Double mt : montant){
            gMontant += mt;
        }

        //
        boolean patientMembre = false;

        // Get the Patient ID :
        Cmdexamens eExams = cmdexamensRepository.findByIdcmx(idcmx[0]);
        if(eExams != null){
            Patient patient = patientRepository.findByIdpat(eExams.getIdpat());
            if(patient != null){
                if(patient.getProvenance() > 0 && gMontant > 0){
                    patientMembre = true;

                    // Get familly MEMBER :
                    Patientfamille patientfamille =
                            patientfamilleRepository.findByIdpaf(patient.getProvenance());
                    // Get the familly OWNER :
                    Patient patientOwner =
                            patientRepository.findByIdpat(patientfamille.getIdparent());
                    // Now get account SOLDE :
                    Patientwalletstate patientwalletstate =
                            patientwalletstateRepository.findByIdpat(patientOwner.getIdpat());
                    if(patientwalletstate.getMontant() >= DoubleStream.of(gMontant).sum()){
                        // Update the WALLET :
                        Double difference = patientwalletstate.getMontant() -
                                gMontant;
                        patientwalletstate.setMontant(difference);
                        // Save :
                        patientwalletstateRepository.save(patientwalletstate);
                    }
                    else return "redirect:/erreurfacturelabo/"+eExams.getIdcon();
                }
            }
        }

        if(gMontant > 0) {
            for (int i = 0; i < montant.length; i++) {
                Cmdexamens cmdexamens = cmdexamensRepository.findByIdcmx(idcmx[i]);
                if (cmdexamens != null) {
                    cmdexamens.setMontant(montant[i]);
                    cmdexamensRepository.save(cmdexamens);
                }
            }
        }

        //   ---------
        return "redirect:/nouvelexamen";
    }


    @PostMapping(value = "/miseajourfichier/{idcon}")
    public String miseajourfichier(@PathVariable int idcon,
        @RequestParam("idexam[]") Integer[] idexams,
        @RequestParam("fichiers[]") MultipartFile[] fichiers,
        Model model, Principal principal){

        // Browse :
        for(int j = 0; j < idexams.length ; j++){
            // Get the appropriate line :
            Cmdexamens examens =
                    cmdexamensRepository.findByIdexamAndIdcon(idexams[j], idcon);
            if(examens!=null){
                // Check if the FILE is not empty :
                if(!fichiers[j].isEmpty()){
                    try {
                        // Save the file :
                        byte[] bytes = fichiers[j].getBytes();
                        String nomfichier = fichiers[j].getOriginalFilename();
                        String typefichier = fichiers[j].getContentType();
                        long tailleFichier = fichiers[j].getSize();
                        // Fill :
                        examens.setFichier(bytes);
                        examens.setNomfichier(nomfichier);
                        examens.setTypefichier(typefichier);
                        examens.setTaillefichier(tailleFichier);
                        examens.setStatut(1);
                        //
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        examens.setDate_fichier(
                                new SimpleDateFormat("yyyy-MM-dd").parse(date));
                        // Save :
                        cmdexamensRepository.save(examens);
                    }
                    catch (Exception exc){
                    }
                }
            }
        }

        return "redirect:/acclaboratoire";
    }


    @GetMapping(value = "/downloadexamen/{idcmx}")
    public void downloadfile(@PathVariable int idcmx, HttpServletResponse response)
    {
        Cmdexamens examen = cmdexamensRepository.findByIdcmx(idcmx);
        try {
            response.setHeader("Content-Disposition", "inline; filename=\"" +
                    examen.getNomfichier()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(examen.getTypefichier());
            response.setContentLength((int)examen.getTaillefichier());
            //IOUtils.copy(new ByteArrayInputStream(fichier.getFichier()), out);
            out.write(examen.getFichier());
            out.close();
        } catch (Exception e) {
            //System.out.println(e.toString());
        }
    }


    @PostMapping(value = "/enregparamserv/{idabo}")
    public String enregparamserv(@PathVariable int idabo,
        @RequestParam(name="lundi", required = false) Integer lundi,
        @RequestParam(name="mardi", required = false) Integer mardi,
        @RequestParam(name="mercredi", required = false) Integer mercredi,
        @RequestParam(name="jeudi", required = false) Integer jeudi,
        @RequestParam(name="vendredi", required = false) Integer vendredi,
        @RequestParam(name="samedi", required = false) Integer samedi,
        @RequestParam(name="dimanche", required = false) Integer dimanche,
        @RequestParam("heure") Integer heure,
        @RequestParam("message") String message){

        // Save :
        Tacheservice tacheservice = new Tacheservice();
        tacheservice.setLundi( lundi == null ? 0 : lundi);
        tacheservice.setMardi( mardi == null ? 0 : mardi);
        tacheservice.setMercredi( mercredi == null ? 0 : mercredi);
        tacheservice.setJeudi( jeudi == null ? 0 : jeudi);
        tacheservice.setVendredi( vendredi == null ? 0 : vendredi);
        tacheservice.setSamedi( samedi == null ? 0 : samedi);
        tacheservice.setDimanche( dimanche == null ? 0 : dimanche);
        tacheservice.setHeure(heure);
        tacheservice.setMessage(message);
        tacheservice.setIdabo(idabo);
        tacheserviceRepository.save(tacheservice);

        return "redirect:/nouvelsouscription";
    }


    @PostMapping(value = "/modifparamserv/{idtac}")
    public String modifparamserv(@PathVariable int idtac,
        @RequestParam(name="lundi", required = false) Integer lundi,
        @RequestParam(name="mardi", required = false) Integer mardi,
        @RequestParam(name="mercredi", required = false) Integer mercredi,
        @RequestParam(name="jeudi", required = false) Integer jeudi,
        @RequestParam(name="vendredi", required = false) Integer vendredi,
        @RequestParam(name="samedi", required = false) Integer samedi,
        @RequestParam(name="dimanche", required = false) Integer dimanche,
        @RequestParam("heure") Integer heure,
        @RequestParam("message") String message){

        // Save :
        Tacheservice tacheservice = tacheserviceRepository.findByIdtac(idtac);
        tacheservice.setLundi( lundi == null ? 0 : lundi);
        tacheservice.setMardi( mardi == null ? 0 : mardi);
        tacheservice.setMercredi( mercredi == null ? 0 : mercredi);
        tacheservice.setJeudi( jeudi == null ? 0 : jeudi);
        tacheservice.setVendredi( vendredi == null ? 0 : vendredi);
        tacheservice.setSamedi( samedi == null ? 0 : samedi);
        tacheservice.setDimanche( dimanche == null ? 0 : dimanche);
        tacheservice.setHeure(heure);
        tacheservice.setMessage(message);
        tacheserviceRepository.save(tacheservice);
        return "redirect:/nouvelsouscription";
    }


    @GetMapping(value = "/rapportadmins")
    public String rapportadmins(Model model, Principal principal){
        List<Services> listeService = servicesRepository.findAll();
        model.addAttribute("listeService", listeService);

        // Set the date :
        String dates =
                new SimpleDateFormat("yyyy-MM-dd").
                        format(new Date());
        model.addAttribute("dates",
                FonctionOrdinaire.formatterDate(dates));
        // Set the link :
        model.addAttribute("modification",
                "/gestion/traiterrappadmin/");

        return "interfacerapportadmin";
    }



    @PostMapping(value = "/traiterrappadmin")
    public String traiterrappadmin(Model model,
        @RequestParam("rapport") int rapport,
        @RequestParam("service") int service,
        @RequestParam("datedebut") String datedebut,
        @RequestParam("datefin") String datefin,
        @RequestParam("action") int action,
        Principal principal){

        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // retourner cela :
        model.addAttribute("datedebut", datedebut);
        model.addAttribute("datefin", datefin);
        model.addAttribute("rapport", rapport);
        model.addAttribute("service", service);
        // Traiter :
        datedebut = ClassFonction.retourDate(datedebut);
        datefin = ClassFonction.retourDate(datefin);
        //
        String filtre = service ==0 ? "" : (" and b.service = "+service);

        if(action==1){
            // Afficher :
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            switch (rapport){
                case 1:
                    // paiements de consultation de la semaine
                    List<Object[]> listeDonnees = em.createQuery(
                        "select distinct concat(a.nom,' ',a.prenom),b.montant,date(b.dates)," +
                        "b.heure,e.libelle,concat(d.nom,' ',d.prenom) " +
                        "from anf_patient a inner join anf_facturation b " +
                        "on a.idpat=b.idpat inner join anf_affectation c on c.idhop=b.idhop " +
                        "inner join anf_medecin d on b.idmed=d.idmed " +
                        "inner join anf_services e on e.idser=b.service " +
                        "where c.idmed="+ medecin.getIdmed()+ filtre +
                        " and date(b.dates) between '"+datedebut+"' and '"+datefin+"'"
                        ).getResultList();
                    model.addAttribute("listeDonnees", listeDonnees);
                    break;

                case 3:
                    // service et par mois
                    List<Object[]> listeDonneesServMois = em.createQuery(
                        "select e.libelle,month(b.dates), count(b.idfac) " +
                        "from anf_patient a inner join anf_facturation b " +
                        "on a.idpat=b.idpat inner join anf_affectation c on c.idhop=b.idhop " +
                        "inner join anf_medecin d on b.idmed=d.idmed " +
                        "inner join anf_services e on e.idser=b.service " +
                        "where c.idmed="+ medecin.getIdmed()+ filtre +
                        " and date(b.dates) between '"+datedebut+"' and '"+
                        datefin+"' group by e.libelle,month(b.dates)"
                    ).getResultList();
                    model.addAttribute("listeDonneesServMois", listeDonneesServMois);
                    break;
            }

            em.getTransaction().commit();
            em.close();
        }
        else{
            return "redirect:/genexcel/"+
                    datedebut+"/"+datefin+"/"+
                    rapport+"/"+medecin.getIdmed()+"/"+service;
        }

        List<Services> listeService = servicesRepository.findAll();
        model.addAttribute("listeService", listeService);

        // Set the date :
        String dates =
                new SimpleDateFormat("yyyy-MM-dd").
                        format(new Date());
        model.addAttribute("dates",
                FonctionOrdinaire.formatterDate(dates));
        // Set the link :
        model.addAttribute("modification",
                "/gestion/traiterrappadmin/");

        return "interfacerapportadmin";
    }



    // gENERATE excel :
    @GetMapping("/genexcel/{debut}/{fin}/{rapport}/{medecin}/{service}")
    public HttpEntity<byte[]> genereExcel(
        @PathVariable("debut") String debut, @PathVariable("fin") String fin,
        @PathVariable("rapport") int rapport,
        @PathVariable("medecin") int medecin,
        @PathVariable("service") int service
    ){

        String filtre = service ==0 ? "" : (" and b.service = "+service);

        try {
            if (rapport == 1) {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                // paiements de consultation de la semaine
                List<Object[]> listeDonnees = em.createQuery(
                    "select distinct concat(a.nom,' ',a.prenom),b.montant,date(b.dates)," +
                    "b.heure,e.libelle,concat(d.nom,' ',d.prenom) " +
                    "from anf_patient a inner join anf_facturation b " +
                    "on a.idpat=b.idpat inner join anf_affectation c on c.idhop=b.idhop " +
                    "inner join anf_medecin d on b.idmed=d.idmed " +
                    "inner join anf_services e on e.idser=b.service " +
                    "where c.idmed=" + medecin + filtre +
                    " and date(b.dates) between '" + debut + "' and '" + fin + "'"
                ).getResultList();
                em.getTransaction().commit();
                em.close();

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("historique");
                Row header = sheet.createRow(0);
                Cell headerCell = header.createCell(0);
                headerCell.setCellValue("Patient");
                headerCell = header.createCell(1);
                headerCell.setCellValue("Montant");
                headerCell = header.createCell(2);
                headerCell.setCellValue("Dates");
                headerCell = header.createCell(3);
                headerCell.setCellValue("Heure");
                headerCell = header.createCell(4);
                headerCell.setCellValue("Services");
                headerCell = header.createCell(5);
                headerCell.setCellValue("Sécrétaire");

                // Add data ;
                if (listeDonnees.size() > 0) {
                    int i = 1;
                    Row row;
                    for (Object[] donnee : listeDonnees) {
                        row = sheet.createRow(i);
                        Cell cell = row.createCell(0);
                        cell.setCellValue(String.valueOf(donnee[0]));
                        cell = row.createCell(1);
                        cell.setCellValue(
                                Double.parseDouble(String.valueOf(donnee[1])));
                        cell = row.createCell(2);
                        cell.setCellValue(String.valueOf(donnee[2]));
                        cell = row.createCell(3);
                        cell.setCellValue(String.valueOf(donnee[3]));
                        cell = row.createCell(4);
                        cell.setCellValue(String.valueOf(donnee[4]));
                        cell = row.createCell(5);
                        cell.setCellValue(String.valueOf(donnee[5]));
                        i++;
                    }

                    ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                    workbook.write(archivo);

                    if (null != workbook && null != archivo) {
                        workbook.close();
                        archivo.close();
                    }

                    byte[] documentContent = archivo.toByteArray();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportExcel.xlsx");
                    headers.setContentLength(documentContent.length);
                    return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
                }
            }
            else if(rapport==3){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                // paiements de consultation de la semaine
                List<Object[]> listeDonnees = em.createQuery(
                    "select e.libelle,month(b.dates), count(b.idfac) " +
                    "from anf_patient a inner join anf_facturation b " +
                    "on a.idpat=b.idpat inner join anf_affectation c on c.idhop=b.idhop " +
                    "inner join anf_medecin d on b.idmed=d.idmed " +
                    "inner join anf_services e on e.idser=b.service " +
                    "where c.idmed=" + medecin + filtre +
                    " and date(b.dates) between '" + debut + "' and '" +
                            fin + "' group by e.libelle,month(b.dates)"
                ).getResultList();
                em.getTransaction().commit();
                em.close();

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Service");
                Row header = sheet.createRow(0);
                Cell headerCell = header.createCell(0);
                headerCell.setCellValue("Service");
                headerCell = header.createCell(1);
                headerCell.setCellValue("Mois");
                headerCell = header.createCell(2);
                headerCell.setCellValue("Total");

                // Add data ;
                if (listeDonnees.size() > 0) {
                    int i = 1;
                    Row row;
                    for (Object[] donnee : listeDonnees) {
                        row = sheet.createRow(i);
                        Cell cell = row.createCell(0);
                        cell.setCellValue(String.valueOf(donnee[0]));
                        cell = row.createCell(1);
                        cell.setCellValue(Integer.parseInt(
                                String.valueOf(donnee[1])));
                        cell = row.createCell(2);
                        cell.setCellValue(Integer.parseInt(
                                String.valueOf(donnee[2])));
                        i++;
                    }

                    ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                    workbook.write(archivo);

                    if (null != workbook && null != archivo) {
                        workbook.close();
                        archivo.close();
                    }

                    byte[] documentContent = archivo.toByteArray();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportServiceExcel.xlsx");
                    headers.setContentLength(documentContent.length);
                    return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
                }
            }
        }
        catch (Exception exc){
            //System.out.println("Exception : "+exc.toString());
        }

        return null;
    }



    @GetMapping(value = "/modifpriserdv/{idddv}")
    public String modifpriserdv(@PathVariable int idddv,
                               Model model, Principal principal){

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        // First , pull the RDV request :
        Demanderdv demanderdv = demanderdvRepository.findByIdddv(idddv);
        if(demanderdv != null){

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            // Liste des facturations :
            List<Object[]> listeMedecins = em.createQuery(
                "select distinct a.idmed,concat(a.nom,' ',a.prenom) as docteur from " +
                "anf_medecin a inner join anf_assignation b on a.idmed=b.idmed " +
                "inner join anf_affec_service c on c.idmed=a.idmed " +
                "inner join anf_services d on d.idser=c.idser " +
                "where b.idhop = "+assignation.getIdhop()+
                " and d.idser=" + demanderdv.getIdser()).getResultList();
            if(listeMedecins.size() > 0)
                model.addAttribute("listeMedecins", listeMedecins);

            em.getTransaction().commit();
            em.close();

            // Pick the service :
            /*
            Services services =
                    servicesRepository.findByIdser(demanderdv.getIdser());
            // Get the list of DOCTORS that belong to the service
            List<Affectationserv> listeAffectationservs =
                    affectationservRepository.findAllByIdser(services.getIdser());
            List<Integer> listeIdMedecin = new ArrayList<>();
            for(Affectationserv affec : listeAffectationservs){
                listeIdMedecin.add(affec.getIdmed());
            }
            // Now, pick the list of DOCTORS :
            List<Medecin> listeMedecins =
                    medecinRepository.findAllByIdmedIn(listeIdMedecin);
            model.addAttribute("listeMedecins", listeMedecins);
            */

            // Pick HOURS :
            List<Heure> listeHeure = heureRepository.findAll();
            model.addAttribute("listeHeure", listeHeure);

            model.addAttribute("demanderdv", demanderdv);

            // Set the date :
            String dates =
                new SimpleDateFormat("yyyy-MM-dd").
                        format(demanderdv.getDates());
            model.addAttribute("dates",
                    FonctionOrdinaire.formatterDate(dates));

            // Set the link :
            model.addAttribute("modification",
                    "/gestion/modifierpriserdv/"+idddv);

            //
            return "gestionattenterdv";
        }
        else return "redirect:/accfacturation";
    }


    @PostMapping(value = "/modifierpriserdv/{idddv}")
    public String modifierpriserdv(@PathVariable int idddv,
        @RequestParam("idmed") int idmed,
        @RequestParam("idheu") int idheu,
        @RequestParam("daterdv") String daterdv, Principal principal){
        // First , pull the RDV request :
        Demanderdv demanderdv = demanderdvRepository.findByIdddv(idddv);
        Medecin secretaire =
                medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation =
                assignationRepository.findByIdmed(secretaire.getIdmed());
        Date dateConsul;
        try {
            dateConsul = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(daterdv));
            demanderdv.setDates(dateConsul);
            demanderdv.setIdheu(idheu);
            demanderdv.setIdmed(idmed);
            // Mettre plutot l'ID de lhopital ici :
            demanderdv.setSecretaire(assignation != null ? assignation.getIdhop() : 0);
            demanderdv.setSuggestion("");
            demanderdvRepository.save(demanderdv);

            //
            Consultation cn =  consultationRepository.
                findTopByIdpat(demanderdv.getIdpat());

            // Retrieve first
            Factorisationrdv obj = factorisationrdvRepository.
                findByIdpatAndProvenanceAndIdmedAndDates(
                        demanderdv.getIdpat(), 0,idmed,
                        demanderdv.getDates()
                            );
            //
            if(obj == null) obj = new Factorisationrdv();
            // Now hit anf_factorisationrdv TABLE :
            obj = new Factorisationrdv();
            obj.setDates(demanderdv.getDates());
            obj.setHeure(idheu);
            obj.setHopital(cn.getIdhop());
            obj.setIdpat(demanderdv.getIdpat());
            obj.setIdmed(idmed);
            obj.setIdser(demanderdv.getIdser());
            obj.setProvenance(0);
            // Save :
            factorisationrdvRepository.save(obj);

            // Send a mail :
            try {
                // Get MEDECIN name :
                Medecin medecin =
                        medecinRepository.findByIdmed(idmed);
                // Get Heure :
                Heure heure = heureRepository.findByIdheu(idheu);
                // Populate :
                String patientEmail = demanderdv.getEmail();
                tachesService.envoiMailRdv(medecin.getNom()+" "+medecin.getPrenom(),
                        daterdv, heure.getLibelle(), patientEmail);
                // Envoyer un mail au MEDECIN :
            } catch (Exception exc) {
            }
        }
        catch (Exception exc){
        }
        return "redirect:/accfacturation";
    }


    private void mailCreation(String objet, String identifiant, String motpasse,
                              String adresseMail){
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,
                    "utf-8");
            StringBuilder contenu = new StringBuilder();
            contenu.append("<h2> Informations relatives au compte </h2>");
            contenu.append("<div><p>Identifiant : <span style='font-weight:bold;'>"+identifiant+"</span></p></div>");
            contenu.append("<div><p>Mot de passe : <span style='font-weight:bold;'>"+motpasse+"</span></p></div>");
            //contenu.append("<div><p>Mot de passe : "+motpasse+"</p></div>");
            contenu.append(
                    "<div><p>Lien de l'application : http://www.gestdp.com/gestion/login</p></div>");
            //
            helper.setText(String.valueOf(contenu), true);
            helper.setTo(adresseMail);
            helper.setSubject(objet);
            helper.setFrom("bendressouarnaud@gmail.com");
            helper.setCc("ngbandamakonan@gmail.com");
            emailSender.send(mimeMessage);
        }
        catch (Exception exc){
            //
        }
    }



    //
    @GetMapping(value = "/accgestionnaire")
    public String accgestionnaire(Principal principal, Model model){

        // Montant annuel :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Object montantAnnuel = em.createQuery(
                "select sum(a.montant) from anf_patient_wallet_state a ").
                getSingleResult();
        Double mGlobal = (Double) montantAnnuel;

        // Nombre de comptes
        Object patientJournalier = em.createQuery(
                "select count(a.idpws) from anf_patient_wallet_state a").
                getSingleResult();
        long comptes = (long) patientJournalier;


        //
        model.addAttribute("montantglobal", mGlobal);
        model.addAttribute("comptes", comptes);

        // Liste des facturations :
        List<Object[]> listederniersvers = em.createQuery(
            "select concat(a.nom,' ',a.prenom),b.montant," +
            "(select date(max(c.dates)) from anf_patient_wallet c where c.idpat=a.idpat) " +
            "as dte from anf_patient a inner join anf_patient_wallet_state b on " +
            "a.idpat=b.idpat" ).getResultList();
        model.addAttribute("listederniersvers", listederniersvers);
        // Historique des transactions :
        List<Object[]> histotransactions = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom),date(c.dates),c.montant," +
            "c.idpwa from anf_patient a inner join anf_facturation b on a.idpat=" +
            "b.idpat inner join anf_patient_wallet c on a.idpat=c.idpat " +
            "where b.idhop="+assignation.getIdhop() ).getResultList();
        model.addAttribute("histotransactions", histotransactions);

        em.getTransaction().commit();
        em.close();

        return "accueilgestionnaire";
    }



    //
    @GetMapping(value = "/nouvtransaction")
    public String nouvtransaction(Model model, Principal principal){

        // Assignation , ce profil est assigné à un et un seul HOPITAL :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
            "select distinct a.idpat,concat(a.nom,' ',a.prenom) as pte from anf_patient " +
            "a inner join anf_facturation b on a.idpat=b.idpat " +
            "where b.idhop = " + assignation.getIdhop()+
            " and a.provenance = 0").getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();

        //
        model.addAttribute("creation", 1);

        return "nouvtransaction";
    }


    @PostMapping(value = "/enregtransaction")
    public String enregtransaction(@RequestParam("idpat") int idpat,
        @RequestParam("montant") int montant,
        Model model, Principal principal)
    {
        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        //
        Patientwallet patientwallet = new Patientwallet();
        patientwallet.setMontant(montant);
        patientwallet.setIdpat(idpat);
        patientwallet.setIdmed(medecin.getIdmed());

        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            patientwallet.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            // Save :
            patientwalletRepository.save(patientwallet);

            // Now Hit anf_patient_wallet_state
            Patientwalletstate patientwalletstate =
                patientwalletstateRepository.findByIdpat(idpat);
            if(patientwalletstate==null){
                patientwalletstate = new Patientwalletstate();
                patientwalletstate.setIdpat(idpat);
                patientwalletstate.setMontant(Double.valueOf(montant));
            }
            else {
                // Get the total AMOUNT
                Long cotisation = patientwalletRepository.cotisation(idpat);
                patientwalletstate.setMontant(Double.valueOf(cotisation));
            }
            patientwalletstateRepository.save(patientwalletstate);

            // Envoi de mail aux profils ADMINISTRATEUR :
        }
        catch (Exception exc){
            //System.out.println("Exception : " + exc.toString());
        }

        // Retour :
        return "redirect:/accgestionnaire";
    }


    @GetMapping(value = "/modiftransaction/{idpwa}")
    public String modiftransaction(@PathVariable int idpwa,
        Model model, Principal principal){

        // get it :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
            "select distinct a.idpat,concat(a.nom,' ',a.prenom) as pte " +
            "from anf_patient a inner join anf_patient_wallet b on " +
            "a.idpat=b.idpat where b.idpwa= " + idpwa).getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();

        //
        Patientwallet patientwallet = patientwalletRepository.findByIdpwa(idpwa);
        model.addAttribute("patient", patientwallet);

        // Lien pour la modification :
        model.addAttribute("modification",
                "/gestion/modifiertransaction/"+idpwa);
        return "nouvtransaction";
    }


    @PostMapping(value = "/modifiertransaction/{idpwa}")
    public String modifiertransaction(@PathVariable int idpwa,
        @RequestParam("idpat") int idpat,
        @RequestParam("montant") int montant,
        Model model, Principal principal){
        // get the OLD MONEY :
        Patientwallet patientwallet = patientwalletRepository.findByIdpwa(idpwa);
        patientwallet.setMontant(montant);
        // save :
        patientwalletRepository.save(patientwallet);
        // -------- STATE
        Patientwalletstate patientwalletstate =
                patientwalletstateRepository.findByIdpat(idpat);
        // Get the total AMOUNT
        Long cotisation = patientwalletRepository.cotisation(idpat);
        patientwalletstate.setMontant(Double.valueOf(cotisation));
        patientwalletstateRepository.save(patientwalletstate);
        return "redirect:/accgestionnaire";
    }



    //
    @GetMapping(value = "/validereservation/{idpre}")
    public String validereservation(
        @PathVariable("idpre") int idpre,
        Model model, Principal principal){

        // get first RESERVATION Id :
        Patientreservation patientreservation =
            patientreservationRepository.findByIdpre(idpre);
        // Get the MEMBER NAME :
        Patientfamille patientfamille =
                patientfamilleRepository.findByIdpaf(
                        patientreservation.getIdpaf());
        model.addAttribute("membre", patientfamille);
        // get Services :
        List<Services> listeService = servicesRepository.findAll();
        model.addAttribute("listeService", listeService);

        //
        return "validerreservation";
    }


    // For new MESSAGE :
    @GetMapping(value = "/checkvalidereservation/{idpre}/{erreur}")
    public String validereservation(
            @PathVariable("idpre") int idpre,
            @PathVariable("erreur") int erreur,
            Model model, Principal principal){

        // get first RESERVATION Id :
        Patientreservation patientreservation =
                patientreservationRepository.findByIdpre(idpre);
        // Get the MEMBER NAME :
        Patientfamille patientfamille =
                patientfamilleRepository.findByIdpaf(
                        patientreservation.getIdpaf());
        model.addAttribute("membre", patientfamille);
        // get Services :
        List<Services> listeService = servicesRepository.findAll();
        model.addAttribute("listeService", listeService);

        // For the message :
        model.addAttribute("erreur", 0);

        //
        return "validerreservation";
    }



    @PostMapping(value = "/enregreservation")
    public String enregreservation(@RequestParam("idpaf") int idpaf,
        @RequestParam("sexe") String sexe,
        @RequestParam("montant") int montant,
        @RequestParam("service") int service,
        Model model, Principal principal)
    {
        //
        Medecin medecin =
            medecinRepository.findByIdentifiant(principal.getName().trim());
        //
        int idpat= 0;
        //------------
        Patientreservation patientreservation =
            patientreservationRepository.findByIdpaf(idpaf);

        // Get familly MEMBER :
        Patientfamille patientfamille =
            patientfamilleRepository.findByIdpaf(idpaf);
        // Get the familly OWNER :
        Patient patientOwner =
            patientRepository.findByIdpat(patientfamille.getIdparent());
        // Now get account SOLDE :
        Patientwalletstate patientwalletstate =
            patientwalletstateRepository.findByIdpat(patientOwner.getIdpat());

        if(patientwalletstate == null)
            return "redirect:/checkvalidereservation/"+
                    patientreservation.getIdpre()+"/0";

        // Now check whether the amount needed is less than the one in the account
        if(patientwalletstate.getMontant() >= montant){
            // OK, now hit PATIENT table :
            Patient getPatient =
                patientRepository.
                    findByNomAndPrenomAndTelephone(
                            patientfamille.getNom().trim(),
                            patientfamille.getPrenom().trim(),
                            patientfamille.getNumero().trim());
            if(getPatient == null){
                // Create a new one :
                getPatient = new Patient();
                getPatient.setNom(patientfamille.getNom().trim());
                getPatient.setPrenom(patientfamille.getPrenom().trim());
                getPatient.setCni("");
                getPatient.setEmail(patientfamille.getEmail());
                getPatient.setSexe(sexe);
                getPatient.setAge(0);
                getPatient.setProfession(0);
                getPatient.setAdresse("");
                // Set Identifiant
                String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
                date = date.replaceAll("-","").replaceAll(":","")
                        .replaceAll(" ","");
                getPatient.setIdentifiant(date);
                getPatient.setProvenance(idpaf); // Pour marquer la distinction
                getPatient.setTelephone(patientfamille.getNumero().trim());
                // Save :
                Patient pte = patientRepository.save(getPatient);
                idpat = pte.getIdpat();
            }
            else idpat = getPatient.getIdpat();

            // Now hit anf_facturation :
            Facturation facturation = new Facturation();
            facturation.setAssurance(1);
            facturation.setCouverture(0);
            try {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                facturation.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                // Get the HOSPITAL where this SECRETARY is :
                Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());
                facturation.setIdhop(assignation.getIdhop());
                facturation.setIdmed(medecin.getIdmed());
                facturation.setIdpat(idpat);
                facturation.setMontant(montant);
                facturation.setNumclient("");
                facturation.setService(service);
                facturation.setTypeclient(0);
                // Save :
                facturationRepository.save(facturation);

                // Substract the amount from the FAMILLY OWNER ACCOUNT :
                Double difference = patientwalletstate.getMontant() - montant;
                patientwalletstate.setMontant(difference);
                //
                patientwalletstateRepository.save(patientwalletstate);

                // Delete the BOOKING
                patientreservationRepository.deleteByIdpaf(idpaf);

                // Envoi de mail aux profils SECRETAIRE
            }
            catch (Exception exc){
            }
        }
        else return "redirect:/checkvalidereservation/"+
                patientreservation.getIdpre()+"/0";

        // Retour :
        return "redirect:/accfacturation";
    }



    // Reach the interface :
    @GetMapping(value = "/parammail")
    public String parammail(Model model){
        Parametrage parametrage = parametrageRepository.findByIdprm(1);
        if(parametrage != null){
            model.addAttribute("parametrage", parametrage);
        }
        return "parammail";
    }

    //----------
    @PostMapping(value = "/enregparamail")
    public String enregparamail(@RequestParam("secretaire") int secretaire,
        @RequestParam("administrateur") int administrateur,
        @RequestParam("gestionnaire") int gestionnaire,
        @RequestParam("superadmin") int superadmin,
        @RequestParam("laborantin") int laborantin,
        Model model){

        //
        Parametrage parametrage = parametrageRepository.findByIdprm(1);
        if(parametrage == null) parametrage = new Parametrage();
        parametrage.setEnvoimailadmin(administrateur);
        parametrage.setEnvoimaillaborantin(laborantin);
        parametrage.setEnvoimailmanager(gestionnaire);
        parametrage.setEnvoimailsecretaire(secretaire);
        parametrage.setEnvoimailsuperadmin(superadmin);
        parametrageRepository.save(parametrage);

        return "redirect:/parammail";
    }


    // Get PATIENT DATA
    @GetMapping(value = "/getpatientdata")
    public String getpatientdata(Model model, Principal principal){

        //
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom) as pte, a.telephone,a.email," +
            "a.identifiant,a.idpat from anf_patient a inner join anf_facturation b " +
            "on a.idpat=b.idpat where b.idhop =" +
            assignation.getIdhop()+" order by pte").getResultList();
        model.addAttribute("listepatient", listepatient);
        em.getTransaction().commit();
        em.close();
        //
        return "listepatient";
    }

    @GetMapping(value = "/getcalendrierdv")
    public String getcalendrierdv(Model model, Principal principal){
        //
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        model.addAttribute("medecinid", medecin.getIdmed());
        model.addAttribute("monurl", monUrl);
        return "calendrierdv";
    }

    @GetMapping(value = "/calendrierrdvmedecin/{idser}")
    public String calendrierrdvmedecin(@PathVariable int idser, Model model, Principal principal){
        //
        Medecin medecin =
                medecinRepository.findByIdentifiant(principal.getName().trim());
        model.addAttribute("service", idser);
        model.addAttribute("monurl", monUrl);
        model.addAttribute("secretaireid", medecin.getIdmed());
        return "calendriermedecinrdv";
    }

    // Reach CONSTANTE interface :
    @GetMapping(value = "/constantesecretaire")
    public String constantesecretaire(Model model, Principal principal){
        //
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeConstante =
            em.createQuery("select distinct concat(a.nom,' ',a.prenom) " +
            "as patient,date(b.dates),b.heure,b.poids,b.taille,b.tension," +
            "b.idcons,b.temperature,b.tensionarterielle from anf_patient a " +
            "inner join anf_constante b on a.idpat=b.idpat where b.idmed = "+
            medecin.getIdmed()+" and b.idcon=0 and date(b.dates)=date(now())").getResultList();
        em.getTransaction().commit();
        em.close();
        model.addAttribute("listeConstante", listeConstante);
        return "accueilconstante";
    }

    @GetMapping(value = "/updatepatient")
    public String updatepatient(Model model, Principal principal){
        //
        List<Patient> listePts = patientRepository.findAll();
        for(Patient pt : listePts){
            //pt.setNoms(pt.getNom());
            //pt.setPrenoms(pt.getPrenom());
            //pt.setCnis(pt.getCni());
            //pt.setEmails(pt.getEmail());
            //pt.setTelephones(pt.getTelephone());
            patientRepository.save(pt);
        }

        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeConstante =
                em.createQuery("select distinct concat(a.nom,' ',a.prenom) " +
                        "as patient,date(b.dates),b.heure,b.poids,b.taille,b.tension," +
                        "b.idcons,b.temperature,b.tensionarterielle from anf_patient a " +
                        "inner join anf_constante b on a.idpat=b.idpat where b.idmed = "+
                        medecin.getIdmed()+" and b.idcon=0").getResultList();
        em.getTransaction().commit();
        em.close();
        model.addAttribute("listeConstante", listeConstante);
        return "accueilconstante";
    }


    // Nouvelle entree :
    @GetMapping(value = "/approvisionnement")
    public String approvisionnement(Model model, Principal principal)
    throws Exception{
        model.addAttribute("creation", 1);
        String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        model.addAttribute("currentdate", currentdate);
        // Get Approvisionnement :
        List<Medicament> listeMedicament =
            medicamentRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeMedicament", listeMedicament);
        return "creerapprovisonnement";
    }


    // Display the HISTORY :
    @GetMapping(value = "/historiqueappro")
    public String historiqueappro(Model model, Principal principal) {

        // Get DRUGs list :
        List<Medicament> listeMedic = medicamentRepository.findAll();
        model.addAttribute("listeMedicament", listeMedic);

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
        "select distinct a.libelle, date(b.dates) as dateappro, b.heure, " +
            "b.numlot,date(b.dateperemption) as datepremption, concat(c.nom,' '," +
            "c.prenom) as utilisateur from anf_medicament a inner join " +
            "anf_approvionnement b on a.idmd=b.idmd inner join anf_medecin c on " +
            "c.idmed=b.idusr").getResultList();

        // Niveaux faibles
        Seuil sl = seuilRepository.findTopByOrderByIdseuDesc();
        //System.out.println("Seuil : "+sl.getSeuil());
        List<Medicament> niveauFaible =
                medicamentRepository.findByQuantiteLessThanEqual(sl.getSeuil());
        if(niveauFaible != null){
            if(niveauFaible.size() > 0){
                model.addAttribute("niveauFaible", niveauFaible);
            }
        }

        emr.getTransaction().commit();
        emr.close();
        model.addAttribute("listeAppro", resultat);

        return "histoapprovisionement";
    }


    // Enregistrer APPROVISIONNEMENT :
    @PostMapping(value = "/enregapprovisionnement")
    public String enregapprovisionnement(Principal principal,
        @RequestParam("idmedic") int idmedic,
        @RequestParam("prixunitaire") int prixunitaire,
        @RequestParam("medicament") String medicament,
        @RequestParam("dateperemption") String dateperemption,
        @RequestParam("datesaisie") String dateapprovisionnement,
        @RequestParam("quantite") int quantite,
        @RequestParam("numlot") String numlot
        ){

        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        //
        int keepDrogId = 0;
        if(medicament.trim().length() > 0){
            // Save it :
            Medicament mdet = new Medicament();
            mdet.setLibelle(medicament);
            mdet.setQuantite(quantite);
            mdet.setPrix(prixunitaire);
            Medicament keepObject = medicamentRepository.save(mdet);
            keepDrogId = keepObject.getIdmd();
        }
        //
        if(idmedic > 0){
            // Update the drog PRICE and Quantity:
            Medicament mdt = medicamentRepository.findByIdmd(idmedic);
            mdt.setPrix(prixunitaire);
            mdt.setQuantite(mdt.getQuantite()+quantite);
            medicamentRepository.save(mdt);
        }
        //
        if((idmedic > 0) || (keepDrogId >0)) {
            try {
                Approvisionnement approvisionnement = new Approvisionnement();
                approvisionnement.setIdusr(medecin.getIdmed());
                approvisionnement.setIdmd(idmedic > 0 ? idmedic : keepDrogId);
                String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
                approvisionnement.setHeure(heure);
                String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                // Date APPROVISIONNEMENT
                try {
                    Date dateAppro = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(dateapprovisionnement));
                    approvisionnement.setDates(dateAppro);
                } catch (Exception exc) {
                    //
                    approvisionnement.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                }

                // Date PEREMPTION
                try {
                    Date dateP = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(dateperemption));
                    approvisionnement.setDateperemption(dateP);
                } catch (Exception exc) {
                    //
                    approvisionnement.setDateperemption(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                }

                // Set the OTHERS :
                approvisionnement.setNumlot(numlot);
                approvisionnement.setPrixunitaire(prixunitaire);
                approvisionnement.setQuantite(quantite);
                approvisionnementRepository.save(approvisionnement);
            } catch (Exception exc) {
            }
        }
        return "redirect:/accpharmacie";
    }


    // Nouvelle entree :
    @GetMapping(value = "/ventemedicament")
    public String ventemedicament(Model model, Principal principal)
            throws Exception{

        // Get Customer DATA :
        List<AnfCustomer> listeClient = customerRepository.findAll();
        model.addAttribute("listeClient", listeClient);
        model.addAttribute("creation", 1);
        //
        List<String> listeProfil = new ArrayList<>();
        listeProfil.add("medecin");
        List<Medecin> listeMedecin =
            medecinRepository.findAllByProfilIn(listeProfil);
        model.addAttribute("listemedecin",listeMedecin);

        model.addAttribute("monurl", monUrl);
        return "ventemedicament";
    }


    // Enregistrer APPROVISIONNEMENT :
    @PostMapping(value = "/enregventedrog")
    public String enregventedrog(Principal principal,
        @RequestParam("medicamentid[]") Integer[] medicamentid,
        @RequestParam("prixdrog[]") Integer[] prixdrog,
        @RequestParam("quantite[]") Integer[] quantite,
        @RequestParam("idcus") int idcus,
        @RequestParam("customer") String customer,
        @RequestParam("idmed") int idmed
    ) {
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        // Add Ticket :
        TicketMedic tm = new TicketMedic();
        try{
            String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            tm.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
            int keepTicketId = ticketMedicRepository.save(tm).getIdtck();
            // Now Reach VENTEMEDICAMENT :
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            for (int i = 0; i<medicamentid.length; i++) {
                Ventemedicament vm = new Ventemedicament();
                vm.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                vm.setHeure(heure);
                vm.setIdmd(medicamentid[i]);
                vm.setIdtck(keepTicketId);
                vm.setIdusr(medecin.getIdmed());
                vm.setQuantite(quantite[i]);
                vm.setPrix(prixdrog[i]);

                //
                if(customer.trim().length() > 0){
                    // Create Customer :
                    AnfCustomer cr = new AnfCustomer();
                    cr.setNom(customer.trim());
                    idcus = customerRepository.save(cr).getIdcus();
                }

                // Add new lines to VM :
                vm.setIdcus(idcus);
                vm.setIdmed(idmed);

                // Save :
                ventemedicamentRepository.save(vm);

                // Decrease DRUGS quantity from anf_medicament table for each drug :
                Medicament mt = medicamentRepository.findByIdmd(medicamentid[i]);
                if(mt != null){
                    int total = mt.getQuantite();
                    total = total - quantite[i];
                    mt.setQuantite(total);
                    // Save it :
                    medicamentRepository.save(mt);

                    // Now, if the drug's level matches the threshold, notify :
                    Seuil seuil = seuilRepository.findTopByOrderByIdseuDesc();
                    if(seuil==null){
                        seuil = new Seuil();
                        seuil.setSeuil(7);
                        seuil.setIdusr(medecin.getIdmed());
                        seuil.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                        seuilRepository.save(seuil);
                    }
                    //
                    if(total <= seuil.getSeuil()){
                        //System.out.println("Seuil atteint");
                        // Send the mail :
                        List<Medecin> notifisMails =
                            medecinRepository.findAllByProfil("pharmacien");
                        //System.out.println("Taille : "+notifisMails.size());
                        String[] tabMails = new String[notifisMails.size()];
                        for(int c=0; c < notifisMails.size(); c++){
                            tabMails[c] = notifisMails.get(c).getEmail();
                        }
                        String objet="Niveau faible de "+mt.getLibelle();
                        String message ="Le médicament "+mt.getLibelle()+
                            " sera bientôt en rupture de stock.";
                        tachesService.envoiMail(objet, message, tabMails);
                    }
                }
            }
        }
        catch (Exception exc){
        }

        return "redirect:/accpharmacie";
    }

}
