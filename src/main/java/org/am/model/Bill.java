package org.am.model;

/**
 * Represents a Bill record with id, product id, and price.
 *
 * @param id        the unique identifier of the bill
 * @param productId the product identifier related to the bill
 * @param price     the price of the product in the bill

 */
public record Bill(int id, int productId, double price) {

    public Bill(int productId, double price) {
        this(0, productId, price);
    }
}
