package io.chree.vamzer.checker;

import java.util.Map;

import static io.chree.vamzer.util.Guards.nonNull;
import static java.util.Collections.emptyMap;

/**
 * Interface for classes checking if an arbitrary non-null string is
 * a secret of some type. Such a secret can be, for example, an access
 * token, a JWT, a public key, etc.
 *
 * Implementations of this interface are required to be thread-safe.
 */
public interface SecretChecker {
    /**
     * Checks if the specified value is a secret.
     *
     * Note, that this method does not check if {@code value} contains a secret, but
     * whether the {@code value} is a secret itself.
     *
     * When performing the check, the provided hints are used to speed up the process. Let's
     * assume that we have two checkers: the first one checks if the value is a random value
     * in Base64 encoding, while the second one checks if the value is a Base64 encoded JWT.
     * Clearly, if the first checker determines that the {@code value} is not a valid
     * Base64 string, then the second checker can re-use this knowledge and skip its checking
     * logic altogether. Supported hints are specific to implementations.
     * @param value the value to be checked
     * @param hints a map of hints altering/speeding up the check
     * @return an object containing whether {@code value} is a secret
     * @throws NullPointerException if {@code value} is {@code null}
     * @throws NullPointerException if {@code hints} is {@code null}
     */
    SecretCheckResult checkIfSecret(String value, Map<String, Object> hints);

    /**
     * Checks if the specified value is a secret.
     *
     * Note, that this method does not check if {@code value} contains a secret, but
     * whether the {@code value} is a secret itself.
     *
     * Will not use any hints to perform the check.
     * @param value the value to be checked
     * @throws NullPointerException if {@code value} is {@code null}
     */
    default SecretCheckResult checkIfSecret(final String value) {
        nonNull(value, "value");

        return checkIfSecret(value, emptyMap());
    }
}
