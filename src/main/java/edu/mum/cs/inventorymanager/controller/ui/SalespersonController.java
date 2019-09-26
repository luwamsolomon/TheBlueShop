package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.config.RabbitConfig;
import edu.mum.cs.inventorymanager.model.entity.Buyer;
import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Salesperson;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.service.contract.LocationService;
import edu.mum.cs.inventorymanager.service.contract.SalespersonService;
import edu.mum.cs.inventorymanager.utils.EncrytedPasswordUtils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/salespersons")
public class SalespersonController {
     
	@Autowired
	private RabbitTemplate rabbitTemplate;
//
//	@Autowired
//	private RabbitListener listener;
//	
    @Autowired
    private SalespersonService salespersonService;
    @Autowired
    private LocationService locationService;

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView salespersons(Principal principal, HttpSession session,Model model) {
        ModelAndView mav = new ModelAndView();
        if(principal==null){
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Salesperson> salespersons = salespersonService.findAllByMerchant(merchant);
        mav.addObject("salespersons", salespersons);
        mav.setViewName("salespersons/index");
        return mav;
    }

    @GetMapping(value = "/new")
    public String createSalespersonForm(Model model,Principal principal,HttpSession session) {
        model.addAttribute("salesperson", new Salesperson());
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            List<Location> locations = locationService.findMerchantLocations(merchant);
            model.addAttribute("locations",locations);
        }else{
            List<Location> locations = new ArrayList<>();
            model.addAttribute("locations",locations);
        }

        return "salespersons/new";
    }

    @PostMapping(value = "/new")
    public String registerNewSalesperson(@Valid @ModelAttribute("salesperson") Salesperson salesperson,
                                         BindingResult bindingResult, Model model, HttpSession session, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("salesperson", salesperson);
            return "merchants/new";
        }
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            salesperson.setMerchant(merchant);
        }
        String encryptedPassword = EncrytedPasswordUtils.encrytePassword(salesperson.getUser().getAppUser().getEncrytedPassword());
        salesperson.getUser().getAppUser().setEncrytedPassword(encryptedPassword);
        salespersonService.create(salesperson);
        return "redirect:/salespersons/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editSalesperson(@PathVariable Long id, Model model,HttpSession session) {
        Salesperson s = salespersonService.findById(id);
        if (s != null) {
            model.addAttribute("salesperson", s);
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            List<Location> locations = locationService.findMerchantLocations(merchant);
            model.addAttribute("locations",locations);
            return "salespersons/edit";
        }
        return "salespersons/edit";
    }

    @PostMapping(value = "/edit")
    public String updateSalesperson(@Valid @ModelAttribute("salesman") Salesperson salesperson,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("salesperson", salesperson);
            return "salespersons/edit";
        }
        salespersonService.update(salesperson);
        return "redirect:/salespersons";
    }
    
    @GetMapping(value="/buy")
    public String buy(Model model, Principal principal) {
    	
    	model.addAttribute("buyer", new Buyer());
    	
    	return "buy/buy2";
    	
    }
    
    @PostMapping(value="/buy")
    public String buy1(@ModelAttribute("buyer") Buyer buyer) throws InterruptedException {
    	//System.out.println(buyer);
    	
    	rabbitTemplate.convertAndSend(RabbitConfig.getQueue_name(),buyer);
		//listener.getCountDownLatch().await(10000, TimeUnit.MICROSECONDS);
	
    	return "redirect:/sale/index";
    }
}
