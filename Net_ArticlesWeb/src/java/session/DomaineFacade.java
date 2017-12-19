/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.ClientNetArticles;
import dal.Domaine;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
public class DomaineFacade {

    public List<Domaine> getFields() throws Exception {
        List<Domaine> listFields = new ArrayList<>();
        try {
            ClientNetArticles clientNetArticles = new ClientNetArticles();
            listFields = clientNetArticles.getFields();
        } catch (Exception e) {
            throw e;
        }
        return listFields;
    }
}
