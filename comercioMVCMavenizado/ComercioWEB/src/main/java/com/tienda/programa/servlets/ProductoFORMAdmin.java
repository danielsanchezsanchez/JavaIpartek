package com.tienda.programa.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDeClases.Producto;

public class ProductoFORMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_PRODUCTOS = "/WEB-INF/vistas/ProductoCRUD.jsp";
	static final String RUTA_ADMINISTRADOR_PRODUCTOCRUD = "/admin/productoCRUD";
	static final String RUTA_ADMINISTRADOR_REGISTROPRODUCTO = "/WEB-INF/vistas/ProductoFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("salir");
		String op = request.getParameter("op");

		if (salir != null) {
			request.getRequestDispatcher(RUTA_ADMINISTRADOR_PRODUCTOCRUD).forward(request, response);
			return;
		}

		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		Producto producto = new Producto(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"), new BigDecimal(request.getParameter("precio")), request.getParameter("descripcion"), request.getParameter("url_producto_img"));

		switch (op) {
		case "modificar":
			DAOProducto.abrirComercioddbb();
			DAOProducto.update(producto);
			DAOProducto.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_PRODUCTOCRUD);
			return;

		case "borrar":
			DAOProducto.abrirComercioddbb();
			DAOProducto.delete(producto);
			DAOProducto.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_PRODUCTOCRUD);
			return;

		case "alta":
			String nombre = request.getParameter("nombre");
			DAOProducto.abrirComercioddbb();
			// Si el nombre ya existe.
			if (DAOProducto.buscarPorNombre(nombre) != null) {
				producto.setErrores("Nombre no disponible.");
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROPRODUCTO).forward(request, response);
				return;
			}

			// Longitudes minimas
			if (nombre.length() < 4) {
				producto.setErrores("El nombre debe tener al menos 5 caracteres.");
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROPRODUCTO).forward(request, response);
				return;
			}

			DAOProducto.insert(producto);
			DAOProducto.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_PRODUCTOCRUD);
			return;
		}
	}

}
