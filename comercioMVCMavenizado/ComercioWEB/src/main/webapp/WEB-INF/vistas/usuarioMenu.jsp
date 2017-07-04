<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Opciones de CLIENTE:</h2>
				<p>En esta parte del programa web, usted como usuario podra
					comprobar sus transacciones y realizar nuevas.</p>
				<div class="btn-group" role="group">

					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=comenzarAComprar"
						role="button">Comenzar una compra</a> <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=verHistoricos"
						role="button">Ver tus compras</a>

				</div>
			</div>
			<div class="jumbotron">
				<a class="btn btn-lg btn-primary"
					onclick="return confirm('¿Estás seguro de que quieres salir');"
					href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar"
					role="button">Volver al inicio</a>
			</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>