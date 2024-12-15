package com.example.myapplication.util;

/**
 * Indicates that the application is in a state which is illegal according to its design. This is
 * probably caused by an implementation error.
 *
 * @author Felix Wiemuth
 */
public class ImplementationError extends RuntimeException {
    public ImplementationError() {
    }

    public ImplementationError(String message) {
        super(message);
    }

    public ImplementationError(String message, Throwable cause) {
        super(message, cause);
    }

    public ImplementationError(Throwable cause) {
        super(cause);
    }
}
