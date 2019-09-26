package edu.mum.cs.inventorymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs.inventorymanager.model.AmountAdapter;
import edu.mum.cs.inventorymanager.model.DateAdapter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private String orderCode;
    private String paymentMethod;
//    @ManyToOne
//    @JoinColumn(name = "terminalId", nullable = false)
//    private Terminal terminal;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date datePurchased = new Date();
    @XmlJavaTypeAdapter(AmountAdapter.class)
    private Double orderTotal = 0.0;
    private String salesUserName;
    @OneToOne
    @JoinColumn(name = "orderId")
    private Payment payment;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Orderline> orderlines;
    @ManyToOne
    private Merchant merchant;

    public Order() {
    }

    public Order(String orderCode, String paymentMethod, Terminal terminal, Date datePurchased, Double orderTotal, String salesUserName, Payment payment, List<Orderline> orderlines) {
        this.orderCode = orderCode;
        this.paymentMethod = paymentMethod;
//        this.terminal = terminal;
        this.datePurchased = datePurchased;
        this.orderTotal = orderTotal;
        this.salesUserName = salesUserName;
        this.payment = payment;
        this.orderlines = orderlines;
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

//    public Terminal getTerminal() {
//        return terminal;
//    }
//
//    public void setTerminal(Terminal terminal) {
//        this.terminal = terminal;
//    }

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
