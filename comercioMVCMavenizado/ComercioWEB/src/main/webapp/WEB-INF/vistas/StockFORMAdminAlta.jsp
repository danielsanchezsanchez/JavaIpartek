<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Formulario Stock de producto:</h2>
				<jsp:useBean id="producto" scope="request"
					class="org.tiposDeClases.Producto" />

				<form
					action="${pageContext.request.contextPath}/admin/controladorStocksAdmin"
					method="post">
					<fieldset class="form-group">
						<input type="hidden" name="id_producto" value="${producto.ID}" />
					</fieldset>
					<fieldset class="form-group">
						<label for="nombre_producto">Nombre del producto: </label> <input
							id="nombre_producto" name="nombre_producto" class="form-control"
							placeholder="Nombre del producto" value="${producto.nombre}"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="stock">Stock: </label> <input id="stock" name="stock"
							value="0" class="form-control" type="number"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<c:if test="${param.op!=null and param.op!='' }">
							<input type="submit" value="${fn:toUpperCase(param.op)}"
								class="btn btn-warning btn-lg btn-block" />
						</c:if>
						<c:if test="${param.op==null or param.op=='' }">
							<input type="submit" value="${fn:toUpperCase(op)}"
								class="btn btn-warning btn-lg btn-block" />
						</c:if>
						<input type="submit" name="salir" value="SALIR"
							class="btn btn-lg btn-success" />
						<c:if test="${factura.errores ne null}">
							<fieldset class="form-group">
								<div class="alert alert-danger alert-dismissible" role="alert">${factura.errores}</div>
							</fieldset>
						</c:if>
						<input type="hidden" name="opcion" value="Aceptar" /> <input
							type="hidden" name="op" value="alta" />
					</fieldset>
				</form>
				<div>${errores}</div>
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