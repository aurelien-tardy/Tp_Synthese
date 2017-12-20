/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import dal.ClientGestUser;

/**
 *
 * @author Epulapp
 */
public class ArticleFacade {

    public Article getLastArticle() throws Exception {
        try {
            ClientGestUser clientGestUser = new ClientGestUser();
            return clientGestUser.getLastArticle();
        } catch (Exception e) {
            throw e;
        }
    }
}
