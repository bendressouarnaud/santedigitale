<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 15/09/2020
  Time: 05:59
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>GestPAT - Nouvelles entr&eacute;es</title>
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

        //
        function checkValeur(choix, valeur){
            var retour = true;
            var info = document.getElementById("infos");
            info.innerHTML = "";
            switch(choix){
                case 0:
                    /* Prix */
                    if (!/^[0-9]+$/.test(valeur)){
                        // Alerter infos
                        info.innerHTML = "<h3 style='color:red;'>Le prix renseign&eacute; est incorrect</h3>";
                        window.location = "#infos";
                        retour = false;
                    }
                    break;

                case 1:
                    /* Quantité */
                    if (!/^[0-9]+$/.test(valeur)){
                        // Alerter infos
                        info.innerHTML = "<h3 style='color:red;'>La Quantit&eacute; renseign&eacute;e est incorrect</h3>";
                        window.location = "#infos";
                        retour = false;
                    }
                    break;
            }
            return retour;
        }



        function performCheck(){

            // If a patient has been choosen, go out and stop :
            var prix = $("#prixunitaire").val();
            var quantite = $("#quantite").val();

            if(!checkValeur(0, prix)) return false;
            if(!checkValeur(1, quantite)) return false;

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
                            <img src="<c:url value='/images/medicament.jpg'/>"
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
                                <p class="text-muted m-b-30 font-14">Approvisionnement</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="${(!empty constante) ? constanteid : '/gestion/enregapprovisionnement'}"
                                      method="POST" enctype="multipart/form-data" onsubmit="return performCheck();">
                                    <!--      -->
                                    <fieldset>

                                        <!--  Liste des patients  &  age -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Recher. M&eacute;dicament</label>
                                                    <div class="col-lg-9">
                                                        <select id="idmedic" name="idmedic" class="form-control select2">
                                                            <c:if test="${!empty creation}">
                                                                <option value="0">Ne pas choisir</option>
                                                            </c:if>
                                                            <c:forEach items="${listeMedicament}" var="donnee" >
                                                                <option value="${donnee.getIdmd()}">
                                                                        ${ donnee.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Nouveau</label>
                                                    <div class="col-lg-9">
                                                        <input id="medicament" name="medicament" type="text"
                                                               value=""
                                                               class="form-control" placeholder="Nouveau m&eacute;dicament">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!--  Numero LOT & Date de PEREMPTION -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label  class="col-lg-3 col-form-label">Num&eacute;ro lot</label>
                                                    <div class="col-lg-9">
                                                        <input name="numlot" id="numlot"
                                                               value=""
                                                               type="text" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Date p&eacute;remption <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="dateperemption" type="text"
                                                               value="${(!empty creation) ? currentdate : ""}"
                                                               class="form-control datepicker">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  Profession & adresse -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Prix unitaire</label>
                                                    <div class="col-lg-9">
                                                        <input name="prixunitaire" id="prixunitaire" type="text"
                                                               value=""
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Quantit&eacute;</label>
                                                    <div class="col-lg-9">
                                                        <input name="quantite" id="quantite" type="text"
                                                               value=""
                                                               class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <!--  Date d'enregistrement -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Date de saisie <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="datesaisie" type="text"
                                                               value="${(!empty creation) ? currentdate : ""}"
                                                               class="form-control datepicker">
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
            $(".form-control.datepicker").datepicker();
            $(".form-control.select2").select2();
        });
    });


</script>
</body>
</html>
