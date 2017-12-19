/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import dal.Client;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Epulapp
 */
@Stateless
public class ClientFacade {

    private Client client;

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Lecture du client sur son login
     * Note : le login est unique (contrainte de bd)
     * @param login login du client à lire
     * @return un objet Client
     * @throws Exception 
     */
    public Client lireLogin(String login) throws Exception {
        try {
            Query requete = em.createNamedQuery("Client.findByLoginClient");
            requete.setParameter("loginClient", login);
            Client client = ((Client)requete.getSingleResult());
            return client;
        } catch (Exception e) {
            throw e;
        }
    }

    }
