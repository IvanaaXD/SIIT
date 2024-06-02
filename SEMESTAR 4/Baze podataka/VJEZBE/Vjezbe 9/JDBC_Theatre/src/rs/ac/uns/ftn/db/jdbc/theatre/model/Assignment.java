package rs.ac.uns.ftn.db.jdbc.theatre.model;

import java.util.Date;

public class Assignment {
	private int id;
	private double honorar;
	private Date startDate;
	private Date endDate;
	private int partId;
	private int actorId;

	public Assignment() {
		super();
	}

	public Assignment(int id, double honorar, Date startDate, Date endDate, int actorId, int partId) {
		super();
		this.id = id;
		this.honorar = honorar;
		this.startDate = startDate;
		this.endDate = endDate;
		this.partId = partId;
		this.actorId = actorId;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + actorId;
		long temp;
		temp = Double.doubleToLongBits(honorar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		//result = prime * result + ((partId == null) ? 0 : partId.hashCode());
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
		Assignment other = (Assignment) obj;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (actorId != other.actorId)
			return false;
		if (Double.doubleToLongBits(honorar) != Double.doubleToLongBits(other.honorar))
			return false;
		if (id != other.id)
			return false;
		if (partId == 0) {
			if (other.partId != 0)
				return false;
		} else if (partId != other.partId)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHonorar() {
		return honorar;
	}

	public void setHonorar(double honorar) {
		this.honorar = honorar;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	@Override
	public String toString() {
		return String.format("%-6d %-10d %-20.20s %-20.20s %-10d %-10d", id, honorar, startDate.toString(), endDate.toString(), partId, actorId);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-10s %-20s %-20s %-10s %-10s", "ID", "HONORAR", "POCETAK", "KRAJ", "ID ULOGE", "ID GLUMCA");
	}

}
