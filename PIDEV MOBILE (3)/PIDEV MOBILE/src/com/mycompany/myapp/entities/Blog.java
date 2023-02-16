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
public class Blog {
    
    private int id;
    private String titre;
    private String Contenu;
    private Date date;
    private String auteur;
    private String image;
    private String categorie;
    private float rating;
    

    public Blog() {
    }

    public Blog(int id, String titre, String Contenu, Date date, String auteur, String image, String categorie) {
        this.id = id;
        this.titre = titre;
        this.Contenu = Contenu;
        this.date = date;
        this.auteur = auteur;
        this.image = image;
        this.categorie = categorie;
    }

    public Blog(String titre, String Contenu, Date date, String auteur, String image, String categorie) {
        this.titre = titre;
        this.Contenu = Contenu;
        this.date = date;
        this.auteur = auteur;
        this.image = image;
        this.categorie = categorie;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    
    
}
