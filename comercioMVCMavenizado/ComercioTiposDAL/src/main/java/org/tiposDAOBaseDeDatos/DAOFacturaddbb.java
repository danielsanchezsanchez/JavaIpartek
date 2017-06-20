package org.tiposDAOBaseDeDatos;

import org.tiposDeClases.Factura;

public interface DAOFacturaddbb extends DAOComercioddbb {

	public Factura[] buscarTodas();

	public Factura[] buscarTodasConUsuarios();

	public Factura buscarPorId(int id);

	public Factura buscarPorNumero(String numero_factura);

	public int insert(Factura factura);

	public void update(Factura factura);

	public void delete(Factura factura);

	public void delete(int id);
}
