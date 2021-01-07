package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class AllTests {
	// Check if player is properly initialised.
	@Test
    public void startingPlayerIsPlayer0()
    {
        var player = new Player();
        var answer = player.getPlayer();
        assertEquals(0, answer);
    }
	// Check if player update method functions properly (1/2).
	@Test
	public void secondPlayerIsPlayer1()
	{
		var player = new Player();
		player.nextPlayer();
		var answer = player.getPlayer();
		assertEquals(1, answer);
	}
	// Check if player update method functions properly (2/2).
	@Test
	public void thirdPlayerIsPlayer0()
	{
		var player = new Player();
		player.nextPlayer();
		player.nextPlayer();
		var answer = player.getPlayer();
		assertEquals(0, answer);
	}
	// Check if first bowl number of stones is initialised properly.
	@Test
	public void bowlContains4Stones()
	{
		var bowl = new Bowl();
		var answer = bowl.getStones();
		assertEquals(4, answer);
	}
	// Check if first bowl index is initialised properly.
	@Test
	public void bowlIndexIs0()
	{
		var bowl = new Bowl();
		var answer = bowl.getIndex();
		assertEquals(0, answer);
	}
	// check if first bowl owner is initialised properly.
	@Test
	public void bowlOwnerIs0()
	{
		var bowl = new Bowl();
		var answer = bowl.getOwner();
		assertEquals(0, answer);
	}
	// check if neighbour of first bowl is a bowl.
	@Test
	public void bowlNeighborIsBowl() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor().getClass();
		assertEquals(Bowl.class, answer);
	}
	// check if neighbour of first bowl has 4 stones.
	@Test
	public void bowlNeighborHas4Stones() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor().getStones();
		assertEquals(4, answer);
	}
	// check if neighbour of first bowl has owner 0.
	@Test
	public void bowlNeighborHasOwner0() {
		var bowl=new Bowl();
		var answer = bowl.getNeighbor().getOwner();
		assertEquals(0,answer);
	}
	// check if neighbour of first bowl has index 2.
	@Test
	public void bowlNeighborHasIndex1() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor().getIndex();
		assertEquals(1, answer);
	}
	// check if alternative getNeighbor function works for 1 step.
	@Test
	public void bowlNeighborIsBowlNeighbor() {
		var bowl=new Bowl();
		var neighborA = bowl.getNeighbor();
		var neighborB = bowl.getNeighbor(1);
		assertEquals(neighborA, neighborB);
	}
	// check if six neighbours further down, it is a kalaha.
	@Test
	public void bowlNeighbor6IsKalaha() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor(6).getClass();
		assertEquals(Kalaha.class, answer);
	}
	// check if six neighbours further down, it has index 7.
	@Test
	public void bowlNeighbor6HasIndex6() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor(6).getIndex();
		assertEquals(6,answer);
	}
	// check if six neighbours further down, it has 0 stones.
	@Test
	public void bowlNeighbor6Has0Stones() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor(6).getStones();
		assertEquals(0,answer);
	}
	// check if six neighbours further down, it has owner 0.
	@Test
	public void firstKahalaHasOwner0() {
		var bowl = new Bowl();
		var answer = bowl.getNeighbor(6).getOwner();
		assertEquals(0,answer);
	}
	// check if 14 neighbours further down ends up at the same bowl.
	@Test	
	public void bowlNeighbor14Isbowl() {
		var bowl = new Bowl();
		var other = bowl.getNeighbor(14);
		assertSame(bowl,other);
	}
	// check if all bowls/kalahas have the right owners.
	@Test
	public void allBowlsHaveCorrectOwners() {
		int[] correct = {0,0,0,0,0,0,0,1,1,1,1,1,1,1};
		int[] actual = new int[14];
		var bowl = new Bowl();
		actual[0]=bowl.getOwner();
		for(int i=1;i<14;i++) {
			actual[i] = bowl.getNeighbor(i).getOwner();
		}
		assertArrayEquals(actual,correct);
	}
	// check if all bowls/kalahas have the right number of stones.
	@Test
	public void allBowlsHaveCorrectStones() {
		int[] correct = {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
		int[] actual = new int[14];
		var bowl = new Bowl();
		actual[0]=bowl.getStones();
		for(int i=1;i<14;i++) {
			actual[i] = bowl.getNeighbor(i).getStones();
		}
		assertArrayEquals(actual,correct);
	}
	// check if all bowls/kalahas have the right index.
	@Test
	public void allBowlsHaveCorrectIndex() {
		int[] correct = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] actual = new int[14];
		var bowl = new Bowl();
		actual[0]=bowl.getIndex();
		for(int i=1;i<14;i++) {
			actual[i] = bowl.getNeighbor(i).getIndex();
		}
		assertArrayEquals(actual,correct);
	}
	// check if opposites of all bowls are correct.
	@Test
	public void allBowlsReportCorrectOpposites() {
		int[] correct = {12,11,10,9,8,7,13,5,4,3,2,1,0,6};
		int[] actual = new int[14];
		var bowl = new Bowl();
		actual[0] = bowl.getOpposite().getIndex();
		for(int i=1;i<14;i++) {
			actual[i]=bowl.getNeighbor(i).getOpposite().getIndex();
		}
		assertArrayEquals(correct,actual);
	}
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
	// Most tests need a game at its end stage.
	public Bowl setupGame(Bowl bowl) {
		bowl.letSteal();
		for(int i =1; i<5;i++) {
			bowl.getNeighbor(i).letSteal();
		}
		return bowl;
		// Of course this is a very artificial game state, as bowls
		// should not be emptied like this, but it aids in testing.
	}
	
	// If one side is empty, gameEndCheck should return true.
	@Test
	public void emptyOneSideEndsGame() {
		Bowl bowl = setupGame(new Bowl());
		var player = new Player();
		var answer = bowl.gameEndCheck();
		assertEquals(false,answer);
		bowl.getNeighbor(5).playTurn(player);
		answer = bowl.getNeighbor(6).gameEndCheck();
		assertEquals(true,answer);
	}
	// gameWinCheck should return the player with the most points.
	@Test
	public void gameWinCheckCallsCorrectWinner() {
		Bowl bowl = new Bowl();
		var answer = bowl.gameWinCheck();
		assertEquals(-1,answer);
		bowl = setupGame(bowl);
		answer = bowl.gameWinCheck();
		assertEquals(1,answer);
		bowl.getNeighbor(6).passTheft(49);
		answer = bowl.getNeighbor(2).gameWinCheck();
		assertEquals(0,answer);
		answer = bowl.getNeighbor(6).gameWinCheck();
		assertEquals(0,answer);
	}
}