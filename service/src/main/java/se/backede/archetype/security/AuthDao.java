/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.security;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import se.backede.webservice.exception.AuthorizationException;
import se.backede.webservice.security.AuthorizationDao;
import se.backede.webservice.security.Credentials;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
public class AuthDao implements AuthorizationDao {

    @Override
    public Boolean authorize(Credentials creds) throws AuthorizationException {
        return true;
    }

}
