/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
public class ArticleFacade {
    
    @PersistenceContext(unitName="NetArticlesRestPU")
    EntityManager em;
        
        public Article getLastArticle() throws Exception {
        try {
            return (Article) em.createNamedQuery("Article.getLast").getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }
    
}
