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
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.entities.Sprint_review;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.services.ServiceSprint_retro.instance;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hajer
 */
public class ServiceSprint_review {
    public ArrayList<User> users;
        public ArrayList<Sprint_review> review;
public static ServiceSprint_review instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceSprint_review() {
         req = new ConnectionRequest();
    }

    public static ServiceSprint_review getInstance() {
        if (instance == null) {
            instance = new ServiceSprint_review();
        }
        return instance;
    }
    
     public ArrayList<Sprint_review> getReview(String name){
    String url = Statics.BASE_URL+"/Meeting/fin/"+name+"";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    review= parsereview(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return review;
    }
   
   public ArrayList<Sprint_review> parsereview(String jsonText){
        try {
            review=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                          
              Sprint_review sr = new Sprint_review();
               float id = Float.parseFloat(obj.get("id_sprint_review").toString());
               float id_sprint = Float.parseFloat(obj.get("ide_sprint").toString());
               float id_equipe = Float.parseFloat(obj.get("ide_equipe").toString());
               float id_projet = Float.parseFloat(obj.get("ide_projet").toString());
               float id_po = Float.parseFloat(obj.get("ide_product_owner").toString());
               //float id1 = Float.parseFloat(obj.get("id").toString());
       
               sr.setId_sprint_review((int)id);
               sr.setIde_sprint((int)id_sprint);
               sr.setIde_equipe((int)id_equipe);
               sr.setIde_projet((int)id_projet);
               sr.setIde_product_owner((int)id_po);
               
               //sp.setId((int)id1);
               sr.setRemarque_review_equipe(obj.get("remarque_review_equipe").toString());
               sr.setRemarque_review_product_owner(obj.get("remarque_review_product_owner").toString());
              sr.setDate_sprint_review(obj.get("Date_sprint_review").toString());

               sr.setNom_equipe(obj.get("nom_equipe").toString());
               sr.setNom_projet(obj.get("nom_projet").toString());
               sr.setDescription(obj.get("description").toString());
               sr.setUsername(obj.get("username").toString());
               
              
                
              
                
                review.add(sr);
            }
        } catch (IOException ex) {
            
        }
        return review;
    }
   
     public boolean addTask(Sprint_review t) {
        String url = Statics.BASE_URL + "/Meeting/addd/" + t.getDescription()+ "/" +t.getRemarque_review_equipe()+ "/" +t.getRemarque_review_product_owner()+ "/" +t.getDate_sprint_review()+ "/" +t.getNom_equipe()+ "/" +t.getNom_projet()+"/" +t.getUsername()+"";
        
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
        String url = Statics.BASE_URL + "/Meeting/del/"+id+"";
        
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
