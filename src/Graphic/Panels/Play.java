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
    private int mana;

    private JButton endTurn;
    private JButton backMenu;
    private JButton[] player1DeckCards;
    File[] cardFiles;
    BufferedImage[] cardImages;

    private JLabel player1Herohp;


    private JButton player1DeckButton;
    private JButton player2DeckButton;

    private int player1round = 0;

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
        setMana(0);
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

            player1Herohp = new JLabel();
            player1Herohp.setText(30 + "");
            player1Herohp.setForeground(Color.WHITE);
            if (Contoller.getPlayer() != null) {
                player1Herohp.setText(Contoller.getGamePlay().getFirstPlayer().getCurrentHero().getHP() + "");
                player1Herohp.setForeground(Color.WHITE);
            }
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
            File endTurnFile = new File(buttonPath + "battleground1.jpg");
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
                    setManaByTurn();
                    mana = Contoller.getGamePlay().getTurn();
                    if (Contoller.getPlayer() != null) {
                        player1Herohp.setText(Contoller.getGamePlay().getFirstPlayer().getCurrentHero().getHP() + "");
                        player1Herohp.setForeground(Color.WHITE);
                    }

                }
            });
            /////////////////////////////////////////////////////
            player1Mana = new JButton[Constants.mana];
            for (int i = 0; i < Constants.mana; i++) {
                player1Mana[i] = new JButton();
                File manaFile = new File(buttonPath + "mana_crystal.png");
                BufferedImage manaBufferedImage = ImageIO.read(manaFile);
                player1Mana[i].setContentAreaFilled(false);
                player1Mana[i].setIcon(new ImageIcon(manaBufferedImage));
                player1Mana[i].setBorderPainted(false);
            }

            /////////////////////////////////////////////////////
            player1Hand = new JComboBox();
            player1HandCard = new JButton();
            DefaultComboBoxModel player1HandCardsModel = new DefaultComboBoxModel();
            if (Contoller.getPlayer() != null)
                for (Cards cards : Contoller.getGamePlay().getFirstPlayerHand()) {
                    player1HandCardsModel.addElement(cards.getName());
                }
            player1Hand.setModel(player1HandCardsModel);
            player1Hand.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName;
                    if (player1Hand.getSelectedItem() == null) return;
                    cardName = player1Hand.getSelectedItem().toString();
                    Cards card = Cards.createCardByName(cardName);
                    File cardFile = new File("src\\Graphic\\Cards\\card" + (card.getId() + 1) + ".png");
                    BufferedImage cardBufferedImage = null;
                    try {
                        cardBufferedImage = ImageIO.read(cardFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    player1HandCard.setIcon(new ImageIcon(cardBufferedImage));
                    player1HandCard.setBorderPainted(false);
                    player1HandCard.setContentAreaFilled(false);
                    player1HandCard.setForeground(Color.DARK_GRAY);
                }
            });
            player1HandCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName = player1Hand.getSelectedItem().toString();
                    if (Contoller.getGamePlay().useCard(cardName)) {
                        mana = mana - Cards.createCardByName(cardName).getMana();
                        setPlayer1DeckCards();
                        setMana(mana);
                    }
                    player1HandCardsModel.removeAllElements();
                    for (Cards cards : Contoller.getGamePlay().getFirstPlayerHand()) {
                        player1HandCardsModel.addElement(cards.getName());
                    }
                    player1Hand.setModel(player1HandCardsModel);

                }
            });

            /////////////////////////////////////////////////////

            player1DeckButton = new JButton();
            File cardFile = new File(buttonPath + Constants.getPlayCardFileName());
            BufferedImage cardBufferedImage = ImageIO.read(cardFile);
            player1DeckButton.setIcon(new ImageIcon(cardBufferedImage));
            player1DeckButton.setContentAreaFilled(false);
            player1DeckButton.setBorderPainted(false);
            player1DeckButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player1round == 0) {
                        Cards card = Contoller.getGamePlay().pickCardFromDeck();
                        player1round++;
                        player1HandCardsModel.removeAllElements();
                        for (Cards cards : Contoller.getGamePlay().getFirstPlayerHand()) {
                            player1HandCardsModel.addElement(cards.getName());
                        }
                        player1Hand.setModel(player1HandCardsModel);
                        setManaByTurn();
                    }

                    if (Contoller.getPlayer() != null) {
                        player1Herohp.setText(Contoller.getGamePlay().getFirstPlayer().getCurrentHero().getHP() + "");
                        player1Herohp.setForeground(Color.WHITE);
                    }
                }
            });

            player1DeckCards = new JButton[5];
            for (int i = 0; i < 5; i++) {
                player1DeckCards[i] = new JButton();
                // File cardFile = new File(buttonPath + Constants.getPlayCardFileName());
                // BufferedImage cardBufferedImage = ImageIO.read(cardFile);
                // player1DeckButton.setIcon(new ImageIcon(cardBufferedImage));
                player1DeckCards[i].setContentAreaFilled(false);
                player1DeckCards[i].setBorderPainted(false);
            }
            cardFiles = new File[5];
            cardImages = new BufferedImage[5];


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

        for (int i = 0; i < 5; i++) {
            gridBagConstraints.insets = new Insets(300, 230 + (i * 110), 140, 20);
            gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
            backgroundLabel.add(player1DeckCards[i], gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(300, 0, 0, 50);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        backgroundLabel.add(endTurn, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        for (int i = 0; i < Constants.mana; i++) {
            gridBagConstraints.insets = new Insets(444 - (i * 14), 0, 0, 48);
            backgroundLabel.add(player1Mana[i], gridBagConstraints);

        }

        gridBagConstraints.insets = new Insets(450, 0, 0, 90);
        backgroundLabel.add(player1DeckButton, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(375, 80, 0, 0);
        backgroundLabel.add(player1Hand, gridBagConstraints);

        gridBagConstraints.insets = new Insets(405, 80, 0, 0);
        backgroundLabel.add(player1HandCard, gridBagConstraints);


        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(500, 0, 0, 0);
        backgroundLabel.add(player1Herohp, gridBagConstraints);


    }

    private void setManaByTurn() {
        setMana(Contoller.getGamePlay().getTurn());
    }

    private void setMana(int mana) {
        if (mana > 7) mana = 7;
        for (int i = 0; i < Constants.mana; i++) {
            player1Mana[i].setVisible(true);
        }
        for (int i = 0; i < Constants.mana - mana; i++) {
            player1Mana[i].setVisible(false);
        }
    }

    private void setPlayer1DeckCards() {
        int[] cardIDs = new int[5];
        int k = 0;
        try {
            for (int i = 0; i < 5; i++) {
                k = i;
                if (i < Contoller.getGamePlay().getFirstPlayerGround().size() &&
                        Contoller.getGamePlay().getFirstPlayerGround().get(i) != null)
                    cardIDs[i] = Contoller.getGamePlay().getFirstPlayerGround().get(i).getId();
                else {
                    player1DeckCards[k].setVisible(false);
                    break;
                }
            }

            for (int i = 0; i < Contoller.getGamePlay().getFirstPlayerGround().size(); i++) {
                cardFiles[i] = new File("src\\Graphic\\Cards\\card" + (cardIDs[i] + 1) + ".png");
                cardImages[i] = ImageIO.read(cardFiles[i]);
                player1DeckCards[i].setIcon(new ImageIcon(cardImages[i]));
                player1DeckCards[i].setVisible(true);
            }
        } catch (IOException e) {
            player1DeckCards[k].setVisible(false);
        }


    }

}
