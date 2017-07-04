<%@ include file="includes/cabeceraIndex/cabeceraIndex.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<nav class="col-xs-10 col-xs-offset-1">
		<h2>Registro de usuario:</h2>

		<form action="controladorIndex" method="post">
			<fieldset class="form-group">
				<label for="nickusuario">Nick:</label> <input id="nickusuario"
					name="nickusuario" class="form-control" placeholder="Nick usuario"
					value="${usuario.nickusuario}" />
			</fieldset>
			<fieldset class="form-group">
				<label for="nombre">Nombre:</label> <input id="nombre" name="nombre"
					class="form-control" placeholder="Nombre" value="${usuario.nombre}" />
			</fieldset>
			<fieldset class="form-group">
				<label for="apellido1">1er Apellido:</label> <input id="apellido1"
					name="apellido1" class="form-control" placeholder="Apellido"
					value="${usuario.apellido1}" />
			</fieldset>
			<fieldset class="form-group">
				<label for="apellido2">2º Apellido:</label> <input id="apellido2"
					name="apellido2" class="form-control" placeholder="Apellido 2"
					value="${usuario.apellido2}" />
			</fieldset>
			<fieldset class="form-group">
				<label for="contrasenia1">Contraseña:</label> <input type="password"
					id="contrasenia1" name="contrasenia1" class="form-control"
					placeholder="Contrasenia" />
			</fieldset>
			<fieldset class="form-group">
				<label for="contrasenia2">Repita Contraseña:</label> <input
					type="password" id="contrasenia2" name="contrasenia2"
					class="form-control" placeholder="Repetir contrasenia" />
			</fieldset>
			<fieldset class="form-group">
				<input type="submit" name="op" value="REGISTRAR"
					class="btn btn-success" /> <input type="submit" name="op"
					value="SALIR" class="btn btn-success" />
			</fieldset>
			<c:if test="${usuario.errores ne null}">
				<fieldset class="form-group">
					<div class="alert alert-danger alert-dismissible" role="alert">	
						<strong>${usuario.errores}</strong>
					</div>
				</fieldset>
			</c:if>
		</form>
	</nav>
</div>
<%@ include file="includes/cabeceraIndex/pieIndex.jsp"%>