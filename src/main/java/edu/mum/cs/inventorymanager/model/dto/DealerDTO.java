package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Dealer;

public class DealerDTO {

    private long dealerId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String contactAddress;
//    private MerchantDTO merchant;

    public DealerDTO(Dealer dealer) {
        dealerId = dealer.getDealerId();
        firstName = dealer.getFirstName();
        lastName = dealer.getLastName();
        mobile = dealer.getMobile();
        email = dealer.getEmail();
        contactAddress = dealer.getContactAddress();
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
