package org.tiposDeClases;

public class Articulos {

	private int id_usuario, id_producto, cantidad;

	// Constructores
	public Articulos(int id_usuario, int id_producto) {
		super();
		this.id_usuario = id_usuario;
		this.id_producto = id_producto;
	}

	public Articulos() {

	}

	// Getters y Setters
	public int getId_usuario() {
		return id_usuario;
	}

	public int getId_producto() {
		return id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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
		return "Carrito [id_usuario=" + id_usuario + ", id_producto=" + id_producto + ", cantidad=" + cantidad + "]";
	}

}
