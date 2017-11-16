/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Epulapp
 */
@Path("webservice")
public class WebserviceResource {


    /**
     * Creates a new instance of WebserviceResource
     */
    public WebserviceResource() {
    }

    /**
     * Retrieves representation of an instance of services.WebserviceResource
     *
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("testws")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testws() throws Exception {
        return Response.status(Response.Status.OK).entity("Yop").build();
    }
   

    /**
     * PUT method for updating or creating an instance of WebserviceResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
}
