package edu.mum.cs.inventorymanager;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication{

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagerApplication.class, args);
    }
 	
}
