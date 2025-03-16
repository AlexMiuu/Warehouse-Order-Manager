package org.am.presentation.view;

import javax.swing.*;

/**
 * UI for order
 *

 */
@SuppressWarnings("rawtypes")
public class OrderFrame {
    private JTextField idField;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton backButton;
    private JTable table1;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField idForUpdate;
    private JTextField clientField;
    private JTextField productField;
    private JTextField quantityField;
    private JButton addButton;

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JTextField getUpdateField() {
        return updateField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getIdForUpdate() {
        return idForUpdate;
    }

    public JTextField getClientField() {
        return clientField;
    }

    public JTextField getProductField() {
        return productField;
    }

    public JTextField getQuantityField() {
        return quantityField;
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

    public JTable getTable1() {
        return table1;
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
