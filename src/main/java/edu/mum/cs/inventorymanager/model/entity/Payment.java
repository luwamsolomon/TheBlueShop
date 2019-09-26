package edu.mum.cs.inventorymanager.model.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_payment")
public abstract class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;
    private String referenceId;
    private double paymentAmount;
    @OneToOne
    private Order order;
    private LocalDate paymentDate;
    protected String paymentMethod;
    private String narration;

    public Payment() {
    }

    public Payment(String referenceId, double paymentAmount, Order order, LocalDate paymentDate, String paymentMethod, String narration) {
        this.referenceId = referenceId;
        this.paymentAmount = paymentAmount;
        this.order = order;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.narration = narration;
    }


    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}
