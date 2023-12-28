<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 13/08/2020
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>GestPAT - CACHETS</title>
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
                            <li><a href="/gestion/createconsultation">Nouvelle consultation</a></li>
                            <li><a href="/gestion/autorisation">Autorisation</a></li>
                            <li><a href="/gestion/patientexterne">Avis Patients externes</a></li>
                            <li><a href="/gestion/patientshabilite">Liste des patients</a></li>
                            <li><a href="/gestion/listerdv">Liste des RDV</a></li>
                            <li><a href="/gestion/dashboard">Tableau de bord</a></li>
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
                            <h4 class="page-title">Gestion des CACHETS</h4>
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
                                      action="/gestion/creercachet" enctype="multipart/form-data"
                                      method="POST"  >

                                    <fieldset>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Choisir cachet</label>
                                                    <div class="col-lg-9">
                                                        <input name="cachet" type="file" class="form-control fichier">
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
                                            <th>Identifiant</th>
                                            <th>Illustration</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="donnee" items="${liste}">
                                        <tr>
                                            <td>${donnee.getIdtpm()}</td>
                                            <td>
                                                <img width="200" height="200" src="data:image/jpeg;base64,${donnee.getTampon()}">
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
</script>
</body>
</html>

