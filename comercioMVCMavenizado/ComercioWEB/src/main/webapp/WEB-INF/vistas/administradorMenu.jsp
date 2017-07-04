<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<h2 style="margin: 20px;">Opciones de administrador:</h2>

<div class="btn-group" style="margin: 20px;" role="group">
		
		<a class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionUsuarios">-
		Gestion de usuarios -</a> 
		
		<a class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeProductos">-
		Gestion de productos -</a>
		
		<a class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeStock">-
		Gestion de Stock -</a>
		
		<a class="btn btn-primary" role="button"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeFacturas">-
		Gestion de facturas -</a>
</div>

<div class="botonera">
	<a onclick="return confirm('¿Estás seguro de que quieres salir');"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=desconectar">-
		Volver al inicio -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>