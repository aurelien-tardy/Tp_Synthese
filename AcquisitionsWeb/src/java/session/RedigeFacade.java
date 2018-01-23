/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.ClientNetArticle;
import dal.Redige;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flow
 */
public class RedigeFacade {

    public List<Achete> getArticlesAcheteByAuteurId(String auteurId) throws Exception {
        try {
            List<Achete> lAchats = new ArrayList<Achete>();
            ClientNetArticle clientNetArticle = new ClientNetArticle();
            List<Redige> lRedige = clientNetArticle.getArticlesByAuteurId(auteurId);
            List<Achete> lAchete = clientNetArticle.getAllAchete();
            for (Redige redige : lRedige) {
                for (Achete achete : lAchete) {
                    if (redige.getArticle().getIdArticle() == achete.getArticle().getIdArticle()) {
                        lAchats.add(achete);
                    }
                }
            }
            return lAchats;
        } catch (Exception e) {
            throw e;
        }
    }
}
