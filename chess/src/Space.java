
public class Space extends Piece
{
	public Space(){
		super();
	}
		
	public String toString(){
		return "-";
	}
	
	public boolean isValidMove(Location from, Location to, Piece[][]b)
	{
		return false;
	}
}

