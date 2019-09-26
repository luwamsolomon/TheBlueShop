package edu.mum.cs.inventorymanager.model;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN","Admin Role Type"),
    ROLE_SALESPERSON("ROLE_SALESPERSON","Salesperson Role Type"),
    ROLE_MERCHANT("ROLE_MERCHANT","Merchant Role Type");

    private String roleCode;
    private String description;

    RoleType(String roleCode,String description){
        this.roleCode =roleCode;
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
