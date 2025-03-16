package org.am.single_point_access;

import org.am.presentation.view.*;

/**
 * The {@code SinglePointAccessView} class represents the view layer of the Single Point Access application.
 * It provides static methods for accessing instances of the graphical user interface (GUI) components, including
 * the frames for each entity (bill, client, order, product) and the start frame.
 * This class is responsible for creating and returning instances of the view layer, and for providing access
 * to those instances to the rest of the application.
 *

 */
public class SinglePointAccessView {
    private static final BillFrame billFrame = new BillFrame();
    private static final ClientFrame clientFrame = new ClientFrame();
    private static final OrderFrame orderFrame = new OrderFrame();
    private static final ProductFrame productFrame = new ProductFrame();
    private static final StartFrame startFrame = new StartFrame();

    public static BillFrame getBillFrame() {
        return billFrame;
    }

    public static ClientFrame getClientFrame() {
        return clientFrame;
    }

    public static OrderFrame getOrderFrame() {
        return orderFrame;
    }

    public static ProductFrame getProductFrame() {
        return productFrame;
    }

    public static StartFrame getStartFrame() {
        return startFrame;
    }
}
