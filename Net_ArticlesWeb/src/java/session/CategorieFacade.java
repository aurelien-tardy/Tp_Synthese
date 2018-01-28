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
    
    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    /**
     * Récupère la liste des catégories
     * @return List<Categorie>
     * @throws Exception 
     */
    public List<Categorie> getCategories() throws Exception{
        try {
            return clientNetArticles.getCategories();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Récupère une catégorie à l'aide de son id
     * @param id
     * @return Categorie
     * @throws Exception 
     */
    public Categorie getCategoryById(Integer id) throws Exception{
        try {
            return clientNetArticles.getCategoryById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
