/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Epulapp
 */
public class RedigePK {


    private int idArticle;

    private int idAuteur;

    public RedigePK() {
    }

    public RedigePK(int idArticle, int idAuteur) {
        this.idArticle = idArticle;
        this.idAuteur = idAuteur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArticle;
        hash += (int) idAuteur;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedigePK)) {
            return false;
        }
        RedigePK other = (RedigePK) object;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        if (this.idAuteur != other.idAuteur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.RedigePK[ idArticle=" + idArticle + ", idAuteur=" + idAuteur + " ]";
    }
    
}
