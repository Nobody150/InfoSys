package lagerverwaltung;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {

	private Set<String> berechtigteMitarbeiter;
	private List<Lagerposten> lagerposten;
	PrintWriter writer;

	public Lagerverwaltung() {
		berechtigteMitarbeiter = new HashSet<>();
		lagerposten = new ArrayList<>();
		try {
			writer = new PrintWriter(new FileWriter("LogFile.txt", true));
			writer.println("_________________________________________________________");
		} catch (IOException ioe) {
			System.out.println("Datei kann nicht gelesen werden.");
		}
	}

	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.add(mitarbeiter.getID());
		writer.println(LocalDate.now() + " " + " Berechtigung erteilt an: " + mitarbeiter.getID() + " ("
				+ mitarbeiter.getName() + ")");

	}

	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.remove(mitarbeiter.getID());
		writer.println(LocalDate.now() + " " + " Berechtigung entzogen von: " + mitarbeiter.getID() + " ("
				+ mitarbeiter.getName() + ")");

	}

	/**
	 * Gibt den Aktuellen Lagerbestand mit den Namen des Artikels.
	 */
	public void lagerbestandAusgeben() {
		Artikel artikel;
		for (Lagerposten lposten : lagerposten) {
			artikel = lposten.getArtikel();
			System.out.println(LocalDate.now() + " " + "Der Bestand f�r den Artikel: " + artikel.getID() + " ("
					+ artikel.getName() + ")" + " ist: " + lposten.getLagerbestand());
		}
	}

	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
			boolean vorhanden = false;
			for (Lagerposten lposten : lagerposten) {
				if (lposten.getArtikel().equals(artikel)) {
					vorhanden = true;
					lposten.setLagerbestand(lposten.getLagerbestand() + bestand);
					lposten.setPreis(preis);
					writer.println(LocalDate.now() + " " + " Wareneingang gebucht (bereits existierender Artikel) von: "
							+ mitarbeiter.getID() + " (" + mitarbeiter.getName() + ")" + " Artikel: " + artikel.getID()
							+ " (" + artikel.getName() + ")" + " Bestand :" + bestand + " Preis: " + preis);
				}
			}
			if (!vorhanden) {
				new Lagerposten(artikel, bestand, preis);
				writer.println(LocalDate.now() + " " + " Wareneingang gebucht von: " + mitarbeiter.getID() + " ("
						+ mitarbeiter.getName() + ")" + " Artikel: " + artikel.getID() + " (" + artikel.getName() + ")"
						+ " Bestand :" + bestand + " Preis: " + preis);
			}

		} else {
			System.out.println("Sie haben keine Berechtigung");
		}
		writer.flush();
	}

	/**
	 * F�hrt eine Bestellung aus in dem es �berpr�ft ob der jeweilige Artikel zur
	 * verf�gungstehen und die Menge vorhanden ist.
	 * 
	 * @param mitarbeiter,
	 *            wird zum �berpr�fen ob der jeweilige Mitarbeiter recchte zum
	 *            ausf�hren hat.
	 * @param bestellposten,
	 *            enth�lt die Bestellposten welche bestellt werden sollen (List)
	 */
	public void bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bestellposten) {
		Artikel artikel;
		boolean bestellungausf�hrbar = true;
		if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
			for (Bestellposten bposten : bestellposten) {
				if (bestellungausf�hrbar) {
					for (Lagerposten lposten : lagerposten) {
						artikel = lposten.getArtikel();
						if (artikel.getID().equals(bposten.getARTIKELID())) {
							if (lposten.getLagerbestand() <= bposten.getAnzahl()) {
								bestellungausf�hrbar = true;
							} else {
								bestellungausf�hrbar = false;
							}
						} else {
							bestellungausf�hrbar = false;
						}
					}
				}
			}
			if (bestellungausf�hrbar) {
				for (Bestellposten bposten : bestellposten) {
					for (Lagerposten lposten : lagerposten) {
						artikel = lposten.getArtikel();
						if (artikel.getID().equals(bposten.getARTIKELID())) {
							lposten.setLagerbestand(lposten.getLagerbestand() - bposten.getAnzahl());
						}
					}
				}
				writer.println(LocalDate.now() + " " + " Bestellung gebucht von Mitarbeiter: " + mitarbeiter.getID()
						+ " (" + mitarbeiter.getName() + ")");
				writer.flush();
			}
		} else {
			System.out.println("Sie haben keine Berechtigung");
		}
		writer.flush();
	}

	public void setLagerposten(Lagerposten lagerposten) {
		this.lagerposten.add(lagerposten);
	}
}
