package Graphic.Panels;


import Command.Contoller;
import Constants.Constants;
import Graphic.ButtonController;
import Graphic.GamePanel;
import Graphic.MainPanel;
import Graphic.Tools.MyButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends GamePanel {
    public static final String loginBackgroundFileName = "door.jpg";
    private static Menu menu;
    private String backgroundFileName;
    private JButton shopButton;
    private JButton statusButton;
    private JButton collectionButton;
    private JButton settingButton;
    private JButton playButton;


    private Menu() {
        super();
        backgroundFileName = Constants.menuBackgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    public static Menu menuPanel() {
        if (menu == null)
            menu = new Menu();
        return menu;
    }

    @Override
    protected void setButtons() {
            //SHOP
            shopButton = new MyButton("woodenShop.png", e -> {
                ButtonController shop = new ButtonController(ButtonController.ButtonOptions.Shop);
                MainPanel.setPanel("shop");
            });

            //STATUS
            statusButton = new MyButton("woodenStatus.png", e -> {
                ButtonController status = new ButtonController(ButtonController.ButtonOptions.Status);
                MainPanel.setPanel("status");
            });

            //COLLECTIONS
            collectionButton = new MyButton("woodenCollections.png", e -> {
                ButtonController collection = new ButtonController(ButtonController.ButtonOptions.Collection);
                MainPanel.setPanel("collections");
            });

            //SETTING
            settingButton = new MyButton("woodenSetting.png", e -> {
                ButtonController setting = new ButtonController(ButtonController.ButtonOptions.Setting);
                MainPanel.setPanel("setting");
            });

            //PLAY
            playButton = new MyButton( "play.JPG", e -> {
                Contoller.setGamePlay();
                ButtonController play = new ButtonController(ButtonController.ButtonOptions.Play);
                MainPanel.setPanel("play");
            });

    }

    @Override
    protected void setLabels() {
        int gem;
        if (Contoller.getPlayer() != null)
            gem = Contoller.getPlayer().getGems();
    }

    @Override
    protected void setGridBagConstraints() {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(300, 0, 100, 20);

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        backgroundLabel.add(shopButton, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        backgroundLabel.add(statusButton, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        backgroundLabel.add(settingButton, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(collectionButton, gridBagConstraints);

        gridBagConstraints.insets = new Insets(0, 0, 0, 15);
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        backgroundLabel.add(playButton, gridBagConstraints);

        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }

}
