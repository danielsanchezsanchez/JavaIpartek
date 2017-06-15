package org.tiposDAOBaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.tiposDeClases.Usuario;

public class DAOUsuarioddbbMySQL extends DAOComercioddbbMySQL implements DAOUsuarioddbb {

	private final static String BUSCAR_POR_NICK = "SELECT * FROM usuarios WHERE nickusuario LIKE ?";

	private PreparedStatement psBuscarPorNick;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
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
