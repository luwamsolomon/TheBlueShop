package edu.mum.cs.inventorymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "merchant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="tbl_merchant",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"merchantName"}
        )
)
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long merchantId;
    //@NotEmpty(message = "*Merchant Name is required")
    private String merchantName;
    private String merchantStatus="Active";
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Dealer> dealers;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Salesperson> salespersonList;

    private String merchantImageUrl;

    @OneToOne(cascade = CascadeType.PERSIST)//(mappedBy = "merchant" , cascade = CascadeType.PERSIST, optional = false)
    private User user;
   // @NotEmpty(message = "*address is required")
    private String address;

    public Merchant() {
    }


    public Merchant(String merchantName, String merchantStatus,User user,String address) {
        this.merchantName = merchantName;
        this.merchantStatus = merchantStatus;
        this.user = user;
        this.address = address;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Salesperson> getSalespersonList() {
        return salespersonList;
    }

    public void setSalespersonList(List<Salesperson> salespersonList) {
        this.salespersonList = salespersonList;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(String merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public String getMerchantImageUrl() {
        return merchantImageUrl;
    }

    public void setMerchantImageUrl(String merchantImageUrl) {
        this.merchantImageUrl = merchantImageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
