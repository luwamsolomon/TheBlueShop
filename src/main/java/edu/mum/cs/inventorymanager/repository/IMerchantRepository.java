package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("merchantRepository")
public interface IMerchantRepository extends JpaRepository<Merchant, Long> {

    Merchant findMerchantByMerchantId(long id);

    Merchant findByMerchantName(String merchantName);

    Merchant findByUser(User user);

//    Merchant findByEmail(String email);
//
//    Merchant findByUsername(String userName);
}
