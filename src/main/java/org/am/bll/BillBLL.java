package org.am.bll;

import org.am.dao.BillDAO;
import org.am.model.Bill;

import java.util.List;

/**
 * The BillBLL class provides business logic operations for working with bills.
 *
 */
public class BillBLL {

    /**
     * The data access object used for interacting with the bills table in the database.
     */
    private final BillDAO billDAO;

    /**
     * Constructs a new BillBLL object with the specified BillDAO object.
     *
     * @param billDAO the BillDAO object to be used
     */
    public BillBLL(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    /**
     * Retrieves a list of all bills.
     *
     * @return a list of all bills
     */
    public List<Bill> findAll() {
        return billDAO.findAll();
    }

    /**
     * Retrieves a bill with the specified ID.
     *
     * @param id the ID of the bill to be retrieved
     * @return the bill with the specified ID, or null if the bill does not exist
     */
    public Bill findById(int id) {
        return billDAO.findById(id);
    }

    /**
     * Inserts a new bill into the database.
     *
     * @param bill the bill to be inserted
     * @return the ID of the newly inserted bill, or -1 if the insertion failed
     */
    public int insert(Bill bill) {
        return billDAO.insert(bill);
    }
}
