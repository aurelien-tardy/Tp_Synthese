/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Auteur;
import dal.ClientNetArticle;

/**
 *
 * @author Epulapp
 */
public class AuteurFacade {

    ClientNetArticle clientnetArticle = new ClientNetArticle();
    

    /**
     * retour l'auteur affilié au login donné s'il existe
     * 
     * @param login
     * @return
     * @throws Exception 
     */
    public Auteur lireLogin(String login) throws Exception {
        try {
            return clientnetArticle.connecter(Auteur.class, login);
        } catch (Exception e) {
            throw e;
        }
    }
}
