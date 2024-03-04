/**
 * Exception thrown when attempting an operation on an empty data structure.
 */
public class UnderflowException extends RuntimeException {
    /**
     * Constructs an UnderflowException with no detail message.
     */
    public UnderflowException() {
        super();
    }

    /**
     * Constructs an UnderflowException with the specified detail message.
     * @param message the detail message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}
