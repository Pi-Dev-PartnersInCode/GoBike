/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.participation;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class SingleEventPage extends SideMenuBaseFormSM {

    private static final int[] COLORS = {0x343434, 0xe6b31e, 0xcacaca};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    int id_bs;
    int x;

    Form current;

    public SingleEventPage(Resources res, int x, Form f, String username, int id, int ids, String img) {

        super("Newsfeed", BoxLayout.y());
        this.x = x;

        ArrayList<event> sprints = srv.getSprint(username);
        event s = sprints.get(x);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
//        id_bs=srv.getid_bs(username);
        // createDemo(res);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.west(
                        BoxLayout.encloseY(
                                new Label(username, "Title"),
                                new Label("connected as " + username, "SubTitle")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        this.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS, e -> new Eventspage(res, username, id, img).show());
        setupSideMenu(res, img);
        Container init = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container cx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Image pic = res.getImage("bar.png");
        
        pic = pic.scaled(80, 820);
        cx.add(pic);
        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button viss = new Button();
        Button aa = new Button();
        Label o = new Label(s.getNomEvent() + "          ");
        init.add(o);
        c0.add(init);
        c0.add(c);
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label d = new Label("Event Date :");
        d.setUIID("PaddedLabel");
        SpanLabel d1 = new SpanLabel(s.getDateEvent());
        d1.setUIID("blacked");
        c1.add(d);
        c1.add(d1);
        c0.add(c1);
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label d0 = new Label("Location :");
        d0.setUIID("PaddedLabel");
        SpanLabel d2 = new SpanLabel(s.getLieu());
        d2.setUIID("blacked");
        c2.add(d0);
        c2.add(d2);
        c0.add(c2);
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label d5 = new Label("Description :");
        d5.setUIID("PaddedLabel");
        SpanLabel qte = new SpanLabel("" + s.getDescription());
        qte.setUIID("blacked");
        c3.add(d5);
        c3.add(qte);
        c0.add(c3);
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label d6 = new Label("Event Type :");
        d6.setUIID("PaddedLabel");
        SpanLabel qte1 = new SpanLabel("" + s.getType());
        qte1.setUIID("blacked");

        c4.add(d6);
        c4.add(qte1);

        c0.add(c4);
        
        event y = new event(s.getId(),"","","","","");
        cx.add(c0);
        add(cx);
        cb.add(viss);
        add(cb);
        
      if (srv.checkparticipation(y,id))
      {
          viss.setText("particper");
               viss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                srv.participer(y,id);
                Dialog.show("Check", "Participation engistrée", "Ok", "");
            }
        });        
      }
      else{
           viss.setText("annuler Participation");
         viss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                srv.supprimerparticipation(s,id);
                Dialog.show("Check", "Participation supprimé", "Ok", "");
            }
        });
      }
    }

    @Override
    protected void showOtherForm(Resources res) {
        new HomePage(res, username1, id1, img1).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
        new Eventspage(res, username1, id1, img1).show();
    }

    @Override
    protected void showForm2(Resources res) {

    }

}
