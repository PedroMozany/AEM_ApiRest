package com.adobe.aem.guides.wknd.core.exceptions;

import org.osgi.service.component.annotations.Component;

@Component(service = Exception.class)
public class ExceptionsParamenter extends Exception {


    public ExceptionsParamenter(String meng) {
        super(meng);
    }
}
