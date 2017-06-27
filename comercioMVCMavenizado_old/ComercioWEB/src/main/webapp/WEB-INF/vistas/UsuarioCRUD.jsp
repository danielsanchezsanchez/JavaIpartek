<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de usuarios</h2>

<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nick</th>
			<th>Nombre</th>
			<th>Primer apellido</th>
			<th>Segundo apellido</th>
			<th>Rol del usuario:</th>
			<th colspan="2">Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>${usuario.id}</td>
				<td>${usuario.nickusuario}</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.apellido1}</td>
				<td>${usuario.apellido2}</td>
				<c:if test="${usuario.rol == '1'}">
					<td>Administrador</td>
				</c:if>
				<c:if test="${usuario.rol == '2'}">
					<td>Cliente</td>
				</c:if>
				<td><a href="usuarioCRUD?op=modificar&nickusuario=${usuario.nickusuario}">-
						Modificar -</a></td>
				<td><a href="usuarioCRUD?op=borrar&nickusuario=${usuario.nickusuario}">-
						Borrar -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botoneraAlta">
	<a id="botonAltaUsuario" href="usuarioCRUD?op=alta">- Dar de alta
		nuevo usuario -</a>
</div>
<div class="botonera">
	<a id="botonIrMenuAdministrador"
		href="controladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>