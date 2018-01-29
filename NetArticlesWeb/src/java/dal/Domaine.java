/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.List;

/**
 *
 * @author Epulapp
 */
public class Domaine {

    private static final long serialVersionUID = 1L;
    private Integer idDomaine;
    private String libdomaine;
    private List<Article> articleList;

    public Domaine() {
    }

    public Domaine(Integer idDomaine) {
        this.idDomaine = idDomaine;
    }

    public Integer getIdDomaine() {
        return idDomaine;
    }

    public void setIdDomaine(Integer idDomaine) {
        this.idDomaine = idDomaine;
    }

    public String getLibdomaine() {
        return libdomaine;
    }

    public void setLibdomaine(String libdomaine) {
        this.libdomaine = libdomaine;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomaine != null ? idDomaine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domaine)) {
            return false;
        }
        Domaine other = (Domaine) object;
        if ((this.idDomaine == null && other.idDomaine != null) || (this.idDomaine != null && !this.idDomaine.equals(other.idDomaine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Domaine[ idDomaine=" + idDomaine + " ]";
    }
    
}
