package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Product;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;

@ExtendWith(AemContextExtension.class)
public class InvoiceDaoTest {

    @Test
    void productBuy(){
        InvoiceDao mockito = Mockito.mock(InvoiceDao.class);
        List<Product> list = mockito.productBuy(1);
        Assert.assertTrue(list.isEmpty());
    }
}
