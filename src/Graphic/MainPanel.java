package Graphic;

import Constants.Constants;
import Graphic.Panels.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class MainPanel extends JPanel {
    private static MainPanel mainPanel;
    Dimension dimension;
    GridBagConstraints gridBagConstraints;
    protected JLabel backgroundLabel;

    protected MainPanel() {

    }

    protected void setButtons() {
    }

    public static MainPanel get() {
        if (mainPanel == null) mainPanel = new Login();
        return mainPanel;
    }

    protected void setBackGround(String filename) {
        String path = "src\\Graphic\\Backgrounds\\"+filename;
        try {
            //Image
            File backgroundFile = new File(path);
            BufferedImage backgroundBufferedImage= ImageIO.read(backgroundFile);
            //add Image
            backgroundLabel = new JLabel();
            backgroundLabel.setIcon(new ImageIcon(backgroundBufferedImage));
            backgroundLabel.setBounds(0,0, Constants.width,Constants.height);

            backgroundLabel.setVisible(true);
            backgroundLabel.setLayout(new GridBagLayout());
            backgroundLabel.setBackground(Color.BLACK);

            validate();
            add(backgroundLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
