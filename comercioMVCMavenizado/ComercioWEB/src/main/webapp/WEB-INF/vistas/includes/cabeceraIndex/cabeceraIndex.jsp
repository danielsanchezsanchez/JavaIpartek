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
			<c:set var="rutaIndex" value="${pageContext.request.contextPath}/WEB-INF/vistas/index.jsp" />
			<c:set var="rutaLogin" value="${pageContext.request.contextPath}/WEB-INF/vistas/usuarioLogin.jsp" />
			<c:set var="rutaRegistro" value="${pageContext.request.contextPath}/WEB-INF/vistas/usuarioRegistro.jsp" />
			<c:if test="${pageContext.request.requestURI==rutaIndex}">
				<a id="loginboton"
					href="${pageContext.request.contextPath}/controladorIndex?op=conectarUsuario">-
					Iniciar sesion - |</a>
				<a id="registroboton"
					href="${pageContext.request.contextPath}/controladorIndex?op=registro">-
					Registrar usuario -</a>
			</c:if>
			<c:if test="${pageContext.request.requestURI==rutaLogin}">
				<label id="logeadoboton"
					>-
					Logeando usuario - </label>
			</c:if>
			<c:if test="${pageContext.request.requestURI==rutaRegistro}">
				<label id="registradoboton"
					>-
					Registrando usuario - </label>
			</c:if>
		</div>
	</header>