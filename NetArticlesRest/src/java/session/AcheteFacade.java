/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Flow
 */
@Stateless
public class AcheteFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    /**
     * Renvoie la liste des achats d'un client
     * 
     * @param idClient
     * @return
     * @throws Exception 
     */
    public List<Achete> getListAcheteByIdClient(Integer idClient) throws Exception {
        try {
            Query requete = em.createNamedQuery("Achete.findByIdClient");
            requete.setParameter("idClient", idClient);
            return requete.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Renvoie la list de tous les achats
     * 
     * @return
     * @throws Exception 
     */
    public List<Achete> getAllAchete() throws Exception{
        try {
            Query requete = em.createNamedQuery("Achete.findAll");
            return requete.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Valide le panier si c'est possible
     * 
     * @param achat
     * @return String
     */
    public String validerPanier(Achete achat) {
        String message = "";
        try {
            Calendar c = Calendar.getInstance();
            achat.setDateAchat(new Date(c.getTimeInMillis()));
            em.persist(achat);
            message = "0Panier validé";
        } catch (Exception e) {
            message = "1Panier non validé";
            throw e;
        } finally {
            return message;
        }
    }
}
