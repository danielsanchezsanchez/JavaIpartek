<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Listado de Articulos por factura:</h2>

<table>
	<tr>
		<td><input type="text" readonly="readonly"
			value=${fn:toUpperCase("ID Factura")}> <input type="text"
			readonly="readonly" value=${fn:toUpperCase("ID Producto")}> <input
			type="text" readonly="readonly" value=${fn:toUpperCase("Cantidad")}>
		</td>
	</tr>
	<c:forEach items="${requestScope.articulos_Factura}" var="articulo">
		<tr>
			<td>
				<form action="controladorMenuUsuarios" method="post">
					<input type="number" name="id_factura"
						value="${articulo.id_factura}" readonly="readonly" /> <input
						type="number" name="id_producto" value="${articulo.id_producto}"
						readonly="readonly" /> <input type="number" name="cantidad"
						value="${articulo.cantidad}" readonly="readonly" /> <input
						type="submit" name="op" value="Modificar" /> <input type="submit"
						name="op" value="Borrar" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<div>
	<a id="botonVolverFacturas"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=verHistoricos">-
		Volver al menu de listado de facturas -</a>
</div>
<div>
	<a id="botonVolverMenu"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">-
		Volver al menu de cliente -</a>
</div>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>