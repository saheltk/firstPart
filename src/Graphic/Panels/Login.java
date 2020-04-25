package Graphic.Panels;

import javax.swing.*;

public class Login extends JPanel {
    private static Login login ;
    private Login(){
        setBackGround();
        setButtons();
    }

    public static Login loginPanel() {
        if (login==null)
        login = new Login();
        return login;
    }

    private void setBackGround(){

    }

    private void setButtons(){

    }
}
