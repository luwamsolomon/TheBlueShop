package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.dao.MerchantDao;
import edu.mum.cs.inventorymanager.model.RoleType;
import edu.mum.cs.inventorymanager.model.UserType;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Salesperson;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.security.AppRole;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import edu.mum.cs.inventorymanager.model.security.UserRole;
import edu.mum.cs.inventorymanager.repository.*;
import edu.mum.cs.inventorymanager.service.contract.LocationService;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import edu.mum.cs.inventorymanager.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantDao merchantDao;
    private final UserService userService;
    private final LocationService locationService;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private ISalespersonRepository salespersonRepository;

    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantDao merchantDao, UserService userService, LocationService locationService) {
        this.merchantDao = merchantDao;
        this.userService = userService;
        this.locationService = locationService;
    }

    @Override
    public Merchant create(Merchant merchant) {
        Merchant refinedMerchant = setAllMerchantEntity(merchant);
        merchantDao.createMerchant(refinedMerchant);
        AppRole role = roleRepository.findAppRoleByRoleName(RoleType.ROLE_MERCHANT.getRoleCode());
        UserRole userRole = new UserRole(refinedMerchant.getUser().getAppUser(), role);
        userRoleRepository.save(userRole);
        return refinedMerchant;
    }

    private Merchant setAllMerchantEntity(Merchant merchant) {
//        merchant.getUser().setMerchant(merchant);
        merchant.getUser().getAppUser().setUser(merchant.getUser());
        merchant.getUser().getAppUser().setUser(merchant.getUser());
        return merchant;
    }

    @Override
    public Merchant findById(Long id) {
        return merchantDao.findByMerchantId(id);
    }

    @Override
    public Merchant findByEmail(String email) {
        return merchantDao.findByEmail(email);
    }

    @Override
    public Merchant findByUsername(String userName) {
        return merchantDao.findByUsername(userName);
    }

    @Override
    public Merchant findByMerchantName(String merchantName) {
        return merchantDao.findByMerchantName(merchantName);
    }

    @Override
    public Merchant findUser(User user) {
        return merchantDao.findByUser(user);
    }

    public AppUser findAppUserByUsername(String username){
        AppUser appUser = appUserRepository.findAppUserByUserName(username);
        return appUser;
    }

    @Override
    public Merchant findMerchantByAppUser(AppUser appUser) {
        Merchant merchant = null;
       if(UserType.MERCHANT.getType().equals(appUser.getUser().getUserType())){
           merchant = merchantDao.findByUser(appUser.getUser());
       }else  if(UserType.SALESPERON.getType().equals(appUser.getUser().getUserType())){
           Salesperson salesperson =  salespersonRepository.findByUser(appUser.getUser());
           if(salesperson!=null){
               long merchantId = salespersonRepository.findMerchantIdBySalesmanId(salesperson.getSalesmanId());
               merchant = merchantDao.findByMerchantId(merchantId);
           }
       }

        return merchant;
    }

    @Override
    public List<Merchant> findAll() {
        return merchantDao.findByAll();
    }

    @Override
    public List<Merchant> findAllByMerchant(Merchant merchant) {
        List<Merchant> merchants = new ArrayList<>();
        merchants.add(merchantDao.findByMerchantId(merchant.getMerchantId()));
        return merchants;
    }

    @Override
    public void delete(Merchant merchant) {
        merchantDao.delete(merchant);
    }

    @Override
    public void deleteById(long merchantId) {
        merchantDao.delete(merchantId);
    }

    @Override
    public Merchant update(Merchant merchant) {
        return merchantDao.update(merchant);
    }
}
