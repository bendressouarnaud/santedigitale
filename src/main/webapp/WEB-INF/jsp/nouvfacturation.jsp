<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 07/06/2020
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>GestPAT - Facturation</title>
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

    <script>

        function searchassurance(){
            // Check first if the PATIENT is ASSURé :
            var checkAssure = parseInt($("#typeclient").val());
            if(checkAssure == 0){
                // Disable some fields :
                $("#couverture").val("0");

                document.getElementById("numclient").disabled = true;
                document.getElementById("assurance").disabled = true;
                document.getElementById("couverture").disabled = true;
            }
            else{
                document.getElementById("numclient").disabled = false;
                document.getElementById("assurance").disabled = false;
                document.getElementById("couverture").disabled = false;
            }
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

        // Check POIDS, TAILLE, TENSION
        function checkConstante(constante, choix){
            var retour = true;

            if (isNaN(parseFloat(constante))) {
                switch(choix){
                    case 0:
                        // couverture
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La couverture est incorrecte !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;

                    case 1:
                        // montant
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Le montant est incorrect !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;
                }
            }

            return retour;
        }


        function performCheck(){

            //
            var couverture = $("#couverture").val();
            var montant = $("#montant").val();
            if(!checkConstante(couverture, 0)) return false;
            if(!checkConstante(montant, 1)) return false;

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

            // Disable ALL :
            document.getElementById("numclient").disabled = false;
            document.getElementById("assurance").disabled = false;
            document.getElementById("couverture").disabled = false;

            //
            return true;
        }

        function onchangePatient(){
            var idpatient = parseInt($("#idpatient").val());
            // Get PATIENT INFO :
            if(idpatient > 0){

                var monurl = $("#monurl").val();

                //
                $.get(monurl+"getpatientfulldatas/"+idpatient,
                    function(data) {
                        //
                        $(data).find('item').each(function(){

                            $('#localisation').val($(this).find('patient').find('localisation').text());
                            $('#nom').val($(this).find('patient').find('nom').text());
                            $('#prenom').val($(this).find('patient').find('prenom').text());
                            $('#jeunefille').val($(this).find('patient').find('nomjeunefille').text());
                            $("#sexe").val($(this).find('patient').find('sexe') == "F" ? "F" : "M");
                            $('#sexe').trigger('change');

                            // 1948-01-01
                            var dateNaissance = $(this).find('patient').find('datenaissance').text();
                            var getDate = dateNaissance.substring(0, 10);
                            var pDate = getDate.split("-");
                            var nouvDate = pDate[1]+"/"+pDate[2]+"/"+pDate[0];
                            $('#datenaissance').val(nouvDate);
                            //

                            $('#lieunaissance').val($(this).find('patient').find('lieunaissance').text());
                            $('#pere').val($(this).find('patient').find('nompere').text());
                            $('#mere').val($(this).find('patient').find('nommere').text());
                            $('#residence').val($(this).find('patient').find('residence').text());
                            $('#telephone').val($(this).find('patient').find('telephone').text());
                            $('#societe').val($(this).find('patient').find('societe').text());
                            $('#profession').val(parseInt($(this).find('patient').find('profession').text()));
                            $('#profession').trigger('change');
                            $('#mail').val($(this).find('patient').find('email').text());
                            //------- PERSONNE à CONTACTER :
                            $('#nomprenom').val($(this).find('patientrecours').find('nomprenom').text());
                            $('#adressepostale').val($(this).find('patientrecours').find('adressepostale').text());
                            $('#telautre').val($(this).find('patientrecours').find('telephone').text());
                        });
                    }
                );
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
                            <li><a href="/gestion/accfacturation">Retour</a></li>
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
                            <img src="<c:url value='/images/argent.jpg'/>"
                                 Style="width:90px; height:70px"/>
                        </div>
                    </div>
                </div>
                <!-- end row -->

                <!-- monif -->
                <c:if test="${!empty messageerreur}">
                    <div class="row">
                        <div class="col-sm-6 col-md-3 m-t-30" id="messageerreur">
                            <div class="modal fade bs-example-modal-center" tabindex="-1"
                                 role="dialog" aria-labelledby="mySmallModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title mt-0">Information</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">x</button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Ce patient est rattach&eacute; au compte
                                            d'un souscripteur dont le solde du compte est
                                                <span style="color:red; font-weight: bold">faible</span> !</p>
                                            <p>Le syst&egrave;me ne peut donc pas d&eacute;biter son compte.</p>
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
                                <h4 class="mt-0 header-title">Frais de consultation</h4>
                                <p class="text-muted m-b-30 font-14">Informations relatives &agrave; au patient</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="${(!empty modification) ? modification : '/gestion/enregfacturation'}"
                                      method="POST" onsubmit="return performCheck();">
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

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Localisation </label>
                                                    <div class="col-lg-9">
                                                        <input name="localisation" id="localisation"
                                                               value="${(!empty patient) ? patient.getLocalisation() : ""}"
                                                               type="text" class="form-control">
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
                                                        <input name="nom" id="nom"
                                                               value="${(!empty patient) ? patient.getNom() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Pr&eacute;nom <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input id="prenom" name="prenom" type="text"
                                                               value="${(!empty patient) ? patient.getPrenom() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  NOM de JEUNE FILLE & SEXE  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Nom jeune fille </label>
                                                    <div class="col-lg-9">
                                                        <input name="jeunefille" id="jeunefille"
                                                               value="${(!empty patient) ? patient.getNomjeunefille() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Sexe <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <select id="sexe" name="sexe" class="form-control select2">
                                                            <option value="F" ${(!empty patient) ? patient.getSexe() == 'F' ? "selected" : "" : ""}>F</option>
                                                            <option value="M" ${(!empty patient) ? patient.getSexe() == 'M' ? "selected" : "" : ""}>M</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  Date & lieu de naissance  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Date naissance <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="datenaissance" id="datenaissance"
                                                               value="${(!empty patient) ? datenaissance : currentdate}"
                                                               type="text" class="form-control datepicker">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Lieu naissance </label>
                                                    <div class="col-lg-9">
                                                        <input name="lieunaissance" id="lieunaissance"
                                                               value="${(!empty patient) ? patient.getLieunaissance() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  Pere & Mere  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Nom P&egrave;re </label>
                                                    <div class="col-lg-9">
                                                        <input name="pere" id="pere"
                                                               value="${(!empty patient) ? patient.getNompere() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Nom M&egrave;re </label>
                                                    <div class="col-lg-9">
                                                        <input name="mere" id="mere"
                                                               value="${(!empty patient) ? patient.getNommere() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  Quartier residence & Numero de telephone  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Quartier r&eacute;sidence </label>
                                                    <div class="col-lg-9">
                                                        <input name="residence" id="residence"
                                                               value="${(!empty patient) ? patient.getResidence() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">T&eacute;l&eacute;phone </label>
                                                    <div class="col-lg-9">
                                                        <input name="telephone" id="telephone"
                                                               value="${(!empty patient) ? patient.getTelephone() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  PROFESSION et SCOIETE -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Employeur </label>
                                                    <div class="col-lg-9">
                                                        <input name="societe" id="societe"
                                                               value="${(!empty patient) ? patient.getSociete() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Profession </label>
                                                    <div class="col-lg-9">
                                                        <select name="profession" id="profession" class="form-control select2">
                                                            <c:forEach items="${listeProf}" var="donnee" >
                                                                <option value="${donnee.getIdprof()}"
                                                                    ${ (!empty facturation) ? (facturation.getAssurance()==donnee.getIdprof()) ? "selected" : "" : ""}>
                                                                        ${donnee.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  MAIL  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Mail </label>
                                                    <div class="col-lg-9">
                                                        <input name="mail" id="mail"
                                                               value="${(!empty patient) ? patient.getEmail() : ""}"
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">

                                            </div>
                                        </div>


                                        <div class="row">
                                            <hr/>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-6 col-form-label" style="font-weight: bold">COUVERTURE ASSURANCE </label>
                                                </div>
                                            </div>
                                        </div>

                                        <!--  TYPE CLIENT -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Type client</label>
                                                    <div class="col-lg-9">
                                                        <select id="typeclient" name="typeclient"
                                                            class="form-control select2" onchange="searchassurance()">
                                                            <option value="0" ${(!empty facturation) ? facturation.getTypeclient() == 0 ? "selected" : "" : ""}>Non Assur&eacute;</option>
                                                            <option value="4" ${(!empty facturation) ? facturation.getTypeclient() == 4 ? "selected" : "" : ""}>Non Assur&eacute; Adhr&eacute;rent</option>
                                                            <option value="1" ${(!empty facturation) ? facturation.getTypeclient() == 1 ? "selected" : "" : ""}>Assur&eacute;</option>
                                                            <option value="2" ${(!empty facturation) ? facturation.getTypeclient() == 2 ? "selected" : "" : ""}>Assur&eacute; Adhr&eacute;rent</option>
                                                            <option value="3" ${(!empty facturation) ? facturation.getTypeclient() == 3 ? "selected" : "" : ""}>Assur&eacute; non Adhr&eacute;rent</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Num. client <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input id="numclient" name="numclient" type="text"
                                                               value="${(!empty facturation) ? facturation.getNumclient() : ""}"
                                                               class="form-control" placeholder="Id. client">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  ASSURANCE  et RECHERCHER -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Assurance</label>
                                                    <div class="col-lg-9">
                                                        <select name="assurance" id="assurance" class="form-control select2">
                                                            <c:forEach items="${listeassurance}" var="assurance" >
                                                                <option value="${assurance.getIdass()}"
                                                                    ${ (!empty facturation) ? (facturation.getAssurance()==assurance.getIdass()) ? "selected" : "" : ""}>
                                                                        ${assurance.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row" >

                                                    <label class="col-lg-3 col-form-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                    <div class="col-lg-9">
                                                        <button class="btn btn-outline-warning waves-effect waves-light"
                                                                type="button" onclick="searchassurance()">
                                                            V&eacute;rifier patient
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>



                                        <!--  COUVERTURE  et MONTANT -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Taux couverture <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input id="couverture" name="couverture" type="text"
                                                               value="${(!empty facturation) ? facturation.getCouverture() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row" >
                                                    <label class="col-lg-3 col-form-label">Montant <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input id="montant" name="montant" type="text"
                                                               value="${(!empty facturation) ? facturation.getMontant() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <!--  Service à voir : -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Service </label>
                                                    <div class="col-lg-9">
                                                        <select name="services" id="services" class="form-control select2">
                                                            <c:forEach items="${listeServices}" var="service" >
                                                                <option value="${service.getIdser()}"
                                                                    ${ (!empty facturation) ? (facturation.getService()==service.getIdser()) ? "selected" : "" : ""}>
                                                                        ${service.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <hr/>
                                        </div>

                                        <!-- Autres informations : -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-6 col-form-label" style="font-weight: bold">PERSONNE &Agrave; CONTACTER </label>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Nom & pr&eacute;nom(s) </label>
                                                    <div class="col-lg-9">
                                                        <input id="nomprenom" name="nomprenom" type="text"
                                                               value="${(!empty patientrecours) ? patientrecours.getNomprenom() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row" >
                                                    <label class="col-lg-3 col-form-label">Adresse postale </label>
                                                    <div class="col-lg-9">
                                                        <input id="adressepostale" name="adressepostale" type="text"
                                                               value="${(!empty patientrecours) ? patientrecours.getAdressepostale() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!-- telephone autres personne : -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">T&eacute;l </label>
                                                    <div class="col-lg-9">
                                                        <input id="telautre" name="telautre" type="text"
                                                               value="${(!empty patientrecours) ? patientrecours.getTelephone() : ""}"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <div class="row">
                                            <div id="infos" style="color:red;"></div>
                                            <div id="solde" style="color:olivedrab;"></div>
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
                                                <button class="btn btn-primary w-md waves-effect waves-light"
                                                        type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                            </div>
                                        </div>

                                        <!--   HIDE THINGS   -->
                                        <input type="hidden" id="monurl" value="${monurl}" />

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
    var cptrfic = 0;

    //
    $(document).ready(function() {
        $().ready(function() {
            $(".form-control.datepicker").datepicker();
            $(".form-control.select2").select2();

            searchassurance();

            // afficher le message d'erreur :
            if($("#messageerreur").length ) {
                $('.bs-example-modal-center').modal('toggle');
            }
        });
    });


</script>
</body>
</html>



