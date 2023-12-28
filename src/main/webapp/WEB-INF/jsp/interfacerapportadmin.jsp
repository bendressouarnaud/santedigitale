<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 11/09/2020
  Time: 06:27
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
    <title>GestPAT - Souscription</title>
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
                        </a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <li><a href="/gestion/accfacturation">Retour</a></li>
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
                            <h4 class="page-title">Statistiques</h4>
                        </div>
                    </div>
                </div>
                <!-- end row -->

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Informations</h4>
                                <p class="text-muted m-b-30 font-14">Veuillez d&eacute;finir les filtres</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="${modification}"
                                      method="POST">
                                    <!--<h3>Seller Details</h3>-->
                                    <fieldset>

                                        <!--  Liste des patients  &  age -->
                                        <div style="margin-top: 20px" class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Type rapport</label>
                                                    <div class="col-lg-9">
                                                        <select name="rapport" class="form-control select2">
                                                            <option value="1" ${ (!empty rapport) ? (rapport==1) ? "selected" : "" : "" }>Historique des paiements de consultation</option>
                                                            <option value="2" ${ (!empty rapport) ? (rapport==2) ? "selected" : "" : ""  }>Historique des paiements de consultation de l'ann&eacute;e</option>
                                                            <option value="3" ${ (!empty rapport) ? (rapport==3) ? "selected" : "" : ""  }>Nombre de paiement de frais de consultation par service et par mois</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Service</label>
                                                    <div class="col-lg-9">
                                                        <select name="service" class="form-control select2">
                                                            <option value="0" ${ (!empty service) ? (service==0) ? "selected" : "" : "selected"  }>---</option>
                                                            <c:forEach items="${listeService}" var="donnee" >
                                                                <option value="${donnee.getIdser()}"
                                                                    ${ (!empty service) ? (service==donnee.getIdser()) ? "selected" : "" : ""  }>
                                                                        ${ donnee.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <br>
                                            &nbsp;
                                            <br>
                                            <br>
                                        </div>

                                        <!-- FOR DATE :  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">P&eacute;riode d&eacute;but</label>
                                                    <div class="col-lg-9">
                                                        <input name="datedebut" value="${ !empty datedebut ? datedebut : dates }"
                                                               type="text" class="form-control datepicker">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">P&eacute;riode fin</label>
                                                    <div class="col-lg-9">
                                                        <input name="datefin" value="${dates}"
                                                               type="text" class="form-control datepicker">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div style="margin-top: 20px" class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Action</label>
                                                    <div class="col-lg-9">
                                                        <select name="action" class="form-control select2">
                                                            <option value="1">Afficher</option>
                                                            <option value="2">G&eacute;n&eacute;rer</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-sm-6 text-right" style="float: right; margin-bottom: 20px">
                                                <button class="btn btn-primary w-md waves-effect waves-light"
                                                        type="submit" name="submit-enr">
                                                    Soumettre
                                                </button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Afficher les donnees -->
                <c:if test="${!empty listeDonnees}">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                        <tr>
                                            <th>Patient </th>
                                            <th>Montant</th>
                                            <th>Dates</th>
                                            <th>Heure</th>
                                            <th>Service</th>
                                            <th>S&eacute;cr&eacute;taire</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="donnee" items="${listeDonnees}">
                                            <tr>
                                                <td>${donnee[0]}</td>
                                                <td>${donnee[1]}</td>
                                                <td>${donnee[2]}</td>
                                                <td>${donnee[3]}</td>
                                                <td>${donnee[4]}</td>
                                                <td>${donnee[5]}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>


                <!-- Afficher les donnees -->
                <c:if test="${!empty listeDonneesServMois}">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <table id="tableservice" class="table table-bordered dt-responsive nowrap"
                                           style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                        <tr>
                                            <th>Service</th>
                                            <th>Mois</th>
                                            <th>Total patient</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="donnee" items="${listeDonneesServMois}">
                                            <tr>
                                                <td>${donnee[0]}</td>
                                                <td>${donnee[1]}</td>
                                                <td>${donnee[2]}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>



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
    $(document).ready(function() {
        $().ready(function () {
            $(".form-control.datepicker").datepicker();
            $(".form-control.select2").select2();
            $("#tableservice").DataTable();
        });
    });
</script>
</body>
</html>
