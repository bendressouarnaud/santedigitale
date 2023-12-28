<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>Accueil | Gestion des patients</title>
      <meta content="Admin Dashboard" name="description">
      <meta content="Themesbrand" name="author">
      <!--<link rel="shortcut icon" href="/images/favicon.ico">-->
      <!--Chartist Chart CSS
      <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">-->
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
      <!--<link href="<c:url value='/css/app.min.css' />" rel="stylesheet" type="text/css">-->
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
         <!-- Top Bar Start-->
         <div class="topbar">
            <div class="topbar-left"><a href="#" class="logo"><span><img src="<c:url value='/images/logo-light.png' />" alt="" height="18"> </span>
            <i><img src="<c:url value='/images/logo-sm.png' />" alt="" height="22"></i></a></div>
            <nav class="navbar-custom">
               <ul class="navbar-right d-flex list-inline float-right mb-0">
                  <li class="dropdown notification-list d-none d-md-block">
                     <form role="search" class="app-search" onsubmit="return false;">
                     <div class="form-group mb-0"><input type="text" class="form-control" placeholder="Search.."> <button ><i class="fa fa-search"></i></button></div>
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
                           <li><a href="/gestion/createconsultation">Nouvelle consultation</a></li>
                           <li><a href="/gestion/autorisation">Autorisation</a></li>
                           <li><a href="/gestion/patientexterne">Avis Patients externes</a></li>
                           <li><a href="/gestion/dashboard">Tableau de bord</a></li>
                           <li><a href="/gestion/deconnexion">Se d&eacute;connecter</a></li>
                           <!--<li><a href="/gestion/testnvd3">nvd3</a></li>-->
                        </ul>
                     </li>
                     <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="far fa-address-card"></i><span> Management <span class="float-right menu-arrow"><i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="/gestion/patientshabilite">Liste des patients</a></li>
                           <li><a href="/gestion/attentepatients">Liste d'attente patients</a></li>
                           <li><a href="/gestion/listerdv">Liste des RDV</a></li>
                           <li><a href="/gestion/getcalendrierdv">Calendrier des RDV</a></li>
                           <li><a href="/gestion/gesttamponmedecin">Gestion des cachets</a></li>
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
                           <h4 class="page-title">Accueil</h4>
                           <ol class="breadcrumb">
                              <li class="breadcrumb-item active">Gestion des patients</li>
                           </ol>
                        </div>
                     </div>
                  </div>
                  <!-- end row -->
                  <div class="row">
                     <div class="col-xl-3 col-md-6">
                        <div class="card mini-stat bg-primary text-white">
                           <div class="card-body">
                              <div class="mb-4">
                                 <div class="float-left mini-stat-img mr-4"><img src="<c:url value='/images/services-icon/01.png '/>" alt=""></div>
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Consultations</h5>
                                 <h4 class="font-500">${(!empty nbreconsultation) ? nbreconsultation : "0"} </h4>
                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="fab fa-accessible-icon"></i></a></div>
                                 <p class="text-white-50 mb-0">&nbsp;</p>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xl-3 col-md-6">
                        <div class="card mini-stat bg-primary text-white">
                           <div class="card-body">
                              <div class="mb-4">
                                 <div class="float-left mini-stat-img mr-4"><img src="<c:url value='/images/services-icon/02.png' />" alt=""></div>
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Hopitaux</h5>
                                 <h4 class="font-500">${(!empty nbrehopitaux) ? nbrehopitaux : "0"} </h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="fas fa-ambulance"></i></a></div>
                                 <p class="text-white-50 mb-0">&nbsp;</p>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xl-3 col-md-6">
                        <div class="card mini-stat bg-primary text-white">
                           <div class="card-body">
                              <div class="mb-4">
                                 <div class="float-left mini-stat-img mr-4"><img src="<c:url value='/images/services-icon/03.png' />" alt=""></div>
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Sp&eacute;cialit&eacute;s</h5>
                                 <h4 class="font-500">${(!empty nbrespecialite) ? nbrespecialite : "0"} </h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="fas fa-briefcase-medical"></i></a></div>
                                 <p class="text-white-50 mb-0">&nbsp;</p>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xl-3 col-md-6">
                        <div class="card mini-stat bg-primary text-white">
                           <div class="card-body">
                              <div class="mb-4">
                                 <div class="float-left mini-stat-img mr-4"><img src="<c:url value='/images/services-icon/04.png' />" alt=""></div>
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Patients</h5>
                                 <h4 class="font-500">${(!empty nbrepatients) ? nbrepatients : "0"} </h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="far fa-address-card"></i></a></div>
                                 <p class="text-white-50 mb-0">&nbsp;</p>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- end row -->


                  <!-- Add image : -->
                  <div class="row">
                     <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                           <div class="card-body">
                              <div class="float-left mr-4">
                                 <img src="<c:url value='/images/alcool.jpg' />" alt=""
                                      class="img-fluid img-thumbnail rounded-circle thumb-lg">
                              </div>
                              <h5 class="text-primary font-18 mt-0 mb-1">Ethylisme</h5>
                              <p class="font-12 mb-2">Proportion</p>
                              <p class="mb-4" style="font-weight: bold">${(!empty nbreEthylisme) ? nbreEthylisme : "0"}</p>
                              <div class="clearfix"></div>
                              <hr>
                              <!--<p class="mb-0">&nbsp;</p>-->
                           </div>
                        </div>
                     </div>

                     <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                           <div class="card-body">
                              <div class="float-left mr-4">
                                 <img src="<c:url value='/images/diabete.jpg' />" alt=""
                                      class="img-fluid img-thumbnail rounded-circle thumb-lg">
                              </div>
                              <h5 class="text-primary font-18 mt-0 mb-1">Diab&egrave;te</h5>
                              <p class="font-12 mb-2">Proportion</p>
                              <p class="mb-4" style="font-weight: bold">${(!empty nbreDiabete) ? nbreDiabete : "0"}</p>
                              <div class="clearfix"></div>
                              <hr>
                              <!--<p class="mb-0">&nbsp;</p>-->
                           </div>
                        </div>
                     </div>

                     <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                           <div class="card-body">
                              <div class="float-left mr-4">
                                 <img src="<c:url value='/images/hypertension.jpg' />" alt=""
                                      class="img-fluid img-thumbnail rounded-circle thumb-lg">
                              </div>
                              <h5 class="text-primary font-18 mt-0 mb-1">Hypertension</h5>
                              <p class="font-12 mb-2">Proportion</p>
                              <p class="mb-4" style="font-weight: bold">${(!empty nbreHypertension) ? nbreHypertension : "0"}</p>
                              <div class="clearfix"></div>
                              <hr>
                              <!--<p class="mb-0">&nbsp;</p>-->
                           </div>
                        </div>
                     </div>
                  </div>


                  <div class="row">
                     <div class="col-12">
						<div class="card">
							<div class="card-body">

                               <!-- Nav tabs -->
                               <ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                                  <li class="nav-item">
                                     <a class="nav-link active" data-toggle="tab" href="#aujourdhui" role="tab">
                                        <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                                        <span class="d-none d-sm-block">Consultations enregistr&eacute;es aujourd'hui</span>
                                     </a>
                                  </li>
                                  <li class="nav-item">
                                     <a class="nav-link" data-toggle="tab" href="#historique" role="tab">
                                        <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                                        <span class="d-none d-sm-block">Historique des consultations</span>
                                     </a>
                                  </li>
                               </ul>

                               <!-- Tab panes -->
                               <div class="tab-content">
                                  <div class="tab-pane active p-3" id="aujourdhui" role="tabpanel">
                                     <table id="tabaujourdhui" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                        <tr>
                                           <th>Nom </th>
                                           <th>Pr&eacute;nom</th>
                                           <th>H&ocirc;pital</th>
                                           <th>T&eacute;l&eacute;phone</th>
                                           <th>Email</th>
                                           <th>Sexe</th>
                                           <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="patient" items="${listePatientToday}">
                                           <tr>
                                              <td>${patient[0]}</td>
                                              <td>${patient[1]}</td>
                                              <td>${patient[6]}</td>
                                              <td>${patient[2]}</td>
                                              <td>${patient[3]}</td>
                                              <td>${patient[4]}</td>
                                              <td>
                                                 <a title="Afficher l'historique" href="/gestion/afficherhistpatient/${patient[5]}" style="color: #78CBF5">
                                                    <i class="far fa-address-card"></i>
                                                 </a>
                                                 &nbsp;&nbsp;&nbsp;
                                                 <a target="_blank" title="G&eacute;n&eacute;rer le dossier m&eacute;dical" href="/gestion/genererdossierpat/${patient[5]}" style="color: green">
                                                    <i class="fas fa-file-download "></i>
                                                 </a>
                                              </td>
                                           </tr>
                                        </c:forEach>
                                        </tbody>
                                     </table>
                                  </div>

                                  <div class="tab-pane p-3" id="historique" role="tabpanel">
                                     <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                        <tr>
                                           <th>Nom </th>
                                           <th>Pr&eacute;nom</th>
                                           <th>H&ocirc;pital</th>
                                           <th>T&eacute;l&eacute;phone</th>
                                           <th>Email</th>
                                           <th>Sexe</th>
                                           <th>Derni&egrave;re consultation</th>
                                           <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="patient" items="${listePatient}">
                                           <tr>
                                              <td>${patient[0]}</td>
                                              <td>${patient[1]}</td>
                                              <td>${patient[6]}</td>
                                              <td>${patient[2]}</td>
                                              <td>${patient[3]}</td>
                                              <td>${patient[4]}</td>
                                              <td>${patient[7]}</td>
                                              <td>
                                                 <a title="Afficher l'historique" href="/gestion/afficherhistpatient/${patient[5]}" style="color: #78CBF5">
                                                    <i class="far fa-address-card"></i>
                                                 </a>
                                                 &nbsp;&nbsp;&nbsp;
                                                 <a target="_blank" title="G&eacute;n&eacute;rer le dossier m&eacute;dical" href="/gestion/genererdossierpat/${patient[5]}" style="color: green">
                                                    <i class="fas fa-file-download "></i>
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
	  <!--<script src="<c:url value='/pages/dashboard.js' />"></script>-->
	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>

      <script>

         $(document).ready(function() {
            $().ready(function () {
               $('#tabaujourdhui').DataTable();
            });
         });

      </script>
   </body>
</html>


