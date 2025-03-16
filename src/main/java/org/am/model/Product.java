package org.am.model;

/**
 * This class represents a product in the inventory system.
 *

 */
public class Product {

    /**
     * The unique identifier of the product.
     */
    int id;
    /**
     * The name of the product.
     */
    String productName;
    /**
     * The price of the product.
     */
    double price;
    /**
     * The current stock quantity of the product.
     */
    int stock;

    public Product() {
    }

    public Product(String productName, double price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
