package io.chree.vamzer.checker

/**
 * The output of a secret check.
 * @property checkedValue the value that was examined by the checker.
 * @property hints a possibly augmented map of checking hints.
 * @property details if [checkedValue] is a secret, then this property contains more
 *                   information regarding what kind of secret it is.
 * @see io.chree.vamzer.checker.SecretChecker
 */
data class SecretCheckResult(
    val checkedValue: String,
    val hints: Map<String, Any>,
    val details: SecretDetails?
) {
    companion object {
        fun secretDetected(checkedValue: String, hints: Map<String, Any>, details: SecretDetails) =
            SecretCheckResult(checkedValue, hints, details)

        fun noSecretDetected(checkedValue: String, hints: Map<String, Any>) =
            SecretCheckResult(checkedValue, hints, null)
    }

    /**
     * Returns whether [checkedValue] is a secret.
     * @return {@code true} if the checked value is a secret, `false` otherwise.
     */
    fun isSecretDetected(): Boolean = details != null

    /**
     * Throws [SecretDetectedException] if the checked value is a secret.
     * @throws SecretDetectedException if the checked value is a secret
     */
    fun throwIfSecretDetected() {
        if (isSecretDetected()) {
            throw IllegalArgumentException()
        }
    }

    /**
     * Throws [SecretDetectedException] if the checked value is a secret.
     * @throws SecretDetectedException if the checked value is a secret.
     */
    fun snitch() = throwIfSecretDetected()
}
