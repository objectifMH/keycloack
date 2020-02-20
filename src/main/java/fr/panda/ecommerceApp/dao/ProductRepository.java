/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.ecommerceApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.panda.ecommerceApp.entity.Product;

/**
 *
 * @author gtu
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

    
}
