<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 29/06/2020
  Time: 21:51
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
    <title>GestPAT - Configuration service</title>
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
                            <li><a href="/gestion/nouvelsouscription">Retour</a></li>
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
                            <img src="<c:url value='/images/parametres.jpg'/>"
                                 Style="width:90px; height:70px"/>
                        </div>
                    </div>
                </div>
                <!-- end row -->

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Informations</h4>
                                <p class="text-muted m-b-30 font-14">Param&egrave;tres</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="${(!empty modification) ? modification : creation}"
                                      method="POST">
                                    <!--<h3>Seller Details</h3>-->
                                    <fieldset>

                                        <div class="row">
                                            <div class="col-md-6" style="font-weight:bold">
                                                Ajuster les jours
                                            </div>
                                        </div>

                                        <!--  Liste des patients  &  age -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <table class="table mb-0">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th>Jour</th>
                                                            <th>Valider</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <label>Lundi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="lundi" id="switchlun" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getLundi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchlun" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Mardi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="mardi" id="switchmar" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getMardi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchmar" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Mercredi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="mercredi" id="switchmer" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getMercredi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchmer" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Jeudi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="jeudi" id="switchjeu" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getJeudi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchjeu" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Vendredi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="vendredi" id="switchven" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getVendredi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchven" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Samedi</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="samedi" id="switchsam" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getSamedi()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchsam" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <label>Dimanche</label>
                                                            </td>
                                                            <td style="line-height: 5%;">
                                                                <div>
                                                                    <input type="checkbox" name="dimanche" id="switchdim" switch="bool" value="1"
                                                                    ${(!empty tacheservice) ? (tacheservice.getDimanche()==1) ? "checked='checked'" : "" : ""}>
                                                                    <label for="switchdim" data-on-label="Oui" data-off-label="Non"></label>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Heure <span style="color:red">*</span></label>
                                                    <div class="col-lg-6">
                                                        <select name="heure" class="form-control select2">
                                                            <c:forEach items="${listeHeure}" var="heure" >
                                                                <option ${(!empty tacheservice) ? (tacheservice.getHeure()==heure.getIdheu()) ? "selected" : "" : ""} value="${heure.getIdheu()}">
                                                                        ${heure.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Message <span style="color:red">*</span></label>
                                                    <div class="col-lg-6">
                                                        <textarea maxlength="300" placeholder="Renseigner le message..." name="message" class="form-control" rows="7" >${(!empty tacheservice) ? tacheservice.getMessage() : ''}</textarea>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                        <!--<div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>-->

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
            $(".form-control.select2").select2();
        });
    });
</script>
</body>
</html>
