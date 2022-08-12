package com.adobe.aem.guides.wknd.core.controller;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface ProductController {
    String searchParamenters(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException;
    void save(SlingHttpServletRequest req, SlingHttpServletResponse resp);
    void update(SlingHttpServletRequest req, SlingHttpServletResponse resp);
    void delete(SlingHttpServletRequest req, SlingHttpServletResponse resp);
}
