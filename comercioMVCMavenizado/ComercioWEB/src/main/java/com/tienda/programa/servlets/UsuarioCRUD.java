package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Usuario;

public class UsuarioCRUD extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_USUARIOS = "/WEB-INF/vistas/UsuarioCRUD.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOUsuarioddbbMySQL DAOUsuario = new DAOUsuarioddbbMySQL();
		String op = request.getParameter("op");

		if (op == null) {
			Usuario[] usuarios = DAOUsuario.buscarTodos();
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher(RUTA_LISTADO_USUARIOS).forward(request, response);
			return;
		}
	}

}
