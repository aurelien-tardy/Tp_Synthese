/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dal.Achete;
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
import dal.Auteur;
import dal.Categorie;
import dal.Domaine;
import dal.Redige;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.GenericEntity;
import session.AcheteFacade;
import session.ArticleFacade;
import session.AuteurFacade;
import session.CategorieFacade;
import session.DomaineFacade;
import session.RedigeFacade;

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
    private AuteurFacade auteurFacade;

    @EJB
    private ArticleFacade articleFacade;

    @EJB
    private DomaineFacade domaineFacade;

    @EJB
    private RedigeFacade redigeFacade;

    @EJB
    private CategorieFacade categorieFacade;

    @EJB
    private AcheteFacade acheteFacade;

    @GET
    @Path("getLastArticle")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastArticle() throws Exception {
        Response response = null;
        try {
            Article article = articleFacade.getLastArticle();
            GenericEntity<Article> articles = new GenericEntity<Article>(article) {
            };
            response = Response.status(Response.Status.OK).entity(articles).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
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
    @Path("getConnexionAuteur/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecterAuteur(@PathParam("login") String login) throws Exception {
        Response response = null;
        try {
            Auteur auteur = auteurFacade.lireLogin(login);
            response = Response.status(Response.Status.OK).entity(auteur).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getArticlesByAuteurId/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticlesByAuteurId(@PathParam("Id") String Id) throws Exception {
        Response response = null;
        try {
            List<Redige> lRedige = redigeFacade.getRedigeByIdAuteur(Integer.parseInt(Id));
            GenericEntity<List<Redige>> lRed = new GenericEntity<List<Redige>>(lRedige) {
            };
            response = Response.status(Response.Status.OK).entity(lRed).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getAllAchete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAchete() throws Exception {
        Response response = null;
        try {
            List<Achete> lAchete = acheteFacade.getAllAchete();
            GenericEntity<List<Achete>> lAchats = new GenericEntity<List<Achete>>(lAchete) {
            };
            response = Response.status(Response.Status.OK).entity(lAchats).build();
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
            GenericEntity<List<Article>> lArticles = new GenericEntity<List<Article>>(listArticles) {
            };
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

    @GET
    @Path("getCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() throws Exception {
        Response response = null;
        try {
            List<Categorie> listCategories = categorieFacade.getCategories();
            GenericEntity<List<Categorie>> lCategories = new GenericEntity<List<Categorie>>(listCategories) {
            };
            response = Response.status(Response.Status.OK).entity(lCategories).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getCategoryById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id) throws Exception {
        Response response = null;
        try {
            Categorie category = categorieFacade.getCategoryById(id);
            response = Response.status(Response.Status.OK).entity(category).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Client client) throws Exception {
        Response response = null;
        try {
            if (client != null) {
                clientFacade.createAccount(client);
                response = Response.status(Response.Status.OK).entity(client).build();
            }
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("editAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAccount(Client client) throws Exception {
        Response response = null;
        try {
            if (client != null) {
                clientFacade.editAccount(client);
                response = Response.status(Response.Status.OK).entity(client).build();
            }
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getClientLastId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientLastId() throws Exception {
        Response response = null;
        try {
            Client newClient = new Client(clientFacade.getLastId() + 1);
            response = Response.status(Response.Status.OK).entity(newClient).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getClientById/{idClient}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientById(@PathParam("idClient") Integer idClient) throws Exception {
        Response response = null;
        try {
            Client client = clientFacade.lireId(idClient);
            response = Response.status(Response.Status.OK).entity(client).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getListAcheteByIdClient/{idClient}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListAcheteByIdClient(@PathParam("idClient") Integer idClient) throws Exception {
        Response response = null;
        try {
            List<Achete> listAchete = acheteFacade.getListAcheteByIdClient(idClient);
            GenericEntity<List<Achete>> lAchete = new GenericEntity<List<Achete>>(listAchete) {
            };
            response = Response.status(Response.Status.OK).entity(lAchete).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("validerPanier")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validerPanier(Achete achat) throws Exception {
        Response response = null;
        try {
            String messageValidation = acheteFacade.validerPanier(achat);
            response = Response.status(Response.Status.OK).entity(messageValidation).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
}
