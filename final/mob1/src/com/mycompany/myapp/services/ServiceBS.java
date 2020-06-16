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
import com.mycompany.gui.Eventspage;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.participation;
import com.mycompany.myapp.entities.Tache;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.UserSt;
import com.mycompany.myapp.entities.UserSt_bp;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceBS {

    public ArrayList<User> users;
    public ArrayList<event> sprints;
    public ArrayList<participation> sprints8;

    public ArrayList<UserSt> usrst;
    public ArrayList<UserSt_bp> usrst_bp;
    public ArrayList<Tache> taches;
    public int id_bs;

    public ArrayList<String> names;

    public static ServiceBS instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceBS() {
        req = new ConnectionRequest();
    }

    public static ServiceBS getInstance() {
        if (instance == null) {
            instance = new ServiceBS();
        }
        return instance;
    }

    public ArrayList<User> parseuser(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setRoles(obj.get("roles").toString());
                //    u.setImg(obj.get("image_user").toString());
                users.add(u);
            }
        } catch (IOException ex) {

        }
        return users;
    }

    public ArrayList<User> getuser(String name) {
        String url = Statics.BASE_URL + "find_user/" + name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseuser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public ArrayList<event> getSprint(String name) {
        String url = Statics.BASE_URL + "find_event";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sprints = findEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //System.out.println(sprints);
        return sprints;
    }

    public ArrayList<event> my_events(int name) {
        String url = Statics.BASE_URL + "my_event/" + name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sprints = findEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //System.out.println(sprints);
        return sprints;
    }

    public ArrayList<event> findEvents(String jsonText) {
        try {
            sprints = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                event u = new event();
                float id = Float.parseFloat(obj.get("id").toString());

                u.setId((int) id);
                u.setNomEvent(obj.get("nom").toString());
                u.setDateEvent(obj.get("dateevent").toString());
                u.setLieu(obj.get("lieu").toString());
                u.setDescription(obj.get("description").toString());
                u.setType(obj.get("type").toString());

                sprints.add(u);
            }
        } catch (IOException ex) {

        }
        return sprints;
    }

    public ArrayList<participation> parsesprint8(String jsonText) {
        try {
            sprints8 = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            participation u = new participation();
            float id = Float.parseFloat(tasksListJson.get("id").toString());

            u.setId((int) id);
            float id_event = Float.parseFloat(tasksListJson.get("id_event").toString());

            u.setId_event((int) id_event);
            u.setRecord(tasksListJson.get("record").toString());
            float ranking = Float.parseFloat(tasksListJson.get("ranking").toString());
            u.setRanking((int) ranking);

            float id_membre = Float.parseFloat(tasksListJson.get("id_membre").toString());

            u.setId_Membre((int) id_membre);

            sprints8.add(u);
            return sprints8;

        } catch (IOException ex) {

        }
        return sprints8;
    }

    public int parseid_bs(String jsonText) {
        int k = 0;
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("id").toString());
                k = (int) id;
            }
        } catch (IOException ex) {

        }
        return k;
    }

    public boolean participer(event s, int userId) {
        String url = Statics.BASE_URL + "mob_participer/" + s.getId() + "/" + userId;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
    public boolean supprimerparticipation(event s, int userId) {
        String url = Statics.BASE_URL + "suppPar/" + s.getId() + "/" + userId;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    public boolean checkparticipation(event s, int name) {
        String url = Statics.BASE_URL + "testPar/" + s.getId() + "/" + name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                System.out.println(new String(req.getResponseData()).isEmpty());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return new String(req.getResponseData()).equals("[]");
    }

    public ArrayList<String> parsename(String jsonText) {
        try {
            names = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                String name = (obj.get("username").toString());
                names.add(name);
            }
        } catch (IOException ex) {

        }
        return names;
    }

}
