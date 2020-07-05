package Graphic.Tools;

import Cards.Cards;
import Cards.Deck;
import Command.Contoller;
import Command.Check;
import Graphic.ButtonController;
import Graphic.Panels.Shop;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ActionListners {
    public static ActionListener addListener(JComboBox deckCardSelection, JComboBox addCardJComboBox,
                                             JComboBox choosenDeckCardSelection, Deck chosenDeck, DefaultComboBoxModel cardsForDeck) {
        ActionListener actionListener = ((ActionListener) e -> {
            try {
                String cardName = addCardJComboBox.getSelectedItem().toString();
                Cards card = Cards.createCardByName(cardName);
                if (Check.addCard(cardName, chosenDeck)) {
                    if (card != null) {
                        ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.add_Card_To_Deck, card.getName());
                    }

                } else if (!Check.addCard(cardName, chosenDeck)) {
                    JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't add this card", "DECK MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

                DefaultComboBoxModel addableCards12 = new DefaultComboBoxModel();
                addableCards12.removeAllElements();
                for (Cards card1 : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                    addableCards12.addElement(card1.getName());
                }
                addCardJComboBox.setModel(addableCards12);
                if (chosenDeck.getAllCards() != null && chosenDeck.getAllCards().size() != 0) {
                    DefaultComboBoxModel choosenDeckCardSelectionModel = new DefaultComboBoxModel();
                    choosenDeckCardSelectionModel.removeAllElements();
                    for (Cards cards : chosenDeck.getAllCards()) {
                        choosenDeckCardSelectionModel.addElement(cards.getName());
                    }
                    choosenDeckCardSelection.setModel(choosenDeckCardSelectionModel);
                }
                cardsForDeck.removeAllElements();
                if (chosenDeck.getAllCards() != null)
                    for (Cards card1 : chosenDeck.getAllCards()) {
                        cardsForDeck.addElement(card1.getName());
                    }
                deckCardSelection.setModel(cardsForDeck);
            } catch (Exception e1) {
            }
        });
        return actionListener;
    }

    public static ActionListener removeListener(JComboBox deckCardSelection, JComboBox addCardJComboBox,
                                                JComboBox choosenDeckCardSelection, Deck chosenDeck, DefaultComboBoxModel cardsForDeck,
                                                JComboBox cardRemoveSelection) {
        ActionListener actionListener = ((ActionListener) e -> {
            try {
                String cardName = cardRemoveSelection.getSelectedItem().toString();
                Cards card = Cards.createCardByName(cardName);
                if (Check.removeCard(cardName, chosenDeck)) {
                    if (card != null) {
                        ButtonController mycard = new ButtonController(ButtonController.ButtonOptions.remove_Card_FromDeck, card.getName());
                    }

                } else if (!Check.removeCard(cardName, chosenDeck)) {
                    JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't remove this card", "DECK MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

                DefaultComboBoxModel addableCards1 = new DefaultComboBoxModel();
                addableCards1.removeAllElements();
                for (Cards card1 : Contoller.getPlayer().despiteDeck(chosenDeck)) {
                    addableCards1.addElement(card1.getName());
                }
                addCardJComboBox.setModel(addableCards1);
                if (chosenDeck.getAllCards() != null && chosenDeck.getAllCards().size() != 0) {
                    DefaultComboBoxModel choosenDeckCardSelectionModel = new DefaultComboBoxModel();
                    choosenDeckCardSelectionModel.removeAllElements();
                    for (Cards cards : chosenDeck.getAllCards()) {
                        choosenDeckCardSelectionModel.addElement(cards.getName());
                    }
                    choosenDeckCardSelection.setModel(choosenDeckCardSelectionModel);
                }
                cardsForDeck.removeAllElements();
                if (chosenDeck.getAllCards() != null)
                    for (Cards card1 : chosenDeck.getAllCards()) {
                        cardsForDeck.addElement(card1.getName());
                    }
                deckCardSelection.setModel(cardsForDeck);
            } catch (Exception e2) {
            }

        });
        return actionListener;
    }

    public static ActionListener choseDeckListener(Deck chosenDeck) {
        ActionListener actionListener = e -> {
            if (chosenDeck != null) {
                Contoller.getPlayer().setChosenDeck(chosenDeck);
                Contoller.getPlayer().update();
                ButtonController choosedeckbc = new ButtonController(ButtonController.ButtonOptions.Choose_Deck, chosenDeck.getName());
            }
        };
        return actionListener;
    }

    public static ActionListener editNameListener(JTextField editNameField, Deck chosenDeck, JTextArea deckDetail) {
        ActionListener actionListener = e -> {

            String name = editNameField.getText();
            if (chosenDeck != null) {
                String lastName = chosenDeck.getName();
                chosenDeck.setName(name);
                Contoller.getPlayer().update();
                ButtonController editNamebc = new ButtonController(ButtonController.ButtonOptions.edit_Deck_Name, lastName + " to " + name);
                Tools.setText(chosenDeck, deckDetail, chosenDeck.getMostCommon().getName());
            }
        };
        return actionListener;

    }

    public static ActionListener deleteListener(Deck chosenDeck) {
        ActionListener actionListener = e -> {
            if (chosenDeck != null) {
                Contoller.getPlayer().deleteDeck(chosenDeck);
                Contoller.getPlayer().update();
                ButtonController editNamebc = new ButtonController(ButtonController.ButtonOptions.delete_Deck, chosenDeck.getName());
            }
        };
        return actionListener;
    }
    public static ActionListener chosenDeckListener(Deck chosenDeck, JRadioButton chosenDeckRadioButton) {
        ActionListener actionListener = e -> {
            if (chosenDeckRadioButton.isSelected()) {
                Contoller.getPlayer().setChosenDeck(chosenDeck);
            }
        };
        return actionListener;
    }

}
