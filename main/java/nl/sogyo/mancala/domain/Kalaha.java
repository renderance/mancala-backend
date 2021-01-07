package nl.sogyo.mancala.domain;

public class Kalaha implements LikeBowl{
	private int stones;
	private int index;
	private int owner;
	private LikeBowl neighbor;
	public Kalaha(int index, Bowl first) {
		this.stones = 0;
		this.index = index;
		this.owner = index/7;
		if(index == 6) {
			this.neighbor = new Bowl(index+1, first);
		}
		else if(index == 13){
			this.neighbor = first;
		}
	}
	public int getStones() {
		return stones;
	}
	public int getIndex() {
		return index;
	}
	public int getOwner() {
		return owner;
	}
	public LikeBowl getNeighbor() {
		return neighbor;
	}
	public LikeBowl getNeighbor(int num) {
		return num <1 ? this : this.getNeighbor().getNeighbor(num - 1);
	}
	public LikeBowl getOpposite() {
		return this.getNeighbor(7);
	}
	public boolean playTurn(Player player) {
		return false;
	}
	public void getStonesInTurn(Player player, int cairn) {
		if(this.owner==player.getPlayer()) {
			this.stones++;
			cairn--;
		}
		this.passStones(player, cairn);
	}
	private void passStones(Player player, int cairn) {
		if(cairn>0) {
			this.getNeighbor().getStonesInTurn(player, cairn);
		} 
	}
	public int letSteal() {
		return 0;
	}
	public void passTheft(int stash) {
		this.stones=this.stones+stash;
	}
	public boolean gameEndCheck() {
		boolean[] state = new boolean[2];
		state[0]=true;
		state[1]=true;
		state = this.getNeighbor().gameEndCheck(1,state);
		return (state[0] || state[1]);
	}
	public boolean[] gameEndCheck(int num, boolean[] state) {
		state = num<13 ? this.getNeighbor().gameEndCheck(num+1,state) : state;
		return state;
	}
	public int gameWinCheck() {
		int[] totals = new int[2];
		totals[0]=0;
		totals[1]=0;
		totals[this.owner]=this.stones;
		totals = this.getNeighbor().gameWinCheck(1,totals);
		return totals[0]==totals[1] ? -1 : 
				totals[0]>totals[1] ? 0 : 1;
	}
	public int[] gameWinCheck(int num, int[] total) {
		total[this.owner]=total[this.owner]+this.stones;
		total = num<13 ? this.getNeighbor().gameWinCheck(num+1,total) : total;
		return total;
	}}



