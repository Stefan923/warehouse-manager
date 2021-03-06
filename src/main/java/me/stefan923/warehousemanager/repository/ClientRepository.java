package me.stefan923.warehousemanager.repository;

import me.stefan923.warehousemanager.connection.ConnectionFactory;
import me.stefan923.warehousemanager.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class ClientRepository extends Repository<Client> {

    public ClientRepository() {
        super(Client.class);
    }

    /**
     * Returns the data associated with a class type (Client) and a given name.
     *
     * @param name - the identifier of the element whose data is to be retrieved.
     * @return a list of objects of type Client.
     */
    public List<Client> findByName(String name) {
        String query = SQLStatements.SELECT_ALL_LIKE_FIELD
                .replace("%table%", type.getSimpleName())
                .replace("%field%", "name");

        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:findByName " + e.getMessage());
        } finally {
            if (resultSet != null) {
                ConnectionFactory.close(resultSet);
            }
        }

        return Collections.emptyList();
    }

}
