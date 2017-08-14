/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.security;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import se.backede.webservice.security.Authentication;
import se.backede.webservice.security.AuthorizationDao;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Path("/auth")
public class Auth extends Authentication {

    @EJB
    AuthDao dao;

    @Override
    public AuthorizationDao getDao() {
        return dao;
    }

}
