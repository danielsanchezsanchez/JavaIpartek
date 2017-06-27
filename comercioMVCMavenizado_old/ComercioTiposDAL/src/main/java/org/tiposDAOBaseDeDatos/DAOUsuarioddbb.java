package org.tiposDAOBaseDeDatos;

import org.tiposDeClases.Usuario;

public interface DAOUsuarioddbb extends DAOComercioddbb {

	public Usuario[] buscarTodos();

	public int buscarElId(String nick);

	public Usuario buscarPorNick(String nickusuario);

	public int insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(int id);
}
