/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

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
 *        ClientGestUser client = new ClientGestUser();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Epulapp
 */
public class ClientGestUser {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Net_ArticlesRest/webresources";

    public ClientGestUser() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }

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

    public <T> T connecter(Class<T> responseType, String login) throws ClientErrorException, Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getConnexion/{0}", new Object[]{login}));
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
