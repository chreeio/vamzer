package io.chree.vamzer.checker;

/**
 * Interface for factories creating default and custom-configured {@link SecretChecker}
 * instances.
 * @param <Checker> The type of the created checkers.
 * @param <Options> The type of the configurable checker options.
 */
public interface SecretCheckerFactory<Checker, Options> {
    /**
     * Gets the default options used when calling the {@link #createDefault()} method to
     * obtain a new checker instance.
     *
     * If a checker has no options, then this method returns {@code null}.
     * @return the options used when creating a default checker instance or {@code null}
     */
    default Options getDefaultOptions() {
        return null;
    }

    /**
     * Creates a new checker instance using the options returned by {@link #getDefaultOptions()}.
     * @return a new checker instance with default configuration
     */
    Checker createDefault();

    /**
     * Creates a new checker instance using the provided options. If the checker has no
     * configurable options, then will always return the same result as {@link #createDefault()}.
     * @param options custom configuration options to be used by the checker
     * @return a new checker instance using the specified options
     */
    Checker createConfigured(Options options);
}
