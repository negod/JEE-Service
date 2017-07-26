/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.control;

import com.negod.generics.persistence.GenericDao;
import com.negod.generics.persistence.search.GenericFilter;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import se.backede.archetype.boundary.UserDao;
import se.backede.archetype.entity.UserEntity;
import se.backede.webservice.service.GenericRestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
@Path("/user")
@Stateless
public class User extends GenericRestService<UserEntity>{
    
    @EJB
    UserDao dao;

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public GenericDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     *
     * @summary Get all
     * @responseType java.lang.Boolean
     */
    @GET
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getAll() {
        return super.getAll();
    }

    /**
     * {@inheritDoc}
     *
     * @summary Creates
     * @responseType se.backede.archetype.entity.UserEntity
     * @param entity
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response create(UserEntity entity) {
        return super.create(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Updates
     * @responseType se.backede.archetype.entity.UserEntity
     * @param entity
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response update(@PathParam("id") String id, UserEntity entity) {
        return super.update(id, entity);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Deletes by the id
     * @param id
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        return super.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets data by id
     * @responseType se.backede.archetype.entity.UserEntity
     */
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getById(@PathParam("id") String id) {
        return super.getById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets a filtered list
     * @responseType java.util.List<se.backede.archetype.entity.UserEntity>
     */
    @POST
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getFilteredList(GenericFilter filter) {
        return super.getFilteredList(filter);
    }

    /**
     * {@inheritDoc}
     *
     * @summary Gets all searchable fields
     * @responseType java.lang.String
     */
    @GET
    @Path("/search/fields")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response getSearchFields() {
        return super.getSearchFields();
    }

    /**
     * {@inheritDoc}
     *
     * @summary Reindexes all indexes
     * @responseType java.lang.Boolean
     */
    @GET
    @Path("/index")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response indexEntity() {
        return super.indexEntity();
    }
    
}
