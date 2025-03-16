package org.am.dao;

import org.am.connection.ConnectionFactory;
import org.am.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**

 */
public class BillDAO {
    /**
     * The SQL query string used to find a log by its id in the "Log" table.
     */
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM Log WHERE Log.id = ?";
    /**
     * The SQL query string used to find all logs in the "Log" table.
     */
    private static final String FIND_ALL_QUERY = "SELECT * FROM Log";
    /**
     * The SQL query string used to insert a new log into the "Log" table.
     */
    private static final String INSERT_QUERY = "INSERT INTO Log(productId,price) VALUES (?, ?)";


    /**
     * Finds a bill in the database by its id.
     *
     * @param id the id of the bill to find
     * @return the Bill object found or null if no bill is found
     */
    public Bill findById(int id) {
        Bill toReturn = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            int productId = resultSet.getInt("productId");
            double price = resultSet.getDouble("price");

            toReturn = new Bill(id, productId, price);

        } catch (SQLException e) {
            System.out.println("BillDAO:findALL " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return toReturn;
    }

    /**
     * Retrieves a list of all bills from the database.
     *
     * @return A List of all bills from the database.
     */
    public List<Bill> findAll() {
        List<Bill> toReturn = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(FIND_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int productId = resultSet.getInt("productId");
                double price = resultSet.getDouble("price");

                Bill bill = new Bill(id, productId, price);

                toReturn.add(bill);
            }
        } catch (SQLException e) {
            System.out.println("BillDAO:findALL " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return toReturn;
    }

    /**
     * Retrieves a list of all bills from the database.
     *
     * @return A List of all bills from the database.
     */
    public int insert(Bill bill) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, bill.productId());
            insertStatement.setDouble(2, bill.price());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("BillDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}
