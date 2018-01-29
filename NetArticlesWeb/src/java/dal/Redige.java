/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;



/**
 *
 * @author Epulapp
 */

public class Redige {

    private static final long serialVersionUID = 1L;
    protected RedigePK redigePK;
    private int part;
    private Article article;
    private Auteur auteur;

    public Redige() {
    }

    public Redige(RedigePK redigePK) {
        this.redigePK = redigePK;
    }

    public Redige(RedigePK redigePK, int part) {
        this.redigePK = redigePK;
        this.part = part;
    }

    public Redige(int idArticle, int idAuteur) {
        this.redigePK = new RedigePK(idArticle, idAuteur);
    }

    public RedigePK getRedigePK() {
        return redigePK;
    }

    public void setRedigePK(RedigePK redigePK) {
        this.redigePK = redigePK;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
        hash += (redigePK != null ? redigePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Redige)) {
            return false;
        }
        Redige other = (Redige) object;
        if ((this.redigePK == null && other.redigePK != null) || (this.redigePK != null && !this.redigePK.equals(other.redigePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Redige[ redigePK=" + redigePK + " ]";
    }
    
}
