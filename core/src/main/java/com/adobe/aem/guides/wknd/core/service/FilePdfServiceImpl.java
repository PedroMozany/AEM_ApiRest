package com.adobe.aem.guides.wknd.core.service;


import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = FilePdfService.class)
public class FilePdfServiceImpl implements FilePdfService {


    public FilePdfServiceImpl(String txt) {

    }


    @Override
    public void writePdf(String report) {
    }
}
