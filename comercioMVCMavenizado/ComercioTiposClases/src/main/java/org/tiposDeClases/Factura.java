package org.tiposDeClases;

import java.util.Date;

public class Factura {

	private String numero_factura, errores;
	private int id, id_usuario;
	private Date fecha;

	private Usuario usuario;

	// Constructores
	public Factura(String numero_factura, int id, int id_usuario, Date fecha) {
		super();
		this.numero_factura = numero_factura;
		this.id = id;
		this.id_usuario = id_usuario;
		this.fecha = fecha;
	}

	public Factura() {

	}

	public Factura(String numero_factura, int id_usuario) {
		super();
		this.numero_factura = numero_factura;
		this.id_usuario = id_usuario;
	}

	public Factura(String numero_factura, String errores) {
		super();
		this.numero_factura = numero_factura;
		this.errores = errores;
	}

	public Factura(int id, String numero_factura, int id_usuario) {
		super();
		this.numero_factura = numero_factura;
		this.id = id;
		this.id_usuario = id_usuario;
	}

	// Getters and Setters
	public String getNumero_factura() {
		return numero_factura;
	}

	public int getId() {
		return id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDia() {
		return fecha.toString().split(" ")[0];
	}

	public String getHora() {
		return fecha.toString().split(" ")[1];
	}

	// Hascode Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + id_usuario;
		result = prime * result + ((numero_factura == null) ? 0 : numero_factura.hashCode());
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
		Factura other = (Factura) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
			return false;
		if (id_usuario != other.id_usuario)
			return false;
		if (numero_factura == null) {
			if (other.numero_factura != null)
				return false;
		} else if (!numero_factura.equals(other.numero_factura))
			return false;
		return true;
	}

	// To String
	@Override
	public String toString() {
		return "Factura [numero_factura=" + numero_factura + ", id=" + id + ", id_usuario=" + id_usuario + ", fecha=" + fecha + "]";
	}

}
