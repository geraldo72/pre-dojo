package exceptions;

public class LeituraLogException extends Exception {
	
	private static final long serialVersionUID = -8027768698672157515L;

	public LeituraLogException(){
		
	}
	
	public LeituraLogException(final String message){
		super(message);
	}
	
	public LeituraLogException(final Throwable cause){
		super(cause);
	}
	
	public LeituraLogException(final String message, final Throwable cause){
		super(message, cause);
	}
}
