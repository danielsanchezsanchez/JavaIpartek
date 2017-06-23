package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tiposDAL.CarritoDAL;
import org.tiposDAL.CarritoDALFactory;
import org.tiposDAOBaseDeDatos.DAOFacturaddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOProductoddbb;
import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbb;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Articulo;
import org.tiposDeClases.Producto;

public class ControladorMenuUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_MENU_USUARIO = "/WEB-INF/vistas/usuarioMenu.jsp";
	static final String RUTA_MENU_USUARIO_COMPRANDO = "/WEB-INF/vistas/usuarioComprando.jsp";
	static final String RUTA_MENU_USUARIO_CARRITO = "/WEB-INF/vistas/usuarioCarrito.jsp";
	static final String RUTA_INDEX = "/WEB-INF/vistas/index.jsp";
	static final String RUTA_SEGUIR_COMPRANDO = "controladorMenuUsuarios?op=seguirComprando";
	static final String RUTA_COMENZAR_NUEVA_COMPRA = "controladorMenuUsuarios?op=comenzarAComprar";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Controlamos las opciones
		String op = request.getParameter("op");

		// Obtenemos el id del usuario conectado
		String nick = request.getParameter("nickusuario");
		DAOUsuarioddbb usuarioDB = new DAOUsuarioddbbMySQL();
		usuarioDB.abrirComercioddbb();
		int id_usuario = usuarioDB.buscarElId(nick);
		usuarioDB.cerrarComercioddbb();

		DAOProductoddbb productoDB = new DAOProductoddbbMySQL();
		Articulo articulo;

		HttpSession sesion = request.getSession();

		// Primera vez que accede
		if (op == null) {
			request.getRequestDispatcher(RUTA_MENU_USUARIO).forward(request, response);
			return;
		}

		if (op != null) {

			CarritoDAL carrito;

			switch (op) {
			case "desconectar":
				sesion.invalidate();
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;

			case "comenzarAComprar":

				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				if (carrito == null || carrito.esCarritoVacio()) {

					// Busco y relleno los productos
					productoDB.abrirComercioddbb();
					Producto[] productos;
					productos = productoDB.buscarTodos();
					productoDB.cerrarComercioddbb();

					// Mando los productos
					request.setAttribute("productos", productos);
					request.getRequestDispatcher(RUTA_MENU_USUARIO_COMPRANDO).forward(request, response);

				} else {
					response.sendRedirect(request.getContextPath() + RUTA_SEGUIR_COMPRANDO);

				}

				return;

			case "seguirComprando":
				String ruta = request.getParameter("ruta");

				// Si estoy viendo los productos no guardo nada
				if (ruta != null) {

				} else {
					if (request.getParameter("id") == null) {

					} else {
						// Si ha comprado algo lo guardamos AQUI
						int id_producto = Integer.parseInt(request.getParameter("id"));
						int cantidad = Integer.parseInt(request.getParameter("cantidad"));
						articulo = new Articulo(id_producto, cantidad);
						carrito = (CarritoDAL) sesion.getAttribute("carrito");

						// Compruebo si ya tenia este articulo
						if (carrito.buscarUnArticuloPorIdProducto(id_producto) == null) {

							// Si no lo tiene, añado el articulo al carrito
							// En la session ofc
							carrito = (CarritoDAL) sesion.getAttribute("carrito");
							carrito.aniadir(articulo);
							sesion.setAttribute("carrito", carrito);
						} else {
							// Si ya lo tiene lo modifico
							carrito = (CarritoDAL) sesion.getAttribute("carrito");
							articulo.setCantidad(cantidad + carrito.buscarUnArticuloPorIdProducto(id_producto).getCantidad());
							carrito.modificar(articulo);
							sesion.setAttribute("carrito", carrito);
						}

						// Mostrar el contenido del carrito
						// for (Articulo articulo2 :
						// carrito.buscarTodosLosArticulos())
						// System.out.println(articulo2);
					}
				}

				// Busco y relleno los productos
				productoDB.abrirComercioddbb();
				Producto[] productosSeguir;
				productosSeguir = productoDB.buscarTodos();
				productoDB.cerrarComercioddbb();

				// Mando los productos
				request.setAttribute("productos", productosSeguir);
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
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				articulo = new Articulo(id_producto, cantidad);

				// Modifico el articulo en el carrito
				// En la session ofc
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				carrito.modificar(articulo);
				sesion.setAttribute("carrito", carrito);

				// Mostrar el contenido del carrito
				for (Articulo articulo2 : carrito.buscarTodosLosArticulos())
					System.out.println(articulo2);

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
				// DAOFactura.
				// Factura factura = new Factura("DENDA" + , id_usuario);

				// Introducimos las lineas de factura-productos

				// Redirigo a comenzar compra tras vaciar el carrito
				carrito = CarritoDALFactory.getCarritoDAL();
				sesion.setAttribute("carrito", carrito);
				response.sendRedirect(request.getContextPath() + RUTA_COMENZAR_NUEVA_COMPRA);
				return;
			}
		}
	}
}
