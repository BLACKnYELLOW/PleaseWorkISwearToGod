// Class: Piece
// Written by: Mr. Swope
// Date: 10/-18/15
// Description: This class implements a Piece.  This Piece will be drawn onto a graphics panel. 
// 
// If you modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

public class Piece {
	private ImageIcon image;			// The ImageIcon will be used to hold the Character's png.
	// This png must be saved in the images folder and will be loaded 
	// in the constructor.

	private int team;					// This int will represent which team the piece is, 1 for yellow team, 
	// -1 for black team, 0 for an empty space.
	// alternatiely you can leave empty spots empty and check for null instead of 0.
	private int offset; 				// used for drawing images because they all have different widths.

	private boolean valid;
	
	// method: Default constructor - see packed constructors comments for a description of parameters.
	public Piece(){
		this(0,0);
	}

	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: int team - should be either 1 or -1. 1 for yellow team, -1 for black team.
	public Piece(int team, int offset){
		this(0,0,"space");			
	}

	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: int team - should be either 1 or -1. 1 for yellow team, -1 for black team.
	public Piece(int team, int offset, String imagePath){
		if(team == 0)
			setImageIcon("images/space.png");
		else
			setImageIcon("images/" + imagePath + team + ".png");
		this.offset = offset;
		this.setTeam(team);		
		setValid(false);
	}

	protected void setImageIcon(String imagePath){
		ClassLoader cldr = this.getClass().getClassLoader();	

		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);
	}

	// method: isValidMove
	// description: This method checks to see if a move is valid.
	// Returns whether or not the attempted move is valid.
	// @param - Location from - the location that the piece will be moved from
	// @param - Location to - the location that the piece will be moved to
	// @param - Piece[][]b - the chess board.  a two dimensional array of pieces.
	// return - boolean - true if the move is valid 
	public boolean isValidMove(Location from, Location to, Piece[][]b){

		// make sure that you're not landing on a piece from the same team.
		if (b[from.getRow()][from.getColumn()].equals(b[to.getRow()][to.getColumn()]))
			return false;

		return true;
	}



	// method: isValidHorizontalMove
	// description: This method checks to see if a move is valid horizontal move. You should use it in the rook and queen class.
	// Returns whether or not the attempted move is valid.
	// @param - Location from - the location that the piece will be moved from
	// @param - Location to - the location that the piece will be moved to
	// @param - Piece[][]b - the chess board.  a two dimensional array of pieces.
	// return - boolean - true if the move is valid 
	public boolean isValidHorizontalMove(Location from, Location to, Piece[][]b){
		// check to make sure that the move remains in the same row.
		if(from.getRow() != to.getRow() || from.getColumn() == to.getColumn())
				return false;

		// look for any pieces in between the starting and ending Locations.
		// if you find location that isn't empty than return false.
		for(int i = Math.min(from.getColumn(), to.getColumn())+1; i<Math.max(from.getColumn(), to.getColumn()); i++){
				if(b[from.getRow()][i].getTeam()!=0) 
					return false;
				}

				return true;
		}

	// method: isValidVerticalMove
	// description: This method checks to see if a move is valid vertical move. You should use it in the rook and queen class.
	// Returns whether or not the attempted move is valid.
	// @param - Location from - the location that the piece will be moved from
	// @param - Location to - the location that the piece will be moved to
	// @param - Piece[][]b - the chess board.  a two dimensional array of pieces.
	// return - boolean - true if the move is valid 
	public boolean isValidVerticalMove(Location from, Location to, Piece[][]b){
		// check to make sure that the move remains in the same column.
		if(from.getColumn() != to.getColumn() || from.getRow() == to.getRow())
				return false;

		// look for any pieces in between the starting and ending Locations.
		// if you find location that isn't empty than return false.
		for(int i = Math.min(from.getRow(), to.getRow())+1; i<Math.max(from.getRow(), to.getRow()); i++){
			if(b[i][from.getColumn()].getTeam()!=0) 
				return false;
			}

			return true;
		}

	public boolean isValidKnightMove(Location from, Location to, Piece[][]b)
	{
		if (((to.getRow() == from.getRow() + 2 || to.getRow() == from.getRow() - 2) && (to.getColumn() == from.getColumn() + 1 || to.getColumn() == from.getColumn() - 1)) || ((to.getRow() == from.getRow() + 1 || to.getRow() == from.getRow() - 1) && (to.getColumn() == from.getColumn() + 2 || to.getColumn() == from.getColumn() - 2)) )
		{
			return true;
		}
		return false;
	}
	
	// method: isValidDiagonalMove
	// description: This method checks to see if a move is valid vertical move. You should use it in the bishop and queen class.
	// Returns whether or not the attempted move is valid.
	// @param - Location from - the location that the piece will be moved from
	// @param - Location to - the location that the piece will be moved to
	// @param - Piece[][]b - the chess board.  a two dimensional array of pieces.
	// return - boolean - true if the move is valid 
	public boolean isValidDiagonalMove(Location from, Location to, Piece[][]b) {

		// check to see if the move is in fact in a diagonal line.
		if(from.getRow() - to.getRow() != from.getColumn() - to.getColumn() &&
				from.getRow() - to.getRow() != -1*(from.getColumn() - to.getColumn())) 
			return false;

		// down to right or up to left
		if ((from.getRow() < to.getRow() && from.getColumn() < to.getColumn()) ||
				(from.getRow() > to.getRow() && from.getColumn() > to.getColumn())){
			int y = Math.min(from.getColumn(), to.getColumn())+1;
			for(int i = Math.min(from.getRow(), to.getRow())+1; i < Math.max(from.getRow(), to.getRow()); i++){
				if(b[i][y].getTeam()!=0) 
					return false;
				y +=1;
			}
		}
		// down to left or up to right
		else{
			int y = Math.max(from.getColumn(), to.getColumn())-1;
			for(int i = Math.min(from.getRow(), to.getRow())+1; i < Math.max(from.getRow(), to.getRow()); i++){
				if(b[i][y].getTeam()!=0) 
					return false;
				y -=1;
			}
		}

		return true;
	}
	// method: draw
	// description: This method is used to draw the image onto the GraphicsPanel.  You shouldn't need to 
	//				modify this method.
	// parameters: Graphics g - this object draw's the image.
	//			   Component c - this is the component that the image will be drawn onto.
	//			   Location l - a Location that determines where to draw the piece.
	public void draw(Graphics g, Component c, Location l) {
		image.paintIcon(c, g, l.column*90 + offset + 5, l.row*90+10); // you'll need to update the last two parameters so that it will 
		// correctly draw the piece in the right location.
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String toString(){
		return "piece";
	}

	// Use the equals method to check to see if pieces are on the same team.
	public boolean equals(Object o) {
		Piece p = (Piece)o;

		return p.getTeam() == this.getTeam();
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
