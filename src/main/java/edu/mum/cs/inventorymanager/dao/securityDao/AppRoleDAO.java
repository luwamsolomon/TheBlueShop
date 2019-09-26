package edu.mum.cs.inventorymanager.dao.securityDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs.inventorymanager.model.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppRoleDAO {

    @Autowired
    private EntityManager entityManager;

    public List getRoleNames(Long id) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur "
                + " where ur.appUser.id = :id ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}