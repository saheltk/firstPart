package Player;

import Player.Player;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class PlayersFactory {
    private static ArrayList<Player> allPlayers = new ArrayList<>();

    private PlayersFactory(){ }

    public static void add(Player player){ allPlayers.add(player); }
    public static void remove(Player player){ allPlayers.remove(player); }

    public static Player find(String name){
        for (Player player: allPlayers ) {
            if (player.getUsername().equals(name))
                return player;
        }
        return null;
    }
    public static boolean passCheck(String Name, String Pass) {
        try {
            if (find(Name).getPassword().equals(Pass))
                return true;
            System.out.println("Invalid Password");
        }
        catch (Exception e){
            return false;
        }

        return false;

    }


}
