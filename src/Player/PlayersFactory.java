package Player;

import Player.Player;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class PlayersFactory {
    static public ArrayList<Player> AllPlayers = new ArrayList<>();

    public static Player find(String name){
        for (Player p: AllPlayers ) {
            if (p.UserName.equals(name))
                return p;
        }
        return null;
    }
    PlayersFactory(){
       // File fileSave= new File("C:\Users\Sahel\IdeaProjects\fisrtPart\src\saving");
     //   ObjectMapper om =  new ObjectMapper(fileSave);
    }

}
