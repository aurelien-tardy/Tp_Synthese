/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Categorie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Flow
 */
@Stateless
public class CategorieFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
    
    public List<Categorie> getCategories() {
        return em.createNamedQuery("Categorie.findAll").getResultList();
    }
    
    public Categorie getCategoryById (Integer id) {
        return em.find(Categorie.class, id);
    }
}
