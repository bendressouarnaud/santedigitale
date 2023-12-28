package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.*;
import com.maternite.gestion.repositories.*;
import net.bytebuddy.asm.Advice;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Controller
public class PatientController {

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
    ExamenRepository examenRepository;
    @Autowired
    CmdexamensRepository cmdexamensRepository;
    @Autowired
    PharmacieRepository pharmacieRepository;
    @Autowired
    QcodesRepository qcodesRepository;
    @Autowired
    AssignationpharmRepository assignationpharmRepository;
    @Autowired
    ParametrageRepository parametrageRepository;
    @Autowired
    HistoassuranceRepository histoassuranceRepository;
    @Autowired
    ProthesesRepository prothesesRepository;

    @Autowired
    JavaMailSender emailSender;
    @Autowired
    TachesService tachesService;
    @Autowired
    TamponsRepository tamponsRepository;
    @Autowired
    FactorisationrdvRepository factorisationrdvRepository;
    @Autowired
    FacturationRepository facturationRepository;
    @Autowired
    AssuranceRepository assuranceRepository;
    @Autowired
    GroupesanguinRepository groupesanguinRepository;
    @Autowired
    HospitalisationRepository hospitalisationRepository;
    @Autowired
    UserprofileRepository userprofileRepository;
    @Autowired
    CabinetdentaireRepository cabinetdentaireRepository;
    @Autowired
    InterventionsRepository interventionsRepository;
    @Autowired
    CompteurRepository compteurRepository;
    @Autowired
    ProlongationRepository prolongationRepository;
    @Autowired
    AnfCustomerRepository customerRepository;


    @PersistenceUnit
    EntityManagerFactory emf;

    @Value("${mon.lienlocal}")
    private String monUrl;

    @Value("${cabinet.dentaire}")
    private String cabinetdentaireId;

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    //
    @GetMapping(value = "/createconsultation")
    public String createconsultation(Model model, HttpSession session,
        Principal principal)
    {
        // Perform an ACTION :
        /*String test="Macules hypopigmentées regroupées, de taille différente, blanchissant" +
                " au grattage doux, localisées au visage, les bras, la partie supérieure" +
                " du dos, le cou. Evoluant depuis 02 semaines. Xérose cutanée modérée.";
        Commentaires ctest = new Commentaires();
        decouperTexte(test,ctest);
        */

        //
        /*for(String texte : ctest.getListeCommentaire()){
            System.out.println("Texte : "+texte);
        }*/

        Object medecinid = session.getAttribute("medecinid");
        if(medecinid != null){

            Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

            // Because SECRETAIRE can create PATIENT, just pick the LIST of those SECRETAIRES :
            List<Assignation> listeAssignation =
                    assignationRepository.findAllByIdmed(medecin.getIdmed());
            // Get all HOSPITALs the MEDECIN is attached to:
            List<Integer> listeIdHopitaux = new ArrayList<Integer>();
            for(Assignation assignation : listeAssignation){
                listeIdHopitaux.add(assignation.getIdhop());
            }

            // Now get Medecins IDs:
            List<Assignation> listeMedecinsIds =
                    assignationRepository.findAllByIdhopIn(listeIdHopitaux);
            List<Integer> medecinsIds = new ArrayList<Integer>();
            for(Assignation assignation : listeMedecinsIds){
                if(assignation.getIdmed() != medecin.getIdmed())
                    medecinsIds.add(assignation.getIdmed());
            }

            //
            List<Integer> secretaireIds = new ArrayList<Integer>();
            if(medecinsIds.size() > 0){
                List<String> profils = new ArrayList<>();
                profils.add("secretairemedicale");
                profils.add("secretaire");
                profils.add("aidesoignante");
                List<Medecin> listeMedecinSecretaire =
                        medecinRepository.findAllByProfilInAndIdmedIn(profils,
                                medecinsIds);
                /*        medecinRepository.findAllByProfilAndIdmedIn("secretaire",
                                medecinsIds);
                */
                for(Medecin medec : listeMedecinSecretaire){
                    secretaireIds.add(medec.getIdmed());
                }
            }

            // Build the list :
            String maListeIds = "";
            if(secretaireIds.size() == 1){
                maListeIds = ""+secretaireIds.get(0)+"";
            }
            else if(secretaireIds.size() > 1){
                maListeIds = "";
                for(int i = 0; i<secretaireIds.size(); i++){
                    if(i==0) maListeIds+=secretaireIds.get(i);
                    else maListeIds+=","+secretaireIds.get(i);
                }
                maListeIds+="";
            }

            // Get PATIENT, DOCTOR has worked with :
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // Call the STORED PROCEDURE :
            StoredProcedureQuery procedureQuery = em
                    .createStoredProcedureQuery("findAllPatientByDoctor");
            procedureQuery.registerStoredProcedureParameter("doctorID",
                    Integer.class, ParameterMode.IN);
            procedureQuery.setParameter("doctorID", medecin.getIdmed());
            procedureQuery.registerStoredProcedureParameter("motdepasse",
                    String.class, ParameterMode.IN);
            procedureQuery.setParameter("motdepasse", "K8_jemange");
            List<Object[]> listepatient = procedureQuery.getResultList();

            maListeIds = "("+maListeIds+")";

            if(secretaireIds.size() > 0) {
                // Get All CONSTANTES to be displayed when the DOCTOR is connected :
                List<Object[]> listeConstantes =
                    em.createQuery("select distinct concat(a.nom,' ',a.prenom) as patient," +
                    "b.heure,b.poids,b.taille,b.tensionarterielle,b.idcons,b.temperature" +
                    ",b.pouls from anf_patient a " +
                    "inner join anf_constante b on a.idpat=b.idpat where b.idmed in "+
                    maListeIds+" and b.idcon=0 and b.iddocteur="+medecinid.toString()+
                    " and date(b.dates)=date(now())").getResultList();
                if(listeConstantes.size() > 0) {
                    model.addAttribute("listeconstantes", listeConstantes);
                }
            }

            // Pass it to the MODEl :
            model.addAttribute("listepatient", listepatient);

            // Liste des hopitaux : mais seulement ceux auquels il est assigné :
            List<Assignation> listeDesHopitaux = assignationRepository.findAllByIdmed(
                Integer.valueOf(medecinid.toString()));
            // GET HOSPITALS IDs:
            List<Long> listeHopitalID = new ArrayList<Long>();
            for(Assignation ass : listeDesHopitaux){
                listeHopitalID.add(Long.valueOf(ass.getIdhop()));
            }
            // Now find HOSPITALs :   -->  List<Hopital> listehopital = hopitalRepository.findAll();
            List<Hopital> listehopital = hopitalRepository.findAllByIdhopIn(listeHopitalID);
            model.addAttribute("listehopital", listehopital);
            //-----------------------------------------------------------------
            // Liste des services : mais seulement ceux auquels il est assigné :
            List<Affectationserv> listeDesAffectationSer = affectationservRepository.
                    findAllByIdmed(Integer.valueOf(medecinid.toString()));
            // GET HOSPITALS IDs:
            List<Integer> listeServiceID = new ArrayList<Integer>();
            for(Affectationserv asv : listeDesAffectationSer){
                listeServiceID.add(asv.getIdser());
            }
            // Liste des services :
            List<Services> listeservices = servicesRepository.findAllByIdserIn(listeServiceID);
            model.addAttribute("listeservices", listeservices);

            // Liste des professions :
            List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
            model.addAttribute("listeprofession", listeprofession);
            // Liste des services :
            //List<Services> listeservices = servicesRepository.findAll();
            //model.addAttribute("listeservices", listeservices);

            //
            model.addAttribute("creation", 0);

            // Close :
            em.getTransaction().commit();
            em.close();

            // Get list of NATURE DES FICHIERS
            List<Naturefichier> listenaturefichiers = naturefichierRepository.findAll();
            model.addAttribute("listenaturefichiers", listenaturefichiers);

            // Now GET the current date :
            //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
            //String currentheure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            model.addAttribute("currentdate", currentdate);

            /*
            ZoneId zoneId = ZoneId.of("Africa/Nouakchott");
            LocalTime localTime=LocalTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime=localTime.format(formatter);
            model.addAttribute("currentheure", formattedTime);
            */

            // Get the list of TIMES :
            List<Heure> listeHeure = heureRepository.findAll();
            model.addAttribute("listeHeure", listeHeure);

            // Get list of MODE-DE-VIE :
            //List<Modedevie> listemodedevies = modedevieRepository.findAll();
            //model.addAttribute("listemodedevies", listemodedevies);

            // Add LABORATOIRE EXAMS :
            List<Examen> liseExamens = examenRepository.findAll();
            model.addAttribute("liseExamens", liseExamens);

            //
            model.addAttribute("monurl", monUrl);
            model.addAttribute("cabinetdentaireId", cabinetdentaireId);

            // to allow to check PATIENT DATA if CABINET DENTAIRE is selected :
            model.addAttribute("checkfirstuser", 1);

            return "createconsultation";
        }
        else return "redirect:/patient";
    }


    // Tableau de bord : dashboard
    @GetMapping(value = "/dashboard")
    public String tableaubord(Model model, HttpSession session, Principal principal)
    {
        //Object medecinid = session.getAttribute("medecinid");
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
                "select a.sexe,count(a.idpat) from anf_patient a inner join " +
                        "anf_consultation b on a.idpat=b.idpat where b.idmed=" +medecin.getIdmed()+
                        " group by a.sexe" )
                .getResultList();
        emr.getTransaction().commit();

        // Browse :
        String retour = "";
        for(Object[] ob : resultat){
            // Create a new Object :
            String sexe = (String)ob[0];
            sexe = sexe.trim();
            //System.out.println("Sexe : "+sexe);
            if(sexe.equals("F")) retour += "    "+ (String)ob[0] + ":"+(Long)ob[1];
            else if(sexe.equals("M")) retour += "    "+ (String)ob[0] + ":"+(Long)ob[1];
        }

        emr.close();
        //
        model.addAttribute("statistiques", retour);
        model.addAttribute("medecinid", medecin.getIdmed());
        model.addAttribute("monurl", monUrl);
        //return "tableaudebord";
        return "tableaudebordbis";
    }


    //
    @GetMapping(value = "/accueilpatient")
    public String accueilpatient(Model model, HttpSession session)
    {
        Object medecinid = session.getAttribute("medecinid");
        if(medecinid != null){

            // Get all the AUTHORIZATION :
            /*List<Autorisation> listeAutorisation =
                    autorisationRepository.findAllByIdmeddestin(Integer.parseInt(medecinid.toString()));
            // Set the filter :
            String filtre = "(";
            for (int i = 0; i < listeAutorisation.size(); i++){
                if(i==0 && listeAutorisation.size()==1){
                    filtre += listeAutorisation.get(i).getIdmedorigin()+")";
                }
                else if(i==0 && listeAutorisation.size()>1){
                    filtre += listeAutorisation.get(i).getIdmedorigin()+",";
                }
                else if(i==(listeAutorisation.size()-1)){
                    filtre += listeAutorisation.get(i).getIdmedorigin()+")";
                }
                else if(i<(listeAutorisation.size()-1)){
                    filtre += listeAutorisation.get(i).getIdmedorigin()+",";
                }
            }
            */

            // Get
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            //if(listeAutorisation.size() ==0 ) {
            List<Object[]> listePatient = em.createQuery(
                "select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle, " +
                "(select max(date(e.dates)) from anf_consultation e where e.idpat=a.idpat) as " +
                "dte from anf_patient a inner join anf_consultation b on a.idpat=b.idpat inner " +
                "join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on d.idhop=b.idhop " +
                "where c.idmed = " + medecinid.toString()).getResultList();
            // Pass it to the MODEl :
            model.addAttribute("listePatient", listePatient);

            // Now run query to get the PATIENT registred today :
            List<Object[]> listePatientToday = em.createQuery(
                "select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle" +
                " from anf_patient a inner join anf_consultation b on a.idpat=b.idpat inner " +
                "join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on d.idhop=b.idhop " +
                "where date(b.dates)=date(now()) and c.idmed = " + medecinid.toString()).getResultList();
            // Pass it to the MODEl :
            model.addAttribute("listePatientToday", listePatientToday);

            //}
            /*else{
                List<Object[]> listePatient = em.createQuery(
                        "select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle from anf_patient a inner join anf_consultation b " +
                                "on a.idpat=b.idpat inner join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on " +
                                "d.idhop=b.idhop " +
                                "where c.idmed = " + medecinid.toString()).getResultList();

                List<Object[]> listePatientAuto = em.createQuery("select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle from anf_patient a inner join anf_consultation b " +
                        "on a.idpat=b.idpat inner join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on " +
                        "d.idhop=b.idhop " +
                        "where c.idmed in "+filtre).getResultList();
                if(listePatientAuto.size() > 0) {
                    for (Object[] objet : listePatientAuto) {
                        listePatient.add(objet);
                    }
                }

                // Pass it to the MODEl :
                model.addAttribute("listePatient", listePatient);
            }*/

            // le nombre de consultation :
            long nbreconsultation = 0;
            try {
                Object nbre_consultation = em.createQuery(
                        "select count(b.idcon) from anf_medecin a inner join " +
                                "anf_consultation b on a.idmed=b.idmed " +
                                "where a.idmed = " + medecinid.toString() +
                                " group by b.idmed").getSingleResult();
                nbreconsultation = (Long) nbre_consultation;
            }
            catch (Exception exc){
                nbreconsultation = 0;
            }
            model.addAttribute("nbreconsultation", nbreconsultation);

            // Le nombre d'hopitaux :
            long nbrehopitaux = 0;
            try {
                Object nbre_hopitaux = em.createQuery(
                        "select count(distinct b.idhop) from anf_medecin a inner join " +
                                "anf_consultation b on a.idmed=b.idmed " +
                                "where a.idmed = " + medecinid.toString() +
                                " group by b.idmed"
                ).getSingleResult();
                nbrehopitaux = (Long) nbre_hopitaux ;
            }
            catch (Exception exc){}
            model.addAttribute("nbrehopitaux", nbrehopitaux);

            // Le nombre de spécialité :
            long nbrespecialite = 0;
            try {
                Object nbre_specialite = em.createQuery(
                        "select count(distinct b.idser) from anf_medecin a inner join " +
                                "anf_consultation b on a.idmed=b.idmed " +
                                "where a.idmed = " + medecinid.toString() +
                                " group by b.idmed"
                ).getSingleResult();
                nbrespecialite = (Long) nbre_specialite;
            }
            catch (Exception exc){}
            model.addAttribute("nbrespecialite", nbrespecialite);

            // Le nombre de patients :
            long nbrepatients = 0;
            try {
                Object nbre_patients = em.createQuery(
                        "select count(distinct b.idpat) from anf_medecin a inner join " +
                                "anf_consultation b on a.idmed=b.idmed " +
                                "where a.idmed = " + medecinid.toString() +
                                " group by b.idmed"
                ).getSingleResult();
                nbrepatients = (Long) nbre_patients;
            }
            catch (Exception exc){}
            model.addAttribute("nbrepatients", nbrepatients);


            // Get ETHYLISME, DIABETE , HYPERTENSION :
            long nbreEthylisme = 0;
            try {
                Object nbre = em.createQuery(
                        "select count(a.idcon) as tot from " +
                                "anf_consultation a where a.idmed="+medecinid.toString()+
                                " and a.ethylisme=1").getSingleResult();
                nbreEthylisme = (Long) nbre;
            }
            catch (Exception exc){}
            model.addAttribute("nbreEthylisme", nbreEthylisme);
            //---
            long nbreDiabete = 0;
            try {
                Object nbre = em.createQuery(
                        "select count(a.idcon) as tot from " +
                                "anf_consultation a where a.idmed="+medecinid.toString()+
                                " and a.diabete=1").getSingleResult();
                nbreDiabete = (Long) nbre;
            }
            catch (Exception exc){}
            model.addAttribute("nbreDiabete", nbreDiabete);
            //---
            long nbreHypertension = 0;
            try {
                Object nbre = em.createQuery(
                        "select count(a.idcon) as tot from " +
                                "anf_consultation a where a.idmed="+medecinid.toString()+
                                " and a.hypertension=1 ").getSingleResult();
                nbreHypertension = (Long) nbre;
            }
            catch (Exception exc){}
            model.addAttribute("nbreHypertension", nbreHypertension);


            em.getTransaction().commit();
            em.close();
        }

        return "accueilpatient";
    }


    // Pour ajouter un enregistrement :
    @PostMapping(value = "/enregconsultation")
    public String enregconsultation(@RequestParam("idpatient") int idpatient,
        @RequestParam(name="age", required = false) Integer age,
        @RequestParam("datenaissance") String datenaissance,
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("profession") int profession,
        @RequestParam("saisirprof") String saisirprof,
        @RequestParam("adresse") String adresse,
        @RequestParam("sexe") String sexe,
        @RequestParam("cni") String cni,
        @RequestParam("telephone") String telephone,
        @RequestParam("email") String email,
        @RequestParam("hopital") int hopital,
        @RequestParam("envoyepar") String envoyepar,
        @RequestParam("service") int service,
        @RequestParam("diagnostic") String diagnostic,
        @RequestParam("dateobserv[]") String[] dateobservation,
        @RequestParam("commobserv[]") String[] commobserv,
        @RequestParam("dateconsultation") String dateconsultation,
        @RequestParam(name="fichiersajoute[]", required = false) MultipartFile[] fichiers,
        @RequestParam(name="naturefichier[]", required = false) Integer[] idnaturefichier,
        @RequestParam("daterdv") String daterdv,
        @RequestParam("heurerdv") Integer heurerdv,
        @RequestParam(name="choixrdv", required = false) Integer choixrdv,
        @RequestParam(name="idconstante", required = false) Integer idcons,

        @RequestParam(name="motif", required = false) String motif,
        @RequestParam(name="diabete", required = false) Integer diabete,
        @RequestParam(name="hypertension", required = false) Integer hypertension,
        @RequestParam(name="pblmecardiaque", required = false) Integer pblmecardiaque,
        @RequestParam(name="pblmepulmon", required = false) Integer pblmepulmon,
        @RequestParam(name="drepano", required = false) Integer drepano,
        @RequestParam(name="autres", required = false) Integer autres,
        @RequestParam(name="commentautre", required = false) String commentautre,
        @RequestParam(name="modedevie", required = false) Integer modedevie,
        @RequestParam(name="examenclinique", required = false) String examenclinique,
        @RequestParam(name="conclusion", required = false) String conclusion,
        @RequestParam(name="traitement", required = false) String traitement,

        @RequestParam(name="interrogatoire", required = false) String interrogatoire,
        @RequestParam(name="examenphysique", required = false) String examenphysique,
        @RequestParam(name="conduiteatenir", required = false) String conduiteatenir,

        @RequestParam(name="tabagismeactif", required = false) Integer tabagismeactif,
        @RequestParam(name="tabagismepassif", required = false) Integer tabagismepassif,
        @RequestParam(name="ethylisme", required = false) Integer ethylisme,
        @RequestParam(name="sedentaire", required = false) Integer sedentaire,
        @RequestParam(name="autresmode", required = false) Integer autresmode,
        @RequestParam(name="autresmodecomment", required = false) String autresmodecomment,

        @RequestParam(name="diagnosticretenu", required = false) String diagnosticretenu,
        @RequestParam(name="hospitalisation", required = false) Integer hospitalisation,

        @RequestParam(name="soins", required = false) String soins,
        @RequestParam(name="avisconfrere", required = false) String avisconfrere,
        @RequestParam(name="ordonnance", required = false) String ordonnance,
        @RequestParam(name="renseignementsclin", required = false) String renseignementsclin,
        @RequestParam(name="examensnondefinis", required = false) String examensnondefinis,

        @RequestParam(name="poids", required = false) Double poids,
        @RequestParam(name="taille", required = false) Double taille,
        @RequestParam(name="temperature", required = false) Double temperature,
        @RequestParam(name="tensionarterielle", required = false) String tensionarterielle,
        @RequestParam(name="pouls", required = false) Integer pouls,

        @RequestParam(name="chambre", required = false) String chambre,
        @RequestParam(name="lit", required = false) String lit,
        @RequestParam(name="datentree", required = false) String datentree,
        @RequestParam(name="heurehospi", required = false) String heurehospi,
        @RequestParam(name="motifhospi", required = false) String motifhospi,
        @RequestParam(name="bilanbio", required = false) String bilanbio,
        @RequestParam(name="bilanradio", required = false) String bilanradio,
        @RequestParam(name="nbrejour", required = false) String nbrejour,

        @RequestParam(name="cardiopathie", required = false) String cardiopathie,
        @RequestParam(name="troubles", required = false) String troubles,
        @RequestParam(name="hta", required = false) String hta,
        @RequestParam(name="diabetedent", required = false) String diabetedent,
        @RequestParam(name="asthme", required = false) String asthme,
        @RequestParam(name="infectionsorl", required = false) String infectionsorl,
        @RequestParam(name="vih", required = false) String vih,
        @RequestParam(name="antibiotique", required = false) String antibiotique,
        @RequestParam(name="anesthesie", required = false) String anesthesie,
        @RequestParam(name="quinine", required = false) String quinine,
        @RequestParam(name="latex", required = false) String latex,
        @RequestParam(name="autresdentaire", required = false) String autresdentaire,
        @RequestParam(name="dentsmanquantes", required = false) String dentsmanquantes,
        @RequestParam(name="appareillage", required = false) Integer appareillage,
        @RequestParam(name="hygienebuccale", required = false) Integer hygienebuccale,
        @RequestParam(name="articuledentaire", required = false) Integer articuledentaire,
        @RequestParam(name="langue", required = false) Integer langue,
        @RequestParam(name="suceur", required = false) Integer suceur,
        @RequestParam(name="orthodontique", required = false) Integer orthodontique,

        @RequestParam("dateintervention[]") String[] dateintervention,
        @RequestParam("dents[]") String[] dents,
        @RequestParam("natureintervention[]") String[] natureintervention,

        @RequestParam("enplace[]") String[] enplace,
        @RequestParam("localisation[]") String[] localisation,
        @RequestParam("typeproth[]") String[] typeproth,
        @RequestParam("dateprothese[]") String[] dateprothese,

        @RequestParam("examens[]") Integer[] examens,
        HttpSession session, Model model)
    {
        Object medecinid = session.getAttribute("medecinid");
        //                   ---    @RequestParam("choixrdv[]") String[] choixrdv,
        if(idpatient == 0){
            // Save it first :
            String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
            date = date.replaceAll("-","").replaceAll(":","")
                    .replaceAll(" ","");
            //
            //String identifiant = medecinid.toString() + date;
            String identifiant = date;

            Patient patient = new Patient();
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setCni(cni);
            patient.setTelephone(telephone);
            patient.setEmail(email);
            patient.setSexe(sexe);

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
                patient.setDatecreation(dateAujourdhui);
            }
            catch (Exception exc){}

            //patient.setAge(age);
            // In case Profession has been set :
            if(saisirprof.trim().length() > 0){
                // Check if it does not exist already :
                List<String> listeProfession = new ArrayList<>();
                listeProfession.add(saisirprof.toLowerCase());
                listeProfession.add(saisirprof.toUpperCase());
                listeProfession.add(saisirprof);
                List<Profession> checkListe =
                        professionRepository.findByLibelleIn(listeProfession);
                if((checkListe == null) || (checkListe.size() ==0)){
                    // Does not exist :
                    Profession profess = new Profession();
                    profess.setLibelle(saisirprof);
                    Profession pfs = professionRepository.save(profess);
                    patient.setProfession(pfs.getIdprof());
                }
                else patient.setProfession(checkListe.get(0).getIdprof());
            }
            else patient.setProfession(profession);
            patient.setAdresse(adresse);
            //**************
            try {
                // Identifiant UNIQUE :
                String identifiantUnique =
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String[] lesIds = identifiantUnique.split("-");
                // Get value from anf-compteur :
                Date dateToday = new SimpleDateFormat("yyyy-MM-dd").
                        parse(identifiantUnique);
                Compteur cpr = compteurRepository.findByDates(dateToday);
                if(cpr == null){
                    cpr = new Compteur();
                    cpr.setDates(dateToday);
                    cpr.setValeur(1);
                    compteurRepository.save(cpr);
                    String finalId = lesIds[0].substring(2) + lesIds[1] +
                            lesIds[2]+"1";
                    patient.setIdentifiant(finalId);
                }
                else{
                    int cptrs = cpr.getIdcpr();
                    cptrs++;
                    String finalId = lesIds[0].substring(2) + lesIds[1] +
                            lesIds[2]+ String.valueOf(cptrs);
                    patient.setIdentifiant(finalId);
                    cpr.setValeur(cptrs);
                    compteurRepository.save(cpr);
                }
            }
            catch (Exception exc){
                patient.setIdentifiant(date);
            }
            //**************
            patient.setResidence("");
            patient.setNompere("");
            patient.setContactpere("");
            patient.setNommere("");
            patient.setContactmere("");
            patient.setRepondant("");
            patient.setContactrepondant("");
            patient.setGroupesanguin(0);
            patient.setProvenance(0);
            patient.setHaswallet(0);
            patient.setSociete("");
            patient.setNomjeunefille("");
            patient.setLieunaissance("");

            // Save :
            idpatient = patientRepository.save(patient).getIdpat();

            //
            //Patient pt = patientRepository.findByIdentifiant(identifiant);
            //idpatient = pt.getIdpat();
        }
        else{
            // Update PATIENT DATA anyway :
            Patient pt = patientRepository.findByIdpat(idpatient);
            if(pt != null){
                // Do it :
                idpatient = pt.getIdpat();
                pt.setEmail(email);
                pt.setTelephone(telephone);
                pt.setCni(cni);
                pt.setAdresse(adresse);
                //pt.setAge(age);
                try {
                    Date dateNaiss = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(datenaissance));
                    pt.setDatenaissance(dateNaiss);
                    String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    Date dateAujourdhui = new SimpleDateFormat("yyyy-MM-dd").
                            parse(dte);
                    long diffInMillies = Math.abs(dateAujourdhui.getTime() - dateNaiss.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    // Now in YEARS :
                    int annee = (int)(diff / 365);
                    pt.setAge(annee);
                    //patient.setDatecreation(dateAujourdhui);
                }
                catch (Exception exc){}
                if(saisirprof.trim().length() > 0){
                    // Check if it does not exist already :
                    List<String> listeProfession = new ArrayList<>();
                    listeProfession.add(saisirprof.toLowerCase());
                    listeProfession.add(saisirprof.toUpperCase());
                    listeProfession.add(saisirprof);
                    List<Profession> checkListe =
                            professionRepository.findByLibelleIn(listeProfession);
                    if((checkListe == null) || (checkListe.size() ==0)){
                        // Does not exist :
                        Profession profess = new Profession();
                        profess.setLibelle(saisirprof);
                        Profession pfs = professionRepository.save(profess);
                        pt.setProfession(pfs.getIdprof());
                    }
                    else pt.setProfession(checkListe.get(0).getIdprof());
                }
                else pt.setProfession(profession);
                patientRepository.save(pt);
            }
        }

        // Move on :
        Consultation consultation = new Consultation();
        Date tpdate;
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            consultation.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            consultation.setHeure(heure);
            consultation.setSymptome(diagnostic);
            consultation.setIdhop(hopital);
            consultation.setIdpat(idpatient);
            consultation.setIdmed(Integer.parseInt(medecinid.toString()));
            consultation.setEnvoyerpar(envoyepar);
            consultation.setIdser(service);
            // Update the CONSULTATION DATE :
            Date dateConsul = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(dateconsultation));
            consultation.setDateconsultation(dateConsul);

            // Try to ADD the new FIELDS :
            if(motif != null) consultation.setMotif(motif);
            if(diabete != null) consultation.setDiabete(diabete);
            if(hypertension != null) consultation.setHypertension(hypertension);
            if(pblmecardiaque != null) consultation.setPblcardiaque(pblmecardiaque);
            if(pblmepulmon != null) consultation.setPblpulmonaire(pblmepulmon);
            if(drepano != null) consultation.setDrepanocytose(drepano);
            if(autres != null) consultation.setAutres(autres);
            if(commentautre != null) consultation.setAutrescommentaire(commentautre.trim());
            if(modedevie != null) consultation.setModevie(modedevie);
            if(examenclinique != null) consultation.setExamenclinique(examenclinique);
            if(conclusion != null) consultation.setConclusion(conclusion);
            if(traitement != null) consultation.setTraitement(traitement);
            // Add new ONES : interrogatoire , examenphysique, conduiteatenir :
            if(interrogatoire != null) consultation.setInterrogatoire(interrogatoire);
            if(examenphysique != null) consultation.setExamenphysique(examenphysique);
            if(conduiteatenir != null) consultation.setConduiteatenir(conduiteatenir);
            // ------------------------------------
            if(tabagismeactif != null) consultation.setTabagismeactif(tabagismeactif);
            if(tabagismepassif != null) consultation.setTabagismepassif(tabagismepassif);
            if(ethylisme != null) consultation.setEthylisme(ethylisme);
            if(sedentaire != null) consultation.setSedentaire(sedentaire);
            if(autresmode != null) consultation.setAutresmode(autresmode);
            if(autresmodecomment != null) consultation.setAutresmodecomment(autresmodecomment);

            //--------------  28/11/2020
            consultation.setDiagnosticretenu(diagnosticretenu != null ? diagnosticretenu : "" );
            consultation.setHospitalisation(hospitalisation != null ? hospitalisation : 0 );
            consultation.setSoins(soins != null ? soins : "" );
            //
            consultation.setAvisconfrere(avisconfrere != null ? avisconfrere.trim() : "" );
            consultation.setOrdonnance(ordonnance != null ? ordonnance.trim() : "" );
            //********************  renseignementsclin
            consultation.setRenseignementsclin(renseignementsclin != null ? renseignementsclin.trim() : "" );
            // Save :
            Consultation consultation1 = consultationRepository.save(consultation);

            // Hit Hospitalisation if needed :
            if(hospitalisation != null){
                if(hospitalisation == 1){
                    Hospitalisation hn = new Hospitalisation();
                    hn.setChambre(chambre.trim());
                    hn.setLit(lit.trim());
                    Date dateEntree = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(datentree));
                    hn.setDatentree(dateEntree);
                    hn.setHeure(heurehospi.trim());
                    hn.setMotif(motifhospi.trim());
                    hn.setBilanbio(bilanbio.trim());
                    hn.setBilanradio(bilanradio.trim());
                    int jrs = 0;
                    try{
                        jrs = Integer.parseInt(nbrejour.trim());
                    }
                    catch (Exception exc){}
                    hn.setNbrejour(jrs);
                    hn.setIdcon(consultation1.getIdcon());
                    // Save it
                    hospitalisationRepository.save(hn);
                }
            }

            // Process DATA for CABINET DENTAIRE
            if(cardiopathie != null){

                if(cardiopathie.trim().length() > 0) {

                    // Check if this PATIENT has been already registred in CABINET DENTAIRE
                    int cdteID = 0;
                    Cabinetdentaire cdte = cabinetdentaireRepository.findByIdpat(idpatient);
                    if (cdte == null) {
                        cdte = new Cabinetdentaire();
                        cdte.setIdcon(consultation1.getIdcon());
                        cdte.setIdpat(consultation1.getIdpat());
                    }

                    cdte.setCardiopathie(cardiopathie.trim());
                    cdte.setTroubles(troubles.trim());
                    cdte.setHta(hta.trim());
                    cdte.setDiabetedent(diabetedent.trim());
                    cdte.setAsthme(asthme.trim());
                    cdte.setInfectionsorl(infectionsorl.trim());
                    cdte.setVih(vih.trim());
                    cdte.setAntibiotique(antibiotique.trim());
                    cdte.setAnesthesie(anesthesie.trim());
                    cdte.setQuinine(quinine.trim());
                    cdte.setLatex(latex.trim());
                    cdte.setAutresdentaire(autresdentaire.trim());
                    cdte.setDentsmanquantes(dentsmanquantes.trim());
                    cdte.setAppareillage(appareillage);
                    cdte.setHygienebuccale(hygienebuccale);
                    cdte.setArticuledentaire(articuledentaire);
                    cdte.setLangue(langue);
                    cdte.setSuceur(suceur);
                    cdte.setOrthodontique(orthodontique);
                    // Save :
                    cdteID = cabinetdentaireRepository.save(cdte).getIdcde();

                    // Now process PROTHESE Data, by deleting the OLDs ones :
                    prothesesRepository.deleteAllByIdpat(consultation1.getIdpat());
                    for (int k = 0; k < enplace.length; k++) {
                        if (enplace[k].trim().length() > 0 || localisation[k].trim().length() > 0) {
                            Protheses ps = new Protheses();
                            ps.setEnplace(enplace[k].trim());
                            ps.setLocalisation(localisation[k].trim());
                            ps.setTypeproth(typeproth[k].trim());
                            Date dateProth = new SimpleDateFormat("yyyy-MM-dd").
                                    parse(ClassFonction.retourDate(dateprothese[k]));
                            ps.setDateprothese(dateProth);
                            ps.setIdpat(consultation1.getIdpat());
                            ps.setIdcde(cdteID);
                            //
                            prothesesRepository.save(ps);
                        }
                        // Stop to keep TWO records
                        if (k == 2) break;
                    }

                    // Process NATURE INTERVENTIONS too :
                    for (int j = 0; j < dateintervention.length; j++) {
                        if (natureintervention[j].trim().length() > 0) {
                            Interventions intervention = new Interventions();
                            int dnts = 0;
                            try{
                                dnts = Integer.parseInt(dents[j]);
                            }
                            catch (Exception exc){}
                            intervention.setDent(dnts);
                            intervention.setNature(natureintervention[j].trim());
                            Date dateInterven = new SimpleDateFormat("yyyy-MM-dd").
                                    parse(ClassFonction.retourDate(dateintervention[j]));
                            intervention.setDates(dateInterven);
                            intervention.setIdcon(consultation1.getIdcon());
                            intervention.setIdpat(consultation1.getIdpat());
                            // Save it :
                            interventionsRepository.save(intervention);
                        }
                    }
                }
            }

            // If CONSTANTE is there, save it :
            if(idcons != null){
                //
                if(idcons > 0) {
                    Constante constante = constanteRepository.findByIdcons(idcons);
                    // Update
                    constante.setTaille(taille);
                    constante.setTemperature(temperature);
                    constante.setTension(0.0);
                    constante.setTensionarterielle(tensionarterielle);
                    constante.setPoids(poids);
                    constante.setPouls(pouls);
                    // Set the IDCONS
                    constante.setIdcon(consultation1.getIdcon());
                    // save :
                    constanteRepository.save(constante);
                }
            }
            else{
                Constante constante = new Constante();
                // Update
                constante.setTaille(taille);
                constante.setTemperature(temperature);
                constante.setTension(0.0);
                constante.setTensionarterielle(tensionarterielle);
                constante.setPoids(poids);
                constante.setPouls(pouls);
                // Set the IDCONS
                constante.setIdcon(consultation1.getIdcon());
                constante.setIdser(consultation1.getIdser());
                constante.setIddocteur(consultation1.getIdmed());
                constante.setIdpat(consultation1.getIdpat());
                constante.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                constante.setHeure(heure);
                constante.setIdmed(0);
                // save :
                constanteRepository.save(constante);
            }

            // Check if we have FILES attached :
            int j = 0;
            if(fichiers != null) {
                for (MultipartFile files : fichiers) {
                    if (!files.isEmpty()) {
                        // Save the file :
                        byte[] bytes = files.getBytes();
                        String nomfichier = files.getOriginalFilename();
                        String typefichier = files.getContentType();
                        long tailleFichier = files.getSize();
                        //
                        Fichiers monFichier = new Fichiers();
                        monFichier.setFichier(bytes);
                        monFichier.setNomfichier(nomfichier);
                        monFichier.setTypefichier(typefichier);
                        monFichier.setTaillefichier(tailleFichier);
                        monFichier.setIdcon(consultation1.getIdcon());
                        //
                        monFichier.setIdntf(idnaturefichier[j]);

                        // Now SAVE :
                        fichiersRepository.save(monFichier);
                    }
                    //
                    j++;
                }
            }


            // Get the EXAMENS
            List<Integer> listeIdExam = new ArrayList<>();
            if(examensnondefinis.trim().length() > 0){
                // Split ';'
                String[] examNonDefinis = examensnondefinis.split(";");
                if(examNonDefinis.length > 0){
                    // Browse :
                    for(String monExam : examNonDefinis){
                        // Create it :
                        if(monExam.trim().length() <= 50){
                            Examen en = new Examen();
                            en.setLibelle(monExam.trim());
                            // Save it :
                            listeIdExam.add(examenRepository.save(en).getIdexam());
                        }
                    }
                }
            }

            // Hit Cmdexamens
            if(listeIdExam.size() > 0){
                for (int idexam : listeIdExam) {
                    Cmdexamens cmdexamens = new Cmdexamens();
                    cmdexamens.setIdcon(consultation1.getIdcon());
                    cmdexamens.setIdexam(idexam);
                    cmdexamens.setIdpat(idpatient);
                    cmdexamens.setMontant(0.0);
                    cmdexamens.setStatut(0);
                    try {
                        String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        cmdexamens.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                        // Save :
                        cmdexamensRepository.save(cmdexamens);
                    } catch (Exception exc) {
                        System.out.println("Erreur survenue : "+exc.toString());
                    }
                }
            }


            // Here try to save EXAMENS :
            if(examens != null) {
                for (int idexam : examens) {
                    if (idexam > 1) {
                        Cmdexamens cmdexamens = new Cmdexamens();
                        cmdexamens.setIdcon(consultation1.getIdcon());
                        cmdexamens.setIdexam(idexam);
                        cmdexamens.setIdpat(idpatient);
                        cmdexamens.setMontant(0.0);
                        cmdexamens.setStatut(0);
                        try {
                            String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                            cmdexamens.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                            // Save :
                            cmdexamensRepository.save(cmdexamens);
                        } catch (Exception exc) {
                            System.out.println("Erreur survenue : "+exc.toString());
                        }
                    }
                }
            }

            // Envoyer le MAIL :
            /*Medecin med = medecinRepository.findByIdmed(Integer.parseInt(medecinid.toString()));
            Parametrage parametrage = parametrageRepository.findByIdprm(1);
            if(parametrage != null){
                if(parametrage.getEnvoimailsuperadmin()==1) {
                    String[] listeEmail = {"ngbandamakonan@gmail.com"};
                    tachesService.envoiMail("Enregistrement d'une consultation",
                            "Une nouvelle consultation a été enregistrée à " + heure +
                                    " par " + med.getNom() + " " + med.getPrenom(), listeEmail);
                }
            }
            */

            // Set the date :
            if(choixrdv != null) {
                //
                Date dteRdv = new SimpleDateFormat("yyyy-MM-dd").
                        parse(ClassFonction.retourDate(daterdv));
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setDates(dteRdv);
                rendezvous.setHeure(heurerdv);
                rendezvous.setIdcon(consultation1.getIdcon());
                // Now, save :
                rendezvousRepository.save(rendezvous);

                // Now hit anf_factorisationrdv TABLE :
                Factorisationrdv factorisationrdv = new Factorisationrdv();
                factorisationrdv.setDates(dteRdv);
                factorisationrdv.setHeure(heurerdv);
                factorisationrdv.setHopital(hopital);
                factorisationrdv.setIdpat(idpatient);
                factorisationrdv.setIdmed(
                        Integer.parseInt(medecinid.toString()));
                factorisationrdv.setIdser(service);
                factorisationrdv.setProvenance(1);
                // Save :
                factorisationrdvRepository.save(factorisationrdv);
            }
        }
        catch (Exception exc){
            System.out.println("Exception : "+exc.toString()+"    --->   "+
                    exc.getLocalizedMessage()+"    --->   "+exc.getMessage());
        }

        // Browse :
        //for(String dates : dateobservation){
        //    System.out.println("Date : "+dates);
        //}
        return "redirect:/accueilpatient";
    }


    //afficherhistpatient
    @GetMapping(value = "/afficherhistpatient/{id}")
    public String afficherhistpatient(@PathVariable int id,
        Model model, HttpSession session)
    {

        Patient pt = patientRepository.findByIdpat(id);

        // Display Patient name, date, hour of each consultation
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeconsultation = em.createQuery(
            "select concat(a.nom, ' ',a.prenom) as nomcomplet," +
                "date(b.dates) as dte,b.heure,b.idcon,c.libelle,a.idpat, count(d.idcmx) as tot," +
                "count(e.idhos) as tothos , count(f.idpro) as totpro " +
                "from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                "inner join anf_services c on b.idser=c.idser left join anf_cmdexamens " +
                "d on d.idcon=b.idcon " +
                "left join anf_hospitalisation e on b.idcon=e.idcon " +
                "left join anf_prolongation f on b.idcon=f.idcon " +
                "where a.idpat =  " + id +
                " group by concat(a.nom, ' ',a.prenom),date(b.dates),b.heure,b.idcon ," +
                "c.libelle,a.idpat").getResultList();
        // Pass it to the MODEl :
        model.addAttribute("listeconsultation", listeconsultation);
        model.addAttribute("nompatient",(pt.getNom()+" "+pt.getPrenom()));

        // Close :
        em.getTransaction().commit();
        em.close();

        return "historiqueconsulpatient";
    }

    // autorisation
    @GetMapping(value = "/autorisation")
    public String autorisation(Model model, HttpSession session, Principal principal){

        // Set the list :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        List<Integer> listeMedecinId = new ArrayList<Integer>();
        listeMedecinId.add(medecin.getIdmed());
        List<Medecin> listeMedecin =
                medecinRepository.findAllByProfilAndIdmedNotIn("medecin", listeMedecinId);
        // Recuperer la liste des medecins :
        //List<Medecin> listeMedecin = medecinRepository.findAllByProfil("medecin");
        model.addAttribute("listeMedecin", listeMedecin);

        // Get the ones allowed :
        Object medecinid = session.getAttribute("medecinid");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeAutorisation = em.createQuery(
        "select distinct b.idaut, a.nom, a.prenom, date(b.dates)," +
            "concat(c.nom,' ',c.prenom) as patient  from anf_medecin a " +
            "inner join anf_autorisation b on a.idmed=b.idmeddestin " +
            "inner join anf_patient c on c.idpat=b.idpat where b.idmedorigin=" +
            medecinid.toString()).getResultList();
        model.addAttribute("listeAutorisation", listeAutorisation);

        // Get the PATIENTS treated by this DOCTOR :
        List<Object[]> listePatient = em.createQuery(
            "select b.idpat,concat(b.nom,' ',b.prenom) as patient from anf_consultation a" +
            " inner join anf_patient b on a.idpat=b.idpat where a.idmed=" +
            medecinid.toString()).getResultList();
        model.addAttribute("listePatient", listePatient);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "autorisation";
    }


    // habilitation
    @GetMapping(value = "/habilitation")
    public String habilitation(Model model, HttpSession session, Principal principal){

        // Set the list :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // Get the ones allowed :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Get all DOCTORS that work in the hospital the ADMINISTRATOR works in too :
        List<Object[]> listeMedecin = em.createQuery(
        "select DISTINCT a.idmed,concat(a.nom,' ',a.prenom) as medecin from " +
            "anf_medecin a inner join anf_assignation b on a.idmed=b.idmed " +
            "inner join anf_affectation c on b.idhop=c.idhop " +
            "where c.idmed="+medecin.getIdmed()+" and a.profil='medecin'").getResultList();
        model.addAttribute("listeMedecin", listeMedecin);

        // Get the list of DOCTORS who have already received the rights :
        List<Object[]> listeDonnees = em.createQuery(
            "select concat(a.nom,' ',a.prenom) as medecin,b.idhab from " +
            "anf_medecin a inner join anf_habilitation b on a.idmed=b.idmed " +
            "where b.idadmin=" + medecin.getIdmed()).getResultList();
        model.addAttribute("listeDonnees", listeDonnees);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "habilitation";
    }



    // enreghabilitation
    @PostMapping(value = "/enreghabilitation")
    public String enreghabilitation(@RequestParam("idmedecin") int idmedecin,
                                    Principal principal){

        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        if(medecin != null){
            //--------------->
            Habilitation habilitation =
                    habilitationRepository.findByIdadminAndIdmed(medecin.getIdmed(), idmedecin);
            if(habilitation == null){
                habilitation = new Habilitation();
                habilitation.setIdadmin(medecin.getIdmed());
                habilitation.setIdmed(idmedecin);
                // Save :
                habilitationRepository.save(habilitation);
                return "redirect:/habilitation";
            }
            else{
                //System.out.println("Etape 1");
                return "redirect:/habilitation";
            }
        }
        else {
            //System.out.println("Etape 0");
            return "redirect:/habilitation";
        }
    }


    // supprimeraut
    @GetMapping(value = "/supprimerhab/{idhab}")
    public String supprimerhab(@PathVariable int idhab, Principal principal)
    {
        try {
            // Get :
            Habilitation habilitation = habilitationRepository.findByIdhab(idhab);
            if(habilitation != null){
                habilitationRepository.deleteByIdhab(idhab);
            }
        }
        catch (Exception exc){}
        return "redirect:/habilitation";
    }


    // Get the list of PATIENTS
    @GetMapping(value = "/patientshabilite")
    public String patientshabilite(Principal principal, Model model)
    {
        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // Get the ones allowed :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Get all DOCTORS that work in the hospital the ADMINISTRATOR works in too :
        /*
        List<Object[]> listePatientshab = em.createQuery(
        "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient,a.telephone,a.email,f.libelle  " +
            "from anf_patient a inner join anf_consultation b on a.idpat=b.idpat inner join  " +
            "anf_hopital f on b.idhop=f.idhop where b.idhop in ( " +
            "select distinct d.idhop from anf_medecin c inner join anf_affectation d on c.idmed= " +
            "d.idmed inner join anf_habilitation e on e.idadmin=c.idmed where e.idmed = "+medecin.getIdmed()+") and " +
            "b.idpat not in (select distinct g.idpat from anf_consultation g where g.idmed ="+medecin.getIdmed()+")").getResultList();*/
        List<Object[]> listePatientshab = em.createQuery(
                "select distinct a.idpat,concat(a.nom,' ',a.prenom) as patient,a.telephone,a.email,a.sexe  " +
                        "from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                        "where b.idmed <>"+medecin.getIdmed()).getResultList();
        model.addAttribute("listePatientshab", listePatientshab);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "patientshabilite";
    }




    // Get the list of PATIENTS
    @GetMapping(value = "/listerdv")
    public String listerdv(Principal principal, Model model)
    {
        // Get :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // Get the ones allowed :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Get all DOCTORS that work in the hospital the ADMINISTRATOR works in too :
        List<Object[]> listerdv = em.createQuery(
            "select distinct concat(a.nom,' ',a.prenom) as medecin, a.email," +
            "concat(c.nom,' ',c.prenom) as patient,c.telephone, date(d.dates),e.libelle,f.libelle " +
            "from anf_medecin a inner join anf_consultation b on a.idmed=b.idmed " +
            "inner join anf_patient c on b.idpat=c.idpat " +
            "inner join anf_rdv d on b.idcon=d.idcon " +
            "inner join anf_heure e on d.heure=e.idheu " +
            "inner join anf_hopital f on b.idhop=f.idhop "+
            "where timestampdiff(DAY,d.dates,date(now())) <= 0 and a.idmed="+
            medecin.getIdmed()).getResultList();
        model.addAttribute("listerdv", listerdv);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "listerdv";
    }


    // lienenregavisprolong
    @PostMapping(value = "/enregavisprolong/{idcon}")
    public String enregavisprolong(
        @RequestParam("motifprolong") String motifprolong,
        @RequestParam("suspicion") String suspicion,
        @RequestParam("nbrejour") String nbrejour,
        @RequestParam("diagnosticevoq") String diagnosticevoq,
        @RequestParam("biologiques") String biologiques,
        @RequestParam("radiologiques") String radiologiques,
        @PathVariable int idcon,
        Model model, HttpSession session)
    {
        Consultation cn = consultationRepository.findByIdcon(idcon);
        Prolongation pn = prolongationRepository.findByIdcon(idcon);
        if(pn == null){
            pn = new Prolongation();
            pn.setIdcon(idcon);
        }
        pn.setMotif(motifprolong.trim());
        // Set NEW :
        pn.setDiagnosticevoq(diagnosticevoq.trim());
        pn.setRadiologiques(radiologiques.trim());
        pn.setBiologiques(biologiques.trim());
        //
        pn.setSuspicion(suspicion.trim());
        int jour = 0;
        try{
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            pn.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            jour = Integer.parseInt(nbrejour.trim());
        }
        catch (Exception exc){
            jour = 0;
        }
        // ----
        pn.setJour(jour);
        // Save :
        prolongationRepository.save(pn);
        return "redirect:/afficherhistpatient/"+String.valueOf(cn.getIdpat());
    }


    // Generate AVIS de prolongation :
    @GetMapping(value = "/ajouteravisprohos/{idcon}")
    public String ajouteravisprohos(@PathVariable int idcon,
        Model model, HttpSession session)
    {
        Hospitalisation hn = hospitalisationRepository.findByIdcon(idcon);
        Prolongation pn = prolongationRepository.findByIdcon(idcon);
        Consultation cn = consultationRepository.findByIdcon(idcon);

        model.addAttribute("motif",
            (pn != null ? pn.getMotif() : hn.getMotif()));
        model.addAttribute("diagnostic",
                (pn != null ? pn.getSuspicion() : cn.getDiagnosticretenu()));
        model.addAttribute("jour",
                (pn != null ? pn.getJour() : hn.getNbrejour()));
        model.addAttribute("diagnosticevoq",
                (pn != null ? pn.getDiagnosticevoq() : cn.getDiagnosticretenu() ));
        model.addAttribute("radiologiques",
                (pn != null ? pn.getRadiologiques() : hn.getBilanradio() ));
        model.addAttribute("biologiques",
                (pn != null ? pn.getBiologiques() : hn.getBilanbio() ));
        // Add LINK to let the user clicks back :
        model.addAttribute("lienretour",
                "/gestion/afficherhistpatient/".
                        concat(String.valueOf(cn.getIdpat())));
        model.addAttribute("lienenregavisprolong",
                "/gestion/enregavisprolong/".
                        concat(String.valueOf(idcon)));
        //
        /*List<Prolongation> ltes =
            prolongationRepository.findAllByIdconOrderByIdproAsc(idcon);
        if(ltes != null){
            if(ltes.size() > 0){

                // Get the ones allowed :
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                // Get all DOCTORS that work in the hospital the ADMINISTRATOR works in too :
                List<Object[]> listes = em.createQuery(
                    "select a.motif, date(a.datentree),a.chambre,b.motif," +
                    "b.suspicion,date(b.dates),b.jour,b.idpro from anf_hospitalisation a " +
                    "inner join anf_prolongation b on a.idcon=b.idcon "+
                    "where a.idcon = "+idcon).getResultList();
                model.addAttribute("listes", listes);

                // Close :
                em.getTransaction().commit();
                em.close();
                return "historiqueprolongation";
            }
        }
        */

        return "prolongationhospi";
    }


    // Modifier consultation :
    @GetMapping(value = "/modifierconsultation/{idcon}")
    public String modifierconsultation(@PathVariable int idcon,
                                      Model model, HttpSession session)
    {
        // Get PATIENT, DOCTOR has work with :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listepatient = em.createQuery(
                "select distinct b.idpat, concat(b.nom, ' ',b.prenom) from " +
                        "anf_consultation a inner join anf_patient b " +
                        "on a.idpat=b.idpat where a.idcon = " + idcon).getResultList();
        // Pass it to the MODEl :
        model.addAttribute("listepatient", listepatient);

        // Liste des hopitaux, first, get MEDECIN id :
        Consultation getConsultation = consultationRepository.findByIdcon(idcon);
        List<Assignation> listeDesHopitaux = assignationRepository.findAllByIdmed(
                getConsultation.getIdmed());
        // GET HOSPITALS IDs:
        List<Long> listeHopitalID = new ArrayList<Long>();
        for(Assignation ass : listeDesHopitaux){
            listeHopitalID.add(Long.valueOf(ass.getIdhop()));
        }

        // Get list of NATURE DES FICHIERS
        List<Naturefichier> listenaturefichiers = naturefichierRepository.findAll();
        model.addAttribute("listenaturefichiers", listenaturefichiers);

        // ---------  Obtenir les fichiers si possible :
        //List<Fichiers> listeDesFichiers = fichiersRepository.findAllByIdcon(idcon);
        //if(listeDesFichiers.size() > 0){
        //    model.addAttribute("listedesfichiers", listeDesFichiers);
        //}
        List<Object[]> lsteFichiers = em.createQuery(
            "select distinct a.idfic,a.nomfichier,b.nom from anf_fichiers a inner join " +
            "anf_naturefichier b on a.idntf=b.idntf where a.idcon = " + idcon).getResultList();
        if(lsteFichiers.size() > 0){
            model.addAttribute("listedesfichiers", lsteFichiers);
        }

        // Now find HOSPITALs :   -->  List<Hopital> listehopital = hopitalRepository.findAll();
        List<Hopital> listehopital = hopitalRepository.findAllByIdhopIn(listeHopitalID);
        model.addAttribute("listehopital", listehopital);
        //-----------------------------------------------------------------
        // Liste des services : mais seulement ceux auquels il est assigné :
        List<Affectationserv> listeDesAffectationSer = affectationservRepository.
                findAllByIdmed(getConsultation.getIdmed());
        // GET HOSPITALS IDs:
        List<Integer> listeServiceID = new ArrayList<Integer>();
        for(Affectationserv asv : listeDesAffectationSer){
            listeServiceID.add(asv.getIdser());
        }
        // Liste des services :
        List<Services> listeservices = servicesRepository.findAllByIdserIn(listeServiceID);
        model.addAttribute("listeservices", listeservices);

        // Liste des professions :
        List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeprofession", listeprofession);
        // Liste des services :
        //List<Services> listeservices = servicesRepository.findAll();
        //model.addAttribute("listeservices", listeservices);

        // Donnees CONSULTATION :
        List<Object[]> consultation = em.createQuery(
            "select distinct b.nom,b.prenom,b.sexe,b.cni,b.telephone," +
            "b.email,a.symptome,b.age,b.adresse,a.envoyerpar,b.profession," +
            "a.idhop,a.idser,date(a.dateconsultation)," +
            "a.motif,a.diabete,a.hypertension,a.pblcardiaque,a.pblpulmonaire," +
            "a.drepanocytose,a.autres,a.autrescommentaire,a.modevie,a.examenclinique," +
            "a.conclusion,a.traitement," +
            "a.tabagismeactif,a.tabagismepassif,a.ethylisme,a.sedentaire,"+
            "a.autresmode,a.autresmodecomment,a.diagnosticretenu,a.hospitalisation," +
            "a.soins,a.avisconfrere,a.ordonnance,a.examenphysique,a.interrogatoire," +
            "a.renseignementsclin,b.datenaissance from " +
            "anf_consultation a inner join anf_patient b " +
            "on a.idpat=b.idpat where a.idcon = " + idcon).getResultList();
        String[] tabCons = new String[40];
        for(Object[] obj : consultation){
            tabCons[0] = String.valueOf(obj[0]);
            tabCons[1] = String.valueOf(obj[1]);
            tabCons[2] = String.valueOf(obj[2]);
            tabCons[3] = String.valueOf(obj[3]);
            tabCons[4] = String.valueOf(obj[4]);
            tabCons[5] = String.valueOf(obj[5]);
            tabCons[6] = String.valueOf(obj[6]);
            tabCons[7] = String.valueOf(obj[7]);
            tabCons[8] = String.valueOf(obj[8]);
            tabCons[9] = String.valueOf(obj[9]);
            tabCons[10] = String.valueOf(obj[10]);
            tabCons[11] = String.valueOf(obj[11]);
            tabCons[12] = String.valueOf(obj[12]);
            tabCons[13] = FonctionOrdinaire.formatterDate(String.valueOf(obj[13]));
            // Add the new DATA ---->   motif,
            tabCons[14] = String.valueOf(obj[14]).trim(); // Motif
            tabCons[15] = String.valueOf(obj[15]); // Diabete
            tabCons[16] = String.valueOf(obj[16]); // Hypertension
            tabCons[17] = String.valueOf(obj[17]); // Probleme cardiaque
            tabCons[18] = String.valueOf(obj[18]); // Probleme pulmonaire
            tabCons[19] = String.valueOf(obj[19]); // Drepanocytose
            tabCons[20] = String.valueOf(obj[20]); // Autres
            tabCons[21] = String.valueOf(obj[21]).trim(); // Autres Commentaires
            tabCons[22] = String.valueOf(obj[22]); // Mode de vie
            tabCons[23] = String.valueOf(obj[23]).trim(); // Examen clinique
            tabCons[24] = String.valueOf(obj[24]).trim(); // Conclusion
            tabCons[25] = String.valueOf(obj[25]).trim(); // Traitement

            tabCons[26] = String.valueOf(obj[26]); // tabagismeactif
            tabCons[27] = String.valueOf(obj[27]); // tabagismepassif
            tabCons[28] = String.valueOf(obj[28]); // ethylisme
            tabCons[29] = String.valueOf(obj[29]); // sedentaire
            tabCons[30] = String.valueOf(obj[30]); // autresmode
            tabCons[31] = String.valueOf(obj[31]).trim(); // autresmodecomment
            // Added on 30/11/2020
            tabCons[32] = String.valueOf(obj[32]).trim(); // diagnosticretenu
            tabCons[33] = String.valueOf(obj[33]).trim(); // hospitalisation
            tabCons[34] = String.valueOf(obj[34]).trim(); // soins
            tabCons[35] = String.valueOf(obj[35]).trim(); // avisconfrere
            tabCons[36] = String.valueOf(obj[36]).trim(); // ordonnance
            tabCons[37] = String.valueOf(obj[37]).trim(); // Examen physique
            tabCons[38] = String.valueOf(obj[38]).trim(); // Interrogatoire
            tabCons[39] = String.valueOf(obj[39]).trim(); // Renseignements cliniques
        }
        model.addAttribute("consultation", tabCons);

        // 40 - datenaissance
        Patient patient = patientRepository.findByIdpat(getConsultation.getIdpat());
        // For date :
        String dtes = new SimpleDateFormat("MM/dd/yyyy").format(patient.getDatenaissance());
        model.addAttribute("datenaissance", dtes);

        // Now, look for CONSTANTE :
        Constante ce = constanteRepository.findByIdcon(idcon);
        if(ce != null) model.addAttribute("constante", ce);

        // Get all Observations ;
        List<Object[]> lesObservations = em.createQuery(
                "select distinct date(a.dates),a.commentaire from " +
                        "anf_observation a where a.idcon = " + idcon).getResultList();
        if(lesObservations != null){
            if(lesObservations.size() > 0){
                // Work on the first :
                String date = new SimpleDateFormat("yyyy-MM-dd").format(
                        lesObservations.get(0)[0]);
                model.addAttribute("observdate",
                        FonctionOrdinaire.formatterDate(date));
                model.addAttribute("observcom", (String)lesObservations.get(0)[1]);
                lesObservations.remove(0);
                // Now Process :
                List<Donneesobservations> listeObservation = new ArrayList<Donneesobservations>();
                for(Object[] donnees : lesObservations){
                    Donneesobservations dos = new Donneesobservations();
                    String dte = new SimpleDateFormat("yyyy-MM-dd").format(
                            donnees[0]);
                    dos.setDates(FonctionOrdinaire.formatterDate(dte));
                    dos.setConsultation(String.valueOf(donnees[1]));
                    listeObservation.add(dos);
                }
                model.addAttribute("lesObservations", listeObservation);
                //model.addAttribute("lesObservations", lesObservations);
            }
        }

        String datecourante = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        //String currentheure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        model.addAttribute("currentdate", datecourante);

        // Get the list of TIMES :
        List<Heure> listeHeure = heureRepository.findAll();
        model.addAttribute("listeHeure", listeHeure);

        //
        model.addAttribute("consultationid",
                "/gestion/modifconsultation/".concat(String.valueOf(idcon)));

        // Now get a RDV if there is one :
        if(getConsultation != null){
            //
            Rendezvous rdz = rendezvousRepository.findByIdcon(idcon);
            if(rdz != null) {
                String dateRdv = new SimpleDateFormat("MM/dd/yyyy").format(rdz.getDates());
                model.addAttribute("dateRdv", dateRdv);
                model.addAttribute("heureRdv", rdz.getHeure());
                //System.out.println("HeureRDV : "+rdz.getHeure());
            }
        }

        // Add LINK to let the user clicks back :
        model.addAttribute("lienretour",
                "/gestion/afficherhistpatient/".
                        concat(String.valueOf(getConsultation.getIdpat())));

        // Add EXAMENS lists :
        List<Examen> liseExamens = examenRepository.findAll();
        model.addAttribute("liseExamens", liseExamens);
        // get the EXAM that have already been set :
        List<Object[]> listeExamsCmd = em.createQuery(
            "select distinct a.idexam,a.libelle," +
            "case " +
            " when b.nomfichier is null then 0" +
            " else 1 " +
            "end as present,b.idcmx from anf_examen a inner join " +
            "anf_cmdexamens b on a.idexam=b.idexam where b.idcon = " + idcon).
                getResultList();
        if(listeExamsCmd != null){
            if(listeExamsCmd.size() > 0){
                model.addAttribute("listeExamsCmd", listeExamsCmd);
            }
        }

        // Close :
        em.getTransaction().commit();
        em.close();

        // Get hospitalisation if needed :
        Hospitalisation hn = hospitalisationRepository.findByIdcon(idcon);
        if(hn!=null) {
            // For date :
            String datentree = new SimpleDateFormat("MM/dd/yyyy").format(
                    hn.getDatentree());
            model.addAttribute("datentree", datentree);
            model.addAttribute("hospitalisation", hn);
        }
        model.addAttribute("monurl", monUrl);

        // Get CABINET DENTAIRE :
        Cabinetdentaire cdte = cabinetdentaireRepository.findByIdpat(getConsultation.getIdpat());
        if(cdte != null) model.addAttribute("cabinetdentaire", cdte);

        // Get INTERVENTION :
        List<Interventions> listeintervention = interventionsRepository.findAllByIdcon(idcon);
        // Process :
        List<Traiterintervention> listedesinterventions = new ArrayList<>();
        if(listeintervention != null) {
            if(listeintervention.size() > 0) {
                for (Interventions is : listeintervention) {
                    String des = new SimpleDateFormat("MM/dd/yyyy").format(is.getDates());
                    Traiterintervention tn = new Traiterintervention(des, String.valueOf(is.getDent()),
                            is.getNature());
                    listedesinterventions.add(tn);
                }
                //
                model.addAttribute("listeintervention", listedesinterventions);
            }
        }

        //
        List<Protheses> listeProtheses = prothesesRepository.findAllByIdpat(getConsultation.getIdpat());
        List<Traiterprothese> listedesprotheses = new ArrayList<>();
        if(listeProtheses != null){
            if(listeProtheses.size() > 0){
                for(Protheses ps : listeProtheses){
                    String des = new SimpleDateFormat("MM/dd/yyyy").format(ps.getDateprothese());
                    Traiterprothese te = new Traiterprothese(ps.getEnplace(), ps.getLocalisation(),
                            ps.getTypeproth(), des);
                    listedesprotheses.add(te);
                }
                //
                model.addAttribute("listeprotheses", listedesprotheses);
            }
        }

        return "createconsultation";
    }


    // enregautorisation
    @PostMapping(value = "/enregautorisation")
    public String enregautorisation(@RequestParam("idmedecin") int idmedecin,
                                    @RequestParam("idpatient") int idpatient,
                                    HttpSession session){
        // Check first if the relation is not there already :
        Object medecinid = session.getAttribute("medecinid");
        if(medecinid != null){
            Autorisation autorisation =
                    autorisationRepository.
                            findByIdmedoriginAndIdmeddestinAndIdpat(Integer.valueOf(medecinid.toString()),
                                    idmedecin, idpatient);
            if(autorisation == null){
                if( idmedecin != Integer.valueOf(medecinid.toString())){
                    // Now process :
                    Autorisation nouvelleAut = new Autorisation();
                    nouvelleAut.setIdmedorigin(Integer.valueOf(medecinid.toString()));
                    nouvelleAut.setIdmeddestin(idmedecin);
                    nouvelleAut.setIdpat(idpatient);
                    //
                    Date tpdate;
                    try {
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        nouvelleAut.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    }
                    catch (Exception exc){}
                    //
                    autorisationRepository.save(nouvelleAut);
                    return "redirect:/autorisation";
                }
                else{
                    //System.out.println("Etape 2");
                    return "redirect:/autorisation";
                }
            }
            else{
                //System.out.println("Etape 1");
                return "redirect:/autorisation";
            }
        }
        else {
            //System.out.println("Etape 0");
            return "redirect:/autorisation";
        }
    }


    // habiliteradmin
    @PostMapping(value = "/habiliteradmin")
    public String habiliteradmin(@RequestParam("idmedecin") int idmedecin,
        @RequestParam("hopital") int hopital){

        // Check first if that request is not set already :
        Affectation affectation = affectationRepository.findByIdmed(idmedecin);
        if(affectation == null){
            // So create it :
            Affectation newAffectation = new Affectation();
            newAffectation.setIdhop(hopital);
            newAffectation.setIdmed(idmedecin);
            affectationRepository.save(newAffectation);
        }

        return "redirect:/connexpatient";
    }


    // habiliteradmin
    @GetMapping(value = "/habilitations")
    public String habilitations(){
        return "redirect:/connexpatient";
    }

    // Hopitaux
    @GetMapping(value = "/gesthopitaux")
    public String gesthopitaux(Model model){
        List<Hopital> listeHopital = hopitalRepository.findAll();
        model.addAttribute("listeHopital", listeHopital);
        return "hopitaux";
    }

    // habiliteradmin
    @GetMapping(value = "/comptes")
    public String comptes(Model model){
        List<Medecin> listeMedecin = medecinRepository.findAll();
        model.addAttribute("listeMedecin", listeMedecin);
        // Get PROFIL list
        List<Userprofile> lesprofils = userprofileRepository.findAllByOrderByLibelleAsc();//.findAllOrderByLibelleByAsc();
        model.addAttribute("lesprofils", lesprofils);
        return "comptes";
    }

    // Modifier COMPTE :
    @GetMapping(value = "/modifmedecin/{idmed}")
    public String modifmedecin(@PathVariable int idmed, Model model){

        // Get first the ACCOUNT :
        Medecin medecin = medecinRepository.findByIdmed(idmed);
        model.addAttribute("medecin", medecin);
        model.addAttribute("medecinid",
                "/gestion/modifcomptes/"+idmed);

        List<Medecin> listeMedecin = medecinRepository.findAll();
        model.addAttribute("listeMedecin", listeMedecin);
        // Get PROFIL list
        List<Userprofile> lesprofils = userprofileRepository.findAllByOrderByLibelleAsc();//.findAllOrderByLibelleByAsc();
        model.addAttribute("lesprofils", lesprofils);
        return "comptes";
    }


    // Modifier un compte :
    @PostMapping(value = "/modifcomptes/{idmed}")
    public String modifcomptes(@PathVariable int idmed,
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom, @RequestParam("cni") String cni,
        @RequestParam("telephone") String telephone, @RequestParam("email") String email,
        @RequestParam("profil") String profil){

        //
        Medecin medecin = medecinRepository.findByIdmed(idmed);
        if(medecin != null){
            medecin.setNom(nom);
            medecin.setPrenom(prenom);
            medecin.setCni(cni);
            medecin.setTelephone(telephone);
            medecin.setEmail(email.toLowerCase());
            medecin.setProfil(profil);
            if(email.contains("@")) {
                // Set the IDENTIFIANT :
                String[] resultat = email.split("@");
                medecin.setIdentifiant(resultat[0]);
                //String heure = new SimpleDateFormat("HH:mm").format(new Date());
                //medecin.setMotpasse(heure.replace(":",""));
                //
                medecinRepository.save(medecin);
                // Send a mail to the user :
                /*mailCreation("Création de votre compte",
                        resultat[0],
                        heure.replace(":",""),
                        email);*/
            }
        }
        return "redirect:/comptes";
    }



    // quantite
    @GetMapping(value = "/gestquantite")
    public String gestquantite(Model model){
        List<Quantite> liste = quantiteRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("liste", liste);
        return "quantite";
    }

    // posologie
    @GetMapping(value = "/gestposologie")
    public String gestposologie(Model model){
        List<Posologie> liste = posologieRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("liste", liste);
        return "posologie";
    }

    // dosage
    @GetMapping(value = "/gestdosage")
    public String gestdosage(Model model){
        List<Dosage> liste = dosageRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("liste", liste);
        return "dosage";
    }

    // type
    @GetMapping(value = "/gesttypes")
    public String gesttypes(Model model){
        List<Typesmedic> liste = typesmedicRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("liste", liste);
        return "typesmedic";
    }

    // Pharmacies
    @GetMapping(value = "/reachpharm")
    public String reachpharm(Model model){
        List<Pharmacie> liste = pharmacieRepository.findAllByIdpharmGreaterThan(1);
        model.addAttribute("liste", liste);
        return "reachpharm";
    }

    // Creer un compte :
    @PostMapping(value = "/creercompte")
    public String creercompte(@RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom, @RequestParam("cni") String cni,
        @RequestParam("telephone") String telephone, @RequestParam("email") String email,
        @RequestParam("profil") String profil){

        // Check first if the account does not exist :
        Medecin checkMedecin = medecinRepository.findByEmail(email.toLowerCase());
        if(checkMedecin == null){
            Medecin medecin = new Medecin();
            medecin.setNom(nom);
            medecin.setPrenom(prenom);
            medecin.setCni(cni);
            medecin.setTelephone(telephone);
            medecin.setEmail(email.toLowerCase());
            medecin.setProfil(profil);
            if(email.contains("@")) {
                // Set the IDENTIFIANT :
                String[] resultat = email.split("@");
                medecin.setIdentifiant(resultat[0]);
                String heure = new SimpleDateFormat("HH:mm").format(new Date());
                String password = heure.replace(":","");
                //String password = "Kx"+heure.replace(":","")+"_1";
                medecin.setMotpasse(password);
                //
                int idMed = medecinRepository.save(medecin).getIdmed();

                // Profil : NO MATTER THE PROFIL, drop a line in 'anf_assignation' :
                List<Hopital> leshopitaux = hopitalRepository.findAll();
                Assignation assignation = new Assignation();
                assignation.setIdmed(idMed);
                int idHop = Integer.valueOf(leshopitaux.get(0).getIdhop().toString());
                assignation.setIdhop(idHop);
                assignationRepository.save(assignation);

                // Send a mail to the user :
                mailCreation("Création de votre compte",
                        resultat[0],
                        password,
                        email);
            }
        }
        return "redirect:/comptes";
    }

    // Creer un hopital :
    @PostMapping(value = "/creerhopital")
    public String creerhopital(@RequestParam("hopital") String hopital){
        Hopital hop = new Hopital();
        hop.setLibelle(hopital);
        hopitalRepository.save(hop);
        return "redirect:/gesthopitaux";
    }

    // Creer une QUANTITE :
    @PostMapping(value = "/creerquantite")
    public String creerquantite(@RequestParam("information") String information){
        Quantite quantite = quantiteRepository.findByLibelle(information.trim());
        if(quantite == null){
            quantite = new Quantite();
            quantite.setLibelle(information);
            quantiteRepository.save(quantite);
        }
        return "redirect:/gestquantite";
    }

    // Creer une PHARMACIE :
    @PostMapping(value = "/creerpharmc")
    public String creerpharmc(@RequestParam("libelle") String libelle,
                              @RequestParam("email") String email,
                              @RequestParam("numero") String numero){
        Pharmacie pharmacie = new Pharmacie();
        pharmacie.setActif(1);
        pharmacie.setLibelle(libelle);
        pharmacie.setEmail(email);
        pharmacie.setNumero(numero);
        pharmacieRepository.save(pharmacie);
        //
        return "redirect:/reachpharm";
    }

    // Creer POSOLOGIE :
    @PostMapping(value = "/creerposologie")
    public String creerposologie(@RequestParam("information") String information){
        Posologie posologie = posologieRepository.findByLibelle(information.trim());
        if(posologie == null){
            posologie = new Posologie();
            posologie.setLibelle(information);
            posologieRepository.save(posologie);
        }
        return "redirect:/gestposologie";
    }

    // Creer DOSAGE :
    @PostMapping(value = "/creerdosage")
    public String creerdosage(@RequestParam("information") String information){
        Dosage dosage = dosageRepository.findByLibelle(information.trim());
        if(dosage == null){
            dosage = new Dosage();
            dosage.setLibelle(information);
            dosageRepository.save(dosage);
        }
        return "redirect:/gestdosage";
    }

    // Creer TYPE :
    @PostMapping(value = "/creertypesmedic")
    public String creertypesmedic(@RequestParam("information") String information){
        Typesmedic typesmedic = typesmedicRepository.findByLibelle(information.trim());
        if(typesmedic == null){
            typesmedic = new Typesmedic();
            typesmedic.setLibelle(information);
            typesmedicRepository.save(typesmedic);
        }
        return "redirect:/gesttypes";
    }


    // Services
    @GetMapping(value = "/gestservices")
    public String gestservices(Model model){
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);
        // ---------------

        return "services";
    }

    // Modifier un service
    @GetMapping(value = "/modifservices/{idser}")
    public String modifservices(@PathVariable int idser, Model model){

        // Get the service :
        Services services = servicesRepository.findByIdser(idser);
        if(services != null){
            model.addAttribute("services", services);
            model.addAttribute("servicesid",
                    "/gestion/modifierservices/"+idser);
        }
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);
        // ---------------
        return "services";
    }

    // gestservicesoption
    @GetMapping(value = "/gestservicesoption")
    public String gestservicesoption(Model model){
        List<Services> listeServices = servicesRepository.findAll();
        model.addAttribute("listeServices", listeServices);

        /*****   Get the services wth their DATA    ******/
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeDonnees = em.createQuery(
            "SELECT a.libelle,b.motif,b.antecedent,b.modevie,b.examenclinique," +
            "b.conclusion,b.diagnostic,b.traitement,b.interrogatoire," +
            "b.examenphysique,b.conduiteatenir,b.idsop,c.libelle FROM " +
            "anf_services a inner join anf_serviceoption b on a.idser=b.idser " +
            "inner join anf_hopital c on b.idhop=c.idhop").getResultList();
        model.addAttribute("listeDonnees", listeDonnees);
        // Close :
        em.getTransaction().commit();
        em.close();

        // Get HOSPITAL data :
        List<Hopital> listeHop = hopitalRepository.findAll();
        model.addAttribute("listeHop", listeHop);
        model.addAttribute("monurl", monUrl);

        return "servicesoption";
    }


    // Creer un service :
    @PostMapping(value = "/creerservices")
    public String creerservices(@RequestParam("services") String serv){
        Services servi = new Services();
        servi.setLibelle(serv);
        servicesRepository.save(servi);
        return "redirect:/gestservices";
    }

    // Mettre à jour un SERVICE :
    @PostMapping(value = "/modifierservices/{idser}")
    public String modifierservices(@PathVariable int idser,
        @RequestParam("services") String serv){
        Services servi = servicesRepository.findByIdser(idser);
        servi.setLibelle(serv);
        servicesRepository.save(servi);
        return "redirect:/gestservices";
    }


    // Definir les options par service :
    @PostMapping(value = "/creerservicesopt")
    public String creerservicesopt(
        @RequestParam(name="service") Integer service,
        @RequestParam(name="motif", required = false) Integer motif,
        @RequestParam(name="antecedent", required = false) Integer antecedent,
        @RequestParam(name="modevie", required = false) Integer modevie,
        @RequestParam(name="examenclinique", required = false) Integer examenclinique,
        @RequestParam(name="conclusion", required = false) Integer conclusion,
        @RequestParam(name="diagnostic", required = false) Integer diagnostic,
        @RequestParam(name="traitement", required = false) Integer traitement,
        @RequestParam(name="interrogatoire", required = false) Integer interrogatoire,
        @RequestParam(name="examenphysique", required = false) Integer examenphysique,
        @RequestParam(name="conduiteatenir", required = false) Integer conduiteatenir,
        @RequestParam(name="diagnosticretenu", required = false) Integer diagnosticretenu,
        @RequestParam(name="hospitalisation", required = false) Integer hospitalisation,
        @RequestParam(name="soins", required = false) Integer soins,
        @RequestParam(name="avisconfrere", required = false) Integer avisconfrere,
        @RequestParam(name="ordonnance", required = false) Integer ordonnance,

        @RequestParam(name="passemedical", required = false) Integer passemedical,
        @RequestParam(name="allergies", required = false) Integer allergies,
        @RequestParam(name="passedentaire", required = false) Integer passedentaire,
        @RequestParam(name="protheses", required = false) Integer protheses,
        @RequestParam(name="interventions", required = false) Integer interventions,
        @RequestParam(name="examparaclinique", required = false) Integer examparaclinique,

        @RequestParam(name="hopital", required = false) Integer hopital
        ){

        // Try to get ServiceOption
        //Serviceoption so = serviceoptionRepository.findByIdser(service);
        Serviceoption so = serviceoptionRepository.findByIdserAndIdhop(service,
                hopital);
        if(so == null){
            // Create :
            so = new Serviceoption();
        }

        so.setMotif(motif != null ? motif : 0);
        so.setAntecedent(antecedent != null ? antecedent : 0);
        so.setModevie(modevie != null ? modevie : 0);
        so.setExamenclinique(examenclinique != null ? examenclinique : 0);
        so.setConclusion(conclusion != null ? conclusion : 0);
        so.setDiagnostic(diagnostic != null ? diagnostic : 0);
        so.setTraitement(traitement != null ? traitement : 0);
        so.setInterrogatoire(interrogatoire != null ? interrogatoire : 0);
        so.setExamenphysique(examenphysique != null ? examenphysique : 0);
        so.setConduiteatenir(conduiteatenir != null ? conduiteatenir : 0);
        /******  NOUVEAUX  *****/
        so.setDiagnosticretenu(diagnosticretenu != null ? diagnosticretenu : 0);
        so.setHospitalisation(hospitalisation != null ? hospitalisation : 0);
        so.setSoins(soins != null ? soins : 0);
        so.setAvisconfrere(avisconfrere != null ? avisconfrere : 0 );
        so.setOrdonnance(ordonnance != null ? ordonnance : 0 );
        //
        so.setPassemedical(passemedical != null ? passemedical : 0 );
        so.setAllergies(allergies != null ? allergies : 0 );
        so.setPassedentaire(passedentaire != null ? passedentaire : 0 );
        so.setProtheses(protheses != null ? protheses : 0 );
        so.setInterventions(interventions != null ? interventions : 0 );
        so.setExamparaclinique(examparaclinique != null ? examparaclinique : 0 );

        /***********/
        so.setIdhop(hopital != null ? hopital : 0);
        so.setIdser(service);
        // Keep :
        serviceoptionRepository.save(so);

        return "redirect:/gestservicesoption";
    }



    // Pour ajouter un enregistrement :
    @PostMapping(value = "/modifconsultation/{idcon}")
    public String modifconsultation(@RequestParam("idpatient") int idpatient,
        @RequestParam(name="age", required = false) Integer age,
        @RequestParam("datenaissance") String datenaissance,
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("profession") int profession,
        @RequestParam("adresse") String adresse,
        @RequestParam("sexe") String sexe,
        @RequestParam("cni") String cni,
        @RequestParam("telephone") String telephone,
        @RequestParam("email") String email,
        @RequestParam("hopital") int hopital,
        @RequestParam("envoyepar") String envoyepar,
        @RequestParam("service") int service,
        @RequestParam("diagnostic") String diagnostic,
        @RequestParam("dateobserv[]") String[] dateobservation,
        @RequestParam("commobserv[]") String[] commobserv,
        @RequestParam("dateconsultation") String dateconsultation,
        @RequestParam(name="fichiersajoute[]", required = false) MultipartFile[] fichiers,
        @RequestParam(name="naturefichier[]", required = false) Integer[] idnaturefichier,

        @RequestParam("daterdv") String daterdv,
        @RequestParam("heurerdv") Integer heurerdv,
        @RequestParam(name="choixrdv", required = false) Integer choixrdv,

        @RequestParam(name="motif", required = false) String motif,
        @RequestParam(name="diabete", required = false) Integer diabete,
        @RequestParam(name="hypertension", required = false) Integer hypertension,
        @RequestParam(name="pblmecardiaque", required = false) Integer pblmecardiaque,
        @RequestParam(name="pblmepulmon", required = false) Integer pblmepulmon,
        @RequestParam(name="drepano", required = false) Integer drepano,
        @RequestParam(name="autres", required = false) Integer autres,
        @RequestParam(name="commentautre", required = false) String commentautre,
        @RequestParam(name="modedevie", required = false) Integer modedevie,
        @RequestParam(name="examenclinique", required = false) String examenclinique,
        @RequestParam(name="conclusion", required = false) String conclusion,
        @RequestParam(name="traitement", required = false) String traitement,

        @RequestParam(name="interrogatoire", required = false) String interrogatoire,
        @RequestParam(name="examenphysique", required = false) String examenphysique,
        @RequestParam(name="conduiteatenir", required = false) String conduiteatenir,

        @RequestParam(name="tabagismeactif", required = false) Integer tabagismeactif,
        @RequestParam(name="tabagismepassif", required = false) Integer tabagismepassif,
        @RequestParam(name="ethylisme", required = false) Integer ethylisme,
        @RequestParam(name="sedentaire", required = false) Integer sedentaire,
        @RequestParam(name="autresmode", required = false) Integer autresmode,
        @RequestParam(name="autresmodecomment", required = false) String autresmodecomment,

        @RequestParam(name="diagnosticretenu", required = false) String diagnosticretenu,
        @RequestParam(name="hospitalisation", required = false) Integer hospitalisation,
        @RequestParam(name="soins", required = false) String soins,

        @RequestParam(name="avisconfrere", required = false) String avisconfrere,
        @RequestParam(name="ordonnance", required = false) String ordonnance,
        @RequestParam(name="renseignementsclin", required = false) String renseignementsclin,
        @RequestParam(name="examensnondefinis", required = false) String examensnondefinis,

        @RequestParam(name="poids", required = false) Double poids,
        @RequestParam(name="taille", required = false) Double taille,
        @RequestParam(name="temperature", required = false) Double temperature,
        @RequestParam(name="tensionarterielle", required = false) String tensionarterielle,
        @RequestParam(name="pouls", required = false) Integer pouls,

        @RequestParam(name="chambre", required = false) String chambre,
        @RequestParam(name="lit", required = false) String lit,
        @RequestParam(name="datentree", required = false) String datentree,
        @RequestParam(name="heurehospi", required = false) String heurehospi,
        @RequestParam(name="motifhospi", required = false) String motifhospi,
        @RequestParam(name="bilanbio", required = false) String bilanbio,
        @RequestParam(name="bilanradio", required = false) String bilanradio,
        @RequestParam(name="nbrejour", required = false) String nbrejour,

        @RequestParam(name="cardiopathie", required = false) String cardiopathie,
        @RequestParam(name="troubles", required = false) String troubles,
        @RequestParam(name="hta", required = false) String hta,
        @RequestParam(name="diabetedent", required = false) String diabetedent,
        @RequestParam(name="asthme", required = false) String asthme,
        @RequestParam(name="infectionsorl", required = false) String infectionsorl,
        @RequestParam(name="vih", required = false) String vih,
        @RequestParam(name="antibiotique", required = false) String antibiotique,
        @RequestParam(name="anesthesie", required = false) String anesthesie,
        @RequestParam(name="quinine", required = false) String quinine,
        @RequestParam(name="latex", required = false) String latex,
        @RequestParam(name="autresdentaire", required = false) String autresdentaire,
        @RequestParam(name="dentsmanquantes", required = false) String dentsmanquantes,
        @RequestParam(name="appareillage", required = false) Integer appareillage,
        @RequestParam(name="hygienebuccale", required = false) Integer hygienebuccale,
        @RequestParam(name="articuledentaire", required = false) Integer articuledentaire,
        @RequestParam(name="langue", required = false) Integer langue,
        @RequestParam(name="suceur", required = false) Integer suceur,
        @RequestParam(name="orthodontique", required = false) Integer orthodontique,

        @RequestParam("dateintervention[]") String[] dateintervention,
        @RequestParam("dents[]") String[] dents,
        @RequestParam("natureintervention[]") String[] natureintervention,

        @RequestParam("enplace[]") String[] enplace,
        @RequestParam("localisation[]") String[] localisation,
        @RequestParam("typeproth[]") String[] typeproth,
        @RequestParam("dateprothese[]") String[] dateprothese,

        @PathVariable int idcon,
        @RequestParam("examens[]") Integer[] examens,
        HttpSession session, Model model, Principal principal)
    {
        // get Back the patient
        Patient patient = patientRepository.findByIdpat(idpatient);
        if(nom.trim().length() > 0) patient.setNom(nom);
        if(prenom.trim().length() > 0) patient.setPrenom(prenom);
        if(cni.trim().length() > 0) patient.setCni(cni);
        if(telephone.trim().length() > 0) patient.setTelephone(telephone);
        if(email.trim().length() > 0) patient.setEmail(email);
        patient.setSexe(sexe);
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
            //patient.setDatecreation(dateAujourdhui);
        }
        catch (Exception exc){}
        patient.setProfession(profession);
        if(adresse.trim().length() > 0) patient.setAdresse(adresse);
        // Save :
        patientRepository.save(patient);

        // Move on :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        Date tpdate;
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //consultation.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            //consultation.setHeure(heure);
            consultation.setSymptome(diagnostic);
            consultation.setIdhop(hopital);
            consultation.setIdpat(idpatient);

            Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
            //consultation.setIdmed(medecin.getIdmed());
            consultation.setEnvoyerpar(envoyepar);
            consultation.setIdser(service);
            // Update the CONSULTATION DATE :
            Date dateConsul = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(dateconsultation));
            consultation.setDateconsultation(dateConsul);

            // Try to ADD the new FIELDS :
            if(motif != null) consultation.setMotif(motif);
            else consultation.setMotif("");
            if(diabete != null) consultation.setDiabete(diabete);
            else consultation.setDiabete(0);
            if(hypertension != null) consultation.setHypertension(hypertension);
            else consultation.setHypertension(0);
            if(pblmecardiaque != null) consultation.setPblcardiaque(pblmecardiaque);
            else consultation.setPblcardiaque(0);
            if(pblmepulmon != null) consultation.setPblpulmonaire(pblmepulmon);
            else consultation.setPblpulmonaire(0);
            if(drepano != null) consultation.setDrepanocytose(drepano);
            else consultation.setDrepanocytose(0);
            if(autres != null) consultation.setAutres(autres);
            else consultation.setAutres(0);
            if(commentautre != null) consultation.setAutrescommentaire(commentautre.trim());
            else consultation.setAutrescommentaire("");
            if(modedevie != null) consultation.setModevie(modedevie);
            else consultation.setModevie(0);
            if(examenclinique != null) consultation.setExamenclinique(examenclinique);
            else consultation.setExamenclinique("");
            if(conclusion != null) consultation.setConclusion(conclusion);
            else consultation.setConclusion("");
            if(traitement != null) consultation.setTraitement(traitement);
            else consultation.setTraitement("");
            if(interrogatoire != null) consultation.setInterrogatoire(interrogatoire);
            else consultation.setInterrogatoire("");
            if(examenphysique != null) consultation.setExamenphysique(examenphysique);
            else consultation.setExamenphysique("");
            if(conduiteatenir != null) consultation.setConduiteatenir(conduiteatenir);
            else consultation.setConduiteatenir("");

            if(tabagismeactif != null) consultation.setTabagismeactif(tabagismeactif);
            else consultation.setTabagismeactif(0);
            if(tabagismepassif != null) consultation.setTabagismepassif(tabagismepassif);
            else consultation.setTabagismepassif(0);
            if(ethylisme != null) consultation.setEthylisme(ethylisme);
            else consultation.setEthylisme(0);
            consultation.setSedentaire(sedentaire != null ? sedentaire : 0);
            consultation.setAutresmode(autresmode != null ? autresmode : 0);
            consultation.setAutresmodecomment(autresmodecomment != null ? autresmodecomment : "" );
            //--------------  28/11/2020
            consultation.setDiagnosticretenu(diagnosticretenu != null ? diagnosticretenu : "" );
            consultation.setHospitalisation(hospitalisation != null ? hospitalisation : 0 );
            consultation.setSoins(soins != null ? soins : "" );
            //
            consultation.setAvisconfrere(avisconfrere != null ? avisconfrere.trim() : "" );
            consultation.setOrdonnance(ordonnance != null ? ordonnance.trim() : "" );
            consultation.setRenseignementsclin(renseignementsclin != null ? renseignementsclin.trim() : "" );

            // Save :
            consultationRepository.save(consultation);

            // Process CABINET DENTAIRE : idcon
            int cdteId = 0;
            if(cardiopathie != null) {
                if(cardiopathie.trim().length() > 0) {
                    Cabinetdentaire cdte = cabinetdentaireRepository.findByIdpat(idpatient); // Idcon(idcon);
                    if (cdte == null) {
                        cdte = new Cabinetdentaire();
                        cdte.setIdpat(idpatient);
                        cdte.setIdcon(idcon);
                    }
                    cdte.setCardiopathie(cardiopathie.trim());
                    cdte.setTroubles(troubles.trim());
                    cdte.setHta(hta.trim());
                    cdte.setDiabetedent(diabetedent.trim());
                    cdte.setAsthme(asthme.trim());
                    cdte.setInfectionsorl(infectionsorl.trim());
                    cdte.setVih(vih.trim());
                    cdte.setAntibiotique(antibiotique.trim());
                    cdte.setAnesthesie(anesthesie.trim());
                    cdte.setQuinine(quinine.trim());
                    cdte.setLatex(latex.trim());
                    cdte.setAutresdentaire(autresdentaire.trim());
                    cdte.setDentsmanquantes(dentsmanquantes.trim());
                    cdte.setAppareillage(appareillage);
                    cdte.setHygienebuccale(hygienebuccale);
                    cdte.setArticuledentaire(articuledentaire);
                    cdte.setLangue(langue);
                    cdte.setSuceur(suceur);
                    cdte.setOrthodontique(orthodontique);
                    // Save :
                    cdteId = cabinetdentaireRepository.save(cdte).getIdcde();

                    // Now process PROTHESE Data, by deleting the OLDs ones :
                    prothesesRepository.deleteAllByIdpat(idpatient);
                    for (int k = 0; k < enplace.length; k++) {
                        if (enplace[k].trim().length() > 0 || localisation[k].trim().length() > 0) {
                            Protheses ps = new Protheses();
                            ps.setEnplace(enplace[k].trim());
                            ps.setLocalisation(localisation[k].trim());
                            ps.setTypeproth(typeproth[k].trim());
                            Date dateProth = new SimpleDateFormat("yyyy-MM-dd").
                                    parse(ClassFonction.retourDate(dateprothese[k]));
                            ps.setDateprothese(dateProth);
                            ps.setIdpat(idpatient);
                            ps.setIdcde(cdteId);
                            //
                            prothesesRepository.save(ps);
                        }
                        // Stop to keep TWO records
                        if (k == 2) break;
                    }

                    // Process NATURE INTERVENTIONS too :
                    for (int j = 0; j < dateintervention.length; j++) {
                        if (natureintervention[j].trim().length() > 0) {
                            Date dateInterven = new SimpleDateFormat("yyyy-MM-dd").
                                    parse(ClassFonction.retourDate(dateintervention[j]));
                            // Try to UPDATE first and CREATE if not :
                            Interventions intervention =
                                    interventionsRepository.findByIdconAndDates(idcon, dateInterven);
                            if (intervention == null) {
                                // Meaning that we have new DATA
                                intervention = new Interventions();
                                intervention.setIdcon(idcon);
                                intervention.setDates(dateInterven);
                            }
                            int dnts = 0;
                            try{
                                dnts = Integer.parseInt(dents[j]);
                            }
                            catch (Exception exc){}
                            intervention.setDent(dnts);
                            intervention.setNature(natureintervention[j].trim());
                            intervention.setIdpat(patient.getIdpat());
                            // Save it :
                            interventionsRepository.save(intervention);
                        }
                    }
                }
            }

            //*********************************
            Hospitalisation hn = hospitalisationRepository.findByIdcon(idcon);
            if(hospitalisation != null){
                if(hospitalisation == 1){
                    if(hn==null){
                        hn = new Hospitalisation();
                        hn.setIdcon(idcon);
                    }
                    //if(hn != null) {
                    hn.setChambre(chambre.trim());
                    hn.setLit(lit.trim());
                    Date dateEntree = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(datentree));
                    hn.setDatentree(dateEntree);
                    hn.setHeure(heurehospi.trim());
                    hn.setMotif(motifhospi.trim());
                    hn.setBilanbio(bilanbio.trim());
                    hn.setBilanradio(bilanradio.trim());
                    int jrs = 0;
                    try{
                        jrs = Integer.parseInt(nbrejour);
                    }
                    catch (Exception exc){}
                    hn.setNbrejour(jrs);
                    // Save it
                    hospitalisationRepository.save(hn);
                    //}
                }
            }


            // try to update constante :
            Constante constante = constanteRepository.findByIdcon(idcon);
            if(constante != null){
                constante.setTaille(taille);
                constante.setTemperature(temperature);
                constante.setTension(0.0);
                constante.setTensionarterielle(tensionarterielle);
                constante.setPoids(poids);
                constante.setPouls(pouls);
                // save :
                constanteRepository.save(constante);
            }


            // Check if we have FILES attached :
            int j = 0;
            if(fichiers != null) {
                for (MultipartFile files : fichiers) {

                    if (!files.isEmpty()) {
                        // Save the file :
                        byte[] bytes = files.getBytes();
                        String nomfichier = files.getOriginalFilename();
                        String typefichier = files.getContentType();
                        long tailleFichier = files.getSize();
                        //
                        Fichiers monFichier = new Fichiers();
                        monFichier.setFichier(bytes);
                        monFichier.setNomfichier(nomfichier);
                        monFichier.setTypefichier(typefichier);
                        monFichier.setTaillefichier(tailleFichier);
                        monFichier.setIdcon(consultation.getIdcon());
                        //
                        monFichier.setIdntf(idnaturefichier[j]);

                        // Now SAVE :
                        fichiersRepository.save(monFichier);
                    }
                    //
                    j++;
                }
            }

            //
            //Medecin med = medecinRepository.findByIdmed(Integer.parseInt(medecinid.toString()));
            /*sendMail("Modification d'une consultation",
                    "Une consultation a été modifiée à "+heure+
                            " par "+medecin.getNom()+" "+medecin.getPrenom(),
                    medecin.getIdmed());
            */

            // Set the date :
            if(choixrdv != null) {

                // Delete the eventual old one :
                Rendezvous rs = rendezvousRepository.findByIdcon(idcon);
                rendezvousRepository.deleteByIdcon(idcon);

                //
                Date dteRdv = new SimpleDateFormat("yyyy-MM-dd").
                        parse(ClassFonction.retourDate(daterdv));
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setDates(dteRdv);
                rendezvous.setHeure(heurerdv);
                rendezvous.setIdcon(idcon);
                // Now, save :
                rendezvousRepository.save(rendezvous);
                // Retrieve first
                Factorisationrdv obj =
                    factorisationrdvRepository.
                        findByIdpatAndProvenanceAndIdmedAndDates(
                            idpatient, 1, consultation.getIdmed(),
                                rs.getDates()
                        );
                if(obj!=null){
                    // Now hit anf_factorisationrdv TABLE :
                    obj.setDates(dteRdv);
                    obj.setHeure(heurerdv);
                    obj.setHopital(hopital);
                    obj.setIdpat(idpatient);
                    obj.setIdmed(consultation.getIdmed());
                    obj.setIdser(service);
                    // No need to set PROVENANCE Save :
                    factorisationrdvRepository.save(obj);
                }
            }

            // Update EXAMENS list, delete EXAMS REQUESTs that have not been updated
            //  with files :  .deleteAllByIdconAndStatut(idcon,0);
            cmdexamensRepository.deleteAllByIdconAndStatutAndMontant(idcon,0,0.0);

            // Get the EXAMENS
            List<Integer> listeIdExam = new ArrayList<>();
            if(examensnondefinis.trim().length() > 0){
                // Split ';'
                String[] examNonDefinis = examensnondefinis.split(";");
                if(examNonDefinis.length > 0){
                    // Browse :
                    for(String monExam : examNonDefinis){
                        // Create it :
                        if(monExam.trim().length() <= 50){
                            Examen en = new Examen();
                            en.setLibelle(monExam.trim());
                            // Save it :
                            listeIdExam.add(examenRepository.save(en).getIdexam());
                        }
                    }
                }
            }

            // Hit Cmdexamens
            if(listeIdExam.size() > 0){
                for (int idexam : listeIdExam) {
                    Cmdexamens cmdexamens = new Cmdexamens();
                    cmdexamens.setIdcon(idcon);
                    cmdexamens.setIdexam(idexam);
                    cmdexamens.setIdpat(idpatient);
                    cmdexamens.setMontant(0.0);
                    cmdexamens.setStatut(0);
                    try {
                        String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        cmdexamens.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                        // Save :
                        cmdexamensRepository.save(cmdexamens);
                    } catch (Exception exc) {
                        System.out.println("Erreur survenue : "+exc.toString());
                    }
                }
            }


            // Get those that remain :
            List<Cmdexamens> listeRestant = cmdexamensRepository.findAllByIdcon(idcon);
            for(int exam : examens){
                if(exam > 1){
                    boolean verifier_etat = false;
                    // Avoid a new EXAM if it exists already :
                    for (Cmdexamens exams : listeRestant){
                        if(exams.getIdexam() == exam){
                            verifier_etat = true;
                            break;
                        }
                    }
                    if(!verifier_etat) {
                        Cmdexamens cmdexamens = new Cmdexamens();
                        cmdexamens.setIdcon(idcon);
                        cmdexamens.setIdexam(exam);
                        cmdexamens.setIdpat(idpatient);
                        cmdexamens.setMontant(0.0);
                        cmdexamens.setStatut(0);
                        try {
                            String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                            cmdexamens.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(dte));
                            // Save :
                            cmdexamensRepository.save(cmdexamens);
                        } catch (Exception exc) {
                        }
                    }
                }
            }
        }
        catch (Exception exc){

        }

        // delete all ROWS :
        //observationRepository.deleteByIdcon(idcon);
        observationRepository.deleteAllByIdcon(idcon);

        // Browse :
        for(int i =0; i < dateobservation.length; i++){
            Observation observ = new Observation();
            try{
                if(commobserv[i].trim().length() > 0) {
                    Date tamponDTE = new SimpleDateFormat("yyyy-MM-dd").
                            parse(ClassFonction.retourDate(dateobservation[i]));
                    observ.setDates(tamponDTE);
                    observ.setCommentaire(commobserv[i]);
                    observ.setIdcon(idcon);
                    // Sauvegarder :
                    observationRepository.save(observ);
                }
            }
            catch (Exception exc){
            }
        }
        //for(String dates : dateobservation){
        //    System.out.println("Date : "+dates);
        //}
        return "redirect:/accueilpatient";
    }


    @GetMapping(value = "/genererfiche/{idcon}")
    public HttpEntity<byte[]> genererfiche(@PathVariable int idcon,
                                           Model model, HttpSession session)
            throws Exception
    {
        // Let's get Observation DATA :
        List<Observation> listeObservation =
                observationRepository.findAllByIdcon(idcon);
        List<Donneesobservations> ObjetObservation =
                new ArrayList<Donneesobservations>();
        Donneesobservations dos = new Donneesobservations();
        // Initialize :
        dos.setDates("");
        dos.setConsultation("");
        ObjetObservation.add(dos);
        for(Observation obv : listeObservation){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(
                    obv.getDates());
            dos = new Donneesobservations();
            dos.setDates(date);
            dos.setConsultation(" "+obv.getCommentaire());
            ObjetObservation.add(dos);
        }

        //
        //System.out.println("Taille : "+ ObjetObservation);

        // Now set the parameters :
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ObjetObservation);
        //InputStream inputStream = this.getClass().getResourceAsStream("/reports/fichedermatologie.jrxml");
        InputStream inputStream =
            this.getClass().getResourceAsStream("/reports/dermatologietreich.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // get PATIENT DATA :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        // Now get PATIENT
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("iconechu", "/static/images/logo-chu-1.jpg");
        parameters.put("itemdatasource", dataSource);
        //System.out.println("Diagnostic : "+consultation.getSymptome());

        Commentaires lediagnostic = new Commentaires();
        decouperTexte(consultation.getSymptome().replaceAll("[\r\n]+", " "), lediagnostic);
        /*String[] lediagnostic =
                FonctionOrdinaire.decouperDiagnostic(consultation.getSymptome().
                        replaceAll("[\r\n]+"," "));*/
        parameters.put("diagnosticun",
                lediagnostic.getListeCommentaire().size() >= 1 ?
                        lediagnostic.getListeCommentaire().get(0) : "" );
        parameters.put("diagnosticdeux", lediagnostic.getListeCommentaire().size() >= 2 ?
                lediagnostic.getListeCommentaire().get(1) : "");
        parameters.put("diagnostictrois", lediagnostic.getListeCommentaire().size() >= 3 ?
                lediagnostic.getListeCommentaire().get(2) : "");

        //System.out.println("Diagnostic 1: "+lediagnostic[0]);
        //System.out.println("Diagnostic 2: "+lediagnostic[1]);
        //System.out.println("Diagnostic 3: "+lediagnostic[2]);

        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        parameters.put("adresse", patient.getAdresse());
        parameters.put("age", String.valueOf(patient.getAge()));
        parameters.put("envoyepar", consultation.getEnvoyerpar());
        Profession profession = professionRepository.findByIdprof(patient.getProfession());
        parameters.put("profession", profession.getLibelle());
        parameters.put("numdossier", String.valueOf(idcon));

        Commentaires leTraitement = new Commentaires();
        decouperTexte(consultation.getTraitement().replaceAll("[\r\n]+", " "), leTraitement);
        /*String[] leTraitement =
                FonctionOrdinaire.decouperTraitement(consultation.getTraitement().
                        replaceAll("[\r\n]+"," "));*/
        parameters.put("traitementun", leTraitement.getListeCommentaire().size() >= 1 ?
                leTraitement.getListeCommentaire().get(0) : "");
        parameters.put("traitementdeux", leTraitement.getListeCommentaire().size() >= 2 ?
                leTraitement.getListeCommentaire().get(1) : "");
        parameters.put("traitementtrois", leTraitement.getListeCommentaire().size() >= 3 ?
                leTraitement.getListeCommentaire().get(2) : "");
        parameters.put("traitementquatre", leTraitement.getListeCommentaire().size() >= 4 ?
                leTraitement.getListeCommentaire().get(3) : "");

        // Examen clinique :
        Commentaires examenClinique = new Commentaires();
        decouperTexte(
                consultation.getExamenclinique().replaceAll("[\r\n]+", " ") ,
                examenClinique);
        parameters.put("examun", examenClinique.getListeCommentaire().size() >= 1 ?
                examenClinique.getListeCommentaire().get(0) : "");
        parameters.put("examdeux", examenClinique.getListeCommentaire().size() >= 2 ?
                examenClinique.getListeCommentaire().get(1) : "");
        parameters.put("examtrois", examenClinique.getListeCommentaire().size() >= 3 ?
                examenClinique.getListeCommentaire().get(2) : "");
        parameters.put("examquatre", examenClinique.getListeCommentaire().size() >= 4 ?
                examenClinique.getListeCommentaire().get(3) : "");
        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapport.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }


    // habiliteradmin
    @GetMapping(value = "/testnvd3")
    public String testnvd3(){
        return "testnvd3";
    }


    // habiliteradmin
    @GetMapping(value = "/comptesmedecin")
    public String comptesmedecin(Principal principal, Model model){

        // Get ADMIN id back :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        // set our lists :
        List <Object> resultat = new ArrayList<Object>();
        resultat = emr.createQuery(
            "select distinct a.nom,a.prenom,a.telephone,a.email,e.libelle " +
            "from anf_medecin a inner join anf_consultation b on a.idmed=b.idmed " +
            "inner join anf_affectation c on b.idhop=c.idhop " +
            "inner join anf_affec_service d on d.idmed=a.idmed " +
            "inner join anf_services e on e.idser=d.idser " +
            "where c.idmed = " + medecin.getIdmed())
                .getResultList();
        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listemedecin",resultat);
        return "listemedecin";
    }


    @GetMapping(value = "/comptespatientsadmin")
    public String comptespatientsadmin(Principal principal, Model model){

        // Get ADMIN id back :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        // set our lists :
        List <Object> resultat = new ArrayList<Object>();
        //List <Object[]> resultat = new ArrayList<Object[]>();
        List<Medecin> listeMedecin = new ArrayList<Medecin>();

        resultat = emr.createQuery(
            "select distinct concat(a.nom, ' ', a.prenom) , a.telephone, a.sexe, a.age, b.libelle," +
            "a.idpat from anf_patient a inner join anf_profession b on a.profession=b.idprof " +
            "where a.idpat in (select distinct a.idpat from anf_consultation a inner join " +
            "anf_affectation b on a.idhop=b.idhop where b.idmed= " + medecin.getIdmed()+")")
                .getResultList();
        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listepatients",resultat);
        return "listepatientsadmin";
    }


    //------- LISTE D'ATTENTE PATIENTS passés en prise de Constante ---
    @GetMapping(value = "/attentepatients")
    public String attentepatient(Principal principal, Model model){

        // Get ADMIN id back :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        // set our lists :
        List <Object> resultat = new ArrayList<>();
        resultat = emr.createQuery(
            "select distinct a.nom,a.prenom,a.sexe,c.libelle,a.age from " +
            "anf_patient a inner join anf_constante b on a.idpat=b.idpat " +
            "inner join anf_profession c on c.idprof=a.profession " +
            "where b.iddocteur = "+medecin.getIdmed()+
            " and date(b.dates) = date(now()) and b.idcon=0")
                .getResultList();
        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listepatients",resultat);
        return "attentepatients";
    }



    // autorisation
    @GetMapping(value = "/assignation")
    public String assignation(Model model, HttpSession session, Principal principal){

        // Recuperer la liste des medecins :
        //List<Medecin> listeMedecin = medecinRepository.findAllByProfil("medecin");
        List<String> listeProfil = new ArrayList<String>();
        listeProfil.add("medecin");
        listeProfil.add("secretaire");
        listeProfil.add("secretairemedicale");
        listeProfil.add("laborantin");
        listeProfil.add("gestionnaire");
        listeProfil.add("pharmacien");
        listeProfil.add("aidesoignante");
        List<Medecin> listeMedecin = medecinRepository.findAllByProfilIn(listeProfil);
        model.addAttribute("listeMedecin", listeMedecin);

        // Liste des HOPITAUX :
        List<Hopital> listeHopitaux = hopitalRepository.findAll();
        model.addAttribute("listeHopital", listeHopitaux);

        // Get the ones allowed :
        Object medecinid = session.getAttribute("medecinid");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeAssignation = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom), c.libelle " +
                        "from anf_medecin a inner join anf_assignation b on " +
                        "a.idmed=b.idmed inner join anf_hopital c on b.idhop=c.idhop").getResultList();
        model.addAttribute("listeAssignation", listeAssignation);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "assignation";
    }



    // Assignation pharmacie
    @GetMapping(value = "/assignationpharm")
    public String assignationpharm(Model model, HttpSession session, Principal principal){

        // Recuperer la liste des medecins :
        //List<Medecin> listeMedecin = medecinRepository.findAllByProfil("medecin");
        List<String> listeProfil = new ArrayList<String>();
        listeProfil.add("pharmacien");
        List<Medecin> listeMedecin = medecinRepository.findAllByProfilIn(listeProfil);
        model.addAttribute("listeMedecin", listeMedecin);

        // Liste des HOPITAUX :
        List<Pharmacie> listePharmacie =
                pharmacieRepository.findAllByIdpharmGreaterThan(1);
        model.addAttribute("listePharmacie", listePharmacie);

        // Get the ones allowed :
        Object medecinid = session.getAttribute("medecinid");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeAssignation = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom), c.libelle " +
                        "from anf_medecin a inner join anf_assignationpharm b on " +
                        "a.idmed=b.idmed inner join anf_pharmacie c on b.idpharm=c.idpharm").getResultList();
        model.addAttribute("listeAssignation", listeAssignation);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "assignationpharm";
    }




    // enregautorisation
    @PostMapping(value = "/enregassignation")
    public String enregassignation(@RequestParam("idmedecin") int idmedecin,
                                   @RequestParam("idhopital") int idhopital){

        Assignation assignation = assignationRepository.findByIdmedAndIdhop(idmedecin, idhopital);
        if(assignation == null){
            assignation = new Assignation();
            // Create a new one :
            assignation.setIdhop(idhopital);
            assignation.setIdmed(idmedecin);
            assignationRepository.save(assignation);
        }
        return "redirect:/assignation";

        // return "redirect:/autorisation";
    }


    // enregassignationph
    @PostMapping(value = "/enregassignationph")
    public String enregassignationph(@RequestParam("idmedecin") int idmedecin,
                                   @RequestParam("idpharm") int idpharm){
        Assignationpharm assignation =
            assignationpharmRepository.findByIdmedAndIdpharm(idmedecin,idpharm);
        if(assignation == null){
            assignation = new Assignationpharm();
            // Create a new one :
            assignation.setIdpharm(idpharm);
            assignation.setIdmed(idmedecin);
            assignationpharmRepository.save(assignation);
        }
        return "redirect:/assignationpharm";

        // return "redirect:/autorisation";
    }


    // envoyer un mail :
    private void sendMail(String sujet,String contenus, int userID){
        //
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,
                    "utf-8");
            StringBuilder contenu = new StringBuilder();
            Medecin utilis = medecinRepository.findByIdmed(userID);
            contenu.append("<h2> Information </h2>");
            contenu.append("<div><p>"+contenus+"</p></div>");

            //
            helper.setText(String.valueOf(contenu), true);
            helper.setTo("ngbandamakonan@gmail.com");
            //helper.setTo(utilis.getEmail());
            helper.setSubject(sujet);
            helper.setFrom("bendressouarnaud@gmail.com");
            emailSender.send(mimeMessage);
        }
        catch (Exception exc){
        }
    }



    // Affectation Service
    @GetMapping(value = "/affecservice")
    public String affecservice(Model model, HttpSession session, Principal principal){

        // Recuperer la liste des medecins :
        List<Medecin> listeMedecin = medecinRepository.findAllByProfil("medecin");
        model.addAttribute("listeMedecin", listeMedecin);

        // Liste des Service :
        List<Services> listeService = servicesRepository.findAll();
        model.addAttribute("listeService", listeService);

        // Get the ones allowed :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeAssignation = em.createQuery(
                "select distinct concat(a.nom,' ',a.prenom), c.libelle " +
                        "from anf_medecin a inner join anf_affec_service b on " +
                        "a.idmed=b.idmed inner join anf_services c on b.idser=c.idser").getResultList();
        model.addAttribute("listeAffectation", listeAssignation);

        // Close :
        em.getTransaction().commit();
        em.close();

        return "affectationservice";
        //return "assignation";
    }


    // enregaffservice
    @PostMapping(value = "/enregaffservice")
    public String enregaffservice(@RequestParam("idmed") int idmed,
                                   @RequestParam("idser") int idser){

        Affectationserv affectationserv = affectationservRepository.findByIdmedAndIdser(idmed, idser);
        if(affectationserv == null){
            affectationserv = new Affectationserv();
            // Create a new one :
            affectationserv.setIdser(idser);
            affectationserv.setIdmed(idmed);
            affectationservRepository.save(affectationserv);
        }
        return "redirect:/affecservice";

        // return "redirect:/autorisation";
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
                    "<div><p>Lien de l'application : "+monUrl+"login</p></div>");
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



    //afficherordonnance
    @GetMapping(value = "/afficherordonnance/{id}")
    public String afficherordonnance(@PathVariable int id,
                                      Model model, HttpSession session)
    {
        // id --> idcon : consultation
        // Display Patient name, date, hour of each consultation
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> listeOrdonnance = em.createQuery(
                "select distinct date(a.dates) as dte, a.idcon" +
                        " from anf_ordonnance a where a.idcon = " + id).getResultList();
        // Pass it to the MODEl :
        model.addAttribute("listeordonnance", listeOrdonnance);
        model.addAttribute("idcont",id);
        // Obtenir l'ID du patient :
        Consultation cn = consultationRepository.findByIdcon(id);
        model.addAttribute("idpat",cn.getIdpat());

        // Close :
        em.getTransaction().commit();
        em.close();

        model.addAttribute("monurl", monUrl);

        return "afficherordonnance";
    }


    // Afficher le formulaire de creation de l'ordonnance :
    @GetMapping(value = "/createordonnance/{idcon}")
    public String createordonnance(@PathVariable int idcon,
                                     Model model, HttpSession session)
    {
        model.addAttribute("idcon",idcon);
        model.addAttribute("lienenreg","/gestion/enregordonnance/"+idcon);

        // Quantite
        List<Quantite> listeQuantite = quantiteRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeQuantite",listeQuantite);
        // Posologie
        List<Posologie> listePosologie = posologieRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listePosologie",listePosologie);
        // dosage
        List<Dosage> listeDosage = dosageRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeDosage",listeDosage);
        // Type
        List<Typesmedic> listeTypesmedic = typesmedicRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeTypes",listeTypesmedic);
        // Get pharmacie LIST :
        List<Pharmacie> listePharmacie = pharmacieRepository.findAll();
        model.addAttribute("listePharmacie",listePharmacie);

        model.addAttribute("monurl", monUrl);

        return "createordonnance";
    }


    // Enregistrer les données d'une ordonnance :
    @PostMapping(value = "/enregordonnance/{idcon}")
    public String enregordonnance(@PathVariable int idcon, Model model,
        @RequestParam("medicament[]") String[] medicament,
        @RequestParam("quantite[]") Integer[] quantite,
        @RequestParam("posologie[]") Integer[] posologie,
        @RequestParam("dosage[]") Integer[] dosage,
        @RequestParam("type[]") Integer[] types,
        @RequestParam(name="pharmacie", required = false) Integer pharmacie)
    {
        //
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try{

            for(int i =0; i < medicament.length; i++){
                Ordonnance ordonnance = new Ordonnance();
                ordonnance.setPrescription(medicament[i]);
                ordonnance.setQuantite(quantite[i]);
                ordonnance.setPosologie(posologie[i]);
                ordonnance.setDosage(dosage[i]);
                ordonnance.setTypemedic(types[i]);
                ordonnance.setIdcon(idcon);
                ordonnance.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                ordonnance.setIdpharm(pharmacie == null ? 0 : pharmacie);
                //
                ordonnanceRepository.save(ordonnance);
                //System.out.println("Compteur : "+i);
            }

            // Now, generate the QrCODE :

            //if(pharmacie != null) {
            //    if (pharmacie > 1) {

            Consultation consul = consultationRepository.findByIdcon(idcon);
            Patient patt = patientRepository.findByIdpat(consul.getIdpat());
            Services servs = servicesRepository.findByIdser(consul.getIdser());

            // Merge
            StringBuilder merge = new StringBuilder();
            merge.append(patt.getNom() + ' ' + patt.getPrenom());
            merge.append(" - ");
            merge.append(servs.getLibelle());

            QrCode qrCode = new QrCode();
            String img = qrCode.convertImageToTextByte(
                    qrCode.getQR(merge.toString(), 200, 200)
            );

            Qcodes qcodes = new Qcodes();
            qcodes.setIdcon(idcon);
            qcodes.setIdpharm(pharmacie == null ? 0 : pharmacie);
            qcodes.setImage(img);
            // Save it :
            qcodesRepository.save(qcodes);
        }
        catch (Exception exc){
        }

        return "redirect:/afficherordonnance/"+idcon;
    }

    // modifierordonnance
    @GetMapping(value = "/modifierordonnance/{idcon}")
    public String modifierordonnance(@PathVariable int idcon,
                                   Model model, HttpSession session)
    {
        List<Ordonnance> listOrdonnance = ordonnanceRepository.findAllByIdcon(idcon);
        model.addAttribute("idcon",idcon);
        model.addAttribute("lienmodiford",
                "/gestion/modifordonnanceup/"+idcon);
        model.addAttribute("listOrdonnance",listOrdonnance);
        // Set the SIZE :
        int taille = listOrdonnance.size() - 1;
        if(taille > 0) model.addAttribute("taille",taille);

        // Quantite
        List<Quantite> listeQuantite = quantiteRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeQuantite",listeQuantite);
        // Posologie
        List<Posologie> listePosologie = posologieRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listePosologie",listePosologie);
        // dosage
        List<Dosage> listeDosage = dosageRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeDosage",listeDosage);
        // Type
        List<Typesmedic> listeTypesmedic = typesmedicRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeTypes",listeTypesmedic);

        model.addAttribute("monurl", monUrl);

        //
        return "createordonnance";
    }


    // Enregistrer les données d'une ordonnance :
    @PostMapping(value = "/modifordonnanceup/{idcon}")
    public String modifordonnanceup(@PathVariable int idcon, Model model,
        @RequestParam("medicament[]") String[] medicament,
        @RequestParam("quantite[]") Integer[] quantite,
        @RequestParam("posologie[]") Integer[] posologie,
        @RequestParam("dosage[]") Integer[] dosage,
        @RequestParam("type[]") Integer[] types)
    {
        // Delete the previous :
        ordonnanceRepository.deleteByIdcon(idcon);
        //
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try{
            for(int i =0; i < medicament.length; i++){
                Ordonnance ordonnance = new Ordonnance();
                ordonnance.setPrescription(medicament[i]);
                ordonnance.setQuantite(quantite[i]);
                ordonnance.setPosologie(posologie[i]);
                ordonnance.setDosage(dosage[i]);
                ordonnance.setTypemedic(types[i]);
                ordonnance.setIdcon(idcon);
                ordonnance.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                //
                ordonnanceRepository.save(ordonnance);
                //System.out.println("Compteur : "+i);
            }
        }
        catch (Exception exc){
        }

        return "redirect:/afficherordonnance/"+idcon;
    }




    @GetMapping(value = "/genererqrcode/{idcon}")
    public HttpEntity<byte[]> genererqrcode(@PathVariable int idcon,
                                                Model model, HttpSession session)
            throws Exception
    {
        // Get the ORDONNANCE :
        Qcodes qcodes = qcodesRepository.findByIdcon(idcon);

        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("illustration", qcodes.getImage());

        // Now set the parameters :
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/qrcode.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=qrcode.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }






    @GetMapping(value = "/genererordonnance/{idcon}")
    public HttpEntity<byte[]> genererordonnance(@PathVariable int idcon,
        Model model, HttpSession session, Principal principal)
            throws Exception
    {

        Medecin mn = medecinRepository.findByIdentifiant(principal.getName().trim());
        List<Assignation> listeAssignation =
                assignationRepository.findAllByIdmed(mn.getIdmed());
        // Set the choice :
        int choix = 0;
        /*for(Assignation an : listeAssignation){
            Hopital hl = hopitalRepository.findByIdhop(
                    Long.valueOf(String.valueOf(an.getIdhop())));
            if(hl.getLibelle().contains("HENRIETTE")){
                choix = 1;
                break;
            }
        }
        */
        // Force it :
        choix = 1;

        // Get the ORDONNANCE :
        List<Ordonnance> listeOrdonnance = ordonnanceRepository.findAllByIdcon(idcon);

        // Get the PATIENT NAME :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());
        String nom = patient.getSexe().trim().equals("M") ? ("M. "+patient.getNom()+" "+patient.getPrenom()) :
                ("Mme/Mlle "+patient.getNom()+" "+patient.getPrenom());

        // Get the current date :
        String dates = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String tp = "Abidjan, le "+dates;

        // Set the DATA :

        List<NumMedicament> listeNumMedicament = new ArrayList<NumMedicament>();
        int compteur = 1;
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        for(Ordonnance ord : listeOrdonnance){
            nm = new NumMedicament();
            nm.setNum(String.valueOf(compteur));
            nm.setMedicament(ord.getPrescription());
            nm.setQuantite(quantiteRepository.findByIdqte(ord.getQuantite()).getLibelle());
            nm.setDosage(dosageRepository.findByIddos(ord.getDosage()).getLibelle());
            nm.setPosologie(posologieRepository.findByIdpos(ord.getPosologie()).getLibelle());
            nm.setTypes(typesmedicRepository.findByIdtyp(ord.getTypemedic()).getLibelle());
            listeNumMedicament.add(nm);
            compteur++;
        }
        //System.out.println("Taille donnees : "+donnees.length);
        //System.out.println("Taille liste : "+listeNumMedicament.size());

        // Now set the parameters :
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listeNumMedicament);
        //String nomRapport = choix ==0 ? "/reports/ordonnance.jrxml" : "/reports/ordonnancecmc.jrxml";
        String nomRapport = "/reports/ordonnanceesperance.jrxml";
        InputStream inputStream =
                this.getClass().getResourceAsStream(nomRapport);
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("nom", ("Patient : " + nom));
        parameters.put("dates", tp);
        parameters.put("itemdatasource", dataSource);
        parameters.put("iconechu", "/static/images/esperanceIcone.png");
        // Set the DOCTOR who performed the work ;
        Medecin medecin = medecinRepository.findByIdmed(consultation.getIdmed());
        parameters.put("medecin", ("Dr. " + medecin.getNom() + " " + medecin.getPrenom()));


        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ordonnance.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }



    @GetMapping(value = "/genererbulletin/{idcon}")
    public HttpEntity<byte[]> genererbulletin(@PathVariable int idcon,
        Model model, HttpSession session, Principal principal)
            throws Exception
    {
        // Get Today's date :
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        // Get Patient data :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());
        // Get the TOP anf_facturation for the patient :
        Facturation fn =
            facturationRepository.findTopByIdpat(patient.getIdpat());
        Assurance assurance = assuranceRepository.findByIdass(fn.getAssurance());
        // Get EXAMENS list :
        List<Cmdexamens> listExamens = cmdexamensRepository.findAllByIdcon(idcon);
        StringBuilder mesExamens = new StringBuilder();
        int cpt = 0;
        for(Cmdexamens cs : listExamens){
            cpt++; // incrementer
            Examen examen = examenRepository.findByIdexam(cs.getIdexam());
            mesExamens.append(examen.getLibelle());
            if(listExamens.size() == 1) mesExamens.append(" ");
            else if(cpt < listExamens.size()) mesExamens.append(", ");
        }

        // Parse the EXAMENS libelle :
        Commentaires lesexamens = new Commentaires();
        decouperTexte(mesExamens.toString(), lesexamens);
        Commentaires lescliniques = new Commentaires();
        decouperTexte(consultation.getExamenclinique(), lescliniques);

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
            new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/bulletinexamen.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        /*
        dates,nom,prenom,matriculeadherent,matriculepatient,assurance,
            societe,age,sexe,exam1,exam2,rens1,rens2,imageclinic;
         */

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dates", ("Date : "+todayDate));
        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        parameters.put("matriculeadherent", (fn != null ? fn.getNumclient() : "---"));
        parameters.put("matriculepatient", "---");
        parameters.put("assurance", assurance.getLibelle());
        parameters.put("societe", patient.getSociete());
        parameters.put("age", String.valueOf(patient.getAge()));
        parameters.put("sexe", patient.getSexe());
        parameters.put("exam1",
                lesexamens.getListeCommentaire().size() >= 1 ?
                        lesexamens.getListeCommentaire().get(0) : "" );
        parameters.put("exam2", lesexamens.getListeCommentaire().size() >= 2 ?
                lesexamens.getListeCommentaire().get(1) : "");
        parameters.put("rens1",
                lescliniques.getListeCommentaire().size() >= 1 ?
                        lescliniques.getListeCommentaire().get(0) : "" );
        parameters.put("rens2", lescliniques.getListeCommentaire().size() >= 2 ?
                lescliniques.getListeCommentaire().get(1) : "");
        parameters.put("imageclinic", "/static/images/serpentcaduce.png");

        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=bulletinexamen.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }




    @GetMapping(value = "/downloadfile/{idfic}")
    public void downloadfile(@PathVariable int idfic, HttpServletResponse response)
    {
        Fichiers fichier = fichiersRepository.findByIdfic(idfic);

        try {
            response.setHeader("Content-Disposition", "inline; filename=\"" +
                    fichier.getNomfichier()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(fichier.getTypefichier());
            response.setContentLength((int)fichier.getTaillefichier());
            //IOUtils.copy(new ByteArrayInputStream(fichier.getFichier()), out);
            out.write(fichier.getFichier());
            out.close();
        } catch (Exception e) {
            //System.out.println(e.toString());
        }

    }

    // supprimeraut
    @GetMapping(value = "/supprimeraut/{idaut}")
    public String supprimeraut(@PathVariable int idaut, Principal principal)
    {
        try {

            // Set the list :
            Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

            // Supprimer l'autorisation
            autorisationRepository.deleteByIdaut(idaut);

            /*
            sendMail("Enregistrement d'une consultation",
                    "Une nouvelle consultation a été enregistrée à "+heure+
                            " par "+med.getNom()+" "+med.getPrenom(),
                    Integer.parseInt(medecinid.toString()));
            */
        }
        catch (Exception exc){

        }
        return "redirect:/autorisation";
    }

    // Nouvelle Constante
    @GetMapping(value = "/prisedeconstante/{idpat}")
    public String prisedeconstante(@PathVariable int idpat,
        Principal principal, Model model) throws Exception{
        // get the patient
        Patient pt = patientRepository.findByIdpat(idpat);
        model.addAttribute("patient", pt);
        // Get the SERVICES
        List<Services> services = servicesRepository.findAll();
        model.addAttribute("listeservices", services);
        // Get DOCTORS
        List<String> profil = new ArrayList<>();
        profil.add("medecin");
        List<Medecin> medecin = medecinRepository.findAllByProfilIn(profil);
        model.addAttribute("listemedecin", medecin);
        model.addAttribute("monurl", monUrl);
        // Now check if PATIENT has DATA for TODAY, else it will be a
        // new CONSTANTE DATA:
        String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date dateToday = new SimpleDateFormat("yyyy-MM-dd").
            parse(dte);
        Constante cte = constanteRepository.
            findTopByIdpatAndDatesOrderByIdconsDesc(idpat, dateToday);
        int modeopeartion = 0; // 1 : Modification, 0: Ajout de constante
        if(cte != null){
            // Set the DATA :
            modeopeartion = 1;
            model.addAttribute("constante", cte);
            model.addAttribute("lienmodif",
                "/gestion/modifpriseconstante/"+cte.getIdcons());
        }
        else model.addAttribute("lienenreg","/gestion/enregpriseconstante");

        //
        model.addAttribute("modeopeartion",modeopeartion);

        return "prisedeconstante";
    }


    // Nouvelle Constante
    @GetMapping(value = "/createconstante")
    public String createconstante(Principal principal, Model model){

        // Get the ONE connected :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        EntityManager emss = emf.createEntityManager();
        emss.getTransaction().begin();

        // Get the list of PATIENT who have already taken the CONSTANTES :
        /*List<Object[]> listepatient =
                emss.createQuery("select distinct a.idpat, concat(a.nom,' ',a.prenom) as patient" +
            " from anf_patient a inner join anf_constante b " +
            "on a.idpat=b.idpat where b.idmed = "+medecin.getIdmed()+
                                " and date(b.dates)=date(now())"
                ).getResultList();*/
        // Get the list of Patients who paid the bills :
        List<Object[]> listepatient =
                emss.createQuery("select distinct c.idpat, concat(c.nom,' '," +
            "c.prenom) as patient from anf_patient c inner join anf_facturation d on " +
            "c.idpat=d.idpat where d.idhop = "+assignation.getIdhop()
            +" and datediff(now(), d.dates) <= 14").getResultList();
        // +" and date(d.dates)=date(now())").getResultList();
        /*listepatient.addAll(listepatientFacture);
        // Delete the OCCURENCE :
        Set<Object[]> noDuplicates = new HashSet<>(listepatient);
        // Créer une Nouvelle ArrayList à partir de Set
        List<Object[]> listepatientDefinitive = new ArrayList<Object[]>(noDuplicates);
        */
        //listepatient
        model.addAttribute("listepatient", listepatient);

        // Liste des professions :
        List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeprofession", listeprofession);
        model.addAttribute("creation", 0);

        // Pick the list of DOCTORS
        List<Object[]> listeDocteur =
            emss.createQuery("select a.idmed,concat(a.nom,' ',a.prenom) as medecin " +
            "from anf_medecin a inner join anf_assignation b on a.idmed=b.idmed where " +
            "b.idhop = (select b.idhop from anf_medecin a inner join anf_assignation b " +
            "on a.idmed=b.idmed where a.idmed="+medecin.getIdmed()+
            ") and a.profil='medecin'").getResultList();
        emss.getTransaction().commit();
        emss.close();

        model.addAttribute("listeDocteur", listeDocteur);
        model.addAttribute("idhop", assignation.getIdhop());
        //
        model.addAttribute("monurl", monUrl);
        return "createconstante";
    }




    // Pour ajouter un enregistrement de constante : @PostMapping(value = "/enregpriseconstante")
    @PostMapping(value = {"/enregpriseconstante", "/modifpriseconstante/{idcons}"})
    public String enregconstante(@RequestParam("idpatient") int idpatient,
        @RequestParam("poids") Double poids,
        @RequestParam("taille") Double taille,
        @RequestParam("temperature") Double temperature,
        @RequestParam("medecin") int medecinavoir,
        @RequestParam("specialite") int specialite,
        @RequestParam("pouls") int pouls,
        @RequestParam("tensionarterielle") String tensionarterielle,
        @PathVariable(name="idcons", required = false) Integer idcons,
        HttpSession session, Model model, Principal principal)
    {

        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        // Move on :
        Constante constante =
            constanteRepository.findByIdcons(idcons==null ? 0 : idcons);
        if(constante == null){
            constante = new Constante();
        }
        Date tpdate;
        try {
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            if(idcons==null) {
                // In case it's a new CONSTANTE (first)
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                constante.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                constante.setHeure(heure);
            }

            constante.setIdmed(medecin.getIdmed());
            constante.setIdpat(idpatient);
            constante.setPoids(poids);
            constante.setTaille(taille);
            constante.setTension(0.0);
            constante.setTemperature(temperature);
            constante.setIddocteur(medecinavoir);
            constante.setTensionarterielle(tensionarterielle);
            if(idcons==null) {
                constante.setIdcon(0);
            }
            constante.setIdser(specialite);
            constante.setPouls(pouls);

            // Save :
            constanteRepository.save(constante);

            // Envoyer le MAIL :
            /*sendMail("Enregistrement d'une constante",
                    "Une nouvelle constante a été enregistrée à "+heure+
                            " par "+medecin.getNom()+" "+medecin.getPrenom(),
                    medecin.getIdmed());
            */
        }
        catch (Exception exc){
            //System.out.println("Exception : "+exc.toString());
        }

        return "redirect:/afficherlesconstantes";
    }


    // modifierconstante
    @GetMapping(value = "/afficherlesconstantes")
    public String modifierconstante(Model model, HttpSession session,
        Principal principal)
    {
        // Get
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        EntityManager emss = emf.createEntityManager();
        emss.getTransaction().begin();

        List<Object[]> listeConstante =
            emss.createQuery("select distinct concat(a.nom,' ',a.prenom) as patient," +
                "date(b.dates),b.heure,b.poids,b.taille,b.tension,b.idcons,b.temperature," +
                "b.tensionarterielle,b.pouls,a.idpat from " +
                "anf_patient a inner join anf_constante b on a.idpat=b.idpat where b.idmed = "+
                medecin.getIdmed()+" and b.idcon=0 and date(b.dates)=date(now())").
                getResultList();
        emss.getTransaction().commit();
        emss.close();
        model.addAttribute("listeConstante", listeConstante);
        return "accueilconstante";
    }



    // modifierconstante
    @GetMapping(value = "/modifierconstante/{idcons}")
    public String modifierconstante(@PathVariable int idcons,
        Model model, HttpSession session, Principal principal)
    {
        // Get the values :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        // get the CONSTANTE :
        Constante cte = constanteRepository.findByIdcons(idcons);

        // Get The patient from the REQUEST ::
        EntityManager emss = emf.createEntityManager();
        emss.getTransaction().begin();
        List<Object[]> listepatient =
            emss.createQuery("select a.idpat, concat(a.nom,' ',a.prenom) as patient" +
            " from anf_patient a where a.idpat = "+ cte.getIdpat()).getResultList();
        //emss.getTransaction().commit();
        //emss.close();
        model.addAttribute("listepatient", listepatient);

        // Liste des professions :
        List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeprofession", listeprofession);

        // Get the PATIENT from the CONSTANTE object :
        Patient patient = patientRepository.findByIdpat(cte.getIdpat());
        model.addAttribute("patient", patient);
        model.addAttribute("constante", cte);

        model.addAttribute("constanteid",
                "/gestion/modifconstante/"+String.valueOf(idcons));

        // Pick the list of DOCTORS
        // Get the MEDECIN :
        //Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        List<Object[]> listeDocteur =
                emss.createQuery("select a.idmed,concat(a.nom,' ',a.prenom) as medecin " +
                        "from anf_medecin a inner join anf_assignation b on a.idmed=b.idmed where " +
                        "b.idhop = (select b.idhop from anf_medecin a inner join anf_assignation b " +
                        "on a.idmed=b.idmed where a.idmed="+medecin.getIdmed()+
                        ") and a.profil='medecin'").getResultList();
        emss.getTransaction().commit();
        emss.close();

        model.addAttribute("listeDocteur", listeDocteur);
        model.addAttribute("idhop", assignation.getIdhop());
        //
        model.addAttribute("monurl", monUrl);
        return "createconstante";
    }



    // Pour ajouter un enregistrement de constante :
    @PostMapping(value = "/modifconstante/{idcons}")
    public String modifconstante(@RequestParam("idpatient") int idpatient,
        @RequestParam("age") int age,
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("profession") int profession,
        @RequestParam("sexe") String sexe,
        @RequestParam("poids") Double poids,
        @RequestParam("taille") Double taille,
        @RequestParam("tension") Double tension,
        @RequestParam("medecinavoir") int medecinavoir,
        @RequestParam("tensionarterielle") String tensionarterielle,
        @RequestParam("temperature") Double temperature,
        @PathVariable int idcons,
        HttpSession session, Model model, Principal principal)
    {
        //
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // Get The CONSTANTE :
        Constante constante = constanteRepository.findByIdcons(idcons);

        // Get the Patient :
        Patient patient = patientRepository.findByIdpat(constante.getIdpat());
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setSexe(sexe);
        patient.setAge(age);
        patient.setProfession(profession);
        //
        patientRepository.save(patient);

        // Move on :
        Date tpdate;
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            constante.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            constante.setHeure(heure);

            constante.setIdmed(medecin.getIdmed());
            constante.setIdpat(idpatient);
            constante.setPoids(poids);
            constante.setTaille(taille);
            constante.setTension(tension);
            constante.setIddocteur(medecinavoir);
            constante.setTemperature(temperature);
            constante.setTensionarterielle(tensionarterielle);
            // Save :
            constanteRepository.save(constante);
        }
        catch (Exception exc){
            //System.out.println("Exception : "+exc.toString());
        }

        return "redirect:/connexpatient";
    }


    @GetMapping(value="/patientexterne")
    public String getconnextaxe(Principal principal, Model model){
        //
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        // Get all the AUTHORIZATION :
        List<Autorisation> listeAutorisation =
                autorisationRepository.findAllByIdmeddestin(medecin.getIdmed());
        // Set the filter :
        String filtre = "(";
        for (int i = 0; i < listeAutorisation.size(); i++){
            if(i==0 && listeAutorisation.size()==1){
                filtre += listeAutorisation.get(i).getIdpat()+")";
            }
            else if(i==0 && listeAutorisation.size()>1){
                filtre += listeAutorisation.get(i).getIdpat()+",";
            }
            else if(i==(listeAutorisation.size()-1)){
                filtre += listeAutorisation.get(i).getIdpat()+")";
            }
            else if(i<(listeAutorisation.size()-1)){
                filtre += listeAutorisation.get(i).getIdpat()+",";
            }
        }

        //
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if(listeAutorisation.size() > 0) {
            List<Object[]> listePatientAuto =
                    em.createQuery("select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat," +
                            "c.libelle from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                            "inner join anf_hopital c on c.idhop=b.idhop where a.idpat in " +
                            filtre).getResultList();

            model.addAttribute("listePatientEx", listePatientAuto);
        }

        //
        em.getTransaction().commit();
        em.close();

        return "patientexterne";
    }


    public void retrouverEspace(int fin, Commentaires texte, Commentaires comment){
        int debut = fin - 1;
        String ligne = texte.getCommentaire().substring(debut,fin);
        //System.out.println("Ligne : "+ligne);
        if(!ligne.equals(" ")) retrouverEspace(debut, texte, comment);
        else comment.setLimiteCaractere(debut);
    }


    public void decouperTexte(String commentaire, Commentaires liste, int... limite){

        // Set it
        int laLimite = limite.length > 0 ? limite[0] : 99;

        if(commentaire.length() > laLimite){

            Commentaires cLigne = new Commentaires();
            cLigne.setCommentaire(commentaire.substring(0,laLimite-1));
            //System.out.println("Commentaire : "+cLigne.getCommentaire());

            String precedent = commentaire.substring(laLimite-2,laLimite-1); //
            String suivant = commentaire.substring(laLimite-1,laLimite); //
            //int limiteCaractere = 0;
            Commentaires cLimiteCaractere = new Commentaires();
            cLimiteCaractere.setLimiteCaractere(0);
            if((!precedent.equals(" ")) && (!suivant.equals(" "))){
                retrouverEspace(laLimite-1, cLigne, cLimiteCaractere);
                cLigne.setCommentaire(commentaire.substring(0,
                        cLimiteCaractere.getLimiteCaractere()).trim());
            }

            //
            liste.getListeCommentaire().add(cLigne.getCommentaire());
            int position = cLimiteCaractere.getLimiteCaractere() > 0 ?
                    cLimiteCaractere.getLimiteCaractere() : laLimite-1;
            String commentaires = commentaire.substring(position);

            // -----------
            decouperTexte(commentaires, liste);
        }
        else{
            // -----------
            liste.getListeCommentaire().add(commentaire);
        }
    }


    // Gestion de l'ICONE
    @GetMapping(value = "/gesttamponmedecin")
    public String gesttamponmedecin(Principal principal, Model model){
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        List<Tampons> liste = tamponsRepository.findAllByIdmed(medecin.getIdmed());
        model.addAttribute("liste", liste);
        return "tamponmedecin"; // tamponmedecin
    }


    // Creer CACHET :
    @PostMapping(value = "/creercachet")
    public String creercachet(@RequestParam("cachet") MultipartFile cachet,
        Principal principal){
        //
        Medecin medecin =
            medecinRepository.findByIdentifiant(principal.getName().trim());
        if(cachet != null) {
            if (!cachet.isEmpty()) {
                try {
                    String getCachet =
                        Base64.getEncoder().encodeToString(cachet.getBytes());
                    Tampons tampons = new Tampons();
                    tampons.setIdmed(medecin.getIdmed());
                    tampons.setTampon(getCachet);
                    tamponsRepository.save(tampons);
                }
                catch (Exception exc){
                }
            }
        }
        //
        return "redirect:/gesttamponmedecin";
    }





    // Nouveau PATIENT
    @GetMapping(value = "/createpatients")
    public String createpatients(Principal principal, Model model){

        // Get the ONE connected :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        // Liste des professions :
        List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeProf", listeprofession);

        // Liste des groupe sanguins
        List<Groupesanguin> listeSanguin = groupesanguinRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeSanguin", listeSanguin);

        // Liste des Assurances
        List<Assurance> listeassurance = assuranceRepository.findAll();
        model.addAttribute("listeassurance", listeassurance);

        String datecourante = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        model.addAttribute("currentdate", datecourante);

        //
        model.addAttribute("monurl", monUrl);
        return "createpatients";
    }


    // MODIFIER PATIENT
    @GetMapping(value = "/modifierpatinfo/{idpat}")
    public String modifierpatinfo(@PathVariable int idpat,
        Principal principal, Model model){

        // Get the ONE connected :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        Assignation assignation = assignationRepository.findByIdmed(medecin.getIdmed());

        // Liste des professions :
        List<Profession> listeprofession = professionRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeProf", listeprofession);

        // Liste des groupe sanguins
        List<Groupesanguin> listeSanguin = groupesanguinRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeSanguin", listeSanguin);

        // Liste des Assurances
        List<Assurance> listeassurance = assuranceRepository.findAll();
        model.addAttribute("listeassurance", listeassurance);

        // --------------------
        Patient patient = patientRepository.findByIdpat(idpat);
        model.addAttribute("patient", patient);
        // For date :
        String dte = new SimpleDateFormat("MM/dd/yyyy").format(
            patient.getDatenaissance());
        model.addAttribute("datenaissance", dte);
        // Pour la date de creation :
        String dteCreation = new SimpleDateFormat("MM/dd/yyyy").format(
                patient.getDatecreation());
        model.addAttribute("datecreation", dteCreation);
        //
        Histoassurance he =
            histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(idpat);
        model.addAttribute("histoassurance", he);
        // Modification Line
        model.addAttribute("modification",
                "/gestion/modificationpatinfo/"+idpat);

        model.addAttribute("monurl", monUrl);
        return "createpatients";
    }


    // Enregistrer un nouveau PATIENT
    @PostMapping(value = "/enregistrepatient")
    public String enregsouscription(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("datenaissance") String datenaissance,
        @RequestParam("datecreation") String datecreation,
        @RequestParam("sexe") String sexe,
        @RequestParam("profession") int profession,
        @RequestParam("saisieprof") String saisieprof,
        @RequestParam("residence") String residence,
        @RequestParam("telephone") String telephone,
        @RequestParam("pere") String pere,
        @RequestParam("contactpere") String contactpere,
        @RequestParam("mere") String mere,
        @RequestParam("contactmere") String contactmere,
        @RequestParam("repondant") String repondant,
        @RequestParam("contactrepondant") String contactrepondant,
        @RequestParam("groupesanguin") int groupesanguin,
        @RequestParam("assurance") int assurance,
        @RequestParam("matricule") String matricule,
        @RequestParam("sociale") String sociale,
        @RequestParam("societe") String societe,
        @RequestParam("numdossier") String numdossier,
        @RequestParam("particulier") int particulier,
        Principal principal, Model model){

        //
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        try {
            Date dateNaiss = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaissance));
            patient.setDatenaissance(dateNaiss);
            //-----------------------------------------------------------------
            Date dateCreation = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datecreation));
            patient.setDatecreation(dateCreation);

            //System.out.println("Date naissance: "+patient.getDatenaissance());
            String dte = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Date dateAujourdhui = new SimpleDateFormat("yyyy-MM-dd").
                    parse(dte);
            long diffInMillies = Math.abs(dateAujourdhui.getTime() - dateNaiss.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            // Now in YEARS :
            int annee = (int)(diff / 365);
            patient.setAge(annee);
            //patient.setDatecreation(dateAujourdhui);
        }
        catch (Exception exc){}

        patient.setSexe(sexe);
        // If the profession does not exist create it :
        if(saisieprof.trim().length() > 0){
            Profession pn = new Profession();
            pn.setLibelle(saisieprof.trim());
            patient.setProfession(professionRepository.save(pn).getIdprof());
        }
        else patient.setProfession(profession);

        patient.setTelephone(telephone.trim());
        patient.setResidence(residence.trim());
        patient.setNompere(pere.trim());
        patient.setContactpere(contactpere.trim());
        patient.setNommere(mere.trim());
        patient.setContactmere(contactmere.trim());
        patient.setRepondant(repondant.trim());
        patient.setContactrepondant(contactrepondant.trim());
        patient.setGroupesanguin(groupesanguin);
        // Les champs VIDES --- :
        patient.setAdresse("");
        String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
        date = date.replaceAll("-","").replaceAll(":","")
                .replaceAll(" ","");
        if(numdossier.trim().length() == 0) {
            try {
                // Identifiant UNIQUE :
                String identifiantUnique =
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String[] lesIds = identifiantUnique.split("-");
                // Get value from anf-compteur :
                Date dateToday = new SimpleDateFormat("yyyy-MM-dd").
                        parse(identifiantUnique);
                Compteur cpr = compteurRepository.findByDates(dateToday);
                if (cpr == null) {
                    cpr = new Compteur();
                    cpr.setDates(dateToday);
                    cpr.setValeur(1);
                    compteurRepository.save(cpr);
                    String finalId = lesIds[0].substring(2) + lesIds[1] +
                            lesIds[2] + "1";
                    patient.setIdentifiant(finalId);
                } else {
                    int cptrs = cpr.getIdcpr();
                    cptrs++;
                    String finalId = lesIds[0].substring(2) + lesIds[1] +
                            lesIds[2] + String.valueOf(cptrs);
                    patient.setIdentifiant(finalId);
                    cpr.setValeur(cptrs);
                    compteurRepository.save(cpr);
                }
            } catch (Exception exc) {
                patient.setIdentifiant(date);
            }
        }
        else{
            patient.setIdentifiant(numdossier.trim());
        }

        patient.setCni("");
        patient.setEmail("");
        patient.setProvenance(0);
        patient.setHaswallet(0);
        patient.setSociete("");
        patient.setNomjeunefille("");
        patient.setLieunaissance("");
        int idPat = patientRepository.save(patient).getIdpat();
        // Save this GUY
        AnfCustomer customer = new AnfCustomer();
        customer.setNom(nom+" "+prenom);
        customerRepository.save(customer);

        //
        Histoassurance histoassurance = new Histoassurance();
        Assurance ass = assuranceRepository.findByIdass(assurance);
        histoassurance.setAssurance(ass.getLibelle());
        histoassurance.setIdpat(idPat);
        histoassurance.setMatricule(matricule.trim());
        histoassurance.setParticulier(particulier);
        histoassurance.setSociale(sociale.trim());
        histoassurance.setSociete(societe);
        // -- Save :
        histoassuranceRepository.save(histoassurance);
        //
        return "redirect:/getlistepatients";
    }


    // MOdifier les données d'un PATIENT
    @PostMapping(value = "/modificationpatinfo/{idpat}")
    public String modificationpatinfo(
            @PathVariable int idpat,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("datenaissance") String datenaissance,
            @RequestParam("datecreation") String datecreation,
            @RequestParam("sexe") String sexe,
            @RequestParam("profession") int profession,
            @RequestParam("saisieprof") String saisieprof,
            @RequestParam("residence") String residence,
            @RequestParam("telephone") String telephone,
            @RequestParam("pere") String pere,
            @RequestParam("contactpere") String contactpere,
            @RequestParam("mere") String mere,
            @RequestParam("contactmere") String contactmere,
            @RequestParam("repondant") String repondant,
            @RequestParam("contactrepondant") String contactrepondant,
            @RequestParam("groupesanguin") int groupesanguin,
            @RequestParam("assurance") int assurance,
            @RequestParam("matricule") String matricule,
            @RequestParam("sociale") String sociale,
            @RequestParam("societe") String societe,
            @RequestParam("particulier") int particulier,
            @RequestParam("numdossier") String numdossier,
            Principal principal, Model model){

        //
        Patient patient = patientRepository.findByIdpat(idpat);
        patient.setNom(nom);
        patient.setPrenom(prenom);
        try {
            Date dateNaiss = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaissance));
            patient.setDatenaissance(dateNaiss);
            //
            Date dateCreation = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datecreation));
            patient.setDatecreation(dateCreation);

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

        patient.setSexe(sexe);
        // If the profession does not exist create it :
        if(saisieprof.trim().length() > 0){
            Profession pn = new Profession();
            pn.setLibelle(saisieprof.trim());
            patient.setProfession(professionRepository.save(pn).getIdprof());
        }
        else patient.setProfession(profession);

        patient.setTelephone(telephone.trim());
        patient.setResidence(residence.trim());
        patient.setNompere(pere.trim());
        patient.setContactpere(contactpere.trim());
        patient.setNommere(mere.trim());
        patient.setContactmere(contactmere.trim());
        patient.setRepondant(repondant.trim());
        patient.setContactrepondant(contactrepondant.trim());
        patient.setGroupesanguin(groupesanguin);
        // Les champs VIDES :
        patient.setAdresse("");
        String date = new SimpleDateFormat("yyyy-MM HH:mm").format(new Date());
        date = date.replaceAll("-","").replaceAll(":","")
                .replaceAll(" ","");
        patient.setIdentifiant(numdossier);
        patient.setCni("");
        patient.setEmail("");
        patient.setProvenance(0);
        patient.setHaswallet(0);
        patient.setSociete("");
        patient.setNomjeunefille("");
        patient.setLieunaissance("");
        patientRepository.save(patient);
        //
        Histoassurance histoassurance =
            histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(idpat);
        Assurance ass = assuranceRepository.findByIdass(assurance);
        histoassurance.setAssurance(ass.getLibelle());
        histoassurance.setIdpat(idpat);
        histoassurance.setMatricule(matricule.trim());
        histoassurance.setParticulier(particulier);
        histoassurance.setSociale(sociale.trim());
        histoassurance.setSociete(societe.trim());
        // -- Save :
        histoassuranceRepository.save(histoassurance);
        //
        return "redirect:/getlistepatients";
    }


    // Tableau de bord : dashboard
    @GetMapping(value = "/getlistepatients")
    public String getlistepatients(Model model, HttpSession session, Principal principal)
    {
        //Object medecinid = session.getAttribute("medecinid");
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        /****/
        StoredProcedureQuery procedureQuery = emr
                .createStoredProcedureQuery("findAllPatients");
        procedureQuery.registerStoredProcedureParameter("motdepasse",
                String.class, ParameterMode.IN);
        procedureQuery.setParameter("motdepasse", "K8_jemange");
        List<Object[]> resultat = procedureQuery.getResultList();
        //System.out.println("Taille resultat : "+resultat.size());
        /****/

        /* Get PATIENT created today */
        StoredProcedureQuery procedureToday = emr
                .createStoredProcedureQuery("findAllPatientsToday");
        procedureToday.registerStoredProcedureParameter("motdepasse",
                String.class, ParameterMode.IN);
        procedureToday.setParameter("motdepasse", "K8_jemange");
        List<Object[]> resultatToday = procedureToday.getResultList();

        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listepatient", resultat);
        model.addAttribute("listepatientToday", resultatToday);
        model.addAttribute("monurl", monUrl);
        return "listedespatients";
    }



    // Liste des patients Hospitalises
    @GetMapping(value = "/getlistepatientshosp")
    public String getlistepatientshosp(Model model, HttpSession session, Principal principal)
    {
        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> liste =
            emr.createQuery("select distinct concat(a.nom,' ',a.prenom) as patient,a.age,a.sexe, " +
                "concat(d.nom,' ',d.prenom) as medecin, date(b.dateconsultation),date(c.datentree)," +
                "c.heure,c.nbrejour,b.idcon from anf_patient a inner join anf_consultation b on a.idpat=b.idpat " +
                "inner join anf_hospitalisation c on c.idcon=b.idcon inner join anf_medecin d on b.idmed=" +
                    "d.idmed").getResultList();
        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listepatient", liste);
        model.addAttribute("monurl", monUrl);
        return "listepatienshosp";
    }


    // Liste des examens demandés
    @GetMapping(value = "/getlistepatientsexam")
    public String getlistepatientsexam(Model model, HttpSession session, Principal principal)
    {
        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> liste =
            emr.createQuery("select distinct concat(a.nom,' ',a.prenom),a.age,a.sexe, concat(d.nom,' ',d.prenom)," +
                "date(b.dateconsultation),b.idcon from  anf_patient a inner join anf_consultation b on " +
                "a.idpat=b.idpat inner join anf_cmdexamens c on c.idcon=b.idcon inner join anf_medecin d on " +
                "d.idmed=b.idmed").getResultList();
        emr.getTransaction().commit();
        emr.close();
        //
        model.addAttribute("listepatient", liste);
        model.addAttribute("monurl", monUrl);
        return "listepatiensexam";
    }

}
