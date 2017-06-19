package org.tiposDeClases;

public class Usuario {

	private int id, rol;
	private String nickusuario, nombre, apellido1, apellido2, contrasenia, errores;

	// Constructores
	public Usuario() {

	}

	public Usuario(int id, String nombre, String nickusuario, String apellido1, String apellido2, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nickusuario = nickusuario;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.contrasenia = contrasenia;
	}

	public Usuario(String nickusuario, String errores) {
		super();
		this.nickusuario = nickusuario;
		this.errores = errores;
	}

	public Usuario(int rol, String nickusuario, String nombre, String apellido1, String apellido2) {
		super();
		this.rol = rol;
		this.nickusuario = nickusuario;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public int getRol() {
		return rol;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNickusuario() {
		return nickusuario;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String getErrores() {
		return errores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNickusuario(String nickusuario) {
		this.nickusuario = nickusuario;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	// Equals HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result + ((apellido2 == null) ? 0 : apellido2.hashCode());
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + id;
		result = prime * result + ((nickusuario == null) ? 0 : nickusuario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + rol;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (errores == null) {
			if (other.errores != null)
				return false;
		} else if (!errores.equals(other.errores))
			return false;
		if (id != other.id)
			return false;
		if (nickusuario == null) {
			if (other.nickusuario != null)
				return false;
		} else if (!nickusuario.equals(other.nickusuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}

	// To String
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rol=" + rol + ", nombre=" + nombre + ", nickusuario=" + nickusuario + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", contrasenia=" + contrasenia
				+ "]";
	}

}
