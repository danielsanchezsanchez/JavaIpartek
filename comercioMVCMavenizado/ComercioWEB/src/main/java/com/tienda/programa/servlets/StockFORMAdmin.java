package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbb;
import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDeClases.Producto;
import org.tiposDeClases.Stock;

public class StockFORMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_STOCK = "/WEB-INF/vistas/StockCRUD.jsp";
	static final String RUTA_ADMINISTRADOR_STOCKCRUD = "/admin/stockCRUD";
	static final String RUTA_FORMULARIO_STOCK_ALTA = "/WEB-INF/vistas/StockFORMAdminAlta.jsp";
	static final String RUTA_FORMULARIO_STOCK_MODIFICAR = "/WEB-INF/vistas/StockFORMAdminModificarBorrar.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("salir");
		String op = request.getParameter("opform");
		int stockCantidad;
		int id_producto;
		DAOProductoddbb DAOProducto;

		if (salir != null) {
			request.getRequestDispatcher(RUTA_ADMINISTRADOR_STOCKCRUD).forward(request, response);
			return;
		}

		DAOStockddbbMySQL DAOStock = new DAOStockddbbMySQL();
		Stock stock = new Stock(Integer.parseInt(request.getParameter("id_producto")), Integer.parseInt(request.getParameter("stock")), true);

		switch (op) {
		case "modificar":
			id_producto = Integer.parseInt(request.getParameter("id_producto"));
			stockCantidad = Integer.parseInt(request.getParameter("stock"));
			DAOProducto = new DAOProductoddbbMySQL();

			// Comprobamos el stock
			if (stockCantidad <= 0) {

				// Error de no suficiente STOCK
				request.setAttribute("errores", "¡¡¡ATENCION!!!: Error en la cantidad, debe ser 1 o superior.");
				request.setAttribute("op", "modificar");

				// Cargamos la tabla productos
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);

				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_MODIFICAR).forward(request, response);
				return;

			}
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

		case "Aceptar":
			id_producto = Integer.parseInt(request.getParameter("id_producto"));
			stockCantidad = Integer.parseInt(request.getParameter("stock"));
			DAOProducto = new DAOProductoddbbMySQL();

			// Comprobamos el stock
			if (stockCantidad <= 0) {

				// Error de no suficiente STOCK
				request.setAttribute("errores", "¡¡¡ATENCION!!!: Error en la cantidad, debe ser 1 o superior.");
				request.setAttribute("op", "altaTienda");
				// Cargamos la tabla productos
				DAOProducto.abrirComercioddbb();
				Producto producto = DAOProducto.buscarPorId(id_producto);
				DAOProducto.cerrarComercioddbb();
				request.setAttribute("producto", producto);

				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_ALTA).forward(request, response);
				return;

			}

			DAOStock.abrirComercioddbb();
			// Si el nombre ya existe.
			if (DAOStock.buscarStockPorProducto(id_producto) != null) {
				stock.setErrores("El producto ya esta dado de alta.");
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_ALTA).forward(request, response);
				return;
			}

			DAOStock.insert(stock);
			DAOStock.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_STOCKCRUD);
			return;
		}
	}

}
