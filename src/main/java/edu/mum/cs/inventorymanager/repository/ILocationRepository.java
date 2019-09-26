package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.entity.Location;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {
    Location findByMerchant(Merchant merchant);
    Location findByLocationId(Long id);
    Location findByLocationName(String locationName);
    List<Location> findAllByMerchant(Merchant merchant);
}
