package rs.ac.uns.ftn.db.jdbc.theatre.model;

public class Role {
	private int id;
	private String name;
	private String gender;
	private String type;
	private int playId;

	public Role() {
		super();
	}

	public Role(int id, String name, String gender, String type, int playId) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.type = type;
		this.playId = playId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + playId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (playId != other.playId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (gender != other.gender)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayId() {
		return playId;
	}

	public void setPlayId(int playId) {
		this.playId = playId;
	}

	@Override
	public String toString() {
		return String.format("%-6d %-15.15s %-10s %-10s %-10d", id, name, gender, type, playId);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-15s %-10s %-10s %-10s", "ID", "NAZIV", "POL", "TIP", "ID PREDSTAVE");
	}

}
