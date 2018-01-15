/**
 * Klasse für die Mitarbeiter.
 * @author Phil Schneider und Jakob Burger
 *
 */
package lagerverwaltung;

public class Mitarbeiter {

	private final String ID;
	private String name;

	/**
	 * Konstruktor für die Klasse Mitarbeiter
	 * @param id Eindeutige ID, die den Mitarbeiter identifiziert
	 * @param name Name des Mitarbeiters (Vorname Name)
	 */
	public Mitarbeiter(String id, String name) {
		this.ID = id;
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

}
