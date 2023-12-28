package com.maternite.gestion.beans;

public class ClassFonction {

    public String tampon = "";
    public int t_nombre = 0;

    public ClassFonction(){}

    public static String retourDate(String dates){
        String retour = "";
        String[] tampon = dates.split("/");  // MM-DD-YYYY
        retour = tampon[2]+"-"+tampon[0]+"-"+tampon[1];
        return retour;
    }

    public String getMois(int ms){
        String mois = "";

        switch (ms){
            case 1:
                mois = "Janvier";
                break;

            case 2:
                mois = "Février";
                break;

            case 3:
                mois = "Mars";
                break;

            case 4:
                mois = "Avril";
                break;

            case 5:
                mois = "Mai";
                break;

            case 6:
                mois = "Juin";
                break;

            case 7:
                mois = "Juillet";
                break;

            case 8:
                mois = "Aout";
                break;

            case 9:
                mois = "Septembre";
                break;

            case 10:
                mois = "Octobre";
                break;

            case 11:
                mois = "Novembre";
                break;

            case 12:
                mois = "Decembre";
                break;
        }

        return mois;
    }



    public String lettre(int nombre){
        t_nombre = nombre;
        for(int i=0; i<String.valueOf(nombre).length(); i++){
            if(t_nombre != 0)
                chiffre();
            else break;
        }

        // Return this
        return tampon;
    }



    // Get a number in STRING :
    public void chiffre(){

        // Get the first character :
        int firstCar = Integer.parseInt(String.valueOf(t_nombre).substring(0,1));

        switch(firstCar){
            case 9:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "neuf mille ";  // 9080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "neuf cent ";   // 980
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        // tampon += "quatre-vingt-";   // 95
                        //int secondCharacter = Integer.parseInt(String.valueOf(t_nombre).substring(1,2));
                        String tt = String.valueOf(t_nombre).substring(1,2);
                        int secondCharacter = Integer.parseInt(tt);
                        switch(secondCharacter){
                            case 0:
                                tampon += "quatre-vingt-dix ";   // 0
                                break;

                            case 1:
                                tampon += "quatre-vingt-onze ";   // 1
                                break;

                            case 2:
                                tampon += "quatre-vingt-douze ";   // 2
                                break;

                            case 3:
                                tampon += "quatre-vingt-treize ";   // 3
                                break;

                            case 4:
                                tampon += "quatre-vingt-quatorze ";   // 4
                                break;

                            case 5:
                                tampon += "quatre-vingt-quinze ";   // 5
                                break;

                            case 6:
                                tampon += "quatre-vingt-seize ";   // 6
                                break;

                            case 7:
                                tampon += "quatre-vingt-dix-sept ";   // 7
                                break;

                            case 8:
                                tampon += "quatre-vingt-dix-huit ";   // 8
                                break;

                            case 9:
                                tampon += "quatre-vingt-dix-neuf ";   // 9
                                break;
                        }
                        t_nombre = 0 ;//Integer.parseInt(String.valueOf(t_nombre).substring(0,2));
                        break;

                    case 1:
                        tampon += "neuf ";   // 9
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(0,1));
                        break;
                }
                break;

            case 8:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "huit mille ";  // 8080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "huit cent ";   // 880
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "quatre-vingt ";   // 85
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "huit ";   // 8
                        t_nombre = 0; // Integer.parseInt(String.valueOf(t_nombre).substring(0,1));
                        break;
                }
                break;

            case 7:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "sept mille ";  // 7080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "sept cent ";   // 780
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        // Get the following character :
                        int secondCharacter = Integer.parseInt(String.valueOf(t_nombre).substring(1,2));
                        switch(secondCharacter){
                            case 0:
                                tampon += "soixante-dix ";   // 0
                                break;

                            case 1:
                                tampon += "soixante-onze ";   // 1
                                break;

                            case 2:
                                tampon += "soixante-douze ";   // 2
                                break;

                            case 3:
                                tampon += "soixante-treize ";   // 3
                                break;

                            case 4:
                                tampon += "soixante-quatorze ";   // 4
                                break;

                            case 5:
                                tampon += "soixante-quinze ";   // 5
                                break;

                            case 6:
                                tampon += "soixante-seize ";   // 6
                                break;

                            case 7:
                                tampon += "soixante-dix-sept ";   // 7
                                break;

                            case 8:
                                tampon += "soixante-dix-huit ";   // 8
                                break;

                            case 9:
                                tampon += "soixante-dix-neuf ";   // 9
                                break;
                        }
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(2));
                        break;

                    case 1:
                        tampon += "sept ";   // 8
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(0,1));
                        break;
                }
                break;


            case 6:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "six mille ";  // 6080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "six cent ";   // 680
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "soixante ";   // 65
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "six ";   // 6
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;
                }
                break;


            case 5:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "cinq mille ";  // 5080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "cinq cent ";   // 580
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "cinquante ";   // 55
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "cinq ";   // 5
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;
                }
                break;

            case 4:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "quatre mille ";  // 4080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "quatre cent ";   // 480
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "quarante ";   // 45
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "quatre ";   // 4
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;
                }
                break;


            case 3:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "trois mille ";  // 3080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "trois cent ";   // 380
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "trente ";   // 35
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "trois ";   // 3
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;
                }
                break;

            case 2:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "deux mille ";  // 2080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "deux cent ";   // 280
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += "vingt ";   // 25
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += "deux ";   // 2
                        t_nombre = 0;
                        break;
                }
                break;

            case 1:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += "mille ";  // 2080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += "cent ";   // 280
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        // Get the following character :
                        int secondCharacter = Integer.parseInt(String.valueOf(t_nombre).substring(1,2));
                        switch(secondCharacter){
                            case 0:
                                tampon += "dix ";   // 0
                                break;

                            case 1:
                                tampon += "onze ";   // 1
                                break;

                            case 2:
                                tampon += "douze ";   // 2
                                break;

                            case 3:
                                tampon += "treize ";   // 3
                                break;

                            case 4:
                                tampon += "quatorze ";   // 4
                                break;

                            case 5:
                                tampon += "quinze ";   // 5
                                break;

                            case 6:
                                tampon += "seize ";   // 6
                                break;

                            case 7:
                                tampon += "dix-sept ";   // 7
                                break;

                            case 8:
                                tampon += "dix-huit ";   // 8
                                break;

                            case 9:
                                tampon += "dix-neuf ";   // 9
                                break;
                        }
                        t_nombre = 0;//Integer.parseInt(String.valueOf(t_nombre).substring(2));
                        break;

                    case 1:
                        tampon += "un ";   // 2
                        t_nombre = 0;
                        break;
                }
                break;

            case 0:
                switch(String.valueOf(t_nombre).length()){
                    case 4:
                        tampon += " ";  // 2080
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 3:
                        tampon += " ";   // 280
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 2:
                        tampon += " ";   // 25
                        t_nombre = Integer.parseInt(String.valueOf(t_nombre).substring(1));
                        break;

                    case 1:
                        tampon += " ";   // 2
                        t_nombre = 0;
                        break;
                }
                break;
        }

        //return tampon;
    }

}
