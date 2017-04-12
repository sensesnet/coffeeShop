package servicesException;

public class ServicesException extends Exception {

    public ServicesException() {
    }

    public ServicesException(String message) {
        super(message);
    }

    public ServicesException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServicesException(Throwable throwable) {
        super(throwable);
    }

    public ServicesException(String message, Throwable throwable, boolean b, boolean b1) {
        super(message, throwable, b, b1);
    }
}
