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
public class Auteur {

    private static final long serialVersionUID = 1L;
    private Integer idAuteur;
    private String identiteAuteur;
    private String adresseAuteur;
    private String loginAuteur;
    private String pwdAuteur;
    private List<Redige> redigeList;
    private List<Droits> droitsList;

    public Auteur() {
    }

    public Auteur(Integer idAuteur) {
        this.idAuteur = idAuteur;
    }

    public Auteur(Integer idAuteur, String loginAuteur, String pwdAuteur) {
        this.idAuteur = idAuteur;
        this.loginAuteur = loginAuteur;
        this.pwdAuteur = pwdAuteur;
    }

    public Integer getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Integer idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getIdentiteAuteur() {
        return identiteAuteur;
    }

    public void setIdentiteAuteur(String identiteAuteur) {
        this.identiteAuteur = identiteAuteur;
    }

    public String getAdresseAuteur() {
        return adresseAuteur;
    }

    public void setAdresseAuteur(String adresseAuteur) {
        this.adresseAuteur = adresseAuteur;
    }

    public String getLoginAuteur() {
        return loginAuteur;
    }

    public void setLoginAuteur(String loginAuteur) {
        this.loginAuteur = loginAuteur;
    }

    public String getPwdAuteur() {
        return pwdAuteur;
    }

    public void setPwdAuteur(String pwdAuteur) {
        this.pwdAuteur = pwdAuteur;
    }

    public List<Redige> getRedigeList() {
        return redigeList;
    }

    public void setRedigeList(List<Redige> redigeList) {
        this.redigeList = redigeList;
    }

    public List<Droits> getDroitsList() {
        return droitsList;
    }

    public void setDroitsList(List<Droits> droitsList) {
        this.droitsList = droitsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuteur != null ? idAuteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auteur)) {
            return false;
        }
        Auteur other = (Auteur) object;
        if ((this.idAuteur == null && other.idAuteur != null) || (this.idAuteur != null && !this.idAuteur.equals(other.idAuteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Auteur[ idAuteur=" + idAuteur + " ]";
    }

}
