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
public class Consultation {
    
    private int id;
    private String nom;
    private int age;
    private String sexe;
    private Date daterdv;
    private String etatPhysique;
    private String categorie;
    private String prenom;
    private String email;
    

    public Consultation() {
    }

    public Consultation(int id, String nom, int age, String sexe, Date daterdv, String etatPhysique, String categorie, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.sexe = sexe;
        this.daterdv = daterdv;
        this.etatPhysique = etatPhysique;
        this.categorie = categorie;
        this.prenom = prenom;
        this.email = email;
    }

    public Consultation(String nom, int age, String sexe, Date daterdv, String etatPhysique, String categorie, String prenom, String email) {
        this.nom = nom;
        this.age = age;
        this.sexe = sexe;
        this.daterdv = daterdv;
        this.etatPhysique = etatPhysique;
        this.categorie = categorie;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(Date daterdv) {
        this.daterdv = daterdv;
    }

    public String getEtatPhysique() {
        return etatPhysique;
    }

    public void setEtatPhysique(String etatPhysique) {
        this.etatPhysique = etatPhysique;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
