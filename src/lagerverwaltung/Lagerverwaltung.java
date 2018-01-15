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

	/**
	 * Erteilt einem Mitarbeiter die Berechtigung Wareneingänge zu buchen und
	 * Bestellungen auszuführen.
	 * 
	 * @param mitarbeiter
	 *            Der Mitarbeiter dem die Berechtigung erteilt werden soll.
	 */
	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		if (mitarbeiter != null) {
			if (!berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
				berechtigteMitarbeiter.add(mitarbeiter.getID());
				writer.println(LocalDate.now() + " Berechtigung erteilt an: " + mitarbeiter.getID() + " ("
						+ mitarbeiter.getName() + ")");
			} else {
				System.out.println("Dieser Mitarbieter hat bereits eine Berechtigung");
			}
		} else {
			System.out.println("Mitarbieter existiert nicht");
		}
	}

	/**
	 * Entzieht einem Mitarbeiter die Berechtigung Wareneingänge zu buchen und
	 * Bestellungen auszuführen.
	 * 
	 * @param mitarbeiter
	 *            Der Mitarbeiter dem die Berechtigung entzogen werden soll.
	 */
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		if (mitarbeiter != null) {
			if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
				berechtigteMitarbeiter.remove(mitarbeiter.getID());
				writer.println(LocalDate.now() + " Berechtigung entzogen von: " + mitarbeiter.getID() + " ("
						+ mitarbeiter.getName() + ")");
			} else {
				System.out.println("Dieser Mitarbeiter hatte gar keine Berechtigung");
			}
		} else {
			System.out.println("Mitarbieter existiert nicht");
		}
	}

	/**
	 * Gibt den aktuellen Lagerbestand mit den Namen des Artikel aus.
	 */
	public void lagerbestandAusgeben() {
		if (lagerposten != null) {
			Artikel artikel;
			for (Lagerposten lposten : lagerposten) {
				artikel = lposten.getArtikel();
				System.out.println("Der Bestand für den Artikel: " + artikel.getID() + " (" + artikel.getName() + ")"
						+ " ist: " + lposten.getLagerbestand());
			}
		} else {
			System.out.println("Lagerposten existiert nicht");
		}
	}

	/**
	 * Bucht einen Wareneingang. Falls der Artikel bereits im Lagersystem existiert,
	 * aktualisiert die Methode Preis und Bestand.Falls nicht erstellt sie einen
	 * neuen Lagerposten mit dem Artikel.
	 * 
	 * @param mitarbeiter
	 *            Der Mitarbeiter der den Wareneingang buchen soll.
	 * @param artikel
	 *            Der Artikel der gebucht werden soll.
	 * @param bestand
	 *            Die Anzahl an Artikeln die gebucht werden sollen.
	 * @param preis
	 *            Der Preis mit dem ein Artikel gebucht werden soll.
	 */
	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		if (artikel != null && mitarbeiter != null) {
			if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
				boolean vorhanden = false;
				for (Lagerposten lposten : lagerposten) {
					if (lposten.getArtikel().equals(artikel)) {
						vorhanden = true;
						lposten.setLagerbestand(lposten.getLagerbestand() + bestand);
						lposten.setPreis(preis);
						writer.println(LocalDate.now() + " Wareneingang gebucht (bereits existierender Artikel) von: "
								+ mitarbeiter.getID() + " (" + mitarbeiter.getName() + ")" + " Artikel: "
								+ artikel.getID() + " (" + artikel.getName() + ")" + " Bestand: " + bestand + " Preis: "
								+ preis + "€");
					}
				}
				if (!vorhanden) {
					new Lagerposten(artikel, bestand, preis);
					writer.println(LocalDate.now() + " Wareneingang gebucht von: " + mitarbeiter.getID() + " ("
							+ mitarbeiter.getName() + ")" + " Artikel: " + artikel.getID() + " (" + artikel.getName()
							+ ")" + " Bestand: " + bestand + " Preis: " + preis + "€");
				}

			} else {
				System.out.println("Sie haben keine Berechtigung");
			}
			writer.flush();
		} else {
			System.out.println("Warenbuchung fehlgeschlagen");
		}
	}

	/**
	 * Führt eine Bestellung aus in dem es überprüft ob der jeweilige Artikel zur
	 * Verfügung stehen und die Menge vorhanden ist.
	 * 
	 * @param mitarbeiter
	 *            Wird zum Überprüfen ob der jeweilige Mitarbeiter Rechte zum
	 *            ausführen hat.
	 * @param bestellposten
	 *            Enthält die Bestellposten welche bestellt werden sollen (List)
	 */
	public void bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bestellposten) {
		if (mitarbeiter != null && bestellposten != null && lagerposten != null) {
			Artikel artikel;
			boolean bestellungausführbar = true;
			if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
				for (Bestellposten bposten : bestellposten) {
					if (bestellungausführbar) {
						for (Lagerposten lposten : lagerposten) {
							artikel = lposten.getArtikel();
							if (artikel.getID().equals(bposten.getARTIKELID())) {
								if (lposten.getLagerbestand() <= bposten.getAnzahl()) {
									bestellungausführbar = true;
								} else {
									bestellungausführbar = false;
								}
							} else {
								bestellungausführbar = false;
							}
						}
					}
				}
				if (bestellungausführbar) {
					for (Bestellposten bposten : bestellposten) {
						for (Lagerposten lposten : lagerposten) {
							artikel = lposten.getArtikel();
							if (artikel.getID().equals(bposten.getARTIKELID())) {
								lposten.setLagerbestand(lposten.getLagerbestand() - bposten.getAnzahl());
							}
						}
					}
					writer.println(LocalDate.now() + " Bestellung gebucht von Mitarbeiter: " + mitarbeiter.getID()
							+ " (" + mitarbeiter.getName() + ")");
					writer.flush();
				} else {
					System.out.println("Lagerbestand nicht ausreichend");
				}
			} else {
				System.out.println("Sie haben keine Berechtigung");
			}
			writer.flush();
		} else {
			System.out.println("Bestellvorgang fehlgeschlagen");
		}
	}

	public void setLagerposten(Lagerposten lagerposten) {
		this.lagerposten.add(lagerposten);
	}
}
