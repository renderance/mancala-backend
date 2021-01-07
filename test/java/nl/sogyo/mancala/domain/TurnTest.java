package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TurnTest {
	// check if bowl that is selected to play a turn empties itself.
	@Test
	public void bowlPlaysTurnGetsEmtpy() {
		var bowl = new Bowl();
		var player = new Player();
		bowl.playTurn(player);
		var answer = bowl.getStones();
		assertEquals(0,answer);
	}
	// check if neighbouring bowl has one more stone.
	@Test
	public void bowlPlaysTurnNeighborAdds1() {
		var bowl = new Bowl();
		var player = new Player();
		var before = bowl.getNeighbor().getStones();
		bowl.playTurn(player);
		var after = bowl.getNeighbor().getStones();
		assertEquals(before+1,after);
	}
	// check if invalid choice of playing a turn returns false.
	@Test
	public void bowlPlayTurnInvalidReturnsFalse() {
		var bowl = new Bowl();
		var player = new Player();
		var answer = bowl.getNeighbor(8).playTurn(player);
		assertEquals(false,answer);
	}
	// check if choosing kalaha to play a turn returns false.
	@Test
	public void kalahaPlaysTurnReturnsFalse() {
		var bowl = new Bowl();
		var player = new Player();
		var answer = bowl.getNeighbor(6).playTurn(player);
		assertEquals(false,answer);
	}
	// check if valid choice of playing a turn returns true.
	@Test
	public void bowlPlayTurnValidReturnsTrue() {
		var bowl = new Bowl();
		var player = new Player();
		player.nextPlayer();
		var answer = bowl.getNeighbor(8).playTurn(player);
		assertEquals(true,answer);
	}
	// check if kalaha at index 6 receives from player 0.
	@Test
	public void kalaha6ReceivesFromPlayer0() {
		var bowl = new Bowl();
		var player = new Player();
		bowl.getNeighbor(6).getStonesInTurn(player, 1);
		var answer = bowl.getNeighbor(6).getStones();
		assertEquals(1,answer);
	}
	// check if kalaha at index 6 refuses from player 1.
	@Test
	public void kalaha6RefusesFromPlayer1() {
		var bowl = new Bowl();
		var player = new Player();
		player.nextPlayer();
		bowl.getNeighbor(6).getStonesInTurn(player, 1);
		var answer = bowl.getNeighbor(6).getStones();
		assertEquals(0,answer);
	}
	// check if kalaha at index 6 neighbour receives refused stone.
	@Test
	public void kalaha6RefusesAndPassesOnStone() {
		var bowl = new Bowl();
		var player = new Player();
		player.nextPlayer();
		bowl.getNeighbor(6).getStonesInTurn(player, 1);
		var answer = bowl.getNeighbor(7).getStones();
		assertEquals(5,answer);
	}
	// check if a bowl gives up all of its stones when they are stolen.
	@Test
	public void emptyBowlAfterSteal() {
		var bowl = new Bowl();
		var before = bowl.getStones();
		var stash = bowl.letSteal();
		var after = bowl.getStones();
		assertEquals(4,before);
		assertEquals(4,stash);
		assertEquals(0,after);
	}
	// check if bowl that is thief empties itself.
	/* Can no longer fire, because beThief became private.
	 * Functionality is checked by:
	 * 		theftOccursAfterTwoTurns()
	@Test
	public void emptyBowlAfterBeingThief() {
		var bowl = new Bowl();
		var before = bowl.getStones();
		bowl.beThief();
		var after = bowl.getStones();
		assertEquals(4,before);
		assertEquals(0,after);
	}
	*/
	// check if kalaha receives stash when thieving happens.
	/* Can no longer fire, because beThief became private.
	 * Functionality is checked by:
	 * 		theftOccursAfterTwoTurns()
	@Test
	public void kalahaReceivesStash() {
		var bowl = new Bowl();
		bowl.beThief();
		var answer = bowl.getNeighbor(6).getStones();
		assertEquals(8,answer);
	}
	*/
	// check if first bowl is forbidden from thieving.
	/* Can no longer fire, because mayThieve became private.
	* Functionality is checked by:
	 * 		theftOccursAfterTwoTurns()
	@Test
	public void firstBowlMayNotThieve() {
		var bowl = new Bowl();
		var player = new Player();
		var answer = bowl.mayThieve(player);
		assertEquals(false, answer);
	}
	*/
	// play a set to theft and check that theft occurred.
	@Test
	public void theftOccursAfterTwoTurns() {
		var bowl = new Bowl();
		var player = new Player();
		bowl.getNeighbor(4).playTurn(player);
		bowl.getNeighbor(7).playTurn(player);
		bowl.playTurn(player);
		var bowl4 = bowl.getNeighbor(4).getStones();
		var bowl4opposite = bowl.getNeighbor(4).getOpposite().getStones();
		var bowl6 = bowl.getNeighbor(6).getStones();
		assertEquals(0,bowl4);
		assertEquals(0,bowl4opposite);
		assertEquals(8,bowl6);
	}
}
