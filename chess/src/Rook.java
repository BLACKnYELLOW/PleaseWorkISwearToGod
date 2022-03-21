
public class Rook extends Piece {
	public Rook(int p){
		super(p, 11, "rook");
	}
	
	public Rook(){
		this(1);
	}
	
	// check to see if the move is valid, and then sets x and y coordinates to new values.
	// returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && ((isValidHorizontalMove(from, to, b) || (isValidVerticalMove(from, to, b))));
	}
	
	public String toString(){
		return "r";
	}
}
