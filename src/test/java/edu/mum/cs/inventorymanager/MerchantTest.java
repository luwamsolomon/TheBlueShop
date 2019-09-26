package edu.mum.cs.inventorymanager;


import edu.mum.cs.inventorymanager.model.UserType;
import edu.mum.cs.inventorymanager.model.entity.Merchant;
import edu.mum.cs.inventorymanager.model.entity.User;
import edu.mum.cs.inventorymanager.service.contract.MerchantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantTest {
    Merchant merchant;
    @Autowired
    MerchantService service;

    @Before
    public void setUp(){
        User user = new User("Oluwajoba","Adegboye","2348031377870","oadegboye@mum.edu","oluwajoba","joba123",UserType.MERCHANT.getType());
        merchant = new Merchant("OluwajobaBiz","Active",user,"my address");
    }

//    @Test
    public void testCreateMerchant(){
        Merchant mer = service.create(merchant);
        System.out.println(mer);
    }

    @Test
    public void testFindAll(){
        List<Merchant> merchants = service.findAll();
        Assert.assertNotNull(merchants);
    }

    @Test
    public void testFindById(){
        Merchant merchant = service.findById(1L);
        Assert.assertNotNull(merchant);
    }

    @Test
    public void testFindByMerchantName(){
        Merchant mer = service.findByMerchantName("OluwajobaBiz");
        Assert.assertTrue(mer.getMerchantName().equals("OluwajobaBiz"));
    }
}
