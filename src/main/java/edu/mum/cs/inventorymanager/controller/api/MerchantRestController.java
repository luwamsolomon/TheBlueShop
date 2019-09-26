package edu.mum.cs.inventorymanager.controller.api;

import edu.mum.cs.inventorymanager.model.dto.MerchantDTO;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/merchants")
public class MerchantRestController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping(value = {"", "/"})
    public List<MerchantDTO> getAllMerchants() {
        List<Merchant> merchants = merchantService.findAll();
        return merchants.stream().map(MerchantDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public MerchantDTO getMerchantById(@Valid @PathVariable long id) {
        Merchant merchant = merchantService.findById(id);
        return new MerchantDTO(merchant);
    }
}
