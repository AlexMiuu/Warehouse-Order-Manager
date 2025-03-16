package org.am.single_point_access;

import org.am.bll.BillBLL;
import org.am.bll.ClientBLL;
import org.am.bll.OrderBLL;
import org.am.bll.ProductBLL;

import static org.am.single_point_access.SinglePointAccessDAO.*;

/**
 * A utility class that provides a single point of access to the different BLL (Business Logic Layer) classes in the application.
 * <p>
 * This class follows the Singleton pattern, meaning that there can only be one instance of it created.
 *

 */
public class SinglePointAccessBLL {
    private static final BillBLL billBLL;
    private static final ClientBLL clientBLL;
    private static final OrderBLL orderBLL;
    private static final ProductBLL productBLL;

    static {
        billBLL = new BillBLL(getBillDAO());
        clientBLL = new ClientBLL(getClientDAO());
        productBLL = new ProductBLL(getProductDAO());
        orderBLL = new OrderBLL(getOrderDAO(),getProductBLL());
    }

    public static BillBLL getBillBLL() {
        return billBLL;
    }

    public static ClientBLL getClientBLL() {
        return clientBLL;
    }

    public static OrderBLL getOrderBLL() {
        return orderBLL;
    }

    public static ProductBLL getProductBLL() {
        return productBLL;
    }
}
