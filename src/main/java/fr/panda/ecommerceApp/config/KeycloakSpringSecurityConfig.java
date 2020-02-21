/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.ecommerceApp.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 *
 * @author gtu
 */

@KeycloakConfiguration
public class KeycloakSpringSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new  RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    
    
    //Keycloak va se charger de la gestion des utilisateurs et des roles pour SpringSecurity 
    
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        //délégue a  l'adaptateur keycloak la gestion des utilisateurs : 
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }
    
    @Override
    protected void configure (HttpSecurity http) throws Exception{
        super.configure(http);
        //pour accèder à cette ressource il faut etre authéntifié :
        http.authorizeRequests().antMatchers("/products/**").authenticated();
    }
}
