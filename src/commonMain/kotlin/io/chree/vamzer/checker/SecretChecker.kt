package io.chree.vamzer.checker

/**
 * Interface for classes checking if an arbitrary non-null string is
 * a secret of some type. Such a secret can be, for example, an access
 * token, a JWT, a public key, etc.
 *
 * Implementations of this interface are required to be thread-safe.
 */
interface SecretChecker {
    /**
     * Checks if the specified value is a secret.
     *
     * Note, that this method does not check if [value] contains a secret, but
     * whether the [value] is a secret itself.
     *
     * When performing the check, the provided [hints] are used to speed up the process. Let's
     * assume that we have two checkers: the first one checks if the [value] is a random value
     * in Base64 encoding, while the second one checks if the [value] is a Base64 encoded JWT.
     * Clearly, if the first checker determines that the [value] is not a valid
     * Base64 string, then the second checker can re-use this knowledge and skip its checking
     * logic altogether. Supported [hints] are specific to implementations.
     * @param value the value to be checked.
     * @param hints a map of hints altering/speeding up the check.
     * @return an object containing whether [value] is a secret
     */
    fun checkIfSecret(value: String, hints: Map<String, Any> = emptyMap()): SecretCheckResult
}