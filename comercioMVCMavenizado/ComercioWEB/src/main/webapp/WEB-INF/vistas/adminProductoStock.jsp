<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de productos</h2>

<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Imagen</th>
			<th>Alta en tienda</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				<th>${producto.ID}</th>
				<td>${producto.nombre}</td>
				<td>${producto.url_producto_img}</td>
				<td><a href="stockCRUD?op=altaTienda&id=${producto.ID}">-
						Alta en tienda -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botonera">
	<a id="botonIrMenuAdministrador"
		href="controladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>