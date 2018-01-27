/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import dal.Domaine;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ArticleFacade {
    
    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;   

    public EntityManager getEm() {
        return em;
    }
    
    /**
     * Renvoie les articles possédant ce domaine
     * 
     * @param field
     * @return
     * @throws Exception 
     */
    public List<Article> getArticlesByField(int field) throws Exception {
        try {
            Query requeteArticle = em.createNamedQuery("Article.findByDomaine");
            Query requeteDomaine = em.createNamedQuery("Domaine.findByIdDomaine");
            requeteDomaine.setParameter("idDomaine", field);
            requeteArticle.setParameter("domaine", (Domaine) requeteDomaine.getSingleResult());
            return requeteArticle.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Renvoie l'article
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Article getArticleById(int id) throws Exception {
        try {
            Query requete = em.createNamedQuery("Article.findByIdArticle");
            requete.setParameter("idArticle", id);
            return (Article) requete.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Renvoie le dernier article créer
     * 
     * @return Article
     * @throws Exception 
     */
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
