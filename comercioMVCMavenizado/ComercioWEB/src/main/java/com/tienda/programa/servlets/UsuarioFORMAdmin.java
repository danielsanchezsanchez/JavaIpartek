package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Usuario;

public class UsuarioFORMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String RUTA_LISTADO_USUARIOS = "/WEB-INF/vistas/UsuarioCRUD.jsp";
	static final String RUTA_ADMINISTRADOR_USUARIOCRUD = "/admin/usuarioCRUD";
	static final String RUTA_ADMINISTRADOR_REGISTROUSUARIO = "/WEB-INF/vistas/UsuarioFORMAdmin.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("salir");
		String op = request.getParameter("opform");

		if (salir != null) {
			request.getRequestDispatcher(RUTA_ADMINISTRADOR_USUARIOCRUD).forward(request, response);
			return;
		}

		DAOUsuarioddbbMySQL DAOUsuario = new DAOUsuarioddbbMySQL();
		Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("id_rol")), request.getParameter("nickusuario"), request.getParameter("nombre"), request.getParameter("apellido1"),
				request.getParameter("apellido2"));

		switch (op) {
		case "modificar":
			DAOUsuario.abrirComercioddbb();
			DAOUsuario.update(usuario);
			DAOUsuario.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_USUARIOCRUD);
			return;

		case "borrar":
			DAOUsuario.abrirComercioddbb();
			DAOUsuario.delete(usuario);
			DAOUsuario.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_USUARIOCRUD);
			return;

		case "alta":
			String nick = request.getParameter("nickusuario");
			DAOUsuario.abrirComercioddbb();
			// Si el nick ya existe.
			if (DAOUsuario.buscarPorNick(nick) != null) {
				usuario.setErrores("Nick no disponible.");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROUSUARIO).forward(request, response);
				return;
			}

			String pass1 = request.getParameter("contrasenia1");
			String pass2 = request.getParameter("contrasenia2");
			// Si las contraseñas no coinciden
			if (pass1.equals(pass2) == false) {
				usuario.setErrores("Las contraseñas deben de ser identicas.");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROUSUARIO).forward(request, response);
				return;
			}

			// Longitudes minimas
			if (nick.length() < 4) {
				usuario.setErrores("El nick debe tener al menos 5 caracteres.");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROUSUARIO).forward(request, response);
				return;
			}

			if (pass1.length() < 4) {
				usuario.setErrores("La contraseña debe tener al menos 5 caracteres.");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_ADMINISTRADOR_REGISTROUSUARIO).forward(request, response);
				return;
			}

			usuario.setContrasenia(pass1);
			DAOUsuario.insert(usuario);
			DAOUsuario.cerrarComercioddbb();
			response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_USUARIOCRUD);
			return;
		}
	}
}
