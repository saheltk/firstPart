package Cards;

import Constants.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.FileReader;
import java.util.ArrayList;

public class Cards {
    private String name;
    private int mana;
    private Rarity rarity;
    private Class cardClass;
    private Type type;
    private int cost = 0;
    private String description;

    //minion
    public int HP = 0;
    public int attack = 0;
    //spell
    //public Quest_and_reward;
    //weapon
    public int durability = 0;


    static FileReader[] file = new FileReader[Constants.cardNumbers];
    static Cards[] cards = new Cards[Constants.cardNumbers];


    public Cards() {}
    public Cards(String name, int mana, Rarity rarity, Class cardClass, Type type, String Description) {
        this.name = name;
        this.mana = mana;
        this.rarity = rarity;
        this.cardClass = cardClass;
        this.type = type;
        this.description = Description;

    }

    //Getters
    public String getName() { return name; }
    public int getMana() { return mana; }
    public Rarity getRarity() { return rarity; }
    public Class getCardClass() { return cardClass; }
    public Type getType() { return type; }
    public int getCost() { return cost; }
    public String getDescription() { return description; }

    //add card to  factory & set
    public void set(){
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

    private static void setCards(){
        ObjectMapper objectMapper = new ObjectMapper();
        String address ="src\\Cards\\CardFiles\\card";
        try {
            for (int i = 0; i < Constants.cardNumbers; i++) {
                file[i] = new FileReader(address + i + ".json");
                cards[i] = objectMapper.readValue(file[i], Cards.class);
                cards[i].set();
            }
        }
        catch (Exception e){
            System.out.println("Card problem!");

        }
    }

    public static Cards createCardByName(String name) {
        Cards answer = null;
        try {
            for (int i = 0; i < 20; i++) {
                if (cards[i].name.equalsIgnoreCase(name)) {
                    answer = new Cards(name, cards[i].mana, cards[i].rarity, cards[i].cardClass, cards[i].type, cards[i].description);
                    answer.cost = cards[i].cost;
                    answer.HP = cards[i].HP;
                    answer.durability = cards[i].durability;
                    answer.attack = cards[i].attack;
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


}
