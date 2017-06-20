package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOFacturaddbbMySQL;
import org.tiposDeClases.Factura;

public class FacturaFORMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_FACTURAS = "/WEB-INF/vistas/FacturaCRUD.jsp";
	static final String RUTA_ADMINISTRADOR_FACTURACRUD = "/admin/facturaCRUD";
	static final String RUTA_ADMINISTRADOR_REGISTROFACTURA = "/WEB-INF/vistas/FacturaFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("salir");
		String op = request.getParameter("opform");

		if (salir != null) {
			request.getRequestDispatcher(RUTA_ADMINISTRADOR_FACTURACRUD).forward(request, response);
			return;
		}

		DAOFacturaddbbMySQL DAOFactura = new DAOFacturaddbbMySQL();
		Factura factura = new Factura(Integer.parseInt(request.getParameter("id")), request.getParameter("numero_factura"), Integer.parseInt(request.getParameter("id_usuario")));

		switch (op) {
		case "modificar":
			DAOFactura.abrirComercioddbb();
			DAOFactura.update(factura);
			DAOFactura.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_FACTURACRUD);
			return;

		case "borrar":
			DAOFactura.abrirComercioddbb();
			DAOFactura.delete(factura);
			DAOFactura.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_FACTURACRUD);
			return;

		case "alta":
			String numero_factura = request.getParameter("numero_factura");
			DAOFactura.abrirComercioddbb();
			// Si el nombre ya existe.
			if (DAOFactura.buscarPorNumero(numero_factura) != null) {
				factura.setErrores("Numero no disponible.");
				request.setAttribute("factura", factura);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROFACTURA).forward(request, response);
				return;
			}

			DAOFactura.insert(factura);
			DAOFactura.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_FACTURACRUD);
			return;
		}
	}

}
