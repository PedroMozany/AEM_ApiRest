package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.InvoiceDao;
import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Dto;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.Product;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
@Component(immediate = true, service = InvoiceService.class)
public class InvoiceServiceImpl implements InvoiceService {

    @Reference
    InvoiceDao invoiceDao;


    @Override
    public void resgitration(Invoice invoice) throws ExceptionsParamenter {
        invoiceDao.resgitration(invoice);
    }

    @Override
    public void deleteProduct(int idClient, int idProduct) throws ExceptionsParamenter {
        invoiceDao.deleteProduct(idClient,idProduct);

    }

    @Override
    public List<Product> productBuy(int idClient) {
        return invoiceDao.productBuy(idClient);
    }

    @Override
    public List<Dto> getInvoice() {
        return invoiceDao.getInvoice();
    }
}
