package com.favourite.collections.commons.useradmin.enums;

/**
 * Enum representing different types of email notifications in the system.
 * All notifications use OTP-based verification.
 */
public enum NotificationType {
    // OTP-based notifications
    VERIFICATION_OTP,      // Initial email verification with OTP
    PASSWORD_RESET_OTP,    // Password reset email with OTP

    // Non-OTP notifications
    WELCOME,              // Welcome email after successful verification
    PASSWORD_CHANGED
}
