package org.tiposDAL;

import org.tiposDeClases.Articulo;

public interface CarritoDAL {

	public void aniadir(Articulo articulo);

	public void modificar(Articulo articulo);

	public void borrar(Articulo articulo);

	public Articulo[] buscarTodosLosArticulos();

	public Articulo buscarUnArticuloPorIdProducto(int id_producto);

	public boolean esCarritoVacio();

}
