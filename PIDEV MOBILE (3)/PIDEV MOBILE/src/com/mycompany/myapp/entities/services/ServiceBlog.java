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
import com.mycompany.myapp.entities.Blog;

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
public class ServiceBlog {

    public ArrayList<Blog> blogs;
    public ArrayList<Blog> blogrates ;

    public static ServiceBlog instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest reqq;
    public ServiceBlog() {
         req = new ConnectionRequest();
         reqq = new ConnectionRequest();
    }

    public static ServiceBlog getInstance() {
        if (instance == null) {
            instance = new ServiceBlog();
        }
        return instance;
    }
    


    public ArrayList<Blog> parseBlogs(String jsonText){
                try {

                    System.out.println(jsonText);
            blogs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Blog a = new Blog();
                                
                float id = Float.parseFloat(obj.get("idB").toString());
                a.setId((int) id);
                a.setTitre(obj.get("titre").toString());
                a.setContenu(obj.get("contenu").toString());
                        try {  
                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date").toString());
                            a.setDate(date1);

                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }
                

                a.setAuteur(obj.get("auteur").toString());
                a.setImage(obj.get("image").toString());
                a.setCategorie(obj.get("categorie").toString());
                               
                blogrates= getRating(a.getId());
                int nbrate = 0 ;
                float totalrate = 0 ;
                for (Blog fi : blogrates) {
                    System.out.println("ena rate : " +fi.getRating());
                    nbrate++;
                    totalrate = totalrate + fi.getRating() ;                    
                }
                a.setRating(totalrate/nbrate);
                blogs.add(a);


            }
        } catch (IOException ex) {
            
        }
        return blogs;
    }
    
        public ArrayList<Blog> parseRatings(String jsonText){
                try {

                    System.out.println(jsonText);
                        
                    blogrates =new ArrayList<>();
            JSONParser jj = new JSONParser();
            Map<String,Object> tasksListJsonn = jj.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> listt = (List<Map<String,Object>>)tasksListJsonn.get("root");
            for(Map<String,Object> objj : listt){
                Blog a = new Blog();
                                
                a.setRating(Float.parseFloat(objj.get("rat").toString()));

                               
                blogrates.add(a);


            }
        } catch (IOException ex) {
            
        }
        return blogrates;
    }
    public ArrayList<Blog> getAllBlogs(){
        String url = Statics.BASE_URL+"blg/mobile/aff";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blogs = parseBlogs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return blogs;
    }

    public ArrayList<Blog> getRating(int id){
        String urll = Statics.BASE_URL+"blg/mobile/affrating?idblg="+id;
        System.out.println(urll);
        reqq.setUrl(urll);
        reqq.setPost(false);
        reqq.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blogrates = parseRatings(new String(reqq.getResponseData()));
                reqq.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(reqq);
        return blogrates;
    }


    public boolean addBlog(Blog u) {
        String url = Statics.BASE_URL + "blg/mobile/new?titre="+u.getTitre()+"&contenu="+u.getContenu()+"&auteur="+u.getAuteur()+"&image="+u.getImage()+"&categorie="+u.getCategorie();//création de l'URL
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

        public boolean editBlog(Blog u) {
        String url = Statics.BASE_URL + "blg/mobile/editblg?id="+u.getId()+"&titre="+u.getTitre()+"&contenu="+u.getContenu()+"&auteur="+u.getAuteur()+"&image="+u.getImage()+"&categorie="+u.getCategorie(); //création de l'URL
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

    public boolean deleteBlog(int id) {
        String url = Statics.BASE_URL + "blg/mobile/del?id=" + id;
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

    public boolean addRate(Blog fi) {
        String url = Statics.BASE_URL + "blg/mobile/addrate?idblg="+fi.getId()+"&rate="+fi.getRating(); //création de l'URL
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

}
