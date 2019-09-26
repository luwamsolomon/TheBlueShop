package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.security.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleByRoleName(String roleName);
}
