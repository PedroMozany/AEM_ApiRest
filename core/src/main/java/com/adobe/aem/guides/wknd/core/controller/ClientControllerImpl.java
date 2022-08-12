package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.service.ClientService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

@Component(immediate = true, service = ClientController.class)
public class ClientControllerImpl implements ClientController {

    @Reference
    ClientService clientService;

    JsonParser jp = new JsonParser();

    Gson gson = new Gson();

    JsonElement root;

    String json;

    @Override
    public String search(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            Client client = clientService.search(Integer.parseInt(id));
            json = gson.toJson(client);
            return json;
        }  catch (IllegalStateException iex) {
            throw new RuntimeException(iex);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String save(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String name = object.get("name").getAsString();
            Client client = clientService.save(new Client(name));
            json = gson.toJson(client);
            return json;

        } catch (IllegalStateException iex) {
            throw new RuntimeException("Isso nao e json" + iex.getMessage());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            String name = object.get("name").getAsString();
            clientService.update(new Client(Integer.parseInt(id), name));
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
            clientService.delete(Integer.parseInt(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
