package hu.bence.jatek.model.kerdes;

import java.util.HashMap;
import java.util.Map;

/**
 * A kérdések típusát beazonosító enum.
 * 
 * @author erosbencee
 *
 */
public enum QuestionType {

	/**
	 * A négy válaszlehetőséggel rendelkező kérdéseket azonosító típus.
	 */
	CHOOSER(1),
    /**
     * Az igaz-hamis feladványokat azonosító típus.
     */
    TRUE_OR_FALSE(2),
    /**
     * A billentyűzetről választ beolvasó feladványokat azonosító típus.
     */
    SCAN_ANSWER(3);
	
	private int value;
	private static Map<Object, Object> map = new HashMap<>();
	 
	static {
		for (QuestionType pageType : QuestionType .values()) {
			map.put(pageType.value, pageType);
		}
	}

	/**
	 * Visszaadja a bemenő int értékből leképezett enumot.
	 * 
	 * @param pageType egy az adatbázisban szereplő típusazonosító.
	 * @return a kiválasztott típusazonosítónak megfelelő enum.
	 */
	public static QuestionType valueOf(int pageType) {
		return (QuestionType) map.get(pageType);
	}

	private QuestionType(int value) {
		this.value = value;
	}
}
