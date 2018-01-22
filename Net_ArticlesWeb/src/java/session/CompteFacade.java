/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.ClientBanque;
import dal.Compte;

/**
 *
 * @author Epulapp
 */
public class CompteFacade {
    
    ClientBanque clientBanque = new ClientBanque();
    
    public Compte creatéAccount(Compte compte) throws Exception {
        try {
            return clientBanque.createAccount(compte);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Integer getSoldeById(Integer id) throws Exception {
        try {
            return clientBanque.getSoldeById(id).getSolde();
        } catch (Exception e) {
            throw e;
        }
    }

    public void editSolde(Integer montant, Integer idClient) throws Exception{
        try {
            clientBanque.editSolde(montant, idClient);
        } catch (Exception e) {
            throw e;
        }
    }
}
