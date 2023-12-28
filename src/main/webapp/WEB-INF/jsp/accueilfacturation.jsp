<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 07/06/2020
  Time: 19:02
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
    <title>Accueil | Gestion des patients</title>
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
                            <li><a href="/gestion/nouvfacturation">Nouvelle consultation</a></li>
                            <li><a href="/gestion/nouvelexamen">Nouvel examen</a></li>
                            <li><a href="/gestion/nouvelsouscription">Gestion souscription</a></li>
                            <li><a href="/gestion/constantesecretaire">Gestion constantes</a></li>
                            <li><a href="/gestion/getpatientdata">Liste des patients</a></li>
                            <li><a href="/gestion/getcalendrierdv">Calendrier des RDV</a></li>
                            <li><a href="/gestion/deconnexion">Se d&eacute;connecter</a></li>
                            <!--<li><a target="_blank" href="/gestion/testfichiers">Test fichiers</a></li>-->
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
                        <div class="col-sm-3">
                            <h4 class="page-title">Facturation</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">Gestion des patients</li>
                            </ol>
                        </div>

                        <div class="col-sm-9" style="text-align:right">
                            <img src="<c:url value='/images/accueil1.png'/>"
                                 Style="width:80px; height:60px"/>
                        </div>
                    </div>
                </div>
                <!-- end row -->


                <!-- Add image : -->
                <div class="row">
                    <div class="col-xl-4 col-md-6">
                        <div class="card directory-card">
                            <div class="card-body">
                                <div class="float-left mr-4">
                                    <img src="<c:url value='/images/mois.jpg' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">Montant annuel</h5>
                                <p class="font-12 mb-2">Proportion</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty montantannuel) ? montantannuel : "0"}</p>
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
                                    <img src="<c:url value='/images/conjoint.png' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">Nombre de patients journalier</h5>
                                <p class="font-12 mb-2">Proportion</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty patientjournalier) ? patientjournalier : "0"}</p>
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
                                    <img src="<c:url value='/images/argent.jpg' />" alt=""
                                         class="img-fluid img-thumbnail rounded-circle thumb-lg">
                                </div>
                                <h5 class="text-primary font-18 mt-0 mb-1">Montant journalier</h5>
                                <p class="font-12 mb-2">Proportion</p>
                                <p class="mb-4" style="font-weight: bold">${(!empty montantjournalier) ? montantjournalier : "0"}</p>
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
                                <h4 class="mt-0 header-title">Statistiques</h4>
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="nav-item" id="limotif">
                                        <a id="amotif" class="nav-link active" data-toggle="tab"
                                           href="#facturation" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-home"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Facturation</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="liattanterdv">
                                        <a id="aattanterdv" class="nav-link" data-toggle="tab"
                                           href="#attanterdv" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-cog"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Demande de RDV</span>
                                        </a>
                                    </li>

                                    <!-- Reservation  -->
                                    <li class="nav-item" id="lireservation">
                                        <a id="areservation" class="nav-link" data-toggle="tab"
                                           href="#reservation" role="tab">
                                            <span class="d-block d-sm-none">
                                                <i class="fas fa-cog"></i>
                                            </span>
                                            <span class="d-none d-sm-block">Gestion des r&eacute;servations</span>
                                        </a>
                                    </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div class="tab-pane active p-3" id="facturation" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th>Nom </th>
                                                            <th>Dates</th>
                                                            <th>Montant</th>
                                                            <th>Service</th>
                                                            <th>Assurance</th>
                                                            <th>Type client</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <c:forEach var="facture" items="${listefacturations}">
                                                            <tr>
                                                                <td>${facture[0]}</td>
                                                                <td>${facture[1]}</td>
                                                                <td>${facture[2]}</td>
                                                                <td>${facture[3]}</td>
                                                                <td>${facture[5]}</td>
                                                                <td>${facture[6]}</td>
                                                                <td>
                                                                    <a title="Afficher l'historique" href="/gestion/modiffacture/${facture[4]}" style="color: #db1637">
                                                                        <i class="fas fa-check-double"></i>
                                                                    </a>

                                                                    &nbsp;&nbsp;
                                                                    <a target="_blank" title="G&eacute;n&eacute;rer le dossier patient"
                                                                       href="/gestion/generecmcdossier/${facture[4]}" style="color: blue">
                                                                        <i class="fas fa-briefcase-medical"></i>
                                                                    </a>

                                                                    &nbsp;&nbsp;
                                                                    <a target="_blank" title="Test dossier patient"
                                                                       href="/gestion/generecmcdossiercons/${facture[4]}" style="color: orange">
                                                                        <i class="fas fa-briefcase-medical"></i>
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

                                    <div class="tab-pane p-3" id="attanterdv" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table id="tablerdv" class="table table-bordered dt-responsive nowrap"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                            <tr>
                                                                <th>Date </th>
                                                                <th>Patient </th>
                                                                <th>Service</th>
                                                                <th>Med&eacute;cin</th>
                                                                <th>Heure</th>
                                                                <th>Suggestion planning</th>
                                                                <th>Actions</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="donnee" items="${listeRdvAttente}">
                                                                <tr>
                                                                    <td>${donnee[1]}</td>
                                                                    <td>${donnee[2]}</td>
                                                                    <td>${donnee[3]}</td>
                                                                    <td>${donnee[4]}</td>
                                                                    <td>${donnee[5]}</td>
                                                                    <td style="font-weight: bold; font-size: 11px">${donnee[7]}</td>
                                                                    <td>
                                                                        <a title="Traiter la demande" href="/gestion/modifpriserdv/${donnee[0]}" style="color: #db1637">
                                                                            <i class="fas fa-check-double"></i>
                                                                        </a>
                                                                        &nbsp;&nbsp;&nbsp;
                                                                        <a title="Afficher le calendrier" href="/gestion/calendrierrdvmedecin/${donnee[6]}"
                                                                           style="color: #47DE38">
                                                                            <i class="far fa-calendar-check"></i>
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



                                    <div class="tab-pane p-3" id="reservation" role="tabpanel">
                                        <div class="col-lg-12">

                                            <div class="card">
                                                <div class="card-body">
                                                    <table id="tablereservation" class="table table-bordered dt-responsive nowrap"
                                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                        <thead>
                                                        <tr>
                                                            <th>Membre</th>
                                                            <th>Num&eacute;ro</th>
                                                            <th>Email</th>
                                                            <th>Gestionnaire</th>
                                                            <th>Date</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="donnee" items="${listeReservation}">
                                                            <tr>
                                                                <td>${donnee[0]}</td>
                                                                <td>${donnee[1]}</td>
                                                                <td>${donnee[2]}</td>
                                                                <td>${donnee[3]}</td>
                                                                <td>${donnee[4]}</td>
                                                                <td>
                                                                    <a title="Afficher l'historique" href="/gestion/validereservation/${donnee[5]}"
                                                                       style="color: #1EC610">
                                                                        <i class="fas fa-cart-plus"></i>
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


                                    <!--
                                    <div class="tab-pane p-3" id="divexamenclinique" role="tabpanel">
                                        <div class="col-lg-9">
                                            <textarea maxlength="300" placeholder="examen clinique..." name="examenclinique" class="form-control" rows="7" >${(!empty consultation) ? consultation[23] : ''}</textarea>
                                        </div>
                                    </div>-->

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
                $("#tablerdv").DataTable();
                $("#tablereservation").DataTable();
            });
        });
    </script>
</body>
</html>



