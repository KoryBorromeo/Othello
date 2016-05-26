package com.MarqusKoryEdwin.Othello;
 
/**
 * This is an othello program made by Marqus Pino, Kory Borromeo, and Edwin Reyes for a 
 * Computer Science 3 H Final Project in Mr. Mayo's class. This program will be a 
 * digital replication of an already made board game known as Othello or Reversi.
 * Starting off, the game will be intialized through a GUI, which is a grid or board
 * that the player can click on to make different actions of the game. Then the game
 * pieces will be drawn onto the grid, starting off in the center with the black piece
 * having the starting turn. The program will allow the user to click on any specific 
 * coordinate on the grid where a white piece is between the black piece and the 
 * coordinate selected. Same goes for the white chip. The game is played until there are
 * no more spots on the grid to be filled or one player runs out of chips.
 * 
 * @Marqus Pino
 * @Kory Borromeo Macadangdang
 * @Edwin Reyes
 * 
 * @Othello ; CS3 H Final Project
 * @Period 6
 * @feedback: no
 * 
 */
 
 
 
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/*
 * Class board creates and adds and 8 by 8 board to the gui
 */
public class Board {
 
    private final JPanel gui = new JPanel(new BorderLayout(3, 3)); // creates the GUI
    private JButton[][] boardSquares = new JButton[8][8]; // creates the 8x8 board
    private JPanel board;
    private static final String COORDINATES = "01234567"; // Numbers to show grid coordinates on the side / top of board.
    
    private String playerTurn = "Black";
	private int numBlack = 0;
	private int totalChips = 0;
    Board() {
        initializeGui(); // Initializes the whole entire GUI
    }
     
    /*
     * method initializeGui will print out the 
     * graphic user interface with a board that is 8 by 8
     *
     *@param: none
     *@return: none
     * 
     */
     
    public final void initializeGui() 
    {
    	
    	
    	
        // Buttons on the toolbar at the top of the window.
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
         
        //CREATING BUTONS FOR THE GUI; NEW GAME, HELP, CREDITS, and QUIT.
        JButton newBut = new JButton("New Game");
        tools.add(newBut);
         
        JButton helpBut = new JButton("Help");
        tools.add(helpBut);
         
        JButton creditsBut = new JButton("Credits");
        tools.add(creditsBut);
         
        JButton quitBut = new JButton("Quit");
        tools.add(quitBut);
         
        JButton skipBut = new JButton("Skip Turn");
        tools.add(skipBut);
        
        
        
        // NEW GAME ACTION LISTENER
        newBut.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) //re-initializes the GUI (restarts game)
            {
                initializeGui();
            }
        } );
         
        //HELP ACTION LISTENER
        helpBut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)  // basic information on how to play game. 
            {
                JOptionPane.showMessageDialog(gui, "There are two sided chips on a board. One side is black,\n "
                        + "and the other side is white. The black chip always starts first. You play against\n "
                        + "another player. The goal is to end the game with as many chips showing your color as possible.\n "
                        + "To do this, you must sandwich your opponents chip(s) between a piece (showing your own color)\n "
                        + "currently on the board, and the piece you're putting down.");
            }
             
        } );
         
        //CREDITS ACTION LISTENER
        creditsBut.addActionListener(new ActionListener() // game credits
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(gui,"Created By: \nMarqus Pino\nKory Borromeo-Macadangdang"
                        + "\nEdwin Reyes\n\nMusic By:\nMarqus Pino"
                        + "\n\nThis project was created for a Computer Science III H Final Project.");
            }
        } );
         
        //QUIT ACTION LISTENER
        quitBut.addActionListener(new ActionListener()  // quits the game (closes window).
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        } );
         
        board = new JPanel(new GridLayout(0, 9));   
        // add the board to the gui
        gui.add(board);
 
        //traverse through the board squares array in order to create a board / grid.
        //the grid is created each row at a time, alternating color to give a checkerboard feel.
        Insets buttonMargin = new Insets(5,5,5,5);
        for (int i = 0; i < boardSquares.length; i++) // rows
        {
            for (int j = 0; j < boardSquares[i].length; j++) //columns
            {
                JButton b = new JButton(); // creates a new button (to be placed in the current coordinate)
                
                b.setMargin(buttonMargin);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) // even numbers darkGray
                {
                    b.setBackground(Color.darkGray); // the button to be placed is set to darkGray
                } 
                else if ((j % 2 != 1 || i % 2 != 1) || (j % 2 != 0 || i % 2 != 0)) // odd numbers gray
                {
                    b.setBackground(Color.gray); // the button to be placed is set to gray
                }
                boardSquares[j][i] = b; // sets the button at the specific coordinate
                
                boardSquares[j][i].setName(j + "," + i);
                boardSquares[j][i].setToolTipText("");
                
                boardSquares[j][i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						int x = Integer.parseInt(b.getName().split(",")[0]);
						int y = Integer.parseInt(b.getName().split(",")[1]);
					
						skipBut.addActionListener(new ActionListener()
				        {
				        	public void actionPerformed(ActionEvent e)
				        	{
				        		if (playerTurn == "Black")
				        			playerTurn = "White";
				        		else
				        			playerTurn = "Black";
				        		
				        		
				        	}
				        });
						
						try{
							boolean switchPlayer = false;
							
							//down
							for(int i = y; i < 7; i++){ //traversing through line of buttons
								if(boardSquares[x][i].getToolTipText() == "" && i != y){ //check if contains a chip
									break; //stop if no chip
								}else if(boardSquares[x][i].getToolTipText() == playerTurn && i != y+1){ //checks for possible move
									for(int y1 = y; y1 < i; y1++){ //switches the chips in between the player chip
										boardSquares[x][y1].setToolTipText(playerTurn);
										boardSquares[x][y1].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
									}
									//adds and switches player
									switchPlayer = true;
									addPiece(x, y, playerTurn);
									break;
								}else if(boardSquares[x][i].getToolTipText() == playerTurn && i != y-1){ //if its in the same position then dont check
									break;
								}
							}
							// this block of code repeats for each way down,left,up,right, and diagonal
							if(boardSquares[x][y].getToolTipText() == ""){
								//left
								for(int i = x; i > 0; i--){
									if(boardSquares[i][y].getToolTipText() == "" && i != x){
										break;
									}else if(boardSquares[i][y].getToolTipText() == playerTurn && i != x-1){
										for(int x1 = x; x1 > i; x1--){
											boardSquares[x1][y].setToolTipText(playerTurn);
											boardSquares[x1][y].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[i][y].getToolTipText() == playerTurn && i == x-1){
										break;
									}
								}
								//right
								for(int i = x; i < 7; i++){
									if(boardSquares[i][y].getToolTipText() == "" && i != x){
										break;
									}else if(boardSquares[i][y].getToolTipText() == playerTurn && i != x+1){
										for(int x1 = x; x1 < i; x1++){
											boardSquares[x1][y].setToolTipText(playerTurn);
											boardSquares[x1][y].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[i][y].getToolTipText() == playerTurn && i == x+1){
										break;
									}
								}
								
								//up
								for(int i = y; i > 0; i--){
									if(boardSquares[x][i].getToolTipText() == "" && i != y){
										break;
									}else if(boardSquares[x][i].getToolTipText() == playerTurn && i != y-1){
										for(int y1 = y; y1 > i; y1--){
											boardSquares[x][y1].setToolTipText(playerTurn);
											boardSquares[x][y1].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[x][i].getToolTipText() == playerTurn && i == y-1){
										break;
									}
								}
								
								
								//leftup
								int tempCounter = 1;
								while(x+(-1*tempCounter) > 0 && y+(-1*tempCounter) > 0){
									if(boardSquares[x+(-1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == ""){
										break;
									}else if(boardSquares[x+(-1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == playerTurn && x+(-1*tempCounter) != x-1 && y+(-1*tempCounter) != y-1){
										for(int z = 1; z <= tempCounter; z++){
											boardSquares[x+(-1*z)][y+(-1*z)].setToolTipText(playerTurn);
											boardSquares[x+(-1*z)][y+(-1*z)].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[x+(-1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == playerTurn && x+(-1*tempCounter) == x-1 && y+(-1*tempCounter) == y-1){
										break;
									}
									tempCounter++;
								}
								
								//leftdown
								tempCounter = 1;
								while(x+(-1*tempCounter) > 0 && y+(1*tempCounter) > 0){
									if(boardSquares[x+(-1*tempCounter)][y+(1*tempCounter)].getToolTipText() == ""){
										break;
									}else if(boardSquares[x+(-1*tempCounter)][y+(1*tempCounter)].getToolTipText() == playerTurn && x+(-1*tempCounter) != x-1 && y+(1*tempCounter) != y+1){
										for(int z = 1; z <= tempCounter; z++){
											boardSquares[x+(-1*z)][y+(1*z)].setToolTipText(playerTurn);
											boardSquares[x+(-1*z)][y+(1*z)].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[x+(-1*tempCounter)][y+(1*tempCounter)].getToolTipText() == playerTurn && x+(-1*tempCounter) == x-1 && y+(1*tempCounter) == y+1){
										break;
									}
									tempCounter++;
								}
								
								//rightup
								tempCounter = 1;
								while(x+(1*tempCounter) > 0 && y+(-1*tempCounter) > 0){
									if(boardSquares[x+(1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == ""){
										break;
									}else if(boardSquares[x+(1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == playerTurn && x+(1*tempCounter) != x+1 && y+(-1*tempCounter) != y-1){
										for(int z = 1; z <= tempCounter; z++){
											boardSquares[x+(1*z)][y+(-1*z)].setToolTipText(playerTurn);
											boardSquares[x+(1*z)][y+(-1*z)].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[x+(1*tempCounter)][y+(-1*tempCounter)].getToolTipText() == playerTurn && x+(1*tempCounter) == x+1 && y+(-1*tempCounter) == y-1){
										break;
									}
									tempCounter++;
								}
								
								//rightdown
								tempCounter = 1;
								while(x+(1*tempCounter) > 0 && y+(1*tempCounter) > 0){
									if(boardSquares[x+(1*tempCounter)][y+(1*tempCounter)].getToolTipText() == ""){
										break;
									}else if(boardSquares[x+(1*tempCounter)][y+(1*tempCounter)].getToolTipText() == playerTurn && x+(1*tempCounter) != x+1 && y+(1*tempCounter) != y+1){
										for(int z = 1; z <= tempCounter; z++){
											boardSquares[x+(1*z)][y+(1*z)].setToolTipText(playerTurn);
											boardSquares[x+(1*z)][y+(1*z)].setIcon(new ImageIcon( "U:/CS/Final Project Othello/Othello/images/Othello Chip " + playerTurn + ".png" ));
										}
										switchPlayer = true;
										addPiece(x, y, playerTurn);
										break;
									}else if(boardSquares[x+(1*tempCounter)][y+(1*tempCounter)].getToolTipText() == playerTurn && x+(1*tempCounter) == x+1 && y+(1*tempCounter) == y+1){
										break;
									}
									tempCounter++;
								}
								
								
								if(switchPlayer){
									numBlack = 0;
									totalChips = 0;
									for(int o = 0; o < 7; o++){
										for(int p = 0; p < 7; p++){
											if(boardSquares[o][p].getToolTipText() == "Black" || boardSquares[o][p].getToolTipText() == "White")
												totalChips++;
											if(boardSquares[o][p].getToolTipText() == "Black")
												numBlack++;
											
										}
									}
									
									
									 JOptionPane.showMessageDialog(gui,"The number of black chips there are is " + numBlack );
								     JOptionPane.showMessageDialog(gui,"\nThe number of white chips there are is " + (totalChips - numBlack));
								     
									if(playerTurn == "Black")
										playerTurn = "White";
									else
										playerTurn = "Black";
									
									
								}
							}
						}catch(Exception error){}

					}
                	
                }
               
                );
                //gui.add(b);
                 
               /* // makes the starting of pieces of Othello which is the 4 middle squares (3,4) and (4,3)
                if ((j == 4 && i == 3) || (j == 3 && i == 4)) 
                {
                    ImageIcon bIcon = new ImageIcon( "F:/Othello/Othello/images/Othello Chip Black.png" ); // custom chip image black
                    b.setIcon(bIcon);
                    boardSquares[j][i] = b; // draws an imageIcon of the chip at that location (black)
                }
                 
                else if ((j == 3 && i == 3) || (j == 4 && i == 4)) 
                {
                    ImageIcon bIcon = new ImageIcon( "F:/Othello/Othello/images/Othello Chip White.png" ); // custom chip image white
                    b.setIcon(bIcon);
                    boardSquares[j][i] = b; // draws imageIcon of chip at location (white)
                }   */
            }
        }
 
        //fill the spaces in the board
        board.add(new JLabel(""));
         
        // Add letters / coordinate numbers above the top row of the board.
        for (int i = 0; i < 8; i++) 
        {
            board.add(
                    new JLabel(COORDINATES.substring(i, i + 1), // display the numbers on top of board.
                    SwingConstants.CENTER));
        }
          
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                switch (j) 
                {
                    case 0:
                        board.add(new JLabel("" + (i),
                                SwingConstants.CENTER));
                    default:
                        board.add(boardSquares[j][i]);
                }
            }
        }
        
        //adding starting pieces
        addPiece(4, 3, "Black");
        addPiece(3, 4, "Black");
        addPiece(3, 3, "white");
        addPiece(4, 4, "White");
    }
    
    public void actionPerformed(ActionEvent e){
    	
    }
    
    /**
     * @pre none
     * @param posX
     * @param posY
     * @param type
     * @post places a piece, switches turn.
     * @return none
     */
    public void addPiece(int posX, int posY, String type){
    	//defining variables
    	ImageIcon tempImage;
    	
    	//checking type
    	if(type == "Black"){
    		tempImage = new ImageIcon("U:/CS/Final Project Othello/Othello/images/Othello Chip Black.png");
    		boardSquares[posX][posY].setToolTipText("Black");
    	}else{
    		tempImage = new ImageIcon("U:/CS/Final Project Othello/Othello/images/Othello Chip White.png");
    		boardSquares[posX][posY].setToolTipText("White");
    	}
    		
    	//process: sets icon
    	boardSquares[posX][posY].setIcon(tempImage);
    }
    /*
     * getter getBoard gets the 8 by 8 board 
     * 
     * @pre none
     * @param none
     * @post none
     * @return board
     */
 
    public final JComponent getboard() 
    {
        return board;
    }
     
    /*
     * getter getGui gets the gui
     * 
     * @pre none
     * @param none
     * @post none
     * @return gui
     */
 
    public final JComponent getGui() 
    {
        return gui;
    }
      
//   public void start()
//   {
//        
//    
//   }
     
   /*
    * 
    * Method main will run the program 
    * 
    * @pre none
    * @param none
    * @post none
    * @return none
    */
    public static void main(String[] args)
    {
        Runnable r = new Runnable() 
        {
 
            @Override
            public void run() 
            {
                 
                Board cb =
                        new Board(); // creates a new board
 
                JFrame f = new JFrame("Othello"); // Name of the window
                f.add(cb.getGui()); // gets the GUI for the window
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows the program to exit when closed
                f.setLocationByPlatform(true);
 
                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true); // set the window to be visible or seen.
            }
        };
        SwingUtilities.invokeLater(r); // allows the Run() function to be declared after all events have taken place.
    }
}
 
 
 
/**
 *@Commented out pieces of code 
 */
 
/*   public void playSound() {
try {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("E:/Othello/Othello Song.mp3").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
} catch(Exception ex) {
    System.out.println("Error with playing sound.");
    ex.printStackTrace();
}
try{
    AudioInputStream audioInputStream =
        AudioSystem.getAudioInputStream(
            this.getClass().getResource("E:/Othello/Othello Song.mp3"));
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
}
catch(Exception ex)
{
}
}*/