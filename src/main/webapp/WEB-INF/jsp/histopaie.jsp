<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>Commune | Gestion</title>
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

	  <link href="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"
            	rel="stylesheet">
        <link href="https://themesbrand.com/veltrix/layouts/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css">



   </head>
   <body>
      <!-- Begin page -->
      <div id="wrapper">
         <!-- Top Bar Start -->
         <div class="topbar">
            <!-- LOGO -->
            <div class="topbar-left">
                <a href="/gestion/accueiltaxe" class="logo">
                    <span>
                        <img src="<c:url value='/images/logo-light.png' />" alt="" height="18">
                    </span>
                    <i>
                        <img src="<c:url value='/images/logo-sm.png' />" alt="" height="22">
                    </i>
                </a>
            </div>
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
                     <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="ti-email"></i><span> Actions <span class="float-right menu-arrow"><i class="mdi mdi-chevron-right"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="/gestion/accueilpagetaxe">Retour Accueil</a></li>
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
                              <li class="breadcrumb-item active">Historique des paiements de taxes</li>
                           </ol>
                        </div>
                     </div>
                  </div>

                  <div class="row">
                     <div class="col-12">
						<div class="card">
							<div class="card-body">
								<table id="datatable" class="table table-bordered dt-responsive nowrap"
									style="border-collapse: collapse; border-spacing: 0; width: 100%;">
									<thead>
										<tr>
											<th>Taxe</th>
                                            <th>Montant</th>
                                            <th>Date</th>
                                            <th>Heure</th>
                                            <th>Periode</th>
                                            <th>Agent</th>
                                            <th>Imprim. Ticket</th>
										</tr>
									</thead>
									<tbody>
									    <c:forEach var="liste" items="${listepaiement}">
                                            <tr>
                                                <td>${liste[0]}</td>
                                                <td>${liste[1]}</td>
                                                <td>${liste[2]}</td>
                                                <td>${liste[3]}</td>
                                                <td>${liste[4]}</td>
                                                <td>${liste[5]}</td>
                                                <td>
                                                    <a target="_blank" title="G&eacute;n&eacute;rer ticket"
                                                        href="/gestion/gticket/${liste[6]}" style="color: #2B9F0E">
                                                        <i class="fas fa-angle-double-right"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td colspan="6">
                                                <a href="/gestion/genererhisto/${idvendeuse}" target="_blank">
                                                    G&eacute;n&eacute;rer rapport
                                                </a>
                                            </td>
                                        </tr>
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
				<span class="d-none d-sm-inline-block"></span>.
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

	  <script src="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
      <script src="https://themesbrand.com/veltrix/layouts/plugins/select2/js/select2.min.js"></script>

	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>

   </body>
</html>


