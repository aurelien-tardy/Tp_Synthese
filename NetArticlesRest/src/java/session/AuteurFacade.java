/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Auteur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Epulapp
 */
@Stateless
public class AuteurFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Renvoie un Auteur si le login existe
     *
     * @param login
     * @return Auteur
     * @throws Exception
     */
    public Auteur lireLogin(String login) throws Exception {
        try {
            Query requete = em.createNamedQuery("Auteur.findByLoginAuteur");
            requete.setParameter("loginAuteur", login);
            Auteur auteur = ((Auteur) requete.getSingleResult());
            return auteur;
        } catch (Exception e) {
            throw e;
        }
    }
}
