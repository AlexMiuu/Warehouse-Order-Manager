package org.am.bll;

import org.am.dao.ProductDAO;
import org.am.enumeration.ProductValidationResult;
import org.am.model.Product;

import java.util.List;

import static org.am.bll.validators.ProductValidator.isPriceValid;
import static org.am.bll.validators.ProductValidator.isQuantityValid;
import static org.am.enumeration.ProductValidationResult.SUCCESS;

/**
 * This class represents the business logic layer for the Product entity. It provides methods to interact with the data access layer.
 *
 */
public class ProductBLL {
    private final ProductDAO productDAO;

    /**
     * Creates a new instance of the ProductBLL class.
     *
     * @param productDAO an instance of ProductDAO used for database access
     */
    public ProductBLL(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Returns a list of all products in the database.
     *
     * @return a list of Product objects
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    /**
     * Returns the product with the specified ID.
     *
     * @param id the ID of the product to retrieve
     * @return the Product object with the specified ID, or null if not found
     */
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product the Product object to insert
     * @return the result of validation
     */
    public ProductValidationResult insert(Product product) {
        ProductValidationResult productValidationResult = isProductValid(product);
        if(productValidationResult.equals(SUCCESS))
            productDAO.insert(product);
        return  productValidationResult;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product the Product object to update
     * @return the result of validation for the updated Product object
     */
    public ProductValidationResult update(Product product) {
        ProductValidationResult productValidationResult = isProductValid(product);
        if(productValidationResult.equals(SUCCESS))
            productDAO.update(product);
        return  productValidationResult;
    }

    /**
     * Deletes the product with the specified ID from the database.
     *
     * @param id the ID of the product to delete
     * @return true if the product was successfully deleted, false otherwise
     */
    public boolean delete(int id) {
        return productDAO.deleteById(id);
    }

    private ProductValidationResult isProductValid(Product product){
        return isPriceValid().and(isQuantityValid()).apply(product);
    }
}
