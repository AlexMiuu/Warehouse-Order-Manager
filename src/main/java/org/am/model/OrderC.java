package org.am.model;


/**
 * Represents an order made by a client for a particular product.
 *

 */
public class OrderC {
    /**
     * The unique identifier of the order.
     */
    int id;
    /**
     * The identifier of the product ordered.
     */
    int productId;
    /**
     * The identifier of the client who made the order.
     */
    int clientId;
    /**
     * The quantity of the product ordered.
     */
    int quantity;

    public OrderC() {
    }

    public OrderC(int productId, int clientId, int quantity) {
        this.productId = productId;
        this.clientId = clientId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderC{" +
                "id=" + id +
                ", productId=" + productId +
                ", clientId=" + clientId +
                ", quantity=" + quantity +
                '}';
    }
}
