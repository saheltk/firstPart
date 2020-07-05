package Graphic.Tools;

import Cards.Cards;
import Cards.Deck;
import Command.Contoller;
import Command.Check;
import Graphic.ButtonController;
import Graphic.Panels.Shop;
import Heroes.HeroName;
import Heroes.Heroes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tools {
    private static boolean cardDeckSelection;
    private static Deck chosenDeck;


    public static void setVisible(boolean show, ArrayList<JComponent> list) {
        for (JComponent jComponent : list) {
            jComponent.setVisible(show);
        }
    }

    protected static void setText(Deck chosenDeck, JTextArea deckDetail, String commonName) {
        deckDetail.setText("Name: " + chosenDeck.getName() + "\n" + "Hero: " + chosenDeck.getHero().getName() + "\n" +
                "Wins: :" + chosenDeck.getWins() + "\n" + "winsToTotal: " + chosenDeck.getWinsToTotal() + "\n" +
                "Games: " + chosenDeck.getGames() + "\n" + "Price: " + chosenDeck.getCost() +
                "\nMost Common Card: " + commonName);
    }

    public static void decks(boolean show, ArrayList<JComponent> list, JComboBox cardSelection,
                             JComboBox deckCardSelection, JTextArea deckDetail, JComboBox addCardJComboBox,
                             JButton removeCard, JButton addCard, JButton chooseDeck, JButton editName, JButton delete,
                             JComboBox choosenDeckCardSelection, JRadioButton chosenDeckRadioButton, JTextField editNameField,
                             JComboBox cardRemoveSelection) {
        if (!show) {
            cardDeckSelection = false;
            Tools.setVisible(false, list);
            return;
        }
        cardDeckSelection = true;
        Tools.setVisible(true, list);
        DefaultComboBoxModel cardsForDeck = new DefaultComboBoxModel();


        if (cardSelection.getSelectedItem() != null) {
            String deckName;
            String chosen = cardSelection.getSelectedItem().toString();
            if (chosen.length() >= 3 && !chosen.substring(0, 3).equals("New")) {
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
                Contoller.getPlayer().addDeck(newDeck);
                chosenDeck = newDeck;
                Contoller.getPlayer().update();
                ButtonController newDeckbc = new ButtonController(ButtonController.ButtonOptions.newDeck, chosenDeck.getName());

            }

            cardsForDeck.removeAllElements();
            if (chosenDeck.getAllCards() != null)
                for (Cards card1 : chosenDeck.getAllCards()) {
                    cardsForDeck.addElement(card1.getName());
                }
            deckCardSelection.setModel(cardsForDeck);
            String commonName = "-";
            if (chosenDeck != null && chosenDeck.getMostCommon() != null)
                commonName = chosenDeck.getMostCommon().getName();
            setText(chosenDeck, deckDetail, commonName);


            DefaultComboBoxModel addableCards = new DefaultComboBoxModel();
            for (Cards card1 : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                addableCards.addElement(card1.getName());
            }
            addCardJComboBox.setModel(addableCards);

            addCard.addActionListener(ActionListners.addListener(deckCardSelection, addCardJComboBox,choosenDeckCardSelection,chosenDeck,cardsForDeck));
            removeCard.addActionListener(ActionListners.removeListener(deckCardSelection, addCardJComboBox,choosenDeckCardSelection,chosenDeck,cardsForDeck,cardRemoveSelection));
            chooseDeck.addActionListener(ActionListners.choseDeckListener(chosenDeck));
            editName.addActionListener(ActionListners.editNameListener(editNameField,chosenDeck,deckDetail));
            delete.addActionListener(ActionListners.deleteListener(chosenDeck));
            chosenDeckRadioButton.addActionListener(ActionListners.chosenDeckListener(chosenDeck,chosenDeckRadioButton));

            if (chosenDeck.getAllCards() != null && chosenDeck.getAllCards().size() != 0) {
                DefaultComboBoxModel choosenDeckCardSelectionModel = new DefaultComboBoxModel();
                choosenDeckCardSelectionModel.removeAllElements();
                for (Cards cards : chosenDeck.getAllCards()) {
                    choosenDeckCardSelectionModel.addElement(cards.getName());
                }
                choosenDeckCardSelection.setModel(choosenDeckCardSelectionModel);
            }


        }
    }


}
