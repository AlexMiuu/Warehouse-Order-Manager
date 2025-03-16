package org.am.presentation.view;

import javax.swing.*;

/**
 * UI for product
 *

 */
@SuppressWarnings("rawtypes")
public class ProductFrame {
    private JTextField idField;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton backButton;
    private JTable table1;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JTextField updateField;
    private JTextField idUpdate;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JButton addButton;
    private JButton updateButton;
    private JComboBox comboBox1;

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JTextField getUpdateField() {
        return updateField;
    }

    public JTextField getIdUpdate() {
        return idUpdate;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getStockField() {
        return stockField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
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

    public JTable getTable1() {
        return table1;
    }
}
