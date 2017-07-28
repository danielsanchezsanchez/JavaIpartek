package org.tiposDAOBaseDeDatos;

import org.tiposDeClases.Articulo;

public interface DAOFactura_Productoddbb extends DAOComercioddbb {

	public int buscarIdFacturaPorNombreFactura(String nombre_factura);

	public Articulo[] buscarTodasPorFactura(int id_factura);

	public void insert(Articulo articulo);

	public void update(Articulo articulo);

	public void delete(Articulo articulo);

}
