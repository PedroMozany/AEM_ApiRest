package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ProductDao;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;

@Component(immediate = true, service = ProductService.class)
public class ProductServiceImpl implements ProductService {


    @Reference
    ProductDao productDao;


    @Override
    public Product search(int id) {
        return productDao.searchId(id);
    }

    @Override
    public List<Product> orderLow() {
        return productDao.ListOrderLow();
    }

    @Override
    public List<Product> category(String category) {
        return productDao.ListCategory(category);
    }

    @Override
    public List<Product> keyword(String word) {
        return productDao.searchKeyword(word);
    }

    @Override
    public void save(Product product) {
         productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);

    }

    @Override
    public void delete(int id) {
        productDao.delete(id);

    }
}