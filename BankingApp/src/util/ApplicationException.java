package util;

public class ApplicationException extends Exception{

	public ApplicationException(String statement){
     super(statement);
    }
	public ApplicationException(String statement,Throwable cause){
	     super(statement,cause);
	}
}
