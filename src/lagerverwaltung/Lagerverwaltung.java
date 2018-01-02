package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {

	private Set<Mitarbeiter> berechtigteMitarbeiter;
	private List<Lagerposten> lagerposten;
	PrintWriter writer;

	public Lagerverwaltung() {

		try {
			writer = new PrintWriter("testFile.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Datei existiert nicht");
		}
	}

	private void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.add(mitarbeiter);
		writer.println(LocalDate.now() + " Berechtigung erteilt an: " + mitarbeiter);

	}

	private void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.remove(mitarbeiter);
		writer.println(LocalDate.now() + " Berechtigung entzogen von: " + mitarbeiter);

	}
	
	private void lagerbestandAusgeben() {
		Artikel artikel;
		for (Lagerposten lposten:lagerposten){
			artikel = lposten.getArtikel();
			System.out.println("Der Bestand für den Artikel: " + artikel.getID() + " ist: " + lposten.getLagerbestand());
		}
	}

	private void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		if (berechtigteMitarbeiter.contains(mitarbeiter)) {
			new Lagerposten(artikel, bestand, preis);
			writer.println(LocalDate.now() + " Wareneingang gebucht von: " + mitarbeiter + " Artikel: " + artikel
					+ " Bestand :" + bestand + " Preis: " + preis);
		} else {
			System.out.println("Sie haben keine Berechtigung");
		}

	}
	
	private void bestellungAusfuehren (Mitarbeiter mitarbeiter, List<Bestellposten> bestellposten) {
		Artikel artikel;
		boolean bestellungausführbar = true;
		if (berechtigteMitarbeiter.contains(mitarbeiter)) {
			for (Bestellposten bposten:bestellposten) {
				if (bestellungausführbar) {
					for (Lagerposten lposten:lagerposten){
						artikel = lposten.getArtikel();
						if (artikel.getID().equals(bposten.getARTIKELID())) {
							if (lposten.getLagerbestand()<=bposten.getAnzahl()) {
								bestellungausführbar=true;
							}
							else {
								bestellungausführbar = false;
							}
						}
						else {
							bestellungausführbar = false;
						}
					}
				}
			}
		}	
		if (bestellungausführbar) {	
			for (Bestellposten bposten:bestellposten) {
				for (Lagerposten lposten:lagerposten){
					artikel = lposten.getArtikel();
					if (artikel.getID().equals(bposten.getARTIKELID())) {
						lposten.setLagerbestand(lposten.getLagerbestand()-bposten.getAnzahl());
					}
				}
			}
			writer.println(LocalDate.now() + " Bestellung gebucht von: " + mitarbeiter);
		}
		else {
			System.out.println("Sie haben keine Berechtigung");
		}
	
		
	}

	
	public void setLagerposten(Lagerposten lagerposten) {
		this.lagerposten.add(lagerposten);
	}
}
