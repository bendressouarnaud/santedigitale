
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta charset="utf-8">

      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>GestACT - Nouvelle d&eacute;claration</title>
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
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/dataTables.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/buttons.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">
	  <!-- Responsive datatable examples -->
	  <link href="https://themesbrand.com/veltrix/layouts/plugins/datatables/responsive.bootstrap4.min.css"
	  	rel="stylesheet" type="text/css">


	  <!-- Plugins css -->
      <link href="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css"
      	rel="stylesheet">
      <link href="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"
      	rel="stylesheet">
      <link href="https://themesbrand.com/veltrix/layouts/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css">
      <link href="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css"
      rel="stylesheet">

      <script>
        function checkFilePresence(){

            // Check Hour first :
            if(!checkHour()) return false;
            else{
                // Check the hour :
                var heurenaiss = $("#heurenaiss").val();
                var nombres = heurenaiss.split(':');
                if(parseInt(nombres[0]) > 23){
                    document.getElementById("infos").innerHTML = "<h2>L'heure renseign&eacute;e est incorrecte !!!</h2>";
                    window.location = "#infos";
                    return false;
                }
            }

            if($("#fichiersigne").length){
                if(document.getElementById('fichiersigne').files.length === 0){
                    document.getElementById("infos").innerHTML = "<h2>Veuillez ajouter au moins 1 fichier !!!</h2>";
                    window.location = "#infos";
                    return false;
                }
            }
            else return true;
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
                     <li class="menu-title">D&eacute;claration</li>
                     <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="ti-email"></i><span> Declaration <span class="float-right menu-arrow"><i class="mdi mdi-chevron-right"></i></span></span></a>
                        <ul class="submenu">
                           <li><a href="createdeclaration">Enreg. une d&eacute;claration</a></li>
                           <li><a href="/gestion/uploadStatus">Retour accueil</a></li>
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
                           <h4 class="page-title">D&Eacute;CLARATION des actes</h4>
                        </div>
                     </div>
                  </div>
                  <!-- end row -->

                  <div class="row">
                    <div class="col-sm-12">
                    	<div class="card">
                    		<div class="card-body">
                    			<h4 class="mt-0 header-title">Informations</h4>
                    			<p class="text-muted m-b-30 font-14">Veuillez renseigner les informations relatives au nouveau n&eacute;</p>

                    			<form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                    			    action="${(!empty declaration) ? declarationid : '/gestion/enregbebe'}"
                    			    method="POST" enctype="multipart/form-data" onsubmit="return checkFilePresence();">
                    				<!--<h3>Seller Details</h3>-->
                    				<fieldset>

                    				    <!--  NOM et PRENOM bebe -->
                    					<div class="row">
                    						<div class="col-md-6">
                    							<div class="form-group row">
                    								<label for="txtFirstNameBilling" class="col-lg-3 col-form-label">Nom b&eacute;b&eacute;</label>
                    								<div class="col-lg-9">
                    									<input id="txtFirstNameBilling" name="nombebe"
                                                        value="${(!empty declaration) ? declaration.getNombebe() : ""}"
                    									type="text" class="form-control">
                    								</div>
                    							</div>
                    						</div>
                    						<div class="col-md-6">
                    							<div class="form-group row">
                    								<label for="txtLastNameBilling" class="col-lg-3 col-form-label">Pr&eacute;nom b&eacute;b&eacute;</label>
                    								<div class="col-lg-9">
                    									<input id="txtLastNameBilling" name="prenombebe" type="text"
                    									value="${(!empty declaration) ? declaration.getPrenbebe() : ""}"
                    									class="form-control">
                    								</div>
                    							</div>
                    						</div>
                    					</div>


                    					<!--  SEXE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="sexe" class="col-lg-3 col-form-label">Sexe</label>
                                        			<div class="col-lg-9">
                                        			    <select name="sexe" class="form-control select2">
                                                            <option value="0" ${(!empty declaration) ? declaration.getSexe() == 0 ? "selected" : "" : ""}>M</option>
                                                            <option value="1" ${(!empty declaration) ? declaration.getSexe() == 1 ? "selected" : "" : ""}>F</option>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>

                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label for="lieunaissance" class="col-lg-3 col-form-label">N&eacute; &agrave;</label>
                                            		<div class="col-lg-9">
                                            		    <select name="lieunaissance" class="form-control select2">
                                                            <c:forEach items="${lstcommune}" var="commune" >
                                                                <option value="${commune.getIdcom()}"
                                                                  ${(!empty declaration) ? declaration.getLieunaissance()==commune.getIdcom() ? 'selected' : "" : ""} >
                                                                  ${ commune.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>

                                        <!--  DATE et HEURE de naissance -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="datenaiss" class="col-lg-3 col-form-label">Date de naissance</label>
                                        			<div class="col-lg-9">
                                        				<input id="datenaiss" name="datenaiss"
                                        				value="${(!empty declaration) ? dates : '01/21/2019'}" type="text" class="form-control datepicker">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="heurenaiss" class="col-lg-3 col-form-label">Heure de naissance</label>
                                        			<div class="col-lg-9">
                                        				<input id="heurenaiss" name="heurenaiss" type="text" class="form-control"
                                        				value="${(!empty declaration) ? declaration.getHeure() : '20:00'}" >
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


                    					<!--  PERE et NATIONALIE -->
                    					<div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="pere" class="col-lg-3 col-form-label">P&egrave;re</label>
                                        			<div class="col-lg-9">
                                        				<input id="pere" name="pere" type="text" class="form-control"
                                        				    value="${(!empty declaration) ? declaration.getPere() : ''}">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="txtLastNameBilling" class="col-lg-3 col-form-label">Nationalit&eacute; P&egrave;re</label>
                                        			<div class="col-lg-9">
                                        			    <select name="nationpere" class="form-control select2">
                                                            <c:forEach items="${lstnation}" var="nation" >
                                                                <option value="${nation.getIdnat()}"
                                                                  ${(!empty declaration) ? declaration.getNationperer()==nation.getIdnat() ? 'selected' : "" : ""} >
                                                                  ${ nation.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                        			    </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>

                                        <!--  PERE -- DATE NAISSANCE, VILLE NAISSANCE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="datenaisspere" class="col-lg-3 col-form-label">Date naissance</label>
                                        			<div class="col-lg-9">
                                        				<input id="datenaisspere" name="datenaisspere" type="text" class="form-control datepicker"
                                        				value="${(!empty declaration) ? datespere : '01/21/2019'}" >
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="villenaisspere" class="col-lg-3 col-form-label">Ville de naissance</label>
                                        			<div class="col-lg-9">
                                        			    <select name="villenaisspere" class="form-control select2">
                                                            <c:forEach items="${lstcommune}" var="commune" >
                                                                <option value="${commune.getIdcom()}"
                                                                  ${(!empty declaration) ? declaration.getVillenaisspere()==commune.getIdcom() ? 'selected' : "" : ""} >
                                                                  ${ commune.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                        			    </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>

                                        <!--  PERE -- PROFESSION, DOMICILE PERE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        		    <label for="professionpere" class="col-lg-3 col-form-label">Profession</label>
                                                    <div class="col-lg-9">
                                                        <select name="professionpere" class="form-control select2">
                                                            <option value="1" ${(!empty declaration) ? declaration.getProfessionpere() == 1 ? "selected" : "" : ""}>Architecte</option>
                                                            <option value="2" ${(!empty declaration) ? declaration.getProfessionpere() == 2 ? "selected" : "" : ""}>Couturier</option>
                                                            <option value="3" ${(!empty declaration) ? declaration.getProfessionpere() == 3 ? "selected" : "" : ""}>Chef Entreprise</option>
                                                            <option value="4" ${(!empty declaration) ? declaration.getProfessionpere() == 4 ? "selected" : "" : ""}>Comptable</option>
                                                            <option value="5" ${(!empty declaration) ? declaration.getProfessionpere() == 5 ? "selected" : "" : ""}>Technicien Eau</option>
                                                        </select>
                                                    </div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="domicilepere" class="col-lg-3 col-form-label">Domicile</label>
                                                    <div class="col-lg-9">
                                                    	<input id="domicilepere" name="domicilepere" type="text" class="form-control"
                                                    	    value="${(!empty declaration) ? declaration.getDomicilepere() : ''}">
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



                                        <!--  MERE et NATIONALIE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="mere" class="col-lg-3 col-form-label">M&egrave;re</label>
                                        			<div class="col-lg-9">
                                        				<input id="mere" name="mere" type="text" class="form-control"
                                        				    value="${(!empty declaration) ? declaration.getMere() : ''}">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="txtLastNameBilling" class="col-lg-3 col-form-label">Nationalit&eacute; M&egrave;re</label>
                                        			<div class="col-lg-9">
                                        			    <select name="nationmere" class="form-control select2">
                                                            <c:forEach items="${lstnation}" var="nation" >
                                                                <option value="${nation.getIdnat()}"
                                                                  ${(!empty declaration) ? declaration.getNationmere()==nation.getIdnat() ? 'selected' : "" : ""} >
                                                                  ${ nation.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>


                                        <!--  MERE -- DATE NAISSANCE, VILLE NAISSANCE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="datenaissmere" class="col-lg-3 col-form-label">Date naissance</label>
                                        			<div class="col-lg-9">
                                        				<input id="datenaissmere" name="datenaissmere" type="text"
                                        				value="${(!empty declaration) ? datesmere : '01/21/2019'}"
                                        				class="form-control datepicker">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="villenaissmere" class="col-lg-3 col-form-label">Ville de naissance</label>
                                        			<div class="col-lg-9">
                                        			    <select name="villenaissmere" class="form-control select2">
                                                            <c:forEach items="${lstcommune}" var="commune" >
                                                                <option value="${commune.getIdcom()}"
                                                                  ${(!empty declaration) ? declaration.getVillenaissmere()==commune.getIdcom() ? 'selected' : "" : ""} >
                                                                  ${ commune.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                        			    </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>

                                        <!--  MERE -- PROFESSION, DOMICILE PERE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        		    <label for="professionmere" class="col-lg-3 col-form-label">Profession</label>
                                                    <div class="col-lg-9">
                                                        <select name="professionmere" class="form-control select2">
                                                            <option value="1" ${(!empty declaration) ? declaration.getProfessionmere() == 1 ? "selected" : "" : ""}>Architecte</option>
                                                            <option value="2" ${(!empty declaration) ? declaration.getProfessionmere() == 2 ? "selected" : "" : ""}>Couturier</option>
                                                            <option value="3" ${(!empty declaration) ? declaration.getProfessionmere() == 3 ? "selected" : "" : ""}>Chef Entreprise</option>
                                                            <option value="4" ${(!empty declaration) ? declaration.getProfessionmere() == 4 ? "selected" : "" : ""}>Comptable</option>
                                                            <option value="5" ${(!empty declaration) ? declaration.getProfessionmere() == 5 ? "selected" : "" : ""}>M&eacute;nag&egrave;re</option>
                                                            <option value="6" ${(!empty declaration) ? declaration.getProfessionmere() == 6 ? "selected" : "" : ""}>Technicien Eau</option>
                                                        </select>
                                                    </div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="domicilemere" class="col-lg-3 col-form-label">Domicile</label>
                                                    <div class="col-lg-9">
                                                    	<input id="domicilemere" name="domicilemere" type="text"
                                                    	value="${(!empty declaration) ? declaration.getDomicilemere() : ''}"
                                                    	class="form-control">
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



                                        <!--  DECLARATION DE   --    RECUE EN LANGUE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="declarationde" class="col-lg-3 col-form-label">D&eacute;claration de</label>
                                        			<div class="col-lg-9">
                                        				<input id="declarationde" name="declarationde"
                                        				    value="${(!empty declaration) ? declaration.getDeclarationde() : ''}"
                                                            type="text" class="form-control">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="recuelangue" class="col-lg-3 col-form-label">Recue en langue</label>
                                        			<div class="col-lg-9">
                                        				<select name="recuelangue" class="form-control select2">
                                                            <option value="1" ${(!empty declaration) ? declaration.getRecuelangue() == 1 ? "selected" : "" : ""}>Fran&ccedil;aise</option>
                                                            <option value="2" ${(!empty declaration) ? declaration.getRecuelangue() == 2 ? "selected" : "" : ""}>Anglaise</option>
                                                            <option value="3" ${(!empty declaration) ? declaration.getRecuelangue() == 3 ? "selected" : "" : ""}>Baoul&eacute;</option>
                                                            <option value="4" ${(!empty declaration) ? declaration.getRecuelangue() == 4 ? "selected" : "" : ""}>Koulango</option>
                                                            <option value="5" ${(!empty declaration) ? declaration.getRecuelangue() == 5 ? "selected" : "" : ""}>Dioula</option>
                                                            <option value="6" ${(!empty declaration) ? declaration.getRecuelangue() == 6 ? "selected" : "" : ""}>S&eacute;noufo</option>
                                                            <option value="7" ${(!empty declaration) ? declaration.getRecuelangue() == 7 ? "selected" : "" : ""}>B&eacute;t&eacute;</option>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>


                                        <!--  ASSISTANCE DE   -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label for="assistancede" class="col-lg-3 col-form-label">Assistance de</label>
                                        			<div class="col-lg-9">
                                        				<input id="assistancede" name="assistancede"
                                        				    value="${(!empty declaration) ? declaration.getAssistancede() : ''}"
                                                            type="text" class="form-control">
                                        			</div>
                                        		</div>
                                        	</div>

                                        	<c:if test="${!empty declaration}">
                                        	    <div class="form-group row">
                                                	<label for="assistancede" class="col-lg-3 col-form-label">Uploadez d&eacute;claration</label>
                                                	<div class="col-lg-9">
                                                		<input id="fichiersigne" name="fichiersigne"
                                                            type="file" class="form-control">
                                                	</div>
                                                </div>
                                            </c:if>
                                        </div>


                                        <div class="row">
                                            <div id="infos" style="color:red;"></div>
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
		<script src="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

		<script src="https://themesbrand.com/veltrix/layouts/plugins/select2/js/select2.min.js"></script>
        <script src="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>
        <script src="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js"></script>
        <script src="https://themesbrand.com/veltrix/layouts/plugins/bootstrap-touchspin/js/jquery.bootstrap-touchspin.min.js"></script>


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


