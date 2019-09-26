package edu.mum.cs.inventorymanager.service.contract;

import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;

import java.util.List;


public interface LocationService extends AbstractService<Location>{
    public Location findByLocationName(String locationName);
    public Location findMerchant(Merchant merchant);
    public List<Location> findMerchantLocations(Merchant merchant);
}
