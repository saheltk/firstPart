package Heroes;

import Cards.*;
import Cards.Type;
import Cards.Class;
import Player.Player;

import java.util.ArrayList;


public class Heroes {

    private HeroName name;
    private int Mana;
    private int HP;
    private int attack;
    private boolean heroPowerUsed = false;
    private boolean Mage = false;
    private boolean Rogue = false;
    private int price = 0;

    public ArrayList<Cards> defaultCards = new ArrayList<>();//be player ham add she
    ArrayList<Cards> onBoardCards = new ArrayList<Cards>();

    public Heroes() { }
    public Heroes(HeroName name) {
        this.name = name;
        HP = 30;
        this.SpecialPower();
    }


    //Getters
    public int getPrice() { return price; }

    private void SpecialPower() {

        switch (name) {
            case Warlock:
                HP = 35;
                break;
            case Rogue:
                Rogue = true;
                break;
            case Mage:
                Mage = true;
                break;
        }
    }
    public ArrayList<Cards> setDefaultCards() {
        ArrayList<Cards> cards = new ArrayList<Cards>();
        switch (name) {
            case Warlock:
                cards.add(Cards.createCardByName("Dreadscale"));
                cards.add(Cards.createCardByName("Twig of the World Tree"));
                cards.add(Cards.createCardByName("Fireball"));
                cards.add(Cards.createCardByName("Wolfrider"));
                cards.add(Cards.createCardByName("Big Brother"));
                cards.add(Cards.createCardByName("Baby Loyal"));
                cards.add(Cards.createCardByName("Kind Nurse"));
                cards.add(Cards.createCardByName("Die Hard"));
                cards.add(Cards.createCardByName("Rich Guy"));
                cards.add(Cards.createCardByName("Candleshot"));
                break;

            case Mage:
                cards.add(Cards.createCardByName("Polymorph"));
                cards.add(Cards.createCardByName("Candleshot"));
                cards.add(Cards.createCardByName("Healing Touch"));
                cards.add(Cards.createCardByName("Fireball"));
                cards.add(Cards.createCardByName("Barrel Toss"));
                cards.add(Cards.createCardByName("Baby Loyal"));
                cards.add(Cards.createCardByName("Kind Nurse"));
                cards.add(Cards.createCardByName("Savage Roar"));
                cards.add(Cards.createCardByName("Sheep"));
                break;

            case Rogue:
                cards.add(Cards.createCardByName("Friendly Smith"));
                cards.add(Cards.createCardByName("Glaivezooka"));
                cards.add(Cards.createCardByName("Fireball"));
                cards.add(Cards.createCardByName("Wolfrider"));
                cards.add(Cards.createCardByName("Baby Loyal"));
                cards.add(Cards.createCardByName("Savage Roar"));
                cards.add(Cards.createCardByName("Rich Guy"));
                cards.add(Cards.createCardByName("Big Brother"));
                cards.add(Cards.createCardByName("Kind Nurse"));
                cards.add(Cards.createCardByName("Twig of the World Tree"));
                break;

        }
        this.defaultCards = cards;
        return cards;
    }


    public void HeroPower() {
        if (heroPowerUsed) {
            System.out.println("have been used");
            return;
        }
        if (Mana < 2) {
            System.out.println("No enough Mana ");
            return;
        }
        System.out.println("Select what to do?(write down the number)");
        System.out.println("1-add one life an one attack to one Minion");
        System.out.println("2-add one card");
        /////////////////////////////////////////////////////
        switch (name) {
            case Warlock:
                if (HP < 3) {
                    System.out.println("No enough HP ");
                    return;
                }
                HP -= 2;
                Mana -= 2;
                heroPowerUsed = false;
                System.out.println("Selected Weapon?");
                /////////////////////////////////////////////////////
                break;
            case Rogue:
                if (Mana < 3) {
                    System.out.println("No enough Mana ");
                    return;
                }
                heroPowerUsed = false;
                System.out.println("Selected Weapon?");
                /////////////////////////////////////////////////////
                Mana -= 3;
                break;
            case Mage:
                System.out.println("Target?");
                /////////////////////////////////////////////////////
                heroPowerUsed = false;
                Mana -= 2;
                break;
        }
        this.checkDie();
    }

    public void useCard(Cards card) {
        price += card.getMana();
        if (Mage) {
            if (card.getType().equals(Type.Spell)) {
                if (price <= 2) price = 0;
                else price -= 2;
            }
        }
        if (Rogue) {
            if (card.getCardClass().equals(Class.Warlock) || card.getCardClass().equals(Class.Mage)) {
                if (price <= 2) price = 0;
                else price -= 2;
            }
        }
        if (Mana >= price)
            Mana -= price;
        else {
            System.out.println("No enough Mana!");
            return;
        }
        price = 0;
        if (!card.getType().equals(Type.Spell)) onBoardCards.add(card);
        this.action(card);
        this.checkDie();

    }

    public void action(Cards card) {
    }

    public void attack(Player player, Cards c) {
        player.getCurrentHero().HP -= attack;

    }

    public void newTurn() {
        heroPowerUsed = true;
    }

    public void checkDie() {
        for (Cards c : onBoardCards) {
            if ((c.getType().equals(Type.Minion)) && c.HP <= 0) onBoardCards.remove(c);
            if (c.getType().equals(Type.Weapons) && c.durability <= 0) onBoardCards.remove(c);
        }
    }
    public String getName(){
        return name.toString();
    }


}
