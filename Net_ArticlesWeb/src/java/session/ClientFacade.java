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

    public Client lireLogin(String login) throws Exception {
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            return clientNetArticles.connecter(Client.class, login);
        } catch (Exception e) {
            throw e;
        }
    }

    public Response createAccount(Client client) throws Exception {
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            return clientNetArticles.createAccount(client);
        } catch (Exception e) {
            throw e;
        }
    }

}
