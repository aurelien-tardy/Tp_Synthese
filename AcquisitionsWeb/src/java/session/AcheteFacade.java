/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.ClientNetArticle;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Flow
 */
@Stateless
public class AcheteFacade {

    /**
     * Recupère tous les articels achetés
     * 
     * @return
     * @throws Exception 
     */
    public List<Achete> getAllAchete() throws Exception {
        try {
            ClientNetArticle clientNetArticle = new ClientNetArticle();
            return clientNetArticle.getAllAchete();
        } catch (Exception e) {
            throw e;
        }
    }
}
