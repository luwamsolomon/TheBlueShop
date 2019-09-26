package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.dto.OrderLineViewDTO;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Order;
import edu.mum.cs.inventorymanager.model.entity.Orderline;
import edu.mum.cs.inventorymanager.model.entity.Product;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import edu.mum.cs.inventorymanager.service.contract.OrderService;
import edu.mum.cs.inventorymanager.service.contract.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/merchant/salesreport" )
    public ModelAndView salesReport(Model model, HttpSession session, Principal principal) {
        ModelAndView mav = new ModelAndView();
        if(principal==null){
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Order> orders = orderService.findAllByMerchant(merchant);
        mav.addObject("orders",orders);
//        model.addAttribute("product", new Product());
        mav.setViewName("users/merchant/salesreport");
        return mav;
    }

    @GetMapping(value = "/merchant/byproduct" )
    public ModelAndView reportByProduct(Model model, HttpSession session, Principal principal) {
        ModelAndView mav = new ModelAndView();
        if (principal == null) {
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Order> orders = orderService.findAllByMerchant(merchant);
        List<OrderLineViewDTO> orderLineViewDTOS = orderLineViewDTOS(orders);
        mav.addObject("orderLines", orderLineViewDTOS);
//        model.addAttribute("product", new Product());
        mav.setViewName("users/merchant/byproduct");
        return mav;
    }



    @GetMapping(value = "/saler/salesreport" )
    public ModelAndView salesReportSaler(Model model, HttpSession session, Principal principal) {
        ModelAndView mav = new ModelAndView();
        if(principal==null){
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        String username = (String) session.getAttribute("loginUsername");
        List<Order> orders = orderService.findAllByMerchantAndUsername(merchant,username);
        mav.addObject("orders",orders);
//        model.addAttribute("product", new Product());
        mav.setViewName("users/saler/salesreport");
        return mav;
    }

    @GetMapping(value = "/saler/byproduct" )
    public ModelAndView reportByProductSaler(Model model, HttpSession session, Principal principal) {
        ModelAndView mav = new ModelAndView();
        if (principal == null) {
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Order> orders = orderService.findAllByMerchant(merchant);
        List<OrderLineViewDTO> orderLineViewDTOS = orderLineViewDTOS(orders);
        mav.addObject("orderLines", orderLineViewDTOS);
//        model.addAttribute("product", new Product());
        mav.setViewName("users/saler/byproduct");
        return mav;
    }

    private List<OrderLineViewDTO> orderLineViewDTOS(List<Order> orders){
        List<OrderLineViewDTO> orderLineViewDTOS = new ArrayList<>();
        for(Order order : orders){
            for(Orderline orderline : order.getOrderlines()){
                OrderLineViewDTO orderLineViewDTO = new OrderLineViewDTO(orderline.getId(),orderline.getProduct(),orderline.getQuantityBought(),order.getOrderId(),order.getOrderCode(),order.getPaymentMethod(),order.getDatePurchased(),order.getOrderTotal(),order.getSalesUserName());
                orderLineViewDTOS.add(orderLineViewDTO);
            }
        }
        return  orderLineViewDTOS;
    }
}
