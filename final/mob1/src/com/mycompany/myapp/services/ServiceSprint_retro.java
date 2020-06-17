/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.entities.User;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hajer
 */
public class ServiceSprint_retro {
    
     public ArrayList<User> users;
    public ArrayList<Sprint_retrospective> retro;
    
    public static ServiceSprint_retro instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceSprint_retro() {
         req = new ConnectionRequest();
    }

    public static ServiceSprint_retro getInstance() {
        if (instance == null) {
            instance = new ServiceSprint_retro();
        }
        return instance;
    }
      public ArrayList<Sprint_retrospective> getRetro(String name){
    String url = Statics.BASE_URL+"/Meeting/retro/"+name+"";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    retro= parseretro(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return retro;
    }
   
   public ArrayList<Sprint_retrospective> parseretro(String jsonText){
        try {
            retro=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                          

             Sprint_retrospective sp = new Sprint_retrospective();
               float id = Float.parseFloat(obj.get("id_sprint_retrospective").toString());
               float id_sprint = Float.parseFloat(obj.get("ide_sprint").toString());
               float id_equipe = Float.parseFloat(obj.get("ide_equipe").toString());
               float id_projet = Float.parseFloat(obj.get("ide_projet").toString());
               //float id1 = Float.parseFloat(obj.get("id").toString());
       
               sp.setId_sprint_retrospective((int)id);
               sp.setIde_sprint((int)id_sprint);
               sp.setIde_equipe((int)id_equipe);
               sp.setIde_projet((int)id_projet);
               //sp.setId((int)id1);
               sp.setDescription_TODO(obj.get("description_TODO").toString());
               sp.setDate_sprint_retrospective(obj.get("Date_sprint_retrospective").toString());
               sp.setNom_equipe(obj.get("nom_equipe").toString());
               sp.setNom_projet(obj.get("nom_projet").toString());
               sp.setDescritpion(obj.get("description").toString());
               
                
                System.out.println("ferfe");
                
              
                
                retro.add(sp);
            }
        } catch (IOException ex) {
            
        }
        return retro;
    }
   
   public boolean addTask(Sprint_retrospective t) {
        String url = Statics.BASE_URL + "/Meeting/tasks/new/" + t.getDescription()+ "/" +t.getDescription_TODO()+ "/" +t.getDate_sprint_retrospective()+ "/" +t.getNom_equipe()+ "/" +t.getNom_projet()+"";
        
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
   
   public boolean delete(int id) {
        String url = Statics.BASE_URL + "/Meeting/dele/"+id+"";
        
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
