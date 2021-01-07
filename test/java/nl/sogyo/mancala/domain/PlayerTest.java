package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
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
}
