package com.maternite.gestion.controllers;

import com.ibm.icu.impl.LocaleDisplayNamesImpl;
import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.TachesService;
import com.maternite.gestion.repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WelcomeController {

    // Attribute :
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    DeclarationRepository declarationRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    TaxesRepository taxesRepository;
    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    MarcheRepository marcheRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    NationaliteRepository nationaliteRepository;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    AutorisationRepository autorisationRepository;
    @Autowired
    TachesService tachesService;

    @PersistenceUnit
    EntityManagerFactory emf;
    @Autowired
    private EntityManager entityManager;

    @Value("${mon.lienlocal}")
    private String monUrl;



    @GetMapping(value = "/accueil")
    public String accueil(Model model)
    {
        return "hello";
    }

    @GetMapping(value="/eleve")
    public String nom(){
        return "Bonjour";
    }


    @GetMapping(value="/rapport")
    public void rapport(HttpServletResponse response) throws  Exception{
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hopitalRepository.findAll());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/hopital.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }


    @GetMapping(value="/apps")
    public String getapps(){
        return "accueil";
    }

    @GetMapping(value="/acte")
    public String getconnexacte(){
        return "connexacte";
    }

    @GetMapping(value="/patient")
    public String getconnexpatient(){
        return "connexpatient";
    }

    @GetMapping(value="/taxe")
    public String getconnextaxe(HttpSession session){
        // Session :
        session.setAttribute("taxe", "taxe");
        return "connexacte";
    }

    // Se connexter au PATIENT :
    @GetMapping(value="/connexpatient")
    public String setconnexionpatient(Principal principal,
                                      HttpSession session, Model model){

        //tachesService.suggestionRdvAutomatique(1);
        // CHECK here :
        /*EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> listeTest =
                emr.createStoredProcedureQuery(
                        "findAllMedecinsServices(1)").getResultList();
        //----
        StoredProcedureQuery procedureQuery = entityManager
                .createStoredProcedureQuery("findAllMedecinsServices");
        procedureQuery.registerStoredProcedureParameter("idmd",
                Integer.class, ParameterMode.IN);
        procedureQuery.setParameter("idmd", 0);
        List<Object[]> listeTest = procedureQuery.getResultList();
        System.out.println("Taille : "+listeTest.size());
        for(Object[] donnee : listeTest){
            System.out.println("Medecin : "+donnee[0]+"    --->   Service : "+
            donnee[1]);
        }
        emr.getTransaction().commit();
        emr.close();
        */

        if(principal == null || session == null ) return "redirect:/login";

        String pageRetour = "";
        // Check  :
        Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());
        //System.out.println("Profil : "+medecin.getProfil());
        switch (medecin.getProfil().toLowerCase().trim()){
            case "medecin":
                pageRetour = "accueilpatient";

                // Set :
                session.setAttribute("medecinid", medecin.getIdmed());

                // Get
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                //
                List<Object[]> listePatient = em.createQuery(
                    "select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle, " +
                    "(select max(date(e.dates)) from anf_consultation e where e.idpat=a.idpat) as " +
                    "dte from anf_patient a inner join anf_consultation b on a.idpat=b.idpat inner " +
                    "join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on d.idhop=b.idhop " +
                    "where c.idmed = " + medecin.getIdmed()).getResultList();
                // Pass it to the MODEl :
                model.addAttribute("listePatient", listePatient);

                // Now run query to get the PATIENT registred today :
                List<Object[]> listePatientToday = em.createQuery(
                    "select distinct a.nom,a.prenom,a.telephone,a.email,a.sexe,a.idpat,d.libelle" +
                    " from anf_patient a inner join anf_consultation b on a.idpat=b.idpat inner " +
                    "join anf_medecin c on b.idmed=c.idmed inner join anf_hopital d on d.idhop=b.idhop " +
                    "where date(b.dates)=date(now()) and c.idmed = " + medecin.getIdmed()).getResultList();
                // Pass it to the MODEl :
                model.addAttribute("listePatientToday", listePatientToday);

                // le nombre de consultation :
                long nbreconsultation = 0;
                try {
                    Object nbre_consultation = em.createQuery(
                            "select count(b.idcon) from anf_medecin a inner join " +
                                    "anf_consultation b on a.idmed=b.idmed " +
                                    "where a.idmed = " + medecin.getIdmed() +
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
                                    "where a.idmed = " + medecin.getIdmed() +
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
                                    "where a.idmed = " + medecin.getIdmed() +
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
                                    "where a.idmed = " + medecin.getIdmed() +
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
                        "anf_consultation a where a.idmed="+medecin.getIdmed()+
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
                                    "anf_consultation a where a.idmed="+medecin.getIdmed()+
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
                                    "anf_consultation a where a.idmed="+medecin.getIdmed()+
                                    " and a.hypertension=1 ").getSingleResult();
                    nbreHypertension = (Long) nbre;
                }
                catch (Exception exc){}
                model.addAttribute("nbreHypertension", nbreHypertension);


                em.getTransaction().commit();
                em.close();
                break;

            case "admin":
                pageRetour = "tabdashadmin";
                // Get
                EntityManager ema = emf.createEntityManager();
                ema.getTransaction().begin();

                long nbrepatientadmin = 0;
                try {
                    Object nbre_patientadmin = ema.createQuery(
                        "select count(distinct a.idpat) from anf_consultation a inner join anf_affectation b " +
                            "on a.idhop=b.idhop where b.idmed = " + medecin.getIdmed()).getSingleResult();
                    nbrepatientadmin = (Long) nbre_patientadmin;
                }
                catch (Exception exc){
                    nbrepatientadmin = 0;
                }

                // MEDECIN
                long nbremedecinadmin = 0;
                try {
                    Object nbre_medecinadmin = ema.createQuery(
                            "select count(distinct a.idmed) from anf_consultation a inner join anf_affectation b " +
                                    "on a.idhop=b.idhop where b.idmed = " + medecin.getIdmed()).getSingleResult();
                    nbremedecinadmin = (Long) nbre_medecinadmin;
                }
                catch (Exception exc){
                    nbremedecinadmin = 0;
                }

                // CONSULTATION
                long nbreconsuladmin = 0;
                try {
                    Object nbre_consuladmin = ema.createQuery(
                            "select count(a.idcon) from anf_consultation a inner join anf_affectation b " +
                                    "on a.idhop=b.idhop where b.idmed = " + medecin.getIdmed()).getSingleResult();
                    nbreconsuladmin = (Long) nbre_consuladmin;
                }
                catch (Exception exc){
                    nbreconsuladmin = 0;
                }


                ema.getTransaction().commit();
                ema.close();

                model.addAttribute("nbrepatientadmin", nbrepatientadmin);
                model.addAttribute("nbremedecinadmin", nbremedecinadmin);
                model.addAttribute("nbreconsuladmin", nbreconsuladmin);
                model.addAttribute("medecinid", medecin.getIdmed());
                model.addAttribute("monurl", monUrl);
                break;


            case "secretaire":
            case "aidesoignante":
                pageRetour = "redirect:/getlistepatients";
                /*
                // Get
                EntityManager emss = emf.createEntityManager();
                emss.getTransaction().begin();

                List<Object[]> listeConstante =
                    emss.createQuery("select distinct concat(a.nom,' ',a.prenom) as patient," +
                    "date(b.dates),b.heure,b.poids,b.taille,b.tension,b.idcons,b.temperature,b.tensionarterielle from " +
                    "anf_patient a inner join anf_constante b on a.idpat=b.idpat where b.idmed = "+
                            medecin.getIdmed()+" and b.idcon=0 and date(b.dates)=date(now())").
                            getResultList();
                emss.getTransaction().commit();
                emss.close();
                model.addAttribute("listeConstante", listeConstante);
                */
                break;


            case "superadmin":
                pageRetour = "dashspradmin";
                // Get
                EntityManager ems = emf.createEntityManager();
                ems.getTransaction().begin();

                List<Object[]> listeHabilitation =
                        ems.createQuery("select distinct concat(a.nom,' ',a.prenom) as medecins," +
                        "c.libelle,b.idaff from anf_medecin a inner join anf_affectation b " +
                        "on a.idmed=b.idmed inner join anf_hopital c on b.idhop=c.idhop ").getResultList();
                ems.getTransaction().commit();
                ems.close();

                model.addAttribute("listeHabilitation", listeHabilitation);

                // Get MEDECIN :
                List<Medecin> listeMedecin = medecinRepository.findAllByProfil("admin");
                model.addAttribute("listeMedecin", listeMedecin);
                //System.out.println("Taille des ADMIN : "+listeMedecin.size());

                // Get HOPITAL :
                List<Hopital> listeHopital = hopitalRepository.findAll();
                model.addAttribute("listeHopital", listeHopital);

                break;

            case "secretairemedicale":
            case "infirmier":
                // /accfacturation
                //pageRetour = "accueilprofile";
                pageRetour = "redirect:/accfacturation";
                break;

            case "laborantin":
                pageRetour = "redirect:/acclaboratoire";
                break;

            case "pharmacien":
                pageRetour = "redirect:/accpharmacie";
                break;

            case "gestionnaire":
                pageRetour = "redirect:/accgestionnaire";
                break;
        }

        //if(medecin!=null){
        //}
        //else return "redirect:/patient";
        return pageRetour;
    }


    @PostMapping(value="/connexacte")
    public String setconnexion(@RequestParam("username") String username,
                               @RequestParam("userpassword") String userpassword,
                               HttpSession session, Model model){

        String pageRetour = "accueilacte";

        // Check if TAXE has been choosen :
        Object getSessTaxe = session.getAttribute("taxe");
        if(getSessTaxe==null) {
            // Try to know if the user is already connected :  getConnex
            Object getSessUserid = session.getAttribute("userid");
            String getConnex = "";

            if (getSessUserid == null) {
                // Now check with the database if the CREDENTIALS are good :
                Users user = usersRepository.findByIdentifiantAndPassword(username, userpassword);
                if (user != null) {

                    // Save the USER ID in SESSION :
                    session.setAttribute("userid", user.getIdusr());
                    getConnex = String.valueOf(user.getIdusr());

                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();
                    List<Object[]> results = em.createQuery(
                            "SELECT a.code,b.nom FROM " +
                                    "anf_userprofile a JOIN anf_users b on a.idprof=b.idprof " +
                                    "where b.idusr= " + user.getIdusr()).getResultList();

                    for (Object[] result : results) {
                        model.addAttribute("nom", result[0]);
                        model.addAttribute("contact", result[1]);
                        break;
                    }

                    em.getTransaction().commit();
                    em.close();
                }
                else{
                    // Send user to FIRST PAGE :
                    session.invalidate();
                    return "redirect:/apps";
                }
            } else getConnex = getSessUserid.toString();

            // READ DATA from anf_users and anf_declaration
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> resultDeclaration = em.createQuery(
                    "SELECT b.nombebe,b.prenbebe,b.dates,b.heure," +
                            "b.creation,b.sexe,b.iddec,b.etat FROM " +
                            "anf_users a JOIN anf_declaration b on a.idusr=b.idusr " +
                            "where a.idusr= " + getConnex).getResultList();

            // Pass it to the MODEl :
            model.addAttribute("declarations", resultDeclaration);

            // Pour les declarations validees :
            /*List<Object[]> resultDeclarationVal = em.createQuery(
                    "SELECT b.nombebe,b.prenbebe,b.dates,b.heure," +
                            "b.creation,b.sexe,b.iddec,b.etat FROM " +
                            "anf_users a JOIN anf_declaration b on a.idusr=b.idusr " +
                            "where a.idusr= " + getConnex+" and b.etat=1").getResultList();
            model.addAttribute("declarationsval", resultDeclarationVal);
            */

            List<Declaration> listeDeclaration = declarationRepository.findAll();
            List<Commune> listeCommune = communeRepository.findAll();
            List<Hopital> listeHopital = hopitalRepository.findAll();
            List<Users> listeUsers = usersRepository.findAll();
            model.addAttribute("nbredeclarations", listeDeclaration.size());
            model.addAttribute("nbrecommunes", listeCommune.size());
            model.addAttribute("nbrehopital", listeHopital.size());
            model.addAttribute("nbreusers", listeUsers.size());

            // Close :
            em.getTransaction().commit();
            em.close();
        }
        else{
            // Try to login with AGENT credentials :
            //System.out.println("Object getSessTaxe NO NULL ");
            Agent agent = agentRepository.findByIdentifiantAndPassword(username, userpassword);
            if(agent!=null){
                // Just display the TRADERS who belong to the same market than the AGENTs :
                //System.out.println("Agent trouvé ");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                List<Object[]> resultVendeuse = em.createQuery(
                "select d.nom,d.contact,d.pieceidentite ,c.libelle,d.idven " +
                   "from anf_agent a inner join anf_commune b on a.idcom=b.idcom inner join anf_marche " +
                   "c on b.idcom=c.idcom " +
                   "inner join anf_vendeuse d on c.idmar=d.idmar where a.idcom = " +
                        agent.getIdcom()).getResultList();

                // Pass it to the MODEl :
                model.addAttribute("vendeuses", resultVendeuse);
                session.setAttribute("agentid", agent.getIdage());
                session.setAttribute("agentidcom", agent.getIdcom());

                // Get the number of taxes :
                List<Taxes> listetaxes = taxesRepository.findAll();
                List<Vendeuse> listevendeuse = vendeuseRepository.findAll();
                List<Agent> listeagent = agentRepository.findAll();
                List<Marche> listemarche = marcheRepository.findAll();
                model.addAttribute("nbrevendeuses", listevendeuse.size());
                model.addAttribute("nbretaxes", listetaxes.size());
                model.addAttribute("nbreagent", listeagent.size());
                model.addAttribute("nbremarche", listemarche.size());

                pageRetour = "accueiltaxe";

                //
                em.getTransaction().commit();
                em.close();
            }
            else{
                // Send user to FIRST PAGE :
                //System.out.println("Agent null ");
                session.invalidate();
                return "redirect:/apps";
            }
        }

        return pageRetour;
        //return "retour";
    }


    // Read ville :
    @GetMapping(value="/lireville")
    public String lireVille(Model model){

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Object[]> results = em.createQuery(
                "SELECT a.libelle, b.libelle FROM " +
                        "anf_commune a JOIN anf_hopital b on a.idcom=b.idcom ").getResultList();

        for (Object[] result : results) {
            model.addAttribute("nom",result[0]);
            model.addAttribute("contact",result[1]);
        }

        em.getTransaction().commit();
        em.close();

        return "retour";
    }

    // Reach DECLARATION page :
    @GetMapping(value="/createdeclaration")
    public String reachdeclaration(Model model){

        List<Commune> listeCommune = communeRepository.findAll();
        model.addAttribute("lstcommune", listeCommune);
        // Nationalite :
        List<Nationalite> listeNationalite = nationaliteRepository.findAll();
        model.addAttribute("lstnation", listeNationalite);
        return "declaration";
    }


    // Try to save the DATA
    @PostMapping(value="/enregbebe")
    public String savedeclaration(@RequestParam("nombebe") String nombebe,
                                  @RequestParam("prenombebe") String prenombebe,
                                  @RequestParam("pere") String pere,
                                  @RequestParam("nationpere") String nationpere,
                                  @RequestParam("mere") String mere,
                                  @RequestParam("nationmere") String nationmere,
                                  @RequestParam("datenaiss") String datenaiss,
                                  @RequestParam("heurenaiss") String heurenaiss,
                                  @RequestParam("sexe") String sexe,

                                  @RequestParam("lieunaissance") String lieunaissance,
                                  @RequestParam("datenaisspere") String datenaisspere,
                                  @RequestParam("villenaisspere") String villenaisspere,
                                  @RequestParam("professionpere") String professionpere,
                                  @RequestParam("domicilepere") String domicilepere,
                                  @RequestParam("datenaissmere") String datenaissmere,
                                  @RequestParam("villenaissmere") String villenaissmere,
                                  @RequestParam("professionmere") String professionmere,
                                  @RequestParam("domicilemere") String domicilemere,
                                  @RequestParam("declarationde") String declarationde,
                                  @RequestParam("recuelangue") String recuelangue,
                                  @RequestParam("assistancede") String assistancede,

                                  Model model,
                                  HttpSession session){
        //declarationRepository
        Declaration dcl = new Declaration();
        dcl.setNombebe(nombebe);
        dcl.setPrenbebe(prenombebe);
        dcl.setPere(pere);
        dcl.setNationperer(Integer.parseInt(nationpere));
        dcl.setMere(mere);
        dcl.setNationmere(Integer.parseInt(nationmere));

        Date tpdate;
        try {
            tpdate = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaiss));
            dcl.setDates(tpdate);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            dcl.setCreation(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    parse(date));
            // datenaisspere
            tpdate = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaisspere));
            dcl.setDatenaisspere(tpdate);
            // datenaissmere
            tpdate = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(datenaissmere));
            dcl.setDatenaissmere(tpdate);
        }
        catch (Exception exc){
        }

        // Info PERE :
        dcl.setDomicilepere(domicilepere);
        dcl.setVillenaisspere(Integer.parseInt(villenaisspere));
        dcl.setProfessionpere(Integer.parseInt(professionpere));

        // Info MERE :
        dcl.setDomicilemere(domicilemere);
        dcl.setVillenaissmere(Integer.parseInt(villenaissmere));
        dcl.setProfessionmere(Integer.parseInt(professionmere));

        // Autres INFORMATIONS :
        dcl.setDeclarationde(declarationde);
        dcl.setRecuelangue(Integer.parseInt(recuelangue));
        dcl.setAssistancede(assistancede);

        // Autres INFORMATIONS :
        dcl.setHeure(heurenaiss);
        dcl.setEtat(0);
        dcl.setSexe(Integer.parseInt(sexe));
        dcl.setLieunaissance(Integer.parseInt(lieunaissance));

        // Get the USer's ID:
        String getConnex = session.getAttribute("userid").toString();
        if(getConnex!=null){
            dcl.setIdusr(Integer.parseInt(getConnex));
        }
        else dcl.setIdusr(-1);

        // Now save the OBJECT :
        Declaration dclS = declarationRepository.save(dcl);

        // READ DATA from anf_users and anf_declaration :
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultDeclaration = em.createQuery(
                "SELECT b.nombebe,b.prenbebe,b.dates,b.heure," +
                        "b.creation,b.sexe,b.iddec,b.etat FROM " +
                        "anf_users a JOIN anf_declaration b on a.idusr=b.idusr " +
                        "where a.idusr= " + getConnex).getResultList();

        // Pass it to the MODEl :
        model.addAttribute("declarations", resultDeclaration);

        // Pour les declarations validees :
        /*List<Object[]> resultDeclarationVal = em.createQuery(
                "SELECT b.nombebe,b.prenbebe,b.dates,b.heure," +
                        "b.creation,b.sexe,b.iddec,b.etat FROM " +
                        "anf_users a JOIN anf_declaration b on a.idusr=b.idusr " +
                        "where a.idusr= " + getConnex+" and b.etat=1").getResultList();
        model.addAttribute("declarationsval", resultDeclarationVal);
        */

        List<Declaration> listeDeclaration = declarationRepository.findAll();
        List<Commune> listeCommune = communeRepository.findAll();
        List<Hopital> listeHopital = hopitalRepository.findAll();
        List<Users> listeUsers = usersRepository.findAll();
        model.addAttribute("nbredeclarations", listeDeclaration.size());
        model.addAttribute("nbrecommunes", listeCommune.size());
        model.addAttribute("nbrehopital", listeHopital.size());
        model.addAttribute("nbreusers", listeUsers.size());

        //
        em.getTransaction().commit();
        em.close();

        return "accueilacte";
    }


    @GetMapping(value="/lireusers")
    public String lireusers(){
        return "lireusers";
    }



    @GetMapping(value="/affichers/{id}")
    public void genrapports(@PathVariable int id,
                           HttpServletResponse response) throws  Exception{

        // Get the DECLARATION id :
        // READ DATA from anf_users and anf_declaration
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List <Object[]> resultats = em.createQuery("select date(b.creation) as dateenreg,time(b.creation) as heureenreg," +
                "c.libelle as lieunaissance, concat(b.nombebe,' ',b.prenbebe) as bebe,  " +
                "case " +
                "when b.sexe = 0 then 'M' " +
                "else 'F' " +
                "end as sexe ," +
                "b.pere, (select libelle from anf_commune where idcom= b.villenaisspere) as villpere, " +
                "(select libelle from anf_profession where idprof= b.professionpere) as profpere, " +
                "b.domicilepere," +
                "(select libelle from anf_nationalite where idnat= b.nationperer) as natpere, " +
                "b.mere," +
                "(select libelle from anf_commune where idcom= b.villenaissmere) as villmere, " +
                "(select libelle from anf_profession where idprof= b.professionmere) as profmere, " +
                "b.domicilemere," +
                "(select libelle from anf_nationalite where idnat= b.nationmere) as natmere, " +
                "b.dates,b.heure, " +
                "b.declarationde," +
                "(select libelle from anf_langues where idlan= b.recuelangue) as langue, " +
                "b.assistancede,a.nom as sagefemme, b.datenaisspere,b.datenaissmere" +
                " from anf_users a inner join anf_declaration b on a.idusr=b.idusr inner " +
                "join anf_commune c on b.lieunaissance=c.idcom where iddec=" + id).getResultList();

        // Now populate by creating a table of RapportDeclaration :
        RapportDeclaration[] rdcl = new RapportDeclaration[resultats.size()];
        int i=0;
        for(Object[] obj : resultats){
            RapportDeclaration rdt = new RapportDeclaration();
            rdt.dateenreg = String.valueOf(obj[0]);
            rdt.heureenreg = String.valueOf(obj[1]);
            rdt.lieunaissance = String.valueOf(obj[2]);
            rdt.bebe = String.valueOf(obj[3]);
            //rdt.prenbebe = String.valueOf(obj[4]);
            rdt.sexe = String.valueOf(obj[4]);
            rdt.pere = String.valueOf(obj[5]);
            rdt.villpere = String.valueOf(obj[6]);
            rdt.profpere = String.valueOf(obj[7]);
            rdt.domicilepere = String.valueOf(obj[8]);
            rdt.natpere = String.valueOf(obj[9]);
            rdt.mere = String.valueOf(obj[10]);
            rdt.villmere = String.valueOf(obj[11]);
            rdt.profmere = String.valueOf(obj[12]);
            rdt.domicilemere = String.valueOf(obj[13]);
            rdt.natmere = String.valueOf(obj[14]);
            rdt.dates = String.valueOf(obj[15]);
            rdt.heure = String.valueOf(obj[16]);
            rdt.declarationde = String.valueOf(obj[17]);
            rdt.langue = String.valueOf(obj[18]);
            rdt.assistancede = String.valueOf(obj[19]);
            rdt.sagefemme = String.valueOf(obj[20]);
            rdt.datenaisspere = String.valueOf(obj[21]);
            rdt.datenaissmere = String.valueOf(obj[22]);

            rdcl[i] = rdt;
            i++;
        }

        // Close :
        em.getTransaction().commit();
        em.close();

        //response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(rdcl));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/declaration.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Get the TOWN where the DECLARATION has bee done :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resCommune = emr.createQuery("select distinct a.libelle as commune,b.libelle from " +
                        "anf_commune a inner join anf_hopital b on a.idcom=b.idcom inner join " +
                        "anf_users c on b.idhop=c.idhop inner join anf_declaration d on c.idusr=d.idusr" +
                        " where d.iddec=" + id).getResultList();
        String commune = "";
        try {
            for(Object[] obj : resCommune){
                commune = String.valueOf(obj[0]);
                break;
            }
            //commune = String.valueOf(resCommune.get(0)[0]);
        }
        catch (Exception exc){
            commune = "..."+exc.toString();
        }

        // Close
        emr.getTransaction().commit();
        emr.close();

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("iddec", id);
        parameters.put("ville", commune);

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"declaration.pdf\""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }


    @GetMapping(value={"/login", "/login/{id}"})
    public String login(Model model, @PathVariable Optional<Integer> id){
        if(id.isPresent()) model.addAttribute("message", "L'identifiant ou le mot de passe est incorrect");
        else model.addAttribute("message", "");
        //if(logout != null) model.addAttribute("message", "Vous vous etes deconnectes");
        return "connexpatient";
    }


}
