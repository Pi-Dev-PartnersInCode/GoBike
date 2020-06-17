/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.SideMenuBaseFormSM;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public  class Eventspage extends SideMenuBaseFormSM {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
  

    public Eventspage(Resources res,String username, int id,String img)  {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        username1=username;
        id1=id;
        img1=img;
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
         ArrayList<event> sprints = srv.getSprint(username);
       // System.err.println(sprints);
       int k = sprints.size();
      Container remainingTasks = BoxLayout.encloseY(
                        new Label("Team", "CenterTitle"),
                        new Label(sprints.get(0).getDescription(), "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("Project", "CenterTitle"),
                        new Label(sprints.get(0).getType(), "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");
  FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
         
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("welcome "+username, "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        
        tb.setTitleComponent(titleCmp);
                        
     
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
         
        
        for (int i=0;i<k;i++){
        addButtonBottom(res,arrowDown, sprints.get(i).getNomEvent(),sprints.get(i).getDateEvent(), 0x5ae29d, true,i,username,id,sprints.get(i).getId(),img);
        }
        setupSideMenu(res,img);
    }


    
    private void addButtonBottom(Resources res,Image arrowDown, String text,String date, int color, boolean first,int x,String username
    ,int idu,int ids,String img) {
        MultiButton finishLandingPage = new MultiButton("Date evenement "+date);
     
       // finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        Label l= new Label(text);
     //   l.setUIID("TodayEntry");
                l.setUIID("blacked");

          add(l);
        finishLandingPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new SingleEventPage(res,x,current,username,idu,ids,img).show();
              //  new Sprints_Form(res,img1,username1,id1,x).show();

            }
        });
        add(FlowLayout.encloseIn(finishLandingPage));
        
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        /*       g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);*/
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new HomePage(res,username1,id1,img1).show();
    }
    protected void showOtherForm1(Resources res) {
        new Eventspage(res,username1,id1,img1).show();
    }
    
    protected void showsprint(Resources res,int id) {
        
    }

    @Override
    protected void showForm2(Resources res) {
        new My_events(res, username1, id1, img1).show();

    }



    

    

    

}
