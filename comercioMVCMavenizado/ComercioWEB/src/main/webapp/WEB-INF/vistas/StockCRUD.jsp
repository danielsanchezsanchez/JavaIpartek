<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de STOCK:</h2>

<table border="1">
	<thead>
		<tr>
			<th>ID Producto</th>
			<th>Nombre Producto</th>
			<th>Cantidad</th>
			<th colspan="2">Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturas}" var="factura">
			<tr>
				<td>${stock.producto.ID}</td>
				<td>${stock.producto.nombre}</td>
				<td>${stock.stock}</td>
				<td><a href="stockCRUD?op=modificar&id_producto=${stock.producto.ID}">-
						Modificar -</a></td>
				<td><a href="stockCRUD?op=borrar&id_producto=${stock.producto.ID}">-
						Borrar -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botoneraAlta">
	<a id="botonAltaStock" href="stockCRUD?op=alta">- Dar de alta
		stock de producto -</a>
</div>
<div class="botonera">
	<a id="botonIrMenuAdministrador"
		href="controladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>