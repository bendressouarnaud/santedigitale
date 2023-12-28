<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>Accueil | Historique consultation du patient</title>
      <meta content="Admin Dashboard" name="description">
      <meta content="Themesbrand" name="author">
      <!--<link rel="shortcut icon" href="/images/favicon.ico">-->
      <!--Chartist Chart CSS -->
      <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

	  <!-- DataTables -->
	  <link href="<c:url value='/css/datatables.bootstrap4.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/buttons.bootstrap4.css' />" rel="stylesheet" type="text/css">
      <!-- Responsive datatable examples -->
      <link href="<c:url value='/css/responsive.bootstrap4.css' />" rel="stylesheet" type="text/css">
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
                         <a href="javascript:void(0);"
                            class="waves-effect"><i class="fas fa-align-justify"></i>
                             <span> Actions <span class="float-right menu-arrow">
                            <i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="/gestion/createconsultation">Nouvelle consultation</a></li>
                           <li><a href="/gestion/accueilpatient">Accueil</a></li>
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
                        <div class="col-sm-6">
                           <h4 class="page-title">Historique</h4>
                           <ol class="breadcrumb">
                              <li class="breadcrumb-item active">Patient : ${nompatient}</li>
                           </ol>
                        </div>
                     </div>
                  </div>

                  <!-- end row -->
                  <div class="row">
                     <div class="col-12">
						<div class="card">
							<div class="card-body">
								<table id="datatable" class="table table-bordered dt-responsive nowrap"
									style="border-collapse: collapse; border-spacing: 0; width: 100%;">
									<thead>
										<tr>
											<th>Nom </th>
											<th>Dates</th>
											<th>Heure</th>
											<th>Service</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>

									    <c:forEach var="consultation" items="${listeconsultation}">
                                            <tr>
                                                <td>${consultation[0]}</td>
                                                <td>${consultation[1]}</td>
                                                <td>${consultation[2]}</td>
                                                <td>${consultation[4]}</td>
                                                <td>
                                                    <%--  xxx  --%>
                                                    <a target="_blank" title="G&eacute;n&eacute;rer le dossier m&eacute;dical" href="/gestion/genererdossierpat/${consultation[5]}" style="color: #78CBF5">
                                                        <i class="fas fa-file-download"></i>
                                                    </a>

                                                    &nbsp;&nbsp;&nbsp;
                                                    <a title="Modifier la fiche" href="/gestion/modifierconsultation/${consultation[3]}" style="color: red">
                                                        <i class="fas fa-check-double"></i>
                                                    </a>
                                                    &nbsp;&nbsp;&nbsp;
                                                    <a title="G&eacute;rer les ordonnances" href="/gestion/afficherordonnance/${consultation[3]}" style="color: green">
                                                        <i class="fas fa-check-double"></i>
                                                    </a>

                                                    <c:if test = "${consultation[6] > 0}">&nbsp;&nbsp;
                                                        <a target="_blank" title="G&eacute;rer le bulletin d'examens" href="/gestion/genererbulletinesp/${consultation[3]}" style="color: black">
                                                            <i class="fas fa-briefcase-medical"></i>
                                                        </a>
                                                    </c:if>

                                                    <c:if test = "${consultation[7] > 0}">&nbsp;&nbsp;
                                                        <a target="_blank" title="G&eacute;n&eacute;rer l'avis d'hospitalisation"
                                                            href="/gestion/genereravishos/${consultation[3]}" style="color: #A68B6C">
                                                            <i class="fas fa-hospital-symbol"></i>
                                                        </a>

                                                        &nbsp;&nbsp;

                                                        <a title="Ajouter un avis de prolongation d'hospitalisation"
                                                           href="/gestion/ajouteravisprohos/${consultation[3]}" style="color: #ED7F10">
                                                            <i class="fas fa-user-clock"></i>
                                                        </a>
                                                    </c:if>

                                                    <c:if test = "${consultation[8] > 0}">&nbsp;&nbsp;
                                                        <a target="_blank" title="G&eacute;n&eacute;rer l'avis de prolongation d'hospitalisation"
                                                            href="/gestion/genererprolongavishos/${consultation[3]}" style="color: #094EBE">
                                                            <i class="fas fa-book-reader"></i>
                                                        </a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
                  </div>
                  <!-- end row -->
                  <div class="row">



                  </div>
                  <!-- end row -->
                  <div class="row">


                  </div>
                  <!-- end row -->
               </div>
               <!-- container-fluid -->
            </div>
            <!-- content -->
            <footer class="footer">
                <!--
                � 2019 Veltrix
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

	  <!-- Datatable init js -->
	  <script src="<c:url value='/pages/datatables.init.js' />"></script>

	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>
   </body>
</html>


