package com.portal.util.exception;

/**
 * Base project exception class
 * Date: 22.11.13
 */
public class PhotoPortalException extends Exception {

    /**
     * Exception
     */
    public PhotoPortalException() {
        super();
    }

    /**
     * @param message ErrorMessage
     */
    public PhotoPortalException(String message) {
        super(message);
    }

    /**
     * @param message ErrorMessage
     * @param cause
     */
    public PhotoPortalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Error
     */
    public PhotoPortalException(Throwable cause) {
        super(cause);
    }
}
