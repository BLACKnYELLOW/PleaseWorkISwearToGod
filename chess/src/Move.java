
public class Move {
	private int from1;
	private int from2;
	private int to1;
	private int to2;
	
	public Move(int a, int b, int c, int d)
	{
		from1 = a;
		from2 = b;
		to1 = c;
		to2 = d;
	}

	public int getFrom1() {
		return from1;
	}

	public void setFrom1(int from1) {
		this.from1 = from1;
	}

	public int getFrom2() {
		return from2;
	}

	public void setFrom2(int from2) {
		this.from2 = from2;
	}

	public int getTo1() {
		return to1;
	}

	public void setTo1(int to1) {
		this.to1 = to1;
	}

	public int getTo2() {
		return to2;
	}

	public void setTo2(int to2) {
		this.to2 = to2;
	}
}
