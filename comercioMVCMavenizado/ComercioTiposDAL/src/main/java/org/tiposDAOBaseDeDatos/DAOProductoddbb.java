package org.tiposDAOBaseDeDatos;

import org.tiposDeClases.Producto;

public interface DAOProductoddbb extends DAOComercioddbb {

	public Producto[] buscarTodos();

	public Producto[] buscarTodosLosNoEnTienda();

	public Producto buscarPorId(int id);

	public Producto buscarPorNombre(String nombre);

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void delete(int id);
}
