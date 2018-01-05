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
