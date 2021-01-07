package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EndGameTest {

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
	}
}
