package nl.sogyo.mancala.domain;

public class Player {
	private int player;
	public Player() {
		this.player = 0;
	}
	public int getPlayer() {
		return this.player;
	}
	public int nextPlayer() {
		this.player = ((this.player+1)%2);
		return this.player;
	}
}
