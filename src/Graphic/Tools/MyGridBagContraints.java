package Graphic.Tools;

import javax.swing.*;
import java.awt.*;

public class MyGridBagContraints extends GridBagConstraints {
    private JLabel jLabel ;
    public MyGridBagContraints(JLabel jlabel){
        gridx = 0;
        gridy = 0;
        weightx = 1;
        weighty = 1;
        fill = GridBagConstraints.NONE;
        anchor = GridBagConstraints.FIRST_LINE_START;
        this.jLabel = jlabel;
    }

    public void add(JComponent component , int top, int left, int bottom, int right){
        this.insets = new Insets(top, left, bottom, right);
        jLabel.add(component, this);

    }
}
