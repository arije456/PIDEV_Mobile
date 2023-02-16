/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Consultation;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceConsultation {

    public ArrayList<Consultation> consultations;
    
    public static ServiceConsultation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceConsultation() {
         req = new ConnectionRequest();
    }

    public static ServiceConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceConsultation();
        }
        return instance;
    }
    


    public ArrayList<Consultation> parseConsultations(String jsonText){
                try {

                    System.out.println(jsonText);
            consultations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Consultation a = new Consultation();
                                
                float id = Float.parseFloat(obj.get("idC").toString());
                a.setId((int) id);
                a.setNom(obj.get("nom").toString());
                a.setPrenom(obj.get("prenom").toString());
                a.setAge((int) Float.parseFloat(obj.get("age").toString()));
                a.setSexe(obj.get("sexe").toString());

                try {  
                            Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(obj.get("dateRdv").toString());
                            a.setDaterdv(date1);

                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }


                a.setEtatPhysique(obj.get("etatPhysique").toString());
                a.setEmail(obj.get("email").toString());
                a.setCategorie(obj.get("categorieC").toString());
                               
                consultations.add(a);


            }
        } catch (IOException ex) {
            
        }
        return consultations;
    }
    public ArrayList<Consultation> getAllConsultations(){
        String url = Statics.BASE_URL+"consultation/mobile/aff";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                consultations = parseConsultations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return consultations;
    }

    public ArrayList<Consultation> getUser(int id){
        String url = Statics.BASE_URL+"consultation/mobile/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                consultations = parseConsultations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return consultations;
    }


    public boolean addConsultation(Consultation u) {
        String url = Statics.BASE_URL + "consultation/mobile/new?nom="+u.getNom()+"&prenom="+u.getPrenom()+"&age="+u.getAge()+"&sexe="+u.getSexe()+"&email="+u.getEmail()+"&etatphy="+u.getEtatPhysique()+"&daterdv="+u.getDaterdv()+"&categorie="+u.getCategorie();//création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

        public boolean editConsultation(Consultation u) {
        String url = Statics.BASE_URL + "consultation/mobile/editcons?id="+u.getId()+"&nom="+u.getNom()+"&prenom="+u.getPrenom()+"&age="+u.getAge()+"&sexe="+u.getSexe()+"&email="+u.getEmail()+"&etatphy="+u.getEtatPhysique()+"&daterdv="+u.getDaterdv()+"&categorie="+u.getCategorie();//création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteConsultation(int id) {
        String url = Statics.BASE_URL + "consultation/mobile/del?id=" + id;
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
