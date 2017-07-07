<%@ include file="includes/cabeceraAdministradores/cabeceraAdmin.jsp"%>

<section>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="jumbotron">
				<h2>Opciones de administrador:</h2>
				<div class="btn-group" style="margin: 20px;" role="group">
					<a class="btn btn-primary" role="button"
						href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionUsuarios">
						Gestion de usuarios</a> <a class="btn btn-primary" role="button"
						href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeProductos">
						Gestion de productos</a> <a class="btn btn-primary" role="button"
						href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeStock">
						Gestion de Stock</a> <a class="btn btn-primary" role="button"
						href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=gestionDeFacturas">
						Gestion de facturas</a>
				</div>
			</div>
		</nav>
	</div>
	<div class="row">
		<nav class="col-xs-10 col-xs-offset-1">
			<div class="botonera">
				<a onclick="return confirm('¿Estás seguro de que quieres salir');"
					class="btn btn-lg btn-success"
					href="${pageContext.request.contextPath}/admin/controladorMenuAdministradores?op=desconectar">
					Volver al inicio</a>
			</div>
		</nav>
	</div>
</section>
<%@ include file="includes/cabeceraAdministradores/pieAdmin.jsp"%>