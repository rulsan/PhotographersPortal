package com.portal.util.exception;

/**
 * Date: 22.11.13
 */
public class EmailAlreadyOccupiedException extends PhotoPortalException {

    /**
     * Exception
     */
    public EmailAlreadyOccupiedException() {
        super();
    }

    /**
     * @param message ErrorMessage
     */
    public EmailAlreadyOccupiedException(String message) {
        super(message);
    }

    /**
     * @param message ErrorMessage
     * @param cause
     */
    public EmailAlreadyOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Error
     */
    public EmailAlreadyOccupiedException(Throwable cause) {
        super(cause);
    }
}
