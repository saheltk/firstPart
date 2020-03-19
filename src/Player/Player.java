package Player;

import Cards.Cards;
import Heroes.Heroes;
import Heroes.HeroName;


import Log.Logs;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

import javax.print.DocFlavor;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Player {
    public String UserName;
    public String Password;
    public int Gems;
    public Heroes CurrentHero;
    public HeroName CurrentHeroName;
    public String CurrentHeroNameST;


    public ArrayList<Cards> CardsArrayList = new ArrayList<>();
    public ArrayList<String> CardNamesArrayList = new ArrayList<>();

    public ArrayList<String> AvailableHeroesNamesST = new ArrayList<>();
    public ArrayList<HeroName> AvailableHeroesNames = new ArrayList<>();
    public ArrayList<Heroes> AvailableHeroes = new ArrayList<>();

    public ArrayList<Cards> MyCardsArrayList = new ArrayList<>();
    public ArrayList<String> MyCardNamesArrayList = new ArrayList<>();


    static int number=0;
    public Logs log ;
    public  String CreationTime;

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

    public Player(String UserName, String Password,int Gems,String HeroNameST,ArrayList<String> cardNames,ArrayList<String> heroNames, Timestamp creationTime,ArrayList<String> MycardNames ) throws IOException {
        this.UserName=UserName;
        this.Password=Password;
        this.Gems=Gems;

        CurrentHeroNameST=HeroNameST;
        CurrentHeroName=stringToHero(HeroNameST);
        CurrentHero=new Heroes(CurrentHeroName);

        CreationTime=string(creationTime);

        MyCardNamesArrayList=MycardNames;
        MyCardsArrayList=Cards.createCard(MyCardNamesArrayList);


        CardNamesArrayList=cardNames;
        CardsArrayList=Cards.createCard(CardNamesArrayList);

        AvailableHeroesNamesST=heroNames;
        for (String a : AvailableHeroesNamesST) {
            AvailableHeroesNames.add(stringToHero(a));
        }
        try {
            for (HeroName hn: AvailableHeroesNames ) {
                AvailableHeroes.add(new Heroes(hn));
            }

        }
        catch (Exception  e){
            System.out.println("There is no Hero with that name ");

        }
        number++;


    }

    private String string(Timestamp creationTime) {
        String ans =creationTime.toString();
        return ans;
    }

    public String getUserName() {
        return UserName;
    }
    public String getPassword() { return Password;}
    public int getGems() {
        return Gems;
    }
    public Heroes getCurrrentHero() {
        return CurrentHero;
    }
    public ArrayList<Cards> getCardsArrayList(){ return CardsArrayList;}
    public ArrayList<Heroes> getAvailableHeroes(){ return AvailableHeroes;}

    public void removeCard(Cards newCard) throws IOException {
        CardsArrayList.remove(newCard);
        CardNamesArrayList.remove(newCard.Name);
        update();
        log.Write("sell cards",newCard.Name);

    }
    public void addCard(Cards newCard) throws IOException {
        CardsArrayList.add(newCard);
        CardNamesArrayList.add(newCard.Name);
        update();
        log.Write("buy cards",newCard.Name);
    }
    public void addHero(Heroes newHero){
        AvailableHeroes.add(newHero);
    }
    public void setCurrrentHero(Heroes newHero) { CurrentHero=newHero; }
    public void setGems(int gems) {
        Gems = gems;
    }

    public void update(){

    String id= "src\\Player\\users\\ID"+ number+".json";
    try {

        FileWriter fileWriter = new FileWriter(id);
        fileWriter.write("{\n");
        fileWriter.write("\"UserName\": \""+UserName+"\",\n");
        fileWriter.write("\"Password\": \""+Password+"\",\n");
        fileWriter.write("\"CreationTime\": \""+CreationTime+"\",\n");
        fileWriter.write("\"Gems\": \""+Gems+"\",\n");
        fileWriter.write("\"CurrentHeroNameST\": \""+CurrentHeroNameST+"\",\n");
        fileWriter.write("\"CardNamesArrayList\": \""+CardNamesArrayList+"\",\n");
        fileWriter.write("\"AvailableHeroesNamesST\": \""+AvailableHeroesNamesST+"\",\n");
        fileWriter.write("\"MyCardNamesArrayList\": \""+MyCardNamesArrayList+"\",\n");
        fileWriter.write("\"Log\": \""+log+"\"\n");

        fileWriter.write("}");
        fileWriter.flush();
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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            while (flag) {
                String id = "src\\Player\\users\\ID" + (number + 1) + ".json";
                FileReader fileReader = new FileReader(id);
                player[number] = objectMapper.readValue(fileReader, Player.class);
                player[number].CardsArrayList = Cards.createCard(player[number].CardNamesArrayList);
                ArrayList<Heroes> availheroes = new ArrayList();
                try {

                    for (HeroName hn : player[number].AvailableHeroesNames) {
                        availheroes.add(new Heroes(hn));
                    }
                    player[number].AvailableHeroes = availheroes;
                } catch (Exception e) {
                    System.out.println("There is no Hero with that name ");

                }
                Logs.usernames.add(player[number].UserName);
                PlayersFactory.AllPlayers.add(player[number]);


                number++;
            }
       }
        catch (Exception e){
                flag=false;
                return;
        }
}

    public void AddMyCard(Cards newCard) throws IOException {
        MyCardNamesArrayList.add(newCard.Name);
        MyCardsArrayList.add(newCard);
        update();
        log.Write("add to deck",newCard.Name);

    }
    public void RemoveMyCard(Cards newCard) throws IOException {
        MyCardNamesArrayList.remove(newCard.Name);
        MyCardsArrayList.remove(newCard);
        update();
        log.Write("remove from deck",newCard.Name);

    }
    public void delete() throws IOException {
        update();
        log.Delete();
        ObjectMapper objectMapper= new ObjectMapper();

        String id= "src\\Player\\users\\deleted\\ID"+ number+".json";
        try {
            FileWriter fileWriter = new FileWriter(id);
            fileWriter.write("{\n");
            fileWriter.write("\"UserName\": \""+UserName+"\",\n");
            fileWriter.write("\"Password\": \""+Password+"\",\n");
            fileWriter.write("\"Created at\": \""+CreationTime+"\",\n");
            fileWriter.write("\"Gems\": \""+Gems+"\",\n");
            fileWriter.write("\"CurrentHero\": \""+CurrentHeroName+"\",\n");
            fileWriter.write("\"Available CardsArrayList\": \""+CardNamesArrayList+"\",\n");
            fileWriter.write("\"AvailableHeroes\": \""+AvailableHeroesNames+"\"\n");
            fileWriter.write("\"My CardsArrayList\": \""+MyCardNamesArrayList+"\",\n");
            fileWriter.write("\"Log\": \""+log+"\"\n");


            fileWriter.write("}");
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("user deleting problem!");
        }
        PlayersFactory.AllPlayers.remove(player[number]);
        FileWriter fileWriter = new FileWriter("src\\Player\\users\\ID"+ number+".json");
        fileWriter.write("");

    }

    public static void main(String[] args) throws IOException {
        setUp();
    }
}
