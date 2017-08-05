/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import com.negod.generics.persistence.exception.DaoException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.hibernate.annotations.Cache;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Singleton
@Startup
@Slf4j
public class EntityRegistry {

    @PersistenceContext(unitName = "ServicePU")
    private EntityManager em;

    @PostConstruct
    private void registerEnties() {

        Map<String, Class> entitiesToRegister = new HashMap<>();

        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            try {
                Optional<String> cacheName = extractCachefields(entity.getBindableJavaType());
                if (cacheName.isPresent()) {
                    entitiesToRegister.put(cacheName.get(), entity.getBindableJavaType());
                }
            } catch (DaoException ex) {
                log.error("Exception when extracting searchFields {}", ex);
            }
        }

        CacheManager cache = CacheManager.getInstance();
        net.sf.ehcache.Cache ehCache = cache.getCache("entity_registry");

        if (!ehCache.isDisabled()) {
            for (Map.Entry<String, Class> entry : entitiesToRegister.entrySet()) {
                ehCache.put(new Element(entry.getKey(), entry.getValue()));
            }
        }

    }

    private final Optional<String> extractCachefields(Class<?> entityClass) throws DaoException {
        try {
            String cacheAnnotaion = org.hibernate.annotations.Cache.class.getName();

            Set<String> fields = new HashSet<>();
            Annotation[] annotations = entityClass.getAnnotations();
            for (Annotation annotation : annotations) {

                if (annotation.annotationType().getName().equals(cacheAnnotaion)) {
                    Cache declaredAnnotation = (Cache) annotation;
                    return Optional.ofNullable(declaredAnnotation.region());
                }

            }
            return Optional.empty();

        } catch (IllegalArgumentException ex) {
            log.error("Error whgen extracting serachFields {}", ex);
            throw new DaoException("Error whgen extracting serachFields {}", ex);
        }
    }

}
