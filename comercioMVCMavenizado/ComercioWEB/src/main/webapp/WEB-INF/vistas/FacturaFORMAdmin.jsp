<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Formulario de facturas</h2>

	<jsp:useBean id="factura" scope="request"
		class="org.tiposDeClases.Factura" />

	<form
		action="${pageContext.request.contextPath}/admin/facturaFORMAdmin"
		method="post">
		<fieldset>
			<label for="id">Id de la factura:</label> <input id="id" name="id"
				value="${factura.id}" readonly="readonly" />
		</fieldset>
		<fieldset>
			<label for="numero_factura">Numero de la factura:</label> <input
				id="numero_factura" name="numero_factura"
				value="${factura.numero_factura}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="id_usuario">Usuario :</label> <input id="id_usuario"
				name="id_usuario" value="${factura.id_usuario}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="fecha">Fecha :</label> <input id="fecha" name="fecha"
				value="${factura.fecha}"
				
			  	readonly="readonly"
			   />
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}"
				<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
			<input type="submit" name="salir" value="SALIR" />
			<p class="errores">${factura.errores}</p>

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