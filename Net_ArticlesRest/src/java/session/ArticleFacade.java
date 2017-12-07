/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import dal.Auteur;
import dal.Client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ArticleFacade {

    private Article article;

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public Article getLastArticle() throws Exception {
        try {
            Date date = new Date(0);
            List<Article> articles = em.createNamedQuery("Article.findAll").getResultList();
            Article lastArticle = new Article();
            lastArticle.setDateArticle(date);
            for (Article article : articles) {
                if(article.getDateArticle().after(lastArticle.getDateArticle())){
                    lastArticle = article;
                }
            }
            return lastArticle;
        } catch (Exception e) {
            throw e;
        }
    }
}
