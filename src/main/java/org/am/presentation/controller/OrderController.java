package org.am.presentation.controller;

import org.am.bll.BillBLL;
import org.am.bll.OrderBLL;
import org.am.bll.ProductBLL;
import org.am.enumeration.OrderValidationResult;
import org.am.model.Bill;
import org.am.model.OrderC;
import org.am.presentation.view.OrderFrame;
import org.am.single_point_access.SinglePointAccessBLL;
import org.am.util.TableInsert;

import java.util.List;
import java.util.Objects;

import static org.am.single_point_access.SinglePointAccessGUI.*;
import static org.am.single_point_access.SinglePointAccessView.getStartFrame;

/**
 * The OrderController class is responsible for controlling the order-related functionality of the application.
 * <p>
 * This includes adding, deleting, and updating orders in the database, as well as displaying orders in the user interface.
 *

 */
public class OrderController {
    private final OrderBLL orderBLL;
    private final BillBLL billBLL;
    private final ProductBLL productBLL;
    private final OrderFrame orderFrame;

    /**
     * Constructs a new OrderController object with the specified BLL objects and user interface frame.
     *
     * @param orderBLL   the BLL object for the OrderC model.
     * @param billBLL    the BLL object for the Bill model.
     * @param productBLL the BLL object for the Product model.
     * @param orderFrame the user interface frame for the Order functionality.
     */
    public OrderController(OrderBLL orderBLL, BillBLL billBLL, ProductBLL productBLL, OrderFrame orderFrame) {
        this.orderBLL = orderBLL;
        this.billBLL = billBLL;
        this.productBLL = productBLL;
        this.orderFrame = orderFrame;
    }

    /**
     * Sets up the logic for the Order functionality in the user interface.
     * <p>
     * This includes adding action listeners to buttons, such as add, delete, and update, as well as displaying orders in the table.
     */
    public void logic() {
        TableInsert<OrderC> orderCTableInsert = new TableInsert<>();
        orderFrame.getBackButton().addActionListener(e -> changePanel(getStartFrame().getMainPanel(), "Start"));
        orderFrame.getSearchButton().addActionListener(e -> orderCTableInsert.insert(List.of(orderBLL.findById(Integer.parseInt(orderFrame.getIdField().getText()))), orderFrame.getTable1()));
        orderFrame.getShowAllButton().addActionListener(e -> orderCTableInsert.insert(orderBLL.findAll(), orderFrame.getTable1()));
        orderFrame.getDeleteButton().addActionListener(e -> orderBLL.delete(Integer.parseInt(orderFrame.getIdField().getText())));
        orderFrame.getAddButton().addActionListener(e -> {
            OrderC order = new OrderC(
                    Integer.parseInt(orderFrame.getProductField().getText()),
                    Integer.parseInt(orderFrame.getClientField().getText()),
                    Integer.parseInt(orderFrame.getQuantityField().getText())
            );

            OrderValidationResult insertResult = orderBLL.insert(order);

            SinglePointAccessBLL.getBillBLL().insert(new Bill(order.getProductId(), order.getQuantity() * SinglePointAccessBLL.getProductBLL().findById(order.getProductId()).getPrice()));

            showDialogMessage(insertResult.getResult());

        });
        orderFrame.getUpdateButton().addActionListener(e -> {
            OrderC order = orderBLL.findById(Integer.parseInt(orderFrame.getIdForUpdate().getText()));
            switch (Objects.requireNonNull(orderFrame.getComboBox1().getSelectedItem()).toString()) {
                case "Client ID" -> order.setClientId(Integer.parseInt(orderFrame.getUpdateField().getText()));
                case "Product ID" -> order.setProductId(Integer.parseInt(orderFrame.getUpdateField().getText()));
                case "Quantity" -> order.setQuantity(Integer.parseInt(orderFrame.getUpdateField().getText()));
            }
            OrderValidationResult update = orderBLL.update(order);

            billBLL.insert(new Bill(order.getProductId(), order.getQuantity() * productBLL.findById(order.getProductId()).getPrice()));

            showDialogMessage(update.getResult());
        });

    }

    public OrderFrame getOrderFrame() {
        return orderFrame;
    }
}
