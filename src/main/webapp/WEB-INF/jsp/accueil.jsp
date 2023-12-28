
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
		<title>Choix Application</title>
		<meta content="Admin Dashboard" name="description">
		<meta content="Themesbrand" name="author">
		<!--<link rel="shortcut icon" href="/images/favicon.ico">-->
		<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Roboto:400,500|Sarabun:400,600,700" rel="stylesheet">
		<link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="home-btn d-none d-sm-block"><a href="#" class="text-dark"><i class="fas fa-home h2"></i></a></div>
		<div class="wrapper-page">
			<div class="card overflow-hidden account-card mx-3">
				<div class="bg-primary p-4 text-white text-center position-relative">
					<h4 class="font-20 m-b-5">Accueil</h4>
					<p class="text-white-50 mb-4">S&eacute;lectionner une application</p>
					<a href="index.html" class="logo logo-admin">
						<img src="<c:url value='/images/logo-sm.png' />" height="24" alt="logo">
					</a>
				</div>

				<div class="account-card-content">
				    <div class="form-group">
                    	<a href="/gestion/patient">Gestion des patients</a>
                    </div>
					<div class="form-group">
                    	<a href="/gestion/acte">Gestion des actes</a>
                    </div>
                    <div class="form-group">
                        <a href="/gestion/taxe">Gestion des taxes</a>
                    </div>
				</div>
			</div>
		</div><!-- end wrapper-page --><!-- jQuery  -->

		<script src="<c:url value='/js/jquery.min.js' />"></script>
		<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
		<script src="<c:url value='/js/metisMenu.min.js' />"></script>
		<script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
		<script src="<c:url value='/js/waves.min.js' />"></script>
		<!-- App js -->
		<script src="<c:url value='/js/app.js' />"></script>
	</body>
</html>