package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {

	private Set<String> berechtigteMitarbeiter;
	private List<Lagerposten> lagerposten;
	PrintWriter writer;
	

	public Lagerverwaltung() {
		berechtigteMitarbeiter= new HashSet<>();
		lagerposten = new ArrayList<>();
		try {
			writer = new PrintWriter(new FileWriter("LogFile.txt", true));
		} catch (IOException ioe ) {
			System.out.println("Datei kann nicht gelesen werden.");
		}		
	}


	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.add(mitarbeiter.getID());
		writer.println(LocalDate.now() + " Berechtigung erteilt an: " + mitarbeiter);

	}

	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		berechtigteMitarbeiter.remove(mitarbeiter.getID());
		writer.println(LocalDate.now() + " " + LocalTime.now() + " Berechtigung entzogen von: " + mitarbeiter);

	}
	
	
	/**
	 * Gibt den Aktuellen Lagerbestand mit den Namen des Artikels.
	 */
	public void lagerbestandAusgeben() {
		Artikel artikel;
		for (Lagerposten lposten:lagerposten){
			artikel = lposten.getArtikel();
			System.out.println(LocalDate.now() + " " + LocalTime.now() +"Der Bestand für den Artikel: " + artikel.getID() + " ist: " + lposten.getLagerbestand());
		}
	}

	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
			boolean vorhanden=false;
			for (Lagerposten lposten:lagerposten){
				if (lposten.getArtikel().equals(artikel))
				{
					vorhanden = true;
					lposten.setLagerbestand(lposten.getLagerbestand()+bestand);
					lposten.setPreis(preis);
					writer.println(LocalDate.now() +" " +  LocalTime.now() + " Wareneingang gebucht (bereits existierender Artikel) von: " + mitarbeiter + " Artikel: " + artikel
							+ " Bestand :" + bestand + " Preis: " + preis);
				}
			}
			if (!vorhanden) {
			new Lagerposten(artikel, bestand, preis);
			writer.println(LocalDate.now() +" " +  LocalTime.now() + " Wareneingang gebucht von: " + mitarbeiter + " Artikel: " + artikel
					+ " Bestand :" + bestand + " Preis: " + preis);
			}
			
		} else {
			System.out.println("Sie haben keine Berechtigung");
		}
		writer.flush();
	}
	
	/**
	 * Führt eine Bestellung aus in dem es überprüft ob der jeweilige Artikel zur verfügungstehen und die Menge vorhanden ist.
	 * @param mitarbeiter, wird zum überprüfen ob der jeweilige Mitarbeiter recchte zum ausführen hat.
	 * @param bestellposten, enthält die Bestellposten welche bestellt werden sollen (List)
	 */
	public void bestellungAusfuehren (Mitarbeiter mitarbeiter, List<Bestellposten> bestellposten) {
		Artikel artikel;
		boolean bestellungausführbar = true;
		if (berechtigteMitarbeiter.contains(mitarbeiter.getID())) {
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
			writer.println(LocalDate.now() +" " +  LocalTime.now() + " Bestellung gebucht von: " + mitarbeiter);
			writer.flush();
		}
		else {
			System.out.println("Sie haben keine Berechtigung");
		}
	
		writer.flush();
	}

	
	public void setLagerposten(Lagerposten lagerposten) {
		this.lagerposten.add(lagerposten);
	}
}
