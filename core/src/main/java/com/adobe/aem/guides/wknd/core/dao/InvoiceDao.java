package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.Product;

import java.util.List;

public interface InvoiceDao {
    void resgitration(Invoice invoice);
    void deleteProduct(int number, int idProduct);
    List<Product> productBuy(int idClient);
}
