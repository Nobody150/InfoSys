package lagerverwaltung;
import java.util.*;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Lagerposten> lagerposten = new ArrayList<>();
		lagerposten.add(new Lagerposten(new Artikel("001","Produkt1","angeben1"),50,0.5));
		lagerposten.add(new Lagerposten(new Artikel("002","Produkt2","angeben2"),45,0.6));
		lagerposten.add(new Lagerposten(new Artikel("003","Produkt3","angeben3"),40,0.7));
		lagerposten.add(new Lagerposten(new Artikel("004","Produkt4","angeben4"),35,0.8));
		lagerposten.add(new Lagerposten(new Artikel("005","Produkt5","angeben5"),30,0.9));
		lagerposten.add(new Lagerposten(new Artikel("006","Produkt6","angeben6"),25,1.0));
		lagerposten.add(new Lagerposten(new Artikel("007","Produkt7","angeben7"),20,1.1));
		lagerposten.add(new Lagerposten(new Artikel("008","Produkt8","angeben8"),15,1.2));
		
		
		List<Mitarbeiter> mitarbeiter = new ArrayList<>();
		mitarbeiter.add(new Mitarbeiter("001","Peter Müller"));
		mitarbeiter.add(new Mitarbeiter("002","Max Mustermann"));
		mitarbeiter.add(new Mitarbeiter("003","Erika Mustermann"));
		
		List<Bestellposten> bestellposten = new ArrayList<>();
		bestellposten.add(new Bestellposten("001",20));
		
		//...
	}

}
