/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author msi
 */
public class Coaching {
    
    private int id;
    private Date dateSeance;
    private String validation;
    private float prix;
    private String nomUser;
    private String prenomUser;
    

    public Coaching() {
    }

    public Coaching(int id, Date dateSeance, String validation, float prix, String nomUser, String prenomUser) {
        this.id = id;
        this.dateSeance = dateSeance;
        this.validation = validation;
        this.prix = prix;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }

    public Coaching(Date daterdv, String validation, float prix, String nomUser, String prenomUser) {
        this.dateSeance = dateSeance;
        this.validation = validation;
        this.prix = prix;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    
    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    
    
    
    
}
