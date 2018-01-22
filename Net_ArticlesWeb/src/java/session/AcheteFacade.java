/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.Article;
import dal.ClientNetArticles;
import java.util.List;

/**
 *
 * @author Flow
 */
public class AcheteFacade {
    
    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws Exception {
        try {
            return clientNetArticles.getListAcheteByIdClient(idClient);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public String validerPanier(Achete achat) throws Exception{
        try {
            return clientNetArticles.validerPanier(achat);
        } catch (Exception e) {
            throw e;
        }
    }
}
