package org.am.presentation.controller;

import org.am.bll.ClientBLL;
import org.am.model.Client;
import org.am.presentation.view.ClientFrame;
import org.am.single_point_access.SinglePointAccessGUI;
import org.am.util.TableInsert;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static org.am.single_point_access.SinglePointAccessGUI.*;
import static org.am.single_point_access.SinglePointAccessView.getStartFrame;


/**
 * The ClientController class controls the actions performed on the ClientFrame and communicates with the ClientBLL class.
 *

 */
public class ClientController {

    /**
     * The ClientBLL object used to communicate with the database
     */
    private final ClientBLL clientBLL;

    /**
     * The ClientFrame object used to display the clients and receive user input
     */
    private final ClientFrame clientFrame;

    /**
     * Constructs a new ClientController object with the specified ClientBLL and ClientFrame objects
     *
     * @param clientBLL   the ClientBLL object used to communicate with the database
     * @param clientFrame the ClientFrame object used to display the clients and receive user input
     */
    public ClientController(ClientBLL clientBLL, ClientFrame clientFrame) {
        this.clientBLL = clientBLL;
        this.clientFrame = clientFrame;
    }

    /**
     * This method controls the actions performed on the ClientFrame and communicates with the ClientBLL class.
     * It adds ActionListeners to the buttons on the ClientFrame and performs the corresponding actions when the buttons are clicked.
     * The logic() method retrieves a Client object from the database using the ID entered in the ID field,
     * displays the Client object in the ClientFrame's table when the Search button is clicked,
     * displays all Clients in the database in the ClientFrame's table when the Show All button is clicked,
     * deletes a Client from the database when the Delete button is clicked and inserts a new Client to the database when the Add button is clicked,
     * and updates an existing Client in the database when the Update button is clicked.
     */
    public void logic() {
        TableInsert<Client> clientTableInsert = new TableInsert<>();
        clientFrame.getBackButton().addActionListener(e -> changePanel(getStartFrame().getMainPanel(), "Start"));
        clientFrame.getSearchButton().addActionListener(e -> clientTableInsert.insert(List.of(clientBLL.findById(Integer.parseInt(clientFrame.getIdField().getText()))), clientFrame.getTable1()));
        clientFrame.getShowAllButton().addActionListener(e -> clientTableInsert.insert(clientBLL.findAll(), clientFrame.getTable1()));
        clientFrame.getDeleteButton().addActionListener(e -> clientBLL.delete(Integer.parseInt(clientFrame.getIdField().getText())));
        clientFrame.getAddButton().addActionListener(e -> {
            Client client = new Client(
                    clientFrame.getFirstNameField().getText(),
                    clientFrame.getLastNameField().getText(),
                    Date.valueOf(clientFrame.getDobField().getText()),
                    clientFrame.getEmailField().getText(),
                    clientFrame.getPhoneField().getText()
            );
            clientBLL.insert(client);
          //  SinglePointAccessGUI.showDialogMessage(clientBLL.insert(client).getResult());
        });
        clientFrame.getUpdateButton().addActionListener(e -> {
            Client client = clientBLL.findById(Integer.parseInt(clientFrame.getIdUpdateField().getText()));
            switch (Objects.requireNonNull(clientFrame.getComboBox1().getSelectedItem()).toString()) {
                case "First Name" -> client.setFirstName(clientFrame.getUpdateField().getText());
                case "Last Name" -> client.setLastName(clientFrame.getUpdateField().getText());
                case "Email" -> client.setEmail(clientFrame.getUpdateField().getText());
                case "Date of Birth" -> client.setDob(Date.valueOf(clientFrame.getUpdateField().getText()));
                case "Phone Number" -> client.setPhoneNumber(clientFrame.getUpdateField().getText());
            }
            clientBLL.update(client);
        });
    }

    public ClientFrame getClientFrame() {
        return clientFrame;
    }
}
