package com.MarqusKoryEdwin.Othello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/*
 * Class board creates and adds and 8 by 8 board to the gui
 */
public class Board {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] boardSquares = new JButton[8][8];
    private JPanel board;
    private static final String COLS = "12345678";

    Board() {
        initializeGui();
    }
    
    /*
     * method initializeGui will print out the 
     * graphic user interface with a board that is 8 by 8
     *
     *@param: none
     *@return: none
     * 
     */

    public final void initializeGui() {
        // Buttons on the top left
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        /*tools.add(new JButton("New Game"));
        tools.add(new JButton("Credits")); 
        tools.add(new JButton("Help")); 
        tools.add(new JButton("Quit")); 
*/        

        JButton button = new JButton("New Game");
        tools.add(button);
        
        JButton button2 = new JButton("Credits");
        tools.add(button2);
        
        JButton button3 = new JButton("Quit");
        tools.add(button3);
        
        button3.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				System.exit(0);
        			}
        		}
        		);
        
        
        
        
        board = new JPanel(new GridLayout(0, 9));	
        gui.add(board);

        // board squares
        Insets buttonMargin = new Insets(5,5,5,5);
        for (int i = 0; i < boardSquares.length; i++) 
        {
            for (int j = 0; j < boardSquares[i].length; j++) 
            {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // goes through boardSquares and make every other square a 
               
               if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) 
                {
                	ImageIcon bIcon = new ImageIcon( "G:/Othello/Othello/images/Othello Chip Black.png" );
                    b.setIcon(bIcon);
                    boardSquares[j][i] = b;
                } 
                else 
                {
                	ImageIcon bIcon = new ImageIcon( "G:/Othello/Othello/images/Othello Chip White.png" );
                    b.setIcon(bIcon);
                    boardSquares[j][i] = b;
                }
                
            
 
                // this goes through each button square and goes inbetween each other to make a white square or gray square
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) 
                {
                    b.setBackground(Color.darkGray);
                } else {
                    b.setBackground(Color.gray);
                }
                boardSquares[j][i] = b;
            }
        }

        //fill the board
        board.add(new JLabel(""));
        
        // soon to add letters on the top row
        for (int i = 0; i < 8; i++) 
        {
            board.add(
                    new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
         
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                switch (j) 
                {
                    case 0:
                        board.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        board.add(boardSquares[j][i]);
                }
            }
        }
    }
    /*
     * getter getBoard gets the 8 by 8 board 
     */

    public final JComponent getboard() 
    {
        return board;
    }
    /*
     * getter getGui gets the gui
     */

    public final JComponent getGui() 
    {
        return gui;
    }
    /*
     * Method main will run the program 
     */

    public static void main(String[] args)
    {
        Runnable r = new Runnable() 
        {

            @Override
            public void run() 
            {
                Board cb =
                        new Board();

                JFrame f = new JFrame("Othello");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}