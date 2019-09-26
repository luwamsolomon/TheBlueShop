package edu.mum.cs.inventorymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs.inventorymanager.model.entity.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "merchant")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchantDTO {

    private long merchantId;
    private String merchantName;
    private String merchantStatus = "Active";
    private String merchantImageUrl;
    private String address;
    private UserDTO user;
//    private List<LocationDTO> locations;
//    private List<DealerDTO> dealers;
//    private List<ProductDTO> products;
//    private List<SalespersonDTO> salespersonList;


    public MerchantDTO(Merchant merchant) {
        this.merchantId = merchant.getMerchantId();
        this.merchantName = merchant.getMerchantName();
        this.merchantStatus = merchant.getMerchantStatus();
        this.merchantImageUrl = merchant.getMerchantImageUrl();
        this.address = merchant.getAddress();
        this.user = new UserDTO(merchant.getUser());
//        this.locations = merchant.getLocations().stream().map(location -> new LocationDTO(location)).collect(Collectors.toList());
//        this.dealers = merchant.getDealers().stream().map(dealer -> new DealerDTO(dealer)).collect(Collectors.toList());
//        this.products = merchant.getProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
//        this.salespersonList = merchant.getSalespersonList().stream().map(salesperson -> new SalespersonDTO(salesperson)).collect(Collectors.toList());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
