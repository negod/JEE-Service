package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import se.backede.archetype.entity.ServiceEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
public class ServiceDao extends GenericDao<ServiceEntity> {
}
