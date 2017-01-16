package com.acme.edu;

/**
 * Created by eugene on 16/01/2017.
 */
public class MyBLException extends RuntimeException {
    public MyBLException() {
        super();
    }

    public MyBLException(String message) {
        super(message);
    }

    public MyBLException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBLException(Throwable cause) {
        super(cause);
    }

    protected MyBLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
