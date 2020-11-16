package io.chree.vamzer;

import io.chree.vamzer.checker.SecretCheckResult;
import io.chree.vamzer.checker.SecretChecker;

import java.util.List;
import java.util.Map;

import static io.chree.vamzer.util.Guards.nonNull;
import static java.util.Collections.emptyList;

/**
 * The global entry point of the Vamzer library.
 */
public final class Vamzer {
    /**
     * Creates a new {@code SecretChecker} that will sequentially apply
     * all checkers in the {@code checkers} list until one of them
     * detects a secret. See {@link Checkers} for the available checkers.
     * @param checkers the list of checkers to apply
     * @throws NullPointerException if {@code checkers} is {@code null}
     * @return a new {@code SecretChecker} instance
     */
    public static SecretChecker usingMultipleCheckers(final List<SecretChecker> checkers) {
        return nonNull(checkers, "checkers").isEmpty()
                ? new NullChecker()
                : new ChainingChecker(checkers);
    }

    /**
     * Entry point to easily discover and create new instances for the
     * secret checkers offered by Vamzer.
     */
    public static final class Checkers {
        /**
         * Creates a list containing a new, default-configured
         * ({@link io.chree.vamzer.checker.SecretCheckerFactory#createDefault()}) instance
         * for every checker type contained in Vamzer.
         *
         * The order of checkers in the returned list is pre-configured to make
         * optimal use of hints.
         * @return a list of new, default-configured checkers
         */
        public static List<SecretChecker> all() {
            return emptyList();
        }

        /**
         * This class is not instantiatable.
         */
        private Checkers() {}
    }

    /**
     * This class is not instantiatable.
     */
    private Vamzer() {}

    private static final class NullChecker implements SecretChecker {
        @Override
        public SecretCheckResult checkIfSecret(final String value, final Map<String, Object> hints) {
            return SecretCheckResult.noSecretDetected(value, hints);
        }
    }

    private static final class ChainingChecker implements SecretChecker {
        private final List<SecretChecker> checkerChain;

        private ChainingChecker(final List<SecretChecker> checkerChain) {
            this.checkerChain = checkerChain;
        }

        @Override
        public SecretCheckResult checkIfSecret(final String value, final Map<String, Object> hints) {
            var result = SecretCheckResult.noSecretDetected(value, hints);

            for (final var checker : checkerChain) {
                result = checker.checkIfSecret(result.getCheckedValue(), result.getHints());
            }

            return result;
        }
    }
}
