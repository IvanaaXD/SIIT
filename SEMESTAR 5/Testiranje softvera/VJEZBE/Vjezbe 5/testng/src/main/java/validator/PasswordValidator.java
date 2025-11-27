package validator;

public class PasswordValidator {
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        // Check for at least one uppercase letter.
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check for at least one lowercase letter.
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check for at least one digit.
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check for at least one special character (e.g., @, #, $, etc.).
        return password.matches(".*[@#$%^&+=].*");
    }

    public PasswordStrength getPasswordStrength(String password) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
