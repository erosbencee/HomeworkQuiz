package hu.bence.jatek.model.kerdes;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * Az egyes kategóriák adatbázisban tárolt számazonosítóihoz készített enum.
 * 
 * @author erosbencee
 *
 */
public enum Categories {
	 /**
	 * A történelem kategória könnyű kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (1).
	 */
	HISTORY_EASY (new Pair<>("HistoryEsyBt",1)),
	 /**
	 * A történelem kategória közepes nehézségű kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (3).
	 */
	HISTORY_MEDIUM (new Pair<>("HistoryMediumBt",3)),
	 /**
	 * A történelem kategória nehéz kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (6).
	 */
	HISTORY_HARD (new Pair<>( "HistoryHardBt",6)),
	 /**
	 * A természettudomány kategória könnyű kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (2).
	 */
	SCIENCE_EASY (new Pair<>("ScienceEasyBt",2)),
	 /**
	 * A természettudomány kategória közepes nehézségű kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (5).
	 */
	SCIENCE_MEDIUM (new Pair<>("ScienceMediumBt",5)),
	 /**
	 * A természettudomány kategória nehéz kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (10).
	 */
	SCIENCE_HARD (new Pair<>("ScienceHardBt",10)),
	 /**
	 * Az irodalom kategória kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (7).
	 */
	LITERATURE (new Pair<>("LiteratureBt",7)),
	 /**
	 * A matematika kategória közepes nehézségű kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (4).
	 */
	MATH_MEDIUM (new Pair<>("MathMediumBt",4)),
	 /**
	 * A matematika kategória nehéz kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (8).
	 */
	MATH_HARD (new Pair<>( "MathHardBt",8)),
	 /**
	 * Az informatika kategória kérdéseit azonosító típus, 
	 * hozzá megfeleltetve a kategória gombazonosítója és a megfelelő adatbázisbeli számérték (9).
	 */
	IT (new Pair<>("ITBt",9));
	 
	//Pair<Integer, String> simplePair = new Pair<>(42, "Second");
	private Pair< String, Integer> value;
	private static Map<Object, Object> map = new HashMap<>();
	private static Map<Object, Object> mapInt = new HashMap<>(); 
	
	static {
		for (Categories pageType : Categories.values()) {
			map.put(pageType.value.getKey(), pageType);
			mapInt.put(pageType.value.getValue(), pageType);
		}
	}
	 
	/**
	 * Visszaadja, hogy az enum milyen gombazonosító és azonosítószám értékpárosnak feleltethető meg.
	 * 
	 * @return a kategóriának megfeleltetett gombazonosító és azonosítószám értékpáros.
	 */
	public Pair<String, Integer> getValue() {
		return value;
	}

	/**
	 * Visszaadja a kiválasztott kategóriának megfelelő felsorolási típust.
	 * 
	 * @param pageType a játékos által kiválaszott kategória gombjának azonosítója.
	 * @return a játékos által kiválasztott kategóriának megfelelő felsorolási típus.
	 */
	public static Categories getValueOf(String pageType) {
		return (Categories) map.get(pageType);
		       
	 }
	
	/**
	 * Visszaadja a kiválaszott kategórának megfelelő felsorolási típust.
	 * 
	 * @param pageType a kategóriához rendelt számérték, értéke 1 és 10 közötti szám.
	 * @return játékos által kiválasztott kategóriának megfelelő felsorolási típus.
	 */
	
	public static Categories getValueOf(int pageType) {
		return (Categories) mapInt.get(pageType);
		       
	 }
	 
	private Categories(Pair<String,Integer> value) {
		this.value = value;
	}
	
}
