package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import com.negod.generics.persistence.exception.DaoException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import se.backede.archetype.entity.ServiceEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
@Slf4j
public class Dao extends GenericDao<ServiceEntity> {

    @PersistenceContext(unitName = "ServicePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Dao() throws DaoException {
        super(ServiceEntity.class);
    }

}
