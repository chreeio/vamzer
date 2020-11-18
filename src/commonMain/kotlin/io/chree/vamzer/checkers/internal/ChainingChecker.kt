package io.chree.vamzer.checkers.internal

import io.chree.vamzer.checker.SecretCheckResult
import io.chree.vamzer.checker.SecretChecker

/**
 * [SecretChecker] implementation used by [io.chree.vamzer.Vamzer.usingMultipleCheckers] if
 * multiple checkers are provided.
 */
internal class ChainingChecker(val checkerChain: List<SecretChecker>) : SecretChecker {
    override fun checkIfSecret(value: String, hints: Map<String, Any>): SecretCheckResult {
        var previousResult = SecretCheckResult.noSecretDetected(value, hints)

        for (checker in checkerChain) {
            previousResult = checker.checkIfSecret(value, previousResult.hints)

            if (previousResult.isSecretDetected()) {
                break
            }
        }

        return previousResult
    }
}