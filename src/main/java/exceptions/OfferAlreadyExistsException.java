package exceptions;

public class OfferAlreadyExistsException extends RuntimeException {
    public OfferAlreadyExistsException(String message) {
        super(message);
    }
}

