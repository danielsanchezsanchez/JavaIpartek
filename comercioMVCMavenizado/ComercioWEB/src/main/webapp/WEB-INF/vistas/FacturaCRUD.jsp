<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Mantenimiento de factura</h2>

				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Numero de la factura</th>
							<th>Usuario</th>
							<th>Fecha de la compra</th>
							<th colspan="3">Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.facturas}" var="factura">
							<tr>
								<td>${factura.numero_factura}</td>
								<td>${factura.usuario.nickusuario}</td>
								<td>${factura.fecha}</td>
								<td><a class="btn btn-info btn-sm"
									href="facturaCRUD?op=modificar&numero_factura=${factura.numero_factura}">-
										Modificar -</a></td>
								<td><a class="btn btn-info btn-sm"
									href="facturaCRUD?op=borrar&numero_factura=${factura.numero_factura}">-
										Borrar -</a></td>
								<td><a class="btn btn-info btn-sm"
									href="facturaCRUD?op=desplegar&numero_factura=${factura.numero_factura}">-
										Desplegar factura -</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="botoneraAlta">
					<a class="btn btn-warning btn-lg btn-block"
						href="facturaCRUD?op=alta">- Dar de alta nueva factura -</a>
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