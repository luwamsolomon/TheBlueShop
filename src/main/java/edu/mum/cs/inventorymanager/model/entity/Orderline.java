package edu.mum.cs.inventorymanager.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_orderline")
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Product product;
    private long quantityBought;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public Orderline() {
    }

    public Orderline(Product product, long quantityBought) {
        this.product = product;
        this.quantityBought = quantityBought;
    }

    public Orderline(Product product, long quantityBought, Order order) {
        this.product = product;
        this.quantityBought = quantityBought;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
