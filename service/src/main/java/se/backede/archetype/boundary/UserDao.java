/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import com.negod.generics.persistence.exception.DaoException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import se.backede.archetype.entity.UserEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
@Slf4j
public class UserDao extends GenericDao<UserEntity> {

    @PersistenceContext(unitName = "ServicePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public UserDao() throws DaoException {
        super(UserEntity.class);
    }

}
