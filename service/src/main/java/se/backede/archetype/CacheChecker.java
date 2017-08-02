/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Startup
@Singleton
@Slf4j
public class CacheChecker {

    public void logCaches() {

        CacheManager cm = CacheManager.getInstance();

        Cache service = cm.getCache("service");
        Cache user = cm.getCache("user");
        Cache domain = cm.getCache("domain");
        Cache service_detail = cm.getCache("service_detail");

        log.debug("Service Online?" + service.isDisabled());
        log.debug("Domain Online?" + user.isDisabled());
        log.debug("User Online?" + domain.isDisabled());
        log.debug("SErvice_deatail Online?" + service_detail.isDisabled());

        cm.shutdown();

    }

}
