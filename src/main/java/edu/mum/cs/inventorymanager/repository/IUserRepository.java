package edu.mum.cs.inventorymanager.repository;


import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUserId(long id);

    User findByEmail(String email);

    User findByAppUser_UserName(String username);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByMobile(String mobile);

    User findByAppUser_UserNameAndAndAppUser_EncrytedPassword(String username, String encryptedPassword);
    User findUserByAppUserUserNameAndAppUserEncrytedPassword(String userName,String encrytedPassword);
}
