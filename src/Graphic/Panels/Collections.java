package Graphic.Panels;

import Cards.Cards;
import Command.Contoller;
import Constants.Constants;
import Graphic.GamePanel;
import Graphic.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Collections extends GamePanel {

    private static Collections collections;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private JButton backMenu;


    private JLabel cardShow;
    private JTextArea detail;
    private JTextArea wallet;

    private JRadioButton buyButton;
    private JRadioButton sellButton;
    private ButtonGroup trade;
    private JComboBox cardSelection;
    private JButton okButton;
    private JButton showButton;
    private Cards card;



    private Collections() {
        super();
        backgroundFileName = Constants.backgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    public static Collections collectionsPanel() {
        if (collections == null)
            collections = new Collections();
        return collections;
    }

    @Override
    protected void setButtons() {
        try {
            File backMenuFile = new File(buttonPath + "woodenBackMenu.png");
            BufferedImage backMenuBufferedImage = ImageIO.read(backMenuFile);
            backMenu = new JButton();
            backMenu.setIcon(new ImageIcon(backMenuBufferedImage));
            backMenu.setBackground(Color.BLACK);
            backMenu.setBorderPainted(false);
            backMenu.setContentAreaFilled(false);
            backMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setPanel("menu");
                    Contoller.getPlayer().update();
                }
            });

            //////////////////////////////////////////////



        }
        catch (Exception e){ }
    }

    @Override
    protected void setLabels() {

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
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(backMenu, gridBagConstraints);
    }
}
