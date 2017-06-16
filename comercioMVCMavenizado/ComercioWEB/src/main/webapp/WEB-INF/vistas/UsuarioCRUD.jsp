<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de usuarios</h2>

<table border="1">
	<thead>
		<tr>
			<th>Nick</th>
			<th>Nombre</th>
			<th>Primer apellido</th>
			<th>Segundo apellido</th>
			<th colspan="2">Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>${usuario.nickusuario}</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.apellido1}</td>
				<td>${usuario.apellido2}</td>
				<td><a href="usuarioCRUD?op=modificar&id=${usuario.id}">-
						Modificar -</a></td>
				<td><a href="usuarioCRUD?op=borrar&id=${usuario.id}">-
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
		href="ControladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>