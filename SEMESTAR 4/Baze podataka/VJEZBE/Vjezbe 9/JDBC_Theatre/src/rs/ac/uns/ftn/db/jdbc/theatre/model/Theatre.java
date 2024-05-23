package rs.ac.uns.ftn.db.jdbc.theatre.model;

public class Theatre {
	private int id;
	private String name;
	private String address;
	private String website;
	private int placeId;

	public Theatre() {
		super();
	}


	public Theatre(int id, String name, String address, String website, int placeId) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.website = website;
		this.placeId = placeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + placeId;
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Theatre other = (Theatre) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (placeId != other.placeId)
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		return String.format("%-6d %-37.37s %-30.30s %-30.30s %-30.30s", id, name, address, website,
				placeId);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s", "ID", "NAZIV", "ADRESA", "SAJT", "ID MESTA");
	}

}
