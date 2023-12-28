<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 14/06/2020
  Time: 16:52
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
    <!--  SELECT2  -->
    <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">
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
                        <a href="javascript:void(0);"
                           class="waves-effect"><i class="fas fa-align-justify"></i>
                            <span> Actions <span class="float-right menu-arrow">
                            <i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <li><a href="/gestion/nouvelexamen">Retour</a></li>
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
                            <h4 class="page-title">Liste des examens</h4>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item active">
                                    R&egrave;glement des factures
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- end row -->

                <c:if test="${!empty messageerreur}">
                    <div class="row">
                        <div class="col-sm-6 col-md-3 m-t-30" id="messageerreur">
                            <div class="modal fade bs-example-modal-center" tabindex="-1"
                                 role="dialog" aria-labelledby="mySmallModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title mt-0">Information</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">x</button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Ce patient est rattach&eacute; au compte
                                                d'un souscripteur dont le solde du compte est
                                                <span style="color:red; font-weight: bold">faible</span> !</p>
                                            <p>Le syst&egrave;me ne peut donc pas d&eacute;biter son compte.</p>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                    </div>
                </c:if>


                <div class="row">
                    <div class="col-lg-12">

                        <form action="/gestion/modifacturelabo" method="POST" >

                            <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                   style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                <thead>
                                    <tr>
                                        <th>Nom </th>
                                        <th>Dates</th>
                                        <th>Montant</th>
                                        <th>Examen</th>
                                        <th>Ids.</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="valeur" items="${listefactureslabo}">
                                        <tr>
                                            <td><label>${valeur[0]}</label></td>
                                            <td><label>${valeur[1]}</label></td>
                                            <td>
                                                <input id="montant" name="montant[]" type="text" value="${valeur[2]}"
                                                    class="form-control">
                                            </td>
                                            <td><label>${valeur[3]}</label></td>
                                            <td>

                                                <select name="idcmx[]" class="form-control select2">
                                                    <option value="${valeur[4]}">${valeur[4]}</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                            <div class="row">
                                &nbsp;
                            </div>

                            <div class="row">
                                <div class="col-sm-6 text-center" >
                                    <button class="btn btn-primary w-md waves-effect waves-light"
                                            type="submit" name="submit-enr">
                                        Enregistrer
                                    </button>
                                </div>
                            </div>

                        </form>

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
<!-- SELECT2 -->
<script src="<c:url value='/js/select2.min.js' />"></script>
<!-- Datatable init js -->
<script src="<c:url value='/pages/datatables.init.js' />"></script>
<!--<script src="<c:url value='/pages/dashboard.js' />"></script>-->
<!-- App js -->
<script src="<c:url value='/js/app.js' />"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $().ready(function () {
            $(".form-control.select2").select2();

            // afficher le message d'erreur :
            if($("#messageerreur").length ) {
                $('.bs-example-modal-center').modal('toggle');
            }
        });
    });
</script>
</body>
</html>
