package io.chree.vamzer.checker

/**
 * Exception thrown if a checker determined the specified value to be a secret. Note, that
 * this exception is not thrown automatically.
 * @property checkedValue The value that was determined to be a secret.
 * @property details Detailed information regarding what kind of secret [checkedValue] is.
 * @see SecretCheckResult.throwIfSecretDetected
 */
class SecretDetectedException(var checkedValue: String, val details: SecretDetails) :
    RuntimeException("The checked value is a secret of type \"${details.type}\".")
