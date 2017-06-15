package org.tiposDAL;

public class UsuariosDALException extends RuntimeException {

	private static final long serialVersionUID = -5703554170858759756L;

	public UsuariosDALException() {
		super();
	}

	public UsuariosDALException(String message) {
		super(message);
	}

	public UsuariosDALException(Throwable cause) {
		super(cause);
	}

	public UsuariosDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuariosDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
