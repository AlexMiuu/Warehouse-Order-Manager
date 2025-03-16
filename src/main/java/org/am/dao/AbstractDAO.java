package org.am.dao;

import org.am.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an abstract DAO class that provides common CRUD operations for any DAO subclass.
 *
 * @param <T> The type of the model class that the DAO subclass is handling.

 */

@SuppressWarnings({"rawtypes", "SameParameterValue"})
public class AbstractDAO<T> {
    /**
     * The logger instance used for logging messages.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    /**
     * The class object representing the entity type for this DAO class.
     */

    private final Class<T> type;

    /**
     * Constructs an instance of this DAO class and initializes the type parameter using reflection.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates an SQL SELECT query for a specified field of a given class.
     *
     * @param field A String representing the name of the field to search for in the database.
     * @return A String representing the SQL SELECT query.
     */
    private String createSelectQuery(String field) {
        return "SELECT * FROM " +
                type.getSimpleName() +
                " WHERE " + field + " =?";
    }

    /**
     * Creates an SQL INSERT query for the specified fields of a given class, excluding the "id" field.
     *
     * @param fields An array of Field objects representing the fields to be included in the query.
     * @return A String representing the SQL INSERT query.
     */
    private String createInsertQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO warehouse.");
        sb.append(type.getSimpleName());
        sb.append("(");
        for (Field f : fields) {
            if (!f.getName().equals("id")) {
                sb.append(f.getName()).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES ");
        for (int i = 1; i <= fields.length - 1; i++) {
            if (i == 1) {
                sb.append("(?");
            } else {
                sb.append(", ?");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Creates an SQL UPDATE query for the specified fields of a given class, excluding the "id" field.
     *
     * @param fields An array of Field objects representing the fields to be updated in the query.
     * @return A String representing the SQL UPDATE query.
     */
    private String createUpdateQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field f : fields) {
            if (!f.getName().equals("id")) {
                sb.append(f.getName()).append("=?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id=?");

        return sb.toString();
    }

    /**
     * Creates an SQL DELETE query for a specified field of a given class.
     *
     * @param field A String representing the name of the field to be deleted from the database.
     * @return A String representing the SQL DELETE query.
     */
    public String createDeleteQuery(String field) {
        return "DELETE FROM " +
                type.getSimpleName() +
                " WHERE " + field + " =?";
    }


    /**
     * Creates an SQL SELECT query to retrieve all records of a given class.
     *
     * @return A String representing the SQL SELECT query.
     */
    public String createSelectAllQuery() {
        return "SELECT * FROM " +
                type.getSimpleName();
    }

    /**
     * Retrieves all records from the database for a given class.
     *
     * @return A List of objects representing the retrieved records.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findALL " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves a record from the database for a given class, based on its ID.
     *
     * @param id An integer representing the ID of the record to retrieve.
     * @return An object representing the retrieved record.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a list of objects of the specified class based on a result set.
     *
     * @param resultSet A result set containing the records to create objects from.
     * @return A list of objects created from the records in the result set.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : constructors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                assert ctor != null;
                ctor.setAccessible(true);
                @SuppressWarnings("unchecked")
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                 InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Inserts a new record into the database based on the given object. The object is assumed to correspond
     * to a table in the database, where each field in the object represents a column in the table.
     *
     * @param t the object to be inserted into the database
     * @return the inserted object, or null if an error occurred during the insertion process
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t.getClass().getDeclaredFields());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int currentParameter = 1;
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id"))
                    continue;
                statement.setObject(currentParameter, f.get(t));
                currentParameter += 1;
            }
            statement.execute();
            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Inserts an object of type T into the database.
     *
     * @param t The object to be inserted.
     * @return The inserted object if the insertion was successful, null otherwise.
     */
    public T update(T t) {
        int id = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t.getClass().getDeclaredFields());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int currentParameter = 1;
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id")) {
                    id = (int) f.get(t);
                    continue;
                }
                statement.setObject(currentParameter, f.get(t));
                currentParameter += 1;
            }
            statement.setInt(currentParameter, id);
            statement.execute();
            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Deletes an object from the database with the specified ID.
     *
     * @param id the ID of the object to be deleted
     * @return true if the object is successfully deleted, false otherwise
     */
    public boolean deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

}
