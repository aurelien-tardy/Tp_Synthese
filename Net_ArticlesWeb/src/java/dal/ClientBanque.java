/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import outils.Utilitaire;

/**
 *
 * @author Epulapp
 */
public class ClientBanque {
    private WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/BanqueRest/webresources";

    public ClientBanque() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }
    
    public Compte createAccount(Compte compte) throws Exception {
        Response response = webTarget.path("createAccount").request(MediaType.APPLICATION_JSON).post(Entity.entity(compte, MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(Compte.class);
    }
    
    public Compte getSoldeById(Integer id) throws Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("getSoldeById/{0}", new Object[]{id})).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            JsonObject jsonObject = Utilitaire.convertJson(response.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        return response.readEntity(Compte.class);
    }

    public void editSolde(Integer montant, Integer idClient) throws Exception{
        Compte compte = webTarget.path(java.text.MessageFormat.format("getSoldeById/{0}", new Object[]{idClient})).request(MediaType.APPLICATION_JSON).get().readEntity(Compte.class);
        compte.setSolde(montant);
        webTarget.path("editSolde").request(MediaType.APPLICATION_JSON).post(Entity.entity(compte, MediaType.APPLICATION_JSON), Compte.class);
    }
    
    public String confirmPaiement(String email) throws Exception {
        Response response = webTarget.path(java.text.MessageFormat.format("sendPaiementEmail/{0}", new Object[]{email})).request(MediaType.APPLICATION_JSON).get();
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
