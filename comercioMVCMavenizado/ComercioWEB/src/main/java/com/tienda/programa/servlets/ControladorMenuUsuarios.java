package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tiposDAL.CarritoDAL;
import org.tiposDAL.CarritoDALFactory;
import org.tiposDAOBaseDeDatos.DAOFactura_Productoddbb;
import org.tiposDAOBaseDeDatos.DAOFactura_ProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOFacturaddbb;
import org.tiposDAOBaseDeDatos.DAOFacturaddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOProductoddbb;
import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOStockddbb;
import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbb;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Articulo;
import org.tiposDeClases.Factura;
import org.tiposDeClases.Producto;
import org.tiposDeClases.Stock;
import org.tiposDeClases.Usuario;

public class ControladorMenuUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_MENU_USUARIO = "/WEB-INF/vistas/usuarioMenu.jsp";
	static final String RUTA_MENU_USUARIO_COMPRANDO = "/WEB-INF/vistas/usuarioComprando.jsp";
	static final String RUTA_MENU_USUARIO_CARRITO = "/WEB-INF/vistas/usuarioCarrito.jsp";
	static final String RUTA_MENU_USUARIO_HISTORICO = "/WEB-INF/vistas/usuarioHistoricoFacturas.jsp";
	static final String RUTA_MENU_FACTURAS_PRODUCTOS = "/WEB-INF/vistas/usuarioFacturasProductos.jsp";
	static final String RUTA_INDEX = "/WEB-INF/vistas/index.jsp";
	static final String RUTA_SEGUIR_COMPRANDO = "controladorMenuUsuarios?op=seguirComprando";
	static final String RUTA_COMENZAR_NUEVA_COMPRA = "controladorMenuUsuarios?op=comenzarAComprar";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Abrimos sesion
		HttpSession sesion = request.getSession();

		// Controlamos las opciones
		String op = request.getParameter("op");

		// Obtenemos el id del usuario conectado
		Usuario nick = (Usuario) sesion.getAttribute("usuario");
		String nickusuario = nick.getNickusuario();
		DAOUsuarioddbb usuarioDB = new DAOUsuarioddbbMySQL();
		usuarioDB.abrirComercioddbb();
		int id_usuario = usuarioDB.buscarElId(nickusuario);
		usuarioDB.cerrarComercioddbb();

		DAOProductoddbb productoDB = new DAOProductoddbbMySQL();
		Articulo articulo;

		// Primera vez que accede
		if (op == null) {
			request.getRequestDispatcher(RUTA_MENU_USUARIO).forward(request, response);
			return;
		}

		if (op != null) {

			CarritoDAL carrito;
			int cantidad;

			switch (op) {
			case "desconectar":
				if (sesion != null) {
					sesion.invalidate();
					request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
					return;
				}

				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;

			case "comenzarAComprar":
				cantidad = 0;
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				if (carrito == null || carrito.esCarritoVacio()) {

					// Busco y relleno los productos
					productoDB.abrirComercioddbb();
					Producto[] productosComenzar;
					productosComenzar = productoDB.buscarTodosLosEnTienda();
					productoDB.cerrarComercioddbb();

					// Metemos los productos en un array de articulos para poder
					// mostrar la cantidad
					Articulo[] articulosSeguir = new Articulo[productosComenzar.length];
					for (int i = 0; i < productosComenzar.length; i++) {
						Articulo articuloPorProducto = new Articulo();
						articuloPorProducto.setProducto(productosComenzar[i]);
						articuloPorProducto.setCantidad(cantidad);

						articulosSeguir[i] = articuloPorProducto;
					}
					articulosSeguir.toString();

					// Mando los productos
					request.setAttribute("articulos", articulosSeguir);
					request.getRequestDispatcher(RUTA_MENU_USUARIO_COMPRANDO).forward(request, response);

				} else {
					response.sendRedirect(request.getContextPath() + RUTA_SEGUIR_COMPRANDO);

				}

				return;

			case "seguirComprando":
				DAOStockddbb stockProducto = new DAOStockddbbMySQL();
				String ruta = request.getParameter("ruta");

				// Si estoy viendo los productos no guardo nada
				if (ruta != null) {

				} else {

					if (request.getParameter("id") == null) {

					} else {
						cantidad = Integer.parseInt(request.getParameter("cantidad"));

						// Si ha comprado algo lo guardamos AQUI
						int id_producto = Integer.parseInt(request.getParameter("id"));
						articulo = new Articulo(id_producto, cantidad);
						carrito = (CarritoDAL) sesion.getAttribute("carrito");

						// Comprobamos el stock
						stockProducto.abrirComercioddbb();
						Stock stock = stockProducto.buscarStockPorProducto(id_producto);
						stockProducto.cerrarComercioddbb();

						if (stock.getStock() < cantidad) {

							// Error de no suficiente STOCK
							request.setAttribute("errores", "¡¡¡ATENCION!!!: Error en la cantidad, menos de la especificada.");
							carrito = (CarritoDAL) sesion.getAttribute("carrito");
							carrito.borrar(articulo);
							sesion.setAttribute("carrito", carrito);

						} else {
							// Compruebo si ya tenia este articulo
							if (carrito.buscarUnArticuloPorIdProducto(id_producto) == null) {

								// Si no lo tiene, añado el articulo al carrito
								// En la session ofc, excepto que la cantidad
								// sea
								// cero.
								if (cantidad == 0) {

								} else {
									carrito = (CarritoDAL) sesion.getAttribute("carrito");
									carrito.aniadir(articulo);
									sesion.setAttribute("carrito", carrito);
								}
							} else {
								// Si ya lo tiene lo modifico
								carrito = (CarritoDAL) sesion.getAttribute("carrito");
								articulo.setCantidad(cantidad + carrito.buscarUnArticuloPorIdProducto(id_producto).getCantidad());
								carrito.modificar(articulo);
								sesion.setAttribute("carrito", carrito);
							}
						}
					}
				}

				// Busco y relleno los productos en un array de articulos
				productoDB.abrirComercioddbb();
				Producto[] productosSeguir;
				productosSeguir = productoDB.buscarTodosLosEnTienda();
				productoDB.cerrarComercioddbb();

				// Metemos los productos en un array de articulos para poder
				// mostrar la cantidad
				Articulo[] articulosSeguir = new Articulo[productosSeguir.length];
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				int cantidadProducto;
				for (int i = 0; i < productosSeguir.length; i++) {
					Articulo articuloPorProducto = new Articulo();
					articuloPorProducto.setProducto(productosSeguir[i]);
					// Añadimos la cantidad de cada carrito
					if (carrito.buscarUnArticuloPorIdProducto(articuloPorProducto.getProducto().getID()) != null) {
						cantidadProducto = carrito.buscarUnArticuloPorIdProducto(articuloPorProducto.getProducto().getID()).getCantidad();
						articuloPorProducto.setCantidad(cantidadProducto);

						// Si el producto no esta en el carrito cantidad=0
					} else {
						articuloPorProducto.setCantidad(0);
					}

					articulosSeguir[i] = articuloPorProducto;
				}

				// Mando los productos
				request.setAttribute("articulos", articulosSeguir);
				request.getRequestDispatcher(RUTA_MENU_USUARIO_COMPRANDO).forward(request, response);
				return;
			case "verCarrito":

				// Mando el carrito
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				request.setAttribute("articulos", carrito.buscarTodosLosArticulos());
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;

			case "Modificar":
				// Si ha modificado algo lo guardamos AQUI
				int id_producto = Integer.parseInt(request.getParameter("id"));
				cantidad = Integer.parseInt(request.getParameter("cantidad"));

				articulo = new Articulo(id_producto, cantidad);

				// Modifico el articulo en el carrito
				// En la session ofc
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				carrito.modificar(articulo);
				sesion.setAttribute("carrito", carrito);

				// Mando el carrito
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				request.setAttribute("articulos", carrito.buscarTodosLosArticulos());
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;

			case "Borrar":

				// Si quiere borrar un articulo cojemos el id del articulo
				// (producto)
				int id_productoBorrar = Integer.parseInt(request.getParameter("id"));
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				articulo = carrito.buscarUnArticuloPorIdProducto(id_productoBorrar);
				// Borramos
				carrito.borrar(articulo);

				// Mando el carrito
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				request.setAttribute("articulos", carrito.buscarTodosLosArticulos());
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;

			case "aceptarCompra":
				// Cargamos el carrito comprado
				carrito = (CarritoDAL) sesion.getAttribute("carrito");

				// Creamos la factura
				DAOFacturaddbbMySQL DAOFactura = new DAOFacturaddbbMySQL();
				DAOFactura.abrirComercioddbb();

				// Buscamos la ultima factura
				String DENDA = DAOFactura.buscarUltima();
				String DENDASSTRG = DENDA.substring(5);
				int DENDANUMERO = Integer.parseInt(DENDASSTRG) + 1;
				DENDASSTRG = String.valueOf(DENDANUMERO);

				// Le damos formato "DENDA-------1 ... etc"
				int NUMEROCEROS = 8 - DENDASSTRG.length();
				for (int i = NUMEROCEROS; i > 0; i--) {
					DENDASSTRG = "0" + DENDASSTRG;
				}
				DENDASSTRG = "DENDA" + DENDASSTRG;
				System.out.println(DENDASSTRG);

				// Abrimos la transaccion
				DAOFactura.iniciarTransaccion();

				try {
					// Creamos la factura
					Factura factura = new Factura(DENDASSTRG, id_usuario);
					int claveFacturaGenerada;

					claveFacturaGenerada = DAOFactura.insert(factura);

					// Introducimos las lineas de factura-productos
					DAOFactura_ProductoddbbMySQL DAOFac_Pro = new DAOFactura_ProductoddbbMySQL();
					Articulo articuloFacPro;
					DAOFac_Pro.reutilizarConexion(DAOFactura);

					for (Articulo articulosParaFacturasProductos : carrito.buscarTodosLosArticulos()) {
						articuloFacPro = new Articulo(claveFacturaGenerada, articulosParaFacturasProductos.getId_producto(), articulosParaFacturasProductos.getCantidad());
						DAOFac_Pro.insert(articuloFacPro);

						// Restamos stock
						DAOStockddbb DAOStock = new DAOStockddbbMySQL();
						DAOStock.reutilizarConexion(DAOFactura);

						int cantidadNegativa = (DAOStock.buscarStockPorProducto(articuloFacPro.getId_producto())).getStock() - articuloFacPro.getCantidad();

						Stock stock = new Stock(articuloFacPro.getId_producto(), cantidadNegativa);
						DAOStock.update(stock);
					}

					// Cerramos la transaccion (TRY)
					DAOFactura.confirmarTransaccion();
				} catch (Exception e) {
					// Rollback (CATCH)
					DAOFactura.deshacerTransaccion();
				} finally {

					// Cerramos las ddbb
					DAOFactura.cerrarComercioddbb();
				}

				// Redirigo a comenzar compra tras vaciar el carrito
				carrito = CarritoDALFactory.getCarritoDAL();
				sesion.setAttribute("carrito", carrito);
				request.getRequestDispatcher(RUTA_MENU_USUARIO).forward(request, response);
				return;

			case "verHistoricos":

				// Cargo todas las facturas de un usuario
				DAOFacturaddbb DAOFacturaHistorica = new DAOFacturaddbbMySQL();
				DAOFacturaHistorica.abrirComercioddbb();

				// Cargamos las facturas del usuario logeado para enviarselas al
				// jsp
				Factura[] facturaHistoricos;
				facturaHistoricos = DAOFacturaHistorica.buscarTodasPorUsuario(id_usuario);
				request.setAttribute("facturaHistoricos", facturaHistoricos);

				// Cerramos la ddbb
				DAOFacturaHistorica.cerrarComercioddbb();

				request.getRequestDispatcher(RUTA_MENU_USUARIO_HISTORICO).forward(request, response);
				return;

			case "Ver factura":
				// Recojemos el id_factura
				int id_factura = Integer.parseInt(request.getParameter("id_factura"));

				// Recojemos las filas de facturas-productos con ese id_factura
				// Cargo todas las facturas de un usuario
				DAOFactura_Productoddbb DAOFac_ProdFila = new DAOFactura_ProductoddbbMySQL();
				DAOFac_ProdFila.abrirComercioddbb();

				// Cargamos las facturas del usuario logeado para enviarselas al
				// jsp
				Articulo[] articuloHistoricos;
				articuloHistoricos = DAOFac_ProdFila.buscarTodasPorFactura(id_factura);
				request.setAttribute("articulos_Factura", articuloHistoricos);

				// Cerramos la ddbb
				DAOFac_ProdFila.cerrarComercioddbb();
				request.getRequestDispatcher(RUTA_MENU_FACTURAS_PRODUCTOS).forward(request, response);
				return;
			}
		}
	}
}
