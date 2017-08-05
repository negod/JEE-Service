package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import com.negod.generics.persistence.exception.DaoException;
import com.negod.generics.persistence.update.ObjectUpdate;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import se.backede.archetype.entity.DomainEntity;
import se.backede.archetype.entity.ServiceEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
@Slf4j
public class ServiceDao extends GenericDao<ServiceEntity> {

    @EJB
    DomainDao domainDao;

    @PersistenceContext(unitName = "ServicePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ServiceDao() throws DaoException {
        super(ServiceEntity.class);
    }

    public Optional<ServiceEntity> putDomain(String serviceId, ObjectUpdate update) throws DaoException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Optional<DomainEntity> domain = domainDao.getById(update.getObjectId());
        if (domain.isPresent()) {
            Optional<ServiceEntity> service = super.getById(serviceId);
            Optional<DomainEntity> byId = super.getById(update.getObjectId(), DomainEntity.class);
            if (service.isPresent()) {
                ServiceEntity entity = service.get();
                getEntityManager().detach(entity);
                service.get().setDomain(byId.get());
                return Optional.ofNullable(getEntityManager().merge(service.get()));
            }
        }
        return Optional.empty();
    }

}
