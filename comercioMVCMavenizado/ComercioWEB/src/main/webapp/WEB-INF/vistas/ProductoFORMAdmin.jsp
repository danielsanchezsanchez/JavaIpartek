<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">

				<h2>Formulario de productos</h2>

				<jsp:useBean id="producto" scope="request"
					class="org.tiposDeClases.Producto" />

				<form
					action="${pageContext.request.contextPath}/admin/controladorProductosAdmin"
					method="post">
					<input type="hidden" name="id" value="${producto.ID}" />
					<fieldset class="form-group">
						<label for="nombre">Nombre del producto:</label> <input
							id="nombre" name="nombre" value="${producto.nombre}"
							class="form-control" placeholder="Nombre del producto"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="precio">Precio:</label> <input id="precio"
							name="precio" value="${producto.precio == null ? 0 : producto.precio}" class="form-control"
							type="number"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="descripcion">Descripcion :</label> <input
							id="descripcion" name="descripcion"
							value="${producto.descripcion}" class="form-control"
							placeholder="Descripcion del producto"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<label for="url_producto_img">URL de la imagen :</label> <input
							id="url_producto_img" name="url_producto_img"
							value="${producto.url_producto_img}" class="form-control"
							placeholder="URL del producto"
							<c:if test="${param.op == param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
					</fieldset>
					<fieldset class="form-group">
						<input type="submit" value="${fn:toUpperCase(param.op)}"
							class="btn btn-warning btn-lg btn-block"
							<c:if test="${param.op==null or param.op=='' }">
			Style="display:none;"
			</c:if> />
						<input type="submit" name="salir" value="SALIR"
							class="btn btn-lg btn-success" />
						<c:if test="${producto.errores ne null}">
							<fieldset class="form-group">
								<div class="alert alert-danger alert-dismissible" role="alert">${producto.errores}</div>
							</fieldset>
						</c:if>
						<input type="hidden" name="opcion" value="${param.op}" /> <input
							type="hidden" name="op" value="alta" />
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