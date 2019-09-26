package edu.mum.cs.inventorymanager.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import edu.mum.cs.inventorymanager.model.dto.MerchantDTO;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.page.Login;
import edu.mum.cs.inventorymanager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = {"/api/login"})
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    ObjectMapper mapper = new ObjectMapper();

    {
        mapper.registerModule(new Hibernate4Module());
    }

    @PostMapping
    public MerchantDTO login(@Valid @RequestBody Login login) throws JsonProcessingException {
//        String encryptedPassword = EncrytedPasswordUtils.encrytePassword(login.getPassword());
//        login.setPassword(encryptedPassword);
        login.setPassword("$2a$10$zQ9omxGrcvWPI.xB1waxXeRXgIi2TDc9pG3IUj1WGn6sNl/U8yvSu"); // TODO:
        Merchant merchant = loginService.login(login);
        return new MerchantDTO(merchant);
    }
}
