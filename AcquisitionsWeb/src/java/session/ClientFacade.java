/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
import dal.ClientGestUser;

/**
 *
 * @author Epulapp
 */
public class ClientFacade {
    
    private Client client;
    
    
    
        public Client lireLogin(String login) throws Exception {
        try {
            ClientGestUser clientGestUser = new ClientGestUser();
            return clientGestUser.connecter(Client.class, login);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
