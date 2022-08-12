/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.controller.ClientController;
import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsJson;
import com.adobe.aem.guides.wknd.core.models.Client;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class }, property = {
        SLING_SERVLET_METHODS + "=" + "GET",
        SLING_SERVLET_METHODS + "=" + "POST",
        SLING_SERVLET_METHODS + "=" + "PUT",
        SLING_SERVLET_METHODS + "=" + "DELETE",
        SLING_SERVLET_PATHS + "=" + "/bin/client",
        SLING_SERVLET_EXTENSIONS + "=" + "json"
})
@ServiceDescription("Simple Demo Servlet")
public class ClientServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    ClientController clientController;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        String client = clientController.search(req,resp);
        resp.setContentType("application/json");
        resp.getWriter().write(client);
    }
    @Override
    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        String client = clientController.save(req,resp);
        resp.setContentType("application/json");
        resp.getWriter().write(client);
    }
    @Override
    protected void doPut(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        clientController.update(req,resp);
    }
    @Override
    protected void doDelete(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        clientController.delete(req,resp);;
    }
}
