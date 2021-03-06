// project: Chess
// written by: Mr. Swope
// date: Mar 16, 2022
// description: Implementation for the Pawn class, which is a subclass of the Piece class.
//              isValidMove will be called when you attempt to move a Pawn.
public class Pawn extends Piece
{

	public Pawn(int p){
		super(p, 13, "pawn");
	}
	
	public Pawn(){
		this(0);
	}
	
	
	// method: isValidMove
	// description: checks to see if the move is valid.
	// parameters: Location from - the Location on the board that the Pawn is moving from.
	//             Location to - the Location on the board that the Pawn is attempting to move to.
	//             Piece[][]b - the current board state.
	// return: true if it is a valid move, otherwise return false
	public boolean isValidMove(Location from, Location to, Piece[][]b) {
		boolean success = false;
				// player 1 move forward 1
		if(((getTeam() == 1 && from.getColumn() == to.getColumn() && from.getRow()+1 == to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==0)||
				// player 1 move forward 2 on first pawn move
				(getTeam() == 1 && from.getColumn() == to.getColumn() && from.getRow() == 1 && from.getRow()+2 == to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==0)||
				// player 2 move forward 1
				(getTeam() == 2 && from.getColumn() == to.getColumn() && from.getRow()-1 ==to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==0)||
				// player 2 move forward 2 on first pawn move
				(getTeam() == 2 && from.getColumn() == to.getColumn() && from.getRow() == 6 && from.getRow()-2 == to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==0))||
				// player 1 move diagonally down to the right
				(getTeam() == 1 && from.getColumn()+1 == to.getColumn() && from.getRow()+1==to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==2)||
				// player 1 move diagonally down to the left
				(getTeam() == 1 && from.getColumn()-1 ==to.getColumn() && from.getRow()+1==to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==2)||
				// player 2 move diagonally up to the right
				(getTeam() == 2 && from.getColumn()+1 ==to.getColumn() && from.getRow()-1==to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==1)||
				// player 2 move diagonally up to the left
				(getTeam() == 2 && from.getColumn()-1 ==to.getColumn() && from.getRow()-1==to.getRow() && b[to.getRow()][to.getColumn()].getTeam()==1))
			success = true;
				
		return success;
	}
	
	public String toString(){
		return "p";
	}
}
