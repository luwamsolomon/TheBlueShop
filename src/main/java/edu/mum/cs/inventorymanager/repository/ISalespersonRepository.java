package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Salesperson;
import edu.mum.cs.inventorymanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalespersonRepository extends JpaRepository<Salesperson,Long> {
    List<Salesperson> findAllByMerchant(Merchant merchant);
    Salesperson findByUser(User user);
    @Query(value = "SELECT merchant_id FROM tbl_salesperson where salesman_id = ?", nativeQuery = true)
    long findMerchantIdBySalesmanId(long salesmanId);
}
