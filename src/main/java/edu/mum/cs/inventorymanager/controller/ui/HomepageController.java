package edu.mum.cs.inventorymanager.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

	@RequestMapping(value={"/home","/","/index",""}, method=RequestMethod.GET)
	public String homepage() {
		return "homepage/index";
	}

}