package edu.mum.cs.inventorymanager.model.dto;

import edu.mum.cs.inventorymanager.model.entity.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationDTO {

    private long locationId;
    private String locationName;
    private String address;
    private List<TerminalDTO> terminals;
//    private MerchantDTO merchant;

    public LocationDTO(Location location) {
        locationId = location.getLocationId();
        locationName = location.getLocationName();
        address = location.getAddress();
        terminals = location.getTerminals().stream().map(terminal -> new TerminalDTO(terminal)).collect(Collectors.toList());
//        merchant = new MerchantDTO(location.getMerchant());
    }
}
