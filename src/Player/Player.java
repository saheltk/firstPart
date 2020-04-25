package Player;

import Cards.Cards;
import Cards.Deck;

import Constants.Constants;
import Heroes.Heroes;
import Heroes.HeroName;
import Log.LogFactory;
import Log.Logs;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Player {
    private String username;
    private String password;
    private int gems;
    //Hero
    private Heroes currentHero;
    private ArrayList<Heroes> availableHeroes = new ArrayList<>();
    //Cards
    private ArrayList<Cards> myCards = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    
    private static int number = 0;
    private Logs log;
    private String creationTime;
    private static FileReader[] file = new FileReader[Constants.Players];
    private static Player[] player = new Player[Constants.Players];

    Player() { }
    public Player(String username, String password, int gems, Heroes Heroe, ArrayList<Cards> cards, ArrayList<Heroes> heroNames, Timestamp creationTime, ArrayList<Cards> MycardNames) throws IOException {
        this.username = username;
        this.password = password;
        this.gems = gems;
        this.creationTime = toString(creationTime);

        currentHero = Heroe;
        myCards = MycardNames;
        myCards = cards;
        availableHeroes = heroNames;

        log=Logs.setLog(this);
        PlayersFactory.add(this);
    }

    //Getter
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCreationTime() { return creationTime; }
    public int getGems() { return gems; }
    public Heroes getCurrentHero() { return currentHero; }
    public ArrayList<Heroes> getAvailableHeroes() { return availableHeroes; }
    public ArrayList<Cards> getMyCards() { return myCards; }
    public ArrayList<Deck> getDeck() { return decks; }
    //Setter
    public void setGems(int gems) { gems = gems; }
    public static Timestamp setTime() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }

    private String toString(Timestamp creationTime) {
        String ans = creationTime.toString();
        return ans;
    }

    //AllCardsCotrol
    public void removeCard(Cards newCard) throws IOException {
        myCards.remove(newCard);
        update();
        log.Write("sell card : ", newCard.getName());

    }
    public void addCard(Cards newCard) throws IOException {
        myCards.add(newCard);
        update();
        log.Write("buy card : ", newCard.getName());
    }


    public void update() {
        try {
            FileWriter fileWriter = new FileWriter("src\\Player\\users\\ID" + (number) + ".json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(fileWriter, this);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("user creating problem!");
        }


    }

    public ArrayList stringToArray(String a) {
        ArrayList<String> answer = new ArrayList<>();
        int r = a.length();
        a = a.substring(1, r);
        while (a.contains(",")) {
            answer.add(a.substring(0, a.indexOf(",")).trim());
            a = a.substring(a.indexOf(",") + 1);
        }
        return answer;
    }

    public static void setUp() {
        boolean flag = true;
        int number = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            while (flag) {
                String id = "src\\Player\\users\\ID" + (number) + ".json";
                file[number] = new FileReader(id);
                player[number] = objectMapper.readValue(file[number], Player.class);
                LogFactory.add(player[number].log);
                PlayersFactory.add(player[number]);
                player[number].log = Logs.setLog(player[number]);
                number++;
            }
        } catch (Exception e) {
            flag = false;
            return;
        }
    }

    public void AddMyCard(Cards newCard) throws IOException {
        myCards.add(newCard);
        update();
        log.Write("add to deck", newCard.getName());

    }

    public void RemoveMyCard(Cards newCard) throws IOException {
        myCards.remove(newCard);
        update();
        log.Write("remove from deck", newCard.getName());
    }
    
    public void delete() throws IOException {
        update();
        log.Delete();
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter("src\\Player\\users\\deleted\\ID" + (number) + ".json");
        objectMapper.writeValue(fileWriter, this);
        fileWriter.close();
        PlayersFactory.remove(this);
        FileWriter fileWriter1 = new FileWriter("src\\Player\\users\\ID" + number + ".json");
        fileWriter1.write("");

    }

}
