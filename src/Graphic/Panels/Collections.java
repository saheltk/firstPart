package Graphic.Panels;

import Cards.Cards;
import Cards.Deck;
import Command.Contoller;
import Command.LoginCheck;
import Constants.Constants;
import Graphic.ButtonController;
import Graphic.GamePanel;
import Graphic.MainPanel;
import Heroes.Heroes;
import Heroes.HeroName;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Collections extends GamePanel {

    private static Collections collections;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private JButton backMenu;


    private boolean cardDeckSelection;
    private Deck chosenDeck;
    private JComboBox deckCardSelection;
    private JComboBox addCardJComboBox;

    private JButton addCard;
    private JButton removeCard;
    private JButton delete;
    private JButton chooseDeck;
    private JButton editName;
    private JTextField editNameField;


    private JButton cardShow;
    private JTextArea detail;

    private JRadioButton allCards;
    private JRadioButton myCards;
    private JRadioButton notMyCards;
    private JRadioButton myDecks;
    private ButtonGroup cards;
    private JTextArea deckDetail;


    private JComboBox cardSelection;
    private JComboBox cardRemoveSelection;

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
            deckCardSelection = new JComboBox();
            addCardJComboBox = new JComboBox();

            addCard = new JButton();
            removeCard = new JButton();
            delete = new JButton();
            chooseDeck = new JButton();
            editName = new JButton();
            editNameField = new JTextField(10);


            allCards = new JRadioButton("All Cards");
            allCards.setContentAreaFilled(false);
            allCards.setForeground(Color.WHITE);

            myCards = new JRadioButton("My Cards");
            myCards.setContentAreaFilled(false);
            myCards.setForeground(Color.WHITE);

            notMyCards = new JRadioButton("Not My Cards");
            notMyCards.setContentAreaFilled(false);
            notMyCards.setForeground(Color.WHITE);

            myDecks = new JRadioButton("My Decks");
            myDecks.setContentAreaFilled(false);
            myDecks.setForeground(Color.WHITE);

            deckDetail = new JTextArea();

            cards = new ButtonGroup();
            cards.add(allCards);
            cards.add(myCards);
            cards.add(notMyCards);
            cards.add(myDecks);
            allCards.setSelected(true);

            allCards.setActionCommand("all");
            myCards.setActionCommand("my");
            notMyCards.setActionCommand("not");
            myDecks.setActionCommand("decks");

            cardSelection = new JComboBox();
            cardRemoveSelection = new JComboBox();
            cardShow = new JButton();
            cardShow.setBorderPainted(false);
            cardShow.setContentAreaFilled(false);
            detail = new JTextArea();


            DefaultComboBoxModel allModel = new DefaultComboBoxModel();
            DefaultComboBoxModel myModel = new DefaultComboBoxModel();
            DefaultComboBoxModel notModel = new DefaultComboBoxModel();
            DefaultComboBoxModel deckModel = new DefaultComboBoxModel();

            cardSelection.setModel(allModel);


            for (int i = 0; i < Constants.cardNumbers; i++) {
                allModel.addElement(Cards.cards[i].getName());
            }

            if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                    myModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                }
            if (Contoller.getPlayer() != null && Contoller.getPlayer().despiteMyCards().size() != 0)
                for (int i = 0; i < Contoller.getPlayer().despiteMyCards().size(); i++) {
                    notModel.addElement(Contoller.getPlayer().despiteMyCards().get(i).getName());
                }
            if (Contoller.getPlayer() != null && Contoller.getPlayer().getDeck().size() != 0)
                for (int i = 0; i < Contoller.getPlayer().getDeck().size(); i++) {
                    deckModel.addElement(Contoller.getPlayer().getDeck().get(i).getName());
                }
            deckModel.addElement("New");

            allCards.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardSelection.setModel(allModel);
                    decks(false);
                    ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.Choose_All_Cards);


                }

            });
            myCards.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (myModel != null)
                        myModel.removeAllElements();
                    if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                        for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                            myModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                        }
                    cardSelection.setModel(myModel);
                    decks(false);
                    ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.Choose_My_Cards);
                }
            });
            notMyCards.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (notModel != null)
                        notModel.removeAllElements();
                    if (Contoller.getPlayer() != null && Contoller.getPlayer().despiteMyCards().size() != 0)
                        for (int i = 0; i < Contoller.getPlayer().despiteMyCards().size(); i++) {
                            notModel.addElement(Contoller.getPlayer().despiteMyCards().get(i).getName());
                        }
                    for (Heroes hero : Contoller.getPlayer().getAvailableHeroes())
                        deckModel.addElement("New " + hero.getName());
                    cardSelection.setModel(notModel);
                    decks(false);
                    ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.ChooseNot_My_Cards);

                }
            });
            myDecks.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deckModel != null)
                        deckModel.removeAllElements();
                    if (Contoller.getPlayer() != null && Contoller.getPlayer().getDeck().size() != 0)
                        for (int i = 0; i < Contoller.getPlayer().getDeck().size(); i++) {
                            deckModel.addElement(Contoller.getPlayer().getDeck().get(i).getName());
                        }

                    for (Heroes hero : Contoller.getPlayer().getAvailableHeroes()) {
                        deckModel.addElement("New " + hero.getName());
                    }

                    cardSelection.setModel(deckModel);
                    decks(true);
                    ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.Choose_My_Decks);

                }
            });


            //show
            File showFile = new File(buttonPath + "woodenShow.png");
            BufferedImage showBufferedImage = ImageIO.read(showFile);
            showButton = new JButton();
            showButton.setIcon(new ImageIcon(showBufferedImage));
            showButton.setBackground(Color.BLACK);
            showButton.setBorderPainted(false);
            showButton.setContentAreaFilled(false);
            showButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName;
                    if (!cardDeckSelection) {
                        cardName = cardSelection.getSelectedItem().toString();
                    } else {
                        cardName = deckCardSelection.getSelectedItem().toString();
                    }
                    card = Cards.createCardByName(cardName);


                    File shopFile = new File("src\\Graphic\\Cards\\card" + (card.getId() + 1) + ".png");
                    BufferedImage shopBufferedImage = null;
                    try {
                        shopBufferedImage = ImageIO.read(shopFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    cardShow.setIcon(new ImageIcon(shopBufferedImage));
                    cardShow.setBorderPainted(false);
                    cardShow.setContentAreaFilled(false);
                    cardShow.setForeground(Color.DARK_GRAY);

                    detail.setText("Name: " + card.getName() + "\n" + "Type: " + card.getType() + "\n" +
                            "Class: :" + card.getCardClass() + "\n" + "Rarity: " + card.getRarity() + "\n" +
                            "Mana: " + card.getMana() + "\n" + "Price: " + card.getCost());
                }
            });



            cardShow.setForeground(Color.DARK_GRAY);
            cardShow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Contoller.getPlayer().getGems() < card.getCost())
                        return;
                    int check = 0;
                    for (Cards cards : Contoller.getPlayer().getMyCards()) {
                        if (cards.getName().equals(card.getName()))
                            check++;
                    }
                    if (check >= 2) return;
                    MainPanel.setPanel("shop");
                    Contoller.getPlayer().update();
                    ButtonController showCard = new ButtonController(ButtonController.ButtonOptions.Show_Card, card.getName());
                }
            });





            File removeFile = new File(buttonPath + "removeCard.png");
            BufferedImage removeBufferedImage = ImageIO.read(removeFile);
            removeCard = new JButton();
            removeCard.setIcon(new ImageIcon(removeBufferedImage));
            removeCard.setBackground(Color.BLACK);
            removeCard.setBorderPainted(false);
            removeCard.setContentAreaFilled(false);


            File addFile = new File(buttonPath + "addCard.png");
            BufferedImage addBufferedImage = ImageIO.read(addFile);
            addCard = new JButton();
            addCard.setIcon(new ImageIcon(addBufferedImage));
            addCard.setBackground(Color.BLACK);
            addCard.setBorderPainted(false);
            addCard.setContentAreaFilled(false);

            File deleteFile = new File(buttonPath + "delete.png");
            BufferedImage deleteBufferedImage = ImageIO.read(deleteFile);
            delete.setIcon(new ImageIcon(deleteBufferedImage));
            delete.setBackground(Color.BLACK);
            delete.setBorderPainted(false);
            delete.setContentAreaFilled(false);


            File chooseDeckFile = new File(buttonPath + "chooseDeck.png");
            BufferedImage chooseDeckBufferedImage = ImageIO.read(chooseDeckFile);
            chooseDeck.setIcon(new ImageIcon(chooseDeckBufferedImage));
            chooseDeck.setBackground(Color.BLACK);
            chooseDeck.setBorderPainted(false);
            chooseDeck.setContentAreaFilled(false);


            File editNameFile = new File(buttonPath + "editName.png");
            BufferedImage editNameBufferedImage = ImageIO.read(editNameFile);
            editName.setIcon(new ImageIcon(editNameBufferedImage));
            editName.setBackground(Color.BLACK);
            editName.setBorderPainted(false);
            editName.setContentAreaFilled(false);


            addCard.setVisible(false);
            removeCard.setVisible(false);
            delete.setVisible(false);
            chooseDeck.setVisible(false);
            editName.setVisible(false);
            editNameField.setVisible(false);
            deckCardSelection.setVisible(false);
            addCardJComboBox.setVisible(false);
            cardRemoveSelection.setVisible(false);
            deckDetail.setVisible(false);


        } catch (Exception e) {
        }
    }

    protected void decks(boolean show) {
        if (!show) {
            addCard.setVisible(false);
            removeCard.setVisible(false);
            delete.setVisible(false);
            chooseDeck.setVisible(false);
            editName.setVisible(false);
            editNameField.setVisible(false);
            deckCardSelection.setVisible(false);
            addCardJComboBox.setVisible(false);
            cardRemoveSelection.setVisible(false);
            deckDetail.setVisible(false);

            return;
        }
        addCard.setVisible(true);
        removeCard.setVisible(true);
        delete.setVisible(true);
        chooseDeck.setVisible(true);
        editName.setVisible(true);
        editNameField.setVisible(true);
        deckCardSelection.setVisible(true);
        addCardJComboBox.setVisible(true);
        cardRemoveSelection.setVisible(true);
        deckDetail.setVisible(true);
        DefaultComboBoxModel cardsForDeck = new DefaultComboBoxModel();


        if (cardSelection.getSelectedItem() != null) {
            String deckName;
            String chosen = cardSelection.getSelectedItem().toString();
            if (!chosen.substring(0, 3).equals("New")) {
                ArrayList<Deck> decks = Contoller.getPlayer().getDeck();
                deckName = cardSelection.getSelectedItem().toString();
                for (Deck deck : decks) {
                    if (deck.getName().equals(deckName)) {
                        chosenDeck = deck;
                        break;
                    }
                }
            } else {
                HeroName heroName = HeroName.valueOf(chosen.substring(4));
                ArrayList<Cards> newCards = new ArrayList<Cards>();
                Deck newDeck = new Deck(" New Deck", new Heroes(heroName), newCards);
                ButtonController newDeckbc = new ButtonController(ButtonController.ButtonOptions.newDeck, chosenDeck.getName());
                Contoller.getPlayer().addDeck(newDeck);
                chosenDeck = newDeck;
                Contoller.getPlayer().update();
            }


            if (chosenDeck.getAllCards() != null)
                for (Cards card : chosenDeck.getAllCards()) {
                    cardsForDeck.addElement(card.getName());
                }
            deckCardSelection.setModel(cardsForDeck);

            deckDetail.setText("Name: " + chosenDeck.getName() + "\n" + "Hero: " + chosenDeck.getHero().getName() + "\n" +
                    "Wins: :" + chosenDeck.getWins() + "\n" + "winsToTotal: " + chosenDeck.getWinsToTotal() + "\n" +
                    "Games: " + chosenDeck.getGames() + "\n" + "Price: " + chosenDeck.getCost() +
                    "\nMost Common Card: " + chosenDeck.getMostCommon());

            DefaultComboBoxModel addableCards = new DefaultComboBoxModel();
            for (Cards card : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                addableCards.addElement(card.getName());
            }
            addCardJComboBox.setModel(addableCards);

            addCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String cardName = cardSelection.getSelectedItem().toString();
                        card = Cards.createCardByName(cardName);
                        if (LoginCheck.addCard(cardName, chosenDeck)) {
                            if (card != null) {
                                ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.add_Card_To_Deck, card.getName());
                            }

                        } else if (!LoginCheck.addCard(cardName, chosenDeck)) {
                            JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't add this card", "DECK MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }

                        DefaultComboBoxModel addableCards = new DefaultComboBoxModel();
                        for (Cards card : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                            addableCards.addElement(card.getName());
                        }
                        addCardJComboBox.setModel(addableCards);
                    } catch (Exception e1) {
                    }
                }
            });


            removeCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String cardName = cardRemoveSelection.getSelectedItem().toString();
                        card = Cards.createCardByName(cardName);
                        if (LoginCheck.removeCard(cardName, chosenDeck)) {
                            if (card != null) {
                                ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.remove_Card_FromDeck, card.getName());
                            }

                        } else if (!LoginCheck.removeCard(cardName, chosenDeck)) {
                            JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't remove this card", "DECK MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }

                        DefaultComboBoxModel addableCards = new DefaultComboBoxModel();
                        for (Cards card : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                            addableCards.addElement(card.getName());
                        }
                        addCardJComboBox.setModel(addableCards);
                    } catch (Exception e2) {
                    }
                }
            });

            chooseDeck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (chosenDeck != null) {
                        Contoller.getPlayer().setChosenDeck(chosenDeck);
                        Contoller.getPlayer().update();
                        ButtonController choosedeckbc = new ButtonController(ButtonController.ButtonOptions.Choose_Deck, chosenDeck.getName());
                    }
                }
            });

            editName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = editNameField.getText();
                    if (chosenDeck != null) {
                        String lastName = chosenDeck.getName();
                        chosenDeck.setName(name);
                        Contoller.getPlayer().update();
                        ButtonController editNamebc = new ButtonController(ButtonController.ButtonOptions.edit_Deck_Name, lastName + " to " + name);
                    }
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (chosenDeck != null) {
                        Contoller.getPlayer().deleteDeck(chosenDeck);
                        Contoller.getPlayer().update();
                        ButtonController editNamebc = new ButtonController(ButtonController.ButtonOptions.delete_Deck, chosenDeck.getName());
                    }
                }
            });

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
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

        gridBagConstraints.insets = new Insets(210 - 60, 320, 0, 0);
        backgroundLabel.add(allCards, gridBagConstraints);

        gridBagConstraints.insets = new Insets(230 - 60, 320, 0, 0);
        backgroundLabel.add(myCards, gridBagConstraints);

        gridBagConstraints.insets = new Insets(250 - 60, 320, 0, 0);
        backgroundLabel.add(notMyCards, gridBagConstraints);

        gridBagConstraints.insets = new Insets(270 - 60, 320, 0, 0);
        backgroundLabel.add(myDecks, gridBagConstraints);

        gridBagConstraints.insets = new Insets(300 - 60, 320, 0, 0);
        backgroundLabel.add(cardSelection, gridBagConstraints);

        gridBagConstraints.insets = new Insets(330 - 60, 320, 0, 0);
        backgroundLabel.add(deckCardSelection, gridBagConstraints);

        gridBagConstraints.insets = new Insets(360 - 60, 320, 0, 0);
        backgroundLabel.add(showButton, gridBagConstraints);

        gridBagConstraints.insets = new Insets(415 - 60, 320, 0, 0);
        backgroundLabel.add(addCard, gridBagConstraints);

        gridBagConstraints.insets = new Insets(425 - 60, 200, 0, 0);
        backgroundLabel.add(addCardJComboBox, gridBagConstraints);

        gridBagConstraints.insets = new Insets(470 - 60, 320, 0, 0);
        backgroundLabel.add(removeCard, gridBagConstraints);

        gridBagConstraints.insets = new Insets(525 - 60, 320, 0, 0);
        backgroundLabel.add(delete, gridBagConstraints);

        gridBagConstraints.insets = new Insets(580 - 60, 320, 0, 0);
        backgroundLabel.add(chooseDeck, gridBagConstraints);

        gridBagConstraints.insets = new Insets(575, 320, 0, 0);
        backgroundLabel.add(editName, gridBagConstraints);

        gridBagConstraints.insets = new Insets(585, 200, 0, 0);
        backgroundLabel.add(editNameField, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 500, 0, 0);
        backgroundLabel.add(cardShow, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 750, 0, 0);
        backgroundLabel.add(detail, gridBagConstraints);

        gridBagConstraints.insets = new Insets(210, 100, 0, 0);
        backgroundLabel.add(deckDetail, gridBagConstraints);


        gridBagConstraints.insets = new Insets(300, 0, 100, 20);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(backMenu, gridBagConstraints);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }
}
