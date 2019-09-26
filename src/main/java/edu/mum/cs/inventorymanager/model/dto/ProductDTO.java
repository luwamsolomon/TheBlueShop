package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Product;

public class ProductDTO {

    private long id;
    private String name;
    private double costPrice;
    private double sellingPrice;
    private int quantity;
    private String status;
//    private Merchant merchant;

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        costPrice = product.getCostPrice();
        sellingPrice = product.getSellingPrice();
        quantity = product.getQuantity();
        status = product.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
