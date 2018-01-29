/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
import dal.ClientNetArticles;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ClientFacade {

    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    /**
     * Récupère un client à l'aide de son login
     * @param login
     * @return Client
     * @throws Exception 
     */
    public Client lireLogin(String login) throws Exception {
        try {
            return clientNetArticles.connecter(Client.class, login);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Créer un compte client
     * @param client
     * @return Response
     * @throws Exception 
     */
    public Response createAccount(Client client) throws Exception {
        try {
            return clientNetArticles.createAccount(client);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Récupère un client à l'aide de son id
     * @param idClient
     * @return Client
     * @throws Exception 
     */
    public Client getClientById(Integer idClient) throws Exception {
        try {
            return clientNetArticles.getClientById(idClient);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Récupère le dernier id client
     * @return Client
     * @throws Exception 
     */
    public Client getClientLastId() throws Exception {
        try {
            Client client = clientNetArticles.getClientLastId();
            return client;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Edite les détails d'un compte client
     * @param client
     * @throws Exception 
     */
    public void editAccount(Client client) throws Exception{
        try {
            clientNetArticles.editAccount(client);
        } catch (Exception e) {
            throw e;
        }
    }

}
