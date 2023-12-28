package com.maternite.gestion.controllers;


import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.FonctionOrdinaire;
import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ModifierController {

    private String UPLOADED_FOLDER = "D://DossierSpring//Fichiers//";

    @Autowired
    DeclarationRepository declarationRepository;
    @Autowired
    UploadFichierRepository uploadFichierRepository;
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    NationaliteRepository nationaliteRepository;


    @PersistenceUnit
    EntityManagerFactory emf;




    @GetMapping(value="/modifier/{iddec}")
    public String modifier(@PathVariable int iddec,
                           Model model){

        // Get COMMUNES
        List<Commune> listeCommune = communeRepository.findAll();
        model.addAttribute("lstcommune", listeCommune);
        // Nationalite :
        List<Nationalite> listeNationalite = nationaliteRepository.findAll();
        model.addAttribute("lstnation", listeNationalite);

        // Get BACK the declaration :
        Declaration dclS = declarationRepository.findByIddec(iddec);
        // Update the DATES :
        String dates = FonctionOrdinaire.retourDate(dclS.getDates());
        String datesmere = FonctionOrdinaire.retourDate(dclS.getDatenaissmere());
        String datespere = FonctionOrdinaire.retourDate(dclS.getDatenaisspere());
        //dclS.setDates(FonctionOrdinaire.retourDate(dclS.getDates()));
        //dclS.setDatenaissmere(FonctionOrdinaire.retourDate(dclS.getDatenaissmere()));
        //dclS.setDatenaisspere(FonctionOrdinaire.retourDate(dclS.getDatenaisspere()));

        model.addAttribute("dates", dates);
        model.addAttribute("datesmere", datesmere);
        model.addAttribute("datespere", datespere);
        model.addAttribute("declaration", dclS);
        model.addAttribute("declarationid", "/gestion/modifbebe/".concat(String.valueOf(iddec)));
        model.addAttribute("modifier", "1");
        //return "testvaiableexists";
        return "declaration";

    }


    // Save the file :  fichiersigne
    @PostMapping(value="/modifbebe/{id}")
    public String singleFileUpload(@RequestParam("nombebe") String nombebe,
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
            @RequestParam("fichiersigne") MultipartFile file,
            @PathVariable int id,
            HttpSession session, Model model){
        try {







            /*  CHECK  */
            Declaration dcl = declarationRepository.findByIddec(id);
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
            dcl.setEtat(1);
            dcl.setSexe(Integer.parseInt(sexe));
            dcl.setLieunaissance(Integer.parseInt(lieunaissance));

            // Get the USer's ID:
            String getConnex = session.getAttribute("userid").toString();
            if(getConnex!=null){
                dcl.setIdusr(Integer.parseInt(getConnex));
            }
            else dcl.setIdusr(-1);

            // Save the file :
            byte[] bytes = file.getBytes();
            String nomfichier = file.getOriginalFilename();
            String typefichier = file.getContentType();
            long tailleFichier = file.getSize();
            //
            dcl.setFichier(bytes);
            dcl.setNomfichier(nomfichier);
            dcl.setTypefichier(typefichier);
            dcl.setTaillefichier(tailleFichier);

            // Now save the OBJECT :
            declarationRepository.save(dcl);

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


            /*  END CHECK  */





        } catch (Exception e) {

        }

        return "redirect:/uploadStatus"; // return "accueilacte";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(HttpSession session, Model model) {

        Object getSessUserid = session.getAttribute("userid");
        String getConnex = "";

        if(getSessUserid!=null) {
            getConnex = getSessUserid.toString();
        }

        // READ DATA from anf_users and anf_declaration
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultDeclaration = em.createQuery("SELECT b.nombebe,b.prenbebe,b.dates,b.heure," +
                "b.creation,b.sexe,b.iddec,b.etat FROM " +
                "anf_users a JOIN anf_declaration b on a.idusr=b.idusr " +
                "where a.idusr= " + getConnex).getResultList();

        // Pass it to the MODEl :
        model.addAttribute("declarations", resultDeclaration);

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
}
