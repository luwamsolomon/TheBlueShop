package edu.mum.cs.inventorymanager.controller.api;

import edu.mum.cs.inventorymanager.model.dto.LocationDTO;
import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.service.contract.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/api/locations"})
public class LocationRestController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = {"", "/"})
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationService.findAll();
        return locations.stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public LocationDTO getLocationById(@Valid @PathVariable long id) {
        Location location = locationService.findById(id);
        return new LocationDTO(location);
    }
}
