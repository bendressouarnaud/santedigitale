package com.maternite.gestion.controllers;

import com.maternite.gestion.beans.ClassFonction;
import com.maternite.gestion.beans.RapportActe;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TestController {

    // Attributes :
    @PersistenceUnit
    EntityManagerFactory emf;



    @GetMapping(value = "/chiffre/{nombre}")
    public String chiffre(@PathVariable String nombre)
    {
        ClassFonction cf = new ClassFonction();
        return cf.lettre(Integer.parseInt(nombre));
    }


    @GetMapping(value="/affdec/{rptid}")
    public void generateacte(@PathVariable int rptid,
                             HttpServletResponse response) throws  Exception{

        String datenaissance="",heurenaissance="";

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object[]> resultats = em.createQuery("SELECT d.dates as datenaissance, " +
                "concat(d.nombebe,' ',d.prenbebe) as enfant, d.heure as heurenaissance," +
                "d.pere, d.mere, d.nombebe, d.prenbebe, b.libelle as maternite FROM " +
                "anf_commune a inner join anf_hopital b on a.idcom=b.idcom inner join anf_users c " +
                "on b.idhop=c.idhop inner join anf_declaration d on c.idusr=d.idusr " +
                "where d.iddec=" + rptid).getResultList();
        //
        RapportActe[] rapTab = new RapportActe[resultats.size()];
        for(Object[] obj : resultats){
            RapportActe rate = new RapportActe();

            datenaissance = String.valueOf(obj[0]);
            heurenaissance = String.valueOf(obj[2]);

            rate.enfant = String.valueOf(obj[1]);
            rate.pere = String.valueOf(obj[3]);
            rate.mere = String.valueOf(obj[4]);
            rate.nombebe = String.valueOf(obj[5]);
            rate.prenbebe = String.valueOf(obj[6]);
            rate.maternite = String.valueOf(obj[7]);

            rapTab[0] = rate;
            // OUT
            break;
        }

        //
        em.getTransaction().commit();
        em.close();

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();

        // Now, get the date in LETTERS :
        String[] dateNumbers = datenaissance.split("-");
        ClassFonction cf = new ClassFonction();
        String anneelettre = cf.lettre(Integer.parseInt(dateNumbers[0]));
        cf = new ClassFonction();
        String moislettre = cf.getMois(Integer.parseInt(dateNumbers[1]));
        cf = new ClassFonction();
        String jourlettre = cf.lettre(Integer.parseInt(dateNumbers[2]));

        // heure in LETTERS
        String[] heureNumbers = heurenaissance.split(":");
        cf = new ClassFonction();
        String heurelettre = cf.lettre(Integer.parseInt(heureNumbers[0]));
        cf = new ClassFonction();
        String minutelettre = cf.lettre(Integer.parseInt(heureNumbers[1]));

        // Set now :
        parameters.put("annee", dateNumbers[0]);
        parameters.put("dateactuelle", "");
        parameters.put("numregistre", "00001270 du "+dateNumbers[0]+" du Registre");
        parameters.put("jourlettre", jourlettre);
        parameters.put("moislettre", moislettre);
        parameters.put("anneelettre", anneelettre);

        parameters.put("heurelettre", heurelettre);
        parameters.put("minutelettre", minutelettre);

        //
        String datelivraison = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String[] datelivraisonNumbers = datelivraison.split("-");
        cf = new ClassFonction();
        parameters.put("datelivraison", datelivraisonNumbers[0]+" "+
                cf.getMois(Integer.parseInt(datelivraisonNumbers[1]))+" "+datelivraisonNumbers[2]);
        parameters.put("repertoireimage", "/static/images/reportsimages/RCI.png");
        parameters.put("iddec", String.valueOf(rptid));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(rapTab));
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/extrait.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"actenaissance.pdf\""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

    }

}
