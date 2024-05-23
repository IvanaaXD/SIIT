package rs.ac.uns.ftn.db.jdbc.theatre.model;

public class Play {
	private int id;
	private String name;
	private String duration;
	private int year;

	public Play() {
		super();
	}

	public Play(int id) {
		this.id = id;
	}

	public Play(int id, String name, String duration, int year) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		result = prime * result + id;
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
		Play other = (Play) obj;
		if (year != other.year)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (duration != other.duration)
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return String.format("%-6d %-30.30s %-10.10s %-10d", id, name, duration, year);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-30.30s %-10.10s %-10s", "ID_PL", "NAME_PL", "DURATION_PL", "YEAR_PL");
	}

}
