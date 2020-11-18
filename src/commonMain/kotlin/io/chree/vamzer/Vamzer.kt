package io.chree.vamzer

import io.chree.vamzer.checker.SecretChecker
import io.chree.vamzer.checkers.internal.ChainingChecker
import io.chree.vamzer.checkers.internal.NullChecker

/**
 * The global entry point of the Vamzer library.
 */
object Vamzer {
    /**
     * Creates a new [SecretChecker] that will sequentially apply
     * all checkers in the [checkers] list until one of them
     * detects a secret. See [Checkers] for the available checkers.
     * @param checkers the list of checkers to apply
     * @return a new [SecretChecker] instance
     */
    fun usingMultipleCheckers(checkers: List<SecretChecker>): SecretChecker =
        if (checkers.isEmpty())
            NullChecker()
        else
            ChainingChecker(checkers)

    /**
     * Entry point to easily discover and create new instances for the
     * secret checkers offered by Vamzer.
     */
    object Checkers {
        /**
         * Creates a list containing a new, default-configured
         * (see [io.chree.vamzer.checker.SecretCheckerFactory.createDefault]) instance
         * for every checker type contained in Vamzer.
         *
         * The order of checkers in the returned list is pre-configured to make
         * optimal use of hints.
         * @return a list of new, default-configured checkers.
         */
        fun all(): List<SecretChecker> = emptyList()
    }
}
