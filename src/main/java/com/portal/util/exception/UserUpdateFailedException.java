package com.portal.util.exception;

/**
 * UserUpdateFailedException
 *
 * @author SkillsUpPracticeTeam
 */
public class UserUpdateFailedException extends PhotoPortalException {

    /**
     * Exception
     */
    public UserUpdateFailedException() {
        super();
    }

    /**
     * @param message ErrorMessage
     */
    public UserUpdateFailedException(String message) {
        super(message);
    }

    /**
     * @param message ErrorMessage
     * @param cause
     */
    public UserUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Error
     */
    public UserUpdateFailedException(Throwable cause) {
        super(cause);
    }
}
