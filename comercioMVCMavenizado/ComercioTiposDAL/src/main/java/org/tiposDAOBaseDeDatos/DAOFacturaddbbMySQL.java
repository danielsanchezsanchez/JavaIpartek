package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.tiposDeClases.Factura;
import org.tiposDeClases.Usuario;

public class DAOFacturaddbbMySQL extends DAOComercioddbbMySQL implements DAOFacturaddbb {

	// private static Logger log = Logger.getLogger(DAOFacturaddbbMySQL.class);
	private final static String BUSCAR_ULTIMO_NUMERO_FACTURA = "SELECT numero_factura FROM facturas ORDER BY id DESC LIMIT 1";
	private final static String BUSCAR_TODAS = "SELECT * FROM facturas";
	private final static String BUSCAR_TODAS_CON_USUARIOS = "SELECT facturas.id, facturas.numero_factura, usuarios.nickusuario, facturas.fecha_compra FROM facturas, usuarios WHERE usuarios.id=facturas.id_usuario ORDER BY facturas.numero_factura";
	private final static String BUSCAR_POR_NUMERO = "SELECT * FROM facturas WHERE numero_factura LIKE ?";
	private final static String BUSCAR_TODAS_POR_USUARIO = "SELECT * FROM facturas WHERE id_usuario LIKE ?";
	private final static String INSERT = "INSERT INTO facturas (numero_factura, id_usuario)" + " VALUES (?, ?)";
	private final static String UPDATE = "UPDATE facturas " + "SET numero_factura = ?, id_usuario = ? " + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM facturas WHERE id = ?";

	private PreparedStatement psBuscarTodas, psBuscarUltima, psBuscarTodasConUsuarios, psBuscarTodasPorUsuario, psBuscarPorNumero, psInsert, psUpdate, psDelete;

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
	public Factura[] buscarTodasPorUsuario(int id_usuario) {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		Factura factura = null;
		ResultSet rs = null;
		try {
			psBuscarTodasPorUsuario = con.prepareStatement(BUSCAR_TODAS_POR_USUARIO);
			psBuscarTodasPorUsuario.setInt(1, id_usuario);
			rs = psBuscarTodasPorUsuario.executeQuery();

			while (rs.next()) {
				factura = new Factura();

				factura.setId(rs.getInt("id"));
				factura.setNumero_factura(rs.getString("numero_factura"));
				factura.setId_usuario(rs.getInt("id_usuario"));
				factura.setFecha(rs.getTimestamp("fecha_compra"));
				// log.info("Formato de fecha: " +
				// rs.getTimestamp("fecha_compra"));
				facturas.add(factura);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findById", e);
		} finally {
			cerrar(psBuscarTodasPorUsuario, rs);
		}

		return facturas.toArray(new Factura[facturas.size()]);
	}

	@Override
	public String buscarUltima() {
		String numero_factura = null;
		ResultSet rs = null;
		try {
			psBuscarUltima = con.prepareStatement(BUSCAR_ULTIMO_NUMERO_FACTURA);
			rs = psBuscarUltima.executeQuery();

			if (rs.next()) {

				numero_factura = rs.getString("facturas.numero_factura");
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar ultima", e);
		} finally {
			cerrar(psBuscarUltima, rs);
		}

		return numero_factura;
	}

	@Override
	public Factura[] buscarTodas() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ResultSet rs = null;
		try {

			psBuscarTodas = con.prepareStatement(BUSCAR_TODAS);
			rs = psBuscarTodas.executeQuery();

			Factura factura;

			while (rs.next()) {
				factura = new Factura();

				factura.setId(rs.getInt("id"));
				factura.setNumero_factura(rs.getString("numero_factura"));
				factura.setId_usuario(rs.getInt("id_usuario"));
				factura.setFecha(rs.getTimestamp("fecha_compra"));
				facturas.add(factura);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar todas", e);
		} finally {
			cerrar(psBuscarTodas, rs);
		}
		return facturas.toArray(new Factura[facturas.size()]);
	}

	@Override
	public Factura[] buscarTodasConUsuarios() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ResultSet rs = null;
		try {

			psBuscarTodasConUsuarios = con.prepareStatement(BUSCAR_TODAS_CON_USUARIOS);
			rs = psBuscarTodasConUsuarios.executeQuery();

			Factura factura;
			Usuario usuario;

			while (rs.next()) {
				factura = new Factura();
				usuario = new Usuario();

				factura.setId(rs.getInt("facturas.id"));
				factura.setNumero_factura(rs.getString("facturas.numero_factura"));
				usuario.setNickusuario(rs.getString("usuarios.nickusuario"));
				factura.setUsuario(usuario);
				factura.setFecha(rs.getTimestamp("facturas.fecha_compra"));

				facturas.add(factura);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar todas", e);
		} finally {
			cerrar(psBuscarTodasConUsuarios, rs);
		}
		return facturas.toArray(new Factura[facturas.size()]);
	}

	@Override
	public Factura buscarPorId(int id) {

		return null;
	}

	@Override
	public Factura buscarPorNumero(String numero_factura) {
		Factura factura = null;
		ResultSet rs = null;
		try {
			psBuscarPorNumero = con.prepareStatement(BUSCAR_POR_NUMERO);
			psBuscarPorNumero.setString(1, numero_factura);
			rs = psBuscarPorNumero.executeQuery();

			if (rs.next()) {
				factura = new Factura();

				factura.setId(rs.getInt("facturas.id"));
				factura.setNumero_factura(rs.getString("facturas.numero_factura"));
				factura.setId_usuario(rs.getInt("id_usuario"));
				factura.setFecha(rs.getTimestamp("facturas.fecha_compra"));
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findById", e);
		} finally {
			cerrar(psBuscarPorNumero, rs);
		}

		return factura;
	}

	@Override
	public int insert(Factura factura) {
		try {

			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, factura.getNumero_factura());
			psInsert.setInt(2, factura.getId_usuario());

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
	public void update(Factura factura) {
		try {

			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, factura.getNumero_factura());
			psUpdate.setInt(2, factura.getId_usuario());

			psUpdate.setInt(3, factura.getId());

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
	public void delete(Factura factura) {
		delete(factura.getId());

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
