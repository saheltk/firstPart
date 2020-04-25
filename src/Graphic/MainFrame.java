package Graphic;

import javax.swing.*;

public class MainFrame extends JFrame {
    private PanelController panelController;

    public void setPanel(Panels.PanelNames panelName) {
        if (panel!=null)
        remove(panel);
        this.panel = new Panels(panelName);
        add(panel );
    }

    public MainFrame() {
        super("HEARTHSTONE");
        setPanel(Panels.PanelNames.login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,800);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
