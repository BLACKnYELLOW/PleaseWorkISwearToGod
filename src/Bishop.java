
public class Bishop extends Piece
{	
	public Bishop(int p){
		super(p, 5, "bishop");
	}
	
	public Bishop(){
		this(1);
	}
	
	// check to see if the move is valid, and then sets x and y coordinates to new values.
	// returns true if move was valid, and false if it was not valid.
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && (isValidDiagonalMove(from, to, b));
	}
	
	public String toString(){
		return "b";
	}
}
