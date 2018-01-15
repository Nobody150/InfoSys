package lagerverwaltung;

/**
 * Klasse Bestellposten gibt die einzelen Artikel an, welche in einer Bestellung bestellt wurden.
 * @author Phil Schneider und Jakob BurgerPhil
 *
 */
public class Bestellposten {

	private final String ARTIKELID;
	private int anzahl;

	/**
	 * Konstruktor für Bestellposten
	 * @param artikelID welche Bestellt werden soll
	 * @param anzahl welche bestellt werden sol
	 */
	public Bestellposten(String artikelID, int anzahl) {
		this.ARTIKELID = artikelID;
		this.anzahl = anzahl;
	}

	public String getARTIKELID() {
		return ARTIKELID;
	}

	public int getAnzahl() {
		return anzahl;
	}

}
