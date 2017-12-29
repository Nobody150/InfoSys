package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Set;

public class Lagerverwaltung {

	private Set<Mitarbeiter> berechtigteMitarbeiter;
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

	private void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int bestand, double preis) {
		if (berechtigteMitarbeiter.contains(mitarbeiter)) {
			new Lagerposten(artikel, bestand, preis);
			writer.println(LocalDate.now() + " Wareneingang gebucht von: " + mitarbeiter + " Artikel: " + artikel
					+ " Bestand :" + bestand + " Preis: " + preis);
		} else {
			System.out.println("Sie haben keine Berechtigung");
		}

	}

}
