package io.chree.vamzer.util;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility class holding various validation and testing methods.
 */
public final class Guards {
    /**
     * Makes sure that the specified value satisfies a given condition and throws otherwise.
     * @param value the value to be checked
     * @param predicate The condition the value has to meet. If the return value of this predicate is false, then the
     *                  {@code exceptionSupplier} is called.
     * @param exceptionSupplier a supplier providing the exception which is thrown when the predicate returns false
     * @param <T> the type of the checked value
     */
    public static <T> void guard(final T value, final Predicate<T> predicate, final Supplier<RuntimeException> exceptionSupplier) {
        if (!predicate.test(value)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Throws if the specified value is {@code null}.
     * @param value the value to be checked
     * @param name the (parameter) name of the checked value
     */
    public static void nonNull(final Object value, final String name) {
        guard(value, Objects::nonNull, () -> new NullPointerException(name + " must not be null!"));
    }

    /**
     * This class is not instantiatable.
     */
    private Guards() {}
}
