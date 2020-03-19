package Cards;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cards {
   static FileReader [] file= new FileReader[20];
   static Cards [] cards = new Cards[20];

public     static ArrayList<Cards> allCards = new ArrayList<Cards>();
    static ArrayList<Cards> allMage = new ArrayList<Cards>();
    static ArrayList<Cards> allRogue = new ArrayList<Cards>();
    static ArrayList<Cards> allWarlock = new ArrayList<Cards>();
    static ArrayList<Cards> allNatural= new ArrayList<Cards>();

    static ArrayList<Cards> allMinion = new ArrayList<Cards>();
    static ArrayList<Cards> allWeapon = new ArrayList<Cards>();
    static ArrayList<Cards> allSpell= new ArrayList<Cards>();


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
                    cost=2;break;
                case Rare:
                    cost=5;break;
                case Epic:
                    cost=8;break;
                case Legendary:
                    cost=10;break;
            }
        }
    }
    public void setClassArray(){
        switch (CardClass){
            case Warlock:
                allWarlock.add(this);
                break;
            case Rogue:
                allRogue.add(this);
                break;
            case Mage:
                allMage.add(this);
                break;
            case Natural:
                allNatural.add(this);
                break;
        }
    }
    public void setTypeArray(){
        switch (type){
            case Minion:
                allMinion.add(this);
                break;
            case Weapons:
                allWeapon.add(this);
                break;
            case Spell:
                allSpell.add(this);
                break;

        }
    }
    public void setCard(){
        allCards.add(this);
        this.setClassArray();
        this.setTypeArray();
        this.setCost();
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

             file[0] = new FileReader("src\\Cards\\CardFiles\\card1.json");
             file[1] = new FileReader("src\\Cards\\CardFiles\\card2.json");
             file[2] = new FileReader("src\\Cards\\CardFiles\\card3.json");
             file[3] = new FileReader("src\\Cards\\CardFiles\\card4.json");
             file[4] = new FileReader("src\\Cards\\CardFiles\\card5.json");
             file[5] = new FileReader("src\\Cards\\CardFiles\\card6.json");
             file[6] = new FileReader("src\\Cards\\CardFiles\\card7.json");
             file[7] = new FileReader("src\\Cards\\CardFiles\\card8.json");
             file[8] = new FileReader("src\\Cards\\CardFiles\\card9.json");
             file[9] = new FileReader("src\\Cards\\CardFiles\\card10.json");
             file[10] = new FileReader("src\\Cards\\CardFiles\\card11.json");
             file[11] = new FileReader("src\\Cards\\CardFiles\\card12.json");
             file[12] = new FileReader("src\\Cards\\CardFiles\\card13.json");
             file[13] = new FileReader("src\\Cards\\CardFiles\\card14.json");
             file[14] = new FileReader("src\\Cards\\CardFiles\\card15.json");
             file[15] = new FileReader("src\\Cards\\CardFiles\\card16.json");
             file[16] = new FileReader("src\\Cards\\CardFiles\\card17.json");
             file[17] = new FileReader("src\\Cards\\CardFiles\\card18.json");
             file[18] = new FileReader("src\\Cards\\CardFiles\\card19.json");
             file[19] = new FileReader("src\\Cards\\CardFiles\\card20.json");

        try {

            cards[0] = objectMapper.readValue(file[0],Cards.class);cards[0].setCard();
            cards[1] = objectMapper.readValue(file[1],Cards.class);cards[1].setCard();
            cards[2] = objectMapper.readValue(file[2],Cards.class);cards[2].setCard();
            cards[3] = objectMapper.readValue(file[3],Cards.class);cards[3].setCard();
            cards[4] = objectMapper.readValue(file[4],Cards.class);cards[4].setCard();
            cards[5] = objectMapper.readValue(file[5],Cards.class);cards[5].setCard();
            cards[6] = objectMapper.readValue(file[6],Cards.class);cards[6].setCard();
            cards[7] = objectMapper.readValue(file[7],Cards.class);cards[7].setCard();
            cards[8] = objectMapper.readValue(file[8],Cards.class);cards[8].setCard();
            cards[9] = objectMapper.readValue(file[9],Cards.class);cards[9].setCard();
            cards[10] = objectMapper.readValue(file[10],Cards.class);cards[10].setCard();
            cards[11] = objectMapper.readValue(file[11],Cards.class);cards[11].setCard();
            cards[12] = objectMapper.readValue(file[12],Cards.class);cards[12].setCard();
            cards[13] = objectMapper.readValue(file[13],Cards.class);cards[13].setCard();
            cards[14] = objectMapper.readValue(file[14],Cards.class);cards[14].setCard();
            cards[15] = objectMapper.readValue(file[15],Cards.class);cards[15].setCard();
            cards[16] = objectMapper.readValue(file[16],Cards.class);cards[16].setCard();
            cards[17] = objectMapper.readValue(file[17],Cards.class);cards[17].setCard();
            cards[18] = objectMapper.readValue(file[18],Cards.class);cards[18].setCard();
            cards[19] = objectMapper.readValue(file[19],Cards.class);cards[19].setCard();

           // System.out.println("Minions "+allMinion.size());
           // System.out.println("Spell "+allSpell.size());
           // System.out.println("Weapons "+allWeapon.size());

        }
        catch (Exception e){
            System.out.println("Card problem!");
        }

    }

    public static Cards createCard(String Name){
        Cards answer = null;
        try {
            for (int i = 0; i < 20; i++) {
                if(cards[i].Name.equalsIgnoreCase(Name)){
                    answer = new Cards(Name,cards[i].Mana,cards[i].rarity,cards[i].CardClass,cards[i].type,cards[i].Description);
                    answer.cost=cards[i].cost;
                    answer.HP=cards[i].HP;
                    answer.Durability=cards[i].Durability;
                    answer.attack=cards[i].attack;
                }
            }
            return answer;
        }
        catch (Exception e){
            System.out.println("There is no card with that name"+Name);
            return null;
        }

    }

    public static ArrayList<Cards> createCard(ArrayList<String> cardname){
        ArrayList<Cards> answer= new ArrayList();
        for (String cn: cardname ) {
            answer.add(createCard(cn));
        }
        return answer;
    }



}
