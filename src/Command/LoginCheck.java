package Command;

import Cards.Cards;
import Log.Logs;
import Player.Player;
import Player.PlayersFactory;

public class LoginCheck {
    private static String username;
    private static String password;

    //Login
    public static LoginResultCondition check(String usernameInput, String passwordInput, LoginCondition condition) {
        username = usernameInput;
        password = passwordInput;
        LoginResultCondition check=LoginResultCondition.SignIn;
        switch (condition) {
            case SignIn:
                if (PlayersFactory.find(username) == null)
                    check = LoginResultCondition.UsernameNotFound;
                else if (PlayersFactory.passCheck(username, password))
                    {check = LoginResultCondition.SignIn;
                    signIn(PlayersFactory.find(username));}
                else
                    check = LoginResultCondition.WrongPassWord;
                break;
            case SignUp:
                if (PlayersFactory.find(username) == null)
                    {check= LoginResultCondition.SignUp;
                    signUp(username,password);}
                else
                    check= LoginResultCondition.TemporaryUsername;
                break;
        }

        return check;
    }

    private static void signUp(String username, String password) {
        Contoller.setPlayer(Player.createPlayer(username,password));
    }
    private static void signIn(Player player) {
        Contoller.setPlayer(player);
        Logs.write(player,"SignIn","");
        Contoller.getPlayer().update();
    }

    public static boolean buy(Cards card) {
        if (Contoller.getPlayer().getGems()< card.getCost())
            return false;
        Contoller.getPlayer().setGems(Contoller.getPlayer().getGems()-card.getCost());
        System.out.println(Contoller.getPlayer().getGems());
        Contoller.getPlayer().getMyCards().add(card);
        Contoller.getPlayer().update();

        return true;
    }

    public static void sell(Cards card) {
        Contoller.getPlayer().setGems(Contoller.getPlayer().getGems()+card.getCost());
        Contoller.getPlayer().getMyCards().remove(card);
    }

    public enum LoginCondition {
        SignIn,
        SignUp
    }

    public enum LoginResultCondition {
        SignIn,
        SignUp,
        WrongPassWord,
        UsernameNotFound,
        TemporaryUsername
    }

}
