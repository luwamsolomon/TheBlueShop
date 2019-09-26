package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.service.contract.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(value={"","/","/index","/browse"}, method=RequestMethod.GET)
    public ModelAndView displayMerchantLocations(Principal principal, HttpSession session,Model model) {
        ModelAndView mav = new ModelAndView();
        if(principal==null){
            mav.setViewName("common/login");
            model.addAttribute("login", new Login());
            return mav;
        }
        Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
        List<Location> locations = locationService.findMerchantLocations(merchant);
        mav.addObject("locations", locations);
        mav.setViewName("locations/index");
        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String createForm(Model model){
        model.addAttribute("location", new Location());
        return "locations/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreateForm(@Valid @ModelAttribute("location") Location location,
                                    BindingResult bindingResult, Model model, HttpSession session, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("location",location);
            return "locations/new";
        }
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            location.setMerchant(merchant);
        }
        location = locationService.create(location);
        return "redirect:/locations";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Long id, Model model){
        Location s = locationService.findById(id);
        if (s != null) {
            model.addAttribute("location", s);
            return "locations/edit";
        }
        return "locations/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdateForm(@Valid @ModelAttribute("location") Location location,
                                 BindingResult bindingResult, Model model, Principal principal, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("location", location);
            return "locations/edit";
        }
        if (principal != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchantInfo");
            location.setMerchant(merchant);
        }
        location = locationService.update(location);
        return "redirect:/locations";
    }
}

