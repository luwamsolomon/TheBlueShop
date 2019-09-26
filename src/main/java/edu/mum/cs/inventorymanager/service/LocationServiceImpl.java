package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.dao.LocationDao;
import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.service.contract.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao){
        this.locationDao = locationDao;
    }

    @Override
    public Location findByLocationName(String locationName) {
        return locationDao.findByLocationName(locationName);
    }

    @Override
    public Location findMerchant(Merchant merchant) {
        return locationDao.findMerchant(merchant);
    }

    @Override
    public List<Location> findMerchantLocations(Merchant merchant) {
        return locationDao.findMerchantLocations(merchant);
    }

    @Override
    public Location create(Location location) {
        return locationDao.createLocation(location);
    }

    @Override
    public Location findById(Long id) {
        return locationDao.findById(id);
    }

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public List<Location> findAllByMerchant(Merchant merchant) {
        return locationDao.findAllByMerchant(merchant);
    }

    @Override
    public void delete(Location location) {
        locationDao.delete(location);
    }

    @Override
    public void deleteById(long id) {
        locationDao.deleteById(id);
    }

    @Override
    public Location update(Location location) {
        return locationDao.update(location);
    }
}
