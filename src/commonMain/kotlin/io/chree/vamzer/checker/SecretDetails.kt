package io.chree.vamzer.checker

/**
 * Detailed information regarding a secret occurrence.
 * @property type The type of the detected secret (for example: JWT, GitHub Access Token, etc.).
 *                The actual value depends on the checker implementation emitting this object.
 * @property message Human-readable diagnostic message attached to this secret occurrence.
 *                The actual value depends on the checker implementation emitting this object.
 */
data class SecretDetails(val type: String, val message: String)
