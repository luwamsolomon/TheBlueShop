package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.model.entity.*;
import edu.mum.cs.inventorymanager.repository.IProductRepository;
import edu.mum.cs.inventorymanager.repository.OrderRepository;
import edu.mum.cs.inventorymanager.service.contract.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByMerchant(Merchant merchant) {
        return orderRepository.findAllByMerchant(merchant);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order sellProduct(List<Item> cart,Merchant merchant,String userName) {
        Order order = toOrder(cart,merchant,userName);
        Order savedOrder = create(order);
        boolean isSuccessful =  savedOrder.getOrderId() > 0 ? true : false;
        if(isSuccessful){
            decrementProductQuantity(cart);
        }
        return savedOrder;
    }

    @Override
    public List<Order> findAllByMerchantAndUsername(Merchant merchant, String username) {
        return orderRepository.findAllByMerchantAndSalesUserName(merchant,username);
    }

    private void decrementProductQuantity(List<Item> cart) {
        for(Item item  : cart){
            Product product  = productRepository.findById(item.getProduct().getId()).orElse(null);
            if(product!=null){
                product.setQuantity(product.getQuantity() - item.getQuantity());
            }
            productRepository.save(product);
        }
    }

    private Order toOrder(List<Item> cart,Merchant merchant,String userName) {
        Order order = new Order();
        List<Orderline> orderLines = new ArrayList<>();
        double orderTotal = 0 ;
        order.setOrderlines(orderLines);
        String orderCode = UUID.randomUUID().toString();
        order.setOrderCode(orderCode);
        order.setDatePurchased(new Date());
        order.setPaymentMethod("Cash");
        order.setSalesUserName(userName);
        order.setMerchant(merchant);
        for (Item item : cart) {
            Orderline orderline = new Orderline(item.getProduct(), item.getQuantity());
            orderline.setOrder(order);
            orderLines.add(orderline);
            orderTotal += item.getQuantity() * item.getProduct().getSellingPrice();
        }
        order.setOrderTotal(orderTotal);
        return order;
    }
}
