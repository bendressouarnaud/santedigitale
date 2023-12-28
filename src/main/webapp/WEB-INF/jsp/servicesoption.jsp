<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>GestPAT | OPTIONS SERVICES</title>
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
        function activation(){
            document.getElementById("id").disabled = false;
            return true;
        }

        function affichertest() {
            alert("Fonctionnalité en cours ...");
        }

        function afficher(){
            var services = $('#service').val();
            var hopital = $('#hopital').val();
            var monurl = $("#monurl").val();
            //
            $.get(monurl+"getserviceoption/"+services+"/"+hopital,
                function(data) {
                    //
                    var donnees = {};
                    $(data).find('item').each(function(){
                        donnees.motif = parseInt($(this).find('motif').text());
                        donnees.antecedent = parseInt($(this).find('antecedent').text());
                        donnees.modevie = parseInt($(this).find('modevie').text());
                        donnees.examenclinique = parseInt($(this).find('examenclinique').text());
                        donnees.conclusion = parseInt($(this).find('conclusion').text());
                        donnees.diagnostic = parseInt($(this).find('diagnostic').text());
                        donnees.traitement = parseInt($(this).find('traitement').text());
                        donnees.interrogatoire = parseInt($(this).find('interrogatoire').text());
                        donnees.examenphysique = parseInt($(this).find('examenphysique').text());
                        donnees.conduiteatenir = parseInt($(this).find('conduiteatenir').text());
                        donnees.diagnosticretenu = parseInt($(this).find('diagnosticretenu').text());
                        donnees.hospitalisation = parseInt($(this).find('hospitalisation').text());
                        donnees.soins = parseInt($(this).find('soins').text());
                        donnees.avisconfrere = parseInt($(this).find('avisconfrere').text());
                        donnees.ordonnance = parseInt($(this).find('ordonnance').text());
                        donnees.passemedical = parseInt($(this).find('passemedical').text());
                        donnees.allergies = parseInt($(this).find('allergies').text());
                        donnees.passedentaire = parseInt($(this).find('passedentaire').text());
                        donnees.protheses = parseInt($(this).find('protheses').text());
                        donnees.interventions = parseInt($(this).find('interventions').text());
                        donnees.examparaclinique = parseInt($(this).find('examparaclinique').text());
                    });

                    // Now, perform :
                    document.getElementById('motif').checked = (donnees.motif == 1) ? true : false;
                    document.getElementById('antecedent').checked = (donnees.antecedent == 1) ? true : false;
                    document.getElementById('modevie').checked = (donnees.modevie == 1) ? true : false;
                    document.getElementById('examenclinique').checked = (donnees.examenclinique == 1) ? true : false;
                    document.getElementById('conclusion').checked = (donnees.conclusion == 1) ? true : false;
                    document.getElementById('diagnostic').checked = (donnees.diagnostic == 1) ? true : false;
                    document.getElementById('traitement').checked = (donnees.traitement == 1) ? true : false;
                    document.getElementById('interrogatoire').checked = (donnees.interrogatoire == 1) ? true : false;
                    document.getElementById('examenphysique').checked = (donnees.examenphysique == 1) ? true : false;
                    document.getElementById('conduiteatenir').checked = (donnees.conduiteatenir == 1) ? true : false;
                    document.getElementById('diagnosticretenu').checked = (donnees.diagnosticretenu == 1) ? true : false;
                    document.getElementById('hospitalisation').checked = (donnees.hospitalisation == 1) ? true : false;
                    document.getElementById('soins').checked = (donnees.soins == 1) ? true : false;
                    document.getElementById('avisconfrere').checked = (donnees.avisconfrere == 1) ? true : false;
                    document.getElementById('ordonnance').checked = (donnees.ordonnance == 1) ? true : false;
                    document.getElementById('passemedical').checked = (donnees.passemedical == 1) ? true : false;
                    document.getElementById('allergies').checked = (donnees.allergies == 1) ? true : false;
                    document.getElementById('passedentaire').checked = (donnees.passedentaire == 1) ? true : false;
                    document.getElementById('protheses').checked = (donnees.protheses == 1) ? true : false;
                    document.getElementById('interventions').checked = (donnees.interventions == 1) ? true : false;
                    document.getElementById('examparaclinique').checked = (donnees.examparaclinique == 1) ? true : false;
                }
            );
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
                            <li><a href="/gestion/comptes">Comptes</a></li>
                            <li><a href="/gestion/habilitations">Habilitations Admin.</a></li>
                            <li><a href="/gestion/assignation">Assignation m&eacute;decin</a></li>
                            <li><a href="/gestion/gesthopitaux">Gestion hopitaux</a></li>
                            <li><a href="/gestion/deconnexion">D&eacute;connexion</a></li>
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
                            <h4 class="page-title">Gestion OPTIONS SERVICES</h4>
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
                                      action="${(!empty services) ? servicesid : '/gestion/creerservicesopt'}"
                                      method="POST"  >

                                    <fieldset>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Service :</label>
                                                    <div class="col-lg-6">
                                                        <select name="service" id="service" class="form-control select2">
                                                            <c:forEach var="service" items="${listeServices}">
                                                                <option value="${service.getIdser()}">${service.getLibelle()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">H&ocirc;pital :</label>
                                                    <div class="col-lg-6">
                                                        <select name="hopital" id="hopital" class="form-control select2">
                                                            <c:forEach var="hopital" items="${listeHop}">
                                                                <option value="${hopital.getIdhop()}">${hopital.getLibelle()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-12" >
                                                <input type="checkbox" name="motif" id="motif" value="1">
                                                <label>Motif</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="antecedent" id="antecedent" value="1">
                                                <label>Ant&eacute;cedent</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="modevie" id="modevie" value="1">
                                                <label>Mode de vie</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="examenclinique" id="examenclinique" value="1">
                                                <label>Examen clin.</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="conclusion" id="conclusion" value="1">
                                                <label>Conclusion</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="diagnostic" id="diagnostic" value="1">
                                                <label>Diagnostic</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="traitement" id="traitement" value="1">
                                                <label>Traitement</label>&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="interrogatoire" id="interrogatoire" value="1">
                                                <label>Interro.</label>&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="examenphysique" id="examenphysique" value="1">
                                                <label>Examen phy.</label>&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="conduiteatenir" id="conduiteatenir" value="1">
                                                <label>Conduite &agrave; tenir</label>&nbsp;&nbsp;

                                                <br>
                                                <input type="checkbox" name="diagnosticretenu" id="diagnosticretenu" value="1">
                                                <label>Diagnostic retenu</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="hospitalisation" id="hospitalisation" value="1">
                                                <label>Hospitalisation</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="soins" id="soins" value="1">
                                                <label>Soins</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="avisconfrere" id="avisconfrere" value="1">
                                                <label>Avis confr&egrave;re</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="ordonnance" id="ordonnance" value="1">
                                                <label>Ordonnance</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <!---- Added on 08/12/2020  --->
                                                <input type="checkbox" name="passemedical" id="passemedical" value="1">
                                                <label>Pass&eacute; m&eacute;dical</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="allergies" id="allergies" value="1">
                                                <label>Allergies</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="passedentaire" id="passedentaire" value="1">
                                                <label>Pass&eacute; dentaire</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="checkbox" name="protheses" id="protheses" value="1">
                                                <label>Proth&egrave;ses</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="interventions" id="interventions" value="1">
                                                <label>Nature Interventions</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="checkbox" name="examparaclinique" id="examparaclinique" value="1">
                                                <label>Examens paracliniques</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                            </div>
                                        </div>

                                        <br>

                                        <div class="row">
                                            <div class="col-sm-6 text-right" style="float: right; margin-bottom: 20px">
                                                <button class="btn btn-primary w-md waves-effect waves-light"
                                                        type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                                <!--  Pour AFFICHER -->
                                                <button class="btn btn-info w-md waves-effect waves-light"
                                                        type="button" onclick="afficher()" >
                                                    Afficher
                                                </button>

                                                <input type="hidden" id="monurl" value="${monurl}" />
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>
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
                                        <th>Libell&eacute;</th>
                                        <th>Motif</th>
                                        <th>Ant&eacute;c.</th>
                                        <th>Mode vie</th>
                                        <th>Examen clin.</th>
                                        <th>Conclus.</th>
                                        <th>Diagnos.</th>
                                        <th>Trait.</th>
                                        <th>Interro.</th>
                                        <th>Examen phys.</th>
                                        <th>CAT</th>
                                        <th>Hop.</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="donnees" items="${listeDonnees}">
                                        <tr>
                                            <td>${donnees[0]}</td>
                                            <td>${donnees[1]}</td>
                                            <td>${donnees[2]}</td>
                                            <td>${donnees[3]}</td>
                                            <td>${donnees[4]}</td>
                                            <td>${donnees[5]}</td>
                                            <td>${donnees[6]}</td>
                                            <td>${donnees[7]}</td>
                                            <td>${donnees[8]}</td>
                                            <td>${donnees[9]}</td>
                                            <td>${donnees[10]}</td>
                                            <td>${donnees[12]}</td>
                                            <!-- <td>

                                                <a title="Modifier" href="#" style="color: #78CBF5">
                                                    <i class="far fa-address-card"></i>
                                                </a>
                                            </td>-->
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
    $(".form-control.select2").select2();
</script>
</body>
</html>


