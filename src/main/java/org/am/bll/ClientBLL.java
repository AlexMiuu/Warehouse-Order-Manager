package org.am.bll;

import org.am.dao.ClientDAO;
import org.am.model.Client;

import java.util.List;



/**
 * The ClientBLL class represents the business logic layer of the Client entity in the application.
 *
 * <p>
 * It contains methods that operate on Client objects using the underlying ClientDAO.
 *
 */
public class ClientBLL {

    /**
     * The DAO instance used by the business logic layer.
     */
    private final ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object with the given DAO.
     *
     * @param clientDAO the DAO to use in this business logic layer
     */
    public ClientBLL(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * Retrieves all the clients from the database.
     *
     * @return a list of all the clients in the database
     */
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    /**
     * Retrieves a client with the given ID from the database.
     *
     * @param id the ID of the client to retrieve
     * @return the client with the given ID, or null if not found
     */
    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    /**
     * Inserts a new client into the database.
     *
     * @param client the client to insert
     * @return the result of the validation, indicating if the client was inserted or not
     */
    public void insert(Client client) {
            clientDAO.insert(client);
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the client to update
     * @return the result of the validation, indicating if the client was updated or not
     */
    public void update(Client client) {
            clientDAO.update(client);
    }

    /**
     * Deletes a client with the given ID from the database.
     *
     * @param id the ID of the client to delete
     * @return true if the client was deleted successfully, false otherwise
     */
    public boolean delete(int id) {
        return clientDAO.deleteById(id);
    }

    /**
     * Validates a client object.
     *
     * @param client the client to validate
     * @return the result of the validation
     */

}