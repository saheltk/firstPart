package Graphic;

import Graphic.Panels.Login;

import javax.swing.*;
import java.awt.*;

public class PanelController {
    private static JPanel mainPanel;

    PanelController(){
        mainPanel= MainPanel.get();
    }

    public static void changePanel(PanelNames name) {
        mainPanel.setLayout(new BorderLayout());

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
               // mainPanel = Menu.menuPanel();
                break;
            case login:
                mainPanel = Login.loginPanel();
                break;

        }
    }

    protected enum PanelNames {
        play,
        shop,
        status,
        collection,
        menu,
        login
    }
}
