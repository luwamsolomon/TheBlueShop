package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.UserType;
import edu.mum.cs.inventorymanager.model.entity.Item;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Order;
import edu.mum.cs.inventorymanager.model.entity.Product;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import edu.mum.cs.inventorymanager.service.contract.OrderService;
import edu.mum.cs.inventorymanager.service.contract.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleProductController {
    
	
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView products(Principal principal, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (principal == null) {
            mav.setViewName("common/login");
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        AppUser appUser = (AppUser) session.getAttribute("appUser");
        List<Product> products = productService.findAllByMerchant(merchant);
        mav.addObject("products", products);
        if(UserType.MERCHANT.getType().equals(appUser.getUser().getUserType())) {
            mav.setViewName("users/merchant/sale");
        }else if(UserType.SALESPERON.getType().equals(appUser.getUser().getUserType())) {
            mav.setViewName("users/saler/sale");
        }
        return mav;
    }


    @GetMapping(value = {"/add/{id}"})
    public String addProduct(@PathVariable Long id, Model model, HttpSession session) {
        Product product = productService.findById(id);
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Product> products = productService.findAllByMerchant(merchant);
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<>();
            cart.add(new Item(product, 1));
            model.addAttribute("products", products);
            // model.addAttribute("cart", cart);
            session.setAttribute("cart", cart);
//            return "users/merchant/sale";
        } else {
            model.addAttribute("products", products);
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExists(id, cart);
            if (index == -1) {
                cart.add(new Item(product, 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        AppUser appUser = (AppUser) session.getAttribute("appUser");
        if(UserType.MERCHANT.getType().equals(appUser.getUser().getUserType())) {
            return  "users/merchant/sale";
        }else if(UserType.SALESPERON.getType().equals(appUser.getUser().getUserType())) {
            return "users/saler/sale";
        }
        return "redirect:/login";
    }


    private int isExists(Long id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @GetMapping(value = {"/buy"})
    public ModelAndView buyProduct(HttpSession session, Principal principal, Model model) {
        ModelAndView mav = new ModelAndView();
        if (principal == null) {
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        } else {
            AppUser appUser = (AppUser) session.getAttribute("appUser");
            if(UserType.MERCHANT.getType().equals(appUser.getUser().getUserType())) {
                mav.setViewName("users/merchant/sale");
            }else if(UserType.SALESPERON.getType().equals(appUser.getUser().getUserType())) {
                mav.setViewName("users/saler/sale");
            }

        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        String userName = (String) session.getAttribute("loginUsername");
        List<Item> cart = (List<Item>) session.getAttribute("cart");

        if (cart.size() > 0) {
            Order order = orderService.sellProduct(cart, merchant,userName);
            boolean isSuccessful = order.getOrderId() > 0;
            if (isSuccessful) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
                List<Product> products = productService.findAllByMerchant(merchant);
                model.addAttribute("products", products);
                model.addAttribute("orderResponseMessage", "Sale Successful, OrderCode is "+order.getOrderCode());
            }
        }
        return mav;
    }
}
