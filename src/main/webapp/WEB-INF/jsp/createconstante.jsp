
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta charset="utf-8">

      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>GestPAT - Nouvelles constantes</title>
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
                        // Poids
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Le poids est incorrect !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;

                    case 1:
                        // Taille
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La taille est incorrecte !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;

                    case 2:
                        // Tension
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La tension est incorrecte !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;

                    case 3:
                        // Temperature
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La temp&eacute;rature est incorrecte !</h3>";
                        window.location = "#infos";
                        retour = false;
                        break;
                }
            }

            return retour;
        }


        function performCheck(){

            // If a patient has been choosen, go out and stop :
            //var idpatient = parseInt($("#idpatient").val());
            //if(idpatient>0) return true;

            if(!checkAge()) return false;

            //
            var poids = $("#poids").val();
            var taille = $("#taille").val();
            var tension = $("#tension").val();
            var temperature = $("#temperature").val();
            if(!checkConstante(poids, 0)) return false;
            if(!checkConstante(taille, 1)) return false;
            if(!checkConstante(tension, 2)) return false;
            if(!checkConstante(temperature, 3)) return false;

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

            //
            return true;
        }

        function onchangePatient(){
            var idpatient = parseInt($("#idpatient").val());
            var idhop = parseInt($("#idhop").val());
            var monurl = $("#monurl").val();
            //alert("monurl : "+monurl);
            //alert("idpatient : "+idpatient);
            // Get PATIENT INFO :
            if(idpatient > 0){

                //
                //$.get("https://www.gestdp.com/gestion/getpatientinfo/"+idpatient,
                $.get(monurl+"getpatientinfo/"+idpatient,
                    function(data) {
                        //
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
                            $('#nom').val(nom);
                            $('#prenom').val(prenom);
                            $('#age').val(age);
                            //$('#telephone').val(telephone);
                            //$('#email').val(email);
                            // Profession :
                            //document.getElementById('profession').selectedIndex = parseInt(profession);
                            if(parseInt(profession) > 0){
                                $("#profession").val(parseInt(profession));
                            }
                            $('#profession').trigger('change');

                            //$('#adresse').val(adresse);
                            // Sexe
                            if(sexe == "F") $("#sexe").val("F"); //document.getElementById('sexe').selectedIndex = 0;
                            else if(sexe == "M") $("#sexe").val("M"); // document.getElementById('sexe').selectedIndex = 1;
                            //$('#sexe').select2('refresh');
                            $('#sexe').trigger('change');

                            //$('#cni').val(cni);
                        });
                    }
                );

                /* Call to get DOCTORS attached to the service the patient want
                   to be examined by if this one paid the bill 'Facturation' */
                //
                //$.get("https://www.gestdp.com/gestion/getdoctorslist/"+idpatient+"/"+idhop,
                /*$.get(monurl+"getdoctorslist/"+idpatient+"/"+idhop,
                    function(data) {
                        //
                        var nouveauMedecins = [];
                        $(data).find('item').each(function(){
                            var donnees = "<option value='"+$(this).find('idmed').text()+
                                "'>"+$(this).find('nom').text()+"</option>";
                            nouveauMedecins.push(donnees);
                        });

                        // Refresh if necessary :
                        if(nouveauMedecins.length > 0){
                            $("#medecinavoir").html(nouveauMedecins);
                            //$("#medecinavoir").select2('refresh');
                            $('#medecinavoir').select2();
                        }
                    }
                );
                */
            }
            else{
                //
                $('#nom').val("");
                $('#prenom').val("");
                $('#age').val("");
                $('#telephone').val("");
                $('#email').val("");
                $('#adresse').val("");
                $('#cni').val("");
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
                             <img src="<c:url value='/images/mesure.jpeg'/>"
                                  Style="width:90px; height:70px"/>
                         </div>
                     </div>
                  </div>
                  <!-- end row -->

                  <div class="row">
                    <div class="col-sm-12">
                    	<div class="card">
                    		<div class="card-body">
                    			<h4 class="mt-0 header-title">Informations</h4>
                    			<p class="text-muted m-b-30 font-14">Informations relatives &agrave; au patient</p>

                    			<form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                    			    action="${(!empty constante) ? constanteid : '/gestion/enregconstante'}"
                    			    method="POST" enctype="multipart/form-data" onsubmit="return performCheck();">
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
                                            		<label class="col-lg-3 col-form-label">&Acirc;ge <span style="color:red">*</span></label>
                                            		<div class="col-lg-9">
                                            			<input id="age" name="age" type="text"
                                            			value="${(!empty patient) ? patient.getAge() : ""}"
                                            			class="form-control" placeholder="Exemple d'&acirc;ge : 25">
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


                                        <!--  Profession & adresse -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Profession</label>
                                        			<div class="col-lg-9">
                                        				<select name="profession" id="profession" class="form-control select2">
                                                            <c:forEach items="${listeprofession}" var="profession" >
                                                                <option value="${profession.getIdprof()}"
                                                                    ${ (!empty patient) ? (patient.getProfession()==profession.getIdprof()) ? "selected" : "" : ""}>
                                                                  ${profession.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Sexe</label>
                                        			<div class="col-lg-9">
                                        			    <select id="sexe" name="sexe" class="form-control select2">
                                                            <option value="F" ${(!empty patient) ? patient.getSexe() == 'F' ? "selected" : "" : ""}>F</option>
                                                            <option value="M" ${(!empty patient) ? patient.getSexe() == 'M' ? "selected" : "" : ""}>M</option>
                                                        </select>
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>

                                        <!--  POIDS   &   TAILLE -->
                                        <div class="row">
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Poids <span style="color:red">*</span></label>
                                        			<div class="col-lg-9">
                                        				<input id="poids" name="poids"
                                        				value="${(!empty constante) ? constante.getPoids() : '0'}" type="text" class="form-control">
                                        			</div>
                                        		</div>
                                        	</div>
                                        	<div class="col-md-6">
                                        		<div class="form-group row">
                                        			<label class="col-lg-3 col-form-label">Taille <span style="color:red">*</span></label>
                                        			<div class="col-lg-9">
                                        				<input id="taille" name="taille" type="text" class="form-control"
                                        				value="${(!empty constante) ? constante.getTaille() : '0'}" >
                                        			</div>
                                        		</div>
                                        	</div>
                                        </div>


                    					<!--  TENSION  et temperature -->
                    					<div class="row">
                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label class="col-lg-3 col-form-label">Tension <span style="color:red">*</span></label>
                                            		<div class="col-lg-9">
                                            			<input name="tension" id="tension" type="text" class="form-control"
                                            			value="${(!empty constante) ? constante.getTension() : '0'}" >
                                            		</div>
                                            	</div>
                                            </div>
                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label class="col-lg-3 col-form-label">Temp&eacute;rature <span style="color:red">*</span></label>
                                            		<div class="col-lg-9">
                                            			<input name="temperature" id="temperature" type="text" class="form-control"
                                            			value="${(!empty constante) ? constante.getTemperature() : '0'}" >
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>


                    					<!-- Tension arterielle &  selectionner le medecin -->
                    					<div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Tension art&eacute;rielle <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="tensionarterielle" id="tensionarterielle" type="text" placeholder="..." class="form-control"
                                                               value="${(!empty constante) ? constante.getTensionarterielle() : ''}" >
                                                    </div>
                                                </div>
                                            </div>
                                        	<div class="col-md-6">
                                            	<div class="form-group row">
                                            		<label class="col-lg-3 col-form-label">Med&eacute;cin &agrave; voir <span style="color:red">*</span></label>
                                            		<div class="col-lg-9">
                                        				<select name="medecinavoir" id="medecinavoir" class="form-control select2">
                                                            <c:forEach items="${listeDocteur}" var="docteur" >
                                                                <option value="${docteur[0]}"
                                                                    ${ (!empty constante) ? (constante.getIddocteur()==docteur[0]) ? "selected" : "" : ""}>
                                                                  ${docteur[1]}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                            		</div>
                                            	</div>
                                            </div>
                                        </div>





                                        <div class="row">
                                            <div id="infos" style="color:red;"></div>
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

                                        <input type="hidden" id="idhop" value="${idhop}" />
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
                    $(".form-control.select2").select2();
                });
            });


	  </script>
   </body>
</html>


