package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Orderline;

public class OrderlineDTO {

    private long id;
    private ProductDTO product;
    private long quantityBought;
//    private OrderDTO order;

    public OrderlineDTO(Orderline orderline) {
        id = orderline.getId();
        product = new ProductDTO(orderline.getProduct());
        quantityBought = orderline.getQuantityBought();
    }
}
