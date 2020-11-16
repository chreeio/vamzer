package io.chree.vamzer;

import io.chree.vamzer.checker.SecretChecker;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * The global entry point of the Vamzer library.
 */
public final class Vamzer {
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
}
