package demo;

public class MyBEx extends Exception {
    public MyBEx() {
        super();
    }

    public MyBEx(String message) {
        super(message);
    }

    public MyBEx(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBEx(Throwable cause) {
        super(cause);
    }

    protected MyBEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
