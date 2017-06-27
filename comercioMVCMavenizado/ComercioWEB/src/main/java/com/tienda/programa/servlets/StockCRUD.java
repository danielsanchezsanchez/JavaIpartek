package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDeClases.Producto;
import org.tiposDeClases.Stock;

public class StockCRUD extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_STOCK = "/WEB-INF/vistas/StockCRUD.jsp";
	static final String RUTA_FORMULARIO_ALTA_EN_TIENDA = "/WEB-INF/vistas/adminProductoStock.jsp";
	static final String RUTA_FORMULARIO_STOCK = "/WEB-INF/vistas/StockFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOStockddbbMySQL DAOStock = new DAOStockddbbMySQL();
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();

		String op = request.getParameter("op");

		// Primera vez que pasa por aqui.
		if (op == null) {

			// Stock reducido sin el PRODUCTO
			DAOStock.abrirComercioddbb();
			Stock[] stock = DAOStock.buscarTodos();
			DAOStock.cerrarComercioddbb();

			// Cerramos DAOProducto
			DAOProducto.cerrarComercioddbb();
			request.setAttribute("stock", stock);
			request.getRequestDispatcher(RUTA_LISTADO_STOCK).forward(request, response);
			return;
		}
		// Cuando escoje una de las opciones
		if (op != null) {

			int id_producto;
			Stock stock;

			switch (op) {
			case "modificar":
				id_producto = Integer.parseInt(request.getParameter("id_producto"));
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;
			case "borrar":
				id_producto = Integer.parseInt(request.getParameter("id_producto"));
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;
			case "alta":
				DAOProducto.abrirComercioddbb();
				Producto[] productos = DAOProducto.buscarTodosLosNoEnTienda();
				DAOProducto.cerrarComercioddbb();
				request.setAttribute("productos", productos);
				request.getRequestDispatcher(RUTA_FORMULARIO_ALTA_EN_TIENDA).forward(request, response);
				return;
			case "altaTienda":
				int id_stock = Integer.parseInt(request.getParameter("id"));
				Producto productoStock = new Producto();
				DAOProducto.abrirComercioddbb();
				productoStock = DAOProducto.buscarPorId(id_stock);
				DAOProducto.cerrarComercioddbb();
				request.setAttribute("producto", productoStock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;
			}
		}
	}
}
