
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import static com.codename1.ui.Image.createImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseFormPO extends Form {

    public SideMenuBaseFormPO(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseFormPO(String title) {
        super(title);
    }

    public SideMenuBaseFormPO() {
    }

    public SideMenuBaseFormPO(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res,String img) {
   Image i=null;
        try {
           i = createImage(img);
          
        } catch (IOException ex) {
        }
        //Image profilePic = res.getImage("log.png");
        Image profilePic = i;
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  po", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  home", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  feature", FontImage.MATERIAL_ADD_CHART,  e -> showfeat(res));
        getToolbar().addMaterialCommandToSideMenu("  user stories", FontImage.MATERIAL_ADD_COMMENT,  e -> showuserbp(res));
        getToolbar().addMaterialCommandToSideMenu("progress", FontImage.MATERIAL_TRENDING_UP,  e -> Chartshow(res));
        
        getToolbar().addMaterialCommandToSideMenu("update profile", FontImage.MATERIAL_SETTINGS,  e -> editprofile(res));
        getToolbar().addMaterialCommandToSideMenu("  logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
    protected abstract void editprofile(Resources res);
    protected abstract void showfeat(Resources res);
    protected abstract void Chartshow(Resources res);
    protected abstract void showuserbp(Resources res);
}
