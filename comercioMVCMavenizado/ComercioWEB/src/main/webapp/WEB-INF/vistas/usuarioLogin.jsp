<%@ include file="includes/cabeceraIndex/cabeceraIndex.jsp"%>

<div class="row">
	<nav class="col-xs-10 col-xs-offset-1">
		<h2>Logeo de usuario:</h2>

		<form action="controladorIndex" method="post">
			<fieldset class="form-group">
				<label for="nickusuario">Nick de su usuario:</label> <input
					placeholder="nick del usuario" id="nickusuario" name="nickusuario"
					class="form-control" value="${usuario.nickusuario}" />
			</fieldset>
			<fieldset class="form-group">
				<label for="contrasenia">Contraseña:</label> <input
					placeholder="Contrasenia" type="password" id="contrasenia"
					name="contrasenia" class="form-control" />
			</fieldset>
			<fieldset class="form-group">
				<input type="submit" name="op" value="LOGEAR"
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