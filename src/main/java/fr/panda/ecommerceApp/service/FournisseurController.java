/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.ecommerceApp.service;

import fr.panda.ecommerceApp.entity.Fournisseur;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gtu
 */
@Controller
public class FournisseurController {

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @GetMapping("/fournisseurs")
    public String index(Model model) {
        ResponseEntity<PagedModel<Fournisseur>> response
                = keycloakRestTemplate.exchange("http://localhost:8084/fournisseurs", HttpMethod.GET, null, new ParameterizedTypeReference<PagedModel<Fournisseur>>() {
                });

        model.addAttribute("fournisseurs", response.getBody().getContent());
        System.out.println(" je suis dans fournisseurs" + response); 
        return "fournisseurs";
    }

}
