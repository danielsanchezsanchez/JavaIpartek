package org.tiposDAL;

public class CarritoDALFactory {

	public static CarritoDAL getCarritoDAL() {
		return new CarritoDALColeccion();
	}
}
