package com.maternite.gestion.mesobjets;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FonctionOrdinaire {

    private final static int premier = 68;
    private final static int autres = 70;

    public static String formatterDate(String dates){
        String[] tabDate = dates.split("-");
        String retour = tabDate[1]+"/"+tabDate[2]+"/"+tabDate[0];
        return retour;
    }

    public static String retourDate(Date dates){

        String dts = new SimpleDateFormat("yyyy-MM-dd").format(dates);
        //System.out.println("dates : "+dts);
        String[] tabDate = dts.split("-");
        String retour = tabDate[1]+"/"+tabDate[2]+"/"+tabDate[0];
        /*
        try{
            Date tpdatedt = new SimpleDateFormat("MM/dd/yyyy").
                    parse(retour);
            return tpdatedt;
        }
        catch (Exception exc){
        }
        */

        return retour;

    }

    public static String[] decouperDiagnostic(String diagnostic){

        //
        String tampon = diagnostic;
        String[] retour = new String[3];
        for(int i=0; i < 3; i++){
            switch (i){
                case 0:
                    if(diagnostic.trim().length() > premier){
                        retour[i] = diagnostic.substring(0, premier);
                        diagnostic = diagnostic.substring(premier);
                    }
                    else {
                        retour[i] = diagnostic;
                        diagnostic = "";
                    }
                    break;

                case 1:
                    if(diagnostic.trim().length() > autres){
                        retour[i] = diagnostic.substring(0, autres);
                        diagnostic = diagnostic.substring(autres);
                    }
                    else {
                        retour[i] = diagnostic;
                        diagnostic = "";
                    }
                    break;

                case 2:
                    if(diagnostic.trim().length() > autres){
                        retour[i] = diagnostic.substring(0, autres);
                        diagnostic = diagnostic.substring(autres);
                    }
                    else {
                        retour[i] = diagnostic;
                        diagnostic = "";
                    }
                    break;
            }
        }

        return retour;
    }



    public static String[] decouperTraitement(String traitement){

        //
        String tampon = traitement;
        String[] retour = new String[4];
        for(int i=0; i < 4; i++){
            switch (i){
                case 0:
                    if(traitement.trim().length() > premier){
                        retour[i] = traitement.substring(0, premier);
                        traitement = traitement.substring(premier);
                    }
                    else {
                        retour[i] = traitement;
                        traitement = "";
                    }
                    break;

                case 1:
                    if(traitement.trim().length() > autres){
                        retour[i] = traitement.substring(0, autres);
                        traitement = traitement.substring(autres);
                    }
                    else {
                        retour[i] = traitement;
                        traitement = "";
                    }
                    break;

                case 2:
                    if(traitement.trim().length() > autres){
                        retour[i] = traitement.substring(0, autres);
                        traitement = traitement.substring(autres);
                    }
                    else {
                        retour[i] = traitement;
                        traitement = "";
                    }
                    break;

                case 3:
                    if(traitement.trim().length() > autres){
                        retour[i] = traitement.substring(0, autres);
                        traitement = traitement.substring(autres);
                    }
                    else {
                        retour[i] = traitement;
                        traitement = "";
                    }
                    break;
            }
        }

        return retour;
    }




}
