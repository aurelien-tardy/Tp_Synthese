/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.ClientNetArticles;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Flow
 */
@Stateless
public class AcheteFacade {
    
    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    /**
     * Récupère la liste des produits acheté par un client grâce à l'idClient
     * @param idClient
     * @return List<Achete>
     * @throws Exception 
     */
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws Exception {
        try {
            return clientNetArticles.getListAcheteByIdClient(idClient);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Valide le panier d'un client
     * @param achat
     * @return String
     * @throws Exception 
     */
    public String validerPanier(Achete achat) throws Exception{
        try {
            return clientNetArticles.validerPanier(achat);
        } catch (Exception e) {
            throw e;
        }
    }
}
