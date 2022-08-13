package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.service.ClientService;
import com.google.gson.*;
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
    public String search(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException, ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("idClient").getAsString();
            Client client = clientService.search(Integer.parseInt(id));
            json = gson.toJson(client);
            return json;
        } catch (IllegalStateException | NullPointerException iex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        } catch (NumberFormatException nfex) {
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (IOException e) {
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
                String  name = object.get("name").getAsString();
                clientService.save(new Client(name));
            }
        } catch (IllegalStateException | NullPointerException iex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            String name = object.get("name").getAsString();
            clientService.update(new Client(Integer.parseInt(id), name));
        } catch (IllegalStateException | NullPointerException iex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        } catch (NumberFormatException nfex) {
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter {
        try {
            root = jp.parse(req.getReader());
            JsonObject object = root.getAsJsonObject();
            String id = object.get("id").getAsString();
            clientService.delete(Integer.parseInt(id));
        } catch (IllegalStateException | NullPointerException iex) {
            throw new ExceptionsParamenter("Please enter the correct parameter in json format");
        } catch (NumberFormatException nfex) {
            throw new ExceptionsParamenter("Parameters are in numeric format");
        }catch (JsonSyntaxException jsex) {
            throw new ExceptionsParamenter("Json syntax wrong");
        }catch (ExceptionsParamenter ex) {
            throw new ExceptionsParamenter(ex.getMessage());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
