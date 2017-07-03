<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Opciones de administrador:</h2>

<div class="opcionesAdmin" class="btn-group" style="margin: 20px;" role="group">
		
		<a id="botonGestionUsuarios" class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionUsuarios">-
		Gestion de usuarios -</a> 
		
		<a id="botonGestionProductos" class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeProductos">-
		Gestion de productos -</a>
		
		<a id="botonGestionStock" class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeStock">-
		Gestion de Stock -</a>
		
		<a id="botonGestionFacturas" class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeFacturas">-
		Gestion de facturas -</a>
</div>

<div class="botonera">
	<a id="botonSalir" onclick="return confirm('¿Estás seguro de que quieres salir');"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=desconectar">-
		Volver al inicio -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>