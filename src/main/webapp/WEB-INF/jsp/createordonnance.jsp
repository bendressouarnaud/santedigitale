
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
      <meta charset="utf-8">

      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
      <title>GestPAT - Ordonnance</title>
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

      <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">

      <script>






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
                           <li><a href="/gestion/afficherordonnance/${idcon}">Retour</a></li>
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
                           <!--<h4 style="float:left; margin-top: 10px">
                           Informations de l'ordonnance</h4>-->
                            <img src="<c:url value='/images/medicament.jpg'/>"
                                 Style="width:90px; height:70px"/>
                        </div>
                     </div>
                  </div>
                  <!-- end row -->

                   <div class="row">
                       <br>
                   </div>



                  <div class="row">
                    <div class="col-sm-12">
                    	<div class="card">
                    		<div class="card-body">
                    			<h4 class="mt-0 header-title">Informations</h4>
                    			<!--<p class="text-muted m-b-30 font-14">Veuillez renseigner les informations relatives &agrave; la consultation</p>-->

                                <br>
                                <br>

                    			<form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                    			    action="${(!empty lienenreg) ? lienenreg : lienmodiford }"
                    			    method="POST">
                    				<fieldset>

                                        <!--  Diagnostic   -->
                                        <div class="row">
                                        	<div class="col-md-12">
                                        		<table id="tabinfo" width="100%">
                                        		    <tr>
                                        		        <th width="20%">M&eacute;dicament</th>
                                        		        <th width="16%">Quantit&eacute;</th>
                                        		        <th width="16%">Posologie</th>
                                        		        <th width="16%">Dosage</th>
                                        		        <th width="16%">Type</th>
                                        		        <th width="16%">Action</th>
                                        		    </tr>
                                        		    <tr>
                                        		        <td>
                                        		            <input name="medicament[]"
                                                                value="${(!empty listOrdonnance) ? listOrdonnance.get(0).getPrescription() : ''}"
                                                                type="text" class="form-control">
                                        		        </td>
                                                        <td>
                                                            <select name="quantite[]" class="form-control select2">
                                                                <c:forEach items="${listeQuantite}" var="quantite" >
                                                                    <option value="${quantite.getIdqte()}" ${ (!empty listOrdonnance) ? (listOrdonnance.get(0).getQuantite()==quantite.getIdqte()) ? "selected" : "" : ""}>
                                                                        ${ quantite.getLibelle() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td>
                                                            <select name="posologie[]" class="form-control select2">
                                                                <c:forEach items="${listePosologie}" var="posologie" >
                                                                    <option value="${posologie.getIdpos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(0).getPosologie()==posologie.getIdpos()) ? "selected" : "" : ""}>
                                                                        ${ posologie.getLibelle() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>

                                                        <td>
                                                            <select name="dosage[]" class="form-control select2">
                                                                <c:forEach items="${listeDosage}" var="dosage" >
                                                                    <option value="${dosage.getIddos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(0).getDosage()==dosage.getIddos()) ? "selected" : "" : ""}>
                                                                            ${ dosage.getLibelle() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>

                                                        <td>
                                                            <select name="type[]" class="form-control select2">
                                                                <c:forEach items="${listeTypes}" var="types" >
                                                                    <option value="${types.getIdtyp()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(0).getTypemedic()==types.getIdtyp()) ? "selected" : "" : ""}>
                                                                            ${ types.getLibelle() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>

                                                        <td>
                                        		            <input class="btn btn-info w-md waves-effect waves-light"
                                                                type="button" value="Ajouter" id="ajouter">
                                        		        </td>
                                        		    </tr>


                                                    <!--   Display ORDONNANCE   -->
                                                    <c:if test="${!empty taille}">
                                                        <c:forEach var="i" begin="1" end="${taille}" step="1">

                                                            <tr>
                                                                <td>
                                                                    <input name="medicament[]"
                                                                           value="${(!empty listOrdonnance) ? listOrdonnance.get(i).getPrescription() : ''}"
                                                                           type="text" class="form-control">
                                                                </td>
                                                                <td>
                                                                    <select name="quantite[]" class="form-control select2">
                                                                        <c:forEach items="${listeQuantite}" var="quantite" >
                                                                            <option value="${quantite.getIdqte()}" ${ (!empty listOrdonnance) ? (listOrdonnance.get(i).getQuantite()==quantite.getIdqte()) ? "selected" : "" : ""}>
                                                                                    ${ quantite.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <select name="posologie[]" class="form-control select2">
                                                                        <c:forEach items="${listePosologie}" var="posologie" >
                                                                            <option value="${posologie.getIdpos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getPosologie()==posologie.getIdpos()) ? "selected" : "" : ""}>
                                                                                    ${ posologie.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <select name="dosage[]" class="form-control select2">
                                                                        <c:forEach items="${listeDosage}" var="dosage" >
                                                                            <option value="${dosage.getIddos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getDosage()==dosage.getIddos()) ? "selected" : "" : ""}>
                                                                                    ${ dosage.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <select name="type[]" class="form-control select2">
                                                                        <c:forEach items="${listeTypes}" var="types" >
                                                                            <option value="${types.getIdtyp()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getTypemedic()==types.getIdtyp()) ? "selected" : "" : ""}>
                                                                                    ${ types.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>
                                                                </td>
                                                            </tr>

                                                        </c:forEach>
                                                    </c:if>

                                        		</table>
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


                                        <%--
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Choisir une pharmacie</label>
                                                    <div class="col-lg-6">
                                                        <select id="pharmacie" name="pharmacie"
                                                                class="form-control select2">
                                                            <c:forEach items="${listePharmacie}" var="valeur" >
                                                                <option value="${valeur.getIdpharm()}"
                                                                    ${ (!empty facturation) ? (facturation.getAssurance()==valeur.getIdpharm()) ? "selected" : "" : ""}>
                                                                        ${valeur.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        --%>

                                        <!--   HIDE THINGS   -->
                                        <input type="hidden" id="monurl" value="${monurl}" />


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
	  <script src="<c:url value='/js/jquery.datatables.min.js' />"></script>
      <script src="<c:url value='/js/datatables.bootstrap4.min.js' />"></script>
      <script src="<c:url value='/js/datatables.buttons.min.js' />"></script>

      <script src="<c:url value='/js/select2.min.js' />"></script>

	  <!-- Datatable init js -->
		<script src="<c:url value='/pages/datatables.init.js' />"></script>

	  <!-- App js -->
	  <script src="<c:url value='/js/app.js' />"></script>

	  <script>
	        var cptr = 0;
            var monurl = $("#monurl").val();

            $(document).ready(function() {
                $().ready(function () {
                    $(".form-control.select2").select2();
                });

                if($(".ibtnDel").length ) {
                    //alert("OK existe !");
                    $("#tabinfo").on("click", ".ibtnDel", function (event) {
                        // Supprimer la ligne correspondante :
                        $(this).closest("tr").remove();
                    });
                }
            });

	        //
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

                $trNew.find("td:first").html("<input id='"+tp+"' name='medicament[]' type='text' class='form-control'>");

                // Build for the second :
                //$.get("http://www.gestdp.com/gestion/getquantitedonne",
                var lien = monurl+"getquantitedonne";
                $.get(lien,
                    function(data) {
                        //
                        var listeId = new Array();
                        var listeLib = new Array();

                        $(data).find('item').each(function(){
                            listeId.push($(this).find('idqte').text());
                            listeLib.push($(this).find('libelle').text());
                        });

                        //
                        var tpn = "<select class='"+tp+"' name='quantite[]'>";
                        for (i = 0; i < listeId.length; i++) {
                            tpn += "<option value="+listeId[i]+">"+listeLib[i]+"</option>";
                        }
                        tpn += "</select>";

                        // Add :
                        $trNew.find("td:eq(1)").html(tpn);


                        // Add the second : localhost:8080
                        //$.get("http://www.gestdp.com/gestion/getnaturefichier",
                        $.get(monurl+"getposologiedonne",
                            function(data) {
                                //
                                var listeId = new Array();
                                var listeLib = new Array();

                                $(data).find('item').each(function () {
                                    listeId.push($(this).find('idpos').text());
                                    listeLib.push($(this).find('libelle').text());
                                });

                                //
                                var tpnPos = "<select class='" + tp + "' name='posologie[]'>";
                                for (i = 0; i < listeId.length; i++) {
                                    tpnPos += "<option value=" + listeId[i] + ">" + listeLib[i] + "</option>";
                                }
                                tpnPos += "</select>";

                                // Build for the third :
                                $trNew.find("td:eq(2)").html(tpnPos);

                                // Build for the fourth DOSAGE: localhost:8080
                                //$.get("http://www.gestdp.com/gestion/getnaturefichier",
                                $.get(monurl+"getdosagedonne",
                                    function(data) {
                                        //
                                        var listeId = new Array();
                                        var listeLib = new Array();

                                        $(data).find('item').each(function () {
                                            listeId.push($(this).find('iddos').text());
                                            listeLib.push($(this).find('libelle').text());
                                        });

                                        //
                                        var tpnDos = "<select class='" + tp + "' name='dosage[]'>";
                                        for (i = 0; i < listeId.length; i++) {
                                            tpnDos += "<option value=" + listeId[i] + ">" + listeLib[i] + "</option>";
                                        }
                                        tpnDos += "</select>";

                                        // Build for the fourth DOSAGE:
                                        $trNew.find("td:eq(3)").html(tpnDos);


                                        // Build for the fifth TYPE :
                                        //$.get("http://www.gestdp.com/gestion/getnaturefichier",
                                        $.get(monurl+"gettypedonne",
                                            function(data) {
                                                //
                                                var listeId = new Array();
                                                var listeLib = new Array();

                                                $(data).find('item').each(function () {
                                                    listeId.push($(this).find('idtyp').text());
                                                    listeLib.push($(this).find('libelle').text());
                                                });

                                                //
                                                var tpnTyp = "<select class='" + tp + "' name='type[]'>";
                                                for (i = 0; i < listeId.length; i++) {
                                                    tpnTyp += "<option value=" + listeId[i] + ">" + listeLib[i] + "</option>";
                                                }
                                                tpnTyp += "</select>";

                                                // Build for the fifth TYPE :
                                                $trNew.find("td:eq(4)").html(tpnTyp);


                                                //
                                                $("#tabinfo").on("click", ".ibtnDel", function (event) {
                                                    // Supprimer la ligne correspondante :
                                                    $(this).closest("tr").remove();
                                                });

                                                $trLast.after($trNew);

                                                //
                                                $("."+tp).select2();
                                            }
                                        );


                                    }
                                );


                            }
                        );


                    }
                );
                /*$trNew.find("td:eq(1)").html(
                    "<select class='"+tp+"'><option>1</option><option>2</option><option>3</option></select> ");*/



                // ----
                //$("#"+tp).datepicker();
                //$(".form-control.datepicker").datepicker();
            });


	  </script>
   </body>
</html>


