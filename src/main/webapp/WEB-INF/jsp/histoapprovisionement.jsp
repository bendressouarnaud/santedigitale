<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 14/03/2021
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>Historique | Approvisionnement</title>
    <meta content="Admin Dashboard" name="description">
    <meta content="Themesbrand" name="author">
    <!--<link rel="shortcut icon" href="/images/favicon.ico">-->
    <!--Chartist Chart CSS
    <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">-->
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
    <!--<link href="<c:url value='/css/app.min.css' />" rel="stylesheet" type="text/css">-->
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

    <!-- DataTables -->
    <link href="<c:url value='/css/datatables.bootstrap4.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/buttons.bootstrap4.css' />" rel="stylesheet" type="text/css">
    <!-- Responsive datatable examples -->
    <link href="<c:url value='/css/responsive.bootstrap4.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<!-- Begin page -->
<div id="wrapper">
    <!-- Top Bar Start-->
    <div class="topbar">
        <div class="topbar-left"><a href="#" class="logo"><span><img src="<c:url value='/images/logo-light.png' />" alt="" height="18"> </span>
            <i><img src="<c:url value='/images/logo-sm.png' />" alt="" height="22"></i></a></div>
        <nav class="navbar-custom">
            <ul class="navbar-right d-flex list-inline float-right mb-0">
                <li class="dropdown notification-list d-none d-md-block">
                    <form role="search" class="app-search" onsubmit="return false;">
                        <div class="form-group mb-0"><input type="text" class="form-control" placeholder="Search.."> <button ><i class="fa fa-search"></i></button></div>
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
                    <li class="menu-title">Menu principal</li>
                    <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="fas fa-home"></i><span> Actions <span class="float-right menu-arrow"><i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <li><a href="/gestion/ventemedicament">Sortie/Vente</a></li>
                            <li><a href="/gestion/deconnexion">Se d&eacute;connecter</a></li>
                            <!--<li><a href="/gestion/testnvd3">nvd3</a></li>-->
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
                            <h4 class="page-title">Historique</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">
                                    Donn&eacute;es
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Statistiques des stocks</h4>
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="nav-item" id="limotif">
                                        <a id="amotif" class="nav-link active" data-toggle="tab"
                                           href="#listemedicament" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-home"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Liste compl&egrave;te des m&eacute;dicaments</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="litraitement">
                                        <a id="atraitement" class="nav-link" data-toggle="tab"
                                           href="#divapprovisionnement" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-cog"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Historique des approvisionnements</span>
                                        </a>
                                    </li>
                                    <!-- Niveaux faibles -->
                                    <li class="nav-item" id="liniveaufaible">
                                        <a id="aniveaufaible" class="nav-link" data-toggle="tab"
                                           href="#divniveaufaible" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-cog"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Niveaux faibles</span>
                                        </a>
                                    </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div class="tab-pane active p-3" id="listemedicament" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table class="table table-bordered dt-responsive nowrap inventaire"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                            <tr>
                                                                <th>Libell&eacute;</th>
                                                                <th>Quantit&eacute;</th>
                                                                <th>Prix</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="valeur" items="${listeMedicament}">
                                                            <tr>
                                                                <td>${ valeur.getLibelle() }</td>
                                                                <td>${ valeur.getQuantite() }</td>
                                                                <td>${ valeur.getPrix() }</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="tab-pane p-3" id="divapprovisionnement" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table class="table table-bordered dt-responsive nowrap inventaire"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                            <tr>
                                                                <th>Libell&eacute;</th>
                                                                <th>Date approvisionnement</th>
                                                                <th>Date p&eacute;remption</th>
                                                                <th>Agent</th>
                                                                <th>Heure</th>
                                                                <th>Num. Lot</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="donnee" items="${listeAppro}">
                                                            <tr>
                                                                <td>${ donnee[0] }</td>
                                                                <td>${ donnee[1] }</td>
                                                                <td>${ donnee[4] }</td>
                                                                <td>${ donnee[5] }</td>
                                                                <td>${ donnee[2] }</td>
                                                                <td>${ donnee[3] }</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="tab-pane p-3" id="divniveaufaible" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table class="table table-bordered dt-responsive nowrap inventaire"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th>Libell&eacute;</th>
                                                            <th>Prix</th>
                                                            <th>Quantit&eacute;</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="donnee" items="${niveauFaible}">
                                                            <tr>
                                                                <td>${ donnee.getLibelle() }</td>
                                                                <td>${ donnee.getPrix() }</td>
                                                                <td>${ donnee.getQuantite() }</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
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
<!-- Datatable init js -->
<script src="<c:url value='/pages/datatables.init.js' />"></script>
<!--<script src="<c:url value='/pages/dashboard.js' />"></script>-->
<!-- App js -->
<script src="<c:url value='/js/app.js' />"></script>

<script>
    //
    $(document).ready(function() {
        $().ready(function () {
            //
            $('.inventaire').DataTable();
        });
    });
</script>
</body>
</html>

