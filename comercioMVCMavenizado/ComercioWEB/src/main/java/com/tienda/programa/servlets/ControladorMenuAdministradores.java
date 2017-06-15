package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorMenuAdministradores extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_MENU_ADMINISTRADOR = "/WEB-INF/vistas/administradorMenu.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Creamos conexion con la sesion
		// HttpSession sesion = request.getSession();
		String op = request.getParameter("op");

		// Primera vez que accede
		if (op == null) {
			request.getRequestDispatcher(RUTA_MENU_ADMINISTRADOR).forward(request, response);
			return;
		}

		// Opciones de administrador escojida
		if (op != null) {
			switch (op) {

			}
		}
	}

}
