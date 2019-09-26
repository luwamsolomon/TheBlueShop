package edu.mum.cs.inventorymanager.controller.api;

import edu.mum.cs.inventorymanager.model.dto.SalespersonDTO;
import edu.mum.cs.inventorymanager.model.entity.Salesperson;
import edu.mum.cs.inventorymanager.service.contract.SalespersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/api/salespersons"})
public class SalespersonRestController {

    @Autowired
    private SalespersonService salespersonService;

    @GetMapping(value = {"", "/"})
    public List<SalespersonDTO> getAllSalespersons() {
        List<Salesperson> salespersons = salespersonService.findAll();
        return salespersons.stream().map(SalespersonDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = {"/{id}"})
    public SalespersonDTO getSalespersonById(@Valid @PathVariable long id) {
        Salesperson salesperson = salespersonService.findById(id);
        return new SalespersonDTO(salesperson);
    }
}
