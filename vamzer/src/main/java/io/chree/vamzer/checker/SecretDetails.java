package io.chree.vamzer.checker;

import java.util.Objects;

import static io.chree.vamzer.util.Guards.nonNull;

/**
 * Detailed information regarding a secret occurrence.
 */
public final class SecretDetails {
    private final String type;

    private final String message;

    /**
     * Constructs a new instance with the specified type and message.
     * @param type the type of the secret occurrence
     * @param message human-readable detail message
     * @throws NullPointerException if any of the arguments is {@code null}
     */
    public SecretDetails(final String type, final String message) {
        this.type = nonNull(type, "type");
        this.message = nonNull(message, "message");
    }

    /**
     * Gets the type of the detected secret (for example: JWT, GitHub Access Token, etc.). The actual
     * value depends on the checker implementation.
     * @return the type of the detected secret
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the human-readable message attached to this secret occurrence. The actual
     * value depends on the checker implementation.
     * @return the human-readable detail message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SecretDetails{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SecretDetails that = (SecretDetails) o;
        return type.equals(that.type) &&
                message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, message);
    }
}
