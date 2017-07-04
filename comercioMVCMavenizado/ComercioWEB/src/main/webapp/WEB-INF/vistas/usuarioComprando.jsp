<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Listado de productos:</h2>

				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Producto</th>
							<th>Precio</th>
							<th>Descripcion</th>
							<th>Imagen</th>
							<th>Cantidad</th>
							<th>Añadir al carrito</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.articulos}" var="articulo">
							<tr>
								<td>${articulo.producto.ID}</td>
								<td>${articulo.producto.nombre}</td>
								<td>${articulo.producto.precio}&euro;</td>
								<td>${articulo.producto.descripcion}</td>
								<td>Imagen en proceso</td>
								<td>Cantidad: <input min="0" max="100" style="width: 3em"
									type="number" value='${articulo.cantidad}' name="cantidad"></td>
								<td><a class="btn btn-info btn-sm"
									href="controladorMenuUsuarios?op=seguirComprando&id=${articulo.producto.ID}&cantidad="
									role="button">Añadir al carrito</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>${errores}</div>

				<a class="btn btn-lg btn-primary"
					href="controladorMenuUsuarios?op=verCarrito" role="button">Ver
					el carrito</a> <a class="btn btn-lg btn-primary"
					href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios"
					role="button">Volver al menu de cliente</a>

			</div>
			<a class="btn btn-lg btn-success"
				onclick="return confirm('¿Estás seguro de que quieres salir');"
				href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar"
				role="button">Volver al inicio</a>
		</nav>
	</div>
	<script>
		cantidator();
	</script>

</section>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>