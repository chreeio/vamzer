package io.chree.vamzer.checker;

import io.chree.vamzer.util.Guards;

/**
 * Exception thrown if a checker determined the specified value to be a secret. Note, that
 * this exception is not thrown automatically.
 * @see SecretCheckResult#throwIfSecretDetected()
 */
public final class SecretDetectedException extends RuntimeException {
    private final String checkedValue;

    private final SecretDetails details;

    /**
     * Constructs a new instance from the checked value and the secret details.
     * @param checkedValue the checked value that is a secret
     * @param details the detail object
     * @throws NullPointerException if any of the arguments is {@code null}
     */
    public SecretDetectedException(final String checkedValue, final SecretDetails details) {
        super("The checked value is a secret of type " + details.getType() + ".");

        this.checkedValue = Guards.nonNull(checkedValue, "checkedValue");
        this.details = Guards.nonNull(details, "details");
    }

    /**
     * Gets the value that is a secret.
     * @return the value
     */
    public String getCheckedValue() {
        return checkedValue;
    }

    /**
     * Gets the detail object containing more information regarding the secret within the checked value.
     * @return the detail object
     */
    public SecretDetails getDetails() {
        return details;
    }
}
