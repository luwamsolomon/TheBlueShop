package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "productRepository")
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMerchant(Merchant merchant);
    List<Product> findByStatus(String status);
}
