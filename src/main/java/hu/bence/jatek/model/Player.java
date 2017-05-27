package hu.bence.jatek.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Egy játékost reprezentáló osztály. 
 * 
 * @author erosbencee
 *
 */

@Entity
@Table(name = "player")
public class Player  implements Serializable {

	@Id
	@Column(name="ID")
	private int playerId;
	
	@NotNull
	@NotEmpty
	@Column(name="NAME",nullable = false)
	private String userName;
	
	@Column(name="SCORE",nullable = false)
	private int score;
	
	@Column(name="HISTORY_SCORE",nullable = false)
	private int historyScore;
	
	@Column(name="SCIENCE_SCORE",nullable = false)
	private int scienceScore;

	/**
	 * Alapértelmezett konstruktor.
	 */
	public Player() {
		
	}
	
	/**
	 * Konstruktor egy Player objektum létrehozásához.
	 * 
	 * @param id	a játékos azonosítója.
	 * @param name	a játékos neve.
	 * @param score	a játékosnak a játék során összegyűjtött pontja.
	 * @param historyScore a játékosnak a játék során történelem kategóriákból összegyűjtött pontja.
	 * @param scienceScore a játékosnak a játék során a természettudomány kategóriákból összegyűjtött pontja.
	 */
	
	public Player(int id, String name, int score, int historyScore, int scienceScore) {
		super();
		this.playerId = id;
		this.userName =  name;
		this.score = score;
		this.historyScore = historyScore;
		this.scienceScore = scienceScore;

	}
	
	/**
	 * Visszaadja a játékos egyedi azonosítóját.
	 * 
	 * @return a játékos egyedi azonosítója.
	 */
	
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Beállítja a játékos egyedi azonosítóját.
	 * 
	 * @param playerId a játékos egyedi azonosítója.
	 */
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * Visszaadja a játékos felhasználónevét.
	 * 
	 * @return a játékos felhasználóneve.
	 */
	
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Beállítja a játékos felhasználónevét.
	 * 
	 * @param userName a játékos felhasználóneve.
	 */

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Visszaadja a játékos aktuális pontszámát.
	 * 
	 * @return a játékos aktuális pontszáma.
	 */

	public int getScore() {
		return score;
	}
	
	/**
	 * Frissíti a játékos jelenlegi pontszámát.
	 * 
	 * @param score a játékos aktuális pontszáma.
	 */

	public void setScore(int score) {
		this.score = score;
	}

	
	
	/**
	 * Visszaadja a játékos aktuális pontszámát, melyet történelem kategóriájú kérdések megválaszolásával szerzett.
	 * 
	 * @return historyScore a játékos történelem kategóriában szerzett pontjai.
	 */
	public int getHistoryScore() {
		return historyScore;
	}

	/**
	 * Beállítja a játékos aktuális pontszámát, ha a játékos történelmi kategórában válaszolt meg kérdéseket.
	 * 
	 * @param historyScore a játékos történelmi kategóriában szerzett új pontjai.
	 */
	public void setHistoryScore(int historyScore) {
		this.historyScore = historyScore;
	}

	/**
	 * Visszaadja a játékos aktuális pontszámát, melyet természettudományos kategóriájú kérdések megválaszolásával szerzett.
	 * 
	 * @return a játékos természettudományos kérdések megválaszolásával szerzett pontjai.
	 */
	public int getScienceScore() {
		return scienceScore;
	}

	/**
	 * Beállítja a játékos természettudomány kategóriában szerzett pontjait.
	 * 
	 * @param scienceScore a játékos természettudomány kategóriában megszerzett új pontjai.
	 */
	public void setScienceScore(int scienceScore) {
		this.scienceScore = scienceScore;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (score != other.score)
			return false;

		if (playerId != other.playerId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/**
	 * Függvény, amely megvizsgálja, hogy a játékos megszerzett-e már legalább 5 pontot.
	 * 
	 * @return a játékos megszerzett-e már legalább 5 pontot.
	 */

	public boolean hasThePlayerAnsweredHisFirstQuestion() {
		if(this.getScore() >= 5) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Függvény, amely megvizsgálja, hogy a játékos megszerzett-e már legalább 100 pontot.
	 * 
	 * @return a játékos elért-e már 100 pontot.
	 */
	
	public boolean hasThePlayerReachedHundredPoints() {
		if(this.getScore() >= 100) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Függvény, amely megvizsgálja, hogy a játékos megszerzett-e már legalább 90 pontot történelmi kategóriában.
	 * 
	 * @return igazat ad, ha a játékos legalább 90 pontot összegyűjtött történelem kategóriában.
	 */
	
	public boolean hasThePlayerReachedNinetyPointFromHistory() {
		if(this.getHistoryScore() >= 90) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Függvény, amely megvizsgálja, hogy a játékos megszerzett-e már legalább 90 pontot természettudományos kategóriában.
	 * 
	 * @return igazat ad, ha a játékos legalább 90 pontot összegyűjtött természettudomány kategóriában.
	 */
	
	public boolean hasThePlayerReachedNinetyPointFromScience() {
		if(this.getScienceScore() >= 90) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("A játékos [azonosítója= ");
		builder.append(playerId);
		builder.append(", neve= ");
		builder.append(userName);
		builder.append(", elért pontszáma= ");
		builder.append(score);
		builder.append("]");
		return builder.toString();
	}
	
	
}
