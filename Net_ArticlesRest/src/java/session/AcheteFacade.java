/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import dal.Article;
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

    public List<Achete> getListAcheteByIdClient(Integer idClient) throws Exception {
        try {
            Query requete = em.createNamedQuery("Achete.findByIdClient");
            requete.setParameter("idClient", idClient);
            return requete.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public String validerPanier(Achete achat) {
        String message = "";
        try {
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
