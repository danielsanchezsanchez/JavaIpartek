<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Formulario de usuarios</h2>

	<jsp:useBean id="usuario" scope="request"
		class="org.tiposDeClases.Usuario" />

	<form
		action="${pageContext.request.contextPath}/admin/usuarioFORMAdmin"
		method="post">
		<fieldset>
			<label for="nickusuario">Nick del usuario:</label> <input
				id="nickusuario" name="nickusuario" value="${usuario.nickusuario}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre:</label> <input id="nombre" name="nombre"
				value="${usuario.nombre}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="apellido1">Primer apellido:</label> <input id="apellido1"
				name="apellido1" value="${usuario.apellido1}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<fieldset>
			<label for="apellido2">Segundo apellido:</label> <input
				id="apellido2" name="apellido2" value="${usuario.apellido2}"
				<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
		</fieldset>
		<c:if test="${param.op=='alta' }">
			<fieldset>
				<label for="contrasenia1">Contraseña :</label> <input type="password" id="contrasenia1"
					name="contrasenia1"  />
			</fieldset>
			<fieldset>
				<label for="contrasenia2">Repetir contraseña :</label> <input type="password" id="contrasenia2"
					name="contrasenia2" />
			</fieldset>
		</c:if>
		<fieldset>
			<label for="radmin">Admin: </label><input type="radio" id="radmin"
				name="id_rol" value="1" /> <label for="rnormal">Normal: </label><input
				checked="checked" type="radio" id="rnormal" name="id_rol" value="2" />
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