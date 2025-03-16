package org.am.single_point_access;

import org.am.dao.BillDAO;
import org.am.dao.ClientDAO;
import org.am.dao.OrderDAO;
import org.am.dao.ProductDAO;

/**
 * The {@code SinglePointAccessDAO} class is responsible for providing access to the data access objects (DAOs)
 * used in the Single Point Access application. It initializes and stores the DAO objects for bills, clients, orders, and products.
 * These DAO objects can be accessed statically using the provided getter methods.
 *

 */
public class SinglePointAccessDAO {
    private static BillDAO billDAO;
    private static ClientDAO clientDAO;
    private static OrderDAO orderDAO;
    private static ProductDAO productDAO;

    static {
        billDAO = new BillDAO();
        clientDAO = new ClientDAO();
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
    }

    public static BillDAO getBillDAO() {
        return billDAO;
    }

    public static ClientDAO getClientDAO() {
        return clientDAO;
    }

    public static OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public static ProductDAO getProductDAO() {
        return productDAO;
    }
}
