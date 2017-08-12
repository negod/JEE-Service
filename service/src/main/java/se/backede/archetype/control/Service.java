/**
 * Do not forget to rename the @responseType to the actual entityclass for each required method
 */
package se.backede.archetype.control;

import com.negod.generics.persistence.GenericDao;
import io.swagger.annotations.Api;
import java.io.IOException;
import se.backede.archetype.boundary.ServiceDao;
import se.backede.archetype.entity.ServiceEntity;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import se.backede.archetype.SwaggerExtractor;
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
