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

import dal.Article;
import dal.Domaine;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import session.ArticleFacade;
import session.DomaineFacade;

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
    
    @EJB
    private ArticleFacade articleFacade;
    
    @EJB
    private DomaineFacade domaineFacade;
    
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
    
    @GET
    @Path("getFields")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFields() throws Exception {
        Response response = null;
        try {
            List<Domaine> listFields = domaineFacade.getFields();
            GenericEntity<List<Domaine>> lFields = new GenericEntity<List<Domaine>>(listFields) {
            };
            response = Response.status(Response.Status.OK).entity(lFields).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    
    @GET
    @Path("getArticlesByField/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticlesByField(@PathParam("field") String field) throws Exception {
        Response response = null;
        try {
            List<Article> listArticles = articleFacade.getArticlesByField(Integer.parseInt(field));
            GenericEntity<List<Article>> lArticles = new GenericEntity<List<Article>>(listArticles) {};
            response = Response.status(Response.Status.OK).entity(lArticles).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getArticleById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleById(@PathParam("id") String id) throws Exception {
        Response response = null;
        try {
            Article article = articleFacade.getArticleById(Integer.parseInt(id));
            response = Response.status(Response.Status.OK).entity(article).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
}
