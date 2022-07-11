package com.Connect_4;

import  javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game_info {
    private final JFrame frame;
    private Board board;
    public Game_info() {
        frame = new JFrame("Connect 4");
        frame.setSize(750, 450);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new Board(frame.getSize(),this));
        frame.pack();
        frame.setVisible(true);
     }

    public JFrame getFrame()
    {
        return frame;
    }
    }
