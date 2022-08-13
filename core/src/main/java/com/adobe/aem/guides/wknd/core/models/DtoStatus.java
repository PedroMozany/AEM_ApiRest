package com.adobe.aem.guides.wknd.core.models;

public class DtoStatus {
    private String message;
    private int status;

    public DtoStatus(int status, String message) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status: " + status + "\n" +
                "Message: " + message;
    }
}
