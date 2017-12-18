package lagerverwaltung;

public class Lagerposten {
	private int lagerbestand;
	private double preis;

	public Lagerposten(Artikel keinPlan, int lagerbestand, double preis) {
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}

}
