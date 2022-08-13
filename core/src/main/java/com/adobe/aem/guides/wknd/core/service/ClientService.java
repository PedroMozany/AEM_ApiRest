package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;

public interface ClientService {
     Client save(Client client);
     Client search(int id);
     void update(Client client);
     void delete(int id) throws ExceptionsParamenter;
}
