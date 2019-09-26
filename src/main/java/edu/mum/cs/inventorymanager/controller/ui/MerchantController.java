package edu.mum.cs.inventorymanager.controller.ui;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import edu.mum.cs.inventorymanager.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/merchants")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@RequestMapping(value={"/","/index","/browse"}, method=RequestMethod.GET)
	public ModelAndView displayMerchants() {
		ModelAndView mav = new ModelAndView();
		List<Merchant> merchants = merchantService.findAll();
		mav.addObject("merchants", merchants);
		mav.setViewName("merchants/index");
		return mav;
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String createMerchantForm(Model model){
		model.addAttribute("merchant", new Merchant());
		return "merchants/new";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String registerNewMerchant(@Valid @ModelAttribute("merchant") Merchant merchant,
									  BindingResult bindingResult, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			model.addAttribute("merchant",merchant);
			return "merchants/new";
		}
		String encryptedPassword = EncrytedPasswordUtils.encrytePassword(merchant.getUser().getAppUser().getEncrytedPassword());
		merchant.getUser().getAppUser().setEncrytedPassword(encryptedPassword);
		merchant = merchantService.create(merchant);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editMerchant(@PathVariable Long id, Model model){
		Merchant s = merchantService.findById(id);
		if (s != null) {
			model.addAttribute("merchant", s);
			return "merchants/edit";
		}
		return "merchants/dashboard";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateMerchant(@Valid @ModelAttribute("merchant") Merchant merchant,
                                BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			model.addAttribute("merchant", merchant);
			return "merchants/edit";
		}
		merchant = merchantService.update(merchant);
		return "redirect:/merchants/dashboard";
	}
}
