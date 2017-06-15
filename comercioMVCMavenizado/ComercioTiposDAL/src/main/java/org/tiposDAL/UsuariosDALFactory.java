package org.tiposDAL;


public class UsuariosDALFactory {

	public static UsuariosDAL getUsuariosDAL() {
		return new UsuariosDALColeccion();
	}

}
