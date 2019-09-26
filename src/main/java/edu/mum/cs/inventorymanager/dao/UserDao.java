package edu.mum.cs.inventorymanager.dao;

import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.model.security.AppUser;
import edu.mum.cs.inventorymanager.repository.IUserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    @Autowired
    IUserRepository userRepository;

    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public User findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public User findByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUserName(String userName) {
        return userRepository.findByAppUser_UserName(userName);
    }


    public User create(User user) {
        return userRepository.save(user);
    }


    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }

    public List<User> findByAll() {
        return userRepository.findAll();
    }


    public void delete(User user) {
        userRepository.delete(user);
    }


    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


    public User update(User user) {
        return userRepository.save(user);
    }

    public User findByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByAppUserUserNameAndAppUserEncrytedPassword(userName,password);
    }

    public List<User> findAllByMerchant(Merchant merchant) {
        throw new NotYetImplementedException("Not implemented");
    }
}
