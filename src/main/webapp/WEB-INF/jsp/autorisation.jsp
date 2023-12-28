
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta charset="utf-8">

      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>GestPAT - Autorisations</title>
      <meta content="Admin Dashboard" name="description">
      <meta content="Themesbrand" name="author">
      <link rel="shortcut icon" href="/images/favicon.ico">
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

	  <!-- Plugins css -->
	  <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-datepicker.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-colorpicker.min.css' />" rel="stylesheet" type="text/css">
	  <link href="<c:url value='/css/bootstrap-touchspin.min.css' />" rel="stylesheet" type="text/css">

      <script>
            function alerter(valeur){
                // Update the link : modifinfo
                document.getElementById("modifinfo").innerHTML =
                    "<a href='/gestion/supprimeraut/"+valeur+"' "+
                    "class='btn btn-warning waves-effect waves-light' >Oui</a>&nbsp;&nbsp;&nbsp;"+
                    "<a href='javascript:fermer()' class='btn btn-primary waves-effect waves-light'>Non</a>"
                $('.bs-example-modal-center').modal('toggle');
            }

            function fermer(){
                $('.bs-example-modal-center').modal('hide');
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
                           <h4 class="page-title">Gestion autorisation</h4>
                        </div>
                     </div>
                  </div>
                  <!-- end row -->

                  <div class="row">
                    <div class="col-sm-12">
                    	<div class="card">
                    		<div class="card-body">
                    			<h4 class="mt-0 header-title">Informations</h4>

                    			<form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                    			    action="/gestion/enregautorisation"
                    			    method="POST" >

                    				<fieldset>
                                        <!--  Liste des patients  &  age -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Rechercher Medecin</label>
                                        			<div class="col-lg-9">
                                        				<select name="idmedecin" class="form-control select2">
                                                            <c:forEach items="${listeMedecin}" var="medecin" >
                                                                <option value="${ medecin.getIdmed() }">
                                                                  ${ medecin.getNom() }&nbsp;${ medecin.getPrenom() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>

                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Choisir patient</label>
                                        			<div class="col-lg-9">
                                        				<select name="idpatient" class="form-control select2">
                                                            <c:forEach items="${listePatient}" var="pat" >
                                                                <option value="${ pat[0] }">
                                                                  ${ pat[1] }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
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



                                        <div class="row">
                                            <div class="col-sm-6 text-right" style="float: right; margin-bottom: 20px">
                                                <button class="btn btn-primary w-md waves-effect waves-light"
                                                    type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                  </div>


                  <div class="row">
                    <div class="col-sm-6 col-md-3 m-t-30">
						<div class="modal fade bs-example-modal-center" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title mt-0">Supprimer cette autorisation ?</h5>
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
									</div>
									<div class="modal-body">
									    <div id="modifinfo"></div>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
					</div>
                  </div>


                  <!-- Display the DATA : -->
                  <div class="row">
                     <div class="col-12">
						<div class="card">
							<div class="card-body">
                            <!--  Diagnostic   -->
                                <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                    style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                    <thead>
                                        <tr>
                                            <th>Nom </th>
                                            <th>Pr&eacute;nom</th>
                                            <th>Date</th>
                                            <th>Patient(s)</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="autorisation" items="${listeAutorisation}">
                                            <tr>
                                                <td>${autorisation[1]}</td>
                                                <td>${autorisation[2]}</td>
                                                <td>${autorisation[3]}</td>
                                                <td>${autorisation[4]}</td>
                                                <td>
                                                    <a title="Supprimer" href="javascript:alerter(${autorisation[0]})" style="color: #78CBF5">
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

	        //
            $(document).ready(function() {
                $().ready(function() {
                    $(".form-control.datepicker").datepicker();
                    $(".form-control.select2").select2();

                    // Supprimer :
                    $("#tabinfo").on("click", ".ibtnDel", function (event) {
                        // Supprimer la ligne correspondante :
                        $(this).closest("tr").remove();
                    });
                });
            });


            $('#ajouter').click(function() {
                cptr++;

                var tp = "abc"+cptr;

                // tabinfo
                var idTab = "#tabinfo";

                var $tableBody = $(idTab);
                var $trLast = $tableBody.find("tr:last");
                var $trNew = $trLast.clone(true);

                $trNew.find("td:last").html(
                '<button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>');

                $trNew.find("td:first").html("<input id='"+tp+"' name='dateobserv[]' value='01/21/2019' type='text' class='form-control datepicker'>");

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


	  </script>
   </body>
</html>


