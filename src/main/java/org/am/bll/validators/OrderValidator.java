package org.am.bll.validators;

import org.am.enumeration.OrderValidationResult;
import org.am.model.OrderC;
import org.am.model.Product;

import java.util.function.Function;

import static org.am.enumeration.OrderValidationResult.SUCCESS;

/**
 * A functional interface for validating a {@link OrderC} object.
 *
 */
public interface OrderValidator extends Function<OrderC, OrderValidationResult> {
    static OrderValidationResult isQuantityValid(OrderC orderC, Product productToBuy) {
        if (orderC.getQuantity() <= 0)
            return OrderValidationResult.NEGATIVE_OR_NULL_STOCK;
        if (productToBuy.getStock() < orderC.getQuantity())
            return OrderValidationResult.NOT_ENOUGH_STOCK;
        return SUCCESS;
    }

    default OrderValidator and(OrderValidator other) {
        return order -> {
            OrderValidationResult result = this.apply(order);
            return result.equals(SUCCESS) ? other.apply(order) : result;
        };
    }
}
