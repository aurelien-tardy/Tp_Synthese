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

/**
 *
 * @author Epulapp
 */
public class AuteurFacade {
    
    private Auteur auteur;

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }
}
