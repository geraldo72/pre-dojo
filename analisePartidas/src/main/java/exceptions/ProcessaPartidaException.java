package exceptions;

public class ProcessaPartidaException extends Exception {

	private static final long serialVersionUID = -3520205658165077959L;

	public ProcessaPartidaException(){
	
	}

	public ProcessaPartidaException(final String message){
		super(message);
	}

	public ProcessaPartidaException(final Throwable cause){
		super(cause);
	}

	public ProcessaPartidaException(final String message, final Throwable cause){
		super(message, cause);
	}

}
