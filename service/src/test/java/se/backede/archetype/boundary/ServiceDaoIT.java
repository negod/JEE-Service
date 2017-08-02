/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary;

import com.negod.generics.persistence.search.GenericFilter;
import com.negod.generics.persistence.search.Pagination;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.backede.archetype.entity.ServiceEntity;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class ServiceDaoIT {

    /**
     * Test of getSearchFields method, of class ServiceDao.
     */
    @Test
    public void testGetSearchFields() throws Exception {
        System.out.println("getSearchfields");
        ServiceDao instance = new ServiceDao();
        Set<String> expResult = new HashSet<>(Arrays.asList(new String[]{"entitySet.value", "name", "entity.value"}));
        assertEquals(expResult, instance.getSearchFields());
    }

}
