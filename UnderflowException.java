//class for underflow exception for Weiss BST code
public class UnderflowException extends RuntimeException {
	public UnderflowException() 
	{}

	public UnderflowException(String message) {
		
		super(message);
	}
}