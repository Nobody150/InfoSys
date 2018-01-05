package lagerverwaltung;

import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung lagerver = new Lagerverwaltung();

		ArrayList<Artikel> artikel = new ArrayList<>();
		artikel.add(new Artikel("001", "Zettel", "angeben1"));
		artikel.add(new Artikel("002", "Notizzettel", "angeben2"));
		artikel.add(new Artikel("003", "kleiner Notizzettel", "angeben3"));
		artikel.add(new Artikel("004", "besonders geiler Notizzettel", "angeben4"));
		artikel.add(new Artikel("005", "farbiger Notizzettel", "angeben5"));

		ArrayList<Lagerposten> lagerposten = new ArrayList<>();
		lagerposten.add(new Lagerposten(artikel.get(0), 50, 0.5));
		lagerposten.add(new Lagerposten(artikel.get(1), 45, 0.6));
		lagerposten.add(new Lagerposten(artikel.get(2), 40, 0.7));
		lagerposten.add(new Lagerposten(artikel.get(3), 35, 0.8));
		lagerposten.add(new Lagerposten(artikel.get(4), 30, 0.9));

		ArrayList<Mitarbeiter> mitarbeiter = new ArrayList<>();
		mitarbeiter.add(new Mitarbeiter("001", "Peter Meier"));
		mitarbeiter.add(new Mitarbeiter("002", "Max Mustermann"));
		mitarbeiter.add(new Mitarbeiter("003", "Erika Mustermann"));

		ArrayList<Bestellposten> bestellposten = new ArrayList<>();
		bestellposten.add(new Bestellposten("001", 20));
		bestellposten.add(new Bestellposten("002", 88));
		bestellposten.add(new Bestellposten("003", 3));

		ArrayList<Bestellposten> bestellposten2 = new ArrayList<>();
		bestellposten.add(new Bestellposten("001", 20));
		bestellposten.add(new Bestellposten("002", 8));
		bestellposten.add(new Bestellposten("003", 3));

		for (Lagerposten lposten : lagerposten) {

			if (lposten != null) {
				lagerver.setLagerposten(lposten);
			}
		}

		// berechtigung erteilen
		lagerver.berechtigungErteilen(mitarbeiter.get(0));
		// berechtigung erteilen
		lagerver.berechtigungErteilen(mitarbeiter.get(1));
		// berechtigung entziehen
		lagerver.berechtigungZurueckziehen(mitarbeiter.get(1));
		// Lagerbestand ausgeben
		lagerver.lagerbestandAusgeben();
		// Bucht einen Wareneingang
		lagerver.wareneingangBuchen(mitarbeiter.get(0), artikel.get(0), 15, 0.7); // Artikel existiert bereits mit
																					// anderem Preis
		lagerver.wareneingangBuchen(mitarbeiter.get(0), new Artikel("006", "Produkt6", "angeben6"), 15, 0.5);
		lagerver.wareneingangBuchen(mitarbeiter.get(1), artikel.get(1), 35, 1.5);
		lagerver.wareneingangBuchen(mitarbeiter.get(1), new Artikel("006", "Produkt6", "angeben6"), 15, 0.5);
		// führt eine Bestellung aus
		lagerver.bestellungAusfuehren(mitarbeiter.get(0), bestellposten);
		lagerver.bestellungAusfuehren(mitarbeiter.get(0), bestellposten2);
		lagerver.bestellungAusfuehren(mitarbeiter.get(1), bestellposten);
		lagerver.bestellungAusfuehren(mitarbeiter.get(1), bestellposten2);

		System.out.println("Preis von Artikel mit Preisänderung: " + lagerposten.get(0).getPreis());

		// funktioniert nicht

	}

}
