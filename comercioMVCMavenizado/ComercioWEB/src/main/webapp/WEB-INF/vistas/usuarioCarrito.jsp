<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Listado de productos</h2>

<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Producto</th>
			<th>Precio</th>
			<th>Descripcion</th>
			<th>Imagen</th>
			<th>Cantidad</th>
			<th>Añadir al carrito</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				<td>${producto.ID}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precio}&euro;</td>
				<td>${producto.descripcion}</td>
				<td>Imagen en proceso</td>
				<td>Cantidad: <input min="0" max="100" style="width: 3em" type="number" value='1' name="cantidad" ></td>
				<td><a
					href="controladorMenuUsuarios?op=seguirComprando&id=${producto.ID}&cantidad=">-
						Añadir a la compra -</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="botonera">
	<a id="botonVolverMenu"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">-
		Volver al menu de cliente -</a> <a
		onclick="return confirm('¿Estás seguro de que quieres salir');"
		id="botonSalirIndex"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">-
		Volver al inicio -</a>
</div>

<script>cantidator();</script>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>