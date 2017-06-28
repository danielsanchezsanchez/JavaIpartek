package org.tiposDAOBaseDeDatos;

public interface DAOComercioddbb {

	public void abrirComercioddbb();

	public void cerrarComercioddbb();

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();

	public void reutilizarConexion(DAOComercioddbb DAOComercio);
}
