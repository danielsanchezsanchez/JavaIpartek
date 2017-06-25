package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.tiposDeClases.Usuario;

public class DAOUsuarioddbbMySQL extends DAOComercioddbbMySQL implements DAOUsuarioddbb {

	private final static String BUSCAR_TODOS = "SELECT * FROM usuarios";
	private final static String BUSCAR_POR_NICK = "SELECT * FROM usuarios WHERE nickusuario LIKE ?";
	private final static String BUSCAR_EL_ID = "SELECT id FROM usuarios WHERE nickusuario LIKE ?";
	private final static String INSERT = "INSERT INTO usuarios (id_rol, nickusuario, nombre, apellido1, apellido2, contrasenia)" + " VALUES (?, ?, ?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE usuarios " + "SET id_rol = ?, nickusuario = ?, nombre = ?, apellido1 = ?, apellido2 = ? " + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM usuarios WHERE id = ?";

	private PreparedStatement psBuscarPorNick, psBuscarElId, psInsert, psBuscarTodos, psUpdate, psDelete;

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
			throw new DAOBaseDeDatosException("Error en cerrar con 2 parametros", e);
		}
	}

	@Override
	public Usuario[] buscarTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = null;
		try {

			psBuscarTodos = con.prepareStatement(BUSCAR_TODOS);
			rs = psBuscarTodos.executeQuery();

			Usuario usuario;

			while (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getInt("id"));
				usuario.setRol(rs.getInt("id_rol"));
				usuario.setNickusuario(rs.getString("nickusuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en Buscar Todos", e);
		} finally {
			cerrar(psBuscarTodos, rs);
		}
		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	@Override
	public int buscarElId(String nick) {
		ResultSet rs = null;
		int i = 0;
		try {
			psBuscarElId = con.prepareStatement(BUSCAR_EL_ID);
			psBuscarElId.setString(1, nick);
			rs = psBuscarElId.executeQuery();

			if (rs.next()) {
				i = rs.getInt("id");
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findById", e);
		} finally {
			cerrar(psBuscarElId, rs);
		}

		return i;
	}

	@Override
	public Usuario buscarPorNick(String nickusuario) {
		Usuario usuario = null;
		ResultSet rs = null;
		try {
			psBuscarPorNick = con.prepareStatement(BUSCAR_POR_NICK);
			psBuscarPorNick.setString(1, nickusuario);
			rs = psBuscarPorNick.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getInt("id"));
				usuario.setRol(rs.getInt("id_rol"));
				usuario.setNickusuario(rs.getString("nickusuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));
				usuario.setContrasenia(rs.getString("contrasenia"));
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findById", e);
		} finally {
			cerrar(psBuscarPorNick, rs);
		}

		return usuario;
	}

	@Override
	public int insert(Usuario usuario) {
		try {

			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setInt(1, usuario.getRol());
			psInsert.setString(2, usuario.getNickusuario());
			psInsert.setString(3, usuario.getNombre());
			psInsert.setString(4, usuario.getApellido1());
			psInsert.setString(5, usuario.getApellido2());
			psInsert.setString(6, usuario.getContrasenia());

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
	public void update(Usuario usuario) {
		try {

			psUpdate = con.prepareStatement(UPDATE);
			psUpdate.setInt(1, usuario.getRol());
			psUpdate.setString(2, usuario.getNickusuario());
			psUpdate.setString(3, usuario.getNombre());
			psUpdate.setString(4, usuario.getApellido1());
			psUpdate.setString(5, usuario.getApellido2());

			psUpdate.setInt(6, usuario.getId());

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
	public void delete(Usuario usuario) {
		delete(usuario.getId());

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
