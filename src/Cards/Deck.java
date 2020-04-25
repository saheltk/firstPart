package Cards;

import Heroes.Heroes;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Deck {
    private String name;
    private Heroes hero;
    private ArrayList<Cards> cards = new ArrayList<>();

    public Deck(String name, Heroes hero, ArrayList<Cards> cards) {
        this.name = name;
        this.hero = hero;
        this.cards = cards;
    }

    //AllCardsCotrol
    public void removeCard(Cards newCard) throws IOException { cards.remove(newCard); }
    public void addCard(Cards newCard) throws IOException { cards.add(newCard); }


}
