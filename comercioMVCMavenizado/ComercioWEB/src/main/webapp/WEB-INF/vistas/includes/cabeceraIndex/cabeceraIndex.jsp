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
			<c:if test="${pageContext.request.requestURI=='/WEB-INF/vistas/index.jsp'}">
				<a id="loginboton"
					href="${pageContext.request.contextPath}/controladorIndex?op=conectarUsuario">-
					Iniciar sesion - |</a>
				<a id="registroboton"
					href="${pageContext.request.contextPath}/controladorIndex?op=registro">-
					Registrar usuario -</a>
			</c:if>
			<c:if test="${pageContext.request.requestURI=='/WEB-INF/vistas/usuarioLogin.jsp'}">
				<label id="loginboton"
					>-
					Logeando usuario - </label>
			</c:if>
			<c:if test="${pageContext.request.requestURI=='/WEB-INF/vistas/usuarioRegistro.jsp'}">
				<label id="loginboton"
					>-
					Registrando usuario - </label>
			</c:if>
		</div>
	</header>