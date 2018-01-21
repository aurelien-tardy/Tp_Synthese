/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.ClientNetArticles;
import java.util.List;

/**
 *
 * @author Flow
 */
public class AcheteFacade {
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws Exception {
        try {
            ClientNetArticles clientGestUser = new ClientNetArticles();
            return clientGestUser.getListAcheteByIdClient(idClient);
        } catch (Exception e) {
            throw e;
        }
    }
}
