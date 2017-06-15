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
			<c:if test="${usuario.nickusuario==null or usuario.nickusuario=='' }">
				<c:if
					test="${pageContext.request.requestURI=='/WEB-INF/vistas/usuarioLogin.jsp'}">
					<p id="registroboton">- Iniciando sesion -</p>
				</c:if>
			</c:if>
		</div>
	</header>