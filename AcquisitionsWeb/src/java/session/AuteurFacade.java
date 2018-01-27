/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Auteur;
import dal.ClientNetArticle;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
public class AuteurFacade {
    
    private Auteur auteur;

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    /**
     * retour l'auteur affilié au login donné s'il existe
     * 
     * @param login
     * @return
     * @throws Exception 
     */
    public Auteur lireLogin(String login) throws Exception {
        try {
            ClientNetArticle clientnetArticle = new ClientNetArticle();
            return clientnetArticle.connecter(Auteur.class, login);
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
