<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Formulario Stock de producto:</h2>
				<jsp:useBean id="stock" scope="request"
					class="org.tiposDeClases.Stock" />

				<form
					action="${pageContext.request.contextPath}/admin/controladorStocksAdmin"
					method="post">
					<fieldset class="form-group">
						<label for="id_producto">Id del producto: </label> <input
							id="id_producto" name="id_producto" value="${stock.producto.ID}"
							readonly="readonly" />
					</fieldset>
					<fieldset class="form-group">
						<label for="nombre_producto">Nombre del producto: </label> <input
							id="nombre_producto" name="nombre_producto" class="form-control"
							placeholder="Nombre del producto"
							value="${stock.producto.nombre}" readonly="readonly" />
					</fieldset>
					<fieldset class="form-group">
						<label for="stock">Stock: </label> <input id="stock" name="stock"
							value="${stock.stock}"
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
						<p class="errores">${factura.errores}</p>
						<c:if test="${param.op!=null and param.op!='' }">
							<input type="hidden" name="opcion" value='${param.op}' />
						</c:if>
						<c:if test="${param.op==null or param.op=='' }">
							<input type="hidden" name="opcion" value="modificar" />
						</c:if>
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