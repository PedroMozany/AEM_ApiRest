package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;

public interface ClientDao {

    Client authentication(int id, String name) throws ExceptionsParamenter;
    Client search(int id);
    Client save(Client client);
    void update(Client client);
    void delete(int id) throws ExceptionsParamenter;
}
