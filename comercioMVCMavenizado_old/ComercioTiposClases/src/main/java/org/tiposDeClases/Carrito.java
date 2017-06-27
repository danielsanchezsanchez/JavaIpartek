package org.tiposDeClases;

public class Carrito {
	
	private int id, id_factura, id_producto, cantidad;
	
	//Constructores
	
	public Carrito(int id, int id_factura, int id_producto, int cantidad) {
		super();
		this.id = id;
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}
	
	public Carrito(){
		
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	//EQUALS y HASCODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + id;
		result = prime * result + id_factura;
		result = prime * result + id_producto;
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
		Carrito other = (Carrito) obj;
		if (cantidad != other.cantidad)
			return false;
		if (id != other.id)
			return false;
		if (id_factura != other.id_factura)
			return false;
		if (id_producto != other.id_producto)
			return false;
		return true;
	}

	//TO String
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", id_factura=" + id_factura
				+ ", id_producto=" + id_producto + ", cantidad=" + cantidad
				+ "]";
	}
}
