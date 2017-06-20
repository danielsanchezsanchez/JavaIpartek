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
				<th>Añadir al carrito</th>
			</tr>	</thead>
		<tbody>
			<c:forEach items="${requestScope.productos}" var="producto">
				<tr>
					<td>${producto.ID}</td>
					<td>${producto.nombre}</td>
					<td>${producto.precio} &euro;</td>
					<td>${producto.descripcion}</td>
					<td>Imagen en proceso</td>
					<td><a href="controladorMenuUsuarios?opCompra=añadir&id=${producto.ID}">-
						Añadir a la compra -</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="botonera">
				<a onclick="return confirm('¿Estás seguro de que quieres salir');" id="botonSalir" href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">-
					Volver al inicio -</a> 
			</div>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>