package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.entity.Dealer;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "dealerRepository")
public interface IDealerRepository extends JpaRepository<Dealer, Long> {
    List<Dealer> findAllByMerchant(Merchant merchant);
}
