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
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Coaching;
import com.mycompany.myapp.entities.Coaching;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceCoaching {

    public ArrayList<Coaching> coachings;
    
    public static ServiceCoaching instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceCoaching() {
         req = new ConnectionRequest();
    }

    public static ServiceCoaching getInstance() {
        if (instance == null) {
            instance = new ServiceCoaching();
        }
        return instance;
    }
    


    public ArrayList<Coaching> parseCoachings(String jsonText){
                try {

                    System.out.println(jsonText);
            coachings=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Coaching a = new Coaching();
                                
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setNomUser(obj.get("nomUser").toString());
                a.setPrenomUser(obj.get("prenomUser").toString());
                a.setPrix(Float.parseFloat(obj.get("prix").toString()));
                if(obj.get("validation")==null)
                {
                a.setValidation("En cours");
                }
                else
                {
                a.setValidation(obj.get("validation").toString());
                }

                try {  
                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateS").toString());
                            a.setDateSeance(date1);

                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }
                               
                coachings.add(a);


            }
        } catch (IOException ex) {
            
        }
        return coachings;
    }
    public ArrayList<Coaching> getAllCoachings(){
        String url = Statics.BASE_URL+"coaching/mobile/aff";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coachings = parseCoachings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return coachings;
    }


    public boolean addCoaching(Coaching u) {
        String url = Statics.BASE_URL + "coaching/mobile/new?dates="+u.getDateSeance()+"&prix="+u.getPrix()+"&nom="+u.getNomUser()+"&prenom="+u.getPrenomUser()+"&validation="+u.getValidation();//création de l'URL
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

        public boolean editCoaching(Coaching u) {
        String url = Statics.BASE_URL + "coaching/mobile/editcoach?id="+u.getId()+"&dates="+u.getDateSeance()+"&prix="+u.getPrix()+"&nom="+u.getNomUser()+"&prenom="+u.getPrenomUser()+"&validation="+u.getValidation();//création de l'URL
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

    public boolean deleteCoaching(int id) {
        String url = Statics.BASE_URL + "coaching/mobile/del?id=" + id;
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
