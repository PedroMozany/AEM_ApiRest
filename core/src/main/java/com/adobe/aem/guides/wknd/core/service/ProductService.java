package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Product;

import java.util.List;

public interface ProductService {
     Product search(int id);
     List<Product> orderLow();
     List<Product> category(String category);
     List<Product> keyword(String word);
     void save(Product product);
     void update(Product product);
     void delete(int id) throws ExceptionsParamenter;
}
