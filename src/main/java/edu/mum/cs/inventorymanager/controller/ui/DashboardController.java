package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.UserType;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/dashbaord")
public class DashboardController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginForm(Model model, HttpSession session, Principal principal) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (principal != null) {
            AppUser appUser = merchantService.findAppUserByUsername(principal == null ? "" : principal.getName());
            Merchant merchant = merchantService.findMerchantByAppUser(appUser);
            session.setAttribute("merchantInfo", merchant);
            session.setAttribute("appUser", appUser);
            session.setAttribute("loginUsername", appUser.getUser().getFirstName());
            modelAndView.addObject("merchant",merchant);
            if (UserType.MERCHANT.getType().equals(appUser.getUser().getUserType())) {
                modelAndView.setViewName("users/merchant/dashboard");
            } else if (UserType.SALESPERON.getType().equals(appUser.getUser().getUserType())) {
                modelAndView.setViewName("users/saler/salerIndex");
            }
        }  else {
            model.addAttribute("login", new Login());
            modelAndView.setViewName("common/login");
        }

        return modelAndView;
    }

}