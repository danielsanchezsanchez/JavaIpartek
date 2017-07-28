<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Formulario de facturas</h2>

				<jsp:useBean id="factura" scope="request"
					class="org.tiposDeClases.Factura" />

				<form
					action="${pageContext.request.contextPath}/admin/facturaFORMAdmin"
					method="post">
					<fieldset class="form-group">
						<label for="id">Id de la factura:</label> <input id="id" name="id"
							value="${factura.id}" readonly="readonly" class="form-control" />
					</fieldset>
					<fieldset class="form-group">
						<label for="numero_factura">Numero de la factura:</label> <input
							id="numero_factura" name="numero_factura" class="form-control"
							value="${factura.numero_factura}"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="id_usuario">Usuario :</label> <input id="id_usuario"
							name="id_usuario" value="${factura.id_usuario}"
							class="form-control"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="fecha">Fecha :</label> <input id="fecha" name="fecha"
							class="form-control" value="${factura.fecha}" readonly="readonly" />
					</fieldset>
					<fieldset class="form-group">
						<input type="submit" value="${fn:toUpperCase(param.op)}"
							class="btn btn-warning btn-lg btn-block"
							<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
						<input type="submit" name="salir" value="SALIR"
							class="btn btn-lg btn-success" />
						<c:if test="${factura.errores ne null}">
							<fieldset class="form-group">
								<div class="alert alert-danger alert-dismissible" role="alert">${factura.errores}</div>
							</fieldset>
						</c:if>
						<input type="hidden" name="opform" value="${param.op}" />
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