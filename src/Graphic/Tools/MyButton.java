package Graphic.Tools;


import Constants.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyButton extends JButton {
    public MyButton (){
        try {
            setBackground(Color.BLACK);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public MyButton (String filePath){
        try {
            File file = new File(Constants.buttonPath + filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            setIcon(new ImageIcon(bufferedImage));
            setBackground(Color.BLACK);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public MyButton (String filePath1 , String filePath2){
        try {
            File file = new File( filePath1+filePath2);
            BufferedImage bufferedImage = ImageIO.read(file);
            setIcon(new ImageIcon(bufferedImage));
            setBackground(Color.BLACK);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public MyButton (String filePath, ActionListener actionListener){
        try {
            File file = new File(Constants.buttonPath + filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            setIcon(new ImageIcon(bufferedImage));
            setBackground(Color.BLACK);
            setBorderPainted(false);
            setContentAreaFilled(false);
            addActionListener(actionListener);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setPhoto (String filePath){
        try {
            File file = new File( filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            setIcon(new ImageIcon(bufferedImage));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("theres a problem");
        }
    }
}
