package Constants;

public class Constants {
    public static final int cardNumbers=37;
    public static final int Players=100;
    public static final int width=1120;
    public static final int height=700;
    public static final String loginBackgroundFileName="door.jpg";
    public static final String menuBackgroundFileName="box.jpg";
    public static final String backgroundFileName="simpleBackground.png";
    public static final int mana=7;
    public static final String buttonPath = "src\\Graphic\\Buttons\\";


    private static int background=1;
    private static int card=1;
    private static String playBackgroundFileName="battleground"+background+".jpg";
    private static String playCardFileName="card"+card+".png";

    public static String getPlayBackgroundFileName() {
        System.out.println(playBackgroundFileName);
        return playBackgroundFileName;
    }
    public static String getPlayCardFileName() { return playCardFileName; }

    public static void setBackground(int background1) { background=background1; }
    public static void setCard(int card1) { card=card1; }
}
