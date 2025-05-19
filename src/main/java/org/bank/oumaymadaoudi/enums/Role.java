package org.bank.oumaymadaoudi.enums;

/**
 * Enum representing user roles in the system.
 */
public enum Role {
    ROLE_CLIENT,    // Can view their own credits and repayments
    ROLE_EMPLOYEE,  // Can manage credits and view customer information
    ROLE_ADMIN      // Full access to all features
}