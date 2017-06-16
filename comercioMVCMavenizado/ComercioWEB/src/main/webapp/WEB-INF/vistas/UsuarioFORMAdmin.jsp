<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Formulario de usuarios</h2>

	<jsp:useBean id="usuario" scope="request" class="org.tipos.Usuario" />

	<form action="controladorusuarios" method="post">
		<fieldset>
			<label for="id">ID</label> <input id="id" name="id"
				required="required" value="${usuario.id}" readonly="readonly" />
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre:</label> <input id="nombre" name="nombre"
				value="${usuario.nombre}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña:</label> <input id="pass" name="pass"
				value="${usuario.pass}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="pass2">Repetir contraseña:</label> <input id="pass2"
				name="pass2" value=""
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="radmin">Admin: </label><input type="radio" id="radmin" name="tipousuario" value="admin" />
			<label for="rnormal">Normal: </label><input checked="checked" type="radio" id="rnormal"name="tipousuario" value="normal" />
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}"
				<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
			<input type="submit" name="salir" value="SALIR" />
			<p class="errores">${usuario.errores}</p>

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