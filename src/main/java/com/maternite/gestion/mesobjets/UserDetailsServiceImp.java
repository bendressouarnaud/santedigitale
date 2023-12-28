package com.maternite.gestion.mesobjets;

import com.maternite.gestion.beans.Journal;
import com.maternite.gestion.beans.Medecin;
import com.maternite.gestion.repositories.JournalRepository;
import com.maternite.gestion.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    JournalRepository journalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // Try to get the PASSWORD :
        String password = request.getParameter("password"); //
        //
        Medecin medecin = medecinRepository.findByIdentifiantAndMotpasse(username, password);
        //
        User.UserBuilder builder = null;
        if (medecin != null) {
            builder =
                    org.springframework.security.core.
                            userdetails.User.withUsername(username);
            builder.password(medecin.getMotpasse());
            builder.roles(medecin.getProfil());

            // Save in the journal
            Journal jrl = new Journal();
            jrl.setIdmed(medecin.getIdmed());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            try {
                jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                jrl.setHeure(heure);
            }
            catch (Exception exc){

            }
            jrl.setAction("Utilisateur connecté");
            journalRepository.save(jrl);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}