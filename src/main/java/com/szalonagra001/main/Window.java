package com.szalonagra001.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public static final long serialVersionUID = -8237061449288343420L;

    public Window(int width, int height, String title, Game001 game001) {

        JFrame frame = new JFrame(title);

    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(game001);
    frame.setVisible(true);
    game001.start();

    }
}
