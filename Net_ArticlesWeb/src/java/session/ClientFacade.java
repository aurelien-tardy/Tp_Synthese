/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
import dal.ClientNetArticles;
import javax.ws.rs.core.Response;

/**
 *
 * @author Epulapp
 */
public class ClientFacade {

    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    public Client lireLogin(String login) throws Exception {
        try {
            return clientNetArticles.connecter(Client.class, login);
        } catch (Exception e) {
            throw e;
        }
    }

    public Response createAccount(Client client) throws Exception {
        try {
            return clientNetArticles.createAccount(client);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Client getClientLastId() throws Exception {
        try {
            Client client = clientNetArticles.getClientLastId();
            return client;
        } catch (Exception e) {
            throw e;
        }
    }

}
