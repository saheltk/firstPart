package Player;

import Cards.Cards;
import Heroes.Heroes;
import Heroes.HeroName;


import Log.Logs;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Player {
    public String UserName;
    public String Password;
    public int Gems;

    public Heroes CurrentHero;
    public ArrayList<Cards> CardsArrayList = new ArrayList<>();
    public ArrayList<Heroes> AvailableHeroes = new ArrayList<>();
    public ArrayList<Cards> MyCardsArrayList = new ArrayList<>();



    static int number=0;
    public Logs log ;
    public  String CreationTime;
    static FileReader [] file= new FileReader[100];
    static Player[] player= new Player[100];



    public static Timestamp  setTime(){
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }
    static HeroName stringToHero(String a){
        if (a.equals("Mage"))return HeroName.Mage;
        if (a.equals("Rogue"))return HeroName.Rogue;
        if (a.equals("Warlock"))return HeroName.Warlock;
return null;
    }

     Player(){}
    public Player(String UserName, String Password,int Gems,Heroes Heroe,ArrayList<Cards> cards,ArrayList<Heroes> heroNames, Timestamp creationTime,ArrayList<Cards> MycardNames ) throws IOException {
        this.UserName=UserName;
        this.Password=Password;
        this.Gems=Gems;

        CurrentHero=Heroe;
        CreationTime=string(creationTime);
        MyCardsArrayList=MycardNames;
        CardsArrayList=cards;
        AvailableHeroes=heroNames;

        Logs.usernames.add(UserName);
        PlayersFactory.AllPlayers.add(this);

    }

    private String string(Timestamp creationTime) {
        String ans =creationTime.toString();
        return ans;
    }



    public void removeCard(Cards newCard) throws IOException {
        CardsArrayList.remove(newCard);
        update();
        log.Write("sell card : ",newCard.Name);

    }
    public void addCard(Cards newCard) throws IOException {
        CardsArrayList.add(newCard);
        update();
        log.Write("buy card : ",newCard.Name);
    }

    public void setGems(int gems) {
        Gems = gems;
    }
    public void update(){
    try {

       FileWriter fileWriter = new FileWriter("src\\Player\\users\\ID" + (number) + ".json");
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.writeValue(fileWriter,this);
       fileWriter.close();

    }
    catch (Exception e){
        System.out.println("user creating problem!");
    }


}

    public ArrayList stringToArray(String a){
        ArrayList<String> answer= new ArrayList<>();
        int r= a.length();
        a=a.substring(1,r);
        while (a.contains(",")){
            answer.add(a.substring(0,a.indexOf(",")).trim());
            a=a.substring(a.indexOf(",")+1);
        }
        return answer;
    }
    public static void setUp() throws IOException {
        boolean flag=true;
        int number=0;
        ObjectMapper objectMapper = new ObjectMapper();
       try {
            while (flag) {
                String id = "src\\Player\\users\\ID" + (number) + ".json";
                file[number]= new FileReader(id);
                player[number] = objectMapper.readValue(file[number],Player.class);
                Logs.usernames.add(player[number].UserName);
                PlayersFactory.AllPlayers.add(player[number]);
                player[number].log=Logs.setLog( player[number]);
                number++;
            }
       }
      catch (Exception e){
                flag=false;
                return;
      }
}

    public void AddMyCard(Cards newCard) throws IOException {

        MyCardsArrayList.add(newCard);
        update();
        log.Write("add to deck",newCard.Name);

    }
    public void RemoveMyCard(Cards newCard) throws IOException {
        MyCardsArrayList.remove(newCard);
        update();
        log.Write("remove from deck",newCard.Name);

    }
    public void delete() throws IOException {
        update();
        log.Delete();
        ObjectMapper objectMapper= new ObjectMapper();
        FileWriter fileWriter = new FileWriter("src\\Player\\users\\deleted\\ID" + (number) + ".json");
        objectMapper.writeValue(fileWriter,this);
        fileWriter.close();
        PlayersFactory.AllPlayers.remove(this);
        FileWriter fileWriter1 = new FileWriter("src\\Player\\users\\ID"+ number+".json");
        fileWriter1.write("");

    }

    public static void main(String[] args) throws IOException {
        setUp();
    }
}
