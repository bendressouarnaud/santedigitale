
<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 24/06/2020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport"
            content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
        <title>GestPAT - Erreur 404</title>
        <meta content="Admin Dashboard" name="description">
        <meta content="Themesbrand" name="author">
        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
        <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
        <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
        <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">
    </head>
    <body><!-- Begin page -->
        <div class="ex-pages">
            <div class="content-center">
                <div class="content-desc-center">
                    <div class="container">
                        <div class="card mo-mt-2">
                            <div class="card-body">
                                <div class="row align-items-center">
                                    <div class="col-lg-4 offset-lg-1">
                                        <div class="ex-page-content">
                                            <h1 class="text-dark">404!</h1>
                                            <h4 class="mb-4">D&eacute;sol&eacute;, page inexistante</h4>
                                            <p class="mb-5">La ressource demand&eacute;e est soit
                                                inexistante, soit indisponible.
                                            </p>
                                            <a class="btn btn-primary mb-5 waves-effect waves-light"
                                               href="/gestion/connexpatient">
                                                <i class="fas fa-home"></i> Retour Page Accueil
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 offset-lg-1">
                                        <img src="<c:url value='/images/error.png'/>" alt=""
                                             class="img-fluid mx-auto d-block">
                                    </div>
                                </div>
                            </div>
                        </div><!-- end card -->
                    </div><!-- end container -->
                </div>
            </div>
        </div>
        <!-- end error page -->

        <!-- jQuery  -->
        <script src="<c:url value='/js/jquery.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
        <script src="<c:url value='/js/metisMenu.min.js' />"></script>
        <script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
        <script src="<c:url value='/js/waves.min.js' />"></script><!-- App js -->
        <script src="<c:url value='/js/app.js' />"></script>
    </body>
</html>
