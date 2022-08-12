package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Product;

import java.util.List;

public interface ProductDao {
    Product searchId(int id);
    List<Product> searchKeyword(String word);
    List<Product> ListOrderLow();
    List<Product> ListCategory(String category);
    void save(Product product);
    void update(Product product);
    void delete(int id);
}
