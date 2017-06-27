package org.tiposDAOBaseDeDatos;

import org.tiposDeClases.Stock;

public interface DAOStockddbb extends DAOComercioddbb{
	
	public Stock[] buscarTodos();

	public Stock buscarStockPorProducto(int id_producto);

	public int insert(Stock stock);

	public void update(Stock stock);

	public void delete(Stock stock);

	public void delete(int id_producto);
}
