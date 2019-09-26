package edu.mum.cs.inventorymanager.buy;
import java.io.Serializable;
import java.util.Set;

 public class Product implements Serializable {
	private String name;
    private String costPrice;
    private String sellingPrice;
    private String quantity;

    
	   public Product() { }


	public Product(String name, String costPrice, String sellingPrice, String quantity) {
		
		this.name = name;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}


	public String getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


    	
 }