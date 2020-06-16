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
public class ServiceAbsence {
    
     public ArrayList<User> users;
    public ArrayList<Absence> absences;
    
    public static ServiceAbsence instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbsence() {
         req = new ConnectionRequest();
    }

    public static ServiceAbsence getInstance() {
        if (instance == null) {
            instance = new ServiceAbsence();
        }
        return instance;
    }
    
    public ArrayList<Absence> getAbsence(String name){
    String url = Statics.BASE_URL+"/rh/absence/"+name+"";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    absences = parseabsence(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return absences;
    }
   
   public ArrayList<Absence> parseabsence(String jsonText){
        try {
            absences=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             
             Absence u = new Absence();
               float id = Float.parseFloat(obj.get("id_absence").toString());
               float nbr = Float.parseFloat(obj.get("nbre").toString());
               float id_user = Float.parseFloat(obj.get("ide_user").toString());
       
                u.setId_absence((int)id);
                u.setIde_user((int)id_user);
                u.setDate(obj.get("date").toString());
                u.setHeure(obj.get("heure").toString());
                u.setNbre((int)nbr);
                u.setUsername(obj.get("username").toString());
                
                
              
                
                absences.add(u);
            }
        } catch (IOException ex) {
            
        }
        return absences;
    }
   
    public boolean addTask(Absence t) {
        String url = Statics.BASE_URL + "/rh/task/new/" + t.getUsername()+ "/" +t.getHeure()+ "/" +t.getNbre()+ "/" +t.getDate()+"";
        
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
        String url = Statics.BASE_URL + "/rh/del/"+id+"";
        
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
