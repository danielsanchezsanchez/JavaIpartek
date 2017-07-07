<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Mantenimiento de usuarios</h2>

				<table class="table table-bordered">
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
								<td><a class="btn btn-info btn-sm"
									href="controladorUsuariosAdmin?op=modificar&nickusuario=${usuario.nickusuario}">
										Modificar</a></td>
								<td><a class="btn btn-info btn-sm"
									href="controladorUsuariosAdmin?op=borrar&nickusuario=${usuario.nickusuario}">
										Borrar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoneraAlta">
					<a class="btn btn-warning btn-lg btn-block"
						href="controladorUsuariosAdmin?op=alta"> Dar de alta nuevo
						usuario</a>
				</div>
			</div>
		</nav>
	</div>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="botonera">
				<a class="btn btn-lg btn-success"
					href="controladorMenuAdministradores"> Salir al menu de
					opciones de administrador</a>
			</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>