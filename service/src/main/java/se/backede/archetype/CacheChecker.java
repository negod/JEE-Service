/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    

    public Optional<Map> logCaches() {

        HashMap map = new HashMap<String, String>();
        CacheManager cm = CacheManager.getInstance();

        Cache service = cm.getCache("service");
        Cache user = cm.getCache("user");
        Cache domain = cm.getCache("domain");
        Cache service_detail = cm.getCache("service_detail");

        map.put("Service", service.isDisabled());
        map.put("Domain", user.isDisabled());
        map.put("User", domain.isDisabled());
        map.put("Service_deatail", service_detail.isDisabled());

        cm.shutdown();

        return Optional.of(map);

    }

}
