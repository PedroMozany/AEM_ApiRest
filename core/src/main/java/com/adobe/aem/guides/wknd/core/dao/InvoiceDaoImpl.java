package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Dto;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.DatabaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component(immediate = true, service = InvoiceDao.class)
public class InvoiceDaoImpl implements InvoiceDao {

    @Reference
    DatabaseService databaseService;

    @Override
    public void resgitration(Invoice invoice) throws ExceptionsParamenter {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO INVOICE(ID_CLIENT,ID_PRODUCT) VALUES(?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, invoice.getIdClient());
                ps.setInt(2, invoice.getIdProduct());
                ps.execute();
            }
        }catch (SQLIntegrityConstraintViolationException ex) {
            throw new ExceptionsParamenter("Not exist these client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(int idClient, int idProduct) throws ExceptionsParamenter {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE  FROM INVOICE WHERE ID_CLIENT = ? and ID_PRODUCT = ? ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, idClient);
                ps.setInt(2, idProduct);
                ps.execute();
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new ExceptionsParamenter("Not exist these client");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> productBuy(int idClient) {
        List<Product> list = new LinkedList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT ID_PRODUCT, NAME_PRODUCT, CATEGORY, PRICE from( SELECT NAME, ID_CLIENT, ID_PRODUCT, NAME_PRODUCT, CATEGORY, PRICE FROM INVOICE LEFT JOIN( SELECT ID, NAME FROM CLIENT )CLIENT ON ID_CLIENT = CLIENT.ID LEFT JOIN( SELECT ID, NAME_PRODUCT, CATEGORY, PRICE FROM PRODUCT )PRODUCT ON ID_PRODUCT = PRODUCT.ID where ID_CLIENT = ?)productbuy";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, idClient);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    while (rs.next()) {
                        Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                        list.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Dto> getInvoice() {
        List<Dto> list = new LinkedList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT number, NAME , NAME_PRODUCT, CATEGORY, PRICE from( SELECT number, NAME, ID_CLIENT, ID_PRODUCT, NAME_PRODUCT, CATEGORY, PRICE FROM INVOICE LEFT JOIN( SELECT ID, NAME FROM CLIENT )CLIENT ON ID_CLIENT = CLIENT.ID LEFT JOIN( SELECT ID, NAME_PRODUCT, CATEGORY, PRICE FROM PRODUCT )PRODUCT ON ID_PRODUCT = PRODUCT.ID )productbuy";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    while (rs.next()) {
                        Dto dto = new Dto(rs.getInt(1),new Client(rs.getString(2)),new Product(rs.getString(3), rs.getString(4),rs.getDouble(5)));
                        list.add(dto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
