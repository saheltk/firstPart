package Cards;

import Constants.Constants;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.util.ArrayList;

public class Cards {
    public static Cards[] cards = new Cards[Constants.cardNumbers];
    static FileReader[] file = new FileReader[Constants.cardNumbers];
    //minion
    public int HP = 0;
    public int attack = 0;
    //spell
    //public Quest_and_reward;
    //weapon
    public int durability = 0;
    private String name;
    private int mana;
    private Rarity rarity;
    private Class cardClass;
    private Type type;
    private int cost = 0;
    private String description;
    private int id;


    public Cards() {
    }

    public Cards(String name, int mana, Rarity rarity, Class cardClass, Type type, String Description) {
        this.name = name;
        this.mana = mana;
        this.rarity = rarity;
        this.cardClass = cardClass;
        this.type = type;
        this.description = Description;

    }

    public static void setCards() {
        ObjectMapper objectMapper = new ObjectMapper();
        String address = "src\\Cards\\CardFiles\\card";
        try {
            for (int i = 0; i < Constants.cardNumbers; i++) {
                file[i] = new FileReader(address + (i + 1) + ".json");
                cards[i] = objectMapper.readValue(file[i], Cards.class);
                cards[i].set();
                cards[i].id = i;
            }
        } catch (Exception e) {
            System.out.println("Card problem!");

        }
    }

    public static Cards createCardByName(String name) {
        Cards answer = null;
        try {
            for (int i = 0; i < Constants.cardNumbers; i++) {
                if (cards[i].name.equalsIgnoreCase(name)) {
                    answer = cards[i];
                    answer.cardClass = cards[i].cardClass;
                    System.out.println(cards[i].cardClass);
                }
            }
            return answer;
        } catch (Exception e) {
            System.out.println("There is no card with that name" + name);
            return null;
        }

    }

    public static ArrayList<Cards> createCardList(ArrayList<String> cardname) {
        ArrayList<Cards> answer = new ArrayList();
        for (String cn : cardname) {
            answer.add(createCardByName(cn));
        }
        return answer;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getMana() {
        return mana;
    }

    public int getId() {
        return id;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Class getCardClass() {
        return cardClass;
    }

    public Type getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    //add card to  factory & set
    public void set() {
        CardFactory.add(this);
        this.setCost();
    }

    public void setCost() {
        if (cost == 0) {
            switch (rarity) {
                case Common:
                    cost = 2;
                    break;
                case Rare:
                    cost = 5;
                    break;
                case Epic:
                    cost = 8;
                    break;
                case Legendary:
                    cost = 10;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String answer = "name:" + name + " Manna:" + mana + " Rarity:" + rarity + " Class:" + cardClass + " Type:" + type;
        return answer;
    }


}
