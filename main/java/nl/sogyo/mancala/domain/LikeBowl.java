package nl.sogyo.mancala.domain;

public interface LikeBowl {
	public int getStones();
	public int getIndex ();
	public int getOwner ();
	public LikeBowl getNeighbor();
	public LikeBowl getNeighbor(int number);
	public LikeBowl getOpposite();
	public boolean playTurn(Player player);
	public void getStonesInTurn(Player player, int stack);
	public int letSteal();
	public void passTheft(int stash);
	public boolean gameEndCheck();
	public boolean[] gameEndCheck(int i, boolean[] state);
	public int gameWinCheck();
	public int[] gameWinCheck(int i, int[] totals);
}
