package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.tiposDeClases.Stock;

public class DAOStockddbbMySQL extends DAOComercioddbbMySQL implements DAOStockddbb {

	private final static String BUSCAR_TODOS = "SELECT * FROM stock";
	private final static String BUSCAR_STOCK_POR_PRODUCTO = "SELECT * FROM stock WHERE id_producto LIKE ?";
	private final static String INSERT = "INSERT INTO stock (id_producto, stock, entienda)" + " VALUES (?, ?, ?)";
	private final static String UPDATE = "UPDATE stock " + "SET id_producto = ?, stock = ? " + "WHERE id_producto = ?";
	private final static String DELETE = "DELETE FROM stock WHERE id_producto = ?";

	private PreparedStatement psBuscarTodos, psBuscarStockPorProducto, psInsert, psUpdate, psDelete;

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
			throw new DAOBaseDeDatosException("Error en Buscar todos", e);
		}
	}

	@Override
	public Stock[] buscarTodos() {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		ResultSet rs = null;
		try {

			psBuscarTodos = con.prepareStatement(BUSCAR_TODOS);
			rs = psBuscarTodos.executeQuery();

			Stock stock;

			while (rs.next()) {
				stock = new Stock();

				stock.setId_producto(rs.getInt("id_producto"));
				stock.setStock(rs.getInt("stock"));

				// Rellenamos el objeto PRODUCTO
				DAOProducto.abrirComercioddbb();
				stock.setProducto(DAOProducto.buscarPorId(rs.getInt("id_producto")));
				DAOProducto.cerrarComercioddbb();

				stocks.add(stock);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar todos", e);
		} finally {
			cerrar(psBuscarTodos, rs);
		}
		return stocks.toArray(new Stock[stocks.size()]);
	}

	@Override
	public Stock buscarStockPorProducto(int id_producto) {
		DAOProductoddbbMySQL DAOProducto = new DAOProductoddbbMySQL();
		Stock stock = null;
		ResultSet rs = null;
		try {
			psBuscarStockPorProducto = con.prepareStatement(BUSCAR_STOCK_POR_PRODUCTO);
			psBuscarStockPorProducto.setInt(1, id_producto);
			rs = psBuscarStockPorProducto.executeQuery();

			if (rs.next()) {
				stock = new Stock();

				stock.setId_producto(rs.getInt("id_producto"));
				stock.setStock(rs.getInt("stock"));

				// Rellenamos el objeto PRODUCTO
				DAOProducto.abrirComercioddbb();
				stock.setProducto(DAOProducto.buscarPorId(rs.getInt("id_producto")));
				DAOProducto.cerrarComercioddbb();
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar por Producto", e);
		} finally {
			cerrar(psBuscarStockPorProducto, rs);
		}

		return stock;
	}

	@Override
	public void insert(Stock stock) {
		try {

			psInsert = con.prepareStatement(INSERT);

			psInsert.setInt(1, stock.getId_producto());
			psInsert.setInt(2, stock.getStock());
			psInsert.setBoolean(3, true);

			int res = psInsert.executeUpdate();

			if (res != 1) {
				throw new DAOBaseDeDatosException("La inserción ha devuelto un valor " + res);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en insert", e);
		} finally {
			cerrar(psInsert);
		}
	}

	@Override
	public void update(Stock stock) {

		try {

			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setInt(1, stock.getId_producto());
			psUpdate.setInt(2, stock.getStock());

			psUpdate.setInt(3, stock.getId_producto());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				if (res == 0) {

				} else {
					throw new DAOBaseDeDatosException("Estas intentando modificar mas de un producto " + res);
				}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	@Override
	public void delete(Stock stock) {
		try {
			psDelete = con.prepareStatement(DELETE);
			psDelete.setInt(1, stock.getId_producto());

			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOBaseDeDatosException("El delete ha devuelto un valor " + res);

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en delete", e);
		} finally {
			cerrar(psDelete);
		}

	}

	@Override
	public void delete(int id_producto) {
		try {
			psDelete = con.prepareStatement(DELETE);
			psDelete.setInt(1, id_producto);

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
