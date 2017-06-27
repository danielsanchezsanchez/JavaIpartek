package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDeClases.Stock;

public class StockCRUD extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_STOCK = "/WEB-INF/vistas/StockCRUD.jsp";
	static final String RUTA_FORMULARIO_STOCK = "/WEB-INF/vistas/StockFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		DAOStockddbbMySQL DAOStock = new DAOStockddbbMySQL();
		String op = request.getParameter("op");

		// Primera vez que pasa por aqui.
		if (op == null) {

			// Factura adornada con el nick del usuario.
			DAOStock.abrirComercioddbb();
			Stock[] stocks = DAOStock.buscarTodos();
			DAOStock.cerrarComercioddbb();

			request.setAttribute("stock", stocks);
			request.getRequestDispatcher(RUTA_LISTADO_STOCK).forward(request, response);
			return;
		}
		// Cuando escoje una de las opciones
		if (op != null) {

			int id_producto = Integer.parseInt(request.getParameter("id_producto"));

			Stock stock;

			switch (op) {
			case "modificar":
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;
			case "borrar":
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK).forward(request, response);
				return;

			}
		}
	}
}
