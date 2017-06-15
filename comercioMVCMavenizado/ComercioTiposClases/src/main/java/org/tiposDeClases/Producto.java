package org.tiposDeClases;

public class Producto {

	private String nombre, descripcion, errores;
	private int ID;
	private double precio;

	// Constructores

	public Producto(String nombre, int iD, double precio, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		ID = iD;
		this.precio = precio;
	}

	public Producto(String nombre, int iD, double precio) {
		super();
		this.nombre = nombre;
		ID = iD;
		this.precio = precio;
	}

	public Producto() {
		this("", 0, 0.0, "");
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

	public double getPrecio() {
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

	public void setPrecio(double precio) {
		this.precio = precio;
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
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}

	// To String
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", errores=" + errores + ", ID=" + ID + ", precio=" + precio + "]";
	}
}
