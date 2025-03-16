package org.am.enumeration;

/**
 * /**
 * <p>
 * An enum representing the result of an order.
 * <p>
 * This enum contains two possible results: {@link #NOT_ENOUGH_STOCK} and {@link #SUCCESS}.
 * </p>
 *

 */
public enum OrderValidationResult {
    NEGATIVE_OR_NULL_STOCK("Invalid number"),
    NOT_ENOUGH_STOCK("Not enough stock"),
    SUCCESS("Success");
    private final String result;

    OrderValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
