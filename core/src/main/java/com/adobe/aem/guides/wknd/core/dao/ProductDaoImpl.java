package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.DatabaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component(immediate = true, service = ProductDao.class)
public class ProductDaoImpl implements ProductDao {

    @Reference
    DatabaseService databaseService;


    @Override
    public Product searchId(int id) {
        Product result;
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM PRODUCT WHERE ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    if (rs.next()) {
                        return result = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> searchKeyword(String word) {
        List<Product> result = new LinkedList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM PRODUCT WHERE NAME_PRODUCT lIKE '%' ? '%'";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, word);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    while (rs.next()) {
                        Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                        result.add(product);
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Product> ListOrderLow() {
        List<Product> result = new LinkedList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM PRODUCT ORDER BY PRICE";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    while (rs.next()) {
                        Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                        result.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Product> ListCategory(String category) {
        List<Product> result = new LinkedList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM PRODUCT WHERE CATEGORY = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1,category);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    while (rs.next()) {
                        Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                        result.add(product);
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO PRODUCT(NAME_PRODUCT, CATEGORY, PRICE) VALUES(?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, product.getNameProduct());
                ps.setString(2, product.getCategory());
                ps.setDouble(3, product.getPrice());
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE PRODUCT SET NAME_PRODUCT = ?, ATEGORY = ?, PRICE = ? WHERE ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1,product.getNameProduct());
                ps.setString(2, product.getCategory());
                ps.setDouble(3,product.getPrice());
                ps.setInt(4,product.getId());
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws ExceptionsParamenter {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM PRODUCT WHERE ID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,id);
                ps.execute();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new ExceptionsParamenter("ID Product foreign key in Invoice");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
