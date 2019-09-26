package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.model.entity.Dealer;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.repository.IDealerRepository;
import edu.mum.cs.inventorymanager.service.contract.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    IDealerRepository dealerRepository;

    @Override
    public List<Dealer> findAll() {
        return dealerRepository.findAll();
    }

    @Override
    public Dealer save(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer findOne(Long id) {
        return dealerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Dealer dealer) {
        dealerRepository.delete(dealer);
    }

    @Override
    public void delete(Long id) {
        dealerRepository.deleteById(id);
    }

    @Override
    public List<Dealer> findAllByMerchant(Merchant merchant) {
        return dealerRepository.findAllByMerchant(merchant);
    }
}
