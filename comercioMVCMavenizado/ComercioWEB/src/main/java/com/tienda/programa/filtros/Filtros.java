package com.tienda.programa.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.tiposDeClases.Usuario;

public class Filtros implements Filter {

	private static Logger log = Logger.getLogger(Filtros.class);
	static final String RUTA_INDEX = "/controladorIndex";
	static final String RUTA_CONTROLADOR_USUARIONORMAL = "/usuarios/controladorMenuUsuarios";
	static final String RUTA_CONTROLADOR_ADMINISTRADOR = "/admin/controladorMenuAdministradores";

	public Filtros() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Creamos y recojemos el usuario de la sesion para que no pueda entrar
		// donde no debe
		HttpSession session = req.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		// METODO PARA VER LAS URL POR LAS QUE PASA
		String urlComprobacion = req.getRequestURI();
		log.info("Entra la url: " + urlComprobacion);

		// Controla que un usuario normal no pueda entrar en opciones de
		// administrador
		if ((req.getRequestURI()).startsWith("/admin/") && usuario.getRol() == 2) {
			log.info("Entra en la opcion es un usuario normal");
			res.sendRedirect(req.getContextPath() + RUTA_CONTROLADOR_USUARIONORMAL);
			return;
		}

		// Controla que un administrador no pueda entrar en opciones de
		// usuario
		// normal
		if ((req.getRequestURI()).startsWith("/usuarios/") && usuario.getRol() == 1) {
			log.info("Entra en la opcion es un administrador");
			res.sendRedirect(req.getContextPath() + RUTA_CONTROLADOR_ADMINISTRADOR);
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
