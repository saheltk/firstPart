package Log;

import Player.Player;

import java.util.ArrayList;
import java.util.TreeSet;

public class LogFactory {
    private static ArrayList<Logs> allLogs = new ArrayList<Logs>();
    public static ArrayList<String> usernames = new ArrayList<>();

    public static void add(Logs newLog) { allLogs.add(newLog); usernames.add(newLog.player.getUsername());}

    public static void add(Player player) {
        allLogs.add(Logs.setLog(player)); usernames.add(player.getUsername());}

}
