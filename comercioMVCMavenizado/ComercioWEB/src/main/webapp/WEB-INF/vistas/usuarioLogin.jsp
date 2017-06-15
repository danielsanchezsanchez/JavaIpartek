<%@ include file="includes/cabeceraIndex/cabeceraIndex.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav>
	<h2>Logeo de usuario:</h2>

	<form action="controladorIndex" method="post">
		<fieldset>
			<label for="nickusuario">Nick de su usuario:</label> <input id="nickusuario" name="nickusuario"
				value="${usuario.nickusuario}"/>
		</fieldset>
		<fieldset>
			<label for="contrasenia">Contraseña:</label> <input id="contrasenia" name="contrasenia"/>
		</fieldset>
		<fieldset>
			<input type="submit" name="op" value="LOGEAR"/>
			<input type="submit" name="op" value="SALIR" />
			<p class="errores">${usuario.errores}</p>
		</fieldset>
	</form>
</nav>
<%@ include file="includes/cabeceraIndex/pieIndex.jsp"%>