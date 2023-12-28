<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
		<title>Verifions le RETOUR</title>
	</head>
	<body>
		<div class="form-group">
        	<c:if test="${!empty declaration}">
                <h3>${declaration.getNombebe()}</h3>
            </c:if>
        </div>

        <div class="form-group">
        	<c:if test="${empty modifier}">
                <h3>Existe pas</h3>
            </c:if>
        </div>

        <div class="form-group">
        	<h3>${(!empty declaration) ? declaration.getPere() : "Pere existe pas !!!"}</h3>
        </div>
	</body>
</html>