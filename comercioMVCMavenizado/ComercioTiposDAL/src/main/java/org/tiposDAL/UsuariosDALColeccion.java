package org.tiposDAL;

import java.util.TreeMap;

import org.tiposDeClases.Usuario;

public class UsuariosDALColeccion implements UsuariosDAL {
	private TreeMap<Integer, Usuario> usuarios = new TreeMap<Integer, Usuario>();

	@Override
	public void alta(Usuario usuario) {
		if (usuarios.isEmpty()) {
			usuario.setId(1);
		} else {
			int ultimoId = (usuarios.lastKey() + 1);
			usuario.setId(ultimoId);
		}
		usuarios.put(usuario.getId(), usuario);
	}

	@Override
	public void modificar(Usuario usuario) {
		if (!usuarios.containsKey(usuario.getId()))
			throw new UsuariosDALException("Intento de modificar usuario no existente " + usuario);

		usuarios.put(usuario.getId(), usuario);
	}

	@Override
	public void borrar(Usuario usuario) {
		usuarios.remove(usuario.getId());
	}

	@Override
	public boolean validar(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}

	@Override
	public Usuario[] buscarTodosLosUsuarios() {

		return usuarios.values().toArray(new Usuario[usuarios.size()]);
	}

}
