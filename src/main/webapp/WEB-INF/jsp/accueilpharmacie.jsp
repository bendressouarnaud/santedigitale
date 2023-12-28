<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 19/06/2020
  Time: 15:26
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
    <title>Accueil | Gestion des ordonnances</title>
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

                    <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="far fa-address-card"></i><span> Gestion Appro. <span class="float-right menu-arrow"><i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/approvisionnement">Approvisionnement</a></li>
                            <li><a href="/gestion/historiqueappro">Historique approvisionement</a></li>
                            <!--<li><a href="/gestion/listerdv">Niveaux faibles</a></li>-->
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
                            <h4 class="page-title">Pharmacie</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">
                                    Accueil
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- end row -->


                <c:if test="${!empty listeLimite}">
                    <div class="row">
                        <div class="col-sm-6 col-md-3 m-t-30">
                            <div class="modal fade bs-example-modal-lg" tabindex="-1"
                                 role="dialog" aria-labelledby="myLargeModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title mt-0">M&eacute;dicament(s) dont le stock s'&eacute;puise</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body" id="listeLimite">
                                                        <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                            <thead>
                                                            <tr>
                                                                <th>Libell&eacute;</th>
                                                                <th>Prix</th>
                                                                <th>Quantit&eacute;</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            <c:forEach var="donnee" items="${listeLimite}">
                                                                <tr>
                                                                    <td>${donnee.getLibelle()}</td>
                                                                    <td>${donnee.getPrix()}</td>
                                                                    <td>${donnee.getQuantite()}</td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                    </div>
                </c:if>


                <!-- Add image : -->
                <div class="row">
                    <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                            <div class="card-body">
                                <div class="float-left mr-4">
                                    <img src="<c:url value='/images/patientlabo.jpg' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">Patients journaliers</h5>
                                <p class="font-12 mb-2">Proportion</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty totJournalierPatient) ? totJournalierPatient : "0"}</p>
                                <div class="clearfix"></div>
                                <hr>
                                <!--<p class="mb-0">&nbsp;</p>-->
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                            <div class="card-body">
                                <div class="float-left mr-4">
                                    <img src="<c:url value='/images/medicament.jpg' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">Dernier m&eacute;dicament vendu</h5>
                                <p class="font-12 mb-2">&nbsp;</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty dernierVenduLib) ? dernierVenduLib : "---"}</p>
                                <div class="clearfix"></div>
                                <hr>
                                <!--<p class="mb-0">&nbsp;</p>-->
                            </div>
                        </div>
                    </div>


                    <!--  STOCK  -->
                    <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                            <div class="card-body">
                                <div class="float-left mr-4">
                                    <img src="<c:url value='/images/stocks.jpg' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">R&eacute;f&eacute;rence m&eacute;dicament</h5>
                                <p class="font-12 mb-2">Proportion</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty totalMedicJ) ? totalMedicJ : "0"}</p>
                                <div class="clearfix"></div>
                                <hr>
                                <!--<p class="mb-0">&nbsp;</p>-->
                            </div>
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
                                           href="#facturation" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-home"></i>
                                                                    </span>
                                            <span class="d-none d-sm-block">Vente journali&egrave;re</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="liventedeux">
                                        <a id="aventedeux" class="nav-link" data-toggle="tab"
                                           href="#divventedeux" role="tab">
                                                                    <span class="d-block d-sm-none">
                                                                        <i class="fas fa-cog"></i>
                                                                    </span>
                                            <span class="d-none d-sm-block">Historique de vente sur les 2 derni&egrave;res semaines</span>
                                        </a>
                                    </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div class="tab-pane active p-3" id="facturation" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table class="table table-bordered dt-responsive nowrap histo"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th>Agent</th>
                                                            <th>Date</th>
                                                            <th>Heure</th>
                                                            <th>M&eacute;dicament</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="valeur" items="${listevente}">
                                                            <tr>
                                                                <td>${valeur[0]}</td>
                                                                <td>${valeur[1]}</td>
                                                                <td>${valeur[2]}</td>
                                                                <td>${valeur[3]}</td>
                                                                <td>
                                                                    <a title="Modifier" href="#" style="color: red">
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


                                    <div class="tab-pane p-3" id="divventedeux" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table class="table table-bordered dt-responsive nowrap histo"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th>Agent</th>
                                                            <th>Date</th>
                                                            <th>Heure</th>
                                                            <th>M&eacute;dicament</th>
                                                            <th>Client</th>
                                                            <th>M&eacute;decin</th>
                                                            <th>Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="valeur" items="${listeventedeuxSem}">
                                                            <tr>
                                                                <td>${valeur[0]}</td>
                                                                <td>${valeur[1]}</td>
                                                                <td>${valeur[2]}</td>
                                                                <td>${valeur[3]}</td>
                                                                <td>${valeur[4]}</td>
                                                                <td>${valeur[5]}</td>
                                                                <td>
                                                                    <a title="Modifier" href="#" style="color: blue">
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
            � 2019 Veltrix
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
            if($("#listeLimite").length ) {
                $('.bs-example-modal-lg').modal('toggle');
            }

            //
            $('.histo').DataTable();
        });
    });
</script>
</body>
</html>
