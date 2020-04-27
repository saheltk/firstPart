package Graphic.Panels;

import Constants.Constants;
import Graphic.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Login extends MainPanel {
    private static Login login;
    Dimension dimension;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";


    private JButton signInButton;
    private JButton signUpButton;

    private JLabel signInUsername;
    private JLabel signUpUsername;
    private JLabel signInPassword;
    private JLabel signUpPassword;

    private JTextField signInUsernameFeild;
    private JTextField signInPasswordFeild;
    private JTextField signUpUsernameFeild;
    private JTextField signUpPasswordFeild;


    public Login() {
        super();
        backgroundFileName = Constants.loginBackgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    @Override
    protected void setButtons() {
        try {
            //SignIn
            File signInFile = new File(buttonPath + "woodenSignIn.png");
            BufferedImage signInBufferedImage = ImageIO.read(signInFile);
            signInButton = new JButton();
            signInButton.setIcon(new ImageIcon(signInBufferedImage));
            signInButton.setBackground(Color.BLACK);
            signInButton.setBorderPainted(false);
            signInButton.setContentAreaFilled(false);


            //SignUp
            File signUpFile = new File(buttonPath + "woodenSignUp.png");
            BufferedImage signUpBufferedImage = ImageIO.read(signUpFile);
            signUpButton = new JButton();
            signUpButton.setIcon(new ImageIcon(signUpBufferedImage));
            signUpButton.setBackground(Color.BLACK);
            signUpButton.setBorderPainted(false);
            signUpButton.setContentAreaFilled(false);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void setLabels() {
        //SignIn
        signInUsername = new JLabel("Username: ");
        signInUsername.setForeground(Color.WHITE);
        signInPassword = new JLabel("Password: ");
        signInPassword.setForeground(Color.WHITE);


        signInUsernameFeild = new JTextField(10);
        signInPasswordFeild = new JTextField(10);

        //SignUp
        signUpUsername = new JLabel("Username: ");
        signUpUsername.setForeground(Color.WHITE);
        signUpPassword = new JLabel("Password: ");
        signUpPassword.setForeground(Color.WHITE);


        signUpUsernameFeild = new JTextField(10);
        signUpPasswordFeild = new JTextField(10);

    }


    public void setGridBagConstraints() {


        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(220, 300, 0, 0);

        gridBagConstraints.anchor=GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridy = 0;
        backgroundLabel.add(signInUsername, gridBagConstraints);
        gridBagConstraints.insets = new Insets(60, 300, 0, 0);
        gridBagConstraints.gridy = 1;
        backgroundLabel.add(signInUsernameFeild, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        backgroundLabel.add(signInPassword, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        backgroundLabel.add(signInPasswordFeild, gridBagConstraints);

        gridBagConstraints.insets = new Insets(30, 280, 105, 0);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_START;
        backgroundLabel.add(signInButton, gridBagConstraints);
///////////////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(220, 700, 0, 0);

        gridBagConstraints.anchor=GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridy = 0;
        backgroundLabel.add(signUpUsername, gridBagConstraints);
        gridBagConstraints.insets = new Insets(60, 700, 0, 0);
        gridBagConstraints.gridy = 1;
        backgroundLabel.add(signUpUsernameFeild, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        backgroundLabel.add(signUpPassword, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        backgroundLabel.add(signUpPasswordFeild, gridBagConstraints);

        gridBagConstraints.insets = new Insets(30, 680, 105, 0);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor=GridBagConstraints.LAST_LINE_START;
        backgroundLabel.add(signUpButton, gridBagConstraints);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }

    public static Login loginPanel() {
        if (login == null)
            login = new Login();
        return login;
    }

}
