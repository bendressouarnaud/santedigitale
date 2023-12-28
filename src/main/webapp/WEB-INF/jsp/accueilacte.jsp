<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>Accueil | GestACT</title>
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
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/dataTables.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/buttons.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">
	  <!-- Responsive datatable examples -->
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/responsive.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">
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
                        <a href="javascript:void(0);" class="waves-effect"><i class="ti-email"></i><span> Actions <span class="float-right menu-arrow"><i class="mdi mdi-chevron-right"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="/gestion/createdeclaration">Nouvelle d&eacute;claration</a></li>
                           <li><a href="/gestion/gestioncommune">Gestion ville</a></li>
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
                           <h4 class="page-title">Accueil</h4>
                           <ol class="breadcrumb">
                              <li class="breadcrumb-item active">Gestion des actes</li>
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
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">D&eacute;clarations</h5>
                                 <h4 class="font-500">${(!empty nbredeclarations) ? nbredeclarations : "0"} <i class="mdi mdi-arrow-up text-success ml-2"></i></h4>
                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="mdi mdi-arrow-right h5"></i></a></div>
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
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Municipalit&eacute;s</h5>
                                 <h4 class="font-500">${(!empty nbrecommunes) ? nbrecommunes : "0"} <i class="mdi mdi-arrow-down text-danger ml-2"></i></h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="mdi mdi-arrow-right h5"></i></a></div>
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
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Hopitaux</h5>
                                 <h4 class="font-500">${(!empty nbrehopital) ? nbrehopital : "0"} <i class="mdi mdi-arrow-up text-success ml-2"></i></h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="mdi mdi-arrow-right h5"></i></a></div>
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
                                 <h5 class="font-16 text-uppercase mt-0 text-white-50">Utilisateurs</h5>
                                 <h4 class="font-500">${(!empty nbreusers) ? nbreusers : "0"} <i class="mdi mdi-arrow-up text-success ml-2"></i></h4>

                              </div>
                              <div class="pt-2">
                                 <div class="float-right"><a href="#" class="text-white-50"><i class="mdi mdi-arrow-right h5"></i></a></div>
                                 <p class="text-white-50 mb-0">&nbsp;</p>
                              </div>
                           </div>
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
											<th>Nom du b&eacute;b&eacute;</th>
											<th>P&eacute;nom du b&eacute;b&eacute;</th>
											<th>Date naiss.</th>
											<th>Heure naiss.</th>
											<th>Sexe</th>
											<th>Date Enreg.</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>

									    <c:forEach var="declaration" items="${declarations}">
                                            <tr>
                                                <td>${declaration[0]}</td>
                                                <td>${declaration[1]}</td>
                                                <td>${declaration[2]}</td>
                                                <td>${declaration[3]}</td>
                                                <td>${(declaration[5]==0) ? 'M' : 'F'}</td>
                                                <td>${declaration[4]}</td>

                                                <td>
                                                    <a target="_blank" title="Afficher la d&eacute;claration" href="/gestion/affichers/${declaration[6]}" style="color: #78CBF5">
                                                        <i class="far fa-address-card"></i>
                                                    </a>

                                                    <c:if test="${(declaration[7] == 1)}">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <a target="_blank" title="G&eacute;n&eacute;rer l'extrait" href="/gestion/affdec/${declaration[6]}" style="color: #2B9F0E">
                                                            <i class='fas fa-angle-double-right'></i>
                                                        </a>
                                                    </c:if>

                                                    <c:if test="${(declaration[7] == 0)}">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <a title="Modifier la d&eacute;claration" href="/gestion/modifier/${declaration[6]}" style="color: red">
                                                            <i class="fas fa-check-double"></i>
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
		<script src="https://themesbrand.com/veltrix/layouts/plugins/datatables/jquery.dataTables.min.js"></script>
		<script src="https://themesbrand.com/veltrix/layouts/plugins/datatables/dataTables.bootstrap4.min.js"></script>
	  <!-- Datatable init js -->
		<script src="<c:url value='/pages/datatables.init.js' />"></script>
	  <!--Chartist Chart-->
	  <script src="https://themesbrand.com/veltrix/layouts/plugins/chartist/js/chartist.min.js"></script>
	  <script src="https://themesbrand.com/veltrix/layouts/plugins/chartist/js/chartist-plugin-tooltip.min.js"></script>
	  <!-- peity JS -->
	  <script src="https://themesbrand.com/veltrix/layouts/plugins/peity-chart/jquery.peity.min.js"></script>
	  <script src="<c:url value='/pages/dashboard.js' />"></script>
	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>
   </body>
</html>


