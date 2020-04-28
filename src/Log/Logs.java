package Log;


import Player.Player;
import Player.PlayersFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Logs {
    Player player;
    File file;
    private String path;
    private FileWriter fileWriter;


    public Logs() {
    }

    public Logs(Player player) {
        this.player = player;
        path = "src\\Log\\Logs\\" + player.getUsername() + "-userid.log";
        file = new File(path);
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write("Username: " + player.getUsername() + "\n");
            fileWriter.write("Password: " + player.getPassword() + "\n");
            fileWriter.write("CREATED_AT: " + player.getCreationTime() + "\n");

            fileWriter.flush();
            fileWriter.close();
            LogFactory.add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Logs setLog(Player player) {
        Logs log = new Logs();
        log.player = player;
        try {
            log.file = new File("src\\Log\\Logs\\" + player.getUsername() + "-userid.log");
        } catch (Exception e) {
            log = new Logs(player);
        }
        LogFactory.add(log);
        return log;
    }


    public static void  write(Player player, String event, String description) {
        try {
            String path = "src\\Log\\Logs\\" + player.getUsername() + "-userid.log";
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write( getTime().toString() + "  ~  " +event+": " + description + " \n");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) { }

    }

    public static void  delete(){}



    private static Timestamp getTime() {
        Timestamp time = new Timestamp(1000);
        return time;
    }




}
