package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.tiposDeClases.Usuario;

public class DAOUsuarioddbbMySQL extends DAOComercioddbbMySQL implements DAOUsuarioddbb {

	private final static String BUSCAR_TODOS = "SELECT * FROM productos";
	private final static String BUSCAR_POR_NICK = "SELECT * FROM usuarios WHERE nickusuario LIKE ?";
	private final static String INSERT = "INSERT INTO usuarios (id_rol, nickusuario, nombre, apellido1, apellido2, contrasenia)" + " VALUES (?, ?, ?, ?, ?, ?)";

	private PreparedStatement psBuscarPorNick, psInsert, psBuscarTodos;

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

	public DAOUsuarioddbbMySQL() {
		// TODO Auto-generated constructor stub
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
				usuario.setRol(rs.getInt("rol"));
				usuario.setNickusuario(rs.getString("nickusuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error en findAll", e);
		} finally {
			cerrar(psBuscarTodos, rs);
		}
		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	@Override
	public Usuario buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
