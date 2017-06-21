package org.tiposDAL;

public class CarritoDALException extends RuntimeException {

	private static final long serialVersionUID = -500096276038021239L;

	public CarritoDALException() {
		super();
	}

	public CarritoDALException(String message) {
		super(message);
	}

	public CarritoDALException(Throwable cause) {
		super(cause);
	}

	public CarritoDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarritoDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
