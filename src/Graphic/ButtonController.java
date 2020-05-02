package Graphic;

import Command.Contoller;
import Log.Logs;
import Player.Player;
import Player.PlayersFactory;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ButtonController {
    private static Player player;
    private ButtonOptions name;
    private String soundFile;
    private String event;
    private String description;


    public ButtonController(ButtonOptions name) {
        player= Contoller.getPlayer();
        this.name = name;
        //setSound();
        setEvent();
        Logs.write(player,name.toString(),"");
        Contoller.getPlayer().update();
    }


    public ButtonController(ButtonOptions name,String description) {
        player= Contoller.getPlayer();
        this.name = name;
        //setSound();
        setEvent();
        Logs.write(player,name.toString(),description);
        Contoller.getPlayer().update();
    }

    public static void SignInPlayer(String username) {
        ButtonController.player = PlayersFactory.find(username);
    }

    private void setSound() {
        switch (name) {
            case SignIn:
            case SignUp:
        }

        try {
            File soundtrackFile = new File("src\\Graphic\\Sounds"+soundFile);
            Clip soundtrack = AudioSystem.getClip();
            soundtrack.open(AudioSystem.getAudioInputStream(soundtrackFile));
            soundtrack.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setEvent() {
        switch (name) {
            case SignIn:
            case SignUp:
        }
    }


    public enum ButtonOptions {
        SignIn,
        SignUp,
        Shop,
        Status,
        Collection,
        Setting,
        Sell,
        Buy,
        newDeck,
        add_Card_To_Deck,
        remove_Card_FromDeck,
        edit_Deck_Name,
        delete_Deck,
        Choose_My_Cards,
        Choose_All_Cards,
        ChooseNot_My_Cards,
        Choose_My_Decks,
        Show_Card,
        Play,
        End_Turn,
        Choose_Deck,




    }

}