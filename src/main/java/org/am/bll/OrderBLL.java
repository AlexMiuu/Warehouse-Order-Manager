package org.am.bll;

import org.am.dao.OrderDAO;
import org.am.enumeration.OrderValidationResult;
import org.am.model.OrderC;
import org.am.model.Product;

import java.util.List;

import static org.am.bll.validators.OrderValidator.isQuantityValid;
import static org.am.enumeration.OrderValidationResult.SUCCESS;

/**
 * This class represents the business logic layer for Order objects. It communicates with the OrderDAO class to
 * <p>
 * perform various CRUD operations on the orders stored in the database. Additionally, it checks if there is enough
 * <p>
 * stock for products when inserting or updating orders, and updates the stock accordingly.
 *
 */

public class OrderBLL {

    /**
     * The OrderDAO instance used for database communication.
     */
    private final OrderDAO orderDAO;

    /**
     * The productBLL instance used for updating product database based on logic
     */
    private final ProductBLL productBLL;

    /**
     * Dependency injection of the parameters
     *
     * @param orderDAO   instance used for database communication
     * @param productBLL instance used for updating product database based on logic
     */
    public OrderBLL(OrderDAO orderDAO, ProductBLL productBLL) {
        this.orderDAO = orderDAO;
        this.productBLL = productBLL;
    }

    /**
     * Retrieves a list of all orders from the database.
     *
     * @return a list of all orders
     */
    public List<OrderC> findAll() {
        return orderDAO.findAll();
    }

    /**
     * Retrieves an order with the specified ID from the database.
     *
     * @param id the ID of the order to retrieve
     * @return the OrderC object with the specified ID, or null if no order was found
     */
    public OrderC findById(int id) {
        return orderDAO.findById(id);
    }

    /**
     * Inserts a new order into the database, and updates the stock of the corresponding product if there is enough
     * <p>
     * stock available.
     *
     * @param orderC the OrderC object to insert
     * @return an OrderResult indicating the success or failure of the operation
     */
    public OrderValidationResult insert(OrderC orderC) {
        Product productToBuy = productBLL.findById(orderC.getProductId());

        OrderValidationResult quantityValid = isQuantityValid(orderC, productToBuy);

        if (quantityValid.equals(SUCCESS)) {
            productToBuy.setStock(productToBuy.getStock() - orderC.getQuantity());
            productBLL.update(productToBuy);
            orderDAO.insert(orderC);
        }

        return quantityValid;
    }

    /**
     * Updates an existing order in the database, and updates the stock of the corresponding product if there is enough
     * <p>
     * stock available.
     *
     * @param updatedOrder the updated OrderC object
     * @return an OrderResult indicating the success or failure of the operation
     */
    public OrderValidationResult update(OrderC updatedOrder) {
        OrderC oldOrder = orderDAO.findById(updatedOrder.getId());

        Product productToBuy = productBLL.findById(updatedOrder.getProductId());

        productToBuy.setStock(productToBuy.getStock() + oldOrder.getQuantity() - updatedOrder.getQuantity());

        OrderValidationResult quantityValid = isQuantityValid(updatedOrder, productToBuy);

        if (quantityValid.equals(SUCCESS)) {
            productBLL.update(productToBuy);
            orderDAO.update(updatedOrder);
        }

        return quantityValid;
    }

    /**
     * Deletes an order with the specified ID from the database.
     *
     * @param id the ID of the order to delete
     * @return true if the order was successfully deleted, false otherwise
     */
    public boolean delete(int id) {
        OrderC orderC = findById(id);
        Product productBought = productBLL.findById(orderC.getProductId());

        productBought.setStock(productBought.getStock() + orderC.getQuantity());
        productBLL.update(productBought);

        return orderDAO.deleteById(id);
    }
}
