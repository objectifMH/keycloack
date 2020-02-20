package fr.panda.ecommerceApp;

import fr.panda.ecommerceApp.dao.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.panda.ecommerceApp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ECommerceAppApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("On initialise les Produits : ");
        
        
        productRepository.save(new Product(null, "Sony Viao", 1200));
        productRepository.save(new Product(null, "Ipad Air", 650));
        productRepository.save(new Product(null, "Samsung Galaxy Flip", 1200));
        productRepository.save(new Product(null, "BlackBerry Key 3", 900));
        productRepository.findAll().forEach(produit -> {
            System.out.println(produit.getName());
        });
    }

}
