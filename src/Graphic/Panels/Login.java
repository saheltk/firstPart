package Graphic.Panels;

import Command.Check;
import Constants.Constants;
import Graphic.ButtonController;
import Graphic.GamePanel;
import Graphic.MainPanel;
import Graphic.Tools.MyButton;
import Graphic.Tools.MyGridBagContraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends GamePanel {
    private static Login login;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";


    private JButton signInButton;
    private JButton signUpButton;

    private JLabel signInUsername;
    private JLabel signUpUsername;
    private JLabel signInPassword;
    private JLabel signUpPassword;

    private JTextField signInUsernameFeild;
    private JPasswordField signInPasswordFeild;
    private JTextField signUpUsernameFeild;
    private JTextField signUpPasswordFeild;

    private String username;
    private String password;


    private Login() {
        super();
        backgroundFileName = Constants.loginBackgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    public static Login loginPanel() {
        if (login == null)
            login = new Login();
        return login;
    }

    @Override
    protected void setButtons() {
        //SignIn
        signInButton = new MyButton("woodenSignIn.png", e -> {
            username = signInUsernameFeild.getText();
            password = signInPasswordFeild.getText();
            Check.LoginResultCondition check = Check.check(username, password, Check.LoginCondition.SignIn);
            switch (check) {
                case SignIn:
                    ButtonController login = new ButtonController(ButtonController.ButtonOptions.SignIn);
                    MainPanel.setPanel("menu");
                    break;
                case WrongPassWord:
                    JOptionPane.showMessageDialog(Login.loginPanel(), "Wrong PassWord, please try again!", "WRONG PASSWORD", JOptionPane.ERROR_MESSAGE);
                    break;
                case UsernameNotFound:
                    JOptionPane.showMessageDialog(Login.loginPanel(), "Wrong Username, please try again!", "WRONG USERNAME", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

        //SignUp
        signUpButton = new MyButton("woodenSignUp.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = signUpUsernameFeild.getText();
                password = signUpPasswordFeild.getText();
                Check.LoginResultCondition check = Check.check(username, password, Check.LoginCondition.SignUp);
                switch (check) {
                    case SignUp:
                        ButtonController login = new ButtonController(ButtonController.ButtonOptions.SignUp);
                        MainPanel.setPanel("menu");
                        break;
                    case TemporaryUsername:
                        JOptionPane.showMessageDialog(Login.loginPanel(), "Temporary Username, please try again!", "TEMPORARY USERNAME", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
    }

    @Override
    protected void setLabels() {
        //SignIn
        signInUsername = new JLabel("Username: ");
        signInUsername.setForeground(Color.WHITE);
        signInPassword = new JLabel("Password: ");
        signInPassword.setForeground(Color.WHITE);


        signInUsernameFeild = new JTextField(10);
        signInPasswordFeild = new JPasswordField(10);

        //SignUp
        signUpUsername = new JLabel("Username: ");
        signUpUsername.setForeground(Color.WHITE);
        signUpPassword = new JLabel("Password: ");
        signUpPassword.setForeground(Color.WHITE);


        signUpUsernameFeild = new JTextField(10);
        signUpPasswordFeild = new JTextField(10);

    }

    @Override
    protected void setGridBagConstraints() {
        MyGridBagContraints gridBagConstraints = new MyGridBagContraints(backgroundLabel);

        gridBagConstraints.add(signInUsername,300, 260, 0, 0);
        gridBagConstraints.add(signInUsernameFeild,300, 330, 0, 0);
        gridBagConstraints.add(signInPassword,380, 260, 0, 0);
        gridBagConstraints.add(signInPasswordFeild,380, 330, 0, 0);
        gridBagConstraints.add(signInButton,440, 300, 0, 0);

///////////////////////////////////////////
        gridBagConstraints.add(signUpUsername,300, 640, 0, 0);
        gridBagConstraints.add(signUpUsernameFeild,300, 710, 0, 0);
        gridBagConstraints.add(signUpPassword,380, 640, 0, 0);
        gridBagConstraints.add(signUpPasswordFeild,380, 710, 0, 0);
        gridBagConstraints.add(signUpButton,440, 680, 0, 0);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }


}
