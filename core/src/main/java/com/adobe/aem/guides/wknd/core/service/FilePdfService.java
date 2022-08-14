package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.exceptions.ExceptionsParamenter;

public interface FilePdfService {

    void writePdf(String report) throws ExceptionsParamenter;
}
