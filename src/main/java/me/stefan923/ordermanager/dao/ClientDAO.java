package me.stefan923.ordermanager.dao;

import me.stefan923.ordermanager.connection.ConnectionFactory;
import me.stefan923.ordermanager.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client> {

    public ClientDAO() {
        super(Client.class);
    }

    public List<Client> findByName(String name) {
        String query = SQLStatemets.SELECT_ALL_LIKE_FIELD
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
