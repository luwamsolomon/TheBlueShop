package edu.mum.cs.inventorymanager.service.contract;

import edu.mum.cs.inventorymanager.model.entity.Dealer;
import edu.mum.cs.inventorymanager.model.entity.Merchant;

import java.util.List;

public interface DealerService {
    List<Dealer> findAll();
    Dealer save(Dealer dealer);
    Dealer findOne(Long id);
    void delete(Dealer dealer);
    void delete(Long id);
    List<Dealer> findAllByMerchant(Merchant merchant);
}
