
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
		<title>Connexion - Gestion PATIENT</title>
		<meta content="Admin Dashboard" name="description">
		<meta content="Themesbrand" name="author">
		<!--<link rel="shortcut icon" href="/images/favicon.ico">-->
		<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
		<link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">
	</head>
	<body style="background-image: url(<c:url value='/images/images3.jpg'/>); background-repeat: no-repeat; background-attachment: fixed; background-size: cover;">
		<!--<div class="home-btn d-none d-sm-block"><a href="#" class="text-dark"><i class="fas fa-home h2"></i></a></div>-->
		<div class="wrapper-page">
			<div class="card overflow-hidden account-card mx-3">
				<div class="bg-primary p-4 text-white text-center position-relative">
					<h4 class="font-20 m-b-5">Bienvenue !</h4>
					<p class="text-white-50 mb-4">Connexion</p>
					<a href="#" class="logo logo-admin">
						<img src="<c:url value='/images/logo-sm.png' />" height="24" alt="logo">
					</a>
				</div>
				<div class="account-card-content">
					<!--<form class="form-horizontal m-t-30" action="connexacte" method="GET">-->
					<form class="form-horizontal m-t-30" action="${contextPath}/login" method="POST">
						<div class="form-group">
							<label for="username">Identifiant</label>
							<input type="text" class="form-control" id="username" name="username">
						</div>
						<div class="form-group">
							<label for="userpassword">Mot de passe</label>
							<input type="password" class="form-control" id="password" name="password">
						</div>

						<p class="forgetmenot"
						   style="width: 100%; color: red; text-align: center; font-weight: bold">
							${message}
						</p>

						<div class="form-group row m-t-20">
							<div class="col-sm-6 text-right" style="text-align: center;">
								<button class="btn btn-primary w-md waves-effect waves-light" type="submit">Connexion</button>
							</div>
						</div>

						<p style="width: 100%; color: cadetblue; text-align: center; margin-top: 10px">
							Con&ccedil;u et r&eacute;alis&eacute; par la <span style="font-style: italic;font-weight: bold;">Start-up ANKK</span> - 07640051
						</p>
					</form>
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
