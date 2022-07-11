package com.Connect_4;

        import java.awt.Color;
        import java.awt.Dimension;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.HeadlessException;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;
        import javax.swing.*;
        import java.awt.Font;

public class Board extends JPanel implements MouseListener , Player {


    private int startX;
    private int startY;
    private final int cellWidth = 60;
    private static int turn = 2;
    private final int rows = 6;
    private final int cols = 7;

    private Game_info panel;
    private piece player = new piece(255,255,255);



    piece[][] grid = new piece[rows][cols];

    public Board(Dimension dimension,Game_info panel) {
        setSize(dimension);
        setPreferredSize(dimension);
        this.panel=panel;
        addMouseListener(this);
        //1. initialize array here

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new piece(255, 255, 255);

            }
        }
    }
    public void replay(boolean winner,Game_info panel)
    {
        if (winner)
        {
            if (turn % 2 == 0)
            {
                JOptionPane.showConfirmDialog(this,"Red has Won","Congratulations",JOptionPane.PLAIN_MESSAGE);
                if(JOptionPane.showConfirmDialog(this,"Yes to replay","Play again",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                {
                    turn++;
                    panel.getFrame().dispose();
                    Main_menu menu = new Main_menu();
                }
                else
                {
                    System.exit(0);
                }
            } else {
                JOptionPane.showConfirmDialog(this,"Yellow has Won","Congratulations",JOptionPane.PLAIN_MESSAGE);
                if(JOptionPane.showConfirmDialog(this,"Yes to replay","Play again",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                {
                    turn++;
                    panel.getFrame().dispose();
                    Main_menu menu = new Main_menu();
                }
                else
                {
                    System.exit(0);
                }
            }
        }
    }
    public Color getplayer()
    {
        if(turn%2==0)
        {
            player = new piece(255,0,0);
        }
        else
        {
            player = new piece(255,255,0);
        }
        return player;
    }





    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        ImageIcon i = new ImageIcon("/Users/sheroukhelal/Downloads/Background.jpg");
        i.paintIcon(this, g, 0, 0);
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        /*g2.fillRect(0,0,d.width,d.height);

         */
        startX = 0;
        startY = 0;

        //2) draw grid here
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                g2.setColor(grid[row][col]);
                g2.fillOval(startX, startY, cellWidth, cellWidth);
                startX += cellWidth + 10;
            }
            startX = 0;
            startY += cellWidth + 10;
        }

        g2.setFont(new Font("Algerian", Font.BOLD, 30));


        if (turn % 2 == 0) {
            g2.setColor(new Color(255, 0, 0));
            g2.drawString("Red's Turn", 515, 200);
        } else {
            g2.setColor(new Color(255, 255, 0));
            g2.drawString("Yellow's Turn", 485, 200);
        }

    }

    @Override
    public void mouseClicked(MouseEvent click)
    {
        int x = click.getX();
        int col = x / (cellWidth + 10);
        int row = addpiece(col);
        try {
            if (turn % 2 == 0)
            {
                grid[row][col] = new piece(255, 0, 0);
            } else {
                grid[row][col] = new piece(255, 255, 0);
            }
            getplayer();
            boolean winner = Checkforwinner(col,player);
            repaint();
            replay(winner,panel);
            turn++;
        } catch (ArrayIndexOutOfBoundsException Error){
            JOptionPane.showMessageDialog(this , "Please enter a valid location", "Wrong place", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param col
     * @return
     */
    @Override
    public int addpiece(int col) {
        int row = rows - 1;
        try {
            while (!(grid[row][col].equals(new Color(255, 255, 255))) || row < 0) {
                row--;
            }
            return row;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 99;
        }
    }

    public boolean Checkforwinner(int col, piece color)
    {
        boolean someonewon = false;
        for (int row = 0; row < rows; row++) {
            if (!(grid[row][col].equals(new Color (255,255,255))))
            {
                int streak = 4;
                //checks vertical red
                for (int WinRow = row ; WinRow < rows; WinRow++)
                {
                    if (grid[WinRow][col].equals(color)) {
                        streak--;
                        if (streak == 0) {
                            someonewon = true;
                        }
                    } else {
                        streak = 4;
                    }

                }
                streak = 4;
                //horizontal red
                for (int winCol = col - 3; winCol < col + 4; winCol++) {
                    if (winCol < 0) continue;
                    if (winCol >= cols) break;
                    if (grid[row][winCol] != null && grid[row][winCol].equals(color))
                    {
                        streak--;
                        if (streak == 0) {
                            someonewon = true;
                        }
                    } else {
                        streak = 4;
                    }
                }
                streak = 4;
                //left diagonal red
                for (int winRow = row - 3, Wincol = col - 3; winRow <= row + 3 && Wincol <= col + 3; winRow++, Wincol++) {
                    if (winRow < 0 || Wincol < 0) continue;
                    if (winRow >= rows || Wincol >= cols) break;
                    if (grid[winRow][Wincol] != null && grid[winRow][Wincol].equals(color)) {
                        streak--;
                        if (streak == 0) {
                            someonewon = true;
                        }
                    } else {
                        streak = 4;
                    }
                }
                //Right Diagonal
                streak = 4;
                for (int winRow = row - 3, Wincol = col + 3; winRow <= row + 3 && Wincol >= col - 3; winRow++, Wincol--) {
                    if (winRow < 0 || Wincol >= cols) continue;
                    if (winRow >= rows || Wincol < 0) break;
                    if (grid[winRow][Wincol] != null && grid[winRow][Wincol].equals(color)) {
                        streak--;
                        if (streak == 0) {
                            someonewon = true;
                        }
                    } else {
                        streak = 4;
                    }
                }
            }
        }
        return someonewon;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


}