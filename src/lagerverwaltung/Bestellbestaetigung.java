package lagerverwaltung;

/**
 * Bestellbestaetigung dient zur Best�tigung, falls die Bestellung gegl�ckt ist
 * @author Phil Schneider und Jakob Burger
 *
 */
public class Bestellbestaetigung {
	
	private final boolean AUSGEFUEHRT;
	private final double GESAMTPREIS;
	
	/**
	 * Konstruktor der Bestellbest�digung initialisiert
	 * @param ausgefuehrt gibt an, fals die Bestellung ausgef�hrt wurde
	 * @param gesamtpreis gibt den Gesamtpreis der Bestellung an
	 */
	public Bestellbestaetigung (boolean ausgefuehrt, double gesamtpreis) {
		this.AUSGEFUEHRT=ausgefuehrt;
		this.GESAMTPREIS=gesamtpreis;
	}
	

}
