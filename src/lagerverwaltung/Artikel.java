package lagerverwaltung;

/**
 * Klasse Artikel die 1 oder 0 in Lagerposten vorhanden ist
 * @author Phil Schneider und Jakob Burger
 *
 */

public class Artikel {

	private final String ID;
	private String name;
	private String beschreibung;

	/**
	 * Konstruktor für die Klasse Artikel
	 * @param id Eindeutige ID die den Artikel identifiziert 
	 * @param name Name des Artikels (String)
	 * @param beschreibung Beschreibung des Artikels (String)  
	 */
	public Artikel(String id, String name, String beschreibung) {
		this.ID = id;
		this.name = name;
		this.beschreibung = beschreibung;

	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

}
