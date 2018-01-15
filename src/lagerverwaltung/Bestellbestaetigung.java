package lagerverwaltung;

/**
 * Bestellbestaetigung dient zur Bestätigung, falls die Bestellung geglückt ist
 * @author Phil Schneider und Jakob Burger
 *
 */
public class Bestellbestaetigung {
	
	private final boolean AUSGEFUEHRT;
	private final double GESAMTPREIS;
	
	/**
	 * Konstruktor der Bestellbestädigung initialisiert
	 * @param ausgefuehrt gibt an, fals die Bestellung ausgeführt wurde
	 * @param gesamtpreis gibt den Gesamtpreis der Bestellung an
	 */
	public Bestellbestaetigung (boolean ausgefuehrt, double gesamtpreis) {
		this.AUSGEFUEHRT=ausgefuehrt;
		this.GESAMTPREIS=gesamtpreis;
	}
	

}
