<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Listado del carrito: productos cogidos</h2>

<table>
	<c:forEach items="${requestScope.articulos}" var="articulo">
		<tr>
			<td>
				<form action="controladorMenuUsuarios" method="post">
					<input type="text" name="nombre" value="${articulo.producto.nombre}" /> 
					<input type="number" name="cantidad" value="${articulo.cantidad}" />
					<input type="submit" value="Guardar en carrito" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<div>
	<a
		href="controladorMenuUsuarios?op=seguirComprando&ruta=estabaViendoElCarrito">-
		Ver la tienda -</a>
</div>
<div class="botonera">
	<a id="botonVolverMenu"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">-
		Volver al menu de cliente -</a>
</div>
<div>
	<a onclick="return confirm('¿Estás seguro de que quieres salir');"
		id="botonSalirIndex"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">-
		Volver al inicio -</a>
</div>

<script>
	cantidator();
</script>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>