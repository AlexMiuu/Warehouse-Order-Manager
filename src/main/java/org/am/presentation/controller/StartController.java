package org.am.presentation.controller;

import org.am.presentation.view.StartFrame;

import static org.am.single_point_access.SinglePointAccessController.*;
import static org.am.single_point_access.SinglePointAccessGUI.changePanel;

/**
 * This class represents a controller for the start screen of the application. It is responsible for managing the logic of the
 * start screen, as well as coordinating the other controllers for the different screens of the application.
 *

 */
public class StartController {
    private final StartFrame startFrame;
    private final BillController billController;
    private final ClientController clientController;
    private final OrderController orderController;
    private final ProductController productController;

    /**
     * Constructs a new StartController object with the specified parameters.
     *
     * @param startFrame        the StartFrame object associated with this controller.
     * @param billController    the BillController object associated with this controller.
     * @param clientController  the ClientController object associated with this controller.
     * @param orderController   the OrderController object associated with this controller.
     * @param productController the ProductController object associated with this controller.
     */
    public StartController(StartFrame startFrame, BillController billController, ClientController clientController, OrderController orderController, ProductController productController) {
        this.startFrame = startFrame;
        this.billController = billController;
        this.clientController = clientController;
        this.orderController = orderController;
        this.productController = productController;
    }

    /**
     * Sets up the logic for the start screen, as well as for the other screens of the application.
     */
    public void logic() {
        changePanel(startFrame.getMainPanel(), "App");
        billController.logic();
        clientController.logic();
        orderController.logic();
        productController.logic();
        startFrame.getBillsButton().addActionListener(e -> changePanel(getBillController().getBillFrame().getMainPanel(), "Bill"));
        startFrame.getClientButton().addActionListener(e -> changePanel(getClientController().getClientFrame().getMainPanel(), "Client"));
        startFrame.getOrdersButton().addActionListener(e -> changePanel(getOrderController().getOrderFrame().getMainPanel(), "Order"));
        startFrame.getProductButton().addActionListener(e -> changePanel(getProductController().getProductFrame().getMainPanel(), "Product"));
    }
}

