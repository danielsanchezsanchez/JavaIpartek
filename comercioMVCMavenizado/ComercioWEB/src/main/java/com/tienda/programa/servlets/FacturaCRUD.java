package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOFactura_Productoddbb;
import org.tiposDAOBaseDeDatos.DAOFactura_ProductoddbbMySQL;
import org.tiposDAOBaseDeDatos.DAOFacturaddbbMySQL;
import org.tiposDeClases.Articulo;
import org.tiposDeClases.Factura;

public class FacturaCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_FACTURAS = "/WEB-INF/vistas/FacturaCRUD.jsp";
	static final String RUTA_FORMULARIO_FACTURAS = "/WEB-INF/vistas/FacturaFORMAdmin.jsp";
	static final String RUTA_DESPLIEGE_DE_FACTURA = "/WEB-INF/vistas/Factura-ProductosFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFacturaddbbMySQL DAOFactura = new DAOFacturaddbbMySQL();
		String op = request.getParameter("op");

		// Primera vez que pasa por aqui.
		if (op == null) {

			// Factura adornada con el nick del usuario.
			DAOFactura.abrirComercioddbb();
			Factura[] facturas = DAOFactura.buscarTodasConUsuarios();
			DAOFactura.cerrarComercioddbb();

			request.setAttribute("facturas", facturas);
			request.getRequestDispatcher(RUTA_LISTADO_FACTURAS).forward(request, response);
			return;
		}

		// Cuando escoje una de las opciones
		if (op != null) {

			String numero_factura = request.getParameter("numero_factura");

			Factura factura;

			switch (op) {
			case "modificar":
				factura = new Factura();
				DAOFactura.abrirComercioddbb();
				factura = DAOFactura.buscarPorNumero(numero_factura);
				DAOFactura.cerrarComercioddbb();
				request.setAttribute("factura", factura);
				request.getRequestDispatcher(RUTA_FORMULARIO_FACTURAS).forward(request, response);
				return;
			case "borrar":
				factura = new Factura();
				DAOFactura.abrirComercioddbb();
				factura = DAOFactura.buscarPorNumero(numero_factura);
				DAOFactura.cerrarComercioddbb();
				request.setAttribute("factura", factura);
				request.getRequestDispatcher(RUTA_FORMULARIO_FACTURAS).forward(request, response);
				return;
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO_FACTURAS).forward(request, response);
				return;
			case "desplegar":

				// Abrimos la conexion con la base de datos
				DAOFactura_Productoddbb DAOFac_ProdFila = new DAOFactura_ProductoddbbMySQL();
				DAOFac_ProdFila.abrirComercioddbb();

				// Recojemos el id_factura
				int id_factura;
				id_factura = DAOFac_ProdFila.buscarIdFacturaPorNombreFactura(numero_factura);

				// Cargamos las facturas del usuario logeado para enviarselas al
				// jsp
				Articulo[] articuloHistoricos;
				articuloHistoricos = DAOFac_ProdFila.buscarTodasPorFactura(id_factura);
				request.setAttribute("compras", articuloHistoricos);

				// Cerramos la ddbb
				DAOFac_ProdFila.cerrarComercioddbb();

				request.getRequestDispatcher(RUTA_DESPLIEGE_DE_FACTURA).forward(request, response);
				return;
			case "modificarHistorico":
				return;
			case "borrarHistorico":
				return;
			case "altaHistorico":
				return;
			}
		}
	}

}
