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
    private static final String BASE_URI = "http://localhost:8080/Net_ArticlesRest/webresources";

    public ClientNetArticles() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }

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
    
    public dal.Client getClientById(Integer idClient) throws Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("getClientById/{0}", new Object[]{idClient})).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(dal.Client.class);
    }
    
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

    public Response createAccount(dal.Client client) throws ClientErrorException, Exception {
        Response response = webTarget.path("createAccount").request(MediaType.APPLICATION_JSON).post(Entity.entity(client, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response;
    }
    
    public Response editAccount(dal.Client client) throws ClientErrorException, Exception {
        Response response = webTarget.path("editAccount").request(MediaType.APPLICATION_JSON).post(Entity.entity(client, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response;
    }
    
    public dal.Client getClientLastId() throws ClientErrorException, Exception {
        Response response = webTarget.path("getClientLastId").request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(dal.Client.class);
    }
    
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws ClientErrorException, Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("getListAcheteByIdClient/{0}", new Object[]{idClient})).request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Achete>>() {});
    }

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
