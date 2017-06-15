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
	static final String RUTA_ADMINISTRADOR_LOGEADO = "admin/ControladorMenuAdministradores";
	static final String RUTA_USUARIO_LOGEADO = "usuarios/controladorMenuUsuarios";

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

				DAOUsuarioddbb usuDBLog = new DAOUsuarioddbbMySQL();
				usuDBLog.abrirComercioddbb();
				usuarioLog = usuDBLog.buscarPorNick(nickLog);
				usuDBLog.cerrarComercioddbb();
				// Comprobamos si el usuario esta registrado
				if (usuarioLog == null) {
					Usuario usuLog = new Usuario();
					usuLog.setNickusuario(nickLog);
					usuLog.setErrores("Usuario no valido.");
					request.setAttribute("usuario", usuLog);
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
				Usuario usuReg = new Usuario();
				String nickReg = request.getParameter("nickusuario");
				String nombre = request.getParameter("nombre");
				String apellido1 = request.getParameter("apellido1");
				String apellido2 = request.getParameter("apellido2");
				String passReg1 = request.getParameter("contrasenia1");
				String passReg2 = request.getParameter("contrasenia2");

				DAOUsuarioddbb usuDBReg = new DAOUsuarioddbbMySQL();
				usuDBReg.abrirComercioddbb();

				usuReg.setNickusuario(nickReg);
				usuReg.setNombre(nombre);
				usuReg.setApellido1(apellido1);
				usuReg.setApellido2(apellido2);

				// Si el nick ya existe.
				if (usuDBReg.buscarPorNick(nickReg) != null) {
					usuReg.setErrores("Nick no disponible.");
					request.setAttribute("usuario", usuReg);
					request.getRequestDispatcher(RUTA_FORMULARIO_REGISTRO).forward(request, response);
					return;
				}

				// Si las contraseñas no coinciden
				if (passReg1.equals(passReg2) == false) {
					usuReg.setErrores("Las contraseñas deben de ser identicas.");
					request.setAttribute("usuario", usuReg);
					request.getRequestDispatcher(RUTA_FORMULARIO_REGISTRO).forward(request, response);
					return;
				}

				// Longitudes minimas
				if (nickReg.length() < 4) {
					usuReg.setErrores("El nick debe tener al menos 5 caracteres.");
					request.setAttribute("usuario", usuReg);
					request.getRequestDispatcher(RUTA_FORMULARIO_REGISTRO).forward(request, response);
					return;
				}

				if (passReg1.length() < 4) {
					usuReg.setErrores("La contraseña debe tener al menos 5 caracteres.");
					request.setAttribute("usuario", usuReg);
					request.getRequestDispatcher(RUTA_FORMULARIO_REGISTRO).forward(request, response);
					return;
				}

				// Registramos rol y password
				usuReg.setRol(2);
				usuReg.setContrasenia(passReg1);

				// Todos los datos introducimos, creamos el usuario en la DB.
				usuDBReg.insert(usuReg);

				// Me devuelve un int que puedo o no controlar en "filtros?????"
				usuDBReg.cerrarComercioddbb();

				response.sendRedirect(request.getContextPath() + RUTA_USUARIO_LOGEADO);
				return;

			case "SALIR":
				request.getRequestDispatcher(RUTA_INDEX).forward(request, response);
				return;
			}
		}

	}
}
