package org.tiposDAL;

public class UsuarioYaExistenteDALException extends UsuariosDALException {

	private static final long serialVersionUID = 269113338297625758L;

	public UsuarioYaExistenteDALException() {
		super();
	}

	public UsuarioYaExistenteDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioYaExistenteDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioYaExistenteDALException(String message) {
		super(message);
	}

	public UsuarioYaExistenteDALException(Throwable cause) {
		super(cause);
	}

}
