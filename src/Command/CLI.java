package Command;

import Cards.Cards;
import Cards.Type;
import Heroes.HeroName;
import Heroes.Heroes;
import Log.ConsoleColors;
import Log.Logs;
import Log.Shop;
import Player.Player;
import Player.PlayersFactory;

import java.io.Console;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CLI {

    public static void main(String[] args) throws IOException {
        Player.setUp();
        Cards.CLI();
        Scanner s = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE_BOLD + " Welcome to ");
        System.out.println("            \u2694 HEARTHSTONE \u2694 \n");
        System.out.println(ConsoleColors.GREEN +"Help:");
        System.out.println("You can write down the number for choosing that option! ");
        System.out.println("You can also write down \"0\" for exit-a \n");

        Player player=null;
        player=Sign();
        Menu(player);

    }
    static Player Sign() throws IOException {
        Player player=null;
        System.out.println(ConsoleColors.BLUE_BRIGHT+"     Account: "+ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED+"0-Exit "+ConsoleColors.RESET);
        System.out.println("1-SignIn ");
        System.out.println("2-SignUp ");


        switch (getAnswer(2)){
            case 1:
                player= SignIn();
                break;
            case 2:
                player= SignUp();
                break;
            case 0:
                Runtime.getRuntime().exit(1);
                break;
        }
        return player;
    }
   static int getAnswer(int a){
        int answer=0;
        try {
            Scanner m = new Scanner(System.in);
             answer=m.nextInt();
        }
        catch (Exception e){
            System.out.println("Please Enter number");
            getAnswer(a);
        }
        if (answer<0 || answer>a){
            System.out.println("Please Enter integer between 0 and "+a);
            getAnswer(a);}
        return answer;
    }

    static String getAnswer(String a) throws IOException {
        String answer="";
        try {
            Scanner m = new Scanner(System.in);
            answer=m.next();
        }
        catch (Exception e){
            System.out.println("Invalid "+a);
            getAnswer(a);
        }
        if (answer.equalsIgnoreCase("exit"))Sign();

        if (a.equalsIgnoreCase("Username")) {
            if (!Logs.usernames.contains(answer)) {
                System.out.println(a + " Not Found");
                getAnswer(a);
            }
        }
        if (a.equalsIgnoreCase("new Username")) {
            if (answer.trim().equals("")) {
                System.out.println("Username must contains numbers or Alphabet characters");
                getAnswer(a);
            }
        }
        return answer;
    }

    static Player SignIn() throws IOException {
        System.out.println(ConsoleColors.GREEN+"Help:\nIf you want to exit you can type \"exit\" instead"+ConsoleColors.RESET);
        System.out.println("Username: ");
        String username =getAnswer("Username");
        System.out.println("Password: ");
        String password =getAnswer("Password");
try {
    while (!Logs.PassCheck(username,password))
        password =getAnswer("Password");
}
        catch (Exception e){
            System.out.println("Problem !  ");Sign();
        }
        Player player=PlayersFactory.find(username);
        player.log.SignIn(player);
        System.out.println("\n"+ConsoleColors.PURPLE_BRIGHT+"Welcome Back "+username+ConsoleColors.RESET+"\n");
        return PlayersFactory.find(username);
    }

    static Player SignUp() throws IOException {
        System.out.println(ConsoleColors.GREEN+"if you want to exit-a you can type \"exit\" instead"+ConsoleColors.RESET);
        System.out.println("Username: ");
        String username =getAnswer(" new Username");
        System.out.println("Password: ");
        String password =getAnswer("Password");

        Timestamp creationTime = Player.setTime();
        System.out.println("");
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"Welcome "+username+ConsoleColors.RESET+"\n");
        System.out.print("Your first Hero is: ");
        HeroName heroName=null;
        String heroNameST=null;
        Random random = new Random();
        int HeroInt= random.nextInt(3);
        switch (HeroInt){
            case 0:
                heroName=HeroName.Warlock;heroNameST="Warlock";break;
            case 1:
                heroName=HeroName.Mage;heroNameST="Mage";break;
            case 2:
                heroName=HeroName.Rogue;heroNameST="Rogue";break;

        }
        System.out.println(ConsoleColors.BLUE_BOLD+heroName+ConsoleColors.RESET);
        System.out.println("Available cards are: \n");
        Heroes Hero = new Heroes(heroName);
        ArrayList<Cards> heroes = Hero.setCards();

        ArrayList<String> cardNames =new ArrayList<>();
        for (int i = 0; i <heroes.size() ; i++) {
            cardNames.add(heroes.get(i).Name);
            System.out.println("\u2606"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+heroes.get(i).Name);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+heroes.get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+heroes.get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+heroes.get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+heroes.get(i).type);
            if (!heroes.get(i).type.equals(Type.Spell))
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+heroes.get(i).attack);
            if (heroes.get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+heroes.get(i).Durability);
            if (heroes.get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+heroes.get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+heroes.get(i).Description);
            System.out.println("");

        }
        ArrayList<Heroes> heroNameArrayList= new ArrayList<>();
        heroNameArrayList.add(Hero);

        ArrayList<Cards> mycards= new ArrayList<>();
//Log
        Player player= new Player(username,password,50,Hero,heroes,heroNameArrayList,Player.setTime(), mycards);
        player.update();
        player.log = new Logs(player);
        Logs.usernames.add(username);
        PlayersFactory.AllPlayers.add(player);
        return player;
    }

    static void collections(Player player) throws IOException {

        System.out.println(ConsoleColors.BLUE_BRIGHT+"     Card Collections: ");
        System.out.println(ConsoleColors.RED+"0-Exit-a  ");
        System.out.println(ConsoleColors.RESET+"1-Available Cards ");
        System.out.println("2-My Cards  ");
        System.out.println("3-Not My Cards ");
        System.out.println("3-Help ");

        switch (getAnswer(4)){
            case 0:
                Menu(player);
                return;
            case 1:
                collectionsAcards(player);
                break;
            case 2:
                collectionsMcards(player);
                break;
            case 3:
                collectionsNcards(player);
                break;
            case 4:
                System.out.println(ConsoleColors.GREEN+"Help:"+ConsoleColors.RESET);
                System.out.println("CollectionAcards: all of your cards");
                System.out.println("CollectionMcards: cards on your desk");
                System.out.println("CollectionNcards: cards that are not on your desk");
                Menu(player);

        }
        collections(player);
    }
    static void collectionsAcards(Player player) throws IOException {
        player.log.Write("list","cards:All");
        ArrayList<Cards> heroes = player.CardsArrayList;
        System.out.println("You have "+ConsoleColors.GREEN+ heroes.size()+ConsoleColors.RESET+" available cards");
        System.out.println(ConsoleColors.BLUE_BRIGHT+ heroes.size()+ "Cards"+ConsoleColors.RESET);

        for (int i = 0; i <heroes.size() ; i++) {
            System.out.println("\u2606 \n"+(i+1)+")"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+heroes.get(i).Name);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+heroes.get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+heroes.get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+heroes.get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+heroes.get(i).type);
            if (!heroes.get(i).type.equals(Type.Spell))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+heroes.get(i).attack);
            if (heroes.get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+heroes.get(i).Durability);
            if (heroes.get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+heroes.get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+heroes.get(i).Description);
            System.out.println("");

        }
    }
    static void collectionsMcards(Player player) throws IOException {
        player.log.Write("list","cards:My cards");

        ArrayList<Cards> heroes = player.MyCardsArrayList;
        System.out.println("You have "+ConsoleColors.GREEN+ heroes.size()+ConsoleColors.RESET+" cards in your hand");
        System.out.println(ConsoleColors.BLUE_BRIGHT+ heroes.size()+ "Cards"+ConsoleColors.RESET);
        for (int i = 0; i <heroes.size() ; i++) {
            System.out.println("\u2606 \n"+(i+1)+")"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+heroes.get(i).Name);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+heroes.get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+heroes.get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+heroes.get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+heroes.get(i).type);
            if (!heroes.get(i).type.equals(Type.Spell))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+heroes.get(i).attack);
            if (heroes.get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+heroes.get(i).Durability);
            if (heroes.get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+heroes.get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+heroes.get(i).Description);
            System.out.println("");


        }

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+" add/remove card to/from your \"My Cards\" collection?");
        System.out.println(ConsoleColors.RED+"0-Exit-a  ");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+"1-Add ");
        System.out.println("2-remove "+ConsoleColors.RESET);


        switch (getAnswer(4)){
            case 0:
                Menu(player);
                return;
            case 1:
                if (player.MyCardsArrayList.size()==15){
                    System.out.println("You have 15 cards, so you can not add any other cart");
                    break;
                }
                collectionsNcards(player);
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+" Which one do you want to add?( 0 for Exit-a)");
                int ans;
                ans=getAnswer(collectionsforNcards(player).size());
                if (ans==0) Menu(player);
                player.RemoveMyCard(player.MyCardsArrayList.get(ans-1));
                break;
            case 2:
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+" Which one do you want to remove?( 0 for Exit-a)");
                ans=getAnswer(player.MyCardsArrayList.size());
                if (ans==0) Menu(player);
                player.AddMyCard(player.MyCardsArrayList.get(ans-1));
                break;
        }
        collections(player);
    }
    static void collectionsNcards(Player player) throws IOException {
        player.log.Write("list","cards:Not in deck");

        ArrayList<Cards> heroes = collectionsforNcards(player);
        System.out.println("You have "+ConsoleColors.GREEN+ heroes.size()+ConsoleColors.RESET+"  available cards NOT in your hand");
        System.out.println(ConsoleColors.BLUE_BRIGHT+ heroes.size()+ "Cards"+ConsoleColors.RESET);
        for (int i = 0; i <heroes.size() ; i++) {
            System.out.println("\u2606 \n"+(i+1)+")"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+heroes.get(i).Name);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+heroes.get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+heroes.get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+heroes.get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+heroes.get(i).type);
            if (!heroes.get(i).type.equals(Type.Spell))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+heroes.get(i).attack);
            if (heroes.get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+heroes.get(i).Durability);
            if (heroes.get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+heroes.get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+heroes.get(i).Description);
            System.out.println("");


        }
    }
    static ArrayList<Cards> collectionsforNcards(Player player){
        ArrayList<Cards> answer = new ArrayList();
        answer.addAll(player.CardsArrayList);
        answer.removeAll(player.MyCardsArrayList);
        return answer;
    }


    static void Menu(Player player) throws IOException {
    System.out.println(ConsoleColors.BLUE_BRIGHT+"     Menu: ");
    System.out.println(ConsoleColors.RED+"0-Exit and SignOut  ");
    System.out.println(ConsoleColors.RESET+"1-Collections ");
    System.out.println("2-Store ");
        System.out.println("3-Hero ");
        System.out.println("4-Wallet ");
        System.out.println("5-Delete account ");
        System.out.println("6-Help \n\n");

        switch (getAnswer(6)){
        case 0:
            player.log.Write("LogOut","");
            Sign();
        case 1:
            collections(player);
            break;
        case 2:
            Store(player);
            break;
        case 3:
            Hero(player);
            break;
        case 4:
            System.out.println(ConsoleColors.BLUE_BRIGHT+"     Wallet: "+ConsoleColors.RESET);
            System.out.println("You have "+ ConsoleColors.RED+player.Gems +ConsoleColors.RESET+" Gems!\n");
            Menu(player);
            break;
        case 5:
            Delete(player);
            Sign();
            break;
        case 6:
            System.out.println(ConsoleColors.GREEN+"Help:"+ConsoleColors.GREEN_BOLD);
            System.out.println("Collection : you can add cards to you deck or remove from that");
            System.out.println("Store: you can buy or sell cards with your gems");
            System.out.println("Hero: you can add new Hero or change the current Hero");
            System.out.println("Wallet: you can see how many gems you have,at first you have 50 gems ");
            System.out.println("Delete: you can delete your account :( \n"+ConsoleColors.RESET);
            Menu(player);
            break;
     }
    }
    static void Store(Player player) throws IOException {
        System.out.println(ConsoleColors.GREEN+"     $ STORE $");
        System.out.println(ConsoleColors.RESET+"Buy or Sell?");
        System.out.println(ConsoleColors.RED+"0-Exit "+ConsoleColors.RESET);
        System.out.println("1-Wallet ");
        System.out.println("2-Buy ");
        System.out.println("3-Sell ");
        System.out.println("4-Help ");
        switch (getAnswer(4)){
            case 0:
                Menu(player);
                return;
            case 1:
                System.out.println(ConsoleColors.BLUE_BRIGHT+"     Wallet: "+ConsoleColors.RESET);
                System.out.println("You have "+ ConsoleColors.RED+player.Gems +ConsoleColors.RESET+" Gems!\n");
                Menu(player);
                break;
            case 2:
                Buy(player);
                break;
            case 3:
                Sell(player);
                break;
            case 4:
                System.out.println(ConsoleColors.GREEN+"Help:"+ConsoleColors.RESET);
                System.out.println("Wallet: you can see how many gems you have,at first you have 50 gems ");
                System.out.println("Buy: you can buy cards that you don't have ");
                System.out.println("Sell :you can sell you cards to earn gems instead");
                Store(player);
                break;
        }
        Store(player);
    }
    public static void Buy(Player player) throws IOException {
        System.out.println("Available cards for buy");

        for (int i = 0; i <collectionsforNcards(player).size(); i++) {
            System.out.println("\u2606 \n"+(i+1)+")"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).Name);
            System.out.println(ConsoleColors.GREEN+"Cost: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).cost);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).type);
            if (!collectionsforNcards(player).get(i).type.equals(Type.Spell))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).attack);
            if (collectionsforNcards(player).get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).Durability);
            if (collectionsforNcards(player).get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+collectionsforNcards(player).get(i).Description);
            System.out.println("");
        }
        System.out.println("Which one do you want to buy?");
        int ans =getAnswer(collectionsforNcards(player).size());
        if (ans==0) Menu(player);
        if (player.MyCardsArrayList.contains(collectionsforNcards(player).get(ans-1).Name)){
            System.out.println("You have that one !");
        }
        Shop.Buy(player,Cards.allCards.get(ans-1));
    }
    public static void Sell(Player player) throws IOException {
        System.out.println("Available cards for buy");
        ArrayList<String> names = new ArrayList<>();
        for (Heroes heroes:player.AvailableHeroes   ) {
            for (Cards c:heroes.cardsArrayList){
                names.add(c.Name);
            }
        }
        int j=0;
        for (int i = 0; i <Cards.allCards.size(); i++) {
     while (names.contains(Cards.allCards.get(i))){
         i++;
     }
     j++;
            System.out.println("\u2606 \n"+(j)+")"+ConsoleColors.PURPLE_BRIGHT+"Name: "+ConsoleColors.RESET+Cards.allCards.get(i).Name);
            System.out.println(ConsoleColors.GREEN+"Cost: "+ConsoleColors.RESET+Cards.allCards.get(i).cost);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Mana: "+ConsoleColors.RESET+Cards.allCards.get(i).Mana);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Rarity: "+ConsoleColors.RESET+Cards.allCards.get(i).rarity);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Class: "+ConsoleColors.RESET+Cards.allCards.get(i).CardClass);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Type: "+ConsoleColors.RESET+Cards.allCards.get(i).type);
            if (!Cards.allCards.get(i).type.equals(Type.Spell))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Attack: "+ConsoleColors.RESET+Cards.allCards.get(i).attack);
            if (Cards.allCards.get(i).type.equals(Type.Weapons))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"Durability: "+ConsoleColors.RESET+Cards.allCards.get(i).Durability);
            if (Cards.allCards.get(i).type.equals(Type.Minion))
                System.out.println(ConsoleColors.PURPLE_BRIGHT+"HP: "+ConsoleColors.RESET+Cards.allCards.get(i).HP);
            System.out.println(ConsoleColors.PURPLE_BRIGHT+"Description: "+ConsoleColors.RESET+Cards.allCards.get(i).Description);
            System.out.println("");
        }
        System.out.println("Which one do you want to Sell?");
        int ans =getAnswer(j);


        if (ans==0) Menu(player);
        Shop.Sell(player,Cards.allCards.get(ans-1));

    }
    public static void Hero(Player player) throws IOException {
        System.out.println(ConsoleColors.BLUE_BRIGHT+"     Hero Setting: ");
        System.out.println(ConsoleColors.RED+"0-exit ");
        System.out.println(ConsoleColors.RESET+"1-Available Heroes ");
        System.out.println("2-Current Hero ");
        System.out.println("3-Choose Hero ");
        System.out.println("4-Help \n\n");

        switch (getAnswer(4)){
            case 0:
                Menu(player);
            case 1:
                for (Heroes a:player.AvailableHeroes) {
                    System.out.println(ConsoleColors.BLUE_BRIGHT+a.name+ConsoleColors.RESET);
                    switch (a.name){
                        case Rogue:
                            System.out.println("She pays 2 Manas less than others for Spell cards");break;
                        case Mage:
                            System.out.println("He is thief! and he pays 2 Manas less than others for stolen cards");break;
                        case Warlock:
                            System.out.println("He is brave and dedicated, he has 35 HP (others have 30)");break;
                    }
                    System.out.println("");
                }
                Hero(player);
                break;
            case 2:
                System.out.println("Current Hero is "+ConsoleColors.BLUE_BRIGHT+player.CurrentHero.name+ConsoleColors.RESET);
                System.out.println("");
                Hero(player);
                break;
            case 3:
                System.out.println(ConsoleColors.BLUE_BRIGHT+"Which hero you want to choose?");
                System.out.println(ConsoleColors.GREEN+"0 for exit-a");
                int i=0;
                for (Heroes a:player.AvailableHeroes) {
                    i++;
                    System.out.println(ConsoleColors.RED +i+")"+ ConsoleColors.RESET+ a.name );
                    System.out.println("");
                }
                int ans=getAnswer(i);
                if (ans==0){Hero(player);}
                else {
                    player.CurrentHero=player.AvailableHeroes.get(i-1);
                    Hero(player);
                }
                break;
            case 4:
                System.out.println(ConsoleColors.GREEN+"Help:");
                System.out.println("Available Heroes : you can see your available heroes");
                System.out.println("Current Hero: you can see the current Hero");
                System.out.println("Choose Hero: you can change the current Hero "+ConsoleColors.RESET);
                Hero(player);
                break;
        }

    }
    public static void Delete(Player player) throws IOException {
        System.out.println(ConsoleColors.BLUE_BRIGHT+"     Delete Account");
        System.out.println(ConsoleColors.GREEN+"Help:");
        System.out.println("Enter your password to DELETE your account \n If you want to go back to Menu you can type \"Menu\" instead ");
        System.out.println(ConsoleColors.RESET+"Password:");
        String password =getAnswer("Password");
        if (password.equalsIgnoreCase("Menu"))Menu(player);
        while (!Logs.PassCheck(player.UserName,password))
            password =getAnswer("Password");
        player.delete();
    }


    static void EXIT(){

    }
}
