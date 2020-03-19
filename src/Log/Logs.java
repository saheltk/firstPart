package Log;

import Cards.Cards;
import Heroes.HeroName;
import Player.Player;
import Player.PlayersFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Logs {
    Player player;

    public static ArrayList<String> usernames = new ArrayList<>();
    public Logs(Player player) throws IOException {
        this.player=player;
        String logname="src\\Logs\\"+player.UserName+"-userid.log";
        FileWriter fileWriter = new FileWriter(logname);
        fileWriter.write("Username: "+player.UserName+"\n");
        fileWriter.write("Password: "+player.Password+"\n");
        fileWriter.write("CREATED_AT: "+player.CreationTime+"\n");


        fileWriter.flush();
        fileWriter.close();
    }
    public static boolean  PassCheck(String Name , String Pass){
        if (PlayersFactory.find(Name).Password.equals(Pass))
            return true;
        System.out.println("Invalid Password");
        return false;

    }

    public void SignIn() throws IOException {
    String logname="src\\Logs\\"+player.UserName+"-userid.log";
    FileWriter fileWriter = new FileWriter(logname,true);
     fileWriter.write("Sign_in : "+player.CreationTime+"  "+player.UserName+" \n");
        fileWriter.flush();
        fileWriter.close();
    }
    public void Write(String event, String description) throws IOException {
        String logname="src\\Logs\\"+player.UserName+"-userid.log";
        FileWriter fileWriter = new FileWriter(logname,true);
        fileWriter.write(event+player.CreationTime+"  "+description+" \n");
        fileWriter.flush();
        fileWriter.close();
    }
    public void Delete() throws IOException {
        Write("Deleted","");

    }

    public static void main(String[] args) throws IOException {
        System.out.println("\u2606");
    }
}
