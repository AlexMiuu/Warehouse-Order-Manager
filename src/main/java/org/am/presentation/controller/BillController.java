package org.am.presentation.controller;

import org.am.bll.BillBLL;
import org.am.model.Bill;
import org.am.presentation.view.BillFrame;
import org.am.util.TableInsert;

import java.util.List;

import static org.am.single_point_access.SinglePointAccessGUI.*;
import static org.am.single_point_access.SinglePointAccessView.*;


/**
 * The BillController class controls the actions performed on the BillFrame and communicates with the BillBLL class
 *

 */
public class BillController {

    /**
     * The BillBLL object used to communicate with the database
     */
    private final BillBLL billBLL;

    /**
     * The BillFrame object used to display the bills and receive user input
     */
    private final BillFrame billFrame;

    /**
     * Constructs a new BillController object with the specified BillBLL and BillFrame objects
     *
     * @param billBLL   the BillBLL object used to communicate with the database
     * @param billFrame the BillFrame object used to display the bills and receive user input
     */
    public BillController(BillBLL billBLL, BillFrame billFrame) {
        this.billBLL = billBLL;
        this.billFrame = billFrame;
    }

    /**
     * This method controls the actions performed on the BillFrame and communicates with the BillBLL class.
     * It adds ActionListeners to the buttons on the BillFrame and performs the corresponding actions when the buttons are clicked.
     * The logic() method retrieves a Bill object from the database using the ID entered in the ID field,
     * displays the Bill object in the BillFrame's table when the Search button is clicked,
     * and displays all Bills in the database in the BillFrame's table when the Show All button is clicked.
     */
    public void logic() {
        TableInsert<Bill> billTableInsert = new TableInsert<>();
        billFrame.getBackButton().addActionListener(e -> changePanel(getStartFrame().getMainPanel(), "Start"));
        billFrame.getSearchButton().addActionListener(e -> billTableInsert.insert(List.of(billBLL.findById(Integer.parseInt(billFrame.getIdField().getText()))), billFrame.getTable1()));
        billFrame.getShowAllButton().addActionListener(e -> billTableInsert.insert(billBLL.findAll(), billFrame.getTable1()));
    }


    public BillFrame getBillFrame() {
        return billFrame;
    }
}
