package Player;

import Cards.Cards;
import Heroes.Heroes;

import java.util.ArrayList;

public class Player {
    public String UserName;
    public String Password;
    public int Gems;
    public Heroes currrentHero;
    public ArrayList<Cards> cardsArrayList = new ArrayList<>();
    public ArrayList<Heroes> availableHeroes = new ArrayList<>();

    public Player(String UserName, String Password,int Gems,Heroes Hero,ArrayList<Cards> cards,ArrayList<Heroes> heroes){
        this.UserName=UserName;
        this.Password=Password;
        this.Gems=Gems;
        currrentHero=Hero;
        cardsArrayList=cards;
        availableHeroes=heroes;
    }

    public String getUserName() {
        return UserName;
    }
    public String getPassword() { return Password;}
    public int getGems() {
        return Gems;
    }
    public Heroes getCurrrentHero() {
        return currrentHero;
    }
    public ArrayList<Cards> getCardsArrayList(){ return cardsArrayList;}
    public ArrayList<Heroes> getAvailableHeroes(){ return availableHeroes;}

    public void removeCard(Cards newCard) { cardsArrayList.remove(newCard);}
    public void addCard(Cards newCard){
        cardsArrayList.add(newCard);
    }
    public void addHero(Heroes newHero){
        availableHeroes.add(newHero);
    }
    public void setCurrrentHero(Heroes newHero) { currrentHero=newHero; }
    public void setGems(int gems) {
        Gems = gems;
    }
}
