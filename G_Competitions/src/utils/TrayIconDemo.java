package utils;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo {

   
    

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/images/LogoPi.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "GoBike");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("GoBike");
        tray.add(trayIcon);

        trayIcon.displayMessage("GoBike", "participation registered", MessageType.INFO);
    }
}