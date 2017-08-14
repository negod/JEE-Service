/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import java.util.Set;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import lombok.extern.slf4j.Slf4j;
import se.backede.webservice.swagger.AbstractSwaggerExtractor;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
@Singleton
@DependsOn(value = {"ApplicationConfig"})
public class SwaggerExtractor extends AbstractSwaggerExtractor {

    @EJB
    ApplicationConfig config;

    @Override
    public Set<Class<?>> getServiceClasses() {
        return config.getClasses();
    }

}
