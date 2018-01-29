/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
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
        ClientNetArticle client = new ClientNetArticle();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author Epulapp
 */
public class ClientNetArticle {

    private WebTarget webTarget;
    private Client client;
        private static final String BASE_URI = "http://localhost:8081/NetArticlesRest/webresources";

    public ClientNetArticle() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }    
    
    /**
     * Appelle le Web Service et renvoie la liste des lien article/auteur de l'auteur
     * 
     * @param id
     * @return List<Redige>
     * @throws Exception 
     */
    public List<Redige> getArticlesByAuteurId(String id) throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getArticlesByAuteurId/{0}", new Object[]{id}));
        Response response = resource.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Redige>>(){});
    }
    
    /**
     * Appelle le Web Service et renvoie tous les achats
     * 
     * @return List<Achete>
     * @throws Exception 
     */
    public List<Achete> getAllAchete() throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAchete");
        Response response = resource.request(MediaType.APPLICATION_JSON + "; charset=UTF-8").get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(new GenericType<List<Achete>>() {});
    }
    
    
    /**
     * Appelle le Web Service et renvoie le dernier article paru
     * 
     * @return Article
     * @throws Exception 
     */
    public Article getLastArticle() throws Exception {
        WebTarget resource = webTarget;
        resource = resource.path("getLastArticle");
        Response response = resource.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            if (message.contains("No entity found for query")) {
                throw new Exception("Error");
            } else {
                throw new Exception(message);
            }
        }
        return response.readEntity(new GenericType<Article>() {
        });
    }

    /**
     * Appelle le Web Service qui identifie si le login auteur existe - renvoie un auteur
     * 
     * @param <T>
     * @param responseType
     * @param login
     * @return Auteur
     * @throws ClientErrorException
     * @throws Exception 
     */
    public <T> T connecter(Class<T> responseType, String login) throws ClientErrorException, Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getConnexionAuteur/{0}", new Object[]{login}));
        Response response = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
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

    public void close() {
        client.close();
    }

}
