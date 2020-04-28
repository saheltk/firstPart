package Graphic.Panels;


import Command.Contoller;
import Command.LoginCheck;
import Constants.Constants;
import Graphic.ButtonController;
import Graphic.GamePanel;
import Graphic.MainPanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends GamePanel {
    private static Menu menu;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private JButton shopButton;
    private JButton statusButton;
    private JButton collectionButton;
    private JButton settingButton;


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
        try {
            //SHOP
            File shopFile = new File(buttonPath + "woodenShop.png");
            BufferedImage shopBufferedImage = ImageIO.read(shopFile);
            shopButton = new JButton();
            shopButton.setIcon(new ImageIcon(shopBufferedImage));
            shopButton.setBackground(Color.BLACK);
            shopButton.setBorderPainted(false);
            shopButton.setContentAreaFilled(false);
            shopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                            ButtonController shop= new ButtonController(ButtonController.ButtonOptions.Shop);
                            MainPanel.setPanel("shop");
                    }
                });


            //STATUS
            File statusFile = new File(buttonPath + "woodenStatus.png");
            BufferedImage statusBufferedImage = ImageIO.read(statusFile);
            statusButton = new JButton();
            statusButton.setIcon(new ImageIcon(statusBufferedImage));
            statusButton.setBackground(Color.BLACK);
            statusButton.setBorderPainted(false);
            statusButton.setContentAreaFilled(false);
            statusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ButtonController status = new ButtonController(ButtonController.ButtonOptions.Status);
                    MainPanel.setPanel("status");
                }
            });


            //COLLECTIONS
            File collectionsFile = new File(buttonPath + "woodenCollections.png");
            BufferedImage collectionsBufferedImage = ImageIO.read(collectionsFile);
            collectionButton = new JButton();
            collectionButton.setIcon(new ImageIcon(collectionsBufferedImage));
            collectionButton.setBackground(Color.BLACK);
            collectionButton.setBorderPainted(false);
            collectionButton.setContentAreaFilled(false);
            collectionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ButtonController collection= new ButtonController(ButtonController.ButtonOptions.Collection);
                    MainPanel.setPanel("collections");
                }
            });


            //SETTING
            File settingFile = new File(buttonPath + "woodenSetting.png");
            BufferedImage settingBufferedImage = ImageIO.read(settingFile);
            settingButton = new JButton();
            settingButton.setIcon(new ImageIcon(settingBufferedImage));
            settingButton.setBackground(Color.BLACK);
            settingButton.setBorderPainted(false);
            settingButton.setContentAreaFilled(false);
            settingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ButtonController setting = new ButtonController(ButtonController.ButtonOptions.Setting);
                    MainPanel.setPanel("setting");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void setLabels() {
        int gem;
        if (Contoller.getPlayer()!=null)
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

        gridBagConstraints.anchor=GridBagConstraints.FIRST_LINE_START;
        backgroundLabel.add(shopButton,gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.FIRST_LINE_END;
        backgroundLabel.add(statusButton,gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_START;
        backgroundLabel.add(settingButton,gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(collectionButton,gridBagConstraints);

        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }

    public static final String loginBackgroundFileName="door.jpg";

}
