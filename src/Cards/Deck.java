package Cards;

import Graphic.MainPanel;
import Heroes.Heroes;
import Player.Player;
import Cards.Cards;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Deck {
    private String name;
    private Heroes hero;
    private ArrayList<Cards> allCards = new ArrayList<>();
    private int wins=0;
    private int games=0;
    private double cost=0;
    private double winsToTotal=0;
    private Cards mostCommon;

    public Deck(){}
    public Deck(String name, Heroes hero, ArrayList<Cards> allCards) {
        this.name = name;
        this.hero = hero;
        this.allCards = allCards;
    }

    //Setter
    public void setHero(Heroes hero) { this.hero = hero; }
    public void setCards(ArrayList<Cards> allCards) { this.allCards = allCards; }
    public void setWins(int wins) { this.wins = wins; }
    public void setGames(int games) { this.games = games; }
    public void setCost() {
        int sum = 0;
        if (allCards!=null && allCards.size()!=0){
        for (Cards cards : allCards) {
            sum += cards.getMana();
        }
        cost = (sum / allCards.size());}
        else
            cost=0;
    }
    public void setWinsToTotal() {
        if (games!=0) winsToTotal = wins / games;
        else
            winsToTotal=0;
    }
    public void setMostCommon() {

        if (allCards==null || allCards.size()==0){
            mostCommon=null;
            return;
        }
        ArrayList<Cards> cardMax = new ArrayList<>();
        ArrayList<Cards> cardMax1 = new ArrayList<>();
        ArrayList<Cards> cardMax2 = new ArrayList<>();

        int[] cardmax = new int[allCards.size()-1];


        for (int i = 0; i < allCards.size()-1; i++) {
            for (int j = i+1; j < allCards.size(); j++) {
                if (allCards.get(i).getName().equals(allCards.get(j).getName()))
                    cardmax[i]++;
            }
        }

        int max= Math.max(cardmax[0],cardmax[1]);
        for (int i = 0; i < allCards.size()-1; i++) {
            max = Math.max(cardmax[i],cardmax[i+1]);
        }

        for (int i = 0; i < allCards.size()-1; i++) {
            if (cardmax[i]==max)
                cardMax.add(allCards.get(i));
        }
        if(cardMax.size()==1){
            mostCommon=cardMax.get(0);
            return;
        }
        for (Cards cards:cardMax){
            if (cards.getRarity().equals(Rarity.Legendary)){
                cardMax1.add(cards);
            }
        }
        if (cardMax1.size()==0){
            for (Cards cards:cardMax){
                if (cards.getRarity().equals(Rarity.Epic)){
                    cardMax1.add(cards);
                }
            }
        }
        if (cardMax1.size()==0){
            for (Cards cards:cardMax){
                if (cards.getRarity().equals(Rarity.Rare)){
                    cardMax1.add(cards);
                }
            }
        }
        if (cardMax1.size()==0){
            for (Cards cards:cardMax){
                if (cards.getRarity().equals(Rarity.Common)){
                    cardMax1.add(cards);
                }
            }
        }
        if(cardMax1.size()==1){
            mostCommon=cardMax1.get(0);
            return;
        }

        int[] cardmax1 = new int[cardMax1.size()-1];


        for (int i = 0; i < cardMax1.size(); i++) {
                    cardmax1[i]=cardMax1.get(i).getMana();
        }

        int max1= Math.max(cardmax1[0],cardmax1[1]);
        for (int i = 0; i < cardMax1.size()-1; i++) {
            max = Math.max(cardmax1[i],cardmax1[i+1]);
        }

        for (int i = 0; i < cardMax1.size()-1; i++) {
            if (cardmax[i]==max)
                cardMax2.add(cardMax1.get(i));
        }
        if(cardMax2.size()==1){
            mostCommon=cardMax2.get(0);
            return;
        }
        else{
            for (int i = 0; i < cardMax2.size(); i++) {
                if (cardMax2.get(i).getType().equals(Type.Minion)){
                    mostCommon=cardMax2.get(i);
                    return;
                }
            }

        }
        mostCommon=cardMax2.get(0);
        return;
    }

    //Getter

    public String getName() { return name; }
    public Heroes getHero() { return hero; }
    public int getWins() { return wins; }
    public int getGames() { return games; }
    public double getCost() { return cost; }
    public double getWinsToTotal() { return winsToTotal; }
    public Cards getMostCommon() { return mostCommon; }
    public int getCardNumber(){ return allCards.size();}

    public ArrayList<Cards> getAllCards() { return allCards; }

    //add
    public void addWin() {
        wins++;
        games++;
        setWinsToTotal();
    }

    public void addLoose() {
        games++;
        setWinsToTotal();
    }

    //AllCardsControl
    public void removeCard(Cards newCard) throws IOException {
        allCards.remove(newCard);
        setCost();
        setMostCommon();
    }
    public void addCard(Cards newCard) throws IOException {
        allCards.add(newCard);
        setCost();
        setMostCommon();
    }



}
