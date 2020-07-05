package Graphic.Panels;

import Command.Contoller;
import Constants.Constants;
import Graphic.GamePanel;
import Graphic.MainPanel;
import Graphic.Tools.MyButton;
import Graphic.Tools.MyGridBagContraints;

import javax.swing.*;
import java.awt.*;

public class Setting extends GamePanel {
    private static Setting setting;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private JButton changeBackGround;
    private JButton changeCard;


    private JRadioButton backGround1;
    private JRadioButton backGround2;
    private JRadioButton card1;
    private JRadioButton card2;

    private ButtonGroup backGrounds;
    private ButtonGroup cards;

    private JButton backMenu;


    private Setting() {
        super();
        backgroundFileName = Constants.backgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    public static Setting settingPanel() {
        if (setting == null)
            setting = new Setting();
        return setting;
    }

    @Override
    protected void setButtons() {

        backGround1 = new JRadioButton("Background 1");
        backGround2 = new JRadioButton("Background 2");
        card1 = new JRadioButton("Card 1");
        card2 = new JRadioButton("Card 2");

        backGrounds = new ButtonGroup();
        cards = new ButtonGroup();
        backGrounds.add(backGround1);
        backGrounds.add(backGround2);
        cards.add(card1);
        cards.add(card2);

        backGround1.setActionCommand("background1");
        backGround2.setActionCommand("background2");
        card1.setActionCommand("card1");
        card2.setActionCommand("card2");

        backGround1.setContentAreaFilled(false);
        backGround1.setForeground(Color.WHITE);

        backGround2.setContentAreaFilled(false);
        backGround2.setForeground(Color.WHITE);

        card1.setContentAreaFilled(false);
        card1.setForeground(Color.WHITE);

        card2.setContentAreaFilled(false);
        card2.setForeground(Color.WHITE);

        card1.addActionListener(e -> {
            Constants.setCard(1);
        });

        card2.addActionListener(e -> {
            Constants.setCard(2);
        });

        backGround1.addActionListener(e -> {
                    Constants.setBackground(1);
                }
        );

        backGround2.addActionListener(e -> {
            Constants.setBackground(2);

        });

        try {
            backMenu = new MyButton("woodenBackMenu.png", e -> {
                MainPanel.setPanel("menu");
                Contoller.getPlayer().update();
            });
        } catch (Exception e) {
        }

    }

    @Override
    protected void setLabels() {

    }

    @Override
    protected void setGridBagConstraints() {
        MyGridBagContraints gridBagConstraints = new MyGridBagContraints(backgroundLabel);

        gridBagConstraints.add(backGround1, 210, 320, 0, 0);
        gridBagConstraints.add(backGround2, 210, 420, 0, 0);
        gridBagConstraints.add(card1, 260, 320, 0, 0);
        gridBagConstraints.add(card2, 260, 380, 0, 0);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.add(backMenu, 300, 0, 100, 20);
    }


}
