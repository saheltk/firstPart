package Graphic.Tools;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyJRadioButton extends JRadioButton {
    public MyJRadioButton(String text, boolean contentAreaFilled, Color color){
        setText(text);
        setContentAreaFilled(contentAreaFilled);
        setForeground(color);
    }

    public MyJRadioButton(String text, boolean contentAreaFilled, Color color, ActionListener actionListener){
        setText(text);
        setContentAreaFilled(contentAreaFilled);
        setForeground(color);
        addActionListener(actionListener);
    }
}
