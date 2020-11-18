package io.chree.vamzer.checkers.internal

import io.chree.vamzer.checker.SecretCheckResult
import io.chree.vamzer.checker.SecretChecker

/**
 * [SecretChecker] implementation used by [io.chree.vamzer.Vamzer.usingMultipleCheckers] if
 * no checkers are provided. Will always invoke [SecretCheckResult.noSecretDetected].
 */
internal class NullChecker : SecretChecker {
    override fun checkIfSecret(value: String, hints: Map<String, Any>) =
        SecretCheckResult.noSecretDetected(value, hints)
}
