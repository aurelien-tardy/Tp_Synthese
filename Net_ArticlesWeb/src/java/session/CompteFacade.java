/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.ClientBanque;
import dal.Compte;
import javax.ejb.Stateless;

/**
 *
 * @author Epulapp
 */
@Stateless
public class CompteFacade {
    
    ClientBanque clientBanque = new ClientBanque();
    
    /**
     * Créer un compte bancaire
     * @param compte
     * @return Comtpe
     * @throws Exception 
     */
    public Compte creatéAccount(Compte compte) throws Exception {
        try {
            return clientBanque.createAccount(compte);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Récupère le solde d'un compte à l'aide de son id
     * @param id
     * @return Integer
     * @throws Exception 
     */
    public Integer getSoldeById(Integer id) throws Exception {
        try {
            return clientBanque.getSoldeById(id).getSolde();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Edite le solde d'un compte bancaire
     * @param montant
     * @param idClient
     * @throws Exception 
     */
    public void editSolde(Integer montant, Integer idClient) throws Exception{
        try {
            clientBanque.editSolde(montant, idClient);
        } catch (Exception e) {
            throw e;
        }
    }
}
