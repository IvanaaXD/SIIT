package rs.ac.uns.ftn.db.jdbc.theatre.model;

public class Scene {
	private int id;
	private String name;
	private int numOfSeats;
	private int theatreId;

	public Scene() {
		super();
	}

	public Scene(int id, String name, int numOfSeats, int theatreId) {
		this.id = id;
		this.name = name;
		this.numOfSeats = numOfSeats;
		this.theatreId = theatreId;

	}

	public Scene(String name, int numOfSeats, int theatreId) {
		this.name = name;
		this.numOfSeats = numOfSeats;
		this.theatreId = theatreId;
	}

	public Scene(String name, int numOfSeats) {
		this.name = name;
		this.numOfSeats = numOfSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numOfSeats;
		result = prime * result + theatreId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (numOfSeats != other.numOfSeats)
			return false;
		if (theatreId != other.theatreId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	@Override
	public String toString() {
		return String.format("%-8d %-35.35s %-12d %-6d", id, name, numOfSeats, theatreId);
	}

	public static String getFormattedHeader() {
		return String.format("%-8s %-35s %-12s %-6s", "ID SCENE", "NAZIV", "BROJ SEDISTA", "ID POZORISTA");
	}

}
