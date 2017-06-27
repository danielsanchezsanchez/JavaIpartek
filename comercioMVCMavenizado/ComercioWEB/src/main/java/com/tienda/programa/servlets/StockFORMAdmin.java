package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDeClases.Stock;

public class StockFORMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_STOCK = "/WEB-INF/vistas/StockCRUD.jsp";
	static final String RUTA_ADMINISTRADOR_STOCKCRUD = "/admin/stockCRUD";
	static final String RUTA_ADMINISTRADOR_REGISTROSTOCK = "/WEB-INF/vistas/StockFORMAdmin.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("salir");
		String op = request.getParameter("opform");

		if (salir != null) {
			request.getRequestDispatcher(RUTA_ADMINISTRADOR_STOCKCRUD).forward(request, response);
			return;
		}

		DAOStockddbbMySQL DAOStock = new DAOStockddbbMySQL();
		Stock stock = new Stock(Integer.parseInt(request.getParameter("id_producto")), Integer.parseInt(request.getParameter("cantidad")));

		switch (op) {
		case "modificar":
			DAOStock.abrirComercioddbb();
			DAOStock.update(stock);
			DAOStock.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_STOCKCRUD);
			return;

		case "borrar":
			DAOStock.abrirComercioddbb();
			DAOStock.delete(stock);
			DAOStock.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_STOCKCRUD);
			return;

		case "alta":
			int id_producto = Integer.parseInt(request.getParameter("id_producto"));
			DAOStock.abrirComercioddbb();
			// Si el nombre ya existe.
			if (DAOStock.buscarStockPorProducto(id_producto) != null) {
				stock.setErrores("El producto ya esta dado de alta.");
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROSTOCK).forward(request, response);
				return;
			}

			DAOStock.insert(stock);
			DAOStock.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_STOCKCRUD);
			return;
		}
	}

}
