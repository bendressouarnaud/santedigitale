<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 24/11/2020
  Time: 18:36
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
                    <li class="menu-title">Menu principal</li>
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
                            <li><a href="/gestion/getlistepatientshosp">Liste Hospitalisations</a></li>
                            <li><a href="/gestion/getlistepatientsexam">Liste examens &agrave; faire</a></li>
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
                        <div class="col-sm-3">
                            <h4 class="page-title">Accueil</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">Gestion des patients</li>
                            </ol>
                        </div>

                        <div class="col-sm-9" style="text-align:right">
                            <img src="<c:url value='/images/patients.png'/>"
                                 Style="width:80px; height:60px"/>
                        </div>
                    </div>
                </div>

                <!-- end row -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">

                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="tab" href="#aujourdhui" role="tab">
                                            <span class="d-block d-sm-none"><i class="fas fa-home"></i></span>
                                            <span class="d-none d-sm-block">Patients cr&eacute;&eacute;s aujourd'hui</span>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#historique" role="tab">
                                            <span class="d-block d-sm-none"><i class="far fa-user"></i></span>
                                            <span class="d-none d-sm-block">Historique des patients</span>
                                        </a>
                                    </li>
                                </ul>

                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div class="tab-pane active p-3" id="aujourdhui" role="tabpanel">
                                        <table id="tabaujourdhui" class="table table-bordered dt-responsive nowrap"
                                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                            <thead>
                                            <tr>
                                                <th>Patient </th>
                                                <th>Date naissance</th>
                                                <th>Sexe</th>
                                                <th>Profession</th>
                                                <%--<th>Domicile</th>--%>
                                                <th>Assurance</th>
                                                <th>Particulier</th>
                                                <th>Actions</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <c:forEach var="patient" items="${listepatientToday}">
                                                <tr>
                                                    <td>${patient[0]}</td>
                                                    <td>${patient[1]}</td>
                                                    <td>${patient[2]}</td>
                                                    <td>${patient[3]}</td>
                                                        <%--<td>${patient[4]}</td>--%>
                                                    <td>${patient[5]}</td>
                                                    <td>${patient[6]}</td>
                                                    <td>
                                                        <a target="_blank" title="G&eacute;n&eacute;rer le dossier m&eacute;dical"
                                                           href="/gestion/genererdossierpat/${patient[7]}"
                                                           style="color: #1359D1">
                                                            <i class="fas fa-book-open"></i>
                                                        </a>


                                                        &nbsp;&nbsp;
                                                        <a title="Modifier les informations du patient"
                                                           href="/gestion/modifierpatinfo/${patient[7]}"
                                                           style="color: #58B40B">
                                                            <i class="fas fa-check-double"></i>
                                                        </a>
                                                        &nbsp;&nbsp;
                                                        <a title="Prise de constante"
                                                           href="/gestion/prisedeconstante/${patient[7]}"
                                                           style="color: #F0CD0B">
                                                            <i class="fas fa-plus-square"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="tab-pane p-3" id="historique" role="tabpanel">
                                        <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                            <thead>
                                                <tr>
                                                    <th>Patient </th>
                                                    <th>Date naissance</th>
                                                    <th>Date cr&eacute;ation dossier</th>
                                                    <th>Sexe</th>
                                                    <th>Profession</th>
                                                    <%--<th>Domicile</th>--%>
                                                    <th>Assurance</th>
                                                    <th>Particulier</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                            <c:forEach var="patient" items="${listepatient}">
                                                <tr>
                                                    <td>${patient[0]}</td>
                                                    <td>${patient[1]}</td>
                                                    <td>${patient[8]}</td>
                                                    <td>${patient[2]}</td>
                                                    <td>${patient[3]}</td>
                                                    <%--<td>${patient[4]}</td>--%>
                                                    <td>${patient[5]}</td>
                                                    <td>${patient[6]}</td>
                                                    <td>
                                                        <a target="_blank" title="G&eacute;n&eacute;rer le dossier m&eacute;dical"
                                                           href="/gestion/genererdossierpat/${patient[7]}"
                                                           style="color: #1359D1">
                                                            <i class="fas fa-book-open"></i>
                                                        </a>


                                                        &nbsp;&nbsp;
                                                        <a title="Modifier les informations du patient"
                                                           href="/gestion/modifierpatinfo/${patient[7]}"
                                                           style="color: #58B40B">
                                                            <i class="fas fa-check-double"></i>
                                                        </a>
                                                        &nbsp;&nbsp;
                                                        <a title="Prise de constante"
                                                           href="/gestion/prisedeconstante/${patient[7]}"
                                                           style="color: #F0CD0B">
                                                            <i class="fas fa-plus-square"></i>
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
                <!-- end row -->
                <div class="row">



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

         $(document).ready(function() {
            $().ready(function () {
               $('#tabaujourdhui').DataTable();
            });
         });

    </script>

</body>
</html>
