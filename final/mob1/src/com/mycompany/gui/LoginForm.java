package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.mycompany.gui.LoginVideo;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBS;
import static java.lang.System.err;
import java.util.ArrayList;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {

    public LoginForm(Resources theme) {

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome to GoBike ", "WelcomeWhite")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("avatar.jpg");

        Image mask = theme.getImage("circle.png").scaled(500, 500);

        //ImageViewer mask = new ImageViewer(theme.getImage("round-mask.jpg"),scaled);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());

        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        //profilePicLabel.setMask(mask.createMask());
        //ImageViewer pf = new ImageViewer(theme.getImage("avatar.jpg"));

        TextField login = new TextField("", "Login", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 6);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 6);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            ServiceBS srv = new ServiceBS();
            ArrayList<User> names = srv.getuser(login.getText());

            if ((names.isEmpty())) {
                Dialog.show("erreur ", "username ou mot de passe incorrecte" + "", "OK", null);
            } else if ((names.get(0).getUsername().equals(login.getText())) && !(password.getText().isEmpty())) { //String img = "file://"+names.get(0).getImg().substring(3);

                new LoginVideo(theme, names.get(0).getRoles(),
                         names.get(0).getUsername(), names.get(0).getId(), "j").show();
                System.err.println(names.get(0).getId());
            } else {
                Dialog.show("erreur ", "username ou mot de passe incorrecte" + "", "OK", null);
            }
            Toolbar.setGlobalToolbar(true);
        });

        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label("");
        }

        Container by = BoxLayout.encloseY(
                profilePicLabel,
                welcome,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
