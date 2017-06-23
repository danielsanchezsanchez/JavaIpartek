<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de factura</h2>

<table border="1">
	<thead>
		<tr>
			<th>Numero de la factura</th>
			<th>Usuario</th>
			<th>Fecha de la compra</th>
			<th colspan="2">Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturas}" var="factura">
			<tr>
				<td>${factura.numero_factura}</td>
				<td>${factura.usuario.nickusuario}</td>
				<td>${factura.fecha}</td>
				<td><a href="facturaCRUD?op=modificar&numero_factura=${factura.numero_factura}">-
						Modificar -</a></td>
				<td><a href="facturaCRUD?op=borrar&numero_factura=${factura.numero_factura}">-
						Borrar -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botoneraAlta">
	<a id="botonAltaFactura" href="facturaCRUD?op=alta">- Dar de alta
		nueva factura -</a>
</div>
<div class="botonera">
	<a id="botonIrMenuAdministrador"
		href="controladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>