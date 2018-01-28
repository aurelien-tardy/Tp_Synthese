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

    /**
     * Fonction permettant de créer un nouveau compte dans la table Banque.compte
     * @param compte
     * @return le compte créer
     * @throws Exception 
     */
    public Compte createAccount(Compte compte) throws Exception {
        try {
            em.persist(compte);
            return compte;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Récupération du compte afin de récupérer l'attribut solde
     * @param id
     * @return 
     * @throws Exception 
     */
    public Compte getSoldeById(Integer id) throws Exception {
        try {
            // Création d'une query permettant de récupérer un compte avec un idCompte comme paramètre
            Query requete = em.createNamedQuery("Compte.findByIdCompte");
            requete.setParameter("idCompte", id);
            return (Compte) requete.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Permet d'éditer le solde d'un compte après l'achat d'un ou plusieurs articles
     * @param compte
     * @throws Exception 
     */
    public void editSolde(Compte compte) throws Exception {
        try {
            // Edit 
            em.merge(compte);
        } catch (Exception e) {
            throw e;
        }
    }
}
