package org.tiposDAL;

import org.tiposDeClases.Usuario;

public interface UsuariosDAL {

	public void alta(Usuario usuario);

	public void modificar(Usuario usuario);

	public void borrar(Usuario usuario);

	public Usuario[] buscarTodosLosUsuarios();

	public boolean validar(Usuario usuario);

}
