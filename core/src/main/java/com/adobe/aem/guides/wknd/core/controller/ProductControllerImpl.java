package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.ProductService;
import com.google.gson.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
    public String searchParamenters(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
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
        } catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonArray  jarray = root.getAsJsonArray();
            for (int i = 0; i < jarray.size(); i++) {
                JsonObject object = jarray.get(i).getAsJsonObject();
                String nameProduct = object.get("nameProduct").getAsString();
                String category = object.get("category").getAsString();
                String price = object.get("price").getAsString().replace(",",".");
                productService.save(new Product(nameProduct, category, Double.parseDouble(price)));
            }
        } catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonArray  jarray = root.getAsJsonArray();
            for (int i = 0; i < jarray.size(); i++) {
                JsonObject object = jarray.get(i).getAsJsonObject();
                String nameProduct = object.get("nameProduct").getAsString();
                String category = object.get("category").getAsString();
                String price = object.get("price").getAsString().replace(",",".");
                String id = object.get("id").getAsString();
                productService.update(new Product(Integer.parseInt(id), nameProduct, category, Double.parseDouble(price)));
            }
        } catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            productService.delete(Integer.parseInt(id));
        }catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (ExceptionsParamenter ex) {
            throw new ExceptionsParamenter(ex.getMessage());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


