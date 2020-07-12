package com.myretail.exception;


public class MyRetailException extends RuntimeException {

    public MyRetailException() {
        super();
    }

    public MyRetailException(String message) {
        super(message);
    }

    public MyRetailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRetailException(Throwable cause) {
        super(cause);
    }

    protected MyRetailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
