/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import se.backede.archetype.entity.ServiceEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Startup
@Singleton
@Slf4j
public class CacheChecker {

    @PersistenceContext(name = "ServivePU")
    EntityManager em;

    public Boolean cacheContainsServiceEntity(String id) {
        javax.persistence.Cache cache = em.getEntityManagerFactory().getCache();
        return cache.contains(ServiceEntity.class, id);
    }

    public Optional<Map> logCaches() {
        HashMap map = new HashMap<String, List<String>>();
        CacheManager cm = CacheManager.getInstance();
        map.put("CacheNames", cm.getCacheNames());
        return Optional.of(map);
    }

}
