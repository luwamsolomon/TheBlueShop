package edu.mum.cs.inventorymanager.service.contract;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.security.AppUser;


public interface MerchantService  extends AbstractService<Merchant>{
    public Merchant findByEmail(String email);
    public Merchant findByUsername(String userName);
    public Merchant findByMerchantName(String merchantName);
    public Merchant findUser(User user);
    public Merchant findMerchantByAppUser(AppUser appUser);
    public AppUser findAppUserByUsername(String username);
}
