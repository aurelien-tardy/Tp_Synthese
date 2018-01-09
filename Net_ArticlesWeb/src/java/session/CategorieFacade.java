/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Categorie;
import dal.ClientNetArticles;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Flow
 */
@Stateless
public class CategorieFacade {

    public List<Categorie> getCategories() throws Exception{
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            return clientNetArticles.getCategories();
        } catch (Exception e) {
            throw e;
        }
    }
}
