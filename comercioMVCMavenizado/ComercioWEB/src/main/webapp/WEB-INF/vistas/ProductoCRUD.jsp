<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Mantenimiento de productos</h2>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nombre</th>
							<th>Precio</th>
							<th>Descripcion</th>
							<th>Imagen</th>
							<th colspan="2">Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.productos}" var="producto">
							<tr>
								<th>${producto.ID}</th>
								<td>${producto.nombre}</td>
								<td>${producto.precio}</td>
								<td>${producto.descripcion}</td>
								<td>${producto.url_producto_img}</td>
								<td><a class="btn btn-info btn-sm"
									href="controladorProductosAdmin?op=modificar&nombre=${producto.nombre}">-
										Modificar -</a></td>
								<td><a class="btn btn-info btn-sm"
									href="controladorProductosAdmin?op=borrar&nombre=${producto.nombre}">-
										Borrar -</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoneraAlta">
					<a class="btn btn-warning btn-lg btn-block"
						href="controladorProductosAdmin?op=alta">- Dar de alta nuevo
						producto -</a>
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