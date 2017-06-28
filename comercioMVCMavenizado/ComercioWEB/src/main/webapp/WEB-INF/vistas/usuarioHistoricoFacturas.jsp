<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Listado de Facturas:</h2>

<table>
	<tr>
		<td><input type="text" readonly="readonly"
			value="NUMERO DE FACTURA"> <input
			type="text" readonly="readonly" value=${fn:toUpperCase("Fecha")}>
			<input
			type="text" readonly="readonly" value=${fn:toUpperCase("Hora")}>
		</td>
	</tr>
	<c:forEach items="${requestScope.facturaHistoricos}" var="factura">
		<tr>
			<td>
				<form action="controladorMenuUsuarios" method="post">
					<input type="hidden" name="id_factura" value="${factura.id}" /> <input
						type="text" name="numero_factura"
						value="${factura.numero_factura}" readonly="readonly" /> <input
						type="hidden" name="id_usuario" value="${factura.id_usuario}" />
					<input type="date" name="fecha" value="${factura.fecha}"
						readonly="readonly" /> <input type="submit" name="op"
						value="Ver factura" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<div>
	<a id="botonVolverMenu"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">-
		Volver al menu de cliente -</a>
</div>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>