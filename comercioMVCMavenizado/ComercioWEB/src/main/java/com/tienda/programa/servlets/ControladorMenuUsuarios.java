package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tiposDAOBaseDeDatos.DAOProductoddbb;
import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbb;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Articulos;
import org.tiposDeClases.Producto;

public class ControladorMenuUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_MENU_USUARIO = "/WEB-INF/vistas/usuarioMenu.jsp";
	static final String RUTA_MENU_USUARIO_CARRITO = "/WEB-INF/vistas/usuarioCarrito.jsp";
	static final String RUTA_INDEX = "/WEB-INF/vistas/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Controlamos las opciones
		String op = request.getParameter("op");
		int opCompra = Integer.parseInt(request.getParameter("opCompra"));

		// Obtenemos el id del usuario conectado
		String nick = request.getParameter("nickusuario");
		DAOUsuarioddbb usuarioDB = new DAOUsuarioddbbMySQL();
		usuarioDB.abrirComercioddbb();
		usuarioDB.buscarElId(nick);
		usuarioDB.cerrarComercioddbb();

		DAOProductoddbb productoDB = new DAOProductoddbbMySQL();
		Articulos articulos;

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
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;
			case "seguirComprando":

				// Si ha comprado algo lo guardamos AQUI
				if (opCompra != 0) {
					// articulos = articulos + new Articulos(nick, opCompra);
				}

				// Busco y relleno los productos
				productoDB.abrirComercioddbb();
				Producto[] productosSeguir;
				productosSeguir = productoDB.buscarTodos();
				productoDB.cerrarComercioddbb();

				// Mando los productos
				request.setAttribute("productos", productosSeguir);
				request.getRequestDispatcher(RUTA_MENU_USUARIO_CARRITO).forward(request, response);
				return;
			}

		}
	}
}
