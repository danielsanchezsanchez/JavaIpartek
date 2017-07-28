package com.tienda.programa.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDeClases.Producto;

public class ControladorProductosAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String RUTA_LISTADO_PRODUCTOS = "/WEB-INF/vistas/ProductoCRUD.jsp";
	static final String RUTA_FORMULARIO_PRODUCTOS = "/WEB-INF/vistas/ProductoFORMAdmin.jsp";
	static final String RUTA_CONTROLADOR_PRODUCTOS = "/admin/controladorProductosAdmin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		String op = request.getParameter("op");
		String opcion = request.getParameter("opcion");
		String salir = request.getParameter("salir");

		// Primera vez que pasa por aqui.
		if (op == null && opcion == null) {
			DAOProducto.abrirComercioddbb();
			Producto[] productos = DAOProducto.buscarTodos();
			DAOProducto.cerrarComercioddbb();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(RUTA_LISTADO_PRODUCTOS).forward(request, response);
			return;
		}

		// Si esta saliendo de productoFormAdmin
		if (salir != null) {
			DAOProducto.abrirComercioddbb();
			Producto[] productos = DAOProducto.buscarTodos();
			DAOProducto.cerrarComercioddbb();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(RUTA_LISTADO_PRODUCTOS).forward(request, response);
			return;
		}

		// Cuando escoje una de las opciones
		if (op != null && opcion == null) {

			String nombre = request.getParameter("nombre");

			Producto producto;

			switch (op) {
			case "modificar":
				producto = new Producto();
				DAOProducto.abrirComercioddbb();
				producto = DAOProducto.buscarPorNombre(nombre);
				DAOProducto.cerrarComercioddbb();
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(RUTA_FORMULARIO_PRODUCTOS).forward(request, response);
				return;
			case "borrar":
				producto = new Producto();
				DAOProducto.abrirComercioddbb();
				producto = DAOProducto.buscarPorNombre(nombre);
				DAOProducto.cerrarComercioddbb();
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(RUTA_FORMULARIO_PRODUCTOS).forward(request, response);
				return;
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO_PRODUCTOS).forward(request, response);
				return;

			}
		}
		if (opcion != null && op != null) {
			BigDecimal precio2;
			if (request.getParameter("precio") == null || request.getParameter("precio").length() == 0) {
				precio2 = new BigDecimal("0.0");
			} else {
				precio2 = new BigDecimal(request.getParameter("precio"));
			}
			Producto producto = new Producto(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"), precio2, request.getParameter("descripcion"), request.getParameter("url_producto_img"));

			switch (opcion) {
			case "modificar":
				DAOProducto.abrirComercioddbb();
				DAOProducto.update(producto);
				DAOProducto.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_PRODUCTOS);
				return;

			case "borrar":
				DAOProducto.abrirComercioddbb();
				DAOProducto.delete(producto);
				DAOProducto.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_PRODUCTOS);
				return;

			case "alta":
				String nombre = request.getParameter("nombre");
				DAOProducto.abrirComercioddbb();
				// Si el nombre ya existe.
				if (DAOProducto.buscarPorNombre(nombre) != null) {
					producto.setErrores("Nombre no disponible.");
					request.setAttribute("producto", producto);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_PRODUCTOS).forward(request, response);
					return;
				}

				// Longitudes minimas
				if (nombre.length() < 4) {
					producto.setErrores("El nombre debe tener al menos 5 caracteres.");
					request.setAttribute("producto", producto);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_PRODUCTOS).forward(request, response);
					return;
				}

				DAOProducto.insert(producto);
				DAOProducto.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_PRODUCTOS);
				return;
			}
		}
	}

}
