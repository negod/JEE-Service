/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.control;

import java.io.IOException;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import se.backede.archetype.SwaggerExtractor;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
@Stateless
@Path("/swagger")
public class Swagger {

    @EJB
    SwaggerExtractor swagger;

    @GET
    @Path("/")
    public String getJson() throws IOException {
        try {
            Optional<String> swaggerString = swagger.getSwagger();
            if (swaggerString.isPresent()) {
                return swaggerString.get();
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            log.error("Error when retrieving Swagger JSON [ RestLayer ], ErrorMessage: {}", ex);
        }
        return "N/A";
    }

}
