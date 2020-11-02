package pl.coderslab.charity.exeption;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Object with ID: " + id + " not found!");
    }
}
