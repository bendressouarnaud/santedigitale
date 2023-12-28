package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.*;
import com.maternite.gestion.repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class HistopaiementController {

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


    //   M E T H O D E S        :
    @GetMapping(value="/historiquepaiement/{id}")
    public String histopaiement(Model model, @PathVariable int id){

        // READ DATA from anf_users and anf_declaration
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultHistorique = em.createQuery("select " +
                "c.libelle,b.montant, date(b.dates) as dates,b.heure,d.libelle,e.nom,b.idpaie " +
                "from anf_vendeuse a inner join anf_paiement b on a.idven=b.idven inner " +
                "join anf_taxe c on b.idtaxe=c.itaxe inner join anf_periode d on b.idper=d.idper inner join " +
                "anf_agent e on b.idage=e.idage where b.idven=" + id).getResultList();

        // Pass it to the MODEl :
        model.addAttribute("listepaiement", resultHistorique);
        model.addAttribute("idvendeuse", id);

        //
        em.getTransaction().commit();
        em.close();

        return "histopaie";
    }


    @GetMapping(value="/genererhisto/{id}")
    public void genererhisto(@PathVariable int id, HttpServletResponse response) throws  Exception{


        // READ DATA from anf_users and anf_declaration
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        List<Object[]> resultHistorique = em.createQuery("select " +
                "c.libelle,b.montant, date(b.dates) as dates,b.heure,d.libelle as periodes,e.nom " +
                "from anf_vendeuse a inner join anf_paiement b on a.idven=b.idven inner " +
                "join anf_taxe c on b.idtaxe=c.itaxe inner join anf_periode d on b.idper=d.idper inner join " +
                "anf_agent e on b.idage=e.idage where b.idven=" + id).getResultList();

        // Pass it to the MODEl :
        List<Histopaiement> listeHisto = new ArrayList<Histopaiement>();

        Histopaiement ht = new Histopaiement();
        ht.libelle = "";
        ht.montant = 0;
        ht.dates = "";
        ht.heure = "";
        ht.periodes = "";
        ht.nom = "";
        listeHisto.add(ht);

        for(Object[] obj : resultHistorique){
            ht = new Histopaiement();

            ht.libelle = String.valueOf(obj[0]);
            ht.montant = Integer.parseInt(String.valueOf(obj[1]));
            ht.dates = String.valueOf(obj[2]);
            ht.heure = String.valueOf(obj[3]);
            ht.periodes = String.valueOf(obj[4]);
            ht.nom = String.valueOf(obj[5]);
            listeHisto.add(ht);

        }

        //
        em.getTransaction().commit();
        em.close();


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listeHisto);
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/histopaiement.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Vendeuse vd = vendeuseRepository.findByIdven(id);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("itemdatasource", dataSource);
        parameters.put("vendeuse", vd.getNom());

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format(
                "attachment; filename=\"histopaiement.pdf\""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);


        /*
        List<Taxes> listeCommune = taxesRepository.findAll();
        JRBeanCollectionDataSource items = new JRBeanCollectionDataSource(listeCommune);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("itemdatasource", items);

        InputStream inputStream = this.getClass().getResourceAsStream("/reports/histopaiement.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format(
                "attachment; filename=\"histopaiement.pdf\""));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, items);

        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        */
    }


    @GetMapping(value="/gticket/{id}")
    public void gticket(@PathVariable int id, HttpServletResponse response) throws  Exception{
        // READ DATA from anf_users and anf_declaration
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        List<Object[]> resultPaie = em.createQuery("select d.libelle as taxe, date(b.dates) as dte, " +
                "b.heure,c.nom, c.contact,b.montant  from anf_vendeuse a inner join anf_paiement b on a.idven=b.idven " +
                "inner join anf_agent c on b.idage=c.idage inner join anf_taxe d on b.idtaxe=d.itaxe " +
                "where idpaie="+ id).getResultList();

        Ticket[] ticketTab = new Ticket[resultPaie.size()];
        int i=0;
        for(Object[] obj : resultPaie){
            Ticket ticket = new Ticket();
            ticket.taxe = String.valueOf(obj[0]);
            ticket.dte = String.valueOf(obj[1]);
            ticket.heure = String.valueOf(obj[2]);
            ticket.nom = String.valueOf(obj[3]);
            ticket.contact = String.valueOf(obj[4]);
            ticket.montant = Integer.parseInt(String.valueOf(obj[5]));

            ticketTab[i] = ticket;
            i++;
        }

        //
        em.getTransaction().commit();
        em.close();

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("numticket", String.valueOf(id));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(ticketTab));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/ticket.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ticket.pdf\""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }
}
