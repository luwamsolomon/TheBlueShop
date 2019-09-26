package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole , Long> {
}
