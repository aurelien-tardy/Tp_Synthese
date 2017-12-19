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

    public List<Article> getArticlesByField(String field) throws Exception {
        List<Article> listFields = new ArrayList<>();
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            listFields = clientNetArticles.getArticlesByField(field);
        } catch (Exception e) {
            throw e;
        }
        return listFields;
    }
    
    public Article getArticleById(String id) throws Exception {
        Article article = new Article();
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            article = clientNetArticles.getArticleById(id);
        } catch (Exception e) {
            throw e;
        }
        return article;
    }
}
