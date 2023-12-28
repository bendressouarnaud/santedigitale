package com.maternite.gestion.controllers;

import com.maternite.gestion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TendanceController {

    @Autowired
    VendeuseRepository vendeuseRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    TaxesRepository taxesRepository;
    @Autowired
    PeriodeRepository periodeRepository;
    @Autowired
    AgentRepository agentRepository;


    //   A T T R I B U T E S    :
    @PersistenceUnit
    EntityManagerFactory emf;


    //
    @GetMapping(value="/tendance")
    public String tendance(Model model){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultTendance = em.createQuery(
                "select a.nom, c.libelle, year(b.dates), count(b.idpaie) from " +
                        "anf_vendeuse a inner join anf_paiement b on a.idven=b.idven inner join " +
                        "anf_taxe c on b.idtaxe=c.itaxe where year(b.dates)=year(curdate()) group by " +
                        "a.nom,c.libelle,year(b.dates) ").getResultList();

        model.addAttribute("listetendance", resultTendance);

        //
        em.getTransaction().commit();
        em.close();

        return "tendance";
    }

    @GetMapping(value="/deconnexion")
    public String deconnexion(HttpSession session, Model model){
        session.invalidate();
        return "redirect:/apps";
    }

}
