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

/**
 *
 * @author Epulapp
 */
@Stateless
public class DomaineFacade {

    ClientNetArticles clientNetArticles = new ClientNetArticles();
    
    /**
     * Récupère la liste des domaines
     * @return List<Domaine>
     * @throws Exception 
     */
    public List<Domaine> getFields() throws Exception {
        List<Domaine> listFields = new ArrayList<>();
        try {
            listFields = clientNetArticles.getFields();
        } catch (Exception e) {
            throw e;
        }
        return listFields;
    }
}
