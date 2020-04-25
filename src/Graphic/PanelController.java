package Graphic;

import Graphic.Panels.Login;
import javax.swing.*;
import java.awt.*;

public class PanelController extends JPanel {
    public PanelNames name;
    PanelController(PanelNames name) {
        name = name;

        setLayout(new BorderLayout());

        switch (name) {
            case play:
            case shop:
            case status:
            case collection:
            case menu:
            case login:
                JPanel s= new Login.loginPanel();
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
