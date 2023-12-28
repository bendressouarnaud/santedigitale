
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
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


      <script>
        function desactiverId(){
            //alert("TEST");
            document.getElementById("id").disabled = false;
            return true;
        }
      </script>

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
                           <li><a href="/gestion/gesttaxe">Actualiser</a></li>
                           <li><a href="/gestion/creertaxe">Ajouter Taxe</a></li>
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
                              <li class="breadcrumb-item active">Gestion des taxes</li>
                           </ol>
                        </div>
                     </div>
                  </div>


                  <!-- SPACE TO PUT COMMUNE FIELDS -->
                  <c:if test="${(!empty taxes) || (!empty creertaxe)}">
                     <!--  ID et LIBELLE  -->
                     <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                        action="${(!empty taxes) ? '/gestion/editax' : '/gestion/addax'}" method="POST"
                        onsubmit="return desactiverId();">

                         <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label for="id" class="col-lg-3 col-form-label">Id.</label>
                                    <div class="col-lg-9">
                                        <input id="id" name="id" disabled
                                          value="${(!empty taxes) ? taxes.getItaxe() : ""}"
                                        type="text" class="form-control" >
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label for="nom" class="col-lg-3 col-form-label">Nom</label>
                                    <div class="col-lg-9">
                                        <input id="libelle" name="libelle" type="text"
                                        value="${(!empty taxes) ? taxes.getLibelle() : ""}"
                                        class="form-control">
                                    </div>
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
                     </form>

                  </c:if>

                  <div class="row">
                     <div class="col-12">
						<div class="card">
							<div class="card-body">
								<table id="datatable" class="table table-bordered dt-responsive nowrap"
									style="border-collapse: collapse; border-spacing: 0; width: 100%;">
									<thead>
										<tr>
											<th>Libelle</th>
                                            <th>Action</th>
										</tr>
									</thead>
									<tbody>
									    <c:forEach var="taxe" items="${listtaxes}">
                                            <tr>
                                                <td>${ taxe.getLibelle() }</td>
                                                <td>
                                                    <a title="Modifier" href="/gestion/modifiertax/${taxe.getItaxe()}"
                                                        style="color: red">
                                                        <i class="fas fa-check-double"></i>
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


	  <script>
            $(document).ready(function() {
                $().ready(function() {
                    $(".form-control.datepicker").datepicker();
                    $(".form-control.select2").select2();
                });
            });
      </script>

   </body>
</html>


