package com.adobe.aem.guides.wknd.core.dao;


import com.adobe.aem.guides.wknd.core.models.Product;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;

@ExtendWith(AemContextExtension.class)
public class ProductDaoTest {

    @Test
    void searchId(){
        ProductDao mockito = Mockito.mock(ProductDao.class);
        Product product = mockito.searchId(1);
        Assert.assertNull(product);
    }

    @Test
    void searchKeyword(){
        ProductDao mockito = Mockito.mock(ProductDao.class);
        List<Product> list = mockito.searchKeyword("moleto");
        Assert.assertTrue(list.isEmpty());
    }


    @Test
    void ListOrderLow(){
        ProductDao mockito = Mockito.mock(ProductDao.class);
        List<Product> list = mockito.ListOrderLow();
        Assert.assertTrue(list.isEmpty());
    }


    @Test
    void ListCategory(){
        ProductDao mockito = Mockito.mock(ProductDao.class);
        List<Product> list = mockito.ListCategory("alimento");
        Assert.assertTrue(list.isEmpty());
    }

}
