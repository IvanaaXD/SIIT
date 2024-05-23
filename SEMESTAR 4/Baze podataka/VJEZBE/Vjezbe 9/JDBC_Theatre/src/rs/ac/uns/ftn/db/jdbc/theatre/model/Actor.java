package rs.ac.uns.ftn.db.jdbc.theatre.model;

import java.util.Date;

public class Actor {
	private int id;
	private String name;
	private Date dob;
	private boolean status;
	private double salary;
	private double bonus;
	private int theatreId;

	public Actor() {
		super();
	}

	public Actor(int id, String name, Date dob, boolean status, double salary, double bonus, int theatreId) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.status = status;
		this.salary = salary;
		this.bonus = bonus;
		this.theatreId = theatreId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		long temp;
		temp = Double.doubleToLongBits(bonus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + theatreId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + id;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (status ? 1231 : 1237);
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
		Actor other = (Actor) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (Double.doubleToLongBits(bonus) != Double.doubleToLongBits(other.bonus))
			return false;
		if (theatreId != other.theatreId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (status != other.status)
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int teahtreId) {
		this.theatreId = teahtreId;
	}

	@Override
	public String toString() {
		return String.format("%-6d %-10d %-20.20s %-10d %-10d %-10d %-10d", id, name, dob.toString(), status, salary, bonus, theatreId);
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-10s %-20s %-10s %-10s %-10s %-10s", "ID", "IME", "DATUM RODJENJA", "STATUS", "PLATA", "BONUS", "ID POZORISTA");
	}

	

}
