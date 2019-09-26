package edu.mum.cs.inventorymanager.repository;

import edu.mum.cs.inventorymanager.model.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findAppUserByUserName(String username);
}
