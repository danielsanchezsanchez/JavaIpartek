<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Formulario Stock de producto:</h2>
	<jsp:useBean id="stock" scope="request" class="org.tiposDeClases.Stock" />

	<form
		action="${pageContext.request.contextPath}/admin/controladorStocksAdmin"
		method="post">
		<fieldset>
			<label for="id_producto">Id del producto: </label> <input
				id="id_producto" name="id_producto" value="${stock.producto.ID}"
				readonly="readonly" />
		</fieldset>
		<fieldset>
			<label for="nombre_producto">Nombre del producto: </label> <input
				id="nombre_producto" name="nombre_producto"
				value="${stock.producto.nombre}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="stock">Stock: </label> <input id="stock" name="stock"
				value="${stock.stock}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>

			<c:if test="${param.op!=null and param.op!='' }">
				<input type="submit" value="${fn:toUpperCase(param.op)}" />
			</c:if>
			<c:if test="${param.op==null or param.op=='' }">
				<input type="submit" value="${fn:toUpperCase(op)}" />
			</c:if>
			<input type="submit" name="salir" value="SALIR" />
			<p class="errores">${factura.errores}</p>
			<c:if test="${param.op!=null and param.op!='' }">
				<input type="hidden" name="opcion" value='${param.op}' />
			</c:if>
			<c:if test="${param.op==null or param.op=='' }">
				<input type="hidden" name="opcion" value="modificar" />
			</c:if>
		</fieldset>
	</form>
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
</nav>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>