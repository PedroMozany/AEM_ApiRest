package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.service.DatabaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;

@Component(immediate = true, service = ClientDao.class)
public class ClientDaoImpl implements ClientDao {

    @Reference
    private DatabaseService databaseService;


    @Override
    public Client search(int id) {
        try (Connection component = databaseService.getConnection()) {
            String query = "SELECT * FROM CLIENT WHERE ID = ?";
            Client client;
            try (PreparedStatement ps = component.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    if (rs.next()) {
                        client = new Client(rs.getInt(1), rs.getString(2));
                        return client;
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client save(Client client) {
        Client result;
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO CLIENT(NAME) VALUES(?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, client.getName());
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return search(rs.getInt(1));
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Client client) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE CLIENT SET NAME = ? where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, client.getName());
                ps.setInt(2, client.getId());
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws ExceptionsParamenter {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM CLIENT WHERE ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new ExceptionsParamenter("ID Client foreign key in Invoice");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}