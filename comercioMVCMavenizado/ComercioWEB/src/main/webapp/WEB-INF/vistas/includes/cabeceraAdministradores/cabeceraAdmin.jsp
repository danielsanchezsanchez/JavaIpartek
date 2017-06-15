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
			<c:if test="${usuario.nombre==null or usuario.nombre=='' }">
				<c:if
					test="${pageContext.request.requestURI=='/WEB-INF/vistas/adminsform.jsp'}">
					<p id="registroboton">- Iniciando sesion -</p>
				</c:if>
				<c:if
					test="${pageContext.request.requestURI!='/WEB-INF/vistas/adminsform.jsp'}">
					<c:if
						test="${pageContext.request.requestURI=='/WEB-INF/vistas/usuarioRegistroInicial.jsp'}">
						<p id="registroboton">- Registrando usuario -</p>
					</c:if>
					<c:if
						test="${pageContext.request.requestURI!='/WEB-INF/vistas/usuarioRegistroInicial.jsp'}">
						<a id="loginboton" href="${pageContext.request.contextPath}/controladorIndex?op=conectarUsuario">-
							Iniciar sesion - |</a>
						<a id="registroboton" href="${pageContext.request.contextPath}/controladorIndex?op=registro">-
							Registrar usuario -</a>
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${usuario.nombre!=null and usuario.nombre!='' }">
				<c:if
					test="${pageContext.request.requestURI=='/WEB-INF/vistas/adminsform.jsp'}">
					<p id="registroboton">- Iniciando sesion -</p>
				</c:if>
				<c:if
					test="${pageContext.request.requestURI!='/WEB-INF/vistas/adminsform.jsp'}">
					<p id="usuarioDesconectar">
						- Bienvenido ${fn:toUpperCase(usuario.nombre)} | <a
							href="${pageContext.request.contextPath}/controladorIndex?op=desconectarUsuario">Desconectar
							-</a>
					</p>
				</c:if>
			</c:if>
		</div>
	</header>