package hu.bence.jatek;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.bence.jatek.model.Player;

public class PlayerTest {

	@Test
	public void testGetScore() throws Exception {
		
		final Player testPlayer = new Player();
		testPlayer.setScore(100);
		
		assertEquals("Score is not match.", testPlayer.getScore(), 100);
	}
	
	@Test
	public void secondTestGetScore() throws Exception {
		
		final Player testPlayer = new Player(1, "Teszt Jatekos", 50, 0, 0);
		testPlayer.setScore(25);
		
		assertEquals("Score has not updated with setting", testPlayer.getScore(), 25);
	}
	
	@Test
	public void equalsTest() throws Exception {
		
		Player one = new Player(2, "First Person", 100,0 ,0);
		Player two = new Player(3, "Second Person", 150,0,0);
		
		one.setPlayerId(3);
		one.setUserName("Second Person");
		one.setScore(150);
		
		assertTrue(one.equals(two));
		
	}
	
}
