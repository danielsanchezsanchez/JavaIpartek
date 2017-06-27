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
	static final String RUTA_FORMULARIO_USUARIOS = "/WEB-INF/vistas/UsuarioFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOUsuarioddbbMySQL DAOUsuario = new DAOUsuarioddbbMySQL();
		String op = request.getParameter("op");

		// Primera vez que pasa por aqui.
		if (op == null) {
			DAOUsuario.abrirComercioddbb();
			Usuario[] usuarios = DAOUsuario.buscarTodos();
			DAOUsuario.cerrarComercioddbb();
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher(RUTA_LISTADO_USUARIOS).forward(request, response);
			return;
		}

		// Cuando escoje una de las opciones
		if (op != null) {

			String nick = request.getParameter("nickusuario");

			Usuario usuario;

			switch (op) {
			case "modificar":
				usuario = new Usuario();
				DAOUsuario.abrirComercioddbb();
				usuario = DAOUsuario.buscarPorNick(nick);
				DAOUsuario.cerrarComercioddbb();
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
				return;
			case "borrar":
				usuario = new Usuario();
				DAOUsuario.abrirComercioddbb();
				usuario = DAOUsuario.buscarPorNick(nick);
				DAOUsuario.cerrarComercioddbb();
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
				return;
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
				return;

			}
		}
	}

}
