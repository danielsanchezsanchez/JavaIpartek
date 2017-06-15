package com.tienda.programa.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tiposDAOBaseDeDatos.DAOUsuarioddbb;
import org.tiposDAOBaseDeDatos.DAOUsuarioddbbMySQL;
import org.tiposDeClases.Usuario;

public class ControladorIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private static Logger log = Logger.getLogger(ControladorIndex.class);
	// log.info("Usuario.");
	static final String RUTA_INDEX = "/WEB-INF/vistas/index.jsp";
	static final String RUTA_FORMULARIO_LOGIN = "/WEB-INF/vistas/usuarioLogin.jsp";
	static final String RUTA_FORMULARIO_REGISTRO = "/WEB-INF/vistas/usuarioRegistro.jsp";
	static final String RUTA_ADMINISTRADOR_LOGEADO = "admin/controladorAdministrador";
	static final String RUTA_USUARIO_LOGEADO = "usuarios/controladorUsuarios";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Controlamos las diferentes opciones desde el controlador de la pagina
		// de inicio
		HttpSession sesion = request.getSession();
		String op = request.getParameter("op");

		if (op == null) {
			request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
			return;
		} else {
			switch (op) {
			case "desconectarUsuario":
				sesion.invalidate();
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;
			case "conectarUsuario":
				request.getRequestDispatcher(RUTA_FORMULARIO_LOGIN).forward(request, response);
				return;
			case "registro":
				request.getRequestDispatcher(RUTA_FORMULARIO_REGISTRO).forward(request, response);
				return;
			case "LOGEAR":

				// Creamos una instancia de usuario para guardar lo introducido
				// por el usuario y comparar.
				Usuario usuarioLog = null;
				String nickLog = request.getParameter("nickusuario");
				String passLog = request.getParameter("contrasenia");

				DAOUsuarioddbb usuDB = new DAOUsuarioddbbMySQL();
				usuDB.abrirComercioddbb();
				usuarioLog = usuDB.buscarPorNick(nickLog);
				usuDB.cerrarComercioddbb();
				// Comprobamos si el usuario esta registrado
				if (usuarioLog == null) {
					Usuario usu = new Usuario();
					usu.setNickusuario(nickLog);
					usu.setErrores("Usuario no valido.");
					request.setAttribute("usuario", usu);
					request.getRequestDispatcher(RUTA_FORMULARIO_LOGIN).forward(request, response);
					return;
				} else {
					if (usuarioLog.getContrasenia().equals(passLog)) {
						if (usuarioLog.getRol() == 1) {
							response.sendRedirect(request.getContextPath() + RUTA_ADMINISTRADOR_LOGEADO);
							return;
						}
						if (usuarioLog.getRol() == 2) {
							response.sendRedirect(request.getContextPath() + RUTA_USUARIO_LOGEADO);
							return;
						}
					} else {
						usuarioLog.setErrores("Usuario no valido.");
						request.setAttribute("usuario", usuarioLog);
						request.getRequestDispatcher(RUTA_FORMULARIO_LOGIN).forward(request, response);
						return;
					}
				}
			case "REGISTRAR":

				// Creamos una instancia de usuario para guardar lo introducido
				// por el usuario y comparar.
				Usuario usuarioReg = null;
				String nickReg = request.getParameter("nickusuario");
				String passReg = request.getParameter("contrasenia");

				return;
			case "SALIR":
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;
			}
		}

	}
}
