package org.tiposDAL;

import java.util.TreeMap;

import org.tiposDAOBaseDeDatos.DAOProductoddbbMySQL;
import org.tiposDeClases.Articulo;
import org.tiposDeClases.Producto;

public class CarritoDALColeccion implements CarritoDAL {

	private TreeMap<Integer, Articulo> articulos = new TreeMap<Integer, Articulo>();

	@Override
	public void aniadir(Articulo articulo) {

		// Añado el producto en si con el id_producto para poder usarlo en el
		// jsp
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		Producto producto = new Producto();
		DAOProducto.abrirComercioddbb();
		producto = DAOProducto.buscarPorId(articulo.getId_producto());
		DAOProducto.cerrarComercioddbb();
		Articulo articuloaniadir = new Articulo(articulo.getId_producto(), articulo.getCantidad(), producto);

		// Añado el articulo
		articulos.put(articuloaniadir.getId_producto(), articuloaniadir);

	}

	@Override
	public void modificar(Articulo articulo) {
		if (!articulos.containsKey(articulo.getId_producto())) {
			throw new CarritoDALException("Intento de modificar articulo no existente " + articulo);
		}

		// Añado el producto en si con el id_producto para poder usarlo en el
		// jsp
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		Producto producto = new Producto();
		DAOProducto.abrirComercioddbb();
		producto = DAOProducto.buscarPorId(articulo.getId_producto());
		DAOProducto.cerrarComercioddbb();
		Articulo articuloaniadir = new Articulo(articulo.getId_producto(), articulo.getCantidad(), producto);

		// Modifico el articulo
		articulos.put(articuloaniadir.getId_producto(), articuloaniadir);

	}

	@Override
	public void borrar(Articulo articulo) {
		articulos.remove(articulo.getId_producto());

	}

	@Override
	public Articulo[] buscarTodosLosArticulos() {
		return articulos.values().toArray(new Articulo[articulos.size()]);

	}

	@Override
	public Articulo buscarUnArticuloPorIdProducto(int id_producto) {
		return articulos.get(id_producto);
	}

	@Override
	public boolean esCarritoVacio() {
		if (articulos.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
