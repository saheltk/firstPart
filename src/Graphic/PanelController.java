package Graphic;

import Graphic.Panels.Login;
import Graphic.Panels.Menu;

import javax.swing.*;
import java.awt.*;

public class PanelController {
    private static JPanel mainPanel;

    PanelController(){
        mainPanel= MainPanel.get();
    }

    public static void changePanel(MainPanel.PanelName name) {

        switch (name) {
            case play:
                //mainPanel = Login.loginPanel();
                break;
            case shop:
              //  mainPanel = Shop.shopPanel();
                break;
            case status:
              //  mainPanel = Status.statusPanel();
                break;
            case collection:
               // mainPanel = Collection.collectionPanel();
                break;
            case menu:
                System.out.println("menu");
                mainPanel = Menu.menuPanel();
                break;
            case login:
                MainPanel.setPanel("login");
                break;

        }
    }


}
