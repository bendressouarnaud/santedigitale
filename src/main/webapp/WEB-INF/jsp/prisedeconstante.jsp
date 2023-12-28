<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 26/11/2020
  Time: 12:05
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
    <title>GestPAT - CONSTANTE</title>
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
        function checkTensionArterielle(){
            var retour = true;
            var tension = $("#tensionarterielle").val();
            if(tension.trim().length == 0){
                retour = false;
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner la tension art&eacute;rielle</h3>";
            }
            return retour;
        }

        // Check POIDS, TAILLE, TENSION
        function checkConstante(constante, choix){
            var retour = true;

            // Prototype :
            var prototype = /([0-9]*[.])?[0-9]+$/;

            if(!prototype.test(constante)){
                switch(choix){
                    case 0:
                        // Poids
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Le poids est incorrect !</h3>";
                        retour = false;
                        break;

                    case 1:
                        // Taille
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La taille est incorrecte !</h3>";
                        retour = false;
                        break;

                    case 2:
                        // POuls
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La tension est incorrecte !</h3>";
                        retour = false;
                        break;

                    case 3:
                        // Temperature
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>La temp&eacute;rature est incorrecte !</h3>";
                        retour = false;
                        break;
                }
            }

            return retour;
        }


        function performCheck(){
            //
            var poids = $("#poids").val();
            var taille = $("#taille").val();
            var tension = $("#tension").val();
            var temperature = $("#temperature").val();
            if(!checkConstante(poids, 0)) return false;
            if(!checkConstante(taille, 1)) return false;
            if(!checkTensionArterielle()) return false;
            if(!checkConstante(temperature, 3)) return false;

            var medecinId = parseInt($("#medecin").val());
            var info = document.getElementById("infos");
            if(medecinId == 0){
                info.innerHTML = "<h3 style='color:red;'>Veuillez s&eacute;lectionner un SERVICE pour lequel figure un MEDECIN !</h3>";
                return false;
            }

            // Check POULS :
            var pouls = $("#pouls").val();
            if (!/^[0-9]{1,3}$/.test(pouls)){
                // Alerter infos
                info.innerHTML = "<h3 style='color:red;'>Le POULS renseign&eacute; est incorrect !</h3>";
                return false;
            }

            //
            $("#butenregistrer").prop('disabled', true); // Disable
            return true;
        }

        function onchangespecialite(){
            var specialite = parseInt($("#specialite").val());
            var monurl = $("#monurl").val();
            //alert("specialite : "+specialite);
            //alert("monurl : "+monurl);
            if(specialite > 0){

                $.get(monurl+"checklistemedecincpec/"+specialite,
                    function(data) {
                        //
                        var tampon = {};
                        tampon.jour = "";
                        tampon.nombre = 0;
                        $(data).find('item').each(function () {
                            tampon.jour = $(this).find('idmed').text();
                            tampon.nombre = parseInt($(this).find('nombre').text());
                        });

                        //alert("tampon.nombre : "+tampon.nombre);
                        var nouveauMedecins = [];
                        if(tampon.nombre > 0){
                            $.get(monurl+"getlistemedecincpec/"+specialite,
                                function(data) {
                                    //
                                    $(data).find('item').each(function () {
                                        var donnees = "<option value='"+$(this).find('idmed').text()+
                                            "'>"+$(this).find('nom').text()+" "+
                                            $(this).find('prenom').text()+"</option>";
                                        nouveauMedecins.push(donnees);
                                    });

                                    if(nouveauMedecins.length > 0){
                                        $("#medecin").html(nouveauMedecins);
                                        $('#medecin').select2();
                                        // Init every time :
                                        var info = document.getElementById("infos");
                                        info.innerHTML = "";
                                    }
                                    else{
                                        // Display the message :
                                        var info = document.getElementById("infos");
                                        info.innerHTML = "<h3 style='color:red;'>Pas de M&eacute;decin pour cette sp&eacute;cialit&eacute; !!!</h3>";

                                        var donneesN = "<option value='0'>---</option>";
                                        nouveauMedecins.push(donneesN);
                                        // Update with NOTHING
                                        $("#medecin").html(nouveauMedecins);
                                        $('#medecin').select2();
                                    }
                                }
                            );
                        }
                        else{
                            // Display the message :
                            var info = document.getElementById("infos");
                            info.innerHTML = "<h3 style='color:red;'>Pas de M&eacute;decin pour cette sp&eacute;cialit&eacute; !!!</h3>";

                            var donneesN = "<option value='0'>---</option>";
                            nouveauMedecins.push(donneesN);
                            // Update with NOTHING
                            $("#medecin").html(nouveauMedecins);
                            $('#medecin').select2();
                        }
                    }
                );

            }
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
                            <li><a href="/gestion/createpatients">Nouveau patient</a></li>
                            <li><a href="/gestion/afficherlesconstantes">Afficher les constantes prises</a></li>
                            <li><a href="/gestion/getlistepatients">Liste des patients</a></li>
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
                                <h4 class="mt-0 header-title">DONN&Eacute;ES - CONSTANTES</h4>
                                <p class="text-muted m-b-30 font-14">Informations relatives &agrave; au patient</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="${(!empty lienenreg) ? lienenreg : lienmodif}"
                                      method="POST" enctype="multipart/form-data" onsubmit="return performCheck();">
                                    <fieldset>
                                        <!--  NOM et PRENOM   &  POULS-->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Patient</label>
                                                    <div class="col-lg-9">
                                                        <select id="idpatient" name="idpatient" class="form-control select2">
                                                            <option value="${patient.getIdpat()}">
                                                                ${ patient.getNom() }&nbsp;${ patient.getPrenom() }
                                                            </option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Pouls <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="pouls" id="pouls" type="text" placeholder="0" class="form-control"
                                                               value="${(!empty constante) ? constante.getPouls() : '1'}" >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--  Specialite & Medecin -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Sp&eacute;cialit&eacute;</label>
                                                    <div class="col-lg-9">
                                                        <select onchange="onchangespecialite();" name="specialite" id="specialite" class="form-control select2">
                                                            <c:forEach items="${listeservices}" var="donnee" >
                                                                <option value="${donnee.getIdser()}"
                                                                    ${ (!empty constante) ? (constante.getIdser()==donnee.getIdser()) ? "selected" : "" : ""}>
                                                                        ${donnee.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Med&eacute;cin &agrave; voir <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <select name="medecin" id="medecin" class="form-control select2">
                                                            <c:forEach items="${listemedecin}" var="mn" >
                                                                <option value="${mn.getIdmed()}"
                                                                    ${ (!empty constante) ? (constante.getIddocteur()==mn.getIdmed()) ? "selected" : "" : ""}>
                                                                        ${ mn.getNom() }&nbsp;${ mn.getPrenom() }
                                                                </option>
                                                            </c:forEach>
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
                                                    <label class="col-lg-3 col-form-label">Taille (en cm)<span style="color:red">*</span></label>
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
                                                    <label class="col-lg-3 col-form-label">Tension art&eacute;rielle <span style="color:red">*</span></label>
                                                    <div class="col-lg-9">
                                                        <input name="tensionarterielle" id="tensionarterielle" type="text" placeholder="..." class="form-control"
                                                               value="${(!empty constante) ? constante.getTensionarterielle() : ''}" >
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


                                        <div class="row">
                                            <div id="infos"
                                                 style="color:red; text-align: center; margin-top: 5px; width: 100%;">
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
                                                <button id="butenregistrer" class="btn btn-primary w-md waves-effect waves-light"
                                                        type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                            </div>
                                        </div>

                                        <input type="hidden" id="idhop" value="${idhop}" />
                                        <!--   HIDE THINGS   -->
                                        <input type="hidden" id="monurl" value="${monurl}" />
                                        <input type="hidden" id="modeopeartion" value="${modeopeartion}" />
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

            // Call this :
            var modeopeartion = parseInt($("#modeopeartion").val());
            if(modeopeartion == 0) onchangespecialite();
        });
    });


</script>
</body>
</html>



