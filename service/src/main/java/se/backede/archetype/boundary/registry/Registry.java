/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary.registry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import se.backede.webservice.exception.CreateNotPossibleException;
import se.backede.webservice.exception.ServiceNotMatchException;
import se.backede.webservice.registry.ServiceRegistry;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Singleton
@Startup
@Slf4j
@Data
public class Registry extends ServiceRegistry {
    
    @PostConstruct
    public void init() {
        try {
            register().ifPresent(data -> {
                log.info("Successfully registered service: {} with URL: {}", data.getServiceName(), data.getUrl());
            });
        } catch (CreateNotPossibleException | ServiceNotMatchException ex) {
            log.error("Error when registering service {}", getServiceName());
        }
    }
    
    @PreDestroy
    public void shutdown() {
        log.info("Shutting down service {}, setting offline", getServiceName());
        setOffline();
    }
    
    @Override
    public String getServiceName() {
        return "archetype2";
    }
    
    @Override
    public String getRegistryVersion() {
        return "1.0-SNAPSHOT";
    }
    
}
