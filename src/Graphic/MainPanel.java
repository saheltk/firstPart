package Graphic;

import Graphic.Panels.*;
import Graphic.Panels.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class MainPanel extends JPanel {
    private static final String LOGIN = "login";
    private static final String MENU = "menu" ;
    private static final String SHOP = "shop";
    private static final String STATUS = "status" ;
    private static final String COLLECTIONS = "collections";
    private static final String PLAY = "play";
    private static final String SETTING = "setting" ;

    private  static MainPanel mainPanel;
    private static CardLayout cardLayout ;


    private  MainPanel(){

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        add(Login.loginPanel(),LOGIN);
        add(Menu.menuPanel(),MENU);
        add(Shop.shopPanel(),SHOP);
        add(Status.statusPanel(),STATUS);
        //add(Login.loginPanel(),SETTING);
        add(Collections.collectionsPanel(),COLLECTIONS);
        add(Play.playPanel(),PLAY);
    }

    public static void setPanel(String panelName){
        cardLayout.show(mainPanel,panelName);
    }


    public static MainPanel get() {
        if (mainPanel==null){
            mainPanel= new MainPanel();
            setPanel("login");
        }
        return mainPanel;
    }

    public enum PanelName {
        play,
        shop,
        status,
        collection,
        menu,
        login
    }

}
