/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.ClientBanque;

/**
 *
 * @author Epulapp
 */
public class UtilsFacade {
     public String getKey(String email) throws Exception {
         ClientBanque clientBanque = new ClientBanque();
         String key = clientBanque.confirmPaiement(email);
         return key;
    }
}
