package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.Agent;
import com.maternite.gestion.beans.Marche;
import com.maternite.gestion.beans.Taxes;
import com.maternite.gestion.beans.Vendeuse;
import com.maternite.gestion.repositories.AgentRepository;
import com.maternite.gestion.repositories.MarcheRepository;
import com.maternite.gestion.repositories.TaxesRepository;
import com.maternite.gestion.repositories.VendeuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaxesController {

    // A t t r i b u t e s :
    @Autowired
    TaxesRepository taxesRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    MarcheRepository marcheRepository;


    @PersistenceUnit
    EntityManagerFactory emf;


    @GetMapping(value="/gesttaxe")
    public String displayaccueil(Model model){

        // Get the list of TRADERs :
        List<Taxes> listtaxes = taxesRepository.findAll();
        model.addAttribute("listtaxes",listtaxes);

        return "gestaxe";
    }


    @GetMapping(value="/creertaxe")
    public String creertaxe(Model model){

        // Get the list of TRADERs :
        List<Taxes> listtaxes = taxesRepository.findAll();
        model.addAttribute("listtaxes",listtaxes);
        model.addAttribute("creertaxe",1);
        return "gestaxe";
    }


    // Now get the DATA to create the TRADER :
    @PostMapping(value="/addax")
    public String createTaxe(@RequestParam("libelle") String libelle){

        Taxes taxes = new Taxes();
        taxes.setLibelle(libelle);

        // Now, save the object :
        taxesRepository.save(taxes);

        return "redirect:/uploadTaxes"; // return "accueilacte";
    }


    @GetMapping("/uploadTaxes")
    public String uploadCommercante(Model model) {
        // Get the list of TRADERs :
        List<Taxes> listtaxes = taxesRepository.findAll();
        model.addAttribute("listtaxes",listtaxes);
        return "gestaxe";
    }


    @GetMapping(value="/modifiertax/{id}")
    public String modifiertax(@PathVariable int id, Model model){
        // Pick the COMMUNE :
        Taxes taxes = taxesRepository.findByItaxe(id);
        model.addAttribute("taxes",taxes);

        // Get the list of TRADERs :
        List<Taxes> listtaxes = taxesRepository.findAll();
        model.addAttribute("listtaxes",listtaxes);

        return "gestaxe";
    }


    @PostMapping(value="/editax")
    public String modifyTaxe(@RequestParam("id") int id, @RequestParam("libelle") String libelle){

        Taxes taxes = taxesRepository.findByItaxe(id);
        taxes.setLibelle(libelle);
        taxesRepository.save(taxes);

        return "redirect:/uploadTaxes"; // return "accueilacte";
    }

    @GetMapping(value="/accueilpagetaxe")
    public String pagetax(HttpSession session, Model model){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Object getSessAgentIdCom = session.getAttribute("agentidcom");
        int getAgentIdCom = Integer.parseInt(getSessAgentIdCom.toString());

        List<Object[]> resultVendeuse = em.createQuery(
                "select d.nom,d.contact,d.pieceidentite ,c.libelle,d.idven " +
                        "from anf_agent a inner join anf_commune b on a.idcom=b.idcom inner join anf_marche " +
                        "c on b.idcom=c.idcom " +
                        "inner join anf_vendeuse d on c.idmar=d.idmar where a.idcom = " +
                        getAgentIdCom).getResultList();

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

        return "accueiltaxe";
    }
}
