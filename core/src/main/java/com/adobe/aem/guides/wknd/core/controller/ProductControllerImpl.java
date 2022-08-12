package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;

@Component(immediate = true, service = ProductController.class)
public class ProductControllerImpl implements ProductController {

    @Reference
    ProductService productService;
    JsonParser jp = new JsonParser();

    Gson gson = new Gson();

    JsonElement root;

    String json;

    @Override
    public String searchParamenters(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        String value = null;

        try {
            root = jp.parse(req.getReader());
            boolean id = gson.toJson(root).contains("id");
            boolean category = gson.toJson(root).contains("category");
            boolean word = gson.toJson(root).contains("word");
            if (id) {
                JsonObject object = root.getAsJsonObject();
                value = object.get("id").getAsString();
                Product product = productService.search(Integer.parseInt(value));
                return gson.toJson(product);
            } else if (category) {
                JsonObject object = root.getAsJsonObject();
                value = object.get("category").getAsString();
                List<Product> list = productService.category(value);
                return gson.toJson(list);
            } else if (word) {
                JsonObject object = root.getAsJsonObject();
                value = object.get("word").getAsString();
                List<Product> list = productService.keyword(value);
                return gson.toJson(list);
            } else {
                List<Product> list = productService.orderLow();
                return gson.toJson(list);
            }
        } catch (IllegalStateException iex) {
            throw new RuntimeException(iex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String nameProduct = object.get("nameProduct").getAsString();
            String category = object.get("category").getAsString();
            String price = object.get("price").getAsString();
            productService.save(new Product(nameProduct, category, Double.parseDouble(price)));
        } catch (IllegalStateException iex) {
            throw new RuntimeException(iex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String nameProduct = object.get("nameProduct").getAsString();
            String category = object.get("category").getAsString();
            String price = object.get("price").getAsString();
            String id = object.get("id").getAsString();
            productService.update(new Product(Integer.parseInt(id), nameProduct, category, Double.parseDouble(price)));
        } catch (IllegalStateException iex) {
            throw new RuntimeException(iex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            productService.delete(Integer.parseInt(id));
        } catch (IllegalStateException iex) {
            throw new RuntimeException(iex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


