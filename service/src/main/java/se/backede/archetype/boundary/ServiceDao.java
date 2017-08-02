package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import com.negod.generics.persistence.exception.DaoException;
import java.util.Optional;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
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

    public Optional<ServiceEntity> putDomain(String serviceId, String domainId) {
        try {
            Optional<ServiceEntity> service = super.getById(serviceId);
            Optional<DomainEntity> domain = domainDao.getById(domainId);

            if (service.isPresent() && domain.isPresent()) {
                service.get().setDomain(domain.get());
                return super.update(service.get());
            }
        } catch (DaoException ex) {
        }
        return Optional.empty();
    }

}
