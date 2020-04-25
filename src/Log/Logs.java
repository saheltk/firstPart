package Log;


import Player.Player;
import Player.PlayersFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Logs {
    Player player;
    File file;
    private String path ;
    private FileWriter fileWriter ;


    public Logs() {
    }

    public Logs(Player player) throws IOException {
        this.player = player;
        path = "src\\Log\\Log.Logs\\" + player.getUsername() + "-userid.log";
        file = new File(path);
        fileWriter = new FileWriter(path);

        fileWriter.write("Username: " + player.getUsername() + "\n");
        fileWriter.write("Password: " + player.getPassword() + "\n");
        fileWriter.write("CREATED_AT: " + player.getCreationTime() + "\n");

        fileWriter.flush();
        fileWriter.close();
        LogFactory.add(this);
    }


    public static Logs setLog(Player player) throws IOException {
        Logs log = new Logs();
        log.player = player;
        try {
            log.file = new File("src\\Log\\Log.Logs\\" + player.getUsername() + "-userid.log");
        }
        catch (Exception e){
            log=new Logs(player);
        }
        LogFactory.add(log);
        return log;
    }

    public void SignIn(Player player) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write("Sign_in : " + player.getCreationTime() + "  " + player.getUsername() + " \n");
        fileWriter.flush();
        fileWriter.close();
    }

    public void Write(String event, String description) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(event + player.getCreationTime() + "  " + description + " \n");
        fileWriter.flush();
        fileWriter.close();
    }

    public void Delete() throws IOException {
        Write("Deleted at", "");
    }
}
