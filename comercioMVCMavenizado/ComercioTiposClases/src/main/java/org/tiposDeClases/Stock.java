package org.tiposDeClases;

public class Stock {
	
	private Producto producto;
	
	private int stock, id_producto;
	
	private String errores;

	//Constructores
	public Stock(int id_producto, int stock) {
		super();
		this.stock = stock;
		this.id_producto = id_producto;
	}
	
	public Stock(int id_producto, String errores) {
		super();
		this.id_producto = id_producto;
		this.errores = errores;
	}

	public Stock(Producto producto, int stock) {
		super();
		this.producto = producto;
		this.stock = stock;
	}

	public Stock() {
		super();
	}
	
	//Getters y Setters
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	//Hascode y equal
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + stock;
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
		Stock other = (Stock) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	//ToString
	@Override
	public String toString() {
		return "Stock [producto=" + producto + ", stock=" + stock + "]";
	}
	
}
