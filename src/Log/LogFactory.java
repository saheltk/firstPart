package Log;

import java.util.ArrayList;
import java.util.TreeSet;

public class LogFactory {
    private static TreeSet<Logs> allLogs = new TreeSet<Logs>();
    public static ArrayList<String> usernames = new ArrayList<>();

    public static void add(Logs newLog) { allLogs.add(newLog); usernames.add(newLog.player.getUsername());}
}
