/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import outils.Utilitaire;

/**
 * Jersey REST client generated for REST resource:WebserviceResource
 * [webservice]<br>
 * USAGE:
 * <pre>
 *        ClientGestUser client = new ClientGestUser();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Epulapp
 */
public class ClientNetArticles {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/NetArticlesRest/webresources";

    public ClientNetArticles() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }

    /**
     * Appelle le web service de NetArticlesRest pour tenter de connecter le client
     * @param <T>
     * @param responseType
     * @param login
     * @return 
     * @throws ClientErrorException
     * @throws Exception 
     */
    public <T> T connecter(Class<T> responseType, String login) throws ClientErrorException, Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getConnexion/{0}", new Object[]{login}));
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            if (message.contains("No entity found for query")) {
                throw new Exception("Login/Mot de passe incorect !");
            } else {
                throw new Exception(message);
            }
        }
        return response.readEntity(responseType);
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer le client à l'aide de son id
     * @param idClient
     * @return Client
     * @throws Exception 
     */
    public dal.Client getClientById(Integer idClient) throws Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("getClientById/{0}", new Object[]{idClient})).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(dal.Client.class);
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer le dernier article mis en vente
     * @return Article
     * @throws Exception 
     */
    public Article getLastArticle() throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path("getLastArticle");
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            if (message.contains("No entity found for query")) {
                throw new Exception("Error");
            } else {
                throw new Exception(message);
            }
        }
        return response.readEntity(new GenericType<Article>(){});
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer la liste des domaine
     * @return List<Domaine>
     * @throws Exception 
     */
    public List<Domaine> getFields() throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path("getFields");
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Domaine>>() {});
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer la liste des articles d'un domaine
     * @param field
     * @return List<Article>
     * @throws Exception 
     */
    public List<Article> getArticlesByField(String field) throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getArticlesByField/{0}", new Object[]{field}));
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Article>>() {});
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer un article à l'aide de son id
     * @param id
     * @return Article
     * @throws Exception 
     */
    public Article getArticleById(String id) throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getArticleById/{0}", new Object[]{id}));
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(Article.class);
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer la liste des catégories
     * @return List<Categorie>
     * @throws Exception 
     */
    public List<Categorie> getCategories() throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path("getCategories");
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Categorie>>() {});
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer une catégorie à l'aide de son id
     * @param id
     * @return Categorie
     * @throws Exception 
     */
    public Categorie getCategoryById(Integer id) throws Exception{
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCategoryById/{0}", new Object[]{id}));
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(Categorie.class);
    }

    /**
     * Appelle le web service de NetArticlesRest pour créer un compte au client
     * @param client
     * @return Response
     * @throws ClientErrorException
     * @throws Exception 
     */
    public Response createAccount(dal.Client client) throws ClientErrorException, Exception {
        Response response = webTarget.path("createAccount").request(MediaType.APPLICATION_JSON).post(Entity.entity(client, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response;
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour éditer le compte d'un client
     * @param client
     * @return Response
     * @throws ClientErrorException
     * @throws Exception 
     */
    public Response editAccount(dal.Client client) throws ClientErrorException, Exception {
        Response response = webTarget.path("editAccount").request(MediaType.APPLICATION_JSON).post(Entity.entity(client, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response;
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour récupérer le dernier l'id du dernier client
     * @return Client
     * @throws ClientErrorException
     * @throws Exception 
     */
    public dal.Client getClientLastId() throws ClientErrorException, Exception {
        Response response = webTarget.path("getClientLastId").request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(dal.Client.class);
    }
    
    /**
     * Appelle le web service de NetArticlesRest pour la liste des articles acheté par un client à l'aide de son idClient
     * @param idClient
     * @return List<Achete>
     * @throws ClientErrorException
     * @throws Exception 
     */
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws ClientErrorException, Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("getListAcheteByIdClient/{0}", new Object[]{idClient})).request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Achete>>() {});
    }

    /**
     * Appelle le web service de NetArticlesRest pour valider le panier
     * @param achat
     * @return String
     * @throws Exception 
     */
    public String validerPanier(Achete achat) throws Exception{
        Response response = webTarget.path("validerPanier").request(MediaType.APPLICATION_JSON).post(Entity.entity(achat, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(String.class);
    }
    
    public void close() {
        client.close();
    }
}
