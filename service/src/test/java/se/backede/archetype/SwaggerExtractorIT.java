/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.backede.archetype.boundary.Domain;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
public class SwaggerExtractorIT {

    public SwaggerExtractorIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of getSwagger method, of class SwaggerExtractor.
//     */
//    @Test
//    public void testGetSwagger() throws Exception {
//        System.out.println("getSwagger");
//        SwaggerExtractor instance = new SwaggerExtractor();
//        String expResult = "";
//        String result = instance.getSwagger();
//        log.error(result);
//    }
//
//    @Test
//    public void test() throws InstantiationException, IllegalAccessException {
//        Domain domain = Domain.class.newInstance();
//        assert domain instanceof RestService;
//    }

}
