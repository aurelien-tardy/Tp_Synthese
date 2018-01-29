/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author Epulapp
 */
public class Droits {

    private static final long serialVersionUID = 1L;
    protected DroitsPK droitsPK;
    private Character etatDroits;
    private BigDecimal montantsDroits;
    private Auteur auteur;

    public Droits() {
    }

    public Droits(DroitsPK droitsPK) {
        this.droitsPK = droitsPK;
    }

    public Droits(DroitsPK droitsPK, Character etatDroits) {
        this.droitsPK = droitsPK;
        this.etatDroits = etatDroits;
    }

    public Droits(int idAuteur, Date dateTrimestre) {
        this.droitsPK = new DroitsPK(idAuteur, dateTrimestre);
    }

    public DroitsPK getDroitsPK() {
        return droitsPK;
    }

    public void setDroitsPK(DroitsPK droitsPK) {
        this.droitsPK = droitsPK;
    }

    public Character getEtatDroits() {
        return etatDroits;
    }

    public void setEtatDroits(Character etatDroits) {
        this.etatDroits = etatDroits;
    }

    public BigDecimal getMontantsDroits() {
        return montantsDroits;
    }

    public void setMontantsDroits(BigDecimal montantsDroits) {
        this.montantsDroits = montantsDroits;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (droitsPK != null ? droitsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Droits)) {
            return false;
        }
        Droits other = (Droits) object;
        if ((this.droitsPK == null && other.droitsPK != null) || (this.droitsPK != null && !this.droitsPK.equals(other.droitsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Droits[ droitsPK=" + droitsPK + " ]";
    }

}
