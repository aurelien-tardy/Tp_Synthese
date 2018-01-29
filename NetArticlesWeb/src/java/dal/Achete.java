/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;


import java.util.Date;


/**
 *
 * @author Epulapp
 */

public class Achete{

    private static final long serialVersionUID = 1L;
    protected AchetePK achetePK;
    private Date dateAchat;
    private Article article;
    private Client client;

    public Achete() {
    }

    public Achete(AchetePK achetePK) {
        this.achetePK = achetePK;
    }

    public Achete(int idClient, int idArticle) {
        this.achetePK = new AchetePK(idClient, idArticle);
    }

    public AchetePK getAchetePK() {
        return achetePK;
    }

    public void setAchetePK(AchetePK achetePK) {
        this.achetePK = achetePK;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (achetePK != null ? achetePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Achete)) {
            return false;
        }
        Achete other = (Achete) object;
        if ((this.achetePK == null && other.achetePK != null) || (this.achetePK != null && !this.achetePK.equals(other.achetePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Achete[ achetePK=" + achetePK + " ]";
    }
    
}
