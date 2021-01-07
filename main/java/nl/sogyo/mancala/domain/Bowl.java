package nl.sogyo.mancala.domain;

public class Bowl implements LikeBowl{
	private int stones;
	private int index;
	private int owner;
	private LikeBowl neighbor;
	public Bowl() {
		this.index = 0;
		this.stones = 4;
		this.owner = 0;
		this.neighbor = new Bowl(this.index+1, this);
	}
	public Bowl(int index, Bowl first) {
		this.index = index;
		this.stones = 4;
		this.owner = index/7;
		if(index==5 || index==12) {
			this.neighbor = new Kalaha(this.index+1, first);
		}
		else {
			this.neighbor = new Bowl(this.index+1, first);
		}
	}
	public int getStones() {
		return this.stones;
	}
	public int getIndex() {
		return this.index;
	}
	public int getOwner() {
		return this.owner;
	}
	public LikeBowl getNeighbor() {
		return this.neighbor;
	}
	public LikeBowl getNeighbor(int num) {
		return num <1 ? this : this.getNeighbor().getNeighbor(num - 1);
	}
	public LikeBowl getOpposite() {
		return this.getNeighbor(12-(this.index%7)*2);
	}
	public boolean playTurn(Player player) {
		if(this.owner == player.getPlayer() && this.stones > 0) {
			int content = this.stones;
			this.passStones(player,this.stones);
			// this.stones = 0;
			this.stones = this.stones-content;
			return true;
		}
		return false;
	}
	private void passStones(Player player, int cairn) {
		if(cairn>0) {
			this.getNeighbor().getStonesInTurn(player, cairn);
		} 
		else {
			this.doThieve(player);
			player.nextPlayer();
		}
	}
	public void getStonesInTurn(Player player, int cairn) {
		this.stones++;
		cairn--;
		this.passStones(player,cairn);
	}
	private void doThieve(Player player) {
		if(this.owner==player.getPlayer() && this.stones == 1) {
			int stash = this.letSteal()+this.getOpposite().letSteal();
			this.passTheft(stash);
		}
	}
	public int letSteal() {
		int out = this.stones;
		this.stones = 0;
		return out;
	}
	public void passTheft(int stash) {
		this.getNeighbor().passTheft(stash);
	}
	public boolean gameEndCheck() {
		boolean[] state = new boolean[2];
		state[0]=true;
		state[1]=true;
		state[this.owner]=(this.stones==0);
		state = this.getNeighbor().gameEndCheck(1,state);
		return (state[0] || state[1]);
	}
	public boolean[] gameEndCheck(int num, boolean[] state) {
		state[this.owner]=(state[this.owner] && this.stones==0);
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
	}
}
