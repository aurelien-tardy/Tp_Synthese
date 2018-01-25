/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Compte;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.mail.*;

/**
 *
 * @author Epulapp
 */
@Stateless
public class CompteFacade {

    @PersistenceContext(unitName = "BanqueRestPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public Compte createAccount(Compte compte) throws Exception {
        try {
            em.persist(compte);
            return compte;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Compte getSoldeById(Integer id) throws Exception {
        try {
            Query requete = em.createNamedQuery("Compte.findByIdCompte");
            requete.setParameter("idCompte", id);
            return (Compte) requete.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    public void editSolde(Compte compte) throws Exception {
        try {
            em.merge(compte);
        } catch (Exception e) {
            throw e;
        }
    }
}
