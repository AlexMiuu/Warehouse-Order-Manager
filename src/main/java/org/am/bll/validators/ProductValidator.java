package org.am.bll.validators;

import org.am.enumeration.ProductValidationResult;
import org.am.model.Product;

import java.util.function.Function;

import static org.am.enumeration.ProductValidationResult.*;

/**
 * A functional interface for validating a {@link Product} object.
 *

 */
public interface ProductValidator extends Function<Product, ProductValidationResult> {

    static ProductValidator isQuantityValid() {
        return product -> product.getStock() >= 0 ? SUCCESS : INVALID_STOCK;
    }

    static ProductValidator isPriceValid() {
        return product -> product.getPrice() >= 0.0 ? SUCCESS : INVALID_PRICE;
    }

    default ProductValidator and(ProductValidator other) {
        return product -> {
            ProductValidationResult result = this.apply(product);
            return result.equals(SUCCESS) ? other.apply(product) : result;
        };
    }
}
