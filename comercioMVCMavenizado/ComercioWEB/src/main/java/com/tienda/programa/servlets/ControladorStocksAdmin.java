package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOProductoddbb;
import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOStockddbb;
import org.tiposDAOBaseDeDatos.DAOStockddbbMySQL;
import org.tiposDeClases.Producto;
import org.tiposDeClases.Stock;

public class ControladorStocksAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String RUTA_LISTADO_STOCK = "/WEB-INF/vistas/StockCRUD.jsp";
	static final String RUTA_FORMULARIO_ALTA_EN_TIENDA = "/WEB-INF/vistas/adminProductoStock.jsp";
	static final String RUTA_FORMULARIO_STOCK_ALTA = "/WEB-INF/vistas/StockFORMAdminAlta.jsp";
	static final String RUTA_FORMULARIO_STOCK_MODIFICARBORRAR = "/WEB-INF/vistas/StockFORMAdminModificarBorrar.jsp";
	static final String RUTA_ADMINISTRADOR_STOCKCRUD = "/admin/controladorStocksAdmin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOStockddbb DAOStock = new DAOStockddbbMySQL();
		DAOProductoddbb DAOProducto = new DAOProductoddbbMySQL();

		String op = request.getParameter("op");
		String salir = request.getParameter("salir");
		String opcion = request.getParameter("opcion");

		// Primera vez que pasa por aqui.
		if (op == null && opcion == null) {

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

		// Escogemos la opcion salir
		if (salir != null) {
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_STOCKCRUD);
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
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_MODIFICARBORRAR).forward(request, response);
				return;
			case "borrar":
				id_producto = Integer.parseInt(request.getParameter("id_producto"));
				stock = new Stock();
				DAOStock.abrirComercioddbb();
				stock = DAOStock.buscarStockPorProducto(id_producto);
				DAOStock.cerrarComercioddbb();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_MODIFICARBORRAR).forward(request, response);
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
				request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_ALTA).forward(request, response);
				return;
			}
		}

		if (opcion != null) {
			int stockCantidad;
			int id_producto;
			Stock stock = new Stock(Integer.parseInt(request.getParameter("id_producto")), Integer.parseInt(request.getParameter("stock")), true);

			switch (opcion) {
			case "modificar":
				id_producto = Integer.parseInt(request.getParameter("id_producto"));
				stockCantidad = Integer.parseInt(request.getParameter("stock"));

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

					request.getRequestDispatcher(RUTA_FORMULARIO_STOCK_MODIFICARBORRAR).forward(request, response);
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

}
