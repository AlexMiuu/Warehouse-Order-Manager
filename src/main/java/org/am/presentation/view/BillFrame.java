package org.am.presentation.view;

import javax.swing.*;

/**
 * UI for bill
 *

 */
public class BillFrame {
    private JTextField idField;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton backButton;
    private JTable table1;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

}
