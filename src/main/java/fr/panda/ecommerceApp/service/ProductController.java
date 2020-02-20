/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.ecommerceApp.service;

import fr.panda.ecommerceApp.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gtu
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
        
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
        
    
    
}
