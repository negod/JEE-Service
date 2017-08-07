/**
 * Do not forget to rename the @responseType to the actual entityclass for each required method
 */
package se.backede.archetype.control;

import com.negod.generics.persistence.GenericDao;
import se.backede.archetype.boundary.ServiceDao;
import se.backede.archetype.entity.ServiceEntity;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Path("/service-parent")
public class Service implements RestService<ServiceEntity> {

    @EJB
    ServiceDao dao;

    @Override
    public GenericDao getDao() {
        return dao;
    }

}
