package Command;

import Cards.Cards;
import Graphic.MainFrame;
import Player.Player;

public class Contoller {
    private static Player player;
    private static GamePlay gamePlay;

    public static void main(String[] args) {
        //Set up
        Cards.setCards();
        Player.setPlayers();
        //Start
        MainFrame mainFrame = new MainFrame();
    }

    public static void setPlayer(Player player) {
        Contoller.player = player;
    }

    public static void setGamePlay() {
        Contoller.gamePlay = new GamePlay(player);
    }

    public static Player getPlayer() {
        return player;
    }

    public static GamePlay getGamePlay() {
        return gamePlay;
    }
}
