package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tiposDAL.CarritoDAL;
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

			switch (op) {
			case "desconectar":
				sesion.invalidate();
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;

			case "comenzarAComprar":

				// Busco y relleno los productos
				productoDB.abrirComercioddbb();
				Producto[] productos;
				productos = productoDB.buscarTodos();
				productoDB.cerrarComercioddbb();

				// Mando los productos
				request.setAttribute("productos", productos);
				request.getRequestDispatcher(RUTA_MENU_USUARIO_COMPRANDO).forward(request, response);
				return;
			case "seguirComprando":
				String ruta = request.getParameter("ruta");

				// Si estoy viendo los productos no guardo nada
				if (ruta != null) {

				} else {
					// Si ha comprado algo lo guardamos AQUI
					int id_producto = Integer.parseInt(request.getParameter("id"));
					int cantidad = Integer.parseInt(request.getParameter("cantidad"));
					articulo = new Articulo(id_producto, cantidad);

					// Añado el articulo al carrito
					// En la session ofc
					CarritoDAL carrito;
					carrito = (CarritoDAL) sesion.getAttribute("carrito");
					carrito.aniadir(articulo);
					sesion.setAttribute("carrito", carrito);

					// Mostrar el contenido del carrito
					for (Articulo articulo2 : carrito.buscarTodosLosArticulos())
						System.out.println(articulo2);
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
				CarritoDAL carrito;
				carrito = (CarritoDAL) sesion.getAttribute("carrito");
				request.setAttribute("articulos", carrito.buscarTodosLosArticulos());
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;
			}

		}
	}
}
