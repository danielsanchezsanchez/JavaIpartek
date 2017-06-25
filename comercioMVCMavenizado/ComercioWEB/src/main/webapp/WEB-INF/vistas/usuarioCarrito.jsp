<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Listado del carrito: productos cogidos</h2>

<table>
	<c:forEach items="${requestScope.articulos}" var="articulo">
		<tr>
			<td>
				<form action="controladorMenuUsuarios" method="post">
					<input type="hidden" name="id" value="${articulo.producto.ID}"/>
					<input type="text" name="nombre" value="${articulo.producto.nombre}" /> 
					<input type="number" name="cantidad" value="${articulo.cantidad}" />
					<input type="submit" name="op" value="Modificar" />
					<input type="submit" name="op" value="Borrar" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<div>
	<a onclick="return confirm('¿Comprar? - ¡¡¡¡ESTAS USANDO DINERO REAL!!!! - ');"
		id="botonAceptarCompra"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=aceptarCompra">-
		ACEPTAR COMPRA -</a>
</div>
<div class="botonera">
	<a id="botonVolverTienda"
		href="controladorMenuUsuarios?op=seguirComprando&ruta=estabaViendoElCarrito">-
		Ver la tienda -</a>

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
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>