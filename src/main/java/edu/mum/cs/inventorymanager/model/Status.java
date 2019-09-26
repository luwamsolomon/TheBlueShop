package edu.mum.cs.inventorymanager.model;

public enum Status {
    NEW("NEW","NEW"),
    ACTIVE("ACTIVE","ACTIVE"),
    INACTIVE("INACTIVE","INACTIVE"),
    DELETED("DELETED","DELETED");

    private String code;
    private String message;

    Status(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
