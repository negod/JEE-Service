/**
 * Do not forget to rename the @responseType to the actual entityclass for each required method
 */
package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import io.swagger.annotations.Api;
import se.backede.archetype.control.ServiceDao;
import se.backede.archetype.entity.ServiceEntity;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Api
@Path("/service-parent")
public class Service implements RestService<ServiceEntity> {

    @EJB
    ServiceDao dao;

    @Override
    public GenericDao getDao() {
        return dao;
    }

}
