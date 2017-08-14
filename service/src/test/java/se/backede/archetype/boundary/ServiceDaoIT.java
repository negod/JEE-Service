/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.boundary;

import se.backede.archetype.control.ServiceDao;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

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
