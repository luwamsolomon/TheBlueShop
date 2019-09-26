package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.security.AppUser;

public class AppUserDTO {

    private long id;
    private String userName;
    private String encrytedPassword;
    private boolean enabled = true;

    public AppUserDTO(AppUser appUser) {
        id = appUser.getId();
        userName = appUser.getUserName();
        encrytedPassword = appUser.getEncrytedPassword();
        enabled = appUser.isEnabled();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}