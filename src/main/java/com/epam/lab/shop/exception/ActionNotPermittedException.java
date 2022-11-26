package com.epam.lab.shop.exception;

public class ActionNotPermittedException extends Exception{

    private static final long serialVersionUID = -6206471460901689130L;

    public ActionNotPermittedException() {
        super();
    }

    public ActionNotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotPermittedException(String message) {
        super(message);
    }
}
