/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dal.Client;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import outils.Utilitaire;
import session.ClientFacade;

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
    
    @EJB
    private ClientFacade clientFacade;
    
    @GET
    @Path("testws")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testws() throws Exception {
        return Response.status(Response.Status.OK).entity("Yop").build();
    }
    
    @GET
    @Path("getConnexion/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecter(@PathParam("login") String login) throws Exception {
        Response response = null;
        try {
            Client client = clientFacade.lireLogin(login);
            response = Response.status(Response.Status.OK).entity(client).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
   

    /**
     * PUT method for updating or creating an instance of WebserviceResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
}
