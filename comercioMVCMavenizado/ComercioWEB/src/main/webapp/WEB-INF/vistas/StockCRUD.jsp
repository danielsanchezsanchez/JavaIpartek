<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Mantenimiento de STOCK:</h2>

				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID Producto</th>
							<th>Nombre Producto</th>
							<th>Cantidad</th>
							<th colspan="2">Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.stock}" var="stock">
							<tr>
								<td>${stock.producto.ID}</td>
								<td>${stock.producto.nombre}</td>
								<td>${stock.stock}</td>
								<td><a class="btn btn-info btn-sm"
									href="controladorStocksAdmin?op=modificar&id_producto=${stock.producto.ID}">-
										Modificar -</a></td>
								<td><a class="btn btn-info btn-sm"
									href="controladorStocksAdmin?op=borrar&id_producto=${stock.producto.ID}">-
										Borrar -</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoneraAlta">
					<a class="btn btn-warning btn-lg btn-block"
						href="controladorStocksAdmin?op=alta">- Dar de alta stock de
						producto -</a>
				</div>
			</div>
		</nav>
	</div>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="botonera">
				<a class="btn btn-lg btn-success"
					href="controladorMenuAdministradores">- Salir al menu de
					opciones de administrador -</a>
			</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>