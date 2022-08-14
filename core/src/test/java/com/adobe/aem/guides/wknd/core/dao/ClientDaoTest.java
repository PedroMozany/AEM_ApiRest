package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Client;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(AemContextExtension.class)
public class ClientDaoTest {

    @Test
    void search() {
        ClientDao mockito = Mockito.mock(ClientDao.class);
        Client client = mockito.search(1);
        Assert.assertNull(client);
    }
}
