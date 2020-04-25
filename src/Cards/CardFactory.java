package Cards;

import java.util.ArrayList;

public class CardFactory {
    private static ArrayList<Cards> allCards = new ArrayList<Cards>();

    //Arranged by class
    private static ArrayList<Cards> allMage = new ArrayList<Cards>();
    private static ArrayList<Cards> allRogue = new ArrayList<Cards>();
    private static ArrayList<Cards> allWarlock = new ArrayList<Cards>();
    private static ArrayList<Cards> allNatural = new ArrayList<Cards>();

    //Arranged by type
    private static ArrayList<Cards> allMinion = new ArrayList<Cards>();
    private static ArrayList<Cards> allWeapon = new ArrayList<Cards>();
    private static ArrayList<Cards> allSpell = new ArrayList<Cards>();

    public static void add(Cards newCard) {
        allCards.add(newCard);
        setClassArray(newCard);
        setTypeArray(newCard);
    }


    public static void setClassArray(Cards newCard) {
        switch (newCard.getCardClass()) {
            case Warlock:
                allWarlock.add(newCard);
                break;
            case Rogue:
                allRogue.add(newCard);
                break;
            case Mage:
                allMage.add(newCard);
                break;
            case Natural:
                allNatural.add(newCard);
                break;
        }
    }

    public static void setTypeArray(Cards newCard) {
        switch (newCard.getType()) {
            case Minion:
                allMinion.add(newCard);
                break;
            case Weapons:
                allWeapon.add(newCard);
                break;
            case Spell:
                allSpell.add(newCard);
                break;

        }
    }


}
