package io.chree.vamzer.checker

/**
 * Interface for factories creating default and custom-configured [SecretChecker]
 * instances.
 * @param Checker the type of the created checkers.
 * @param Options the type of the configurable checker options.
 */
interface SecretCheckerFactory<Checker, Options> {
    /**
     * The default options used when calling the [createDefault] method to
     * obtain a new checker instance. If a checker has no options, then
     * the value of this property is `null`.
     */
    val defaultOptions: Options?

    /**
     * Creates a new checker instance using the options returned by [defaultOptions].
     * @return a new checker instance with default configuration.
     */
    fun createDefault(): Checker

    /**
     * Creates a new checker instance using the provided options. If the checker has no
     * configurable options, then will always return the same result as [createDefault].
     * @param options custom configuration options to be used by the checker.
     * @return a new checker instance using the specified options.
     */
    fun createConfigured(options: Options): Checker
}