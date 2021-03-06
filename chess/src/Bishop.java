//project name: Bishop.java
//written by: Asher Polsky, Grant Curry, Nate Grunfeld
//date: Mar 22, 2022
//description: Bishop Piece extends the piece class and determines if a move is valid 
//based on if the bishop's movement behavior
public class Bishop extends Piece
{	
	public Bishop(int p){
		super(p, 5, "bishop");
	}
	
	public Bishop(){
		this(1);
	}
	
	//method: isValidMove
	//parameters: Location from, Location to, Piece[][]b
	//return: boolean
	//description: checks to see if the move a player wants to make is valid
	//based on the bishop's movement behavior then sets x and y coordinates to new values.
	//returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && (isValidDiagonalMove(from, to, b));
	}
	
	public String toString(){
		return "b";
	}
}
