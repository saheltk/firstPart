package Graphic;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;

    public void setPanel(PanelController.PanelNames panelName) {
        PanelController.changePanel(panelName);
    }

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

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
