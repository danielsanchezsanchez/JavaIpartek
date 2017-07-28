package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Usuario;

public class ControladorUsuariosAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String RUTA_LISTADO_USUARIOS = "/WEB-INF/vistas/UsuarioCRUD.jsp";
	static final String RUTA_FORMULARIO_USUARIOS = "/WEB-INF/vistas/UsuarioFORMAdmin.jsp";
	static final String RUTA_CONTROLADOR_USUARIOS = "/admin/controladorUsuariosAdmin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOUsuarioddbbMySQL DAOUsuario = new DAOUsuarioddbbMySQL();
		String op = request.getParameter("op");
		String salir = request.getParameter("salir");
		String opcion = request.getParameter("opcion");

		// Primera vez que pasa por aqui.
		if (op == null && opcion == null) {
			DAOUsuario.abrirComercioddbb();
			Usuario[] usuarios = DAOUsuario.buscarTodos();
			DAOUsuario.cerrarComercioddbb();
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher(RUTA_LISTADO_USUARIOS).forward(request, response);
			return;
		}

		if (salir != null) {
			response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_USUARIOS);
			return;
		}

		// Cuando escoje una de las opciones
		if (op != null && opcion == null) {

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
		if (opcion != null && op != null) {
			Usuario usuario = new Usuario(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("id_rol")), request.getParameter("nickusuario"), request.getParameter("nombre"), request.getParameter("apellido1"), request.getParameter("apellido2"));

			switch (opcion) {
			case "modificar":
				DAOUsuario.abrirComercioddbb();
				DAOUsuario.update(usuario);
				DAOUsuario.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_USUARIOS);
				return;

			case "borrar":
				DAOUsuario.abrirComercioddbb();
				DAOUsuario.delete(usuario);
				DAOUsuario.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_USUARIOS);
				return;

			case "alta":
				String nick = request.getParameter("nickusuario");
				DAOUsuario.abrirComercioddbb();
				// Si el nick ya existe.
				if (DAOUsuario.buscarPorNick(nick) != null) {
					usuario.setErrores("Nick no disponible.");
					request.setAttribute("usuario", usuario);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
					return;
				}

				String pass1 = request.getParameter("contrasenia1");
				String pass2 = request.getParameter("contrasenia2");
				// Si las contraseñas no coinciden
				if (pass1.equals(pass2) == false) {
					usuario.setErrores("Las contraseñas deben de ser identicas.");
					request.setAttribute("usuario", usuario);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
					return;
				}

				// Longitudes minimas
				if (nick.length() < 4) {
					usuario.setErrores("El nick debe tener al menos 5 caracteres.");
					request.setAttribute("usuario", usuario);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
					return;
				}

				if (pass1.length() < 4) {
					usuario.setErrores("La contraseña debe tener al menos 5 caracteres.");
					request.setAttribute("usuario", usuario);
					request.setAttribute("op", op);
					request.getRequestDispatcher(RUTA_FORMULARIO_USUARIOS).forward(request, response);
					return;
				}

				usuario.setContrasenia(pass1);
				DAOUsuario.insert(usuario);
				DAOUsuario.cerrarComercioddbb();
				response.sendRedirect(request.getContextPath() + RUTA_CONTROLADOR_USUARIOS);
				return;
			}
		}
	}
}
