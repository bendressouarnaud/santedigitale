package com.maternite.gestion.controllers;


import com.maternite.gestion.beans.Commune;
import com.maternite.gestion.repositories.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommuneController {

    @Autowired
    CommuneRepository communeRepository;


    @GetMapping(value="/gestioncommune")
    public String obtenirPage(Model model){

        // Declare our LIST :
        List<Commune> listCom = communeRepository.findAll();
        if(listCom.size()>0) model.addAttribute("listecommune",listCom);

        return "gestioncommune";
    }

    @GetMapping(value="/modifiercom/{id}")
    public String modifiercom(@PathVariable int id, Model model){

        // Pick the COMMUNE :
        Commune commune = communeRepository.findByIdcom(id);
        model.addAttribute("commune",commune);

        // Get All the commune :
        List<Commune> listCom = communeRepository.findAll();
        if(listCom.size()>0)
            model.addAttribute("listecommune",listCom);

        return "gestioncommune";
    }

    // Create commune :  creercommune
    @GetMapping(value="/creercommune")
    public String creercommune(Model model){

        // Get All the commune :
        List<Commune> listCom = communeRepository.findAll();
        if(listCom.size()>0)
            model.addAttribute("listecommune",listCom);

        model.addAttribute("creercommune",1);
        return "gestioncommune";
    }

    // Now Process : addcom
    @PostMapping(value="/addcom")
    public String addcom(@RequestParam("libelle") String libelle, HttpServletRequest request,
                         Model model){

        Commune com = new Commune();
        com.setLibelle(libelle);
        // Now SAVE :
        communeRepository.save(com);

        // Get All the commune :
        List<Commune> listCom = communeRepository.findAll();
        if(listCom.size()>0)
            model.addAttribute("listecommune",listCom);

        return "gestioncommune";
    }



    @PostMapping(value="/editcom")
    public String editcom(@RequestParam("id") int id,
                          @RequestParam("libelle") String libelle, HttpServletRequest request,
                         Model model){
        try {
            request.setCharacterEncoding("ISO-8859-1");
        }
        catch (Exception exc){

        }

        // Get the object : Pick the COMMUNE :
        Commune commune = communeRepository.findByIdcom(id);
        commune.setLibelle(libelle);
        communeRepository.save(commune);

        // Get All the commune :
        List<Commune> listCom = communeRepository.findAll();
        if(listCom.size()>0)
            model.addAttribute("listecommune",listCom);

        return "gestioncommune";
    }
}
