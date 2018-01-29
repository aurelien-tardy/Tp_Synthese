/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
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
    
    /**
     * Renvoie le client si le login existe
     * 
     * @param id
     * @return Client
     * @throws Exception 
     */
    public Client lireId(Integer id) throws Exception {
        try {
            Query requete = em.createNamedQuery("Client.findByIdClient");
            requete.setParameter("idClient", id);
            Client client = ((Client)requete.getSingleResult());
            return client;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Créer un compte client
     * 
     * @param client
     * @throws Exception 
     */
    public void createAccount(Client client) throws Exception {
        try {
            em.persist(client);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Renvoie le dernier id client
     * 
     * @return Integer
     * @throws Exception 
     */
    public Integer getLastId() throws Exception {
        try {
            Integer lastId = 0;
            List<Client> allCustomer = em.createNamedQuery("Client.findAll").getResultList();
            for (Client client : allCustomer) {
                if (client.getIdClient() > lastId) {
                    lastId = client.getIdClient();
                }
            }
            return lastId;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Modifie un compte client avec le nouveau client passé en paramètre
     * 
     * @param client
     * @throws Exception 
     */
    public void editAccount(Client client) throws Exception{
        try {
            em.merge(client);
        } catch (Exception e) {
            throw e;
        }
    }

}
