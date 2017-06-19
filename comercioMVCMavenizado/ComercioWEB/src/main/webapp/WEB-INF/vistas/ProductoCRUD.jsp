<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 style="margin: 20px;">Mantenimiento de productos</h2>

<table border="1">
	<thead>
		<tr>
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
				<td>${producto.nombre}</td>
				<td>${producto.precio}</td>
				<td>${producto.descripcion}</td>
				<td>${producto.url_producto_img}</td>
				<td><a href="productoCRUD?op=modificar&nombre=${producto.nombre}">-
						Modificar -</a></td>
				<td><a href="productoCRUD?op=borrar&nombre=${producto.nombre}">-
						Borrar -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botoneraAlta">
	<a id="botonAltaProducto" href="productoCRUD?op=alta">- Dar de alta
		nuevo producto -</a>
</div>
<div class="botonera">
	<a id="botonIrMenuAdministrador"
		href="ControladorMenuAdministradores">- Salir al menu de
		opciones de administrador -</a>
</div>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>