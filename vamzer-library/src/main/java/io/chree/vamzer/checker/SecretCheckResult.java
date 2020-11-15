package io.chree.vamzer.checker;

import java.util.Map;
import java.util.Objects;

import static io.chree.vamzer.util.Guards.nonNull;

/**
 * The output of a secret check.
 *
 * @see io.chree.vamzer.checker.SecretChecker
 */
public final class SecretCheckResult {
    private final String checkedValue;

    private final Map<String, Object> hints;

    private final SecretDetails details;

    /**
     * Creates a new {@code SecretCheckResult} with the specified details regarding a detected secret.
     * Use this method to signal that {@code checkedValue} is a secret.
     * @param checkedValue the value that is a secret
     * @param hints the possibly augmented map of hints
     * @param details detailed information regarding the secret in {@code checkedValue}
     * @return a new {@code SecretCheckResult} instance
     * @throws NullPointerException if {@code checkedValue} is {@code null}
     * @throws NullPointerException if {@code hints} is {@code null}
     * @throws NullPointerException if {@code details} is {@code null}
     */
    public static SecretCheckResult secretDetected(final String checkedValue, final Map<String, Object> hints, final SecretDetails details) {
        return new SecretCheckResult(checkedValue, hints, nonNull(details, "details"));
    }

    /**
     * Creates a new {@code SecretCheckResult} with no information regarding a contained secret.
     * Use this method to signal that {@code checkedValue} is <b>not</b> a secret.
     * @param checkedValue the value that was checked and is <b>not</b> a secret (according to the check)
     * @param hints the possibly augmented map of hints
     * @return a new {@code SecretCheckResult} instance
     * @throws NullPointerException if {@code checkedValue} is {@code null}
     * @throws NullPointerException if {@code hints} is {@code null}
     */
    public static SecretCheckResult noSecretDetected(final String checkedValue, final Map<String, Object> hints) {
        return new SecretCheckResult(checkedValue, hints, null);
    }

    private SecretCheckResult(final String checkedValue, final Map<String, Object> hints, final SecretDetails details) {
        this.checkedValue = nonNull(checkedValue, "checkedValue");
        this.hints = nonNull(hints, "hints");
        this.details = details;
    }

    /**
     * Returns whether the checked value is a secret.
     * @return {@code true} if the checked value is a secret, {@code false} otherwise
     */
    public boolean isSecretDetected() {
        return Objects.nonNull(details);
    }

    /**
     * Throws {@code SecretDetectedException} if the checked value is a secret.
     * @throws SecretDetectedException if the checked value is a secret
     */
    public void throwIfSecretDetected() {
        if (isSecretDetected()) {
            throw new SecretDetectedException(checkedValue, details);
        }
    }

    /**
     * Gets the checked value.
     * @return the checked value
     */
    public String getCheckedValue() {
        return checkedValue;
    }

    /**
     * Gets the map of hints received and possibly altered by the checking process.
     * @see SecretChecker#checkIfSecret(String, Map) 
     * @return the map of hints
     */
    public Map<String, Object> getHints() {
        return hints;
    }

    /**
     * Gets detailed information regarding the secret contained within the checked value
     * or {@code null} if the checked value is not a secret.
     * @return secret details or {@code null}
     */
    public SecretDetails getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "SecretCheckResult{" +
                "hints=" + hints +
                ", details=" + details +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SecretCheckResult that = (SecretCheckResult) o;
        return checkedValue.equals(that.checkedValue) &&
                hints.equals(that.hints) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkedValue, hints, details);
    }
}
