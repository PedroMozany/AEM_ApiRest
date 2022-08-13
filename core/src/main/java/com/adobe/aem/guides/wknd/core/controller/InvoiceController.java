package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface InvoiceController {

    void resgitration(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter;
    void deleteProduct(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException, ExceptionsParamenter;
    String productBuy(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter;

}
