package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.repositories.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PaiementController {

    // A T T R I B U T E S  :
    @Autowired
    TaxesRepository taxesRepository;
    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    PeriodeRepository periodeRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    MarcheRepository marcheRepository;


    @PersistenceUnit
    EntityManagerFactory emf;


    //  M E T H O D S  :
    @GetMapping(value="/effectuerpaiement/{id}")
    public String displayinterfacepaie(@PathVariable int id, Model model){

        // Get the list of PERIODE :
        List<Periode> listeperiode = periodeRepository.findAll();
        model.addAttribute("listeperiode",listeperiode);

        // vendeuse
        Vendeuse vendeuse = vendeuseRepository.findByIdven(id);
        model.addAttribute("vendeuse",vendeuse);

        // Get the list of TRADERs :
        List<Taxes> listtaxes = taxesRepository.findAll();
        model.addAttribute("listtaxes",listtaxes);

        return "paiement";
    }


    @PostMapping(value="/addpaie")
    public String enregistrerpaie(@RequestParam("priode") String periode,
                                  @RequestParam("montant") String montant,
                                  @RequestParam("proprietaire") String proprietaire,
                                  @RequestParam("taxe") String taxe, HttpSession session){

        Paiement pt = new Paiement();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        try{
            pt.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }
        catch (Exception exc){}

        //
        pt.setHeure(heure);
        pt.setIdper(Integer.parseInt(periode));
        pt.setMontant(Integer.parseInt(montant));
        pt.setIdven(Integer.parseInt(proprietaire));
        pt.setIdtaxe(Integer.parseInt(taxe));

        Object getSessAgentIdCom = session.getAttribute("agentidcom");
        int getAgentIdCom = Integer.parseInt(getSessAgentIdCom.toString());
        pt.setIdage(getAgentIdCom);

        // Save :
        paiementRepository.save(pt);
        return "redirect:/uploadPaiement";
    }


    @GetMapping(value="/uploadPaiement")
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
