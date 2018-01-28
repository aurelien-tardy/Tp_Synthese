/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import dal.ClientNetArticles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ArticleFacade {

    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    /**
     * Récupère tous les articles d'un domaine
     * @param field
     * @return List<Article>
     * @throws Exception 
     */
    public List<Article> getArticlesByField(String field) throws Exception {
        List<Article> listFields = new ArrayList<>();
        try {
            listFields = clientNetArticles.getArticlesByField(field);
        } catch (Exception e) {
            throw e;
        }
        return listFields;
    }
    
    /**
     * Récupère un article à l'aide de son id
     * @param id
     * @return Article
     * @throws Exception 
     */
    public Article getArticleById(String id) throws Exception {
        Article article = new Article();
        try {
            article = clientNetArticles.getArticleById(id);
        } catch (Exception e) {
            throw e;
        }
        return article;
    }
    
    /**
     * Récupère le dernier article mis en vente
     * @return
     * @throws Exception 
     */
    public Article getLastArticle() throws Exception {
        try {
            return clientNetArticles.getLastArticle();
        } catch (Exception e) {
            throw e;
        }
    }
}