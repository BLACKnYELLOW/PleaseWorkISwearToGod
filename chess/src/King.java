//project name: King.java
//written by: Asher Polsky, Grant Curry, Nate Grunfeld
//date: Mar 22, 2022
//description: King Piece extends the piece class, determines if a move is valid 
//based on if the piece moves 1 space and if it moves into check or not
public class King extends Piece {

	public King(int p) {
		super(p, 5, "King");
	}
	public King(){
		this(1);
	}
	
	//method: isValidMove
	//parameters: Location from, Location to, Piece[][]b
	//return: boolean
	//description: checks to see if the move a player wants to make is valid
	//based on the king's movement behavior and if it moves into check or not then sets x and y coordinates to new values.
	//returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return ((super.isValidKingMove(from, to, b) || super.isValidCastle(from, to, b)) && (super.isValidMove(from, to, b)) && super.doesItGoIntoCheck(from, to, b));
	}
	
	public String toString(){
		return "K";
	}
}
