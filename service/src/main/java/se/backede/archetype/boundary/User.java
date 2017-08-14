/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import io.swagger.annotations.Api;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import se.backede.archetype.control.UserDao;
import se.backede.archetype.entity.UserEntity;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Api
@Path("/user")
public class User implements RestService<UserEntity> {

    @EJB
    UserDao dao;

    @Override
    public GenericDao getDao() {
        return dao;
    }

}
