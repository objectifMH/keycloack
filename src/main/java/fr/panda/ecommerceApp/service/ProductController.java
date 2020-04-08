/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.ecommerceApp.service;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import fr.panda.ecommerceApp.dao.ProductRepository;
import fr.panda.ecommerceApp.entity.Fournisseur;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author gtu
 */
@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    //securité
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @Autowired
    private AdapterDeploymentContext adapterDeploymentContext;

    @GetMapping("/changePsswd")
    public String changePsswd(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpFacade facade = new SimpleHttpFacade(request, response);
        KeycloakDeployment deployment = adapterDeploymentContext.resolveDeployment(facade);
        System.out.println(deployment);
        redirectAttributes.addAttribute("referrer", deployment.getResourceName());
        
        //Pour permettre d'avoir un onglet dans la console keycloak et revenir à l'application : 
        redirectAttributes.addAttribute("referrer_uri", request.getHeader("referer"));

        return "redirect:" + deployment.getAccountUrl() + "/password";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler() {
        System.out.println("Erreur Exception !! ");
        return "error";
    }

}
