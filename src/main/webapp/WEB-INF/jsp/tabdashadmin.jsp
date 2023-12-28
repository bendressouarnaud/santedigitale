<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 24/08/2020
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>Tableau de bord</title>
    <meta content="Admin Dashboard" name="description">
    <meta content="Themesbrand" name="author">

    <!--
    <meta http-equiv="Content-Security-Policy"
          content="default-src 'self'
          https://stackpath.bootstrapcdn.com/bootstrap/
          https://cdnjs.cloudflare.com/ajax/
          https://cdn.jsdelivr.net/npm/
          https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js
          http://www.gestdp.com/gestion/ 'unsafe-inline' 'unsafe-eval' " />
    -->

    <!--<link rel="shortcut icon" href="/images/favicon.ico">-->
    <!--Chartist Chart CSS
    <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">-->
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>

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
                    <li class="menu-title">Menu principal</li>
                    <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="fas fa-home"></i><span> Actions <span class="float-right menu-arrow"><i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <li><a href="/gestion/habilitation">Habilitations</a></li>
                            <li><a href="/gestion/comptesmedecin">Liste des m&eacute;decins</a></li>
                            <li><a href="/gestion/rapportadmins">Reportings</a></li>
                            <li><a href="/gestion/deconnexion">D&eacute;connexion</a></li>
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
                        <div class="col-sm-6">
                            <h4 class="page-title">Tableau de bord</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">Activit&eacute;s relatives aux consultations
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>


                <!-- nombre de consultation par MEDECIN sur les 5 derniers jours et par mois  -->
                <div class="row">

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">
                                    Nombre de consultation par M&eacute;decin sur les 8 derniers jours.</h4>
                                <div class="card-body">
                                    <canvas id="consultationparmedecin" height="100"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <!-- nombre de consultation par service et par mois  -->
                <div class="row">

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Nombre de consultation par service et par mois </h4>
                                <div class="card-body">
                                    <canvas id="consultationservice" height="100"
                                        class="mypiechart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>



                <!-- Nombre de patients par service -->
                <div class="row">

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Nombre de patients par cat&eacute;gorie d'&acirc;ge</h4>
                                <!--<div class="ct-chart ct-golden-section">-->
                                <div class="card-body">
                                    <canvas id="categorieage" height="100" class="mypiechart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <!-- Nombre de patients par service -->
                <div class="row">

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Nombre de patients par service</h4>

                                <div class="card-body">
                                    <canvas id="patientservice" height="100" class="mypiechart"></canvas>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>










                <!-- Nombre de patients par service
                <div class="row">

                  <div class="col-lg-12">
                      <div class="card">
                          <div class="card-body">
                              <h4 class="mt-0 header-title">Nombre de patients par service</h4>
                              <div class="ct-chart ct-golden-section">
                                  <svg id="patientservice" class="mypiechart"></svg>
                              </div>
                          </div>
                      </div>
                  </div>

                </div>-->


                <!-- end row -->
                <div class="row">
                    <input type="hidden" id="medecinid" value="${medecinid}" />
                    <input type="hidden" id="monurl" value="${monurl}" />
                </div>
                <!-- end row -->
            </div>
            <!-- container-fluid -->
        </div>

    </div>
    <!-- ============================================================== --><!-- End Right content here --><!-- ============================================================== -->
</div>
<!-- END wrapper --><!-- jQuery  -->
<script src="<c:url value='/js/jquery.min.js' />"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/js/metisMenu.min.js' />"></script>
<script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
<script src="<c:url value='/js/waves.min.js' />"></script>

<!-- App js -->
<script src="<c:url value='/js/app.js' />"></script>

<script>

    var historicalBChart = [];
    var tabCouleurs = [];
    var tabCouleursLarge = ['#857E8D','#A79DB2','#E0D4EC',
        '#BEACD0','#9977BA','#965DCE','#7235AF','#7C19DF'];
    //------------------------
    var historicalBChartServ = [];
    var tabCouleursService = [];

    var tampons = {};
    tampons.key = "";
    tampons.values = [];
    //-----------------
    var tamponsPie = [];
    //-----------------
    var tamponsService = {};
    tamponsService.key = "";
    tamponsService.values = [];
    //-----------------
    var tamponsCategorie = {};
    tamponsCategorie.key = "";
    tamponsCategorie.values = [];
    var historicalBChartCat = [];
    var monurl = $("#monurl").val();


    $(document).ready(function(){

        //$.get("http://www.gestdp.com/gestion/getpatientinfotest",

        var medecinid = $("#medecinid").val();
        window.chartColors = {
            red : 'rgb(255, 99, 132)',
            orange : 'rgb(255, 159, 64)',
            yellow : 'rgb(255, 205, 86)',
            green : 'rgb(75, 192, 192)',
            blue : 'rgb(83, 136, 207)',
            purple : 'rgb(153, 102, 255)',
            grey : 'rgb(201, 203, 207)',
            greengrey : 'rgb(38, 197, 187)'
        };

        var tabCouleur = [];
        tabCouleur.push({couleur: 'rgb(0, 124, 177)'});
        tabCouleur.push({couleur: 'rgb(155, 93, 73)'});
        tabCouleur.push({couleur: 'rgb(133, 133, 133)'});
        tabCouleur.push({couleur: 'rgb(255, 159, 64)'});
        tabCouleur.push({couleur: 'rgb(255, 205, 86)'});
        tabCouleur.push({couleur: 'rgb(75, 192, 192)'});
        tabCouleur.push({couleur: 'rgb(83, 136, 207)'});
        tabCouleur.push({couleur: 'rgb(153, 102, 255)'});
        tabCouleur.push({couleur: 'rgb(91, 95, 154)'});
        tabCouleur.push({couleur: 'rgb(16, 124, 28)'});
        tabCouleur.push({couleur: 'rgb(136, 140, 136)'});
        tabCouleur.push({couleur: 'rgb(174, 135, 62)'});
        tabCouleur.push({couleur: 'rgb(195, 200, 17)'});
        tabCouleur.push({couleur: 'rgb(142, 20, 39)'});



        // Cat�gorie d'age :
        $.get(monurl+"trancheageadmin/"+medecinid,
            function(data) {

                var tabLibelle = [];
                var tabTotal = [];

                $(data).find('item').each(function(){
                    tabLibelle.push($(this).find('jour').text());
                    tabTotal.push(parseInt($(this).find('nombre').text()));
                });

                //
                if(tabLibelle.length > 0) {
                    var couleur = Chart.helpers.color;
                    var barChartData = {
                        labels: tabLibelle,
                        datasets: [{
                            label: 'Patient(s)',
                            backgroundColor: couleur(window.chartColors.green).alpha(0.5).rgbString(),
                            borderColor: window.chartColors.green,
                            borderWidth: 1,
                            data: tabTotal
                        }]
                    };

                    //
                    var ctx = document.getElementById('categorieage').getContext('2d');
                    new Chart(ctx, {
                        type: 'bar',
                        data: barChartData,
                        options: {
                            responsive: true,
                            scales: {
                                yAxes: [{
                                    display: true,
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            },
                            legend: {
                                position: 'bottom'
                            },
                            title: {
                                display: false,
                                text: 'Bar'
                            },
                            plugins: {
                                datalabels: {
                                    display: false
                                }
                            }
                        }
                    });
                }
            }
        );







        // Nombre de patients par service :
        $.get(monurl+"patientparservice/"+medecinid,
            function(data) {

                var tabLibelle = [];
                var tabTotal = [];

                $(data).find('item').each(function(){
                    tabLibelle.push($(this).find('jour').text());
                    tabTotal.push(parseInt($(this).find('nombre').text()));
                });

                //
                if(tabLibelle.length > 0) {
                    var couleur = Chart.helpers.color;
                    var barChartData = {
                        labels: tabLibelle,
                        datasets: [{
                            label: 'Patient(s)',
                            backgroundColor: couleur(window.chartColors.blue).alpha(0.5).rgbString(),
                            borderColor: window.chartColors.blue,
                            borderWidth: 1,
                            data: tabTotal
                        }]
                    };

                    //
                    var ctx = document.getElementById('patientservice').getContext('2d');
                    new Chart(ctx, {
                        type: 'bar',
                        data: barChartData,
                        options: {
                            responsive: true,
                            scales: {
                                yAxes: [{
                                    display: true,
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            },
                            legend: {
                                position: 'bottom'
                            },
                            title: {
                                display: false,
                                text: 'Bar'
                            },
                            plugins: {
                                datalabels: {
                                    display: false
                                }
                            }
                        }
                    });
                }
            }
        );


        /* Nombre de consultation par medecin sur les 10 derniers jours  */
        $.get(monurl+"consultationparmedecin/",
            function(data) {
                var j=0;
                var tabDonnees = [];
                var tabTampDonnees = [];
                var tabDates = [];
                var tabCode = [];

                $(data).find('item').each(function(){
                    // Set :
                    var tampons = {};
                    // Now fill :
                    tampons.libelle = $(this).find('services').text();
                    tampons.code = parseInt($(this).find('idservices').text());
                    tampons.mois = $(this).find('mois').text();
                    tampons.valeur = parseInt($(this).find('total').text());

                    // Let's define our approriate ARRAY :
                    if(tabCode.indexOf(tampons.code) == -1){
                        // No, so add id :
                        tabCode.push(tampons.code);
                    }

                    if(tabDates.indexOf(tampons.mois) == -1){
                        // No, so add id :
                        tabDates.push(tampons.mois);
                    }

                    // Add :
                    tabDonnees.push(tampons);
                });

                // Fill the ARRAY with '0' :
                var existe = false;
                var setSigle = "";
                for(var k=0; k < tabDates.length ; k++){
                	for(var j=0; j < tabCode.length ; j++){
                		existe = false;
                		setSigle = "";
                		for(var l=0; l < tabDonnees.length ; l++){
                			if(tabCode[j] == tabDonnees[l].code){
                				// Set it :
                				setSigle = tabDonnees[l].libelle;
                				if(tabDonnees[l].mois == tabDates[k]){
                					existe = true;
                					break;
                				}
                			}
                		}
                		// False ? Yes :
                		if(!existe){
                			// Set it :
                			var tampons = {};
                			tampons.libelle = setSigle;
                			tampons.code = tabCode[j];
                			tampons.mois = tabDates[k];
                			tampons.valeur = 0;
                			tabTampDonnees.push(tampons);
                		}
                	}
                }

                // refresh tabDonnees :
                for(var l=0; l < tabTampDonnees.length ; l++){
                    tabDonnees.push(tabTampDonnees[l]);
                }


                // Organize our DATA in an appropriate ARRAY :
                var tabOrganize = [];
                var tamponCode = [];
                // var tampon = {};
                for(var i=0; i < tabCode.length ; i++){
                    var tampon = {};
                    tampon.mois = [];
                    tampon.valeur = [];

                    // browse per DATE :
                    for(var k=0; k < tabDates.length ; k++){
                        for(var j=0; j < tabDonnees.length ; j++){
                            if(tabCode[i] == tabDonnees[j].code){
                                if(tabDates[k] == tabDonnees[j].mois){
                                    // Get DATA :
                                    tampon.mois.push(tabDonnees[j].mois);
                                    tampon.valeur.push(tabDonnees[j].valeur);
                                    tampon.libelle = tabDonnees[j].libelle;
                                }
                            }
                        }
                    }
                    //
                    tabOrganize.push(tampon);
                }

                // Now, create a table to hold data :
                var tableHold = [];
                var color = Chart.helpers.color;
                for(var j=0; j < tabOrganize.length ; j++){
                    var tampon = {};
                    tableHold.push(
                        {
                            label : tabOrganize[j].libelle,
                            backgroundColor: color(tabCouleur[j].couleur).alpha(0.4).rgbString(),
                            borderColor: tabCouleur[j].couleur,
                            data: tabOrganize[j].valeur,
                            fill: true
                        });
                }

                // SETUP :
                var config = {
                    type: 'line',
                    data: {
                        labels: tabDates,
                        datasets: tableHold
                    },
                    options: {
                        responsive: true,
                        title: {
                            display: false,
                            text: 'Chart.js Line Chart'
                        },
                        tooltips: {
                            mode: 'index'
                        },
                        hover: {
                            mode: 'index'
                        },
                        scales: {
                            xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Dates'
                                }
                            }],
                            yAxes: [{
                                display: true,
                                ticks: {
                                	beginAtZero: true
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Nombre de Consultation'
                                }
                            }]
                        }
                    }
                };

                var ctx = document.getElementById('consultationparmedecin').getContext('2d');
                window.myLine = new Chart(ctx, config);
            }
        );



        $.get(monurl+"consultationservmois/"+medecinid,
            function(data) {
                var j=0;
                var tabDonnees = [];
                var tabCode = [];

                $(data).find('item').each(function(){
                    // Set :
                    var tampons = {};
                    // Now fill :
                    tampons.libelle = $(this).find('services').text();
                    tampons.code = parseInt($(this).find('idservices').text());
                    tampons.mois = $(this).find('mois').text();
                    tampons.valeur = parseInt($(this).find('total').text());

                    // Let's define our approriate ARRAY :
                    if(tabCode.indexOf(tampons.code) == -1){
                        // No, so add id :
                        tabCode.push(tampons.code);
                    }

                    // Add :
                    tabDonnees.push(tampons);
                });

                // Organize our DATA in an appropriate ARRAY :
                var tabOrganize = [];
                var tamponCode = [];
                // var tampon = {};
                for(var i=0; i < tabCode.length ; i++){
                    var tampon = {};
                    tampon.mois = [];
                    tampon.valeur = [];

                    for(var j=0; j < tabDonnees.length ; j++){
                        if(tabCode[i] == tabDonnees[j].code){
                            // Get DATA :
                            tampon.mois.push(tabDonnees[j].mois);
                            tampon.valeur.push(tabDonnees[j].valeur);
                            tampon.libelle = tabDonnees[j].libelle;
                        }
                    }
                    //
                    tabOrganize.push(tampon);
                }

                // Now, create a table to hold data :
                var tableHold = [];
                for(var j=0; j < tabOrganize.length ; j++){
                    var tampon = {};
                    tableHold.push(
                        {
                            label : tabOrganize[j].libelle,
                            backgroundColor: tabCouleur[j].couleur,
                            borderColor: tabCouleur[j].couleur,
                            data: tabOrganize[j].valeur,
                            fill: false
                        });
                }

                // SETUP :
                var config = {
                    type: 'line',
                    data: {
                        labels: tabOrganize[0].mois,
                        datasets: tableHold
                    },
                    options: {
                        responsive: true,
                        title: {
                            display: false,
                            text: 'Chart.js Line Chart'
                        },
                        tooltips: {
                            mode: 'index',
                            intersect: false,
                        },
                        hover: {
                            mode: 'nearest',
                            intersect: true
                        },
                        scales: {
                            xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Mois'
                                }
                            }],
                            yAxes: [{
                                display: true,
                                ticks: {
                                	beginAtZero: true
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Consultation'
                                }
                            }]
                        }
                    }
                };

                var ctx = document.getElementById('consultationservice').getContext('2d');
                window.myLine = new Chart(ctx, config);
            }
        );



        /*
        $.get("http://www.gestdp.com/gestion/getpattest",
            function(data) {
                //
                var tampon2 = {};
                $(data).find('item').each(function(){
                    var jour = $(this).find('jour').text();
                    tampon2.label = jour;
                    tampon2.value = parseInt($(this).find('nombre').text());
                });

            }
        );
        */


        /*
        $.get("http://www.gestdp.com/gestion/getpatientinfotest",
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
                    //alert("Nom : "+nom);
                    console.log("Nom : "+nom);
                });
            }
        );
        */








        // Nombre de patients par service :
        /*$.get("http://localhost:8080/gestion/patientparservice",*/
        /*$.get("http://www.gestdp.com/gestion/patientparservice",
            function(data) {
                var j=0;
                $(data).find('item').each(function(){

                    var tampon2 = {};
                    tampon2.label = $(this).find('jour').text();
                    tampon2.value = parseInt($(this).find('nombre').text());
                    tamponsService.values.push(tampon2);

                });

                // Set our colors :
                for(var k=0; k<2;k++){
                    tabCouleursService.push('#35A0B5');
                }

                tamponsService.key = "Patients";
                historicalBChartServ.push(tamponsService);

                nv.addGraph(function() {
                    var chart = nv.models.multiBarChart()
                        .x(function(d) { return d.label })
                        .y(function(d) { return d.value })
                        .staggerLabels(true)
                        .showControls(false)
                        .duration(250)
                        .color(tabCouleursService)
                        ;
                    chart.groupSpacing(0.5);

                    d3.select('#patientservice')
                        .datum(historicalBChartServ)
                        .call(chart);
                    nv.utils.windowResize(chart.update);
                    return chart;
                });
            }
        );
        */
    });

</script>
</body>
</html>
