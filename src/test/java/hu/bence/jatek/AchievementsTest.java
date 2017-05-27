package hu.bence.jatek;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.bence.jatek.model.Player;

public class AchievementsTest {
	
	@Test
	public void unlockFirstAchievement() {
		
		Player player = new Player(1, "Tesztelés", 5, 0, 0);
		
		assertTrue(player.hasThePlayerAnsweredHisFirstQuestion());
	}
	
	@Test
	public void unlockHundredScoreAchievement() {
		
		Player player = new Player(2, "Teszt2", 100, 0, 10);
		
		assertTrue(player.hasThePlayerReachedHundredPoints());
	}
	
	@Test
	public void unlockHistoryAchievement() {
		
		Player player = new Player(3, "Teszt3", 100, 90, 0);
		
		assertTrue(player.hasThePlayerReachedNinetyPointFromHistory());
	}
	
	@Test
	public void unlockScienceAchievement() {
		
		Player player = new Player(4, "Teszt4", 180, 0, 90);
		
		assertTrue(player.hasThePlayerReachedNinetyPointFromScience());
	}
	
}
