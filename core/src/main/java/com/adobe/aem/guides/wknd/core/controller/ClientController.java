package com.adobe.aem.guides.wknd.core.controller;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface ClientController {
    String search(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException, ExceptionsParamenter;
    void save(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter;
    void update(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter;
    void delete(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ExceptionsParamenter;
}
