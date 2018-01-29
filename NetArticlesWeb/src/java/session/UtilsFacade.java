/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.ClientBanque;
import javax.ejb.Stateless;

/**
 *
 * @author Epulapp
 */
@Stateless
public class UtilsFacade {
    
    /**
     * Appel le client pour générer le code de confirmation d'achat
     * @param email
     * @return String
     * @throws Exception 
     */
     public String getKey(String email) throws Exception {
         ClientBanque clientBanque = new ClientBanque();
         String key = clientBanque.confirmPaiement(email);
         return key;
    }
}
