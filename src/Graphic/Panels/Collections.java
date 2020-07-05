package Graphic.Panels;


import Cards.Cards;
import Cards.Deck;
import Command.Contoller;
import Constants.Constants;
import Graphic.ButtonController;
import Graphic.GamePanel;
import Graphic.MainPanel;
import Graphic.Tools.MyButton;
import Graphic.Tools.MyGridBagContraints;
import Graphic.Tools.MyJRadioButton;
import Graphic.Tools.Tools;
import Heroes.Heroes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Collections extends GamePanel {

    private static Collections collections;
    private String backgroundFileName;

    private JButton backMenu;
    private JTextArea lock;

    private boolean cardDeckSelection;
    private Deck chosenDeck;
    private JRadioButton chosenDeckRadioButton;
    private JComboBox deckCardSelection;
    private JComboBox choosenDeckCardSelection;
    private JComboBox addCardJComboBox;

    private JButton addCard;
    private JButton removeCard;
    private JButton delete;
    private JButton chooseDeck;
    private JButton editName;
    private JTextField editNameField;

    private MyButton cardShow;
    private JTextArea detail;

    private JRadioButton allCards;
    private JRadioButton myCards;
    private JRadioButton notMyCards;
    private JRadioButton myDecks;
    private JRadioButton filterByMana;
    private JRadioButton search;
    private JTextField searchBox;
    private ArrayList<JComponent> list;

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
            backMenu = new MyButton("woodenBackMenu.png", e -> {
                MainPanel.setPanel("menu");
                Contoller.getPlayer().update();
            });

            //////////////////////////////////////////////
            deckCardSelection = new JComboBox();
            choosenDeckCardSelection = new JComboBox();
            addCardJComboBox = new JComboBox();
            chosenDeckRadioButton = new MyJRadioButton("Choose this deck", false, Color.WHITE);


            DefaultComboBoxModel allModel = new DefaultComboBoxModel();
            DefaultComboBoxModel myModel = new DefaultComboBoxModel();
            DefaultComboBoxModel notModel = new DefaultComboBoxModel();
            DefaultComboBoxModel deckModel = new DefaultComboBoxModel();
            DefaultComboBoxModel filterModel = new DefaultComboBoxModel();
            DefaultComboBoxModel searchModel = new DefaultComboBoxModel();


            allCards = new MyJRadioButton("All Cards", false, Color.WHITE, e -> {
                cardSelection.setModel(allModel);
                decks(false);
                ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.Choose_All_Cards);
            });
            myCards = new MyJRadioButton("My Cards", false, Color.WHITE, e -> {
                if (myModel != null)
                    myModel.removeAllElements();
                if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                    for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                        myModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                    }
                cardSelection.setModel(myModel);
                decks(false);
                ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.Choose_My_Cards);
            });
            notMyCards = new MyJRadioButton("Not My Cards", false, Color.WHITE, e -> {
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

            });
            myDecks = new MyJRadioButton("My Decks", false, Color.WHITE, e -> {
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

            });
            filterByMana = new MyJRadioButton("Filter by mana(sorted)", false, Color.WHITE, e -> {
                if (filterModel != null)
                    filterModel.removeAllElements();
                if (Contoller.getPlayer() != null && Contoller.getPlayer().despiteMyCards().size() != 0)
                    for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < Constants.cardNumbers; j++) {
                            if (Cards.cards[j].getMana() == i)
                                filterModel.addElement(Cards.cards[j].getName());
                        }
                    }
                cardSelection.setModel(filterModel);
                decks(false);
                ButtonController filterByManabc = new ButtonController(ButtonController.ButtonOptions.Filter_by_mana);
            });
            search = new MyJRadioButton("Search", false, Color.WHITE, e -> {
                String searchedWord = searchBox.getText();
                System.out.println(searchedWord);
                if (searchModel != null)
                    searchModel.removeAllElements();
                if (Contoller.getPlayer() != null && Contoller.getPlayer().despiteMyCards().size() != 0)

                    for (int j = 0; j < Constants.cardNumbers; j++) {
                        if (Cards.cards[j].getName().contains(searchedWord)) {
                            searchModel.addElement(Cards.cards[j].getName());
                        }
                    }

                cardSelection.setModel(searchModel);
                decks(false);
                ButtonController searchbc = new ButtonController(ButtonController.ButtonOptions.Searched_for, searchedWord);
            });


            searchBox = new JTextField(10);

            cards = new ButtonGroup();
            cards.add(allCards);
            cards.add(myCards);
            cards.add(notMyCards);
            cards.add(myDecks);
            cards.add(filterByMana);
            cards.add(search);
            allCards.setSelected(true);

            allCards.setActionCommand("all");
            myCards.setActionCommand("my");
            notMyCards.setActionCommand("not");
            myDecks.setActionCommand("decks");
            filterByMana.setActionCommand("filter");
            search.setActionCommand("search");

            deckDetail = new JTextArea();

            cardSelection = new JComboBox();
            cardRemoveSelection = new JComboBox();
            cardShow = new MyButton();
            detail = new JTextArea();


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

            lock = new JTextArea();
            lock.setText("LOCK");
            lock.setForeground(Color.red);

            //show

            showButton = new MyButton("woodenShow.png", e -> {
                String cardName;
                if (!cardDeckSelection) {
                    cardName = cardSelection.getSelectedItem().toString();
                } else {
                    cardName = deckCardSelection.getSelectedItem().toString();
                }
                card = Cards.createCardByName(cardName);

                System.out.println(cardDeckSelection);

                cardShow.setPhoto("src\\Graphic\\Cards\\card" + (card.getId() + 1) + ".png");
                cardShow.setVisible(true);
                boolean isLock = true;
                for (Cards cards : Contoller.getPlayer().getMyCards())
                    if (cards.getName().equals(card.getName())) isLock = false;

                if (isLock)
                    cardShow.setVisible(true);
                else
                    cardShow.setVisible(false);

                detail.setText("Name: " + card.getName() + "\n" + "Type: " + card.getType() + "\n" +
                        "Class: :" + card.getCardClass() + "\n" + "Rarity: " + card.getRarity() + "\n" +
                        "Mana: " + card.getMana() + "\n" + "Price: " + card.getCost());
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


            removeCard = new MyButton("removeCard.png");
            addCard = new MyButton("addCard.png");
            delete = new MyButton("delete.png");
            chooseDeck = new MyButton("delete.png");
            editName = new MyButton("editName.png");
            editNameField = new JTextField(10);


            list = new ArrayList<>();
            list.add(addCard);
            list.add(removeCard);
            list.add(delete);
            list.add(chooseDeck);
            list.add(editName);
            list.add(editNameField);
            list.add(deckCardSelection);
            list.add(addCardJComboBox);
            list.add(chosenDeckRadioButton);
            list.add(cardRemoveSelection);
            list.add(deckDetail);
            list.add(choosenDeckCardSelection);
            Tools.setVisible(false, list);


        } catch (Exception e) {
        }
    }

    protected void decks(boolean show) {
        Tools.decks(show, list, cardSelection, deckCardSelection, deckDetail, addCardJComboBox,
                removeCard, addCard, chooseDeck, editName, delete,
                choosenDeckCardSelection, chosenDeckRadioButton, editNameField,
                cardRemoveSelection);

    }

    @Override
    protected void setLabels() {

    }

    @Override
    protected void setGridBagConstraints() {
        MyGridBagContraints gridBagConstraints = new MyGridBagContraints(backgroundLabel);

        gridBagConstraints.add(allCards, 170 - 60, 320, 0, 0);
        gridBagConstraints.add(myCards, 190 - 60, 320, 0, 0);
        gridBagConstraints.add(notMyCards, 210 - 60, 320, 0, 0);
        gridBagConstraints.add(myDecks, 230 - 60, 320, 0, 0);
        gridBagConstraints.add(filterByMana, 250 - 60, 320, 0, 0);
        gridBagConstraints.add(search, 275 - 60, 320, 0, 0);
        gridBagConstraints.add(searchBox, 275 - 60, 200, 0, 0);
        gridBagConstraints.add(cardSelection, 300 - 60, 320, 0, 0);
        gridBagConstraints.add(chosenDeckRadioButton, 300 - 60, 430, 0, 0);
        gridBagConstraints.add(deckCardSelection, 330 - 60, 320, 0, 0);
        gridBagConstraints.add(showButton, 360 - 60, 320, 0, 0);
        gridBagConstraints.add(addCard, 415 - 60, 320, 0, 0);
        gridBagConstraints.add(addCardJComboBox, 425 - 60, 200, 0, 0);
        gridBagConstraints.add(removeCard, 470 - 60, 320, 0, 0);
        gridBagConstraints.add(choosenDeckCardSelection, 475 - 60, 200, 0, 0);
        gridBagConstraints.add(delete, 525 - 60, 320, 0, 0);
        gridBagConstraints.add(chooseDeck, 580 - 60, 320, 0, 0);
        gridBagConstraints.add(editName, 575, 320, 0, 0);
        gridBagConstraints.add(editNameField, 585, 200, 0, 0);
        gridBagConstraints.add(cardShow, 210, 500, 0, 0);
        gridBagConstraints.add(lock, 500, 600, 0, 0);
        gridBagConstraints.add(detail, 210, 750, 0, 0);
        gridBagConstraints.add(deckDetail, 240, 10, 0, 0);

        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.add(backMenu, 300, 0, 100, 20);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }
}
