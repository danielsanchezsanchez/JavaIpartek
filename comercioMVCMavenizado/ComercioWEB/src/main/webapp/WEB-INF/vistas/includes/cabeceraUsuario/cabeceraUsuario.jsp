<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Productos S.L.</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones.js"></script>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<header>
		<div id="loginCasilla">
			<label id="logeadoboton">- Bienvenido ${usuario.nickusuario}
				- |</label> <a id="desconectarboton"
				onclick="return confirm('¿Estás seguro de que quieres salir');"
				href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">-
				Desconectar -</a>
		</div>
	</header>