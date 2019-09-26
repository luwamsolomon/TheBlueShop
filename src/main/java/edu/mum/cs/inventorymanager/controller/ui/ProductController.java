package edu.mum.cs.inventorymanager.controller.ui;

import java.security.Principal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.inventorymanager.config.RabbitConfig;
import edu.mum.cs.inventorymanager.model.entity.Buyer;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Product;
import edu.mum.cs.inventorymanager.service.contract.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

//	@Autowired
//	private RabbitListener listener;
//	
    @Autowired
    private ProductService productService;

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView products(Principal principal, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if(principal==null){
            mav.setViewName("common/login");
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Product> products = productService.findAllByMerchant(merchant);
        mav.addObject("products", products);
        mav.setViewName("products/index");
        return mav;
    }

    @GetMapping(value = "/new")
    public String createProduct(Model model, Principal principal) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping(value = "/new")
    public String createNewProduct(@Valid @ModelAttribute("product") Product product,
                                   BindingResult bindingResult, Model model, Principal principal, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("product", product);
            return "product/new";
        }
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            product.setMerchant(merchant);
        }
        productService.create(product);

        return "redirect:/products/";
    }

    @GetMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/edit";
        }
        return "products/index";
    }

    @PostMapping(value = "/edit")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("product", product);
            return "products/edit";
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        product.setMerchant(merchant);
        productService.update(product);
        return "redirect:/products/index";
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("product", product);
            return "products/edit";
        }
        productService.delete(product);
        return "redirect:/products/index";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable long id) {

        productService.deleteById(id);
        return "redirect:/products/index";
    }
    
    @GetMapping(value="/buy")
    public String buy(Model model, Principal principal) {
    	
    	model.addAttribute("buyer", new Buyer());
    	
    	return "buy/buy";
    	
    }
    
    @PostMapping(value="/buy")
    public String buy1(@ModelAttribute("buyer") Buyer buyer) throws InterruptedException {
    	//System.out.println(buyer);
    	
    	rabbitTemplate.convertAndSend(RabbitConfig.getQueue_name(),buyer);
		//listener.getCountDownLatch().await(10000, TimeUnit.MICROSECONDS);
	
    	return "redirect:/sale/index";
    }
}
