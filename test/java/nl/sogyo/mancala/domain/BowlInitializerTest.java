package nl.sogyo.mancala.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

public class BowlInitializerTest {
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
}
