package com.adobe.aem.guides.wknd.core.exceptions;

import org.osgi.service.component.annotations.Component;

@Component(service = Exception.class)
public class ExceptionsJson extends Exception {

    public  ExceptionsJson(String meng){
        super(meng);
    }

    public ExceptionsJson(Throwable t){
        super(t);
    }
}
