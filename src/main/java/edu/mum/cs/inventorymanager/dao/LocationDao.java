package edu.mum.cs.inventorymanager.dao;

import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationDao {

    @Autowired
    ILocationRepository repository;

    public Location update(Location location) {
        return repository.save(location);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Location location) {
        repository.delete(location);
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(Long id) {
        return repository.findByLocationId(id);
    }

    public Location findByLocationName(String locationName) {
        return repository.findByLocationName(locationName);
    }

    public Location findMerchant(Merchant merchant) {
        return null;//repository.findByMerchant(merchant);
    }

    public Location createLocation(Location location) {
        return repository.save(location);
    }

    public List<Location> findMerchantLocations(Merchant merchant) {
        return repository.findAllByMerchant(merchant);
    }

    public List<Location> findAllByMerchant(Merchant merchant) {
        return repository.findAllByMerchant(merchant);
    }
}
