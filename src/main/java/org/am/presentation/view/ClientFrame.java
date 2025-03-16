package org.am.presentation.view;

import javax.swing.*;

/**
 * UI for client
 *

 */
@SuppressWarnings("rawtypes")
public class ClientFrame {
    private JButton showAllButton;
    private JTextField idField;
    private JButton searchButton;
    private JButton backButton;
    private JTable table1;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField idUpdateField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField dobField;
    private JButton addButton;

    public JTable getTable1() {
        return table1;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JTextField getUpdateField() {
        return updateField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getIdUpdateField() {
        return idUpdateField;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getDobField() {
        return dobField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
