/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype;

import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;
import io.swagger.parser.util.SwaggerDeserializationResult;
import io.swagger.util.Json;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import se.backede.webservice.service.EntityRegistrySingleton;
import se.backede.webservice.service.RestService;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
@Singleton
@DependsOn(value = {"EntityRegistrySingleton", "ApplicationConfig"})
public class SwaggerExtractor {
    
    @EJB
    ApplicationConfig config;
    
    @EJB
    EntityRegistrySingleton entities;
    
    private String SwaggerString = null;
    private String[] defaultPaths = new String[]{"/filter", "/index", "/search/fields", "/{id}"};
    
    public String getSwagger() throws IOException, InstantiationException, IllegalAccessException {
        log.trace("Getting Manupulated Swagger json");
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("swagger.json");
        String swaggerFile = swaggerFile = IOUtils.toString(input, Charset.defaultCharset());
        SwaggerDeserializationResult readWithInfo = new SwaggerParser().readWithInfo(swaggerFile);
        Swagger swagger = readWithInfo.getSwagger();
        
        for (Class<?> registeredEntity : entities.getRegisteredEntities()) {
            Model model = new GenericSwaggerModel(registeredEntity);
            swagger.getDefinitions().put(registeredEntity.getSimpleName(), model);
        }
        
        for (Map.Entry<String, Path> entry : swagger.getPaths().entrySet()) {
            log.debug("PATH: {}", entry.getKey());
        }
        
        for (Class<?> clazz : config.getClasses()) {
            
            if (clazz.newInstance() instanceof RestService) {
                String basePath = PropertyCreator.getInstance().getBasePath(clazz);
                
                Class<?> classTypeForRestService = getClassTypeOfrestService(clazz);
                if (classTypeForRestService == null) {
                    continue;
                }
                
                log.trace("Handling basepath {}", basePath);
                Path path = swagger.getPath(basePath);
                
                if (path != null) {
                    
                    handlePost(path, classTypeForRestService, true);
                    handleGet(path, classTypeForRestService);
                    
                    Path getByIdPath = swagger.getPath(basePath + "/{id}");
                    handleGet(getByIdPath, classTypeForRestService);
                    handlePut(getByIdPath, classTypeForRestService);
                    handleDelete(getByIdPath, classTypeForRestService);
                    
                    Path filterPath = swagger.getPath(basePath + "/filter");
                    handlePost(filterPath, classTypeForRestService, false);

//                    Path indexPath = swagger.getPath(basePath + "/index");
//                    handlePost(indexPath, Boolean.class);
//
//                    Path searchFieldPath = swagger.getPath(basePath + "/search/fields");
//                    handleGet(searchFieldPath, String.class);
                    Path objectUpdatePath = swagger.getPath(basePath + "/update/{id}");
                    handlePut(objectUpdatePath, classTypeForRestService);
                    
                }
                
            } else {
                continue;
            }
            
        }
        return Json.pretty(swagger);
    }
    
    private Class<?> getClassTypeOfrestService(Class restService) {
        Type[] genericInterfaces = restService.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    return (Class) genericType;
                }
            }
        }
        return null;
    }
    
    private void handleGet(Path path, Class<?> clazz) {
        if (path.getGet() != null) {
            Operation get = path.getGet();
            if (get.getResponses() != null) {
                setSchemaResponses(get.getResponses(), clazz);
            }
        }
    }
    
    private void handlePost(Path path, Class<?> clazz, Boolean addInParam) {
        if (path.getPost() != null) {
            Operation post = path.getPost();
            if (post.getResponses() != null) {
                setSchemaResponses(post.getResponses(), clazz);
            }
            if (addInParam) {
                if (post.getParameters().size() == 1) {
                    try {
                        BodyParameter get = (BodyParameter) post.getParameters().get(0);
                        get.setSchema(new GenericSwaggerModel(clazz));
                    } catch (ClassCastException e) {
                        log.error("Error when trying to cast param to BodyParam method:handlePost Error:{} ", e);
                    }
                }
            }
        }
    }
    
    private void handlePut(Path path, Class<?> clazz) {
        if (path.getPut() != null) {
            Operation put = path.getPut();
            if (put.getResponses() != null) {
                setSchemaResponses(put.getResponses(), clazz);
            }
        }
    }
    
    private void handleDelete(Path path, Class<?> clazz) {
        if (path.getDelete() != null) {
            Operation delete = path.getDelete();
            if (delete.getResponses() != null) {
                setSchemaResponses(delete.getResponses(), clazz);
            }
        }
    }
    
    private void setSchemaResponses(Map<String, Response> responses, Class<?> clazz) {
        responses.entrySet().forEach((Map.Entry<String, Response> entry) -> {
            switch (entry.getKey()) {
                case "200":
                    entry.getValue().setSchema(PropertyCreator.getInstance().getRefProperty(clazz));
                    break;
                case "204":
                    entry.getValue().setSchema(null);
                    break;
                case "500":
                    entry.getValue().setSchema(null);
                    break;
                default:
                    break;
            }
        });
    }
    
}
