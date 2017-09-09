/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary.registry;

import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import lombok.extern.slf4j.Slf4j;
import se.backede.webservice.registry.RegistryEntity;
import se.backede.webservice.registry.ServiceRegistry;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Singleton
@Startup
@Slf4j
public class Registry extends ServiceRegistry {

    @PostConstruct
    public void init() {
        Optional<RegistryEntity> register = register();

        register.ifPresent(data -> {
            log.debug("Successfully registered service: {} with URL: {}", data.getServiceName(), data.getUrl());
        });

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
