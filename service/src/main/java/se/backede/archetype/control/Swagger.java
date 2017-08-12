/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import se.backede.archetype.SwaggerExtractor;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Stateless
@Path("/swagger")
public class Swagger {

    @EJB
    SwaggerExtractor swagger;

    @GET
    @Path("/")
    public String getJson() throws IOException {
        try {
            return swagger.getSwagger();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Swagger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "N/A";
    }

}
