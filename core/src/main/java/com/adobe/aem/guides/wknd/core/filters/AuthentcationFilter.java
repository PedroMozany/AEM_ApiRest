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
package com.adobe.aem.guides.wknd.core.filters;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.DtoStatus;
import com.adobe.aem.guides.wknd.core.service.ClientService;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.engine.EngineConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.osgi.service.component.propertytypes.ServiceVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Simple servlet filter component that logs incoming requests.
 */
@Component(service = Filter.class,
        property = {
                EngineConstants.SLING_FILTER_SCOPE + "=" + EngineConstants.FILTER_SCOPE_REQUEST,
        })
@ServiceDescription("Demo to filter incoming requests")
@ServiceRanking(-700)
@ServiceVendor("Adobe")
public class AuthentcationFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    ClientService clientService;


    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        SlingHttpServletRequest req = (SlingHttpServletRequest) request;
        SlingHttpServletResponse resp = (SlingHttpServletResponse) response;

        try {

            String id = request.getParameter("id");
            String name = request.getParameter("name");


            if (id != null && name != null) {
                try {
                    Client client = clientService.authentication(Integer.parseInt(id), name);
                    if(client != null){
                        filterChain.doFilter(request, response);
                    }else{
                        response.setContentType("application/json");
                        resp.setStatus(500);
                        response.getWriter().write(new Gson().toJson(new DtoStatus(resp.getStatus(),"Client does not exist")));
                    }

                } catch (ExceptionsParamenter e) {
                    response.setContentType("application/json");
                    resp.setStatus(500);
                    response.getWriter().write(new Gson().toJson(new DtoStatus(resp.getStatus(),e.getMessage())));
                }
            }else{
                response.setContentType("application/json");
                resp.setStatus(500);
                response.getWriter().write(new Gson().toJson(new DtoStatus(resp.getStatus(),"Please check the parameters Authentication")));
            }
        } catch (NullPointerException e) {
            response.setContentType("application/json");
            resp.setStatus(500);
            response.getWriter().write(new Gson().toJson(new DtoStatus(resp.getStatus(),"Please check the parameters Authentication")));
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}