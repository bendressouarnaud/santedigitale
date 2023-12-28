package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.repositories.AgentRepository;
import com.maternite.gestion.repositories.MarcheRepository;
import com.maternite.gestion.repositories.TaxesRepository;
import com.maternite.gestion.repositories.VendeuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommercanteController {

    // A t t r i b u t e s :
    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    MarcheRepository marcheRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    TaxesRepository taxesRepository;

    @PersistenceUnit
    EntityManagerFactory emf;



    //   M E T H O D S    :
    @GetMapping(value="/gestcommercante")
    public String displayaccueil(Model model){

        // Get the list of TRADERs :
        List<Vendeuse> listVendeuse = vendeuseRepository.findAll();
        model.addAttribute("listevendeuse",listVendeuse);

        return "gestcommercante";
    }


    @GetMapping(value="/carte")
    public String carte(Model model){
        return "carte";
    }


    @GetMapping(value="/creercommercante")
    public String creercommercante(Model model){

        // Get the list of TRADERs :
        List<Vendeuse> listVendeuse = vendeuseRepository.findAll();
        model.addAttribute("listevendeuse",listVendeuse);
        model.addAttribute("creercommercante",1);

        // get the MARCHE
        List<Marche> listMarche = marcheRepository.findAll();
        model.addAttribute("marches",listMarche);

        return "gestcommercante";
    }

    // Now get the DATA to create the TRADER :
    @PostMapping(value="/addven")
    public String createTrader(@RequestParam("nom") String nom, @RequestParam("contact") String contact,
                               @RequestParam("email") String email,
                               @RequestParam("naissance") String naissance,
                               @RequestParam("piece") String piece,
                               @RequestParam("marche") String marche,
                               @RequestParam("sexe") String sexe){

        Vendeuse vendeuse = new Vendeuse();
        vendeuse.setNom(nom);
        vendeuse.setContact(contact);
        vendeuse.setEmail(email);

        Date tpdate;
        try {
            tpdate = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(naissance));
            vendeuse.setDatenaissance(tpdate);
        }
        catch (Exception exc){}

        vendeuse.setPieceidentite(piece);
        vendeuse.setIdmar(Integer.parseInt(marche));
        vendeuse.setSexe(Integer.parseInt(sexe));

        // Now, save the object :
        vendeuseRepository.save(vendeuse);

        return "redirect:/uploadCommercante"; // return "accueilacte";
    }


    @GetMapping("/uploadCommercante")
    public String uploadCommercante(Model model) {
        // Get the list of TRADERs :
        List<Vendeuse> listVendeuse = vendeuseRepository.findAll();
        model.addAttribute("listevendeuse",listVendeuse);
        return "gestcommercante";
    }


    @GetMapping(value="/modifierven/{id}")
    public String modifierven(@PathVariable int id,  Model model){
        // Pick the COMMUNE :
        Vendeuse vendeuse = vendeuseRepository.findByIdven(id);
        String dateN = new SimpleDateFormat("MM/dd/yyyy").format(vendeuse.getDatenaissance());
        model.addAttribute("vendeuse",vendeuse);
        model.addAttribute("daten",dateN);

        // Get the list of TRADERs :
        List<Vendeuse> listVendeuse = vendeuseRepository.findAll();
        model.addAttribute("listevendeuse",listVendeuse);

        // get the MARCHE
        List<Marche> listMarche = marcheRepository.findAll();
        model.addAttribute("marches",listMarche);

        return "gestcommercante";
    }



    @PostMapping(value="/editven")
    public String modifyTrader(@RequestParam("id") int id, @RequestParam("nom") String nom,
                               @RequestParam("contact") String contact,
                               @RequestParam("email") String email,
                               @RequestParam("naissance") String naissance,
                               @RequestParam("piece") String piece,
                               @RequestParam("marche") String marche,
                               @RequestParam("sexe") String sexe){

        Vendeuse vendeuse = vendeuseRepository.findByIdven(id);
        vendeuse.setNom(nom);
        vendeuse.setContact(contact);
        vendeuse.setEmail(email);

        Date tpdate;
        try {
            tpdate = new SimpleDateFormat("yyyy-MM-dd").
                    parse(ClassFonction.retourDate(naissance));
            vendeuse.setDatenaissance(tpdate);
        }
        catch (Exception exc){}
        //
        vendeuse.setPieceidentite(piece);
        vendeuse.setIdmar(Integer.parseInt(marche));
        vendeuse.setSexe(Integer.parseInt(sexe));

        // Now, save the object :
        vendeuseRepository.save(vendeuse);

        return "redirect:/uploadCommercante"; // return "accueilacte";
    }


    //     accueiltaxe
    @GetMapping(value="/accueiltaxe")
    public String accueiltaxe(HttpSession session, Model model){

        Object getSessAgentid = session.getAttribute("agentid");
        if(getSessAgentid != null){
            int agentId = Integer.parseInt(getSessAgentid.toString());

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> resultVendeuse = em.createQuery(
                    "select d.nom,d.contact,d.pieceidentite ,c.libelle,d.idven " +
                            "from anf_agent a inner join anf_commune b on a.idcom=b.idcom inner join anf_marche " +
                            "c on b.idcom=c.idcom " +
                            "inner join anf_vendeuse d on c.idmar=d.idmar where a.idcom = " +
                            agentId).getResultList();

            // Pass it to the MODEl :
            model.addAttribute("vendeuses", resultVendeuse);

            List<Taxes> listetaxes = taxesRepository.findAll();
            List<Vendeuse> listevendeuse = vendeuseRepository.findAll();
            List<Agent> listeagent = agentRepository.findAll();
            List<Marche> listemarche = marcheRepository.findAll();
            model.addAttribute("nbrevendeuses", listevendeuse.size());
            model.addAttribute("nbretaxes", listetaxes.size());
            model.addAttribute("nbreagent", listeagent.size());
            model.addAttribute("nbremarche", listemarche.size());

            //
            em.getTransaction().commit();
            em.close();
        }

        return "accueiltaxe";
    }
}
