package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorMenuAdministradores extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_MENU_ADMINISTRADOR = "/WEB-INF/vistas/administradorMenu.jsp";
	static final String RUTA_INDEX = "/WEB-INF/vistas/index.jsp";
	static final String RUTA_ADMINISTRADOR_USUARIOCRUD = "/admin/usuarioCRUD";
	static final String RUTA_ADMINISTRADOR_PRODUCTOCRUD = "/admin/productoCRUD";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Creamos conexion con la sesion
		// HttpSession sesion = request.getSession();
		String op = request.getParameter("op");

		HttpSession sesion = request.getSession();

		// Primera vez que accede
		if (op == null) {
			request.getRequestDispatcher(RUTA_MENU_ADMINISTRADOR).forward(request, response);
			return;
		}

		// Opciones de administrador escojida
		if (op != null) {
			switch (op) {
			case "desconectar":
				sesion.invalidate();
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;
			case "gestionUsuarios":
				response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_USUARIOCRUD);
				return;
			case "gestionDeProductos":
				response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_PRODUCTOCRUD);
				return;
			}

		}
	}

}
