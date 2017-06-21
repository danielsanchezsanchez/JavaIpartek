package org.tiposDeClases;

public class Articulo {

	private int id_producto, cantidad;

	// Constructores

	public Articulo(int id_producto, int cantidad) {
		super();
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}

	public Articulo() {

	}

	// Getters y Setters

	public int getId_producto() {
		return id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// To String
	@Override
	public String toString() {
		return "Carrito [id_producto=" + id_producto + ", cantidad=" + cantidad + "]";
	}

}
