package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.tiposDeClases.Producto;

public class DAOProductoddbbMySQL extends DAOComercioddbbMySQL implements DAOProductoddbb {

	private final static String BUSCAR_TODOS = "SELECT * FROM productos";

	private PreparedStatement psBuscarTodos;

	private void cerrar(PreparedStatement ps) {
		cerrar(ps, null);
	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findAll", e);
		}
	}

	@Override
	public Producto[] buscarTodos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;
		try {

			psBuscarTodos = con.prepareStatement(BUSCAR_TODOS);
			rs = psBuscarTodos.executeQuery();

			Producto producto;

			while (rs.next()) {
				producto = new Producto();

				producto.setID(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setUrl_producto_img(rs.getString("url_producto_img"));

				productos.add(producto);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findAll", e);
		} finally {
			cerrar(psBuscarTodos, rs);
		}
		return productos.toArray(new Producto[productos.size()]);
	}

	@Override
	public Producto buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Producto producto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
