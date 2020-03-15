package Cards;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cards {
     static ArrayList<Cards> allCards = new ArrayList<Cards>();
    static ArrayList<Cards> allMage = new ArrayList<Cards>();
    static ArrayList<Cards> allRogue = new ArrayList<Cards>();
    static ArrayList<Cards> allWarlock = new ArrayList<Cards>();
    static ArrayList<Cards> allNatural= new ArrayList<Cards>();

    public String Name;
     public int Mana;
     public Rarity rarity;
     public Class CardClass;
     public Type type;

    public int cost=0;
    public String Description;

    //minion
    public int HP=0;
    public int attack=0;
    //spell
    //public Quest_and_reward;
    //weapon
    public int Durability=0;

    public Cards(){}
    public Cards(String Name, int Mana, Rarity rarity, Class CardClass,Type type,String Description ){
        this.Name=Name;
        this.Mana=Mana;
        this.rarity=rarity;
        this.CardClass=CardClass;
        this.type=type;
        this.Description=Description;

        allCards.add(this);
    }

    public void setCost(){
        if(cost==0){
            switch (rarity){
                case Common:
                    cost=2;return;
                case Rare:
                    cost=5;return;
                case Epic:
                    cost=8;return;
                case Legendary:
                    cost=10;return;
            }
        }
    }
    public void setClassArray(){
            switch (CardClass){
                case Warlock:
                    allWarlock.add(this);
                    return;
                case Rogue:
                    allRogue.add(this);
                    return;
                case Mage:
                    allMage.add(this);
                    return;
                case Natural:
                    allNatural.add(this);
                    return;
            }
    }
    public void setCard(){
        allCards.add(this);
        this.setClassArray();
        this.setCost();
        //System.out.println(this.Name);
    }


    @Override
    public String toString() {
        String answer="Name:"+Name+" Manna:"+Mana+" Rarity:"+rarity+" Class:"+CardClass+" Type:"+type;
        return answer;
    }

    public static void  PrintToString(Cards c, boolean price){
        System.out.println("*********************");
        System.out.println("Name:"+c.Name);
        System.out.println("Manna: "+c.Mana);
        System.out.println("Rarity: "+c.rarity);
        System.out.println("Class: "+c.CardClass);
        System.out.println("Type: "+c.type);
        if (!c.type.equals(Type.Spell))
            System.out.println("Attack: "+c.attack);
        if (c.type.equals(Type.Weapons))
            System.out.println("Durability: "+c.Durability);
        if (!c.type.equals(Type.Minion))
            System.out.println("HP: "+c.HP);
        if (price)
            System.out.println("Price: "+c.cost);
        System.out.println("*********************");
    }

    public static void CLI() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

            FileReader file1 = new FileReader("src\\Cards\\CardFiles\\card1.json");
            FileReader file2 = new FileReader("src\\Cards\\CardFiles\\card2.json");
            FileReader file3 = new FileReader("src\\Cards\\CardFiles\\card3.json");
            FileReader file4 = new FileReader("src\\Cards\\CardFiles\\card4.json");
            FileReader file5 = new FileReader("src\\Cards\\CardFiles\\card5.json");
            FileReader file6 = new FileReader("src\\Cards\\CardFiles\\card6.json");
            FileReader file7 = new FileReader("src\\Cards\\CardFiles\\card7.json");
            FileReader file8 = new FileReader("src\\Cards\\CardFiles\\card8.json");
            FileReader file9 = new FileReader("src\\Cards\\CardFiles\\card9.json");
            FileReader file10 = new FileReader("src\\Cards\\CardFiles\\card10.json");
            FileReader file11 = new FileReader("src\\Cards\\CardFiles\\card11.json");
            FileReader file12 = new FileReader("src\\Cards\\CardFiles\\card12.json");
          /*  FileReader file13 = new FileReader("src\\Cards\\CardFiles\\card13.json");
            FileReader file14 = new FileReader("src\\Cards\\CardFiles\\card14.json");
            FileReader file15 = new FileReader("src\\Cards\\CardFiles\\card15.json");
            FileReader file16 = new FileReader("src\\Cards\\CardFiles\\card16.json");
            FileReader file17 = new FileReader("src\\Cards\\CardFiles\\card17.json");
            FileReader file18 = new FileReader("src\\Cards\\CardFiles\\card18.json");
            FileReader file19 = new FileReader("src\\Cards\\CardFiles\\card19.json");
            FileReader file20 = new FileReader("src\\Cards\\CardFiles\\card20.json");*/

        try {
            Cards c1 = objectMapper.readValue(file1,Cards.class);c1.setCard();
            Cards c2 = objectMapper.readValue(file2,Cards.class);c2.setCard();
            Cards c3 = objectMapper.readValue(file3,Cards.class);c3.setCard();
            Cards c4 = objectMapper.readValue(file4,Cards.class);c4.setCard();
            Cards c5 = objectMapper.readValue(file5,Cards.class);c5.setCard();
            Cards c6 = objectMapper.readValue(file6,Cards.class);c6.setCard();
            Cards c7 = objectMapper.readValue(file7,Cards.class);c7.setCard();
            Cards c8 = objectMapper.readValue(file8,Cards.class);c8.setCard();
            Cards c9 = objectMapper.readValue(file9,Cards.class);c9.setCard();
            Cards c10 = objectMapper.readValue(file10,Cards.class);c10.setCard();
            Cards c11 = objectMapper.readValue(file11,Cards.class);c11.setCard();
            Cards c12 = objectMapper.readValue(file12,Cards.class);c12.setCard();
            /*  Cards c13 = objectMapper.readValue(file13,Cards.class);c13.setCard();
            Cards c14 = objectMapper.readValue(file14,Cards.class);c14.setCard();
            Cards c15 = objectMapper.readValue(file15,Cards.class);c15.setCard();
            Cards c16 = objectMapper.readValue(file16,Cards.class);c16.setCard();
            Cards c17 = objectMapper.readValue(file17,Cards.class);c17.setCard();
            Cards c18 = objectMapper.readValue(file18,Cards.class);c18.setCard();
            Cards c19 = objectMapper.readValue(file19,Cards.class);c19.setCard();
            Cards c20 = objectMapper.readValue(file20,Cards.class);c20.setCard();*/


        }
        catch (Exception e){
            System.out.println("Card problem!");
        }

    }

    public static void main(String[] args) throws IOException {
        Cards.CLI();
    }
}
