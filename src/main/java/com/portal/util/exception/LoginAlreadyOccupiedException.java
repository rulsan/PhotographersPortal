package com.portal.util.exception;

/**
 * Exception thrown when specified login is already occupied
 *
 * @author Alex Vorobyov
 */
public class LoginAlreadyOccupiedException extends PhotoPortalException {

    /**
     * Exception
     */
    public LoginAlreadyOccupiedException() {
        super();
    }

    /**
     * @param message ErrorMessage
     */
    public LoginAlreadyOccupiedException(String message) {
        super(message);
    }

    /**
     * @param message ErrorMessage
     * @param cause
     */
    public LoginAlreadyOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Error
     */
    public LoginAlreadyOccupiedException(Throwable cause) {
        super(cause);
    }
}
