package org.am.enumeration;

/**
 * An enumeration of possible results of validating a product's price and stock.
 *

 */
public enum ProductValidationResult {
    SUCCESS("Success"),
    INVALID_PRICE("Invalid price"),
    INVALID_STOCK("Invalid stock");
    private final String result;

    ProductValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
