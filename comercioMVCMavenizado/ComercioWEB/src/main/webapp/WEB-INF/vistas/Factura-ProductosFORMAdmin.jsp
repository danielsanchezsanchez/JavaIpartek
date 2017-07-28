<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Mantenimiento de facturas por compra:</h2>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Id de la factura</th>
							<th>Id del producto</th>
							<th>Cantidad</th>
							<th colspan="2">Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.compras}" var="articulo">
							<tr>
								<td>${articulo.id_factura}</td>
								<td>${articulo.id_producto}</td>
								<td>${articulo.cantidad}</td>
								<td><a class="btn btn-info btn-sm"
									href="facturaCRUD?op=modificarHistorico&id_factura=${articulo.id_factura}">-
										Modificar -</a></td>
								<td><a class="btn btn-info btn-sm"
									href="facturaCRUD?op=borrarHistorico&id_factura=${articulo.id_factura}">-
										Borrar -</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoneraAlta">
					<a class="btn btn-warning btn-lg btn-block"
						href="facturaCRUD?op=altaHistorico">- Dar de alta nueva factura -</a>
				</div>
				<div class="botonera">
				<a class="btn btn-lg btn-success"
					href="facturaCRUD">- Volver al menu de
					facturas -</a>
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