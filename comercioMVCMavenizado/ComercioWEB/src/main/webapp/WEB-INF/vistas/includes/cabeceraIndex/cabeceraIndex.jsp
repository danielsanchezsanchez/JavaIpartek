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
	<c:set var="rutaIndex"
		value="${pageContext.request.contextPath}/WEB-INF/vistas/index.jsp" />
	<c:set var="rutaLogin"
		value="${pageContext.request.contextPath}/WEB-INF/vistas/usuarioLogin.jsp" />
	<c:set var="rutaRegistro"
		value="${pageContext.request.contextPath}/WEB-INF/vistas/usuarioRegistro.jsp" />
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<c:if test="${pageContext.request.requestURI==rutaIndex}">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand"
							href="${pageContext.request.contextPath}/controladorIndex?op=registro">Registrar</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<form class="navbar-form navbar-right"
							action="${pageContext.request.contextPath}/controladorIndex?op=conectarUsuario"
							method="post">
							<div class="form-group">
								<input type="text" placeholder="Usuario" class="form-control"
									name="nickusuario">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Contrasenia"
									class="form-control" name="contrasenia">
							</div>
							<button type="submit" class="btn btn-success">Logear</button>
						</form>
					</div>
				</c:if>
				<c:if test="${pageContext.request.requestURI==rutaLogin}">
					<div class="alert alert-info" role="alert">Logeando usuario</div>
				</c:if>
				<c:if test="${pageContext.request.requestURI==rutaRegistro}">
					<label id="registradoboton">Registrando usuario</label>
				</c:if>
			</div>
		</nav>
	</header>