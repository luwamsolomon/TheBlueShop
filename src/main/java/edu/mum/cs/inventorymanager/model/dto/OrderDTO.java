package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Order;
import edu.mum.cs.inventorymanager.model.entity.Orderline;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {

    private long orderId;
    private String orderCode;
    private String paymentMethod;
    private TerminalDTO terminal;
    private Date datePurchased = new Date();
    private Double orderTotal = 0.0;
    private String salesUserName;
    private PaymentDTO payment;
    private List<OrderlineDTO> orderlines;

    public OrderDTO(Order order) {
        orderId = order.getOrderId();
        orderCode = order.getOrderCode();
        paymentMethod = order.getPaymentMethod();
//        terminal = new TerminalDTO(order.getTerminal());
        datePurchased = order.getDatePurchased();
        orderTotal = order.getOrderTotal();
        salesUserName = order.getSalesUserName();
        payment = new PaymentDTO(order.getPayment());
        orderlines = order.getOrderlines().stream().map(orderline -> new OrderlineDTO(orderline)).collect(Collectors.toList());
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

    public TerminalDTO getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalDTO terminal) {
        this.terminal = terminal;
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

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderlineDTO> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<OrderlineDTO> orderlines) {
        this.orderlines = orderlines;
    }
}
