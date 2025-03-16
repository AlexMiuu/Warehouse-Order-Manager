package org.am.presentation.controller;

import org.am.bll.ProductBLL;
import org.am.model.Product;
import org.am.presentation.view.ProductFrame;
import org.am.single_point_access.SinglePointAccessGUI;
import org.am.util.TableInsert;

import java.util.List;
import java.util.Objects;

import static org.am.single_point_access.SinglePointAccessGUI.changePanel;
import static org.am.single_point_access.SinglePointAccessView.getStartFrame;

/**
 * This class is responsible for controlling the Product related functionality in the UI. It receives a ProductBLL object and a
 * <p>
 * ProductFrame object through its constructor and uses them to manage the interactions with the database and the UI elements, respectively.
 */
public class ProductController {
    private final ProductBLL productBLL;
    private final ProductFrame productFrame;

    /**
     * Constructor method for the ProductController class.
     *
     * @param productBLL   the business logic layer object responsible for managing the Product related database operations.
     * @param productFrame the UI frame object containing the Product related UI elements.
     */
    public ProductController(ProductBLL productBLL, ProductFrame productFrame) {
        this.productBLL = productBLL;
        this.productFrame = productFrame;
    }

    /**
     * This method sets up the necessary event listeners for the UI elements of the ProductFrame object, and defines their
     * corresponding actions.
     *

     */
    public void logic() {
        TableInsert<Product> productTableInsert = new TableInsert<>();
        productFrame.getBackButton().addActionListener(e -> changePanel(getStartFrame().getMainPanel(), "Start"));
        productFrame.getSearchButton().addActionListener(e -> productTableInsert.insert(List.of(productBLL.findById(Integer.parseInt(productFrame.getIdField().getText()))), productFrame.getTable1()));
        productFrame.getShowAllButton().addActionListener(e -> productTableInsert.insert(productBLL.findAll(), productFrame.getTable1()));
        productFrame.getDeleteButton().addActionListener(e -> productBLL.delete(Integer.parseInt(productFrame.getIdField().getText())));
        productFrame.getAddButton().addActionListener(e -> {
            Product product = new Product(
                    productFrame.getNameField().getText(),
                    Double.parseDouble(productFrame.getPriceField().getText()),
                    Integer.parseInt(productFrame.getStockField().getText())
            );
            SinglePointAccessGUI.showDialogMessage(productBLL.insert(product).getResult());
        });
        productFrame.getUpdateButton().addActionListener(e -> {
            Product product = productBLL.findById(Integer.parseInt(productFrame.getIdUpdate().getText()));
            switch (Objects.requireNonNull(productFrame.getComboBox1().getSelectedItem()).toString()) {
                case "Name" -> product.setProductName(productFrame.getUpdateField().getText());
                case "Price" -> product.setPrice(Double.parseDouble(productFrame.getUpdateField().getText()));
                case "Stock" -> product.setStock(Integer.parseInt(productFrame.getUpdateField().getText()));
            }
            SinglePointAccessGUI.showDialogMessage(productBLL.update(product).getResult());
        });
    }

    public ProductFrame getProductFrame() {
        return productFrame;
    }
}
