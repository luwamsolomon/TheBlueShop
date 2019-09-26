package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Payment;

import java.time.LocalDate;

public class PaymentDTO {

    private long paymentId;
    private String referenceId;
    private double paymentAmount;
    private OrderDTO order;
    private LocalDate paymentDate;
    protected String paymentMethod;
    private String narration;

    public PaymentDTO(Payment payment) {
        paymentId = payment.getPaymentId();
        referenceId = payment.getReferenceId();
        paymentAmount = payment.getPaymentAmount();
        order = new OrderDTO(payment.getOrder());
        paymentDate = payment.getPaymentDate();
        paymentMethod = payment.getPaymentMethod();
        narration = payment.getNarration();
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
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
