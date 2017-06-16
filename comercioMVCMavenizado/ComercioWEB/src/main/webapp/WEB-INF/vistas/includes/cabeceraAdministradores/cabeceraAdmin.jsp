<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Productos S.L.</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/estilos.css">
<script src="${pageContext.request.contextPath}/js/funciones.js"></script>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<header>
		<div id="loginCasilla">
			<div id="loginCasilla">
				<label id="logeadoboton">- Bienvenido:
					${fn:toUpperCase(usuario.nickusuario)} - |</label> <a id="desconectarboton"
					onclick="return confirm('¿Estás seguro de que quieres salir');"
					href="${pageContext.request.contextPath}/admin/ControladorMenuAdministradores?op=desconectar">-
					Desconectar -</a>
			</div>
		</div>
	</header>