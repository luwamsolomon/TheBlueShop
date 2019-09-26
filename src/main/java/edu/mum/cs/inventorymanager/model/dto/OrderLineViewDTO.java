package edu.mum.cs.inventorymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs.inventorymanager.model.AmountAdapter;
import edu.mum.cs.inventorymanager.model.DateAdapter;
import edu.mum.cs.inventorymanager.model.entity.Product;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLineViewDTO {
    private long id;
    private Product product;
    private long quantityBought;
    private long orderId;
    private String orderCode;
    private String paymentMethod;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date datePurchased = new Date();
    @XmlJavaTypeAdapter(AmountAdapter.class)
    private Double orderTotal = 0.0;
    private String salesUserName;

    public OrderLineViewDTO() {
    }

    public OrderLineViewDTO(long id, Product product, long quantityBought, long orderId, String orderCode, String paymentMethod, Date datePurchased, Double orderTotal, String salesUserName) {
        this.id = id;
        this.product = product;
        this.quantityBought = quantityBought;
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.paymentMethod = paymentMethod;
        this.datePurchased = datePurchased;
        this.orderTotal = orderTotal;
        this.salesUserName = salesUserName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(long quantityBought) {
        this.quantityBought = quantityBought;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getSalesUserName() {
        return salesUserName;
    }

    public void setSalesUserName(String salesUserName) {
        this.salesUserName = salesUserName;
    }
}
