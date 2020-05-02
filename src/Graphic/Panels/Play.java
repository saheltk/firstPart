package Graphic.Panels;

import Cards.Cards;
import Command.Contoller;
import Command.GamePlay;
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

public class Play extends GamePanel {
    private static Play play;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private JButton endTurn;
    private JButton backMenu;

    private JButton player1Deck;
    private JButton player2Deck;

    private int player1round=0;

    private JComboBox player1Hand;
    private JComboBox player2Hand;


    private JButton player1HandCard;
    private JButton player2HandCard;

    private JButton[] player1Mana;


    private Play() {
        super();
        backgroundFileName = Constants.getPlayBackgroundFileName();
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }


    public static Play playPanel() {
        if (play == null)
            play = new Play();
        return play;
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
            /////////////////////////////////////////
            File endTurnFile = new File(buttonPath + Constants.getPlayCardFileName());
            BufferedImage endTurnBufferedImage = ImageIO.read(endTurnFile);
            endTurn = new JButton();
            endTurn.setIcon(new ImageIcon(endTurnBufferedImage));
            endTurn.setBackground(Color.BLACK);
            endTurn.setBorderPainted(false);
            endTurn.setContentAreaFilled(false);
            endTurn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Contoller.getGamePlay().nextTurn();
                    ButtonController endTurnbc = new ButtonController(ButtonController.ButtonOptions.End_Turn);
                    Contoller.getPlayer().update();
                    player1round=0;
                }
            });

            player1Mana = new JButton[Constants.mana];
            for (int i = 0; i < Constants.mana; i++) {
                player1Mana[i] = new JButton();
                File manaFile = new File(buttonPath + "mana_crystal.png");
                BufferedImage manaBufferedImage = ImageIO.read(manaFile);
                player1Mana[i].setContentAreaFilled(false);
                player1Mana[i].setIcon(new ImageIcon(manaBufferedImage));
                player1Mana[i].setBorderPainted(false);
            }

            player1Deck = new JButton();
            File cardFile = new File(buttonPath + Constants.getPlayCardFileName());
            BufferedImage cardBufferedImage = ImageIO.read(cardFile);
            player1Deck.setIcon(new ImageIcon(cardBufferedImage));
            player1Deck.setContentAreaFilled(false);
            player1Deck.setBorderPainted(false);
            player1Deck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player1round==0){
                    Cards card = Contoller.getGamePlay().pickCardFromDeck();
                    player1round++;
                    }
                }
            });


            player1Hand = new JComboBox();
            player1Deck = new JButton();
            DefaultComboBoxModel player1HandCardsModel = new DefaultComboBoxModel();

            player1HandCardsModel.removeAllElements();
            for (Cards cards:Contoller.getGamePlay().getFirstPlayerHand()){
                player1HandCardsModel.addElement(cards.getName());
            }
            player1Hand.setModel(player1HandCardsModel);


            player1Hand.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName;
                    cardName = player1Hand.getSelectedItem().toString();
                    Cards card = Cards.createCardByName(cardName);
                    File shopFile = new File("src\\Graphic\\Cards\\card" + (card.getId() + 1) + ".png");
                    BufferedImage shopBufferedImage = null;
                    try {
                        shopBufferedImage = ImageIO.read(shopFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    player1HandCard.setIcon(new ImageIcon(shopBufferedImage));
                    player1HandCard.setBorderPainted(false);
                    player1HandCard.setContentAreaFilled(false);
                    player1HandCard.setForeground(Color.DARK_GRAY);
                }
            });

            player1HandCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Contoller.getGamePlay().useCard(player1Hand.getSelectedItem().toString());
                    player1HandCardsModel.removeAllElements();
                    for (Cards cards:Contoller.getGamePlay().getFirstPlayerHand()){
                        player1HandCardsModel.addElement(cards.getName());
                    }
                    player1Hand.setModel(player1HandCardsModel);

                }
            });



        } catch (Exception e) {
        }
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


        gridBagConstraints.insets = new Insets(275, 0, 100, 20);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        backgroundLabel.add(backMenu, gridBagConstraints);


        gridBagConstraints.insets = new Insets(300, 0, 0, 50);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        backgroundLabel.add(endTurn, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        for (int i = 0; i < Constants.mana; i++) {
            gridBagConstraints.insets = new Insets(444 - (i * 14), 0, 0, 48);
            backgroundLabel.add(player1Mana[i], gridBagConstraints);
        }

        gridBagConstraints.insets = new Insets(700, 0, 0, 10);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        backgroundLabel.add(player1Deck, gridBagConstraints);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }


    private void setMana(int mana) {
        for (int i = 0; i < Constants.mana; i++) {
            player1Mana[i].setVisible(true);
        }
        for (int i = 0; i < mana; i++) {
            player1Mana[i].setVisible(false);
        }
    }

}
