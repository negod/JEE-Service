/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.control;

import com.negod.generics.persistence.GenericDao;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import se.backede.archetype.boundary.UserDao;
import se.backede.archetype.entity.UserEntity;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Path("/user")
public class User implements RestService<UserEntity> {

    @EJB
    UserDao dao;

    @Override
    public GenericDao getDao() {
        return dao;
    }

}
