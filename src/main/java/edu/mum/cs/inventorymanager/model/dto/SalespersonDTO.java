package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Salesperson;

public class SalespersonDTO {

    private long salesmanId;
    private String staffId;
    private UserDTO user;
    private LocationDTO location;
    private String status="Active";
//    private MerchantDTO merchant;

    public SalespersonDTO(Salesperson salesperson) {
        salesmanId = salesperson.getSalesmanId();
        staffId = salesperson.getStaffId();
        user = new UserDTO(salesperson.getUser());
        location = new LocationDTO(salesperson.getLocation());
        status = salesperson.getStatus();
//        merchant = new MerchantDTO(salesperson.getMerchant());
    }

    public long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
