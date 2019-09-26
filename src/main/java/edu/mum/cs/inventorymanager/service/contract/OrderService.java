package edu.mum.cs.inventorymanager.service.contract;

import edu.mum.cs.inventorymanager.model.entity.Item;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Order;

import java.util.List;

public interface OrderService extends AbstractService<Order> {
    Order sellProduct(List<Item> cart, Merchant merchant,String userName);

    List<Order> findAllByMerchantAndUsername(Merchant merchant, String username);
}
