<%@ include file="includes/cabeceraUsuario/cabeceraUsuario.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 style="margin: 20px;">Opciones de CLIENTE: </h2>

	<div class="opcionesAdmin">
		<h2 style="margin: 20px; border: '1';">
		
		<a id="botonIrALaTienda"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=comenzarAComprar">-
		Comenzar una compra -</a> 
		<a id="botonIrALosHistoricosDeCompra"
		href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=verHistoricos">-
		Ver tus compras -</a> 
		
		</h2>
</div>
	<div class="botonera">
				<a onclick="return confirm('¿Estás seguro de que quieres salir');" id="botonSalir" href="${pageContext.request.contextPath}/usuarios/controladorMenuUsuarios?op=desconectar">-
					Volver al inicio -</a> 
			</div>
<%@ include file="includes/cabeceraUsuario/pieUsuario.jsp"%>