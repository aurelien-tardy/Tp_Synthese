/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ClientFacade {
    
    private Client client;

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    
}
