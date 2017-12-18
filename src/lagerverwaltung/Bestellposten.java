package lagerverwaltung;

public class Bestellposten {

	private final String ARTIKELID;
	private int anzahl;

	public Bestellposten(String artikelID, int anzahl) {
		this.ARTIKELID = artikelID;
		this.anzahl = anzahl;
	}

}
