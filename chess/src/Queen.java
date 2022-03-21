
public class Queen extends Piece {
	public Queen(int p){
		super(p, 3, "queen");
	}
	
	public Queen(){
		this(1);
	}
	
	// check to see if the move is valid, and then sets x and y coordinates to new values.
	// returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && ((isValidHorizontalMove(from, to, b) || (isValidVerticalMove(from, to, b) || (isValidDiagonalMove(from, to, b)))));
	}
	
	public String toString(){
		return "q";
	}
}
