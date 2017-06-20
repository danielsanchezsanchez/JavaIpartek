<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Opciones de administrador:</h2>

<div class="opcionesAdmin">
		<h2 style="margin: 20px; border: '1';">
		<a id="botonGestionUsuarios"
		href="${pageContext.request.contextPath}/admin/ControladorMenuAdministradores?op=gestionUsuarios">-
		Gestion de usuarios -</a> 
		
		<a id="botonGestionProductos"
		href="${pageContext.request.contextPath}/admin/ControladorMenuAdministradores?op=gestionDeProductos">-
		Gestion de productos -</a>
		
		<a id="botonGestionFacturas"
		href="${pageContext.request.contextPath}/admin/ControladorMenuAdministradores?op=gestionDeFacturas">-
		Gestion de facturas -</a></h2>
</div>

<div class="botonera">
	<a id="botonSalir" onclick="return confirm('¿Estás seguro de que quieres salir');"
		href="${pageContext.request.contextPath}/admin/ControladorMenuAdministradores?op=desconectar">-
		Volver al inicio -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>