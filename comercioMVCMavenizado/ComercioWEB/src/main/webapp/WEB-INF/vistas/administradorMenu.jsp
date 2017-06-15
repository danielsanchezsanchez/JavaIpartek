<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Opciones de administrador:</h2>

<div class="opcionesAdmin">
		<a id="botonGestionUsuarios"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionUsuarios">-
		Gestion de usuarios -</a> 
		
		<a id="botonGestionProductos"
		href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeProductos">-
		Gestion de productos -</a>
</div>

<div class="botonera">
	<a id="botonSalir"
		href="${pageContext.request.contextPath}/controladorIndex?op=desconectarUsuario">-
		Volver al inicio -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>