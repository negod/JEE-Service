/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary;

import com.negod.generics.persistence.GenericDao;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import se.backede.archetype.entity.UserEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@LocalBean
@Stateless
public class UserDao extends GenericDao<UserEntity> {
}
