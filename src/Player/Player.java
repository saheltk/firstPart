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
import java.util.zip.DataFormatException;

public class Player {
    private String username;
    private String password;
    private int gems;
    private int id;
    //Hero
    private Heroes currentHero;
    private ArrayList<Heroes> availableHeroes = new ArrayList<>();
    //Cards
    private ArrayList<Cards> myCards = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private Deck[] topDecks = new Deck[10];
    private Deck chosenDeck;
    //game
    private int mana;

    private static int number = 0;
    private Logs log;
    private String creationTime;
    private static FileReader[] file = new FileReader[Constants.Players];
    private static Player[] player = new Player[Constants.Players];

    Player() {
    }

    public static Player createPlayer(String username, String password) {
        if (player[number]!=null)
        number++;
        System.out.println("create"+number);
        player[number] = new Player();
        player[number].id=number;
        player[number].username = username;
        player[number].password = password;
        player[number].log = new Logs();
        player[number].log = new Logs(player[number]);
        player[number].creationTime = setTime().toString();
        player[number].gems = (50);
        PlayersFactory.add(player[number]);
        player[number].update();
        return player[number];

    }

    public Player(String username, String password, int gems, Heroes Heroe, ArrayList<Cards> cards, ArrayList<Heroes> heroNames, Timestamp creationTime, ArrayList<Cards> MycardNames) throws IOException {
        this.username = username;
        this.password = password;
        this.gems = gems;
        this.creationTime = creationTime.toString();


        currentHero = Heroe;
        myCards = MycardNames;
        myCards = cards;
        availableHeroes = heroNames;

        log = Logs.setLog(this);
        PlayersFactory.add(this);
        number++;
        id = number;
        player[number] = this;
    }


    //Getter
    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public int getGems() {
        return gems;
    }

    public Heroes getCurrentHero() {
        return currentHero;
    }

    public ArrayList<Heroes> getAvailableHeroes() {
        return availableHeroes;
    }

    public ArrayList<Cards> getMyCards() {
        return myCards;
    }

    public ArrayList<Deck> getDeck() {
        return decks;
    }

    public Deck[] getTopDecks() {
        return topDecks;
    }

    public int getMana() {
        return mana;
    }

    public Deck getChosenDeck() {
        return chosenDeck;
    }


    //Setter
    public void setGems(int gems) {
        this.gems = gems;
    }

    public static Timestamp setTime() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }

    public void setCurrentHero(Heroes hero){
        currentHero =hero;
        availableHeroes.add(hero);
        update();
    }

    public void setMana(int mana) {
        this.mana=mana;
    }

    public void setChosenDeck(Deck chosenDeck) {
        this.chosenDeck=chosenDeck;
    }



    //AllCardsCotrol
    public void removeCard(Cards newCard) {
        myCards.remove(newCard);
        update();
        log.write(this, "sell card : ", newCard.getName());

    }

    public void addCard(Cards newCard) {
        myCards.add(newCard);
        update();
        log.write(this, "buy card : ", newCard.getName());
    }

    public ArrayList<Cards> despiteDeck(Deck deck) {
        ArrayList<Cards> answer = new ArrayList<Cards>();
        ArrayList<Cards> myArray = new ArrayList<Cards>();

        for (Cards card1: myCards) {
           int count=0;
            for (Cards card2:deck.getAllCards()){
                if (card1!=null && card2!=null)
                if (card1.getName().equals(card2.getName())) count++;
            }
            if (count<2){
                answer.add(card1);
            }

        }
        return answer;
    }


    public ArrayList<Cards> buyAvailable() {
        ArrayList<Cards> answer = new ArrayList<Cards>();

        for (int i = 0; i < Constants.cardNumbers; i++) {
            int count = 0;
            for (Cards cards : myCards) {
                if (Cards.cards[i].getName().equals(cards.getName()))
                count++;
            }
            if (count<2) answer.add(Cards.cards[i]);


        }
        return answer;
    }

    public ArrayList<Cards> despiteMyCards() {
        ArrayList<Cards> answer = new ArrayList<Cards>();
        ArrayList<Cards> myArray = new ArrayList<Cards>();
        myArray.addAll(myCards);

        for (int i = 0; i < Constants.cardNumbers; i++) {
            boolean flag =true;
            for (Cards cards:myCards){
                if (cards.getName().equals(Cards.cards[i].getName())) flag=false;
            }
            if (flag){
                answer.add(Cards.cards[i]);
                //System.out.println(Cards.cards[i].getName());
            }

        }
        return answer;
    }

    public void update() {
        try {
            FileWriter fileWriter = new FileWriter("src\\Player\\users\\ID" + (this.getId()) + ".json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(fileWriter, this);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("user creating problem!");
        }


    }

    public static void setPlayers() {
        boolean flag = true;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            while (flag) {
                System.out.println(number);
                String id = "src\\Player\\users\\ID" + (number) + ".json";
                System.out.println("ok");
                file[number] = new FileReader(id);
                System.out.println("ok");

                player[number] = objectMapper.readValue(file[number], Player.class);
                System.out.println("ok");

                player[number].log = Logs.setLog(player[number]);
                LogFactory.add(player[number]);
                PlayersFactory.add(player[number]);
                // System.out.println(player[number].getUsername());
                player[number].log = Logs.setLog(player[number]);
                System.out.println(number+"         "+player[number].getUsername());

                number++;
            }
        } catch (Exception e) {
            flag = false;
            return;
        }
    }

    public void sortTopDesks() {
        if (topDecks == null) return;
        Deck[] answer = new Deck[10];
        ArrayList<Deck> all = new ArrayList<Deck>();
        for (int i = 0; i < 10; i++) {
            all.add(topDecks[i]);
        }
        for (int i = 0; i < 10; i++) {
            answer[i] = bestDeck(all);
            all.remove(answer[i]);
        }
        topDecks = answer;
    }

    private static Deck betterDeck(Deck deck1, Deck deck2) {
        if (deck1 == null && deck2 == null) return null;
        if (deck1 == null) return deck2;
        if (deck2 == null) return deck1;

        if (deck1.getWinsToTotal() > deck2.getWinsToTotal()) return deck1;
        if (deck1.getWinsToTotal() < deck2.getWinsToTotal()) return deck2;

        if (deck1.getWins() > deck2.getWins()) return deck1;
        if (deck1.getWins() < deck2.getWins()) return deck2;

        if (deck1.getGames() > deck2.getGames()) return deck1;
        if (deck1.getGames() < deck2.getGames()) return deck2;

        if (deck1.getCost() > deck2.getCost()) return deck1;
        if (deck1.getCost() < deck2.getCost()) return deck2;

        return deck1;
    }

    private static Deck bestDeck(ArrayList<Deck> decks) {
        Deck answer = decks.get(0);
        for (Deck deck : decks) {
            answer = betterDeck(answer, deck);
        }
        return answer;
    }

    public void addDeck(Deck newDeck){
        decks.add(newDeck);
        update();
    }

    public void delete() {
        update();
        log.delete();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter fileWriter = new FileWriter("src\\Player\\users\\deleted\\ID" + (number) + ".json");
            objectMapper.writeValue(fileWriter, this);
            fileWriter.close();
            PlayersFactory.remove(this);
            FileWriter fileWriter1 = new FileWriter("src\\Player\\users\\ID" + number + ".json");
            fileWriter1.write("");

        } catch (Exception e) {
        }

    }

    public void deleteDeck(Deck chosenDeck) {
        if (chosenDeck == this.chosenDeck ) {
            chosenDeck=null;
        }
        for (int i = 0; i < 10; i++) {
            if (topDecks[i]==chosenDeck)
                topDecks[i]=null;
        }
        decks.remove(chosenDeck);
        update();
    }
}


