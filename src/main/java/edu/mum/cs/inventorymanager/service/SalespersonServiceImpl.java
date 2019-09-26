package edu.mum.cs.inventorymanager.service;

import edu.mum.cs.inventorymanager.model.RoleType;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Salesperson;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.security.AppRole;
import edu.mum.cs.inventorymanager.model.security.UserRole;
import edu.mum.cs.inventorymanager.repository.IAppUserRepository;
import edu.mum.cs.inventorymanager.repository.IRoleRepository;
import edu.mum.cs.inventorymanager.repository.ISalespersonRepository;
import edu.mum.cs.inventorymanager.repository.IUserRoleRepository;
import edu.mum.cs.inventorymanager.service.contract.SalespersonService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalespersonServiceImpl implements SalespersonService {

    private ISalespersonRepository repository;
    private IRoleRepository roleRepository;
    private IUserRoleRepository userRoleRepository;

    @Autowired
    public SalespersonServiceImpl(ISalespersonRepository repository, IRoleRepository roleRepository, IUserRoleRepository userRoleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Salesperson create(Salesperson salesperson) {
        Salesperson refinedSalesPerson = refineSalesperson(salesperson);
        Salesperson s = repository.save(refinedSalesPerson);
        AppRole role = roleRepository.findAppRoleByRoleName(RoleType.ROLE_SALESPERSON.getRoleCode());
        UserRole userRole = new UserRole(salesperson.getUser().getAppUser(), role);
        userRoleRepository.save(userRole);
        return s;
    }

    private Salesperson refineSalesperson(Salesperson salesperson) {
//        salesperson.getUser().setMerchant(salesperson.getMerchant());
        salesperson.getUser().getAppUser().setUser(salesperson.getUser());
        return salesperson;
    }

    @Override
    public Salesperson findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Salesperson> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Salesperson salesperson) {
        salesperson.setStatus("Deleted");
        repository.save(salesperson);
    }

    @Override
    public void deleteById(long id) {
        throw new UnsupportedOperationException("We decidded not to provide this functionality for Entity");
    }

    @Override
    public Salesperson update(Salesperson salesperson) {
        Salesperson refinedSalesperson = refineSalespersonForUpdate(salesperson);
        return repository.save(refinedSalesperson);
    }

    private Salesperson refineSalespersonForUpdate(Salesperson salesperson) {
        Salesperson sp = findById(salesperson.getSalesmanId());
        if (sp != null) {
            if (Strings.isNotBlank(salesperson.getStaffId())) {
                sp.setStaffId(salesperson.getStaffId());
            }
            if (Strings.isNotBlank(salesperson.getUser().getFirstName())) {
                sp.getUser().setFirstName(salesperson.getUser().getFirstName());
            }
            if (Strings.isNotBlank(salesperson.getUser().getLastName())) {
                sp.getUser().setLastName(salesperson.getUser().getLastName());
            }
            if (salesperson.getLocation() != null) {
                sp.setLocation(salesperson.getLocation());
            }
            if (Strings.isNotBlank(salesperson.getUser().getMobile())) {
                sp.getUser().setMobile(salesperson.getUser().getMobile());
            }
//            if(salesperson.getMerchant()!=null){
//                sp.setMerchant(salesperson.getMerchant());
//            }
        } else {
            sp = salesperson;
        }
        return sp;
    }

    @Override
    public List<Salesperson> findAllByMerchant(Merchant merchant) {
        return repository.findAllByMerchant(merchant);
    }

    @Override
    public Salesperson findByUser(User user) {
        return repository.findByUser(user);
    }
}
