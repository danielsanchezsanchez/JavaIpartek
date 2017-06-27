package org.tiposDAOBaseDeDatos;

public class DAOBaseDeDatosException extends RuntimeException {

	private static final long serialVersionUID = 3896032631825574428L;

	// Constructores de la clase padre
	// Generamos Serial ID sobre DAOException

	public DAOBaseDeDatosException() {
		super();

	}

	public DAOBaseDeDatosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DAOBaseDeDatosException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOBaseDeDatosException(String message) {
		super(message);

	}

	public DAOBaseDeDatosException(Throwable cause) {
		super(cause);

	}

}
