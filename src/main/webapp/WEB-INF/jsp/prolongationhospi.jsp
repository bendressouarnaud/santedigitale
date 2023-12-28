<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 04/01/2021
  Time: 07:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8">

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
        <title>GestPAT - Prolongation Hospitalisation</title>
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
                                    <c:if test="${!empty lienretour}">
                                        <li><a href="${lienretour}">Retour</a></li>
                                    </c:if>
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

                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="mt-0 header-title">Ajouter les informations</h4>

                                        <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                            action="${lienenregavisprolong}"
                                            method="POST" >


                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-form-label">Diagnostic &eacute;voqu&eacute;</label>
                                                        <div class="col-lg-9">
                                                            <textarea maxlength="400" style="font-size: 20px" placeholder="Diagnostic &eacute;voqu&eacute; (400 caract&egrave;res max.)..." class="form-control" rows="3" name="diagnosticevoq">${ diagnosticevoq }</textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-form-label">Motif de la prolongation</label>
                                                        <div class="col-lg-9">
                                                            <textarea maxlength="400" style="font-size: 20px" placeholder="Renseigner le motif (400 caract&egrave;res max.)..." class="form-control" rows="3" name="motifprolong">${ motif }</textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- SIGNES PARACLINIQUES  -->
                                            <div class="row">
                                                 <div class="col-lg-12">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h4 class="mt-0 header-title">Signes paracliniques</h4>
                                                            <div class="row">

                                                                <div class="col-md-6">
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-form-label">Biologiques</label>
                                                                        <div class="col-lg-9">
                                                                            <textarea maxlength="400" style="font-size: 20px" class="form-control" rows="3" name="biologiques">${ biologiques }</textarea>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-6">
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-form-label">Radiologiques</label>
                                                                        <div class="col-lg-9">
                                                                            <textarea maxlength="400" style="font-size: 20px" class="form-control" rows="3" name="radiologiques">${ radiologiques }</textarea>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                 </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-form-label">Suspicion diagnostic actualis&eacute;</label>
                                                        <div class="col-lg-9">
                                                            <textarea maxlength="400" style="font-size: 20px" placeholder="Diagnostic actualis&eacute; (400 caract&egrave;res max.)..." class="form-control" rows="3" name="suspicion">${ diagnostic }</textarea>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-form-label">Nombre de jour(s) suppl&eacute;mentaire(s) demand&eacute;(s)</label>
                                                        <div class="col-lg-3">
                                                            <input maxlength="2" placeholder="..." id="nbrejour" name="nbrejour"
                                                                   type="text" class="form-control"
                                                                   value="${ jour }" >
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

                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
