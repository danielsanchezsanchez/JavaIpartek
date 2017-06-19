package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDeClases.Producto;

public class ProductoCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_PRODUCTOS = "/WEB-INF/vistas/ProductoCRUD.jsp";
	static final String RUTA_FORMULARIO_PRODUCTOS = "/WEB-INF/vistas/ProductoFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		String op = request.getParameter("op");

		// Primera vez que pasa por aqui.
		if (op == null) {
			DAOProducto.abrirComercioddbb();
			Producto[] productos = DAOProducto.buscarTodos();
			DAOProducto.cerrarComercioddbb();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(RUTA_LISTADO_PRODUCTOS).forward(request, response);
			return;
		}

		// Cuando escoje una de las opciones
		if (op != null) {

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
	}

}
