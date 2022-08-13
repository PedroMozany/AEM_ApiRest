package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Dto;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.ClientService;
import com.adobe.aem.guides.wknd.core.service.InvoiceService;
import com.google.gson.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component(immediate = true, service = InvoiceController.class)
public class InvoiceControllerImpl implements InvoiceController {

    @Reference
    InvoiceService invoiceService;

    @Reference
    ClientService clientService;

    JsonParser jp = new JsonParser();

    Gson gson = new Gson();

    JsonElement root;

    String json;

    @Override
    public void resgitration(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {

        try {
            root = jp.parse(req.getReader());
            JsonArray  jarray = root.getAsJsonArray();
            for (int i = 0; i < jarray.size(); i++) {
                JsonObject object = jarray.get(i).getAsJsonObject();
               String idProduct = object.get("idProduct").getAsString();
               String idClient = object.get("idClient").getAsString();
               invoiceService.resgitration(new Invoice(Integer.parseInt(idProduct), Integer.parseInt(idClient)));
            }
        } catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        } catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (ExceptionsParamenter ex) {
            throw new ExceptionsParamenter(ex.getMessage());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException, ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonArray  jarray = root.getAsJsonArray();
            for (int i = 0; i < jarray.size(); i++) {
                JsonObject object = jarray.get(i).getAsJsonObject();
                String idProduct = object.get("idProduct").getAsString();
                String idClient = object.get("idClient").getAsString();
                invoiceService.deleteProduct(Integer.parseInt(idClient), Integer.parseInt(idProduct));
            }
        } catch (IllegalStateException | NullPointerException ex) {
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

    @Override
    public String productBuy(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        Random random = new Random();
        try {
            root = jp.parse(req.getReader());
            boolean idClient = gson.toJson(root).contains("idClient");
            if (idClient) {
                JsonObject object = root.getAsJsonObject();
                String value = object.get("idClient").getAsString();
                List<Product> list = invoiceService.productBuy(Integer.parseInt(value));
                Client client = clientService.search(Integer.parseInt(value));
                int number = random.nextInt(100);
                Dto dto = new Dto(number, client, list);
                json = gson.toJson(dto);
                return json;
            } else {
                List<Dto> listInvoice = invoiceService.getInvoice();
                json = gson.toJson(listInvoice);
                return json;
            }

        } catch (IllegalStateException | NullPointerException ex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (NumberFormatException nfex){
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
