package edu.mum.cs.inventorymanager.controller.api;

import edu.mum.cs.inventorymanager.model.dto.DealerDTO;
import edu.mum.cs.inventorymanager.model.entity.Dealer;
import edu.mum.cs.inventorymanager.service.contract.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/dealers")
public class DealerRestController {

    @Autowired
    private DealerService dealerService;

    @GetMapping(value = {"", "/"})
    public List<DealerDTO> getAllDealers() {
        List<Dealer> dealers = dealerService.findAll();
        return dealers.stream().map(DealerDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public DealerDTO getDealerById(@Valid @PathVariable long id) {
        Dealer dealer = dealerService.findOne(id);
        return new DealerDTO(dealer);
    }
}
