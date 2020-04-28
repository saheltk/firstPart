package Graphic;

import Cards.Cards;
import Constants.Constants;
import Log.Logs;
import Player.Player;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;


    public MainFrame() {
        super("HEARTHSTONE");
        mainPanel= MainPanel.get();
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.width,Constants.height);
        setMaximumSize(new Dimension(Constants.width,Constants.height));
        setVisible(true);
        validate();
    }


}
