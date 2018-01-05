package lagerverwaltung;

public class Mitarbeiter {

	private final String ID;
	private String name;

	public Mitarbeiter(String id, String name) {
		this.ID = id;
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

}
