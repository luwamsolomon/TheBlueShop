package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import edu.mum.cs.inventorymanager.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;
    private final MerchantService merchantService;

    @Autowired
    public LoginService(UserService userService,MerchantService merchantService){
        this.userService = userService;
        this.merchantService = merchantService;
    }

    public Merchant login(Login login){
        User user = userService.findByUserNameAndPassword(login.getUsername(),login.getPassword());
        if(user!=null){
            return merchantService.findUser(user);
        }
        return null;
    }
}
