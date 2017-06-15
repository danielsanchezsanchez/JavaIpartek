package com.tienda.programa.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class listener implements ServletContextListener, HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("NUEVA SESION");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("CERRAMOS SESION");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("CIERRE DE APLICACION");
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ARRANQUE DE APLICACION");
	}

}
