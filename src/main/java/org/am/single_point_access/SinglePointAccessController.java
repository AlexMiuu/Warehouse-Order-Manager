package org.am.single_point_access;

import org.am.presentation.controller.*;

import static org.am.single_point_access.SinglePointAccessBLL.*;
import static org.am.single_point_access.SinglePointAccessView.*;

/**
 * The SinglePointAccessController class is responsible for instantiating and providing access to the
 * controllers in the application. It provides static methods to retrieve instances of the controllers.
 * The class initializes the controllers with their respective Business Logic Layer (BLL) and View
 * classes.
 * The class also imports static methods from the SinglePointAccessBLL and SinglePointAccessView classes
 * to initialize the controllers.
 *

 */
public class SinglePointAccessController {
    private final static BillController billController;
    private final static ClientController clientController;
    private final static OrderController orderController;
    private final static ProductController productController;
    private final static StartController startController;

    static {
        billController = new BillController(getBillBLL(), getBillFrame());
        clientController = new ClientController(getClientBLL(), getClientFrame());
        orderController = new OrderController(getOrderBLL(), getBillBLL(), getProductBLL(), getOrderFrame());
        productController = new ProductController(getProductBLL(), getProductFrame());
        startController = new StartController(getStartFrame(), getBillController(), getClientController(), getOrderController(), getProductController());
    }

    public static BillController getBillController() {
        return billController;
    }

    public static ClientController getClientController() {
        return clientController;
    }

    public static OrderController getOrderController() {
        return orderController;
    }

    public static ProductController getProductController() {
        return productController;
    }

    public static StartController getStartController() {
        return startController;
    }
}
