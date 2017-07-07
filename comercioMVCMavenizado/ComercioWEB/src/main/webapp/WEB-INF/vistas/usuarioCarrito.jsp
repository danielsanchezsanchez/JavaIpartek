<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Listado del carrito: productos
					cogidos</h2>

				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="text" readonly="readonly" value="ARTICULO">
								<input type="text" readonly="readonly" value="PRECIO UNIDAD">
								<input type="text" readonly="readonly" value="CANTIDAD">
							</th>
						</tr>
					</thead>
					<c:forEach items="${requestScope.articulos}" var="articulo">
						<tr>
							<td>
								<form action="controladorMenuUsuarios" method="post">
									<input type="hidden" name="id" value="${articulo.producto.ID}" />
									<input type="text" name="nombre"
										value="${articulo.producto.nombre}" /> <input type="text"
										name="nombre" value="${articulo.producto.precio}" /> <input
										type="number" name="cantidad" value="${articulo.cantidad}" />
									<input type="submit" name="op" value="Modificar" class="btn btn-info btn-sm"/> <input
										type="submit" name="op" value="Borrar" class="btn btn-info btn-sm"/>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div>${errores}</div>
			</div>

			<div>
				<a
					onclick="return confirm('¿Comprar? - ¡¡¡¡ESTAS USANDO DINERO REAL!!!! - ');"
					class="btn btn-danger btn-lg btn-block"
					href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=aceptarCompra">
					ACEPTAR COMPRA</a>
			</div>
			<div class="botonera" style="margin-top: 10px;">
				<a class="btn btn-lg btn-primary"
					href="controladorMenuUsuarios?op=seguirComprando&ruta=estabaViendoElCarrito">
					Ver la tienda</a> <a class="btn btn-lg btn-primary"
					href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">
					Volver al menu de cliente</a>
			</div>
		</nav>
	</div>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<a onclick="return confirm('¿Estás seguro de que quieres salir');"
				class="btn btn-lg btn-success" style="margin-top: 10px; margin-bottom: 10px;"
				href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">
				Volver al inicio</a>
		</nav>
	</div>
</section>

<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>