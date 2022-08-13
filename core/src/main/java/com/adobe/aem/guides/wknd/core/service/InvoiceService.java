package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Dto;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.Product;

import java.util.List;

public interface InvoiceService {

    void resgitration(Invoice invoice) throws ExceptionsParamenter;
    void deleteProduct(int number, int idProduct) throws ExceptionsParamenter;
    List<Product> productBuy(int idClient);
    List<Dto> getInvoice();
}
