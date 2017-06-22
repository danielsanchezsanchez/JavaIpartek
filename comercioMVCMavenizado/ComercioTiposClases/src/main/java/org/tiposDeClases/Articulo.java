package org.tiposDeClases;

public class Articulo {

	private int id_producto, cantidad;
	private Producto producto;

	// Constructores
	public Articulo(int id_producto, int cantidad, Producto producto) {
		super();
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.producto = producto;
	}

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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	// To String
	@Override
	public String toString() {
		return "Articulo [id_producto=" + id_producto + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}

}
