<%@ include file="includes/cabeceraIndex/cabeceraIndex.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Registro de usuario:</h2>

	<form action="controladorIndex" method="post">
		<fieldset>
			<label for="nickusuario">Nick:</label> <input id="nickusuario" name="nickusuario"
				value="${usuario.nickusuario}"/>
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre:</label> <input id="nombre" name="nombre"
				value="${usuario.nombre}"/>
		</fieldset>
		<fieldset>
			<label for="apellido1">1er Apellido:</label> <input id="apellido1" name="apellido1"
				value="${usuario.apellido1}"/>
		</fieldset>
		<fieldset>
			<label for="apellido2">2º Apellido:</label> <input id="apellido2" name="apellido2"
				value="${usuario.nickusuario}"/>
		</fieldset>
		<fieldset>
			<label for="contrasenia1">Contraseña:</label> <input id="contrasenia1" name="contrasenia1"/>
		</fieldset>
		<fieldset>
			<label for="contrasenia2">Repita Contraseña:</label> <input id="contrasenia2" name="contrasenia2"/>
		</fieldset>
		<fieldset>
			<input type="submit" name="op" value="REGISTRAR"/>
			<input type="submit" name="op" value="SALIR" />
			<p class="errores">${usuario.errores}</p>
		</fieldset>
	</form>
</nav>
<%@ include file="includes/cabeceraIndex/pieIndex.jsp"%>