/**
 * Klasse Lagerposten die einen Artikel beinhaltet. 
 * Jeder Lagerposten ist durch den Artikel eineindeutig.
 * @author Phil Schneider und Jakob Burger
 *
 */

package lagerverwaltung;

public class Lagerposten {
	private int lagerbestand;
	private double preis;
	private Artikel artikel;

	/**
	 * Konstruktor für die Klasse Mitarbeiter
	 * @param artikel Objekt von der Klasse Artikel welches im Lagerposten liegen soll.
	 * @param lagerbestand Anzahl der Artikel (int)
	 * @param preis Preis des Artikels (double)
	 */
	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.lagerbestand = lagerbestand;
		this.preis = preis;
		this.artikel = artikel;
	}

	public int getLagerbestand() {
		return lagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

}
