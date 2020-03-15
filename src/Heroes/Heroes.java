package Heroes;

import Cards.*;
import Cards.Type;
import Cards.Class;

import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Heroes {
    int Mana;
    HeroName name;
    int HP;
    int attack;
    boolean heroPower = false;
    boolean Mage = false;
    boolean Rogue = false;

    Scanner s = new Scanner(System.in);
    Heroes againstHero;
     ArrayList<Cards> cardsArrayList = new ArrayList<>();//be player ham add she
    ArrayList<Cards> onBoardCards = new ArrayList<Cards>();


    Heroes(HeroName name) {
        this.name = name;
        HP = 30;
        this.SpecialPower();
    }

    public void SpecialPower() {

        switch (name) {
            case Warlock:
                HP = 35;
                return;
            case Rogue:
                Rogue = true;
                return;
            case Mage:
                Mage = true;
                return;
        }
    }

    public void HeroPower() {
        if ( heroPower) {
            System.out.println("have been used");
            return;
        }
        if (Mana<2){
            System.out.println("No enough Mana ");return; }
        System.out.println("Select what to do?(write down the number)");
        System.out.println("1-add one life an one attack to one Minion");
        System.out.println("2-add one card");
        /////////////////////////////////////////////////////
        switch (name) {
            case Warlock:
                if (HP<3){
                    System.out.println("No enough HP ");return; }
                HP-=2;
                Mana-=2;
                heroPower=false;
                System.out.println("Selected Weapon?");
                /////////////////////////////////////////////////////
                return;
            case Rogue:
                if (Mana<3){
                 System.out.println("No enough Mana ");return; }
                heroPower=false;
                System.out.println("Selected Weapon?");
                /////////////////////////////////////////////////////
                Mana-=3;
                return;
            case Mage:
                System.out.println("Target?");
                /////////////////////////////////////////////////////
                heroPower=false;
                Mana-=2;
                return;
        }
        this.checkDie();
    }

    public int price = 0;

    public void useCard(Cards card) {
        price += card.Mana;
        if (Mage) {
            if (card.type.equals(Type.Spell)) {
                if (price <= 2) price = 0;
                else price -= 2;
            }
        }
        if (Rogue) {
            if (card.CardClass.equals(Class.Warlock) || card.CardClass.equals(Class.Mage)) {
                if (price <= 2) price = 0;
                else price -= 2;
            }
        }
        if (Mana>=price)
        Mana -= price;
        else {
            System.out.println("No enough Mana!");
            return;
        }
        price = 0;
        if (!card.type.equals(Type.Spell)) onBoardCards.add(card);
        this.action(card); this.checkDie();

    }

    public void action(Cards card) {
    }

    public void attack(Player player, Cards c) {
            player.currrentHero.HP -= attack;

    }

    public void newTurn(){
        heroPower=true;
    }

    public void checkDie(){
        for (Cards c : onBoardCards) {
            if ((c.type.equals(Type.Minion))&& c.HP<=0) onBoardCards.remove(c);
            if (c.type.equals(Type.Weapons) && c.Durability<=0) onBoardCards.remove(c);
        }
    }


}
