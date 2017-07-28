package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.tiposDeClases.Articulo;

public class DAOFactura_ProductoddbbMySQL extends DAOComercioddbbMySQL implements DAOFactura_Productoddbb {

	private final static String INSERT = "INSERT INTO factura_productos (id_factura, id_producto, cantidad)" + " VALUES (?, ?, ?)";
	private final static String BUSCAR_POR_IDFACTURA = "SELECT * FROM factura_productos WHERE id_factura LIKE ?";
	private final static String BUSCAR_IDFACTURA_POR_NOMBRE = "SELECT id FROM facturas WHERE numero_factura LIKE ?";

	private PreparedStatement psInsert, psBuscarPorIdFactura, psBuscarIdFacturaPorNombre;

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
	public Articulo[] buscarTodasPorFactura(int id_factura) {
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		Articulo articulo = null;
		ResultSet rs = null;
		try {
			psBuscarPorIdFactura = con.prepareStatement(BUSCAR_POR_IDFACTURA);
			psBuscarPorIdFactura.setInt(1, id_factura);
			rs = psBuscarPorIdFactura.executeQuery();

			while (rs.next()) {
				articulo = new Articulo();

				articulo.setId_factura(rs.getInt("id_factura"));
				articulo.setId_producto(rs.getInt("id_producto"));
				articulo.setCantidad(rs.getInt("cantidad"));

				articulos.add(articulo);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en buscarTodasPorFactura", e);
		} finally {
			cerrar(psBuscarPorIdFactura, rs);
		}

		return articulos.toArray(new Articulo[articulos.size()]);
	}

	@Override
	public void insert(Articulo articulo) {
		try {

			psInsert = con.prepareStatement(INSERT);

			psInsert.setInt(1, articulo.getId_factura());
			psInsert.setInt(2, articulo.getId_producto());
			psInsert.setInt(3, articulo.getCantidad());

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
	public int buscarIdFacturaPorNombreFactura(String nombre_factura) {
		int id_factura = 0;
		ResultSet rs = null;
		try {
			psBuscarIdFacturaPorNombre = con.prepareStatement(BUSCAR_IDFACTURA_POR_NOMBRE);
			psBuscarIdFacturaPorNombre.setString(1, nombre_factura);
			rs = psBuscarIdFacturaPorNombre.executeQuery();

			if (rs.next()) {

				id_factura = rs.getInt("facturas.id");
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar el id de la factura", e);
		} finally {
			cerrar(psBuscarIdFacturaPorNombre, rs);
		}

		return id_factura;
	}

	@Override
	public void update(Articulo articulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Articulo articulo) {
		// TODO Auto-generated method stub

	}

}
