package Command;

import Cards.Cards;
import Cards.Type;

import Player.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class GamePlay {
    private int turn = 0;
    private int cardFromDeck = 0;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean isFirstPlayerTurn;
    private Player playerInRole;

    private ArrayList<Cards> firstPlayerGround;
    private ArrayList<Cards> firstPlayerHand;
    private ArrayList<Cards> firstPlayerDeck;

    private ArrayList<Cards> secondPlayerGround;
    private ArrayList<Cards> secondPlayerHand;
    private ArrayList<Cards> secondPlayerDeck;

    GamePlay(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        playerInRole = firstPlayer;
        secondPlayer = null;
        isFirstPlayerTurn = true;
        firstPlayerHand = new ArrayList<Cards>();
        secondPlayerHand = new ArrayList<Cards>();
        firstPlayerGround = new ArrayList<Cards>();
        secondPlayerGround = new ArrayList<Cards>();
    }


    public void nextTurn() {
        //   isFirstPlayerTurn=!isFirstPlayerTurn;
        if (isFirstPlayerTurn) turn++;
        if (isFirstPlayerTurn) playerInRole = firstPlayer;
        //   else playerInRole=secondPlayer;

        firstPlayer.setMana(turn);
        // if (secondPlayer!=null)
        //secondPlayer.setMana(turn);
    }

    public boolean useCard(String cardName) {
        Cards card = new Cards();

        if (Cards.createCardByName(cardName).getMana() > firstPlayer.getMana()) {
            return false;}

        if (isFirstPlayerTurn) {
            for (Cards cards : firstPlayerHand) {
                if (cards.getName().equals(cardName))
                    card = cards;
            }
            firstPlayer.setMana(firstPlayer.getMana() - card.getMana());
            if (Cards.createCardByName(cardName).getType().equals(Type.Minion) && minions() == 7) {
                System.out.println("Minions:"+minions());
                firstPlayerHand.remove(card);
                return false;
            }
            firstPlayerGround.add(card);
            firstPlayerHand.remove(card);

        }

        return true;
    }

    public Cards pickCardFromDeck() {
        ArrayList<Cards> deck = new ArrayList<Cards>();
        if (isFirstPlayerTurn) {
            System.out.println("first player turn");
            deck.addAll(firstPlayer.getChosenDeck().getAllCards());
            if (firstPlayerHand.size() <= 11)
                firstPlayerHand.add(deck.get(cardFromDeck));
        } else if (secondPlayer != null) {
            deck.addAll(secondPlayer.getChosenDeck().getAllCards());
            if (secondPlayerHand.size() <= 11)
                secondPlayerHand.add(deck.get(cardFromDeck));
        }
        cardFromDeck++;
        return deck.get(cardFromDeck - 1);
    }

    private int minions() {
        int count = 0;
        if (isFirstPlayerTurn) {
            for (Cards cards : firstPlayerGround)
                if (cards.getType().equals(Type.Minion))
                    count++;
        } else {
            for (Cards cards : secondPlayerGround)
                if (cards.getType().equals(Type.Minion))
                    count++;
        }
        return count;
    }

    //Getter
    public Player getPlayerInRole() {
        return playerInRole;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Cards> getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public ArrayList<Cards> getFirstPlayerDeck() {
        return firstPlayerDeck;
    }

    public ArrayList<Cards> getFirstPlayerGround() {
        return firstPlayerGround;
    }

    public ArrayList<Cards> getSecondPlayerHand() {
        return secondPlayerHand;
    }

    public ArrayList<Cards> getSecondPlayerDeck() {
        return secondPlayerDeck;
    }

    public ArrayList<Cards> getSecondPlayerGround() {
        return secondPlayerGround;
    }
}
