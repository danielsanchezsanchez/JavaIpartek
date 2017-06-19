<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Formulario de productos</h2>

	<jsp:useBean id="producto" scope="request"
		class="org.tiposDeClases.Producto" />

	<form
		action="${pageContext.request.contextPath}/admin/productoFORMAdmin"
		method="post">
		<fieldset>
			<label for="nombre">Nombre del producto:</label> <input
				id="nombre" name="nombre" value="${producto.nombre}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="precio">Precio:</label> <input id="precio" name="precio"
				value="${producto.precio}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="descripcion">Descripcion :</label> <input id="descripcion"
				name="descripcion" value="${producto.descripcion}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="url_producto_img">URL de la imagen :</label> <input
				id="url_producto_img" name="url_producto_img" value="${producto.url_producto_img}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}"
				<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
			<input type="submit" name="salir" value="SALIR" />
			<p class="errores">${producto.errores}</p>

			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
</nav>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>