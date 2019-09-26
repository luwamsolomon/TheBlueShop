package edu.mum.cs.inventorymanager.service.contract;


import edu.mum.cs.inventorymanager.model.entity.User;

public interface UserService extends AbstractService<User> {
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByMobile(String mobile);
    User findByEmail(String email);
    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName,String password);
}
