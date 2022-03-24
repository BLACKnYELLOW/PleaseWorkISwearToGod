// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 12/2/15
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class.  
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;


//Nathan is cool
public class GraphicsPanel extends JPanel implements MouseListener{

	private final int SQUARE_WIDTH = 90;    // The width of one space on the board.  Constant used for drawing board.
	private final int OFFSET = 5;
	private Location from;   			    // holds the coordinates of the square that the user would like to move from.
	private Location to;   				    // holds the coordinates of the square that the user would like to move to.
	private Location whiteKing;
	private Location blackKing;
	private boolean blackInMate;
	private boolean whiteInMate;
	private boolean click;   				// false until the game has started by somebody clicking on the board.  should also be set to false
	// after an attempted move.
	private Piece[][] board; 				// an 8x8 board of 'Pieces'.  Each spot should be filled by one of the chess pieces or a 'space'. 
	private Piece[][] pretendBoard;
	private int turn;						// used to keep track of who's turn it is - should only be 1 or 2.

	public GraphicsPanel(){
		setPreferredSize(new Dimension(SQUARE_WIDTH*8+OFFSET*2,SQUARE_WIDTH*8+OFFSET*2));   // Set these dimensions to the width 
		// of your background picture.   
		this.setFocusable(true);					 // for keylistener
		this.addMouseListener(this);

		// instantiate the instance variables.
		board = new Piece[8][8];
		pretendBoard = new Piece[8][8];

		for(int column = 0; column<8; column++){
			board[1][column] = new Pawn(1);
			board[2][column] = new Space();
			board[3][column] = new Space();
			board[4][column] = new Space();
			board[5][column] = new Space();
			board[6][column] = new Pawn(2);
			board[0][column] = new Space();
		}


		// add the rest of the pieces to the board.
		//team 1
		board[0][2] = new Bishop(1);
		board[0][5] = new Bishop(1);
		board[0][1] = new Knight(1);
		board[0][6] = new Knight(1);
		board[0][0] = new Rook(1);
		board[0][7] = new Rook(1);
		board[0][4] = new Queen(1);
		board[0][3] = new King(1);
		whiteKing = new Location(0, 3);


		//team 2
		board[7][2] = new Bishop(2);
		board[7][5] = new Bishop(2);
		board[7][1] = new Knight(2);
		board[7][6] = new Knight(2);
		board[7][0] = new Rook(2);
		board[7][7] = new Rook(2);
		board[7][4] = new Queen(2);
		board[7][3] = new King(2);
		blackKing = new Location(7, 3);


		click = false;
		from = new Location();
		to = new Location();

		blackInMate = false;
		whiteInMate = false;
		turn = 1;
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if(turn == 1)
			g2.setColor(Color.WHITE);
		else
			g2.setColor(Color.LIGHT_GRAY);

		g2.fillRect(0,0,SQUARE_WIDTH*8+OFFSET*2,SQUARE_WIDTH*8+OFFSET*2);
		// Draw the board
		g2.setColor(Color.gray);
		g2.drawLine(SQUARE_WIDTH*8+OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, 0+OFFSET);
		g2.drawLine(OFFSET, OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET);

		for(int i = 0; i <8; i+=2)
			for (int j = 0; j<8; j+=2){
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		for(int i = 1; i <8; i+=2)
			for (int j = 1; j<8; j+=2){
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		if (board[blackKing.getRow()][blackKing.getColumn()].isInCheck())
		{
			g2.setColor(Color.RED);
			g2.fillRect(blackKing.column*SQUARE_WIDTH+OFFSET,blackKing.row*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
		}

		if (board[whiteKing.getRow()][whiteKing.getColumn()].isInCheck())
		{
			g2.setColor(Color.RED);
			g2.fillRect(whiteKing.column*SQUARE_WIDTH+OFFSET,whiteKing.row*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
		}

		
		if(click == true){
			g2.setColor(Color.YELLOW);
			g2.fillRect(from.column*SQUARE_WIDTH+OFFSET,from.row*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
		}	
    
    
		// instead of drawing a single piece you should loop through the two-dimensional array and draw each piece except for 
		// empty spaces.
		for(int column = 0; column<8; column++)
		{

			for(int row = 0; row < 8; row++)
			{
				board[row][column].draw(g2, this, new Location(row,column));

				if (board[row][column].isValid())
				{
					g2.setColor(new Color(90, 90, 90, 150));
					g2.fillOval(column * 90 + 36, row * 90 + 36, 25, 25);
				}
			}
		}

		if (whiteInMate)
		{
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 750, 750);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("serif", Font.PLAIN, 50));
			g2.drawString("Black Wins!", 220, 300);
		}

		if (blackInMate) 
		{
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 750, 750);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("serif", Font.PLAIN, 50));
			g2.drawString("White Wins!", 220, 300);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// use math to figure out the row and column that was clicked.



		if(click == false){
			from.column = e.getX()/90;
			from.row = e.getY()/90;

			//while in check then if neither are mated decide which move
			if ((board[blackKing.getRow()][blackKing.getColumn()].isInCheck() || board[whiteKing.getRow()][whiteKing.getColumn()].isInCheck()))
			{
					ArrayList<Location> moves = new ArrayList<>();
					moves = board[from.row][from.column].getMovesFrom(from.row, from.column);

					if (moves.size() != 0)
					{
						for (Location m : moves)
						{
							board[m.row][m.column].setValid(true);
						}
					}

				}
			} else {
				//check all moves to see if they are valid
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						if (board[from.row][from.column].isValidMove(from, new Location(i, j), board) && board[from.row][from.column].getTeam() == turn)
						{
							board[i][j].setValid(true);
						}
						else
						{
							board[i][j].setValid(false);
						}
					}
				}
			}
			click = true;
		}


		else{
			to.column = e.getX()/90;
			to.row = e.getY()/90;

			//if the space selected in the click has a dot
			if(board[to.row][to.column].isValid()){

				
				
				// move piece
				board[to.row][to.column] = board[from.row][from.column];
				board[from.row][from.column] = new Space();
				

				
				//castle logic
				if (whiteKing.row == 0 && whiteKing.column == 3)
				{
					if (board[0][5] instanceof King)
					{
						board[0][4] = new Rook(1);
						board[0][7] = new Space();
							
					}
					else if (board[0][1] instanceof King)
					{
						board[0][2] = new Rook(1);
						board[0][0] = new Space();
					}
				}
				if (blackKing.row == 7 && blackKing.column == 3)
				{
					if (board[7][5] instanceof King)
					{
						board[7][4] = new Rook(2);
						board[7][7] = new Space();
							
					}
					else if (board[7][1] instanceof King)
					{
						board[7][2] = new Rook(2);
						board[7][0] = new Space();
					}
				}
				
				
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						board[i][j].setValidMoves(new ArrayList<Move>());
					}
				}

				//remember where the kings are
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						if (board[i][j] instanceof King)
						{
							if (board[i][j].getTeam() == 1)
							{
								whiteKing = new Location(i, j);
							} else {
								blackKing = new Location(i, j);
							}
						}
					}
				}

				//nothing is in check
				for (int x = 0; x < 8; x++)
				{
					for (int y = 0; y < 8; y++)
					{
						board[x][y].setInCheck(false);
					}
				}

				//is black king in check
				if (turn == 1)
				{
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if (board[i][j].isValidMove(new Location(i, j), blackKing, board))
							{
								board[blackKing.getRow()][blackKing.getColumn()].setInCheck(true);
							}
						}
					}
				}

				//is white king in check
				else
				{
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if (board[i][j].isValidMove(new Location(i, j), whiteKing, board))
							{
								board[whiteKing.getRow()][whiteKing.getColumn()].setInCheck(true);
							}
						}
					}
				}


				//if either is in check (gonna check for mate)
				if (board[blackKing.getRow()][blackKing.getColumn()].isInCheck() || board[whiteKing.getRow()][whiteKing.getColumn()].isInCheck())
				{
					System.out.println("this");
					printBoard();
					Location white = new Location();
					Location black = new Location();
					//for white
					if (turn == 2)
					{
						for (int a = 0; a < 8; a++)
						{
							for (int b = 0; b < 8; b++)
							{
								if (board[a][b].getTeam() == 1)
								{
									for (int c = 0; c < 8; c++)
									{
										for (int d = 0; d < 8; d++)
										{
											//checking each move

											if (board[a][b].isValidMove(new Location(a, b), new Location(c, d), board))
											{
												for (int i = 0; i < 8; i++)
												{
													for (int j = 0; j < 8; j++)
													{
														pretendBoard[i][j] = board[i][j];
													}
												}
												pretendBoard[c][d] = pretendBoard[a][b];
												pretendBoard[a][b] = new Space();
												for (int k = 0; k < 8; k++)
												{
													for (int l = 0; l < 8; l++)
													{
														if (pretendBoard[k][l] instanceof King && pretendBoard[k][l].getTeam() == 1)
														{
															white.setRow(k);
															white.setColumn(l);
														}
													}
												}

												//if a move moves out of check add it to an arraylist
												if (!(pretendBoard[white.row][white.column].isPieceInCheck(white, pretendBoard)))
												{
													board[a][b].addMove(new Move(a, b, c, d));
												}

											}
										}
									}
								}
							}
						}
						//checks to see if any moves to leave check are stored
						boolean temp = true;
						for (int f = 0; f < 8; f++)
						{
							for (int g = 0; g < 8; g++)
							{
								if (board[f][g].getValidMoves().size() != 0)
									temp = false;
							}
						}
						//can declare white in checkmate
						whiteInMate = temp;

					}

					//for black
					else
					{
						for (int a = 0; a < 8; a++)
						{
							for (int b = 0; b < 8; b++)
							{
								if (board[a][b].getTeam() == 2)
								{
									for (int c = 0; c < 8; c++)
									{
										for (int d = 0; d < 8; d++)
										{
												//checking each move
												if (board[a][b].isValidMove(new Location(a, b), new Location(c, d), board))
												{
													for (int i = 0; i < 8; i++)
													{
														for (int j = 0; j < 8; j++)
														{
															pretendBoard[i][j] = board[i][j];
														}
													}
													pretendBoard[c][d] = pretendBoard[a][b];
													pretendBoard[a][b] = new Space();
													for (int k = 0; k < 8; k++)
													{
														for (int l = 0; l < 8; l++)
														{
															if (pretendBoard[k][l] instanceof King && pretendBoard[k][l].getTeam() == 2)
															{
																black.setRow(k);
																black.setColumn(l);
															}
														}
													}
													
													//if a move moves out of check add it to an arraylist
													if (!(pretendBoard[black.row][black.column].isPieceInCheck(black, pretendBoard)))
													{
														board[a][b].addMove(new Move(a, b, c, d));
													}
												}
											
										}
									}
								}
							}
						}

						//checks to see if black can escape check with any moves
						boolean temp = true;
						for (int f = 0; f < 8; f++)
						{
							for (int g = 0; g < 8; g++)
							{
								if (board[f][g].getValidMoves().size() != 0)
									temp = false;	
							}
						}
						//can declare black in checkmate
						blackInMate = temp;
						System.out.println(blackInMate);
					}
				}


				//switches turn
				if(turn == 1)
					turn = 2;
				else
					turn = 1;
			}

			//removes dot from all pieces
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					board[i][j].setValid(false);
				}
			}

			//Promoting pawns to queens
			for(int i =0; i<8;i++) {
				if(board[0][i] instanceof Pawn) {
					board[0][i] = new Queen(2);
				}
						if	(board[7][i] instanceof Pawn) {
							board[7][i] = new Queen(1);
						}

			}
			

			click = false;
		}
		this.repaint();
	}

	public void printBoard(){
		for(int row = 0; row<8; row++){
			for(int column = 0; column <8; column++)
				System.out.print(board[row][column] + " ");
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
