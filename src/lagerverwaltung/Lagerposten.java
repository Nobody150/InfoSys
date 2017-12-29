package lagerverwaltung;

public class Lagerposten {
	private int lagerbestand;
	private double preis;
	private Artikel artikel;

	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.lagerbestand = lagerbestand;
		this.preis = preis;
		this.artikel = artikel;
	}

}
