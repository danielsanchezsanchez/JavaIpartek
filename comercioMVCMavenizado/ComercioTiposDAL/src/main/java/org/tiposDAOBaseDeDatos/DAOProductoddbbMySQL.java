package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.tiposDeClases.Producto;

public class DAOProductoddbbMySQL extends DAOComercioddbbMySQL implements DAOProductoddbb {

	private final static String BUSCAR_TODOS = "SELECT * FROM productos";
	private final static String BUSCAR_POR_NOMBRE = "SELECT * FROM productos WHERE nombre LIKE ?";
	private final static String INSERT = "INSERT INTO productos (nombre, precio, descripcion, url_producto_img)" + " VALUES (?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE productos " + "SET nombre = ?, precio = ?, descripcion = ?, url_producto_img = ? " + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM productos WHERE id = ?";

	private PreparedStatement psBuscarTodos, psBuscarPorNombre, psInsert, psUpdate, psDelete;

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
		Producto producto = null;
		ResultSet rs = null;
		try {
			psBuscarPorNombre = con.prepareStatement(BUSCAR_POR_NOMBRE);
			psBuscarPorNombre.setString(1, nombre);
			rs = psBuscarPorNombre.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setID(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setUrl_producto_img(rs.getString("url_producto_img"));
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findById", e);
		} finally {
			cerrar(psBuscarPorNombre, rs);
		}

		return producto;
	}

	@Override
	public int insert(Producto producto) {
		try {

			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, producto.getNombre());
			psInsert.setBigDecimal(2, producto.getPrecio());
			psInsert.setString(3, producto.getDescripcion());
			psInsert.setString(4, producto.getUrl_producto_img());

			int res = psInsert.executeUpdate();

			if (res != 1) {
				throw new DAOBaseDeDatosException("La inserción ha devuelto un valor " + res);
			}
			ResultSet generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new DAOBaseDeDatosException("No se ha recibido la clave generada");
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en insert", e);
		} finally {
			cerrar(psInsert);
		}
	}

	@Override
	public void update(Producto producto) {
		try {

			psUpdate = con.prepareStatement(UPDATE);
			psUpdate.setString(1, producto.getNombre());
			psUpdate.setBigDecimal(2, producto.getPrecio());
			psUpdate.setString(3, producto.getDescripcion());
			psUpdate.setString(4, producto.getUrl_producto_img());

			psUpdate.setInt(5, producto.getID());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				if (res == 0) {

				} else {
					throw new DAOBaseDeDatosException("Estas intentando modificar mas de un usuario " + res);
				}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	@Override
	public void delete(Producto producto) {
		delete(producto.getID());

	}

	@Override
	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(DELETE);
			psDelete.setInt(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOBaseDeDatosException("El delete ha devuelto un valor " + res);

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en delete", e);
		} finally {
			cerrar(psDelete);
		}

	}

}
