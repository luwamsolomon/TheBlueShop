package edu.mum.cs.inventorymanager.model;

public enum UserType {
    MERCHANT("MERCHANT","Merchant user type"),
    SALESPERON("SALESPERON","Salesperson user type");

    private String type;
    private String description;

    UserType(String type, String description){
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
