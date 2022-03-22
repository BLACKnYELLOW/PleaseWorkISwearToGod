
public class King extends Piece {

	public King(int p) {
		super(p, 5, "King");
	}
	public King(){
		this(1);
	}
	
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return super.isValidMove(from, to, b) && super.isValidKingMove(from, to, b);
	}
	public String toString(){
		return "K";
	}
}
