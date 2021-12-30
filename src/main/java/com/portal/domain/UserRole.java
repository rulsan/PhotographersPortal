package com.portal.domain;

/**
 * Date: 03.12.13
 */

public enum UserRole {
    /**
     * Not logged users
     */
    ROLE_ANONYMOUS,
    /**
     * Default user
     */
    ROLE_USER,
    /**
     * User that can manage other users
     */
    ROLE_ADMIN;
}
