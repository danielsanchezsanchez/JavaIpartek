<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Formulario de usuarios</h2>

				<jsp:useBean id="usuario" scope="request"
					class="org.tiposDeClases.Usuario" />

				<form
					action="${pageContext.request.contextPath}/admin/controladorUsuariosAdmin"
					method="post">
					<input type="hidden" name="id" value="${usuario.id}" />
					<fieldset class="form-group">
						<label for="nickusuario">Nick del usuario:</label> <input
							id="nickusuario" name="nickusuario"
							value="${usuario.nickusuario}" class="form-control"
							placeholder="Nick usuario"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="nombre">Nombre:</label> <input id="nombre"
							name="nombre" value="${usuario.nombre}" class="form-control"
							placeholder="Nombre"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="apellido1">Primer apellido:</label> <input
							id="apellido1" name="apellido1" value="${usuario.apellido1}"
							class="form-control" placeholder="Apellido"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="apellido2">Segundo apellido:</label> <input
							id="apellido2" name="apellido2" value="${usuario.apellido2}"
							class="form-control" placeholder="Apellido 2"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<c:if test="${param.op=='alta' }">
						<fieldset class="form-group">
							<label for="contrasenia1">Contraseña :</label> <input
								type="password" id="contrasenia1" name="contrasenia1"
								class="form-control" placeholder="Contrasenia" />
						</fieldset>
						<fieldset class="form-group">
							<label for="contrasenia2">Repetir contraseña :</label> <input
								type="password" id="contrasenia2" name="contrasenia2"
								class="form-control" placeholder="Repetir contrasenia" />
						</fieldset>
					</c:if>
					<fieldset class="form-group">
						<div class="row">
							<nav class="col-xs-10 col-xs-offset-1">
								<label for="radmin">Admin: </label><input type="radio"
									id="radmin" name="id_rol" value="1" />
							</nav>
						</div>
						<div class="row">
							<nav class="col-xs-10 col-xs-offset-1">
								<label for="rnormal">Normal: </label><input checked="checked"
									type="radio" id="rnormal" name="id_rol" value="2" />
							</nav>
						</div>
					</fieldset>
					<fieldset class="form-group">

						<input type="submit" value="${fn:toUpperCase(param.op)}"
							class="btn btn-warning btn-lg btn-block"
							<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
						<input type="submit" name="salir" value="SALIR"
							class="btn btn-lg btn-success" />
						<c:if test="${usuario.errores ne null}">
							<fieldset class="form-group">
								<div class="alert alert-danger alert-dismissible" role="alert">${usuario.errores}</div>
							</fieldset>
						</c:if>
						<input type="hidden" name="opcion" value="${param.op}" />
					</fieldset>
				</form>
				<c:if test="${param.op == 'borrar'}">
					<script>
						document.forms[0].onsubmit = confirmarBorrado;
					</script>
				</c:if>
			</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>