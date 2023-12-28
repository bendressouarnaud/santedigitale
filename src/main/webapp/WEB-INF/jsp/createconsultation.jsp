
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta charset="utf-8">

      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>GestPAT - Consultation</title>
      <meta content="Admin Dashboard" name="description">
      <meta content="Themesbrand" name="author">
      <link rel="shortcut icon" href="/images/favicon.ico">
      <!--Chartist Chart CSS -->
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

	  <!-- DataTables -->
	  <link href="<c:url value='/css/datatables.bootstrap4.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/buttons.bootstrap4.css' />" rel="stylesheet" type="text/css">
	  <!-- Responsive datatable examples -->
	  <link href="<c:url value='/css/responsive.bootstrap4.css' />" rel="stylesheet" type="text/css">

	  <!-- Plugins css -->
	  <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-datepicker.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-colorpicker.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-touchspin.min.css' />" rel="stylesheet" type="text/css">

      <style>
          @media screen and (min-width: 676px) {
              .modal-dialog {
                  max-width: 970px; /* New width for default modal */
              }
          }
      </style>

      <script>

            //
            var myBoolean = false;
            var activeRechHopital = false;
            // set CABINET DENTAIRE Id to ease the process of updates :
            //var cabinetId = parseInt($("#cabinetdentaireId").val());

            function modifiermenu(){
                var idService = parseInt($("#servicelib").val());
            }


            // Check POIDS, TAILLE, TENSION
            function checkConstante(constante, choix){
                var retour = true;

                // Prototype :
                var prototype = /([0-9]*[.])?[0-9]+$/;

                if(!prototype.test(constante)){
                    switch(choix){
                        case 0:
                            // Poids
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>Le poids est incorrect !</h3>";
                            retour = false;
                            break;

                        case 1:
                            // Taille
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>La taille est incorrecte !</h3>";
                            retour = false;
                            break;

                        case 2:
                            // POuls
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>La tension est incorrecte !</h3>";
                            retour = false;
                            break;

                        case 3:
                            // Temperature
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>La temp&eacute;rature est incorrecte !</h3>";
                            retour = false;
                            break;
                    }
                }

                return retour;
            }


            // Perform an accurate check to activate or deactivate elements :
            function performAction(){
                if($('#limotif').css('display') != 'none'){
                    document.getElementById('amotif').click();
                    return;
                }
                if($('#liantecedent').css('display') != 'none'){
                    document.getElementById('aantecedent').click();
                    return;
                }
                if($('#limodevie').css('display') != 'none'){
                    document.getElementById('amodedevie').click();
                    return;
                }
                if($('#liexamenclin').css('display') != 'none'){
                    document.getElementById('aexamenclinique').click();
                    return;
                }
                if($('#liconclusion').css('display') != 'none'){
                    document.getElementById('aconclusion').click();
                    return;
                }
                if($('#lidiagnostic').css('display') != 'none'){
                    document.getElementById('adiagnostic').click();
                    return;
                }
                if($('#litraitement').css('display') != 'none'){
                    document.getElementById('atraitement').click();
                    return;
                }
                if($('#liinterrogatoire').css('display') != 'none'){
                    document.getElementById('ainterrogatoire').click();
                    return;
                }
                if($('#liexamenphysique').css('display') != 'none'){
                    document.getElementById('aexamenphysique').click();
                    return;
                }
                if($('#licat').css('display') != 'none'){
                    document.getElementById('acat').click();
                    return;
                }

                // diagnosticretenu
                if($('#lidiagnosticret').css('display') != 'none'){
                    document.getElementById('adiagnosticret').click();
                    return;
                }
                // hospitalisation
                if($('#lihospitalisation').css('display') != 'none'){
                    document.getElementById('ahospitalisation').click();
                    return;
                }
                // soins
                if($('#lisoins').css('display') != 'none'){
                    document.getElementById('asoins').click();
                    return;
                }

                // avis confrere
                if($('#liavisconfrere').css('display') != 'none'){
                    document.getElementById('aavisconfrere').click();
                    return;
                }
                // ordonnance
                if($('#liordonnance').css('display') != 'none'){
                    document.getElementById('aordonnance').click();
                    return;
                }
                // Passé médical
                if($('#lipassemedic').css('display') != 'none'){
                    document.getElementById('apassemedic').click();
                    return;
                }
                // Allergies
                if($('#liallergies').css('display') != 'none'){
                    document.getElementById('aallergies').click();
                    return;
                }
                // Passé dentaire
                if($('#lidentaire').css('display') != 'none'){
                    document.getElementById('adentaire').click();
                    return;
                }
                // Protheses
                if($('#liprotheses').css('display') != 'none'){
                    document.getElementById('aprotheses').click();
                    return;
                }
                // Nature des interventions
                if($('#liinterventions').css('display') != 'none'){
                    document.getElementById('ainterventions').click();
                    return;
                }

                // Examens paracliniques
                if($('#liexamlabo').css('display') != 'none'){
                    document.getElementById('aexamlabo').click();
                    return;
                }
            }

            // For HOPITAL
            function onchangeHopital(){
                if(activeRechHopital) onchangeService();
            }

            /* Change Service : */
            function onchangeService(){

                //
                var idService = parseInt($("#servicelib").val());
                var hopitallib = parseInt($("#hopitallib").val());
                var monurl = $("#monurl").val();

                //
                $.get(monurl+"getserviceid/"+idService+"/"+hopitallib,
                    function(data) {
                        //
                        var donnees = {};

                        $(data).find('item').each(function(){

                            donnees.motif = parseInt($(this).find('motif').text());
                            donnees.antecedent = parseInt($(this).find('antecedent').text());
                            donnees.modevie = parseInt($(this).find('modevie').text());
                            donnees.examenclinique = parseInt($(this).find('examenclinique').text());
                            donnees.conclusion = parseInt($(this).find('conclusion').text());
                            donnees.diagnostic = parseInt($(this).find('diagnostic').text());
                            donnees.traitement = parseInt($(this).find('traitement').text());
                            //
                            donnees.interrogatoire = parseInt($(this).find('interrogatoire').text());
                            donnees.examenphysique = parseInt($(this).find('examenphysique').text());
                            donnees.conduiteatenir = parseInt($(this).find('conduiteatenir').text());

                            // Added on 28/11/2020 :
                            donnees.diagnosticretenu = parseInt($(this).find('diagnosticretenu').text());
                            donnees.hospitalisation = parseInt($(this).find('hospitalisation').text());
                            donnees.soins = parseInt($(this).find('soins').text());
                            donnees.avisconfrere = parseInt($(this).find('avisconfrere').text());
                            donnees.ordonnance = parseInt($(this).find('ordonnance').text());

                            //
                            donnees.passemedical = parseInt($(this).find('passemedical').text());
                            donnees.allergies = parseInt($(this).find('allergies').text());
                            donnees.passedentaire = parseInt($(this).find('passedentaire').text());
                            donnees.protheses = parseInt($(this).find('protheses').text());
                            //
                            donnees.interventions = parseInt($(this).find('interventions').text());
                            donnees.examparaclinique = parseInt($(this).find('examparaclinique').text());
                        });

                        // Now, perform :
                        if(donnees.motif ==0) document.getElementById("limotif").style.display = "none";
                        else document.getElementById("limotif").style.display = "list-item";
                        if(donnees.antecedent ==0) document.getElementById("liantecedent").style.display = "none";
                        else document.getElementById("liantecedent").style.display = "list-item";
                        if(donnees.modevie ==0) document.getElementById("limodevie").style.display = "none";
                        else document.getElementById("limodevie").style.display = "list-item";
                        if(donnees.examenclinique ==0) document.getElementById("liexamenclin").style.display = "none";
                        else document.getElementById("liexamenclin").style.display = "list-item";
                        if(donnees.conclusion ==0) document.getElementById("liconclusion").style.display = "none";
                        else document.getElementById("liconclusion").style.display = "list-item";
                        if(donnees.diagnostic ==0) document.getElementById("lidiagnostic").style.display = "none";
                        else document.getElementById("lidiagnostic").style.display = "list-item";
                        if(donnees.traitement ==0) document.getElementById("litraitement").style.display = "none";
                        else document.getElementById("litraitement").style.display = "list-item";
                        //
                        if(donnees.interrogatoire ==0) document.getElementById("liinterrogatoire").style.display = "none";
                        else document.getElementById("liinterrogatoire").style.display = "list-item";
                        if(donnees.examenphysique ==0) document.getElementById("liexamenphysique").style.display = "none";
                        else document.getElementById("liexamenphysique").style.display = "list-item";
                        if(donnees.conduiteatenir ==0) document.getElementById("licat").style.display = "none";
                        else document.getElementById("licat").style.display = "list-item";

                        // Added on 28/11/2020 :
                        if(donnees.diagnosticretenu ==0) document.getElementById("lidiagnosticret").style.display = "none";
                        else document.getElementById("lidiagnosticret").style.display = "list-item";
                        if(donnees.hospitalisation ==0) document.getElementById("lihospitalisation").style.display = "none";
                        else document.getElementById("lihospitalisation").style.display = "list-item";
                        if(donnees.soins ==0) document.getElementById("lisoins").style.display = "none";
                        else document.getElementById("lisoins").style.display = "list-item";
                        if(donnees.avisconfrere ==0) document.getElementById("liavisconfrere").style.display = "none";
                        else document.getElementById("liavisconfrere").style.display = "list-item";
                        if(donnees.ordonnance ==0) document.getElementById("liordonnance").style.display = "none";
                        else document.getElementById("liordonnance").style.display = "list-item";

                        if(donnees.passemedical ==0) document.getElementById("lipassemedic").style.display = "none";
                        else document.getElementById("lipassemedic").style.display = "list-item";
                        if(donnees.allergies ==0) document.getElementById("liallergies").style.display = "none";
                        else document.getElementById("liallergies").style.display = "list-item";

                        if(donnees.passedentaire ==0) document.getElementById("lidentaire").style.display = "none";
                        else document.getElementById("lidentaire").style.display = "list-item";
                        if(donnees.protheses ==0) document.getElementById("liprotheses").style.display = "none";
                        else document.getElementById("liprotheses").style.display = "list-item";
                        //
                        if(donnees.interventions ==0) document.getElementById("liinterventions").style.display = "none";
                        else document.getElementById("liinterventions").style.display = "list-item";
                        if(donnees.examparaclinique ==0) document.getElementById("liexamlabo").style.display = "none";
                        else document.getElementById("liexamlabo").style.display = "list-item";

                        //
                        performAction();
                        var idpatients = parseInt($("#idpatient").val());
                        var cabinetId = parseInt($("#cabinetdentaireId").val());
                        if((idpatients > 0) && (idService ==cabinetId)){
                            getCabinetDentaireData(idpatients);
                        }
                        if(!activeRechHopital) activeRechHopital = true;
                    }
                );
            }


            function selectconstante(valeur){
                document.getElementById("idconstante").style.display = "block";
                $('#idconstante').val(valeur);
                $('.bs-example-modal-lg').modal('hide');
                document.getElementById("idconstante").style.display = "none";
                // Now use AJAX to pick the PATIENT id :
                var monurl = $("#monurl").val();
                // Get PATIENT's ID :
                $.get(monurl+"getpatidbyidcons/"+valeur,
                    function(data) {
                        //
                        var donnees = {};
                        $(data).find('item').each(function(){
                            donnees.idpat = parseInt($(this).find('idpat').text());
                            donnees.nom = $(this).find('nom').text();
                            donnees.prenom = $(this).find('prenom').text();
                        });

                        // Now, perform :
                        $("#idpatient").val(donnees.idpat);
                        //$('#profession').select2('refresh');
                        $('#idpatient').trigger('change');
                    }
                );
                // Obtenir les CONSTANTES :
                getConstanteData(valeur);
            }

            //   --  DATE
            function checkHour(){
                var retour = true;

                var heurenaiss = $("#heurenaiss").val();
                if (!/^[0-9]{1,2}:[0-5]{1}[0-9]{1}$/.test(heurenaiss)){
                    // Alerter infos
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Le format de l'heure de naissance est incorrect !!!</h3>";
                    window.location = "#infos";
                    retour = false;
                }

                return retour;
            }

            // age
            function checkAge(){
                var retour = true;
                var age = $("#age").val();
                if (!/^[0-9]{1,3}$/.test(age)){
                    // Alerter infos
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un age correct. Exemple : 20 !!!</h3>";
                    window.location = "#infos";
                    retour = false;
                }

                return retour;
            }

            function performCheck(){

                // If a patient has been choosen, go out and stop :
                //var idpatient = parseInt($("#idpatient").val());
                //if(idpatient>0) return true;

                //if(!checkAge()) return false;

                // Check if files have been added :
                var nbre = document.getElementsByClassName("form-control fichier");
                for(var i=1; i < nbre.length; i++){
                    if(nbre[i].files.length === 0){
                        document.getElementById("infos").innerHTML = "<h2>Veuillez uplaoder 1 fichier &agrave; la ligne "+(i)
                            +"</h2>";
                        window.location = "#infos";
                        return false;
                    }
                }

                //
                var nom = $("#nom").val();
                if(nom.length == 0){
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un nom correct !!!</h3>";
                    window.location = "#infos";
                    return false;
                }

                // Prenom :
                var prenom = $("#prenom").val();
                if(prenom.length == 0){
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un pr&eacute;nom correct !!!</h3>";
                    window.location = "#infos";
                    return false;
                }

                // Check if the checkBOx exists :
                /*if($("#commentautre").length){
                    // Get the TEXT :
                    var contenu_comment = $("#commentautre").val();
                    if(contenu_comment.length > 0){
                        if(!$('#autres').is(':checked')){
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>Veuillez cocher la case 'Autres, pr&eacute;cisez', car du texte a &eacute;t&eacute; saisi !!!</h3>";
                            window.location = "#infos";
                            return false;
                        }
                    }
                }
                */


                // Check if the checkBOx exists for MODE DE VIE:
                if($("#autresmodecomment").length){
                    // Get the TEXT :
                    var contenu_commentM = $("#autresmodecomment").val();
                    if(contenu_commentM.length > 0){
                        if(!$('#autresmode').is(':checked')){
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>Veuillez cocher la case 'Autres, pr&eacute;cisez (mode de vie)', car du texte a &eacute;t&eacute; saisi !!!</h3>";
                            window.location = "#infos";
                            return false;
                        }
                    }
                }


                // --->
                if($("#listeconstantes").length) {
                    document.getElementById("idconstante").style.display = "block";
                }

                // Now perform checks on CONSTANTES fields :
                var poids = $("#poids").val();
                var taille = $("#taille").val();
                var tension = $("#tension").val();
                var temperature = $("#temperature").val();
                if(!checkConstante(poids, 0)) return false;
                if(!checkConstante(taille, 1)) return false;
                if(!checkConstante(temperature, 3)) return false;

                // Check POULS :
                var pouls = $("#pouls").val();
                if (!/^[0-9]{1,3}$/.test(pouls)){
                    // Alerter infos
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Le POULS renseign&eacute; est incorrect !</h3>";
                    return false;
                }
                /* Clear ALL messages : */
                var inform = document.getElementById("infos");
                inform.innerHTML = "";

                // Disable the BUTTON :
                $("#butenregistrer").prop('disabled', true);

                return true;
            }


            /**/
            function getConstanteData(idConstante){
                var monurl = $("#monurl").val();
                // Get PATIENT's ID :
                $.get(monurl+"getpatconstbyidcons/"+idConstante,
                    function(data) {
                        //
                        var donnees = {};
                        var idSer = 0;
                        $(data).find('item').each(function(){
                            $("#poids").val($(this).find('poids').text());
                            $("#taille").val($(this).find('taille').text());
                            $("#tensionarterielle").val($(this).find('tensionarterielle').text());
                            $("#temperature").val($(this).find('temperature').text());
                            $("#pouls").val($(this).find('pouls').text());
                            idSer = parseInt($(this).find('idser').text());
                        });

                        if(idSer > 0){
                            $("#servicelib").val(idSer);
                            $('#servicelib').trigger('change');
                        }
                    }
                );
            }


            /* idpatient*/
            function getCabinetDentaireData(idpatient){

                var checkfirstuser = parseInt($("#checkfirstuser").val());
                if(checkfirstuser == 0) return;

                // CABINET DENTAIRE DATA :
                $.get(monurl+"getcabinetdata/"+idpatient,
                    function(data) {
                        //
                        var donnees = {};
                        var checkDATA = 0;
                        $(data).find('item').each(function(){
                            checkDATA = 1;
                            $("#cardiopathie").val($(this).find('cardiopathie').text());
                            $("#troubles").val($(this).find('troubles').text());
                            $("#hta").val($(this).find('hta').text());
                            $("#diabetedent").val($(this).find('diabetedent').text());
                            $("#asthme").val($(this).find('asthme').text());
                            $("#infectionsorl").val($(this).find('infectionsorl').text());
                            $("#vih").val($(this).find('vih').text());
                            $("#antibiotique").val($(this).find('antibiotique').text());
                            $("#anesthesie").val($(this).find('anesthesie').text());
                            $("#quinine").val($(this).find('quinine').text());
                            $("#latex").val($(this).find('latex').text());
                            $("#autresdentaire").val($(this).find('autresdentaire').text());
                            $("#dentsmanquantes").val($(this).find('dentsmanquantes').text());
                            /************/
                            $("#hygienebuccale").val(parseInt($(this).find('hygienebuccale').text()));
                            $("#articuledentaire").val(parseInt($(this).find('articuledentaire').text()));
                            $("#langue").val(parseInt($(this).find('langue').text()));
                            $("#suceur").val(parseInt($(this).find('suceur').text()));
                            $("#orthodontique").val(parseInt($(this).find('orthodontique').text()));
                            $("#appareillage").val(parseInt($(this).find('appareillage').text()));
                        });

                        if(checkDATA > 0){
                            // Update the drop down list
                            $('#hygienebuccale').trigger('change');
                            $('#articuledentaire').trigger('change');
                            $('#langue').trigger('change');
                            $('#suceur').trigger('change');
                            $('#orthodontique').trigger('change');
                            $('#appareillage').trigger('change');
                        }
                    }
                );

                // PROTHESE DATA :
                $.get(monurl+"getcabinetdataproth/"+idpatient,
                    function(data) {
                        //
                        var tableau = [];
                        var idSer = 0;
                        $(data).find('item').each(function(){
                            var donnees = {};
                            donnees.enplace = $(this).find('enplace').text();
                            donnees.localisation = $(this).find('localisation').text();
                            donnees.typeproth = $(this).find('typeproth').text();
                            donnees.dateprothese = $(this).find('dateprothese').text();
                            //
                            tableau.push(donnees);
                        });

                        //alert('cptr_prothese : '+cptr_prothese);
                        if(tableau.length > 0){

                            cptr_prothese++;
                            var tp = "abcd"+cptr_prothese;

                            // get current date value :
                            var getDate = $("#datevalue").val();

                            // tabprothese
                            var idTab = "#tabprothese";
                            var $tableBody = $(idTab);

                            // browse
                            for(var j=0; j < tableau.length ; j++){

                                var res = tableau[j].dateprothese.split("T");
                                var getChiffre = res[0].split("-");
                                var setOurDat = getChiffre[1]+"/"+getChiffre[2]+"/"+getChiffre[0];

                                if(j==0){
                                    $("#enplace").val(tableau[j].enplace);
                                    $("#localisation").val(tableau[j].localisation);
                                    $("#typeproth").val(tableau[j].typeproth);
                                    $("#dateprothese").val(setOurDat);
                                }
                                else{
                                    var $trLast = $tableBody.find("tr:last");
                                    var $trNew = $trLast.clone(true);

                                    $trNew.find("td:eq(0)").html("<input name='enplace[]' value='"+tableau[j].enplace+"' maxlength='100' type='text' class='form-control'>");
                                    $trNew.find("td:eq(1)").html("<input name='localisation[]' value='"+tableau[j].localisation+"' maxlength='100' type='text' class='form-control'>");
                                    $trNew.find("td:eq(2)").html("<input name='typeproth[]' value='"+tableau[j].typeproth+"' maxlength='100' type='text' class='form-control'>");
                                    $trNew.find("td:eq(3)").html("<input id='"+tp+"' name='dateprothese[]' value='"+setOurDat+"' type='text' class='form-control datepicker'>");
                                    $trNew.find("td:last").html('<button type="button" class="ibtnDelPro btn btn-danger" >Supprimer</button>');

                                    //
                                    $("#tabprothese").on("click", ".ibtnDelPro", function (event) {
                                        // Supprimer la ligne correspondante :
                                        $(this).closest("tr").remove();
                                    });

                                    $trLast.after($trNew);
                                    // ----
                                    $("#"+tp).datepicker();
                                }
                            }
                        }
                    }
                );
            }



            function onchangePatient(){
                var idpatient = parseInt($("#idpatient").val());
                // Get PATIENT INFO :
                if(idpatient > 0){
                    //
                    var monurl = $("#monurl").val();
                    $.get(monurl+"getpatientinfo/"+idpatient,
                        function(data) {
                            //
                            //alert("On a des donnees");
                            $(data).find('item').each(function(){
                                var nom = $(this).find('nom').text();
                                var prenom = $(this).find('prenom').text();
                                var cni = $(this).find('cni').text();
                                var telephone = $(this).find('telephone').text();
                                var email = $(this).find('email').text();
                                var sexe = $(this).find('sexe').text();
                                var age = $(this).find('age').text();
                                var profession = $(this).find('profession').text();
                                var adresse = $(this).find('adresse').text();
                                //
                                var datenaissance = $(this).find('datenaissance').text();
                                var tp1 = datenaissance.split("T");
                                var resDate = tp1[0].split("-");
                                $('#datenaissance').val(resDate[1]+"/"+resDate[2]+"/"+resDate[0]);

                                //
                                $('#nom').val(nom);
                                $('#prenom').val(prenom);
                                //$('#age').val(age);
                                $('#telephone').val(telephone);
                                $('#email').val(email);
                                // Profession :
                                //document.getElementById('profession').selectedIndex = parseInt(profession);
                                if(parseInt(profession) > 0) {
                                    $("#profession").val(parseInt(profession));
                                    //$('#profession').select2('refresh');
                                    $('#profession').trigger('change');
                                }

                                $('#adresse').val(adresse);
                                // Sexe
                                if(sexe == "F") $("#sexe").val("F"); //document.getElementById('sexe').selectedIndex = 0;
                                else if(sexe == "M") $("#sexe").val("M"); // document.getElementById('sexe').selectedIndex = 1;
                                //$('#sexe').select2('refresh');
                                $('#sexe').trigger('change');

                                $('#cni').val(cni);


                                // SERVICE is 'CABINET DEBTAIRE' ? Yes, get USER DATA back if possible :
                                var idService = parseInt($("#servicelib").val());
                                var cabinetId = parseInt($("#cabinetdentaireId").val());
                                if(idService==cabinetId){
                                    // Call the fonction to pick the appropriate DATA :
                                    getCabinetDentaireData(idpatient);
                                }

                            });
                        }
                    );
                }
                else{
                    //
                    $('#nom').val("");
                    $('#prenom').val("");
                    $('#age').val("");
                    $('#telephone').val("");
                    $('#email').val("");
                    $('#adresse').val("");
                    $('#cni').val("");
                }
            }
      </script>
   </head>
   <body>
      <!-- Begin page -->
      <div id="wrapper">
         <!-- Top Bar Start -->
         <div class="topbar">
            <!-- LOGO -->
            <div class="topbar-left"><a href="#" class="logo"><span><img src="<c:url value='/images/logo-light.png' />" alt="" height="18"> </span>
            <i><img src="<c:url value='/images/logo-sm.png' />" alt="" height="22"></i></a></div>
            <nav class="navbar-custom">
               <ul class="navbar-right d-flex list-inline float-right mb-0">
                  <li class="dropdown notification-list d-none d-md-block">
                     <form role="search" class="app-search">
                        <div class="form-group mb-0"><input type="text" class="form-control" placeholder="Search.."> <button type="submit"><i class="fa fa-search"></i></button></div>
                     </form>
                  </li>
               </ul>

            </nav>
         </div>
         <!-- Top Bar End --><!-- ========== Left Sidebar Start ========== -->
         <div class="left side-menu">
            <div class="slimscroll-menu" id="remove-scroll">
               <!--- Sidemenu -->
               <div id="sidebar-menu">
                  <!-- Left Menu Start -->
                  <ul class="metismenu" id="side-menu">
                     <li class="menu-title">Menu</li>
                     <li>
                         <a href="javascript:void(0);"
                            class="waves-effect"><i class="fas fa-align-justify"></i>
                             <span> Actions <span class="float-right menu-arrow">
                            <i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <c:if test="${!empty lienretour}">
                                <li><a href="${lienretour}">Retour</a></li>
                            </c:if>
                           <li><a href="/gestion/deconnexion">Se d&eacute;connecter</a></li>
                        </ul>
                     </li>

                  </ul>
               </div>
               <!-- Sidebar -->
               <div class="clearfix"></div>
            </div>
            <!-- Sidebar -left -->
         </div>
         <!-- Left Sidebar End --><!-- ============================================================== --><!-- Start right Content here --><!-- ============================================================== -->
         <div class="content-page">
            <!-- Start content -->
            <div class="content">
               <div class="container-fluid">
                  <div class="page-title-box">
                     <div class="row align-items-center">
                         <div class="col-sm-12" style="text-align:center;">
                             <!--<h4 style="float:left; margin-top: 10px">
                             Informations de l'ordonnance</h4>-->
                             <img src="<c:url value='/images/images3.jpg'/>"
                                  Style="width:90px; height:70px"/>
                         </div>
                     </div>
                  </div>
                  <!-- end row -->



                  <c:if test="${!empty listeconstantes}">
                      <div class="row">
                        <div class="col-sm-6 col-md-3 m-t-30">
                            <div class="modal fade bs-example-modal-lg" tabindex="-1"
                                role="dialog" aria-labelledby="myLargeModalLabel"
                                aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title mt-0">Liste des patients avec leurs constantes</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body" id="listeconstantes">
                                                        <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                                            style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                            <thead>
                                                                <tr>
                                                                    <th>Nom </th>
                                                                    <th>Heure</th>
                                                                    <th>Poids</th>
                                                                    <th>Taille</th>
                                                                    <th>Tension</th>
                                                                    <th>Temp&eacute;r.</th>
                                                                    <th>Pouls</th>
                                                                    <th>S&eacute;lect.</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <c:forEach var="patient" items="${listeconstantes}">
                                                                    <tr>
                                                                        <td>${patient[0]}</td>
                                                                        <td>${patient[1]}</td>
                                                                        <td>${patient[2]}</td>
                                                                        <td>${patient[3]}</td>
                                                                        <td>${patient[4]}</td>
                                                                        <td>${patient[6]}</td>
                                                                        <td>${patient[7]}</td>
                                                                        <td>
                                                                            <a title="Afficher les donn&eacute;es" href="javascript:selectconstante('${patient[5]}')" style="color: #78CBF5">
                                                                                <i class="far fa-address-card"></i>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                      </div>
                  </c:if>




                  <div class="row">
                    <div class="col-sm-12">
                    	<div class="card">
                    		<div class="card-body">
                    			<h4 class="mt-0 header-title">Informations</h4>
                    			<p class="text-muted m-b-30 font-14">Veuillez renseigner les informations relatives &agrave; la consultation</p>

                    			<form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                    			    action="${(!empty consultation) ? consultationid : '/gestion/enregconsultation'}"
                    			    method="POST" enctype="multipart/form-data" onsubmit="return performCheck();">
                    				<!--<h3>Seller Details</h3>-->
                    				<fieldset>

                                        <!--  Liste des patients  &  age -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Rechercher Patient</label>
                                        			<div class="col-lg-9">
                                        				<select id="idpatient" name="idpatient" class="form-control select2"
                                        				    onchange="onchangePatient();">
                                        				    <c:if test="${!empty creation}">
                                        				        <option value="0">Ne pas choisir</option>
                                        				    </c:if>
                                                            <c:forEach items="${listepatient}" var="patient" >
                                                                <option value="${patient[0]}">
                                                                  ${ patient[1] }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>

                                        </div>

                    				    <!--  NOM et PRENOM -->
                    					<div class="row">
                    						<div class="col-md-6">
                    							<div class="form-group row">
                    								<label  class="col-lg-3 col-form-label">Nom <span style="color:red">*</span></label>
                    								<div class="col-lg-9">
                    									<input name="nom" id="nom" maxlength="15"
                                                        value="${(!empty consultation) ? consultation[0] : ""}"
                    									type="text" class="form-control">
                    								</div>
                    							</div>
                    						</div>
                    						<div class="col-md-6">
                    							<div class="form-group row">
                    								<label class="col-lg-3 col-form-label">Pr&eacute;nom <span style="color:red">*</span></label>
                    								<div class="col-lg-9">
                    									<input id="prenom" name="prenom" type="text" maxlength="30"
                    									value="${(!empty consultation) ? consultation[1] : ""}"
                    									class="form-control">
                    								</div>
                    							</div>
                    						</div>
                    					</div>


                                        <!--  Profession  -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Profession <span style="color:red">*</span></label>
                                        			<div class="col-lg-9">
                                        				<select name="profession" id="profession" class="form-control select2">
                                                            <c:forEach items="${listeprofession}" var="profession" >
                                                                <option value="${profession.getIdprof()}"
                                                                    ${ (!empty consultation) ? (consultation[10]==profession.getIdprof()) ? "selected" : "" : ""}>
                                                                  ${profession.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label class="col-lg-3 col-form-label">Saisir Profession</label>
                                            		<div class="col-lg-9">
                                            			<input id="saisirprof" name="saisirprof" type="text" maxlength="30"
                                            			value="" placeholder="Saisir la profession si elle n'existe pas !"
                                            			class="form-control">
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>




                    					<!--  SEXE & CNI -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Sexe <span style="color:red">*</span></label>
                                        			<div class="col-lg-9">
                                        			    <select id="sexe" name="sexe" class="form-control select2">
                                                            <option value="F" ${(!empty consultation) ? consultation[2] == 'F' ? "selected" : "" : ""}>F</option>
                                                            <option value="M" ${(!empty consultation) ? consultation[2] == 'M' ? "selected" : "" : ""}>M</option>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>

                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label for="lieunaissance" class="col-lg-3 col-form-label">CNI</label>
                                            		<div class="col-lg-9">
                                            		    <input id="cni" name="cni" type="text" maxlength="30"
                                                            value="${(!empty consultation) ? consultation[3] : ""}"
                                                            class="form-control">
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>

                                        <!--  TELEPHONE   &   EMAIL -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">T&eacute;l&eacute;phone</label>
                                        			<div class="col-lg-9">
                                        				<input id="telephone" name="telephone" maxlength="20"
                                        				value="${(!empty consultation) ? consultation[4] : ''}" type="text" class="form-control">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Email</label>
                                        			<div class="col-lg-9">
                                        				<input id="email" name="email" type="text" class="form-control" maxlength="30"
                                        				value="${(!empty consultation) ? consultation[5] : ''}" >
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>

                                        <!--  Adresse  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Adresse</label>
                                                    <div class="col-lg-9">
                                                        <input id="adresse" name="adresse" type="text" maxlength="30"
                                                               value="${(!empty consultation) ? consultation[8] : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Date naissance <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="datenaissance" id="datenaissance"
                                                               value="${(!empty datenaissance) ? datenaissance : currentdate}"
                                                               type="text" class="form-control datepicker">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>


                    					<!--  HOPITAL   -->
                    					<div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="pere" class="col-lg-3 col-form-label">H&ocirc;pital</label>
                                        			<div class="col-lg-9">
                                        				<select id="hopitallib" name="hopital" class="form-control select2"
                                                                onchange="onchangeHopital()">
                                                            <c:forEach items="${listehopital}" var="hopital" >
                                                                <option value="${hopital.getIdhop()}"
                                                                    ${ (!empty consultation) ? (consultation[11]==hopital.getIdhop()) ? "selected" : "" : ""}>
                                                                  ${ hopital.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label class="col-lg-3 col-form-label">Envoy&eacute; par</label>
                                            		<div class="col-lg-9">
                                            			<input name="envoyepar" type="text" class="form-control" maxlength="50"
                                            			value="${(!empty consultation) ? consultation[9] : ''}" >
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>

                    					<!--  Service & Date de consultation   -->
                    					<div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Service</label>
                                        			<div class="col-lg-9">
                                        				<select id="servicelib" name="service" class="form-control select2"
                                        				    onchange="onchangeService()">
                                                            <c:forEach items="${listeservices}" var="service" >
                                                                <option value="${service.getIdser()}"
                                                                    ${ (!empty consultation) ? (consultation[12]==service.getIdser()) ? "selected" : "" : ""}>
                                                                  ${ service.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="pere" class="col-lg-3 col-form-label">Date de consultation</label>
                                        			<div class="col-lg-9">
                                        				<input name="dateconsultation" value="${(!empty consultation) ? consultation[13] : currentdate}"
                                                            type="text" class="form-control datepicker">
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>


                                        <!--   Ajouter le bouton CONTEXTUEL pour les constantes :  -->
                                        <c:if test="${!empty listeconstantes}">
                                            <div class="row" >
                                                <!--  Afficher un bouton pour les constantes :  -->
                                                <div class="col-md-6" >
                                                    <div class="form-group row" >
                                                        <!--<label for="pere" class="col-lg-3 col-form-label">Visualiser </label>-->
                                                        <div class="col-lg-6">
                                                            <input class="btn btn-info w-md waves-effect waves-light"
                                                                type="button" id="affconstante" value="Afficher les constantes" >
                                                            <input name="idconstante" id="idconstante" value="0"
                                                                type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

                                        <!--  CONSTANTE MEDICALE  -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h4 class="mt-0 header-title">Constantes m&eacute;dicales</h4>

                                                        <!--  POIDS   &   TAILLE -->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group row">
                                                                    <label class="col-lg-3 col-form-label">Poids </label>
                                                                    <div class="col-lg-9">
                                                                        <input maxlength="5" id="poids" name="poids"
                                                                               value="${(!empty constante) ? constante.getPoids() : '0'}" type="text" class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group row">
                                                                    <label class="col-lg-3 col-form-label">Taille </label>
                                                                    <div class="col-lg-9">
                                                                        <input maxlength="5" id="taille" name="taille" type="text" class="form-control"
                                                                               value="${(!empty constante) ? constante.getTaille() : '0'}" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!--  temperature -->
                                                        <div class="row">

                                                            <div class="col-md-6">
                                                                <div class="form-group row">
                                                                    <label class="col-lg-3 col-form-label">Temp&eacute;rature </label>
                                                                    <div class="col-lg-9">
                                                                        <input maxlength="5" name="temperature" id="temperature" type="text" class="form-control"
                                                                               value="${(!empty constante) ? constante.getTemperature() : '0'}" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <!--  Tension arterielle  et POULS -->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group row">
                                                                    <label class="col-lg-3 col-form-label">Tension art&eacute;rielle </label>
                                                                    <div class="col-lg-9">
                                                                        <input maxlength="10" name="tensionarterielle" id="tensionarterielle" type="text" placeholder="..." class="form-control"
                                                                               value="${(!empty constante) ? constante.getTensionarterielle() : ''}" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group row">
                                                                    <label class="col-lg-3 col-form-label">Pouls </label>
                                                                    <div class="col-lg-9">
                                                                        <input maxlength="5" name="pouls" id="pouls" type="text" placeholder="0" class="form-control"
                                                                               value="${(!empty constante) ? constante.getPouls() : '0'}" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!--  OBSERVATION MEDICALE  -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h4 class="mt-0 header-title">Observation m&eacute;dicale</h4>
                                                        <!-- Nav tabs -->
                                                        <ul class="nav nav-tabs" role="tablist">
                                                            <li class="nav-item" id="limotif">
                                                                <a id="amotif" class="nav-link active" data-toggle="tab"
                                                                    href="#divmotif" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-home"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Motif</span>
                                                                </a>
                                                            </li>
                                                            <!-- Interrogatoire -->
                                                            <li class="nav-item" id="liinterrogatoire">
                                                                <a id="ainterrogatoire" class="nav-link" data-toggle="tab"
                                                                   href="#divinterrogatoire" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Interrogatoire</span>
                                                                </a>
                                                            </li>
                                                            <!-- Examen physique -->
                                                            <li class="nav-item" id="liexamenphysique">
                                                                <a id="aexamenphysique" class="nav-link" data-toggle="tab"
                                                                   href="#divexamenphysique" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Examen physique</span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item" id="liantecedent">
                                                                <a id="aantecedent" class="nav-link" data-toggle="tab"
                                                                    href="#divantecedent" role="tab">
                                                                        <span class="d-block d-sm-none">
                                                                            <i class="far fa-user"></i>
                                                                        </span>
                                                                        <span class="d-none d-sm-block">Ant&eacute;c&eacute;dents</span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item" id="limodevie">
                                                                <a id="amodedevie" class="nav-link" data-toggle="tab"
                                                                    href="#divmodedevie" role="tab">
                                                                        <span class="d-block d-sm-none">
                                                                            <i class="far fa-envelope"></i>
                                                                        </span>
                                                                        <span class="d-none d-sm-block">Mode de vie</span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item" id="liexamenclin">
                                                                <a id="aexamenclinique" class="nav-link" data-toggle="tab"
                                                                    href="#divexamenclinique" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Examen clinique</span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item" id="liconclusion">
                                                                <a id="aconclusion" class="nav-link" data-toggle="tab"
                                                                    href="#divconclusion" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Conclusion</span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item" id="lidiagnostic">
                                                                <a id="adiagnostic" class="nav-link" data-toggle="tab"
                                                                    href="#divdiagnostic" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Hypoth&egrave;se(s) diagnostic(s)</span>
                                                                </a>
                                                            </li>
                                                            <!-- EXAMENS  -->
                                                            <li class="nav-item" id="liexamlabo">
                                                                <a id="aexamlabo" class="nav-link" data-toggle="tab"
                                                                   href="#divexamlabo" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Examens paracliniques</span>
                                                                </a>
                                                            </li>
                                                            <!-- DIAGNOSTIC RETENU  -->
                                                            <li class="nav-item" id="lidiagnosticret">
                                                                <a id="adiagnosticret" class="nav-link" data-toggle="tab"
                                                                   href="#divdiagnosticret" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Diagnostic retenu</span>
                                                                </a>
                                                            </li>
                                                            <!-- HOSPITALISATION  -->
                                                            <li class="nav-item" id="lihospitalisation">
                                                                <a id="ahospitalisation" class="nav-link" data-toggle="tab"
                                                                   href="#divhospitalisation" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Hospitalisation</span>
                                                                </a>
                                                            </li>
                                                            <!-- SOINS  -->
                                                                <li class="nav-item" id="lisoins">
                                                                <a id="asoins" class="nav-link" data-toggle="tab"
                                                                   href="#divsoins" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Soins</span>
                                                                </a>
                                                            </li>
                                                            <!-- AVIS CONFRERE  -->
                                                            <li class="nav-item" id="liavisconfrere">
                                                                <a id="aavisconfrere" class="nav-link" data-toggle="tab"
                                                                   href="#divavisconfrere" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Avis confr&egrave;re</span>
                                                                </a>
                                                            </li>
                                                            <!-- Ordonnance  -->
                                                            <li class="nav-item" id="liordonnance">
                                                                <a id="aordonnance" class="nav-link" data-toggle="tab"
                                                                   href="#divordonnance" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Ordonnance</span>
                                                                </a>
                                                            </li>
                                                            <!-- TRAITEMENT  -->
                                                            <li class="nav-item" id="litraitement">
                                                                <a id="atraitement" class="nav-link" data-toggle="tab"
                                                                    href="#divtraitement" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Traitement</span>
                                                                </a>
                                                            </li>
                                                            <!-- CONDUITE a TENIR -->
                                                            <li class="nav-item" id="licat">
                                                                <a id="acat" class="nav-link" data-toggle="tab"
                                                                   href="#divcat" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Conduite &agrave; tenir</span>
                                                                </a>
                                                            </li>

                                                            <!--  On 08/12/2020  -->
                                                            <!-- Passé médical -->
                                                            <li class="nav-item" id="lipassemedic">
                                                                <a id="apassemedic" class="nav-link" data-toggle="tab"
                                                                   href="#divpassemedic" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Pass&eacute; m&eacute;dical</span>
                                                                </a>
                                                            </li>
                                                            <!-- Allergies -->
                                                            <li class="nav-item" id="liallergies">
                                                                <a id="aallergies" class="nav-link" data-toggle="tab"
                                                                   href="#divallergies" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Allergies</span>
                                                                </a>
                                                            </li>
                                                            <!-- Passé dentaire -->
                                                            <li class="nav-item" id="lidentaire">
                                                                <a id="adentaire" class="nav-link" data-toggle="tab"
                                                                   href="#divdentaire" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Pass&eacute; dentaire</span>
                                                                </a>
                                                            </li>
                                                            <!-- PROTHESES -->
                                                            <li class="nav-item" id="liprotheses">
                                                                <a id="aprotheses" class="nav-link" data-toggle="tab"
                                                                   href="#divprotheses" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Proth&egrave;ses</span>
                                                                </a>
                                                            </li>
                                                            <!-- Nature des Interventions -->
                                                            <li class="nav-item" id="liinterventions">
                                                                <a id="ainterventions" class="nav-link" data-toggle="tab"
                                                                   href="#divinterventions" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                                                    <span class="d-none d-sm-block">Nature des Interventions</span>
                                                                </a>
                                                            </li>

                                                        </ul>
                                                        <!-- Tab panes -->
                                                        <div class="tab-content">
                                                            <div class="tab-pane active p-3" id="divmotif" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner le motif..." name="motif" class="form-control" rows="7" >${(!empty consultation) ? consultation[14] : ''}</textarea>
                                                                </div>
                                                            </div>
                                                            <!-- divinterrogatoire -->
                                                            <div class="tab-pane p-3" id="divinterrogatoire" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner l'interrogatoire..." name="interrogatoire" class="form-control" rows="7" >${(!empty consultation) ? consultation[38] : ''}</textarea>
                                                                </div>
                                                            </div>
                                                            <!-- divexamenphysique -->
                                                            <div class="tab-pane p-3" id="divexamenphysique" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner l'examen physique..." name="examenphysique" class="form-control" rows="7" >${(!empty consultation) ? consultation[37] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divantecedent" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <input type="checkbox" name="diabete" value="1" ${(!empty consultation) ? (consultation[15]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Diab&egrave;te</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="hypertension" value="1" ${(!empty consultation) ? (consultation[16]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Hypertension</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="pblmecardiaque" value="1" ${(!empty consultation) ? (consultation[17]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Cardiopathie</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="pblmepulmon" value="1" ${(!empty consultation) ? (consultation[18]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Pathologie pulmonaire</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="drepano" value="1" ${(!empty consultation) ? (consultation[19]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Dr&eacute;panocytose</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <br><br>
                                                                    <%--<input type="checkbox" id="autres" name="autres" value="1" ${(!empty consultation) ? (consultation[20]==1) ? "checked='checked'" : "" : ""}>
                                                                        <label>Autres, pr&eacute;cisez :</label>
                                                                    --%>

                                                                    <textarea maxlength="100"  style="font-size: 20px" placeholder="Maximum de 100 caract&egrave;res" name="commentautre" id="commentautre" class="form-control" rows="3" >${(!empty consultation) ? consultation[21] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divmodedevie" role="tabpanel">
                                                                <div class="col-lg-9">

                                                                    <input type="checkbox" name="tabagismeactif" value="1" ${(!empty consultation) ? (consultation[26]==1) ? "checked='checked'" : "" : ""}>
                                                                    <label>Tabagisme actif</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="tabagismepassif" value="1" ${(!empty consultation) ? (consultation[27]==1) ? "checked='checked'" : "" : ""}>
                                                                    <label>Tabagisme passif</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="ethylisme" value="1" ${(!empty consultation) ? (consultation[28]==1) ? "checked='checked'" : "" : ""}>
                                                                    <label>Ethylisme</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="checkbox" name="sedentaire" value="1" ${(!empty consultation) ? (consultation[29]==1) ? "checked='checked'" : "" : ""}>
                                                                    <label>S&eacute;dentaire</label>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <br><br>
                                                                    <input type="checkbox" id="autresmode" name="autresmode" value="1" ${(!empty consultation) ? (consultation[30]==1) ? "checked='checked'" : "" : ""}>
                                                                    <label>Autres, pr&eacute;cisez :</label>
                                                                    &nbsp;&nbsp;
                                                                    <input maxlength="100" placeholder="..." id="autresmodecomment" name="autresmodecomment" type="text" class="form-control"
                                                                           value="${(!empty consultation) ? consultation[31] : ''}" >

                                                                    <%--
                                                                    <select name="modedevie" class="form-control select2">
                                                                        <c:forEach items="${listemodedevies}" var="modedevie" >
                                                                            <option value="${modedevie.getIdmvie()}"
                                                                                ${ (!empty consultation) ? (consultation[22]==modedevie.getIdmvie()) ? "selected" : "" : ""}>
                                                                              ${ modedevie.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    --%>

                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divexamenclinique" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <textarea maxlength="300" style="font-size: 20px" placeholder="examen clinique..." name="examenclinique" class="form-control" rows="7" >${(!empty consultation) ? consultation[23] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divconclusion" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <textarea maxlength="300" style="font-size: 20px" placeholder="Conclusion ..." name="conclusion" class="form-control" rows="7" >${(!empty consultation) ? consultation[24] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divdiagnostic" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <textarea maxlength="300" style="font-size: 20px" placeholder="Hypoth&egrave;se Diagnostic ..." class="form-control" rows="7" name="diagnostic">${(!empty consultation) ? consultation[6] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <div class="tab-pane p-3" id="divtraitement" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <textarea maxlength="300" style="font-size: 20px" placeholder="Traitement ..." class="form-control" rows="7" name="traitement">${(!empty consultation) ? consultation[25] : ''}</textarea>
                                                                </div>
                                                            </div>
                                                            <!-- divcat -->
                                                            <div class="tab-pane p-3" id="divcat" role="tabpanel">
                                                                <div class="col-lg-9">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Conduite &agrave; tenir ..." class="form-control" rows="7" name="conduiteatenir">${(!empty consultation) ? consultation[25] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <!--  EXAMEN LABORATOIRES  -->
                                                            <div class="tab-pane p-3" id="divexamlabo" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <table id="tabexamenlabo" width="100%">
                                                                            <tr>
                                                                                <th width="70%">Examen</th>
                                                                                <th width="15%">Action</th>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <select name="examens[]" class="form-control select2">
                                                                                        <c:forEach items="${liseExamens}" var="examen" >
                                                                                            <option value="${(!empty listeExamsCmd) ? (listeExamsCmd[0][0]== examen.getIdexam()) ? listeExamsCmd[0][0] : examen.getIdexam()  : examen.getIdexam() }"
                                                                                                ${(!empty listeExamsCmd) ? (listeExamsCmd[0][0]== examen.getIdexam()) ? "selected" : "" : "" }>
                                                                                                    ${ (!empty listeExamsCmd) ? (listeExamsCmd[0][1]== examen.getLibelle()) ? listeExamsCmd[0][1] : examen.getLibelle() : examen.getLibelle() }
                                                                                            </option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </td>
                                                                                <td>
                                                                                    <input class="btn btn-info w-md waves-effect waves-light"
                                                                                           type="button" value="Nouvel examen" id="addexamen">
                                                                                </td>
                                                                            </tr>
                                                                            <!-- Display the EXAMS that have been set previously : -->
                                                                            <c:if test="${!empty listeExamsCmd}">

                                                                                <c:forEach items="${listeExamsCmd}" var="listex" begin="1">
                                                                                    <tr>
                                                                                        <td>
                                                                                            <select name="examens[]" class="form-control select2">
                                                                                                <option value="${listex[0]}">
                                                                                                        ${ listex[1] }
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>
                                                                                        <td>
                                                                                            <c:choose>
                                                                                                <c:when test="${listex[2] == 1}">
                                                                                                    <a target="_blank" class="btn btn-success w-md waves-effect waves-light"
                                                                                                       href="/gestion/downloadexamen/${ listex[3] }"
                                                                                                    >T&eacute;l&eacute;charger
                                                                                                    </a>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                    <input class="btn btn-warning w-md waves-effect waves-light supprexam"
                                                                                                           type="button" value="Supprimer examen">
                                                                                                </c:otherwise>
                                                                                            </c:choose>
                                                                                        </td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                        </table>
                                                                    </div>


                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Renseignements cliniques </label>
                                                                            <div class="col-lg-9">
                                                                                <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner..." class="form-control" rows="3" name="renseignementsclin">${(!empty consultation) ? consultation[39] : ''}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row" style="margin-top:20px;">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Examens non d&eacute;finis</label>
                                                                            <div class="col-lg-9">
                                                                                <textarea maxlength="200" style="font-size: 20px" placeholder="Pour les examens ne figurant pas dans la liste !" class="form-control" rows="2" name="examensnondefinis"></textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>


                                                            </div>

                                                            <!--  DIAGNOSTIC Retenu  -->
                                                            <div class="tab-pane p-3" id="divdiagnosticret" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner le diagnostic retenu..." name="diagnosticretenu" class="form-control" rows="7" >${(!empty consultation) ? consultation[32] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <!--  Hospitalisation  -->
                                                            <div class="tab-pane p-3" id="divhospitalisation" role="tabpanel">
                                                                <!--   Choix  & Chambre   -->
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Hospitalisation</label>
                                                                            <div class="col-lg-3">
                                                                                <select name="hospitalisation" id="hospitalisation" class="form-control select2">
                                                                                    <option value="0" ${ (!empty consultation) ? (consultation[33]=='0') ? "selected" : "" : ""}>Non</option>
                                                                                    <option value="1" ${ (!empty consultation) ? (consultation[33]=='1') ? "selected" : "" : ""}>Oui</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Chambre </label>
                                                                            <div class="col-lg-3">
                                                                                <input maxlength="20" placeholder="..." id="chambre" name="chambre"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty hospitalisation) ? hospitalisation.getChambre() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <!--   Lit & Date entrée   -->
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Num&eacute;ro Lit</label>
                                                                            <div class="col-lg-3">
                                                                                <input maxlength="10" placeholder="..." id="lit" name="lit"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty hospitalisation) ? hospitalisation.getLit() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Date entr&eacute;e </label>
                                                                            <div class="col-lg-3">
                                                                                <input name="datentree"
                                                                                value="${(!empty hospitalisation) ? datentree : currentdate}"
                                                                                type="text" class="form-control datepicker">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <!--   Heure & Motif   -->
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Heure</label>
                                                                            <div class="col-lg-3">
                                                                                <input maxlength="10" placeholder="..." id="heurehospi" name="heurehospi"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty hospitalisation) ? hospitalisation.getHeure() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Motif</label>
                                                                            <div class="col-lg-9">
                                                                                <textarea maxlength="400" style="font-size: 20px" placeholder="Renseigner le motif (400 caract&egrave;res max.)..." class="form-control" rows="3" name="motifhospi">${(!empty hospitalisation) ? hospitalisation.getMotif() : ''}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <!--   Bilan biologiques & Radiologique   -->
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Bilan biologique</label>
                                                                            <div class="col-lg-9">
                                                                                <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner..." class="form-control" rows="3" name="bilanbio">${(!empty hospitalisation) ? hospitalisation.getBilanbio() : ''}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Bilan Radiologique</label>
                                                                            <div class="col-lg-9">
                                                                                <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner..." class="form-control" rows="3" name="bilanradio">${(!empty hospitalisation) ? hospitalisation.getBilanradio() : ''}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <!--  Nombre de jour   -->
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Nombre de jour(s)</label>
                                                                            <div class="col-lg-3">
                                                                                <input maxlength="2" placeholder="..." id="nbrejour" name="nbrejour"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty hospitalisation) ? hospitalisation.getNbrejour() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>

                                                            <!--  Soins  -->
                                                            <div class="tab-pane p-3" id="divsoins" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Renseigner les soins..." name="soins" class="form-control" rows="7" >${(!empty consultation) ? consultation[34] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <!--  Avis confrère  -->
                                                            <div class="tab-pane p-3" id="divavisconfrere" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Avis confr&egrave;re ..." name="avisconfrere" class="form-control" rows="7" >${(!empty consultation) ? consultation[35] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <!--  ORDONNANCE  -->
                                                            <div class="tab-pane p-3" id="divordonnance" role="tabpanel">
                                                                <div class="col-lg-6">
                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Ordonnance ..." name="ordonnance" class="form-control" rows="7" >${(!empty consultation) ? consultation[36] : ''}</textarea>
                                                                </div>
                                                            </div>

                                                            <!--  PASSE MEDICAL  -->
                                                            <div class="tab-pane p-3" id="divpassemedic" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Cardiopathie </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="cardiopathie" name="cardiopathie"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getCardiopathie() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Troubles neurop. </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="troubles" name="troubles"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getTroubles() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">HTA </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="hta" name="hta"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getHta() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Diab&egrave;te </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="diabetedent" name="diabetedent"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getDiabetedent() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Asthme </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="asthme" name="asthme"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getAsthme() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Infections ORL, pulmonaires</label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="infectionsorl" name="infectionsorl"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getInfectionsorl() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">VIH </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="vih" name="vih"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getVih() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>




                                                            <!--  ALLERGIES  -->
                                                            <div class="tab-pane p-3" id="divallergies" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Antibiotiques </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="antibiotique" name="antibiotique"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getAntibiotique() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Anesth&eacute;siques locaux </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="anesthesie" name="anesthesie"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getAnesthesie() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Quinine </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="quinine" name="quinine"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getQuinine() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Latex </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="latex" name="latex"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getLatex() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Autres </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="autresdentaire" name="autresdentaire"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getAutresdentaire() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>




                                                            <!--  PASSE DENTAIRE  -->
                                                            <div class="tab-pane p-3" id="divdentaire" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Hygi&egrave;ne buccale </label>
                                                                            <div class="col-lg-6">
                                                                                <select name="hygienebuccale" id="hygienebuccale" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getHygienebuccale()==1) ? "selected" : "" : ""}>Bonne</option>
                                                                                    <option value="0" ${(!empty cabinetdentaire) ? (cabinetdentaire.getHygienebuccale()==0) ? "selected" : "" : ""}>Mauvaise</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Articul&eacute; dentaire </label>
                                                                            <div class="col-lg-6">
                                                                                <select name="articuledentaire" id="articuledentaire" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getArticuledentaire()==1) ? "selected" : "" : ""}>Correct</option>
                                                                                    <option value="0" ${(!empty cabinetdentaire) ? (cabinetdentaire.getArticuledentaire()==0) ? "selected" : "" : ""}>Incorrect</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Langue </label>
                                                                            <div class="col-lg-6">
                                                                                <select name="langue" id="langue" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getLangue()==1) ? "selected" : "" : ""}>Normale</option>
                                                                                    <option value="2" ${(!empty cabinetdentaire) ? (cabinetdentaire.getLangue()==2) ? "selected" : "" : ""}>Macro</option>
                                                                                    <option value="3" ${(!empty cabinetdentaire) ? (cabinetdentaire.getLangue()==3) ? "selected" : "" : ""}>Micro</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Enfant </label>
                                                                            <div class="col-lg-6">
                                                                                <select name="suceur" id="suceur" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getSuceur()==1) ? "selected" : "" : ""}>Suceur de pouce</option>
                                                                                    <option value="0" ${(!empty cabinetdentaire) ? (cabinetdentaire.getSuceur()==0) ? "selected" : "" : ""}>Suceur de doigts</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Pass&eacute; orthodontique </label>
                                                                            <div class="col-lg-6">
                                                                                <select name="orthodontique" id="orthodontique" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getOrthodontique()==1) ? "selected" : "" : ""}>Oui</option>
                                                                                    <option value="0" ${(!empty cabinetdentaire) ? (cabinetdentaire.getOrthodontique()==0) ? "selected" : "" : ""}>Non</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Dents manquantes </label>
                                                                            <div class="col-lg-9">
                                                                                <input maxlength="100" style="font-size: 20px" placeholder="..." id="dentsmanquantes" name="dentsmanquantes"
                                                                                type="text" class="form-control"
                                                                                    value="${(!empty cabinetdentaire) ? cabinetdentaire.getDentsmanquantes() : ''}" >
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-6">
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-3 col-form-label">Appareillage</label>
                                                                            <div class="col-lg-6">
                                                                                <select name="appareillage" id="appareillage" class="form-control select2">
                                                                                    <option value="1" ${(!empty cabinetdentaire) ? (cabinetdentaire.getAppareillage()==1) ? "selected" : "" : ""}>Oui</option>
                                                                                    <option value="0" ${(!empty cabinetdentaire) ? (cabinetdentaire.getAppareillage()==0) ? "selected" : "" : ""}>Non</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>



                                                            <!--  PROTHESES  -->
                                                            <div class="tab-pane p-3" id="divprotheses" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <table id="tabprothese" width="100%">
                                                                            <tr>
                                                                                <th width="40%">En place</th>
                                                                                <th width="15%">Localisation</th>
                                                                                <th width="15%">Type</th>
                                                                                <th width="15%">Date</th>
                                                                                <th width="15%">Action</th>
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <input name="enplace[]" id="enplace" maxlength="100"
                                                                                        value="${(!empty listeprotheses) ? listeprotheses.get(0).getEnplace() : ''}"
                                                                                        type="text" class="form-control">
                                                                                </td>
                                                                                <td>
                                                                                    <input name="localisation[]" id="localisation" maxlength="100"
                                                                                        value="${(!empty listeprotheses) ? listeprotheses.get(0).getLocalisation() : ''}"
                                                                                        type="text" class="form-control">
                                                                                </td>
                                                                                <td>
                                                                                    <input name="typeproth[]" id="typeproth" maxlength="100"
                                                                                        value="${(!empty listeprotheses) ? listeprotheses.get(0).getTypes() : ''}"
                                                                                        type="text" class="form-control">
                                                                                </td>
                                                                                <td>
                                                                                    <input name="dateprothese[]" id="dateprothese"
                                                                                        value="${(!empty listeprotheses) ? listeprotheses.get(0).getDates() : currentdate}"
                                                                                        type="text" class="form-control datepicker">
                                                                                </td>
                                                                                <td>
                                                                                    <input class="btn btn-info w-md waves-effect waves-light"
                                                                                        type="button" value="Ajouter" id="ajouterprothese">
                                                                                </td>
                                                                            </tr>

                                                                            <c:if test="${!empty listeprotheses}">
                                                                                <c:forEach items="${listeprotheses}" var="donnees" begin="1">
                                                                                    <tr>
                                                                                        <td>
                                                                                            <input name="enplace[]" maxlength="100"
                                                                                                value="${ donnees.getEnplace() }"
                                                                                                type="text" class="form-control">
                                                                                        </td>

                                                                                        <td>
                                                                                            <input name="localisation[]" maxlength="100"
                                                                                                value="${ donnees.getLocalisation() }"
                                                                                                type="text" class="form-control">
                                                                                        </td>

                                                                                        <td>
                                                                                            <input name="typeproth[]" maxlength="100"
                                                                                                value="${ donnees.getTypes() }"
                                                                                                type="text" class="form-control">
                                                                                        </td>

                                                                                        <td>
                                                                                            <input name="dateprothese[]"
                                                                                                value="${ donnees.getDates() }"
                                                                                                type="text" class="form-control datepicker">
                                                                                        </td>

                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </c:if>

                                                                		</table>
                                                                	</div>
                                                                </div>
                                                            </div>


                                                            <!--  NATURE DES INTERVENTIONS  -->
                                                            <div class="tab-pane p-3" id="divinterventions" role="tabpanel">
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <table id="tabinterventions" width="100%">
                                                                            <tr>
                                                                                <th width="10%">Date</th>
                                                                                <th width="5%">Dent</th>
                                                                                <th width="75%">Nature des interventions</th>
                                                                                <!--<th width="10%">Action</th>-->
                                                                            </tr>

                                                                            <tr>
                                                                                <td>
                                                                                    <input name="dateintervention[]"
                                                                                        value="${(!empty listeintervention) ? listeintervention.get(0).getDates() : currentdate}"
                                                                                        type="text" class="form-control datepicker">
                                                                                </td>
                                                                                <td>
                                                                                    <input name="dents[]" id="dents" maxlength="2"
                                                                                        value="${(!empty listeintervention) ? listeintervention.get(0).getDent() : '1'}"
                                                                                        type="text" class="form-control">
                                                                                </td>
                                                                                <td>
                                                                                    <textarea maxlength="200" style="font-size: 20px" placeholder="Commentaires..." name="natureintervention[]" class="form-control" rows="2" >${(!empty listeintervention) ? listeintervention.get(0).getCommentaire() : ''}</textarea>
                                                                                </td>

                                                                                <!--<td>
                                                                                       id="ajouterintervention"
                                                                                    <input class="btn btn-info w-md waves-effect waves-light"
                                                                                        type="button" value="Ajouter" >
                                                                                </td>-->
                                                                            </tr>

                                                                            <%--   Add the OLD DATA
                                                                            <c:if test="${!empty listeintervention}">
                                                                                <c:forEach items="${listeintervention}" var="donnees" begin="1">
                                                                                    <tr>
                                                                                        <td>
                                                                                            <input name="dateintervention[]"
                                                                                                value="${ donnees.getDates() }"
                                                                                                type="text" class="form-control datepicker">
                                                                                        </td>

                                                                                        <td>
                                                                                            <input name="dents[]"
                                                                                                value="${ donnees.getDent() }"
                                                                                                type="text" class="form-control">
                                                                                        </td>

                                                                                        <td>
                                                                                            <textarea maxlength="200" placeholder="Commentaires..." name="natureintervention[]" class="form-control" rows="2" >${ donnees.getCommentaire() }</textarea>
                                                                                        </td>

                                                                                        <td>
                                                                                            <input class="btn btn-info w-md waves-effect waves-light"
                                                                                                type="button" value="Tester" >
                                                                                        </td>

                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                            --%>
                                                                		</table>
                                                                	</div>
                                                                </div>
                                                            </div>



                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    &nbsp;
                                                </div>
                                            </div>
                                        </div>

                                        <%--
                                        <div class="row">
                                            <div class="col-md-6" style="font-weight:bold">
                                                Ajoutez des fichiers
                                            </div>
                                        </div>

                                        <div class="row">
                                        	<div class="col-md-12">
                                        		<table id="tabficinfo" width="100%">
                                        		    <tr>
                                        		        <th>Fichier</th>
                                        		        <th>Nature</th>
                                        		        <th>Action</th>
                                        		    </tr>

                                        		    <tr>
                                        		        <td>
                                                            <select name="naturefichier[]" class="form-control select2">
                                                                <c:forEach items="${listenaturefichiers}" var="natfichier" >
                                                                    <option value="${natfichier.getIdntf()}">
                                                                      ${ natfichier.getNom() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                        		        </td>
                                        		        <td>
                                                            <input name="fichiersajoute[]"
                                                                type="file" class="form-control fichier">
                                        		        </td>
                                        		        <td>
                                        		            <input class="btn btn-info w-md waves-effect waves-light"
                                                                type="button" value="Ajouter" id="uploader">
                                        		        </td>
                                        		    </tr>
                                        		</table>
                                        	</div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    &nbsp;
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${!empty listedesfichiers}">
                                            <div class="row">
                                                <div class="col-md-6" style="font-weight:bold">
                                                    Liste des fichiers
                                                </div>
                                            </div>

                                            <!--  Afficher les fichiers :  -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <table border="1" width="80%">
                                                        <tr>
                                                            <th width="40%"><span style="font-weight:bold; color:blue">Fichiers</span></th>
                                                            <th width="20%"><span style="font-weight:bold; color:blue">Nature</span></th>
                                                            <th width="20%"><span style="font-weight:bold; color:blue">Actions</span></th>
                                                        </tr>
                                                        <c:forEach items="${listedesfichiers}" var="fichier" >
                                                            <tr>
                                                                <td>${ fichier[1] }</td>
                                                                <td>${ fichier[2] }</td>
                                                                <td>
                                                                    <a target="_blank" class="btn btn-info w-md waves-effect waves-light"
                                                                        href="/gestion/downloadfile/${ fichier[0] }"
                                                                        >T&eacute;l&eacute;charger
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </c:if>
                                        --%>

                                        <!--  Set the current date value :  -->
                                        <input type="hidden" id="datevalue" value="${(!empty currentdate) ? currentdate : ''}" />
                                        <input type="hidden" id="checkfirstuser" value="${(!empty checkfirstuser) ? checkfirstuser : '0'}" />

                                        <c:if test="${!empty dateRdv}">
                                            <input type="hidden" id="activercheck" value="1" />
                                        </c:if>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    &nbsp;
                                                </div>
                                            </div>
                                        </div>


                                        <!--       E s s a i e     l e s     R D V    -->

                                        <div class="row">
                                            <div class="col-md-6" style="font-weight:bold; text-decoration:underline;">
                                                D&eacute;finir un RDV :
                                            </div>
                                        </div>

                                        <!--  RDV   -->
                                        <div class="row">
                                        	<div class="col-md-12">
                                        		<table id="tabinfordv" width="100%">
                                        		    <tr>
                                        		        <th width="33%">Date</th>
                                        		        <th width="33%">Heure</th>
                                        		        <th width="33%" style="text-align:center;">Confirmer</th>
                                        		    </tr>
                                        		    <tr>
                                        		        <td>
                                        		            <input name="daterdv" value="${(!empty dateRdv) ? dateRdv : currentdate}"
                                                                type="text" class="form-control datepicker">
                                        		        </td>
                                        		        <td>
                                                            <select name="heurerdv" class="form-control select2">
                                                                <c:forEach items="${listeHeure}" var="heure" >
                                                                    <option ${(!empty heureRdv) ? (heureRdv==heure.getIdheu()) ? "selected" : "" : ""} value="${heure.getIdheu()}">
                                                                      ${heure.getLibelle()}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                            <!--
                                        		            <input name="heurerdv" value="${(!empty rdvheure) ? rdvheure : currentheure}"
                                                                type="time" min="08:00:00" max="17:00:00" class="form-control">-->
                                        		        </td>
                                        		        <td style="line-height: 50%; text-align:center;">
                                                            <!--<select name="choixrdv[]" class="form-control select2">
                                                                <option value="0">Non</option>
                                                                <option value="1">Oui</option>
                                                            </select>-->
                                        		            <div>
                                                                <input type="checkbox" name="choixrdv" id="switch0" switch="bool" value="1" checked="checked">
                                                                <label for="switch0" data-on-label="Oui" data-off-label="Non"></label>
                                                            </div>
                                        		            <!--<input name="choixrdv[]"
                                                                value="${(!empty rdvchoix) ? rdvchoix : 0}"
                                                                type="checkbox" class="form-control">
                                                            -->
                                        		        </td>
                                        		        <!--
                                        		        <td>
                                        		            <input class="btn btn-info w-md waves-effect waves-light"
                                                                type="button" value="Ajouter" id="ajouterrdv">
                                        		        </td>
                                        		        -->
                                        		    </tr>

                                                    <!--   Display the RDV
                                                    <c:forEach var="rdv" items="${lesRdv}">
                                                        <tr>
                                                            <td>
                                                                <input name="dateobserv[]"
                                                                    value="${ observ.getDates() }"
                                                                    type="text" class="form-control datepicker">
                                                            </td>
                                                            <td>
                                                                <input name="commobserv[]"
                                                                    value="${observ.getConsultation()}"
                                                                    type="text" class="form-control">
                                                            </td>
                                                            <td>
                                                                <button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    -->
                                        		</table>
                                        	</div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    &nbsp;
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-6" style="font-weight:bold">
                                                Observations
                                            </div>
                                        </div>


                                        <!--  Observations   -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table id="tabinfo" width="100%">
                                                    <tr>
                                                        <th>Date</th>
                                                        <th>Observation</th>
                                                        <th>Action</th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input name="dateobserv[]"
                                                                   value="${(!empty observdate) ? observdate : currentdate}"
                                                                   type="text" class="form-control datepicker">
                                                        </td>
                                                        <td>
                                                            <input name="commobserv[]" maxlength="200"
                                                                   value="${(!empty observcom) ? observcom : ''}"
                                                                   type="text" class="form-control">
                                                        </td>
                                                        <td>
                                                            <input class="btn btn-info w-md waves-effect waves-light"
                                                                   type="button" value="Ajouter" id="ajouter">
                                                        </td>
                                                    </tr>


                                                    <c:forEach var="observ" items="${lesObservations}">
                                                        <tr>
                                                            <td>
                                                                <input name="dateobserv[]"
                                                                       value="${ observ.getDates() }"
                                                                       type="text" class="form-control datepicker">
                                                            </td>
                                                            <td>
                                                                <input name="commobserv[]" maxlength="200"
                                                                       value="${observ.getConsultation()}"
                                                                       type="text" class="form-control">
                                                            </td>
                                                            <td>
                                                                <button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                </table>
                                            </div>
                                        </div>



                                        <div class="row">
                                            <div id="infos" style="color:red;"></div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-sm-6 text-right" style="float: right; margin-bottom: 20px">
                                                <button id="butenregistrer" class="btn btn-primary w-md waves-effect waves-light"
                                                    type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                            </div>
                                        </div>

                                        <!--   HIDE THINGS   -->
                                        <input type="hidden" id="monurl" value="${monurl}" />
                                        <input type="hidden" id="cabinetdentaireId" value="${cabinetdentaireId}" />

                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                  </div>



                  <!-- end row -->
               </div>
               <!-- container-fluid -->
            </div>
            <!-- content -->
            <footer class="footer">
                <!--
                © 2019 Veltrix
				<span class="d-none d-sm-inline-block">- Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesbrand</span>.
				-->
			</footer>
         </div>
         <!-- ============================================================== --><!-- End Right content here --><!-- ============================================================== -->
      </div>
      <!-- END wrapper --><!-- jQuery  -->
	  <script src="<c:url value='/js/jquery.min.js' />"></script>
	  <script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
	  <script src="<c:url value='/js/metisMenu.min.js' />"></script>
	  <script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
	  <script src="<c:url value='/js/waves.min.js' />"></script>

	  <!-- Required datatable js -->
	  <script src="<c:url value='/js/jquery.datatables.min.js' />"></script>
      <script src="<c:url value='/js/datatables.bootstrap4.min.js' />"></script>
      <script src="<c:url value='/js/datatables.buttons.min.js' />"></script>

      <script src="<c:url value='/js/select2.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-datepicker.js' />"></script>
      <script src="<c:url value='/js/bootstrap-touchspin.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-filestyle.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-maxlength.min.js' />"></script>

	  <!-- Datatable init js -->
      <script src="<c:url value='/pages/datatables.init.js' />"></script>

	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>

	  <script>
	        var cptr = 0;
	        var cptr_prothese = 0;
	        var cptr_nature = 0;
	        var cptrfic = 0;
	        var cptrrdv = 0;
	        var cptrexam = 0;

            var monurl = $("#monurl").val();

	        //
            $(document).ready(function() {
                $().ready(function() {
                    $(".form-control.datepicker").datepicker();
                    $(".form-control.select2").select2();

                    // desactiver par defaut :
                    if($("#activercheck").length ) {
                        document.getElementById("switch0").checked = true;
                        //alert("activer switch0 !");
                    }
                    else document.getElementById("switch0").checked = false;

                    //
                    if($("#listeconstantes").length ) {
                        $('.bs-example-modal-lg').modal('toggle');

                        //----->
                        document.getElementById("idconstante").style.display = "none";

                        $('#affconstante').click(function() {
                            $('.bs-example-modal-lg').modal('toggle');
                            //document.getElementById("idconstante").style.display = "block";
                        });
                    }

                    // Supprimer une ligne du tableau Observation
                    $("#tabinfo").on("click", ".ibtnDel", function (event) {
                        // Supprimer la ligne correspondante :
                        $(this).closest("tr").remove();
                    });

                    // Supprimer une ligne du tableau FICHIERS :
                    $("#tabficinfo").on("click", ".ibtnficDel", function (event) {
                        // Supprimer la ligne correspondante :
                        $(this).closest("tr").remove();
                    });

                    // Supprimer une ligne du tableau EXAMEN :
                    $("#tabexamenlabo").on("click", ".supprexam", function (event) {
                        // Supprimer la ligne correspondante :
                        $(this).closest("tr").remove();
                    });

                    // Trigger this :
                    onchangeService();
                });
            });



            // Ajouter des observations :
            $('#ajouter').click(function() {
                cptr++;

                var tp = "abc"+cptr;

                // get current date value :
                var getDate = $("#datevalue").val();

                // tabinfo
                var idTab = "#tabinfo";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                $trNew.find("td:last").html(
                '<button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>');

                $trNew.find("td:first").html("<input id='"+tp+"' name='dateobserv[]' value='"+getDate+"' type='text' class='form-control datepicker'>");

                //
                $("#tabinfo").on("click", ".ibtnDel", function (event) {
                    // Supprimer la ligne correspondante :
                    $(this).closest("tr").remove();
                });

                $trLast.after($trNew);

                // ----
                $("#"+tp).datepicker();

                //$(".form-control.datepicker").datepicker();
            });


            // Ajouter des PROTHESES :
            $('#ajouterprothese').click(function() {
                cptr_prothese++;

                var tp = "abcd"+cptr_prothese;

                // get current date value :
                var getDate = $("#datevalue").val();

                // tabprothese
                var idTab = "#tabprothese";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                $trNew.find("td:last").html('<button type="button" class="ibtnDelPro btn btn-danger" >Supprimer</button>');
                $trNew.find("td:eq(3)").html("<input id='"+tp+"' name='dateprothese[]' value='"+getDate+"' type='text' class='form-control datepicker'>");
                //
                $("#tabprothese").on("click", ".ibtnDelPro", function (event) {
                    // Supprimer la ligne correspondante :
                    $(this).closest("tr").remove();
                });

                $trLast.after($trNew);

                // ----
                $("#"+tp).datepicker();

                //$(".form-control.datepicker").datepicker();
            });


            // Ajouter des NATURE D'INTERVENTIONS' :
            /*
            $('#ajouterintervention').click(function() {
                cptr_nature++;

                var tp = "ae"+cptr_nature;

                // get current date value :
                var getDate = $("#datevalue").val();

                //
                var idTab = "#tabinterventions";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                $trNew.find("td:last").html('<button type="button" class="ibtnDelInt btn btn-danger" >Supprimer</button>');
                $trNew.find("td:first").html("<input id='"+tp+"' name='dateintervention[]' value='"+getDate+"' type='text' class='form-control datepicker'>");
                //
                $("#tabinterventions").on("click", ".ibtnDelInt", function (event) {
                    // Supprimer la ligne correspondante :
                    $(this).closest("tr").remove();
                });

                $trLast.after($trNew);

                // ----
                $("#"+tp).datepicker();

                //$(".form-control.datepicker").datepicker();
            });
            */

            // Ajouter des fichiers :
            /*$('#uploader').click(function() {
                cptrfic++;

                var tp = "abce"+cptrfic;

                // tabinfo
                var idTab = "#tabficinfo";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                // Try something :
                $trNew.find("td:eq(1)").html("<input name='fichiersajoute[]' type='file' class='form-control fichier'>");

                $trNew.find("td:last").html(
                '<button type="button" class="ibtnficDel btn btn-danger" >Supprimer</button>');

                //
                $.get(monurl+"getnaturefichier",
                    function(data) {
                        //
                        var listeNatureId = new Array();
                        var listeNatureLib = new Array();

                        $(data).find('item').each(function(){
                            var id = $(this).find('idntf').text();
                            var libelle = $(this).find('nom').text();

                            listeNatureId.push(id);
                            listeNatureLib.push(libelle);
                        });

                        //
                        var tpn = "<select id='"+tp+"' name='naturefichier[]' class='form-control select2'>";
                        for (i = 0; i < listeNatureId.length; i++) {
                            tpn += "<option value="+listeNatureId[i]+">"+listeNatureLib[i]+
                                "</option>";
                        }
                        tpn += "</select>";

                        //
                        $trNew.find("td:first").html(tpn);

                        // Delete
                        $("#tabficinfo").on("click", ".ibtnficDel", function (event) {
                            // Supprimer la ligne correspondante :
                            $(this).closest("tr").remove();
                        });

                        $trLast.after($trNew);

                        // Action :
                        $("#"+tp).select2();
                    }
                );
            });
            */



            // Ajouter des RDV :
            $('#ajouterrdv').click(function() {
                cptrrdv++;

                var tp = "abc"+cptrrdv;
                var tps = "abc"+cptrrdv;

                // get current date value :
                var getDate = $("#datevalue").val();

                // tabinfo
                var idTab = "#tabinfordv";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                $trNew.find("td:last").html(
                '<button type="button" class="ibtnrdvDel btn btn-danger" >Supprimer</button>');

                $trNew.find("td:first").html(
                "<input id='"+tp+"' name='daterdv[]' value='"+getDate+"' type='text' class='form-control datepicker'>");

                $trNew.find("td:eq(2)").html(
                "<select name='choixrdv[]' class='form-control select2 "+tps+"'><option value='0'>Non</option><option value='1'>Oui</option></select>");
                /*
                "<div> <input type='checkbox' name='choixrdv[]' id='switch"+cptrrdv+"' switch='bool' value='1' >"+
                "<label for='switch"+cptrrdv+"' data-on-label='On' data-off-label='Off'></label></div>");
                */

                //$.get("http://localhost:8080/gestion/getlesheures",
                $.get(monurl+"getlesheures",
                    function(data) {
                        //
                        var listeHeureId = new Array();
                        var listeHeureLib = new Array();

                        $(data).find('item').each(function(){
                            var id = $(this).find('idheu').text();
                            var libelle = $(this).find('libelle').text();

                            listeHeureId.push(id);
                            listeHeureLib.push(libelle);
                        });

                        //
                        var tpn = "<select id='"+tps+"' name='heurerdv[]' class='form-control select2'>";
                        for (i = 0; i < listeHeureId.length; i++) {
                            tpn += "<option value="+listeHeureId[i]+">"+listeHeureLib[i]+
                                "</option>";
                        }
                        tpn += "</select>";

                        //
                        $trNew.find("td:eq(1)").html(tpn);

                        //
                        $("#tabinfordv").on("click", ".ibtnrdvDel", function (event) {
                            // Supprimer la ligne correspondante :
                            $(this).closest("tr").remove();
                        });

                        $trLast.after($trNew);

                        // ----
                        $("#"+tp).datepicker();

                        // Action :
                        $("#"+tps).select2();
                        $("."+tps).select2();
                    }
                );

                //$(".form-control.datepicker").datepicker();
            });


            // Ajouter un nouvel EXAMEN :
            $('#addexamen').click(function() {
                cptrexam++;

                var tp = "abc"+cptrexam;

                // tabinfo
                var idTab = "#tabexamenlabo";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                // Try something :
                $trNew.find("td:last").html(
                    '<button type="button" class="ibtnexamDel btn btn-danger" >Supprimer</button>');

                //
                //
                //$.get("http://localhost:8080/gestion/getexamensliste",
                $.get(monurl+"getexamensliste",
                    function(data) {
                        //
                        var listeExamenId = new Array();
                        var listeExamenLib = new Array();

                        $(data).find('item').each(function(){
                            var id = $(this).find('idexam').text();
                            var libelle = $(this).find('libelle').text();

                            listeExamenId.push(id);
                            listeExamenLib.push(libelle);
                        });

                        //
                        var tpn = "<select id='"+tp+"' name='examens[]' class='form-control select2'>";
                        for (i = 0; i < listeExamenId.length; i++) {
                            tpn += "<option value="+listeExamenId[i]+">"+listeExamenLib[i]+
                                "</option>";
                        }
                        tpn += "</select>";

                        //
                        $trNew.find("td:first").html(tpn);

                        // Delete
                        $("#tabexamenlabo").on("click", ".ibtnexamDel", function (event) {
                            // Supprimer la ligne correspondante :
                            $(this).closest("tr").remove();
                        });

                        $trLast.after($trNew);

                        // Action :
                        $("#"+tp).select2();
                    }
                );


                /*
                $trNew.find("td:first").html("<input id='"+tp+
                "' name='dateobserv[]' value='01/21/2019' type='text' class='form-control datepicker'>");
                */
            });


	  </script>
   </body>
</html>


