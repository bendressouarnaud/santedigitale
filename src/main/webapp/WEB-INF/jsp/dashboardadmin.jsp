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

      <meta http-equiv="Content-Security-Policy"
        content="default-src 'self' https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js http://www.gestdp.com/gestion/ 'unsafe-inline' 'unsafe-eval' " />

      <!--<link rel="shortcut icon" href="/images/favicon.ico">-->
      <!--Chartist Chart CSS
      <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">-->
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

      <link href="<c:url value='/css/nv.d3.css' />" rel="stylesheet" type="text/css" />
      <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js" charset="utf-8"></script>
      <script src="<c:url value='/js/nv.d3.js' />"></script>
      <script src="<c:url value='/js/stream_layers.js' />"></script>

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
                           <li><a href="/gestion/comptespatientsadmin">Liste des patients</a></li>
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

                  <!-- end row -->
                  <div class="row">

				    <div class="col-lg-6">
				    	<div class="card">
				    		<div class="card-body">
				    			<h4 class="mt-0 header-title">Nombre de patients/m&eacute;decin sur les 5 derniers jours</h4>
				    			<div class="ct-chart ct-golden-section">
				    			    <svg id="derniersmois" class="mypiechart"></svg>
				    			</div>
				    		</div>
				    	</div>
				    </div>

				    <div class="col-lg-6">
				    	<div class="card">
				    		<div class="card-body">
				    			<h4 class="mt-0 header-title">Proportion du genre</h4>
				    			<div class="ct-chart ct-golden-section">
				    			    <svg id="test1" class="mypiechart"></svg>
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
				    			<div class="ct-chart ct-golden-section">
				    			    <svg id="categorieage" class="mypiechart"></svg>
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
				    			<div class="ct-chart ct-golden-section">
				    			    <svg id="patientservice" class="mypiechart"></svg>
				    			</div>
				    		</div>
				    	</div>
				    </div>

                  </div>


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
        var tabCouleursLarge = ['#857E8D','#A79DB2','#E0D4EC',
                    '#BEACD0','#9977BA','#965DCE','#7235AF','#7C19DF'];


	    $(document).ready(function(){

            var medecinid = $("#medecinid").val();
            var monurl = $("#monurl").val();

            //
            $.get(monurl+"getpatientmedecin/"+medecinid,
                function(data) {
                    var j=0;
                    $(data).find('item').each(function(){
                        var tampon2 = {};
                        var jour = $(this).find('jour').text();
                        tampon2.label = jour;
                        tampon2.value = parseInt($(this).find('nombre').text());
                        tampons.values.push(tampon2);
                    });

                    // Set our colors :
                    for(var k=0; k<2;k++){
                    	tabCouleurs.push('#35A0B5');
                    }

                    tampons.key = "Patients";
                    historicalBChart.push(tampons);

                    nv.addGraph(function() {
                    	var chart = nv.models.multiBarChart()
                    		.x(function(d) { return d.label })
                    		.y(function(d) { return d.value })
                    		.staggerLabels(true)
                    		.showControls(false)
                    		.duration(250)
                    		.color(tabCouleurs)
                    		;
                    	chart.groupSpacing(0.5);

                    	d3.select('#derniersmois')
                    		.datum(historicalBChart)
                    		.call(chart);
                    	nv.utils.windowResize(chart.update);
                    	return chart;
                    });
                }
            );


            // Catégorie d'age
            $.get(monurl+"trancheageadmin/"+medecinid,
                function(data) {
                    var j=0;
                    $(data).find('item').each(function(){
                        var tampon2 = {};
                        var jour = $(this).find('jour').text();
                        tampon2.label = jour;
                        tampon2.value = parseInt($(this).find('nombre').text());
                        tamponsCategorie.values.push(tampon2);
                        j=j+1;
                    });

                    // Set our colors :
                    var tabColour = [];
                    for(var k=0; k<j ;k++){
                    	tabColour.push(tabCouleursLarge[k]);
                    	//alert("couleur : "+tabCouleursLarge[k]);
                    }

                    tamponsCategorie.key = "Patients";
                    historicalBChartCat.push(tamponsCategorie);

                    nv.addGraph(function() {
                    	var chart = nv.models.multiBarChart()
                    		.x(function(d) { return d.label })
                    		.y(function(d) { return d.value })
                    		.staggerLabels(true)
                    		.showControls(false)
                    		.duration(250)
                    		.color(tabColour)
                    		;
                    	chart.groupSpacing(0.5);

                    	d3.select('#categorieage')
                    		.datum(historicalBChartCat)
                    		.call(chart);
                    	nv.utils.windowResize(chart.update);
                    	return chart;
                    });
                }
            );

            //
            $.get(monurl+"patientgenreadmin/"+medecinid,
                function(data) {
                    var j=0;
                    $(data).find('item').each(function(){
                        var tampon2 = {};
                        var jour = $(this).find('jour').text();
                        tampon2.key = jour;
                        tampon2.y = parseInt($(this).find('nombre').text());
                        tamponsPie.push(tampon2);
                    });

                    var height = 300;
                    var width = 300;
                    nv.addGraph(function() {
                        var chart = nv.models.pieChart()
                            .x(function(d) { return d.key })
                            .y(function(d) { return d.y })
                            .width(width)
                            .height(height)
                            .showTooltipPercent(true);
                        d3.select("#test1")
                            .datum(tamponsPie)
                            .transition().duration(1200)
                            .attr('width', width)
                            .attr('height', height)
                            .call(chart);
                        return chart;
                    });
                }
            );

            // Nombre de patients par service :
            $.get(monurl+"patientparservice/"+medecinid,
                function(data) {
                    var j=0;
                    $(data).find('item').each(function(){
                        var tampon2 = {};
                        var jour = $(this).find('jour').text();
                        tampon2.label = jour;
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

	    });

	  </script>
   </body>
</html>


