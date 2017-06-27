package org.tiposDAOBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOComercioddbbMySQL implements DAOComercioddbb {

	protected Connection con;
	private String url = "jdbc:mysql://localhost/comercioddbb";
	private String mysqlUser = "root";
	private String mysqlPass = "";

	public DAOComercioddbbMySQL(String url, String mysqlUser, String mysqlPass) {
		this();
		this.url = url;
		this.mysqlUser = mysqlUser;
		this.mysqlPass = mysqlPass;
	}

	public DAOComercioddbbMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			throw new DAOBaseDeDatosException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DAOBaseDeDatosException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new DAOBaseDeDatosException("No se ha encontrado el driver de MySQL", e);
		} catch (Exception e) {
			throw new DAOBaseDeDatosException("ERROR NO ESPERADO", e);
		}
	}

	@Override
	public void abrirComercioddbb() {
		try {
			con = DriverManager.getConnection(url, mysqlUser, mysqlPass);
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOBaseDeDatosException("ERROR NO ESPERADO", e);
		}
	}

	@Override
	public void cerrarComercioddbb() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
			con = null;
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error de cierre de conexión a la base de datos", e);
		} catch (Exception e) {
			throw new DAOBaseDeDatosException("ERROR NO ESPERADO", e);
		}
	}

	@Override
	public void iniciarTransaccion() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error al desactivar AutoCommit", e);
		}

	}

	@Override
	public void confirmarTransaccion() {
		try {
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error al confirmar transacción", e);
		}
	}

	@Override
	public void deshacerTransaccion() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOBaseDeDatosException("Error al deshacer transacción", e);
		}

	}

}
