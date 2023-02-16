/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author msi
 */
public class Produit {
    
    private int id;
    private String nom;
    private float prix;
    private String image;
    private String categorie;

    public Produit() {
    }

    public Produit(int id, String nom, float prix, String image, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.categorie = categorie;
    }

    public Produit(String nom, float prix, String image, String categorie) {
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.categorie = categorie;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    

    
}
