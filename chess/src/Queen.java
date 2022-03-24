//project name: Queen.java
//written by: Asher Polsky, Grant Curry, Nate Grunfeld
//date: Mar 22, 2022
//description: Queen Piece extends the piece class, determines if a move is valid 
//based on queen's movement behavior
public class Queen extends Piece {
	public Queen(int p){
		super(p, 3, "queen");
	}
	
	public Queen(){
		this(1);
	}
	
	//method: isValidMove
	//parameters: Location from, Location to, Piece[][]b
	//return: boolean
	//description: checks to see if the move a player wants to make is valid
	//based on the queen's movement behavior then sets x and y coordinates to new values.
	//returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && ((isValidHorizontalMove(from, to, b) || (isValidVerticalMove(from, to, b) || (isValidDiagonalMove(from, to, b)))));
	}
	
	public String toString(){
		return "q";
	}
}
