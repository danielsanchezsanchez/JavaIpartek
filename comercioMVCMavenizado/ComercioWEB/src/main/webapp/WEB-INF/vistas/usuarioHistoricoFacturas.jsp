<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Listado de Facturas:</h2>

				<table class="table table-bordered">
					<tr>
						<td><input type="text" readonly="readonly"
							value="NUMERO DE FACTURA"> <input type="text"
							readonly="readonly" value=${fn:toUpperCase("Fecha")}> <input
							type="text" readonly="readonly" value=${fn:toUpperCase("Hora")}>
						</td>
					</tr>
					<c:forEach items="${requestScope.facturaHistoricos}" var="factura">
						<tr>
							<td>
								<form action="controladorMenuUsuarios" method="post">
									<input type="hidden" name="id_factura" value="${factura.id}" />
									<input type="text" name="numero_factura"
										value="${factura.numero_factura}" readonly="readonly" /> <input
										type="hidden" name="id_usuario" value="${factura.id_usuario}" />
									<input type="date" name="fecha" value="${factura.dia}"
										readonly="readonly" /> <input type="time" name="fecha"
										value="${factura.hora}" readonly="readonly" /> <input
										type="submit" name="op" value="Ver factura" class="btn btn-info btn-sm"/>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		<div>
			<a class="btn btn-lg btn-primary" style="margin-bottom: 10px;"
				href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios">
				Volver al menu de cliente</a>
		</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>