package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ClientDao;
import com.adobe.aem.guides.wknd.core.dao.ClientDaoImpl;
import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ClientService.class)
public class ClientServiceImpl implements ClientService {


    @Reference
    ClientDao clientDao;

    @Override
    public Client authentication(int id, String name) throws ExceptionsParamenter {
        return clientDao.authentication(id,name);
    }

    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client search(int id) {
        return clientDao.search(id);
    }


    @Override
    public void update(Client client) {
        clientDao.update(client);
    }

    @Override
    public void delete(int id) throws ExceptionsParamenter {
        clientDao.delete(id);
    }
}
