package org.tiposDeClases;

import java.math.BigDecimal;

public class Producto {

	private String nombre, descripcion, errores, url_producto_img;
	private int ID, cantidad;
	private BigDecimal precio;

	// Constructores

	public Producto(int iD, String nombre, BigDecimal precio, String descripcion, String url_producto_img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url_producto_img = url_producto_img;
		ID = iD;
		this.precio = precio;
	}

	public Producto(String nombre, int iD, BigDecimal precio, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		ID = iD;
		this.precio = precio;
	}

	public Producto(String nombre, int iD, BigDecimal precio) {
		super();
		this.nombre = nombre;
		ID = iD;
		this.precio = precio;
	}

	public Producto(String nombre, BigDecimal precio, String descripcion, String url_producto_img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url_producto_img = url_producto_img;
		this.precio = precio;
	}

	public Producto() {

	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getErrores() {
		return errores;
	}

	public int getID() {
		return ID;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getUrl_producto_img() {
		return url_producto_img;
	}

	public void setUrl_producto_img(String url_producto_img) {
		this.url_producto_img = url_producto_img;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// HashCode y Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((url_producto_img == null) ? 0 : url_producto_img.hashCode());
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
		Producto other = (Producto) obj;
		if (ID != other.ID)
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (errores == null) {
			if (other.errores != null)
				return false;
		} else if (!errores.equals(other.errores))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (url_producto_img == null) {
			if (other.url_producto_img != null)
				return false;
		} else if (!url_producto_img.equals(other.url_producto_img))
			return false;
		return true;
	}

	// To String
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", errores=" + errores + ", url_producto_img=" + url_producto_img + ", ID=" + ID + ", precio=" + precio + "]";
	}
}
