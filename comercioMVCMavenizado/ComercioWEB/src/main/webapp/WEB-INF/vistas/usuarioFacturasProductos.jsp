<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">

				<h2>Listado de Articulos por factura:</h2>

				<table class="table table-bordered">
					<tr>
						<td><input type="text" readonly="readonly"
							value=${fn:toUpperCase("ID Factura")}> <input type="text"
							readonly="readonly" value=${fn:toUpperCase("ID Producto")}>
							<input type="text" readonly="readonly"
							value=${fn:toUpperCase("Cantidad")}></td>
					</tr>
					<c:forEach items="${requestScope.articulos_Factura}" var="articulo">
						<tr>
							<td>
								<form action="controladorMenuUsuarios" method="post">
									<input type="number" name="id_factura"
										value="${articulo.id_factura}" readonly="readonly" /> <input
										type="number" name="id_producto"
										value="${articulo.id_producto}" readonly="readonly" /> <input
										type="number" name="cantidad" value="${articulo.cantidad}"
										readonly="readonly" /> <input type="submit" name="op" class="btn btn-info btn-sm"
										value="Modificar" /> <input type="submit" name="op" class="btn btn-info btn-sm"
										value="Borrar" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div>
				<a class="btn btn-lg btn-info"
					href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=verHistoricos">
					Volver al menu de listado de facturas</a>
			</div>
		</nav>
	</div>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<a style="margin-top: 10px; margin-bottom: 10px;" class="btn btn-lg btn-primary"
				href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">
				Volver al menu de cliente</a>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>