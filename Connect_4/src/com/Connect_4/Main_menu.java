

package com.Connect_4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class Main_menu implements ActionListener {

    private final JLabel titleL;
    private final JButton startB;
    private final Graphics g = null;
    private JButton exitB;


    JFrame frame1 = new JFrame();

    private final JLabel backGround;



    public Main_menu()
    {
        frame1.setSize(750,450);
        frame1.setTitle("Connect 4");
        Container mainP = frame1.getContentPane();
        mainP.setLayout(null);
        backGround = new JLabel();
        backGround.setIcon(new ImageIcon("/Users/sheroukhelal/Downloads/IMG_5115.JPG"));

        titleL = new JLabel("CONNECT FOUR");
        startB = new JButton("Start");

        exitB = new JButton("Exit");


        mainP.add(titleL);
        titleL.setForeground(new java.awt.Color(204,204,255));

        titleL.setFont(new Font("Chiller",Font.BOLD,30));
        titleL.setBounds(230, 100, 300, 150);


        mainP.add(startB);
        startB.setFont(new Font("Algerian",Font.BOLD,23));
        startB.setBackground(new java.awt.Color(204,204,255));
        startB.setMnemonic(KeyEvent.VK_S);
        startB.setBounds(300, 200, 100, 50);



        mainP.add(exitB);
        exitB.setFont(new Font("Algerian",Font.BOLD,23));
        exitB.setBackground(new java.awt.Color(204,204,255));
        exitB.setMnemonic(KeyEvent.VK_E);
        exitB.setBounds(300, 250, 100, 50);


        startB.addActionListener(this);

        exitB.addActionListener(this);
        mainP.add(backGround);
        backGround.setBounds(0,0,750,450);
        frame1.setVisible(true);
        frame1.setResizable(false);



    }
/*
    @Override
    public void paintComponent(Graphics g) {

        paintComponent(g);
        ImageIcon i = new ImageIcon("/Users/sheroukhelal/Downloads/menu_background.jpg");
        i.paintIcon(this, g, 0, 0);
        Graphics2D g2 = (Graphics2D)g;
        Dimension d = getSize();
    }

 */



    @Override
    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand();

        if (key == "Start") {
            frame1.dispose();
            new Game_info();
        } else
            System.exit(0);


    }
}