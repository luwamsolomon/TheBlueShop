package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.entity.Dealer;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.service.contract.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = {"/dealers"})
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView dealers(Principal principal, HttpSession session,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if(principal==null){
            modelAndView.setViewName("common/login");
            model.addAttribute("login", new Login());
            return modelAndView;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Dealer> dealers = dealerService.findAllByMerchant(merchant);
        modelAndView.addObject("dealers", dealers);
        modelAndView.setViewName("dealers/index");
        return modelAndView;
    }

    @GetMapping(value = "/new")
    public String createDealer(Model model) {
        model.addAttribute("dealer", new Dealer());
        return "dealers/new";
    }

    @PostMapping(value = "/new")
    public String registerNewDealer(@Valid @ModelAttribute("dealer") Dealer dealer,
                                    BindingResult bindingResult, Model model, Principal principal, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dealer", dealer);
            return "dealer/new";
        }
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            dealer.setMerchant(merchant);
        }
        dealerService.save(dealer);
        return "redirect:/dealers/";
    }

    @GetMapping(value = "/edit/{id}")
    public String editMerchant(@PathVariable Long id, Model model) {
        Dealer dealer = dealerService.findOne(id);
        if (dealer != null) {
            model.addAttribute("dealer", dealer);
            return "dealers/edit";
        }
        return "dealers/index";
    }

    @PostMapping(value = "/edit")
    public String updateMerchant(@Valid @ModelAttribute("dealer") Dealer dealer,
                                 BindingResult bindingResult, Model model, Principal principal, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dealer", dealer);
            return "dealers/edit";
        }
        if(principal!=null){
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            dealer.setMerchant(merchant);
        }
        dealerService.save(dealer);
        return "redirect:/dealers/index";
    }
}
